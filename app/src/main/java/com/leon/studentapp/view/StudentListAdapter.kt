package com.leon.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.leon.studentapp.R
import com.leon.studentapp.databinding.StudentListItemBinding
import com.leon.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(var view: StudentListItemBinding)
        : RecyclerView.ViewHolder(view.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
//        val view = StudentListItemBinding.inflate(
//            LayoutInflater.from(parent.context), parent, false)
//        return StudentViewHolder(view)
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this
//        val currStudent = studentList[position]
//        holder.binding.txtID.text = currStudent.id
//        holder.binding.txtName.text = currStudent.name
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
//        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imgStudent, object:
//            Callback {
//            override fun onSuccess() {
//                holder.binding.progressImage.visibility = View.INVISIBLE
//                holder.binding.imgStudent.visibility = View.VISIBLE
//            }
//            override fun onError(e: Exception?) {
//                Log.e("picasso_error", e.toString())
//            }
//        })
//
//        holder.binding.btnDetail.setOnClickListener {
//            val action = StudentListFragmentDirections.actionStudentDetail(currStudent.id.toString())
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonUpdateClick(v: View) {
        TODO("Not yet implemented")
    }

//    override fun onNotificationButtonClick(v: View) {
//        val student = studentList.find { it.id == v.tag.toString() }
//        student?.let {
//            Observable.timer(5, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    MainActivity.showNotification(
//                        student.name.toString(),
//                        "Notification Created!",
//                        R.drawable.baseline_person_24
//                    )
//                }
//        }
//    }
    override fun onNotificationButtonClick(v: View) {
        val student = studentList.find { it.id == v.tag.toString() }
        student?.let { student ->
            val message = "Notification for ${student.name} created."
            Toast.makeText(v.context, message, Toast.LENGTH_SHORT).show()
        }
    }

}

