package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    EditText etWeight, etPrice;
    RadioGroup rgType;
    RadioButton rbKeep, rbWear;
    TextView tvResult, tvNote;
    Button btnCalculate, btnReset;

    double nisabKeep = 85;
    double nisabWear = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Link XML to Java
        etWeight = findViewById(R.id.etWeight);
        etPrice = findViewById(R.id.etPrice);
        rgType = findViewById(R.id.rgType);
        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);
        tvResult = findViewById(R.id.tvResult);
        tvNote = findViewById(R.id.tvNote);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);

        // CALCULATE BUTTON
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Validate input
                if (etWeight.getText().toString().isEmpty() ||
                        etPrice.getText().toString().isEmpty()) {

                    Toast.makeText(MainActivity.this,
                            "Please enter all input fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double weight = Double.parseDouble(etWeight.getText().toString());
                double price = Double.parseDouble(etPrice.getText().toString());

                // Determine gold type
                double nisab;
                if (rbKeep.isChecked()) {
                    nisab = nisabKeep;
                } else {
                    nisab = nisabWear;
                }

                // Calculation
                double totalGoldValue = weight * price;

                double zakatPayableValue = 0;
                if (weight > nisab) {
                    zakatPayableValue = (weight - nisab) * price;
                }

                double totalZakat = zakatPayableValue * 0.025;

                // Display results
                tvResult.setText(
                        "Total Gold Value: RM " + totalGoldValue +
                                "\nZakat Payable Value: RM " + zakatPayableValue +
                                "\nTotal Zakat (2.5%): RM " + totalZakat
                );

                // Helpful note
                if (weight <= nisab) {
                    tvNote.setText("No zakat required — gold does not exceed nisab (" + nisab + "g).");
                } else {
                    tvNote.setText("Zakat is required since gold exceeds the nisab level.");
                }
            }
        });

        // RESET BUTTON
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etWeight.setText("");
                etPrice.setText("");
                rbKeep.setChecked(true);
                tvResult.setText("");
                tvNote.setText("");
            }
        });
    }

    // MENU LOAD
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // MENU ACTIONS
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // About Page
        if (item.getItemId() == R.id.menuAbout) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        // Settings
        if (item.getItemId() == R.id.menuSettings) {
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        //Share
        else if (item.getItemId() == R.id.menuShare) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out my Zakat Application! - https://github.com/NurainSyazwani14/ZakatGoldCalculator.git");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return super.onOptionsItemSelected(item);
        }

        return super.onOptionsItemSelected(item);
    }
}
