package com.missouristate.penrose.hw4exercise3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void goBack(View v){
        String greenButton;
        String savedExtra = getIntent().getStringExtra("intent");
        Intent myIntent = new Intent(this, MainActivity.class);
        int numOfClicks = Integer.parseInt(savedExtra);
        numOfClicks += 1;
        greenButton = Integer.toString(numOfClicks);
        myIntent.putExtra("intent", greenButton);
        this.startActivity(myIntent);
    }
}
