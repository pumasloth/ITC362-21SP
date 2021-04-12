package com.missouristate.penrose.candystore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    // build the view dynamically with all the candies
    private void updateView() {
        ArrayList<Candy> candies = dbManager.selectAll();

        // create scollview and gridlayout
        if (candies.size() > 0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(candies.size());
            gridLayout.setColumnCount(4);

            // create array of components
            TextView[] ids = new TextView[candies.size()];
            EditText[][] namesAndPrices = new EditText[candies.size()][2];
            Button[] buttons = new Button[candies.size()];
            ButtonHandler buttonHandler = new ButtonHandler();

            // get width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            // create the textview for the candy ids
            int i = 0;

            for (Candy candy : candies) {

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + candy.getId());

                // create the two EditTexts for the candy's name and price
                namesAndPrices[i][0] = new EditText(this);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][0].setText(candy.getName());
                namesAndPrices[i][1].setText("" + candy.getPrice());
                namesAndPrices[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                namesAndPrices[i][0].setId(10 * candy.getId());
                namesAndPrices[i][1].setId(10 * candy.getId() + 1);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(candy.getId());

                // set up event handling
                buttons[i].setOnClickListener(buttonHandler);

                // add the elements to grid
                gridLayout.addView(
                        ids[i],
                        width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        namesAndPrices[i][0],
                        (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        namesAndPrices[i][1],
                        (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        buttons[i],
                        (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                i++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // retrieve name and price of the candy
            int candyId = v.getId();
            EditText etName = findViewById(10 * candyId);
            EditText etPrice = findViewById(10 * candyId + 1);
            String name = etName.getText().toString();
            String priceString = etPrice.getText().toString();

            // update candy in the database
            try {
                double price = Double.parseDouble(priceString);
                dbManager.updateById(candyId, name, price);
                Toast.makeText(
                        UpdateActivity.this,
                        "Candy Updated",
                        Toast.LENGTH_SHORT)
                        .show();

                // update screen
                updateView();
            }
            catch (NumberFormatException nfe) {
                Toast.makeText(
                        UpdateActivity.this,
                        "Price Error",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}