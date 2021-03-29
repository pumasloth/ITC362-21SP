package com.missouristate.penrose.hangman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
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

        if (fragmentManager.findFragmentByTag("background") == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            BackgroundFragment fragment = new BackgroundFragment();
            fragmentTransaction.add(fragment, "background");
            fragmentTransaction.commit();
        }
    }

    public void play(View view) {
        EditText input = findViewById(R.id.letter);
        Editable userText = input.getText();

        if (userText != null && userText.length() > 0) {
            // update the number of guesses left
            char letter = userText.charAt(0);
            game.guess(letter);

            TextView status = findViewById(R.id.status);
            status.setText("" + game.getGuessesLeft());

            // update incomplete word (fragment)
            FragmentManager fragmentManager = getSupportFragmentManager();
            GameStateFragment gameStateFragment =
                    (GameStateFragment) fragmentManager.findFragmentById(R.id.game_state);
            View gsFragmentView = gameStateFragment.getView();
            TextView gameStateTV = findViewById(R.id.state_of_game);
            gameStateTV.setText(game.currentIncompleteWord());

            // clear EditText
            input.setText("");

            // check the number of plays left
            if (game.getGuessesLeft() == 1) {
                BackgroundFragment backgroundFragment =
                        (BackgroundFragment) getSupportFragmentManager().findFragmentByTag("background");
                GameResultFragment grFragment =
                        (GameResultFragment) getSupportFragmentManager().findFragmentById(R.id.game_result);

                // display the warning message
                grFragment.setResult(backgroundFragment.warning());
            }

            int result = game.gameOver();
            if (result != 0) {
                GameResultFragment gameResultFragment =
                        (GameResultFragment) fragmentManager.findFragmentById(R.id.game_result);

                // update TextView in result Fragment
                switch(result) {
                    case 1:
                        // you won
                        gameResultFragment.setResult("You Won!");
                        break;
                    case -1:
                        // you lost
                        gameResultFragment.setResult("You Lost!");
                        break;
                    default:
                        // game not over
                }

                // clear the hint
                input.setHint("");
            }
        }
    }

    public Hangman getGame() {
        return game;
    }
}