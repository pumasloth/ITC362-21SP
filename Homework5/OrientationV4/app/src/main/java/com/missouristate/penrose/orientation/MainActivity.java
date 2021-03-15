package com.missouristate.penrose.orientation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String MA = "MainActivity";
    public static final int ACTION_BAR_HEIGHT = 56;

    private float pixelDensity;
    private boolean verticalDimensionsSet, horizontalDimensionsSet;
    private int spacingInVP, spacingInHP;
    private Button btn1, btn2, btn3;
    private int actionBarHeight;

    public static int screenHeightInVP, screenHeightInHP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.w(MA, "Inside onCreate");

        super.onCreate(savedInstanceState);

        setUpGUI();
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        pixelDensity = displayMetrics.density;

        Configuration config = getResources().getConfiguration();
        checkDimensions(config);
        modifyLayout(config);
    }

    private void checkDimensions(Configuration config) {
        Log.w(MA, "Inside checkDimensions");

        actionBarHeight = (int) (pixelDensity * ACTION_BAR_HEIGHT);
        TypedValue typedValue = new TypedValue();
        if (getTheme().resolveAttribute(R.attr.actionBarSize, typedValue, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(
                    typedValue.data,
                    getResources().getDisplayMetrics());
        }

        Log.w(MA, "action bar height " + actionBarHeight);

        btn1.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int buttonHeight = btn1.getMeasuredHeight();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            screenHeightInHP = (int) (config.screenWidthDp * pixelDensity);
            spacingInHP = (screenHeightInHP - actionBarHeight - 3 * buttonHeight) / 4;
            horizontalDimensionsSet = true;
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            screenHeightInVP = (int) (config.screenHeightDp * pixelDensity);
            spacingInVP = (screenHeightInVP - actionBarHeight - 3 * buttonHeight) / 4;
            verticalDimensionsSet = true;
        } else  {
            // Do nothing.
        }
    }

    private void setUpGUI() {
        Log.w(MA, "Inside setUpGUI");

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(LinearLayout.VERTICAL);

        btn1 = new Button(this);
        btn2 = new Button(this);
        btn3 = new Button(this);

        // Set the button text.
        btn1.setText(R.string.GOTOV1);
        btn2.setText(R.string.GOTOV2);
        btn3.setText(R.string.GOTOV3);

        // Set the button parameters.
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        btn1.setLayoutParams(params);
        btn2.setLayoutParams(params);
        btn3.setLayoutParams(params);

        // Add the buttons to the view.
        linearLayout.addView(btn1);
        linearLayout.addView(btn2);
        linearLayout.addView(btn3);

        // Set the text size.
        btn1.setTextSize(36);
        btn2.setTextSize(36);
        btn3.setTextSize(36);

        // Apply the layout.
        setContentView(linearLayout);
    }

    private void modifyLayout(Configuration newConfig) {
        Log.w(MA, "Inside modifyLayout");

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setLayoutMargins(spacingInHP);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setLayoutMargins(spacingInVP);
        } else {
            // Do nothing.
        }
    }

    private void setLayoutMargins(int spacing) {
        Log.w(MA, "Inside setLayoutMargins");

        ViewGroup.MarginLayoutParams params1 =
                (ViewGroup.MarginLayoutParams) btn1.getLayoutParams();
        ViewGroup.MarginLayoutParams params2 =
                (ViewGroup.MarginLayoutParams) btn2.getLayoutParams();
        ViewGroup.MarginLayoutParams params3 =
                (ViewGroup.MarginLayoutParams) btn3.getLayoutParams();

        params1.setMargins(0, spacing, 0, 0);
        params2.setMargins(0, spacing, 0, 0);
        params3.setMargins(0, spacing, 0, 0);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.w(MA, "Inside onConfigurationChanged");

        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }
}