package com.missouristate.penrose.hangman;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Hangman game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (game == null) {
            game = new Hangman(Hangman.DEFAULT_GUESSES);
        }

        setContentView(R.layout.activity_main);
        TextView status = findViewById(R.id.status);
        status.setText("" + game.getGuessesLeft());
    }

    public void play(View view) {
    }
}