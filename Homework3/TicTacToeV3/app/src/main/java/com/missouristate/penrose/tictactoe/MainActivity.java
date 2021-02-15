package com.missouristate.penrose.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[] [] buttons;
    private TicTacToe tttGame;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tttGame = new TicTacToe();
        buildGUIByCode();
    }

    private void buildGUIByCode() {
        // Get the width of the screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x/TicTacToe.SIDE;

        // Create the layout Manger as a GridLayout
        GridLayout gridLayout = new GridLayout(this);

        // 3x3 grid
        gridLayout.setColumnCount(TicTacToe.SIDE);
        gridLayout.setRowCount(TicTacToe.SIDE + 1);

        // Create the buttons and add them to the GridLayout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        ButtonHandler buttonHandler = new ButtonHandler();

        for (int row = 0; row < TicTacToe.SIDE; row++){
            for (int column = 0; column < TicTacToe.SIDE; column++){
                buttons[row][column] = new Button(this);

                // Cast to int
                buttons[row][column].setTextSize((int) (w * .2));
                buttons[row][column].setTextSize((int) (w * .2));

                // Set the listener
                buttons[row][column].setOnClickListener(buttonHandler);

                // Add this to the view
                gridLayout.addView(buttons [row][column], w, w);
            }
        }

        // Set the layout parameters for the 4th row of the GridLayout
        status = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1);
        GridLayout.Spec columnSpec = GridLayout.spec(0, TicTacToe.SIDE);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);

        // Set the status characteristics
        status.setWidth(TicTacToe.SIDE * w);
        status.setHeight(w);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int) (w * .15));
        status.setText(tttGame.result());

        gridLayout.addView(status);

        // Set the GridLayout as the View of this Activity
        setContentView(gridLayout);
    }

    private class ButtonHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.w("MainActivity", "Inside onClick, v=" + v);
            for (int row = 0; row < TicTacToe.SIDE; row++){
                for (int column = 0; column < TicTacToe.SIDE; column++){
                    if (v == buttons[row][column]){
                        update(row, column);
                    }
                }
            }
        }
    }

    private void update(int row, int column) {
        Log.w("MainActivity", "Inside update : " + row + ", " + column);
        int play = tttGame.play(row, column);
        if (play == 1){
            buttons[row][column].setText("X");
        } else if (play == 2){
            buttons[row][column].setText("O");
        }

        // Game is over, disable the buttons
        if (tttGame.isGameOver()){
            status.setBackgroundColor(Color.RED);
            enableButtons(false);
            status.setText(tttGame.result());
        }
    }

    private void enableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++){
            for (int column = 0; column < TicTacToe.SIDE; column++){
                buttons[row][column].setEnabled(enabled);
            }
        }
    }
}