package com.missouristate.penrose.hw6_ex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment leftFragment = fragmentManager.findFragmentById(R.id.leftFragment);
        Fragment rightFragment = fragmentManager.findFragmentById(R.id.rightFragment);
        leftFragment.getView().setBackgroundColor(Color.GRAY);
        rightFragment.getView().setBackgroundColor(Color.LTGRAY);
    }

    public void changeLight(View view) {
        TextView textView = findViewById(R.id.trafficLight);
        String currentText = textView.getText().toString();

        if (currentText.equals(getString(R.string.green_light_text))) {
            // Display the yellow light dilemma.
            Context context = getApplicationContext();
            String text = getString(R.string.yellow_light_warning);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            textView.setText(getString(R.string.yellow_light_text));
            textView.setBackgroundColor(Color.YELLOW);
        } else if (currentText.equals(getString(R.string.yellow_light_text))) {
            textView.setText(getString(R.string.red_light_text));
            textView.setBackgroundColor(Color.RED);
        } else if (currentText.equals(getString(R.string.red_light_text))) {
            textView.setText(getString(R.string.green_light_text));
            textView.setBackgroundColor(Color.GREEN);
        } else {
            textView.setText(getString(R.string.red_light_text));
            textView.setBackgroundColor(Color.RED);
        }
    }
}