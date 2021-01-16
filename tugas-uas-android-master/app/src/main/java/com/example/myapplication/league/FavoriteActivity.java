package com.example.myapplication.league;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity implements FavoriteAdapter.ListItemClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private static String Url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328";

    private ArrayList<Pertandingan> FavoriteEvent;

    FavoriteAdapter favoriteAdapter;

    Context context;

    RecyclerView recyclerView;


    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        recyclerView=findViewById(R.id.list);

        FavoriteEvent = new ArrayList<>();

        favoriteAdapter = new FavoriteAdapter(this, FavoriteEvent, this);

        db = new DatabaseHelper(this);
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        new AmbilData().execute();
    }

    public void updateData(ArrayList<Pertandingan> items){
        this.favoriteAdapter.updateData(items);
        favoriteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(int position) {

    }

    private class AmbilData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            Cursor res = db.getAllData();
            if(res.getCount() == 0) { return null; }

            while (res.moveToNext() ) {

                // tmp hash map for single event
                Pertandingan match = new Pertandingan();

                // adding each child node to model
                match.setPertandingan(res.getString(1));
                match.setSkorTuanRumah(res.getString(2));
                match.setSkorLawan(res.getString(3));
                match.setCover(res.getString(4));

                // adding contact to contact list
                FavoriteEvent.add(match);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            updateData(FavoriteEvent);
        }

    }

}
