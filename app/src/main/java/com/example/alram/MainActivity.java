package com.example.alram;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
Button btnstart,btnclose;
    private static final String TAG="Example job";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



    public void  startJob(View v){

       /* PeriodicWorkRequest saveRequest =
                new PeriodicWorkRequest.Builder(NotificationUsingWorkManneger.class, 1, TimeUnit.HOURS)
                        // Constraints
                        .build();*/


        WorkRequest saveRequest =
                new PeriodicWorkRequest.Builder(NotificationUsingWorkManneger.class,
                        0, TimeUnit.HOURS,
                        15, TimeUnit.MINUTES)
                        .addTag("Notify me")
                        .build();


        WorkManager
                .getInstance(this)
                .enqueue(saveRequest);



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void  cancelJob(View v){
        JobScheduler jobScheduler=(JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        jobScheduler.cancel(123);
        Log.d(TAG,"Job cancelled");

    }




}