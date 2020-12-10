package com.example.livedatabusdemo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

/*****************************************************************
 * * File: - ThirdActivity
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/12/10
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/12/10    1.0         create
 ******************************************************************/
public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        LiveDataBus.get().with(BusCon.KEY_TEST11, Boolean.class)
                .observeForever(new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        Log.d(TAG, "observeForever key_test=" + aBoolean);
                    }
                });
    }
}
