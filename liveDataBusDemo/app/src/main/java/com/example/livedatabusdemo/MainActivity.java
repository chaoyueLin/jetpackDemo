package com.example.livedatabusdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LiveDataBus.get().with("key_test", Boolean.class)
                .observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        Log.d(TAG, "observe key_test=" + aBoolean);
                    }
                });

        LiveDataBus.get().with(BusCon.KEY_TEST, Boolean.class)
                .observeForever(new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        Log.d(TAG, "observeForever key_test=" + aBoolean);
                    }
                });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setValue(true);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName chatActivity = new ComponentName("com.example.livedatabusdemo", "com.example.livedatabusdemo.SecondActivity");
                Intent intent = new Intent();
                intent.setComponent(chatActivity);
                startActivity(intent);
            }
        });
    }

    public void setValue(boolean value) {
        LiveDataBus.get().with("key_test").setValue(value);
    }
}