package com.missouristate.penrose.mortgagecalc;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
    }

    public void goBack(View view) {
        this.finish();
    }
}
