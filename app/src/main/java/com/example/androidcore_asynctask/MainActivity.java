package com.example.androidcore_asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public TextView textView;

    public ProgressBar progressBar;

    public static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);
        progressBar = findViewById(R.id.progress);

        if(savedInstanceState != null){
            String str = savedInstanceState.getString(TEXT_STATE);
            textView.setText(str);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TEXT_STATE, textView.getText().toString());
    }

    public void startTask(View view) {
        textView.setText(R.string.nap);

        new SimpleAsyncTask(textView, progressBar).execute();
    }
}
