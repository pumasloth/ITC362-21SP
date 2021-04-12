package com.missouristate.penrose.todolistex4;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class InsertActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private DatabaseManager dbManager;
    private EditText etDueDate;

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // add one to the month for readability as January starts at zero
        month += 1;
        String date = month + "/" + dayOfMonth + "/" + year;
        etDueDate = findViewById(R.id.input_todo_dueDate);
        etDueDate.setText(date);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);

        findViewById(R.id.input_todo_dueDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    public void insert(View view) {
        // retrieve name and price

        Log.w("InsertActivity", "Insert Button Pushed!");

        EditText etItemName = findViewById(R.id.input_todo_item);
        EditText etDueDate = findViewById(R.id.input_todo_dueDate);
        String itemName = etItemName.getText().toString();
        String dueDate = etDueDate.getText().toString();

        // insert into the database
        try {
            Item item = new Item(0, itemName, dueDate);
            dbManager.insert(item);
            Toast.makeText(this,
                    "To-Do Item Added",
                    Toast.LENGTH_SHORT)
                    .show();
        }
        catch (Exception exception) {
            Toast.makeText(this,
                    "Error adding To-Do Item",
                    Toast.LENGTH_SHORT)
                    .show();
        }

        // clear data
        etItemName.setText("");
        etDueDate.setText("");
    }

    public void goBack(View view) {
        this.finish();
    }
}
