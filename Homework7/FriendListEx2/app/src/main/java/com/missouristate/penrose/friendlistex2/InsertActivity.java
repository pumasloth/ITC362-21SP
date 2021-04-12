package com.missouristate.penrose.friendlistex2;

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

        EditText etFirstName = findViewById(R.id.input_first_name);
        EditText etLastName = findViewById(R.id.input_last_name);
        EditText etEmailAddress = findViewById(R.id.input_email_address);
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String emailAddress = etEmailAddress.getText().toString();

        // insert into the database
        try {
            Person person = new Person(0, firstName, lastName, emailAddress);
            dbManager.insert(person);
            Toast.makeText(this, "Friend Added", Toast.LENGTH_SHORT).show();
        }
        catch (Exception exception) {
            Toast.makeText(this,
                    "Error, sorry your friend didn't want to be friends",
                    Toast.LENGTH_SHORT)
                    .show();
        }

        // clear data
        etFirstName.setText("");
        etLastName.setText("");
        etEmailAddress.setText("");
    }

    public void goBack(View view) {
        this.finish();
    }
}
