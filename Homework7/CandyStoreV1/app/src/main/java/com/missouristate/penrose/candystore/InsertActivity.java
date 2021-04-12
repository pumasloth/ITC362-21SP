package com.missouristate.penrose.candystore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View view) {
        // retrieve name and price

        Log.w("InsertActivity", "Insert Button Pushed!");

        EditText etName = findViewById(R.id.input_name);
        EditText etPrice = findViewById(R.id.input_price);
        String name = etName.getText().toString();
        String priceString = etPrice.getText().toString();

        // insert into the database

        // clear data
        etName.setText("");
        etPrice.setText("");
    }

    public void goBack(View view) {
        this.finish();
    }
}
