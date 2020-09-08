/*****************************************************************
 * * File: - ShareViewModel
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

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/*****************************************************************
 * * File: - ShareViewModel
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/8
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/8    1.0         create
 ******************************************************************/
class SharedViewModel : ViewModel() {
    var sharedName:MutableLiveData<String> = MutableLiveData()

    init {
        sharedName.value = "test"
    }
}