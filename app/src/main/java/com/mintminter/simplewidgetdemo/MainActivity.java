package com.mintminter.simplewidgetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mintminter.simplewidget.SimpleProgressBar;

public class MainActivity extends AppCompatActivity {

    SimpleProgressBar simpleProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleProgressbar = (SimpleProgressBar) findViewById(R.id.simpleprogressbar);
        simpleProgressbar.setProgress(0.1f);

        go();
    }

    private void go(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            float f = simpleProgressbar.getProgress();
                            if(f > 1){
                                f = 0;
                            }
                            f += 0.05f;
                            simpleProgressbar.setProgress(f);

                        }
                    });
                }
            }
        }).start();
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
