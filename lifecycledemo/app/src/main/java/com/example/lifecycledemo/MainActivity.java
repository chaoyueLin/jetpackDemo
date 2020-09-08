package com.example.lifecycledemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private IPresenter mPresenter;

    private MutableLiveData<String> mLiveData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        getLifecycle().addObserver(mPresenter);//添加LifecycleObserver

        mLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, "change=" + s);
            }
        });
//        mLiveData.observeForever( new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Log.d(TAG,"change="+s);
//            }
//        });
        mLiveData.setValue("onCreate");

        findViewById(R.id.tv_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mLiveData.setValue("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLiveData.setValue("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLiveData.setValue("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLiveData.setValue("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLiveData.setValue("onDestroy");
    }
}