/*****************************************************************
 * * File: - StudentAdapter
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

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingdemo.db.Student

/*****************************************************************
 * * File: - StudentAdapter
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
class StudentAdapter : PagedListAdapter<Student, StudentViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder =
        StudentViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
                oldItem == newItem
        }
    }
}

//private val diffCallback = object : DiffUtil.ItemCallback<Student>() {
//    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean =
//        oldItem.id == newItem.id
//
//    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean =
//        oldItem == newItem
//}