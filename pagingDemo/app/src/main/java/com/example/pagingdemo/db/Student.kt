/*****************************************************************
 * * File: - Student
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
package com.example.pagingdemo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/*****************************************************************
 * * File: - Student
 * * Description:
 * * Version: 1.0
 * * Date : 2020/9/9
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/9/9    1.0         create
 ******************************************************************/
@Entity
data class Student(@PrimaryKey(autoGenerate = true) val id: Int, val name: String)