package com.missouristate.penrose.mortgagecalc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static Mortgage mortgage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mortgage = new Mortgage();
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateView();
    }

    private void updateView() {
        TextView amountTV = findViewById(R.id.amount);
        amountTV.setText(mortgage.getFormattedAmount());
        TextView yearsTV = findViewById(R.id.years);
        yearsTV.setText("" + mortgage.getYears());
        TextView rateTV = (TextView) findViewById(R.id.rate);
        rateTV.setText(100 * mortgage.getRate() + "%");
        TextView monthlyTV = (TextView) findViewById(R.id.payment);
        monthlyTV.setText( mortgage.formattedMonthlyPayment());
        TextView totalTV = (TextView) findViewById(R.id.total);
        totalTV.setText(mortgage.formattedTotalPayment());
    }

    public void modifyData(View view) {

        // Message between activities
        Intent myIntent = new Intent(this, DataActivity.class);
        this.startActivity(myIntent);
    }
}