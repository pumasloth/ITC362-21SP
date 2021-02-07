package com.missouristate.penrose.trafficlight;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button toggleButton;
    ImageView greenLight;
    ImageView yellowLight;
    ImageView redLight;
    String currentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.btn_ToggleLight);
        greenLight = findViewById(R.id.iv_GreenLight);
        yellowLight = findViewById(R.id.iv_YellowLight);
        redLight = findViewById(R.id.iv_RedLight);

        // Set the image to either green, yellow or red.
        toggleButton.setOnClickListener(v -> {
            currentText = toggleButton.getText().toString();

            // If the current light is green, set it to yellow.
            if(currentText.equals(getString(R.string.green)))
            {
                // Button.
                toggleButton.setText(R.string.yellow);
                toggleButton.setBackgroundColor(Color.parseColor("#FFFF00"));

                // Image.
                greenLight.setVisibility(View.INVISIBLE);
                yellowLight.setVisibility(View.VISIBLE);
                redLight.setVisibility(View.INVISIBLE);
            }
            // If the current light is yellow, set it to red.
            else if (currentText.equals(getString(R.string.yellow)))
            {
                // Button.
                toggleButton.setText(R.string.red);
                toggleButton.setBackgroundColor(Color.parseColor("#FF0000"));

                // Image.
                greenLight.setVisibility(View.INVISIBLE);
                yellowLight.setVisibility(View.INVISIBLE);
                redLight.setVisibility(View.VISIBLE);
            }
            // If the current light is red, set it to green.
            else if (currentText.equals(getString(R.string.red)))
            {
                // Button.
                toggleButton.setText(R.string.green);
                toggleButton.setBackgroundColor(Color.parseColor("#008000"));

                // Image.
                greenLight.setVisibility(View.VISIBLE);
                yellowLight.setVisibility(View.INVISIBLE);
                redLight.setVisibility(View.INVISIBLE);
            }
        });
    }
}