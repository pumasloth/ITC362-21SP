package com.missouristate.penrose.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String MA = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(MA, "Inside onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Configuration config = getResources().getConfiguration();
        modifyLayout(config);
    }

    private void modifyLayout(Configuration newConfig) {
        Log.w(MA, "Inside modifyLayout");

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else {
            // Do nothing.
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.w(MA, "Inside onConfigurationChanged");

        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }
}