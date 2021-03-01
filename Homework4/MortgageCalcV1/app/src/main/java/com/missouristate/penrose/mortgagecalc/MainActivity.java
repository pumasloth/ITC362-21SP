package com.missouristate.penrose.mortgagecalc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void modifyData(View view) {

        // Message between activities
        Intent myIntent = new Intent(this, DataActivity.class);
        this.startActivity(myIntent);
    }
}