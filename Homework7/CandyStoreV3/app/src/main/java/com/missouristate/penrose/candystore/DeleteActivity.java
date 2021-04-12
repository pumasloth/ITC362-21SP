package com.missouristate.penrose.candystore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    // build a view dynamically with all of the candies
    private void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        for (Candy candy : candies) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(candy.getId());
            radioButton.setText(candy.toString());
            radioGroup.addView(radioButton);
        }

        // set up event handling
        RadioButtonHandler rbh = new RadioButtonHandler();
        radioGroup.setOnCheckedChangeListener(rbh);


        // create a back button
        Button backButton = new Button(this);
        backButton.setText("Back");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });

        scrollView.addView(radioGroup);
        relativeLayout.addView(scrollView);

        // add a back button at the bottom of the view
        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.setMargins(0, 0, 0, 50);
        relativeLayout.addView(backButton, layoutParams);

        setContentView(relativeLayout);
    }

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // delete candy from database
            dbManager.deleteById(checkedId);
            Toast.makeText(
                    DeleteActivity.this,
                    "Candy Deleted",
                    Toast.LENGTH_SHORT)
                    .show();

            updateView();
        }
    }
}