# ViewModule
* 1.定义了ViewModel的基类，并建议通过持有LiveData维护保存数据的状态；
* 2.ViewModel不会随着Activity的屏幕旋转而销毁，减少了维护状态的代码成本（数据的存储和读取、序列化和反序列化）;
* 3.在对应的作用域内，保正只生产出对应的唯一实例，多个Fragment维护相同的数据状态，极大减少了UI组件之间的数据传递的代码成本。

setRetainInstance(boolean) 是Fragment中的一个方法。将这个方法设置为true就可以使当前Fragment在Activity重建时存活下来

# LifeData
LiveData是如何避免内存泄漏的

质就是利用了Jetpack 架构组件中的另外一个成员—— Lifecycle。

observe->LifecycleBoundObserver

	class LifecycleBoundObserver extends ObserverWrapper implements GenericLifecycleObserver {
        @NonNull
        final LifecycleOwner mOwner;

        LifecycleBoundObserver(@NonNull LifecycleOwner owner, Observer<? super T> observer) {
            super(observer);
            mOwner = owner;
        }

        @Override
        boolean shouldBeActive() {
            return mOwner.getLifecycle().getCurrentState().isAtLeast(STARTED);
        }

        @Override
        public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
            if (mOwner.getLifecycle().getCurrentState() == DESTROYED) {
                removeObserver(mObserver);
                return;
            }
            activeStateChanged(shouldBeActive());
        }

        @Override
        boolean isAttachedTo(LifecycleOwner owner) {
            return mOwner == owner;
        }

        @Override
        void detachObserver() {
            mOwner.getLifecycle().removeObserver(this);
        }
    }

observerForever->AlwaysActiveObserver

	private class AlwaysActiveObserver extends ObserverWrapper {

        AlwaysActiveObserver(Observer<? super T> observer) {
            super(observer);
        }

        @Override
        boolean shouldBeActive() {
            return true;
        }
    }

## LiveDataBus
利用LiveData避免内存泄漏的危险，又是观察者模式可以做到更新数据的通知，但是有个问题是只要注册了，发送过的数据也会受到通知改变，这点需要改动,LiveData.ObserverWrapper的activeStateChanged->dispatchingValue->considerNotify

	private void considerNotify(ObserverWrapper observer) {
	        if (!observer.mActive) {
	            return;
	        }
	        // Check latest state b4 dispatch. Maybe it changed state but we didn't get the event yet.
	        //
	        // we still first check observer.active to keep it as the entrance for events. So even if
	        // the observer moved to an active state, if we've not received that event, we better not
	        // notify for a more predictable notification order.
	        if (!observer.shouldBeActive()) {
	            observer.activeStateChanged(false);
	            return;
	        }
	        if (observer.mLastVersion >= mVersion) {
	            return;
	        }
	        observer.mLastVersion = mVersion;
	        //noinspection unchecked
	        observer.mObserver.onChanged((T) mData);
	}

总结一下发生的核心原因。对于LiveData，其初始的version是-1，当我们调用了其setValue或者postValue，其vesion会+1；对于每一个观察者的封装ObserverWrapper，其初始version也为-1，也就是说，每一个新注册的观察者，其version为-1；当LiveData设置这个ObserverWrapper的时候，如果LiveData的version大于ObserverWrapper的version，LiveData就会强制把当前value推送给Observer。

	private static class BusMutableLiveData<T> extends MutableLiveData<T> {
	
	        private Map<Observer, Observer> observerMap = new HashMap<>();
	
	        @Override
	        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
	            super.observe(owner, observer);
	            try {
	                hook(observer);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
			...
			 private void hook(@NonNull Observer<T> observer) throws Exception {
	            //get wrapper's version
	            Class<LiveData> classLiveData = LiveData.class;
	            Field fieldObservers = classLiveData.getDeclaredField("mObservers");
	            fieldObservers.setAccessible(true);
	            Object objectObservers = fieldObservers.get(this);
	            Class<?> classObservers = objectObservers.getClass();
	            Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
	            methodGet.setAccessible(true);
	            Object objectWrapperEntry = methodGet.invoke(objectObservers, observer);
	            Object objectWrapper = null;
	            if (objectWrapperEntry instanceof Map.Entry) {
	                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
	            }
	            if (objectWrapper == null) {
	                throw new NullPointerException("Wrapper can not be bull!");
	            }
	            Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();
	            Field fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion");
	            fieldLastVersion.setAccessible(true);
	            //get livedata's version
	            Field fieldVersion = classLiveData.getDeclaredField("mVersion");
	            fieldVersion.setAccessible(true);
	            Object objectVersion = fieldVersion.get(this);
	            //set wrapper's version
	            fieldLastVersion.set(objectWrapper, objectVersion);
	        }
	    }
	}

observeForever，把ObserverWrapper传给observeForever，那么在回调的时候我们去检查调用栈，如果回调是observeForever方法引起的，那么就不回调真正的订阅者。

	@MainThread
    public void observeForever(@NonNull Observer<? super T> observer) {
        assertMainThread("observeForever");
        AlwaysActiveObserver wrapper = new AlwaysActiveObserver(observer);
        ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);
        if (existing != null && existing instanceof LiveData.LifecycleBoundObserver) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }
        wrapper.activeStateChanged(true);
    }