/*****************************************************************
 * * File: - TwoFragment
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/8
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/8    1.0         create
 ******************************************************************/
package com.example.lifecycledemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

/*****************************************************************
 * * File: - TwoFragment
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/8
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/8    1.0         create
 ******************************************************************/
class TwoFragment : Fragment() {
    private lateinit var tv_fragment_two: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        tv_fragment_two = view.findViewById(R.id.tv)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)

        model.sharedName.observe(this, Observer {
            tv_fragment_two.text = it
        })
    }

    override fun onDestroy() {
        Toast.makeText(activity, "TwoFragment is destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}