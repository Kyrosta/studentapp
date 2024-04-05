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
import com.bumptech.glide.Glide

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
        val currentCar = carList[position]
        holder.binding.txtID.text = currentCar.id
        holder.binding.txtName.text = currentCar.name
        holder.binding.txtYear.text = currentCar.year

        Glide.with(holder.itemView.context)
            .load(currentCar.imageUrl)
            .into(holder.binding.imgStudent)

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

