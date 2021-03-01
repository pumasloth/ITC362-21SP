package com.missouristate.penrose.hw4exercise3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        updateView();
    }

    private void updateView() {
        if (getIntent().getStringExtra("intent") != null) {
            String savedExtra = getIntent().getStringExtra("intent");
            TextView myText = findViewById((R.id.greenButton));
            myText.setText(savedExtra);
        }
    }

    public void changeScreen(View v){
        Intent myIntent = new Intent(this, MainActivity2.class);
        TextView valueButton1 = findViewById(R.id.greenButton);
        String valueButton = valueButton1.getText().toString();
        myIntent.putExtra("intent", valueButton);
        this.startActivity(myIntent);
    }
}
