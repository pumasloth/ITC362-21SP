package com.missouristate.penrose.todolistex3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view) {
        // retrieve name and price

        Log.w("InsertActivity", "Insert Button Pushed!");

        EditText etItemName = findViewById(R.id.input_todo_item);
        String itemName = etItemName.getText().toString();

        // insert into the database
        try {
            Item item = new Item(0, itemName);
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
    }

    public void goBack(View view) {
        this.finish();
    }
}
