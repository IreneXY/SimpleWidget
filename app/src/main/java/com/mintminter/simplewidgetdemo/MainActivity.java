package com.mintminter.simplewidgetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.mintminter.simplewidget.SimpleProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleProgressBar progressBar = (SimpleProgressBar) findViewById(R.id.simpleprobressbar);
        progressBar.setProgress(0.6f);

        SimpleProgressBar progressBarWithGap = (SimpleProgressBar) findViewById(R.id.simpleprobressbar_with_gap);
        progressBarWithGap.setProgress(0.4f);


    }
}
