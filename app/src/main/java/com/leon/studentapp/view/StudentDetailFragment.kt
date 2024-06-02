package com.leon.studentapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.leon.studentapp.R
import com.leon.studentapp.databinding.FragmentStudentDetailBinding
import com.leon.studentapp.model.Student
import com.leon.studentapp.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null){
            detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            detailViewModel.fetch(StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid)
            observeViewModel()
        }
    }

    fun observeViewModel(){
        detailViewModel.studentLD.observe(viewLifecycleOwner) {
            var student = it

            binding.btnUpdate.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        MainActivity.showNotification(student.name.toString(),
                            "New Notifications Created!", R.drawable.baseline_person_24)
                    }
            }
            if(it != null){
                binding.txtID.setText(student.id)
                binding.txtName.setText(student.name)
                binding.txtBoD.setText(student.bod)
                binding.txtPhone.setText(student.phone)
                Picasso.get()
                    .load(student.photoUrl)
                    .into(binding.imgStudent)
            }
        }
    }
}