package com.example.myapplication.league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnOpenSoon = findViewById(R.id.button3);
        btnOpenSoon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,CommingSoon.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button btnOpenHistory = findViewById(R.id.button5);
        btnOpenHistory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,HistoryActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button btnOpenFav = findViewById(R.id.button4);
        btnOpenFav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,FavoriteActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }


}