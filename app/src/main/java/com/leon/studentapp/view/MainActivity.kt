package com.leon.studentapp.view

import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.leon.studentapp.R
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.leon.studentapp.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    init {
        instance = this
    }
    companion object {
        private var instance:MainActivity ?= null

        fun showNotification(title:String, content:String, icon:Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val notificationBuilder = NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                setSmallIcon(icon)
                setContentTitle(title)
                setContentText(content)
                setStyle(NotificationCompat.BigTextStyle())
                priority = NotificationCompat.PRIORITY_DEFAULT
                setAutoCancel(true)
            }

            val notif = NotificationManagerCompat.from(instance!!.applicationContext)

            if(ActivityCompat.checkSelfPermission
                    (instance!!.applicationContext, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                return
            }

            notif.notify(1801, notificationBuilder.build())
        }
    }


//    companion object {
//        private var instance:MainActivity ?= null
//        fun showNotification(title:String, content:String, icon:Int){
//            val channelID = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
//            val builder = NotificationCompat.Builder(instance!!.applicationContext, channelID)
//                .apply {
//                    setSmallIcon(icon)
//                    setContentTitle(title)
//                    setContentText(content)
//                    setStyle(NotificationCompat.BigTextStyle())
//                    priority = NotificationCompat.PRIORITY_DEFAULT
//                    setAutoCancel(true)
//                }
//            val notif = NotificationManagerCompat.from(instance!!.applicationContext)
//            notif.notify(1801, builder.build())
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel."
        )

        /* Observable.just("a stream of data","hellow","!!!")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {Log.d("Messages", "data captured: $it")},
                {Log.d("Messages", "error: ${it.message.toString()}")},
                {Log.d("Messages", "complete")},
            )*/

        /*val observer = object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Messages", "Start Subscribe")
            }

            override fun onNext(t: String) {
                Log.d("Messages", "data captured: ${t.toString()}")
            }

            override fun onError(e: Throwable) {
                Log.e("Messages", "error: ${e!!.message.toString()}")
            }

            override fun onComplete() {
                Log.d("Messages", "Complete")
            }
        }*/

    }
}