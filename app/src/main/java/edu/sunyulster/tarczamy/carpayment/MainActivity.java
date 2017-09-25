package edu.sunyulster.tarczamy.carpayment;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText userCarPrice;
    private EditText userDownPayment;
    private EditText userInterestRate;
    private EditText userLoanPeriod;
    private TextView monthlyPayment;
    private TextView totalPayment;

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userCarPrice = (EditText) findViewById(R.id.carPrice);
        userDownPayment = (EditText) findViewById(R.id.downPayment);
        userInterestRate = (EditText) findViewById(R.id.interestRate);
        userLoanPeriod = (EditText) findViewById(R.id.loanPeriod);
        monthlyPayment = (TextView) findViewById(R.id.monthlyPayment);
        totalPayment = (TextView) findViewById(R.id.totalPayment);

        final Button button = (Button) findViewById(R.id.calculate);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View button) {

                double carPrice = Double.parseDouble(userCarPrice.getText().toString());
                double downPayment = Double.parseDouble(userDownPayment.getText().toString());
                double interestRate = Double.parseDouble(userInterestRate.getText().toString());
                double loanPeriod = Double.parseDouble(userLoanPeriod.getText().toString());

                loanPeriod = loanPeriod * 12;

                double finalPrice = carPrice - downPayment;
                double rate = interestRate / 1200;
                double exponential = Math.pow(rate + 1, loanPeriod);

                double m = (double) ((rate + (rate / (exponential - 1))) * finalPrice);
                double t = (m * loanPeriod) + downPayment;

                monthlyPayment.setText(currencyFormat.format(m));
                totalPayment.setText(currencyFormat.format(t));
            }
    });

}

}
