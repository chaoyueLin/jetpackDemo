package com.example.lifecycledemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/*****************************************************************
 * * File: - SecondActivity
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/8
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/8    1.0         create
</desc></version></date></author> */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        replaceFragment(TwoFragment())
        val bt_one: Button = findViewById(R.id.bt_one)
        val bt_two: Button = findViewById(R.id.bt_two)
        bt_one.setOnClickListener {
            replaceFragment(OneFragment())
        }
        bt_two.setOnClickListener {
            replaceFragment(TwoFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fl, fragment)
        transaction.commit()
    }

    override fun onDestroy() {
        Log.e("SecondActivity", "onDestroy")
        super.onDestroy()
    }
}