/*****************************************************************
 * * File: - CommonViewModel
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

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagingdemo.db.Student
import com.example.pagingdemo.db.StudentDb

/*****************************************************************
 * * File: - CommonViewModel
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
class CommonViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = StudentDb.get(app).studentDao()
    fun getRefreshLiveData(): LiveData<PagedList<Student>> =
        LivePagedListBuilder(dao.getAllStudent(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)                         //配置分页加载的数量
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)     //配置是否启动PlaceHolders
            .setInitialLoadSizeHint(PAGE_SIZE)              //初始化加载的数量
            .build()).build()

    companion object {

        private const val PAGE_SIZE = 15

        private const val ENABLE_PLACEHOLDERS = false
    }
}