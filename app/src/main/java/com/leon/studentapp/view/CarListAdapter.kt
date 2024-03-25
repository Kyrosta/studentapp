package com.leon.studentapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.leon.studentapp.databinding.CarListItemBinding
import com.leon.studentapp.model.Car

class CarListAdapter(val carList:ArrayList<Car>)
    :RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    class CarViewHolder(var binding: CarListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.txtID.text = carList[position].id
        holder.binding.txtName.text = carList[position].name
        holder.binding.txtYear.text = carList[position].year

        //holder.binding.btnDetail.setOnClickListener {
            //Karena tidak disuruh detail
            //val action = StudentListFragmentDirections.actionStudentDetail()
        //    Navigation.findNavController(it).navigate(action)
        //}
    }

    fun updateCarList(newCarList: ArrayList<Car>) {
        carList.clear()
        carList.addAll(newCarList)
        notifyDataSetChanged()
    }

}

