package com.missouristate.penrose.hellogoodbye;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button toggleButton;
    ImageView greetingImage;
    TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton = (Button)findViewById(R.id.clickMeButton);
        greetingImage = (ImageView)findViewById(R.id.greetingImage);
        greeting = (TextView)findViewById(R.id.greetingText);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String greetingMessage = greeting.getText().toString();

                // Flip the greeting based on what it currently is.
                if (greetingMessage.equals(getString(R.string.hello)))
                {
                    greetingImage.setImageDrawable(getDrawable(R.drawable.goodbye));
                    greeting.setText(R.string.goodbye);
                }
                else
                {
                    greetingImage.setImageDrawable(getDrawable(R.drawable.hello));
                    greeting.setText(R.string.hello);
                }
            }
        });
    }
}