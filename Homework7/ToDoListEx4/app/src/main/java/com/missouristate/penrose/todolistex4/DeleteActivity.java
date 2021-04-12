package com.missouristate.penrose.todolistex4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    // build a view dynamically with all of the to-do items
    private void updateView() {
        ArrayList<Item> items = dbManager.selectAll();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        for (Item item : items) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(item.getId());
            radioButton.setText(item.getItemName() + " " + item.getDueDate());
            radioButton.setTextSize(20);
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
            // delete a to-do item from database
            dbManager.deleteById(checkedId);
            Toast.makeText(
                    DeleteActivity.this,
                    "To-Do Item Deleted",
                    Toast.LENGTH_SHORT)
                    .show();

            updateView();
        }
    }
}