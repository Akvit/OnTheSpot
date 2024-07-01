package com.azhel.onthespot.presentation

import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.azhel.onthespot.R
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    //TODO setup strings.xml for English and Israel languages

    //TODO add progress bar
    //TODO add clean list of reboots button
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()
        initWorkManager()
        checkPermissions()
    }

    private fun initUI() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val button: Button = findViewById(R.id.update)
        button.setOnClickListener {  }
        val adapter = BootInformationAdapter(viewModel.state.value)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            //TODO implement diff util and pagination

            viewModel.state.collect{
                adapter.list = it
                adapter.notifyDataSetChanged()

            }

        }
        viewModel.update()

    }

    private fun initWorkManager() {
        val workRequest = PeriodicWorkRequest.Builder(BootCheckWorker::class.java, 15, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("BootCheckWork", ExistingPeriodicWorkPolicy.REPLACE, workRequest)
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1001)
            } else {
                // TODO Permission already granted, post the notification
                postNotification()
            }
        } else {
            // TODO For devices below Android 13, directly post the notification
            postNotification()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1001) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // TODO Permission granted, post the notification
                postNotification()
            } else {
                // TODO Permission denied, show a message to the user
                // TODO You may want to explain why the permission is necessary and prompt again
            }
        }
    }

    private fun postNotification() {
        val channelId = "my_channel_id"
        val channelName = "My Channel"
        val channelDescription = "A description of my channel"

        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance).apply {
            description = channelDescription
        }
//        val notificationManager: NotificationManager = ContextCompat.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        notificationManager.createNotificationChannel(channel)
//
//        val builder = NotificationCompat.Builder(this, channelId)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("My notification")
//            .setContentText("Hello World!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)) {
//            if (ActivityCompat.checkSelfPermission(
//                    this@MainActivity,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO make a request the missing permissions
//                return
//            }
//            notify(1001, builder.build())
//        }
    }
}
