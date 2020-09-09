/*****************************************************************
 * * File: - Executors
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
package com.example.pagingdemo

import java.util.concurrent.Executors

/*****************************************************************
 * * File: - Executors
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}