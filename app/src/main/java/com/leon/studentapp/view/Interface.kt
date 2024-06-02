package com.leon.studentapp.view

import android.view.View

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface ButtonNotifClickListener{
    fun onNotificationButtonClick(v: View)
}

interface ButtonUpdateClickListener{
    fun onButtonUpdateClick(v: View)
}