package com.missouristate.penrose.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    private Button[] [] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        gridLayout.setRowCount(TicTacToe.SIDE);

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
        buttons[row][column].setText("X");
    }
}