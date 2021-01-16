package com.example.myapplication.league;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Detail extends AppCompatActivity {

    ImageView vcover;
    TextView vjudul, vskor1, vskor2, vtim1, vtim2;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new DatabaseHelper(this);


        Intent myIntent = getIntent();
        final String judul = myIntent.getStringExtra("judul");
        final String cover = myIntent.getStringExtra("cover");
        final String skor1 = myIntent.getStringExtra("skor1");
        final String skor2 = myIntent.getStringExtra("skor2");

        vcover = (ImageView) findViewById(R.id.imageView2);
        vjudul = (TextView) findViewById(R.id.pertandingan);
        vskor1 = (TextView) findViewById(R.id.Score1);
        vskor2 = (TextView) findViewById(R.id.Score2);

        vjudul.setText(judul);
        vskor1.setText(skor1);
        vskor2.setText(skor2);

        Picasso.get().load(cover).into(vcover);

        Button btnAdd = (Button) findViewById(R.id.btn_add_fav);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(db.insertData(judul,skor1, skor2,cover)) {
                    Toast.makeText(Detail.this, "Berhasil ditambahkan", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Detail.this,"Gagal ditambahkan",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}