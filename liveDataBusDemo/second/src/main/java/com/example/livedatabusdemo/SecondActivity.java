package com.example.livedatabusdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

/*****************************************************************
 * * File: - SecondActivity
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/12/7
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/12/7    1.0         create
 ******************************************************************/
public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(false);
            }
        });
    }

    public void setValue(boolean value) {
        LiveDataBus.get().with(BusCon.KEY_TEST).setValue(value);
    }
}
