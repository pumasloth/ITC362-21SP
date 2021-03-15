package com.missouristate.penrose.hw5exercise3tictactoe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String MA = "MainActivity";
    private boolean verticalOrientationSet, horizontalOrientationSet;
    private int screenHeight, screenWidth;
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
        Configuration config = getResources().getConfiguration();
        getDeviceDimensions(config);

        // Create the layout Manger as a GridLayout
        GridLayout gridLayout = new GridLayout(this);

        // 3x3 grid
        gridLayout.setColumnCount(TicTacToe.SIDE);

        // Add one to avoid an exception when adding to the view,
        // since the message is 4th
        gridLayout.setRowCount(TicTacToe.SIDE + 1);

        // Create the buttons and add them to the GridLayout
        buttons = new Button[TicTacToe.SIDE][TicTacToe.SIDE];
        ButtonHandler buttonHandler = new ButtonHandler();

        for (int row = 0; row < TicTacToe.SIDE; row++){
            for (int column = 0; column < TicTacToe.SIDE; column++){
                buttons[row][column] = new Button(this);

                // Set the listener
                buttons[row][column].setOnClickListener(buttonHandler);

                // Add to the view
                if (horizontalOrientationSet) {
                    buttons[row][column].setTextSize((int) ((screenHeight) * .2));
                    gridLayout.addView(buttons[row][column], screenWidth, screenHeight);
                } else {
                    buttons[row][column].setTextSize((int) ((screenWidth) * .2));
                    gridLayout.addView(buttons[row][column], screenWidth, screenWidth);
                }
            }
        }

        // Set the layout parameters for the 4th row of the GridLayout
        status = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(TicTacToe.SIDE, 1);
        GridLayout.Spec columnSpec = GridLayout.spec(0, TicTacToe.SIDE);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);

        // Set the status characteristics
        status.setWidth(TicTacToe.SIDE * screenWidth);
        status.setHeight(screenWidth);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int) (screenWidth * .15));
        status.setText(tttGame.result());

        gridLayout.addView(status);

        // Set the GridLayout as the View of this Activity
        setContentView(gridLayout);
    }

    private class ButtonHandler implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Log.w(MA, "Inside onClick, v=" + v);
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
        Log.w(MA, "Inside update : " + row + ", " + column);
        int play = tttGame.play(row, column);
        String buttonValue;

        // Landscape will use A/Z and otherwise use X/O.
        if (play == 1){
            buttonValue = (horizontalOrientationSet)
                            ? "A"
                            : "X";
            buttons[row][column].setText(buttonValue);
        } else if (play == 2){
            buttonValue = (horizontalOrientationSet)
                            ? "Z"
                            : "O";
            buttons[row][column].setText(buttonValue);
        }

        // Game is over, disable the buttons
        if (tttGame.isGameOver()){
            status.setBackgroundColor(Color.RED);
            enableButtons(false);
            status.setText(tttGame.result());
            showNewGameDialog();
        }
    }

    private void showNewGameDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Tacking the toe!");
        alert.setMessage("Play again?");
        PlayDialog playAgain = new PlayDialog();
        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show();
    }

    private void enableButtons(boolean enabled) {
        for (int row = 0; row < TicTacToe.SIDE; row++){
            for (int column = 0; column < TicTacToe.SIDE; column++){
                buttons[row][column].setEnabled(enabled);
            }
        }
    }

    private void resetButtons() {
        for (int row = 0; row < TicTacToe.SIDE; row++){
            for (int column = 0; column < TicTacToe.SIDE; column++){
                // Clear the box
                buttons[row][column].setText("");
            }
        }

        status.setBackgroundColor(Color.GREEN);
        status.setText(tttGame.result());
    }

    private class PlayDialog implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int selection) {
            if (selection == -1) {
                // Yes
                tttGame.resetGame();
                enableButtons(true);
                resetButtons();
            } else if (selection == -2) {
                // No
                MainActivity.this.finish();
            }
        }
    }

    private void getDeviceDimensions(Configuration config) {
        Log.w(MA, "Inside checkDimensions");

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            horizontalOrientationSet = true;
            verticalOrientationSet = false;
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            horizontalOrientationSet = false;
            verticalOrientationSet = true;
        } else  {
            // Do nothing.
        }

        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        screenHeight = size.y/TicTacToe.SIDE;
        screenWidth = size.x/TicTacToe.SIDE;

        // If this is landscape, divide each by half
        if (horizontalOrientationSet) {
            screenHeight /= 2;
            screenWidth /= 2;
        } else {
            // Do nothing.
        }

        Log.w(MA, "screenHeight: " + screenHeight);
        Log.w(MA, "screenWidth: " + screenWidth);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.w(MA, "Inside onConfigurationChanged");

        super.onConfigurationChanged(newConfig);
        buildGUIByCode();
    }
}