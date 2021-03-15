package com.missouristate.penrose.hw5exercise2tipcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalculator;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalculator = new TipCalculator(.17f, 100);
        setContentView(R.layout.activity_main);
        Configuration config = new Configuration();
        buildLayout(config);
    }

    // onClick
    public void calculate (){
        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView totalTextView = findViewById(R.id.tv_BillTotal);
        TextView tipTextView = findViewById(R.id.tv_TipTotal);

        try {
            // Convert billString and tipString to floats
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            // Update the model
            tipCalculator.setBill(billAmount);
            tipCalculator.setTip(.01f * tipPercent);

            // Ask model to calculate the tip and total
            float tip = tipCalculator.tipAmount();
            float total = tipCalculator.totalAmount();

            // Update the view
            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        } catch (NumberFormatException nfe){
            // Not good!
        }
    }

    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Do nothing.
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Do nothing.
        }

        @Override
        public void afterTextChanged(Editable s) {
            calculate();
        }
    }

    private void buildLayout(Configuration newConfig) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
        } else {
            // Do nothing.
        }

        billEditText = findViewById(R.id.et_BillAmount);
        tipEditText = findViewById(R.id.et_TipAmount);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        buildLayout(newConfig);
    }
}