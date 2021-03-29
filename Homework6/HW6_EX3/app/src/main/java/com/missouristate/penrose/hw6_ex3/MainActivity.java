package com.missouristate.penrose.hw6_ex3;

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

        TextView redLight = findViewById(R.id.redLight);
        TextView yellowLight = findViewById(R.id.yellowLight);
        TextView greenLight = findViewById(R.id.greenLight);

        // the top one is red when we start.
        redLight.setText(R.string.red_light_text);
        redLight.setBackgroundColor(Color.RED);
        redLight.setVisibility(View.VISIBLE);

        yellowLight.setText(R.string.yellow_light_text);
        yellowLight.setBackgroundColor(Color.YELLOW);
        yellowLight.setVisibility(View.INVISIBLE);

        greenLight.setText(R.string.green_light_text);
        greenLight.setBackgroundColor(Color.GREEN);
        greenLight.setVisibility(View.INVISIBLE);
    }

    public void changeLight(View view) {
        TextView redLight = findViewById(R.id.redLight);
        TextView yellowLight = findViewById(R.id.yellowLight);
        TextView greenLight = findViewById(R.id.greenLight);

        if (greenLight.getVisibility() == View.VISIBLE) {
            // If the current light is green, set it to yellow.
            redLight.setVisibility(View.INVISIBLE);
            yellowLight.setVisibility(View.VISIBLE);
            greenLight.setVisibility(View.INVISIBLE);

            // Display the yellow light dilemma.
            Context context = getApplicationContext();
            String text = getString(R.string.yellow_light_warning);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            TextView yLTextView = findViewById(R.id.yellowLight);
            yLTextView.setText(getString(R.string.yellow_light_text));
            yLTextView.setBackgroundColor(Color.YELLOW);
        } else if (yellowLight.getVisibility() == View.VISIBLE) {
            // If the current light is yellow, set it to red.
            redLight.setVisibility(View.VISIBLE);
            yellowLight.setVisibility(View.INVISIBLE);
            greenLight.setVisibility(View.INVISIBLE);

            TextView rLTextView = findViewById(R.id.redLight);
            rLTextView.setText(getString(R.string.red_light_text));
            rLTextView.setBackgroundColor(Color.RED);
        } else if (redLight.getVisibility() == View.VISIBLE) {
            // If the current light is red, set it to green.
            redLight.setVisibility(View.INVISIBLE);
            yellowLight.setVisibility(View.INVISIBLE);
            greenLight.setVisibility(View.VISIBLE);

            TextView gLTextView = findViewById(R.id.greenLight);
            gLTextView.setText(getString(R.string.green_light_text));
            gLTextView.setBackgroundColor(Color.GREEN);
        } else {
            // Default to red light.
            redLight.setVisibility(View.VISIBLE);
            yellowLight.setVisibility(View.INVISIBLE);
            greenLight.setVisibility(View.INVISIBLE);

            TextView rLTextView = findViewById(R.id.yellowLight);
            rLTextView.setText(getString(R.string.red_light_text));
            rLTextView.setBackgroundColor(Color.RED);
        }
    }
}