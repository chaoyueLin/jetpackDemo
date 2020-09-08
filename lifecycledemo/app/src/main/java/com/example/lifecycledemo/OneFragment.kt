/*****************************************************************
 * * File: - OneFragment
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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

/*****************************************************************
 * * File: - OneFragment
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/8
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/8    1.0         create
 ******************************************************************/
class OneFragment : Fragment() {
    private lateinit var bt_fragment_one: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        bt_fragment_one = view.findViewById(R.id.bt_fragment_one)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(activity!!).get(SharedViewModel::class.java)

        bt_fragment_one.setOnClickListener {
            model.sharedName.value = "one"
        }
    }

    override fun onDestroy() {
        Toast.makeText(activity, "OneFragment is destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}