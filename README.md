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