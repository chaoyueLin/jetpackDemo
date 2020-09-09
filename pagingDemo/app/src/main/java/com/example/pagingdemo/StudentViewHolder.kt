/*****************************************************************
 * * File: - StudentViewHolder
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

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingdemo.db.Student

/*****************************************************************
 * * File: - StudentViewHolder
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
class StudentViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
) {
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    var student: Student? = null

    fun bindTo(student: Student?) {
        this.student = student
        nameView.text = student?.name
    }
}