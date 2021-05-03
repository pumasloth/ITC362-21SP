package com.missouristate.penrose.threadingdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    TextView textView;

    Handler waitHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            textView = findViewById(R.id.tv_gobears);
            textView.setText("Go Missouri State");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.iv_bearlogo);
        logo.setVisibility(View.INVISIBLE);
    }

    public void clickMe(View view) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // Add 10 seconds
                long future = (System.currentTimeMillis()) + 10000;
                while (System.currentTimeMillis() < future) {
                    try {
                        wait(future - System.currentTimeMillis());
                    } catch (Exception exception) {
                        Log.w("MainActivity", exception);
                    }
                }

                waitHandler.sendEmptyMessage(0);
            }
        };

        Thread t = new Thread(r);
        t.start();

        logo.setVisibility(View.VISIBLE);
    }
}