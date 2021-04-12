package com.missouristate.penrose.candystore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                //Intent insertIntent = new Intent(this, InsertActivity.class);
                return true;
            case R.id.action_update:
                Log.w("MainActivity", "Update Selected!");
                //Intent insertIntent = new Intent(this, InsertActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}