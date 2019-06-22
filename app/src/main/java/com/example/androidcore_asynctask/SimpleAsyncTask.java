package com.example.androidcore_asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> textViewWeakReference;
    private WeakReference<ProgressBar> progressBarWeakReference;

    SimpleAsyncTask(TextView textView, ProgressBar progressBar) {
        textViewWeakReference = new WeakReference<>(textView);
        progressBarWeakReference = new WeakReference<>(progressBar);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressBarWeakReference.get().setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random random = new Random();
        int n = random.nextInt(11);

        int count = n * 100;

        for (int i=0;i<n;i++){
            publishProgress((i * 100) / n);
            try {
                Thread.sleep(count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return "Awake at last after sleeping for " + count + " milliseconds";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressBarWeakReference.get().setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        textViewWeakReference.get().setText(s);           // get() is use because of WeakReference.
        progressBarWeakReference.get().setProgress(0);
        progressBarWeakReference.get().setVisibility(View.INVISIBLE);
    }
}
