package com.example.zakatgoldcalculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AboutActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Toolbar setup
        Toolbar toolbar = findViewById(R.id.toolbarAbout);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("About");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // back button
        }

        // TextView with clickable URL
        TextView tvWebsite = findViewById(R.id.tvWebsite);
        tvWebsite.setMovementMethod(LinkMovementMethod.getInstance());
        tvWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/NurainSyazwani14/ZakatGoldCalculator.git"));
            startActivity(browserIntent);
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Back button closes activity
        return true;
    }
}
