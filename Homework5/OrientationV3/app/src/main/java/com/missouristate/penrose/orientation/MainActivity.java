package com.missouristate.penrose.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String MA = "MainActivity";
    public static final int SPACING_VERTICAL = 50;
    public static final int SPACING_HORIZONTAL = 25;

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

        Button button2 = findViewById(R.id.btn_view2);
        ViewGroup.MarginLayoutParams params2 =
                (ViewGroup.MarginLayoutParams) button2.getLayoutParams();

        Button button3 = findViewById(R.id.btn_view3);
        ViewGroup.MarginLayoutParams params3 =
                (ViewGroup.MarginLayoutParams) button3.getLayoutParams();

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            params2.setMargins(0, SPACING_HORIZONTAL, 0, 0);
            params2.setMargins(0, SPACING_HORIZONTAL, 0, 0);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            params3.setMargins(0, SPACING_VERTICAL, 0, 0);
            params3.setMargins(0, SPACING_VERTICAL, 0, 0);
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