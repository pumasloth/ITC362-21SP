package com.missouristate.penrose.todolistex4;

import android.content.Intent;
import android.graphics.Color;
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

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
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
    protected void onResume() {
        super.onResume();

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
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    // build a view dynamically with all of the to-do items
    private void updateView() {
        ArrayList<Item> items = dbManager.selectAll();

        // create scollview and gridlayout
        if (items.size() > 0) {
            ScrollView scrollView = new ScrollView(this);
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount(items.size());
            gridLayout.setColumnCount(1);

            // create array of components
            TextView[] tvItemList = new TextView[items.size()];

            // get width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            // create the textview for the to-do list
            int i = 0;
            Date today = new Date();

            for (Item item : items) {
                tvItemList[i] = new TextView(this);
                tvItemList[i].setGravity(Gravity.CENTER);
                tvItemList[i].setText(item.getItemName() + " (" + item.getDueDate() + ")");
                tvItemList[i].setTextSize(20);
                try {
                    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                    Date dueDate = df.parse(item.getDueDate());
                    if (dueDate.before(today)) {
                        // past due items should be colored in RED.
                        tvItemList[i].setTextColor(Color.RED);
                    } else {
                        tvItemList[i].setTextColor(Color.BLACK);
                    }
                } catch (ParseException pe) {
                    Log.w("MainActivity", "Could not parse date");

                    // default to black
                    tvItemList[i].setTextColor(Color.BLACK);
                }

                // add the item to the grid
                gridLayout.addView(
                        tvItemList[i],
                        width,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                i++;
            }

            scrollView.addView(gridLayout);
            setContentView(scrollView);
        }
    }
}