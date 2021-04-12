package com.missouristate.penrose.friendlistex2;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    // build the view dynamically with all the friends
    private void updateView() {
        ArrayList<Person> friends = dbManager.selectAll();

        // create scollview and gridlayout
        if (friends.size() > 0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(friends.size());
            gridLayout.setColumnCount(5);

            // create array of components
            TextView[] ids = new TextView[friends.size()];
            EditText[][] namesAndEmailAddresses = new EditText[friends.size()][3];
            Button[] buttons = new Button[friends.size()];
            ButtonHandler buttonHandler = new ButtonHandler();

            // get width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            // create the textview for the person ids
            int i = 0;

            for (Person person : friends) {

                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + person.getId());

                // create the EditTexts for the friend's name and email address
                namesAndEmailAddresses[i][0] = new EditText(this);
                namesAndEmailAddresses[i][1] = new EditText(this);
                namesAndEmailAddresses[i][2] = new EditText(this);
                namesAndEmailAddresses[i][0].setText(person.getFirstName());
                namesAndEmailAddresses[i][1].setText(person.getLastName());
                namesAndEmailAddresses[i][2].setText(person.getEmailAddress());
                namesAndEmailAddresses[i][0].setInputType(InputType.TYPE_CLASS_TEXT);
                namesAndEmailAddresses[i][1].setInputType(InputType.TYPE_CLASS_TEXT);
                namesAndEmailAddresses[i][2].setInputType(InputType.TYPE_CLASS_TEXT);
                namesAndEmailAddresses[i][0].setId(10 * person.getId());
                namesAndEmailAddresses[i][1].setId(10 * person.getId() + 1);
                namesAndEmailAddresses[i][2].setId(10 * person.getId() + 2);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(person.getId());

                // set up event handling
                buttons[i].setOnClickListener(buttonHandler);

                // add the elements to grid
                gridLayout.addView(
                        ids[i],
                        width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        namesAndEmailAddresses[i][0],
                        (int) (width * .20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        namesAndEmailAddresses[i][1],
                        (int) (width * .20),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        namesAndEmailAddresses[i][2],
                        (int) (width * .40),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(
                        buttons[i],
                        (int) (width * .10),
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
            // retrieve the name and email for the friends
            int personId = v.getId();
            EditText etFirstName = findViewById(10 * personId);
            EditText etLastName = findViewById(10 * personId + 1);
            EditText etEmailAddress = findViewById(10 * personId + 2);
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String emailAddress = etEmailAddress.getText().toString();

            // update the friend in the database
            try {
                dbManager.updateById(personId, firstName, lastName, emailAddress);
                Toast.makeText(
                        UpdateActivity.this,
                        "Friend Updated",
                        Toast.LENGTH_SHORT)
                        .show();

                // update screen
                updateView();
            }
            catch (Exception exception) {
                Toast.makeText(
                        UpdateActivity.this,
                        "Error, your friend couldn't be updated at this time",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}