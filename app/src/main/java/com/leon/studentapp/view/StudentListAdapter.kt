package com.leon.studentapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.leon.studentapp.databinding.StudentListItemBinding
import com.leon.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {
    class StudentViewHolder(var binding: StudentListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val currStudent = studentList[position]
        holder.binding.txtID.text = currStudent.id
        holder.binding.txtName.text = currStudent.name

        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener { picasso, uri, exception -> exception.printStackTrace() }
        picasso.build().load(studentList[position].photoUrl).into(holder.binding.imgStudent, object:
            Callback {
            override fun onSuccess() {
                holder.binding.progressImage.visibility = View.INVISIBLE
                holder.binding.imgStudent.visibility = View.VISIBLE
            }
            override fun onError(e: Exception?) {
                Log.e("picasso_error", e.toString())
            }
        })

        holder.binding.btnDetail.setOnClickListener {
            val action = StudentListFragmentDirections.actionStudentDetail(currStudent.id.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

}

