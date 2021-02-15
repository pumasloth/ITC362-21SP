package com.missouristate.penrose.shadesv1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ConstraintSet constraintSet;
    private ConstraintLayout constraintLayout;
    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout = (ConstraintLayout) findViewById(R.id.CL_1);
        constraintLayout.setBackgroundColor(Color.parseColor("#c89b6d"));
        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        // Button 1
        button1 = new Button(this);
        button1.setText(getString(R.string.plum));
        button1.setId(View.generateViewId());
        button1.setTag("btn1");
        button1.setBackgroundColor(Color.parseColor("#ac7d50"));
        button1.setOnClickListener(ShadeChangeListener);

        // Build the constraints
        constraintLayout.addView(button1);
        constraintSet.connect(
                button1.getId(),
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                50);
        constraintSet.connect(
                button1.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                0);
        constraintSet.connect(
                button1.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                0);
        constraintSet.constrainHeight(button1.getId(), 200);

        // Apply the constraint
        constraintSet.applyTo(constraintLayout);


        // Button 2
        button2 = new Button(this);
        button2.setText(getString(R.string.blue));
        button2.setId(View.generateViewId());
        button2.setTag("btn2");
        button2.setBackgroundColor(Color.parseColor("#ac7d50"));
        button2.setOnClickListener(ShadeChangeListener);

        // Build the constraints
        constraintLayout.addView(button2);
        constraintSet.connect(
                button2.getId(),
                ConstraintSet.TOP,
                button1.getId(),
                ConstraintSet.BOTTOM,
                10);
        constraintSet.connect(
                button2.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                0);
        constraintSet.connect(
                button2.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT, 0);
        constraintSet.constrainHeight(
                button2.getId(),
                200);

        // Apply the constraint
        constraintSet.applyTo(constraintLayout);

        // Button 3
        button3 = new Button(this);
        button3.setText(getString(R.string.gold));
        button3.setId(View.generateViewId());
        button3.setTag("btn3");
        button3.setBackgroundColor(Color.parseColor("#ac7d50"));
        button3.setOnClickListener(ShadeChangeListener);

        // Build the constraints
        constraintLayout.addView(button3);
        constraintSet.connect(
                button3.getId(),
                ConstraintSet.TOP,
                button2.getId(),
                ConstraintSet.BOTTOM,
                10);
        constraintSet.connect(
                button3.getId(),
                ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.RIGHT,
                0);
        constraintSet.connect(
                button3.getId(),
                ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID,
                ConstraintSet.LEFT,
                0);
        constraintSet.constrainHeight(button3.getId(), 200);

        // Apply the constraint
        constraintSet.applyTo(constraintLayout);
    }

    // Create and populate the TextView programmatically.
    private View.OnClickListener ShadeChangeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String description = (String) v.getTag();
            TextView textView = new TextView(MainActivity.this);
            textView.setLayoutParams(
                    new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            );
            textView.setGravity(Gravity.CENTER);
            switch((String) v.getTag()){
                case "btn1":
                    textView.setText(R.string.plum_is);
                    break;
                case "btn2":
                    textView.setText(R.string.blue_is);
                    break;
                case "btn3":
                    textView.setText(R.string.gold_is);
                    break;
            }

            textView.setBackgroundColor(Color.parseColor("#ac7d50"));
            textView.setId(View.generateViewId());

            // Build the constraints
            constraintLayout.addView(textView);
            constraintSet.connect(
                    textView.getId(),
                    ConstraintSet.TOP,
                    button3.getId(),
                    ConstraintSet.BOTTOM,
                    50);
            constraintSet.connect(
                    textView.getId(),
                    ConstraintSet.RIGHT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.RIGHT,
                    0);
            constraintSet.connect(
                    textView.getId(),
                    ConstraintSet.LEFT,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.LEFT,
                    0);
            constraintSet.constrainHeight(textView.getId(), 400);

            // Apply the constraint
            constraintSet.applyTo(constraintLayout);
        }
    };
}