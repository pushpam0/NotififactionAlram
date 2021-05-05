package com.example.alram;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ExampleJobService extends JobService {
    private static final String TAG="Example job";
    private boolean jobCancelled=false;
    int count=10;
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG,"JOB Started");

        doBackGroundWork(params);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG,"Job Cancelled Before Completion");
        jobCancelled=true;
        return true;
    }

    private void doBackGroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count>0){
                   // Toast.makeText(ExampleJobService.this, "job "+count, Toast.LENGTH_SHORT).show();
                    System.out.println("run "+count);
                    if(jobCancelled)
                        return;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    count--;
                }
                Log.d("TAG","Job Fnished");
                jobFinished(params,true);
            }
        }).start();

    }
}
