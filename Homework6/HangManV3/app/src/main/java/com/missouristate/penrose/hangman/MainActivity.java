package com.missouristate.penrose.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.game_state) == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            GameStateFragment fragment = new GameStateFragment();
            fragmentTransaction.add(R.id.game_state, fragment);
            fragmentTransaction.commit();
        }

        if (fragmentManager.findFragmentById(R.id.game_result) == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            GameResultFragment fragment = new GameResultFragment();
            fragmentTransaction.add(R.id.game_result, fragment);
            fragmentTransaction.commit();
        }
    }

    public void play(View view) {
    }

    public Hangman getGame() {
        return game;
    }
}