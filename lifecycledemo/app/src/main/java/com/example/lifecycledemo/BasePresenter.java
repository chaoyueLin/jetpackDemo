package com.example.lifecycledemo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/*****************************************************************
 * * File: - BasePresenter
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/9/8
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/8    1.0         create
 ******************************************************************/
public class BasePresenter implements IPresenter {
    private static final String TAG = BasePresenter.class.getSimpleName();

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onCreate=" + this.getClass().toString());
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        Log.d(TAG, "onDestroy=" + this.getClass().toString());
    }

    @Override
    public void onLifecycleChanged(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event) {

    }
}
