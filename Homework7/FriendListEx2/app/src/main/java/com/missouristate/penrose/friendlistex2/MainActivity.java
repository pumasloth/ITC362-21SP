package com.missouristate.penrose.friendlistex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);
        updateView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // refresh the main activity
        updateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // super.onCreateOptionsMenu(menu);
        // inflate the menu
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        // handle action bar items
        int id = menuItem.getItemId();

        switch(id) {
            case R.id.action_add:
                Log.w("MainActivity", "Add Selected!");
                Intent insertIntent = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Log.w("MainActivity", "Delete Selected!");
                Intent deleteIntent = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "Update Selected!");
                Intent updateIntent = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    // build a view dynamically with all of the friends
    private void updateView() {
        ArrayList<Person> friends = dbManager.selectAll();

        // create scollview and gridlayout
        if (friends.size() > 0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(friends.size());
            gridLayout.setColumnCount(1);

            // create array of components
            TextView[] tvFriendList = new TextView[friends.size()];

            // get width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            // create the textview for the person ids
            int i = 0;

            for (Person person : friends) {

                tvFriendList[i] = new TextView(this);
                tvFriendList[i].setGravity(Gravity.CENTER);
                tvFriendList[i].setText(person.toString() + " (" + person.getEmailAddress() + ")");
                tvFriendList[i].setTextSize(20);

                // add the friend to the grid
                gridLayout.addView(
                        tvFriendList[i],
                        width,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                i++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }
    }
}