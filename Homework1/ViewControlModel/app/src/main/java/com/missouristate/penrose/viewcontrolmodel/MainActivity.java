package com.missouristate.penrose.viewcontrolmodel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCounter = 0;
    Button tapButton;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tapButton = (Button) findViewById(R.id.tapButton);
        text = (TextView) findViewById(R.id.numOfTaps);

        tapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increment the counter then display it.
                mCounter ++;
                text.setText(Integer.toString(mCounter));
            }
        });
    }
}