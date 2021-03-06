package com.example.myapplication.league;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CommingSoon extends AppCompatActivity implements CommingSoonAdapter.ListItemClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;

    private static String oncomingUrl = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328";

    private ArrayList<Pertandingan> oncomingEventList;

    CommingSoonAdapter commingSoonAdapter;

    Context context;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comming_soon);

        recyclerView=findViewById(R.id.list);

        oncomingEventList = new ArrayList<>();

        commingSoonAdapter = new CommingSoonAdapter(this, oncomingEventList, this);

        recyclerView.setAdapter(commingSoonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new GetData().execute();
    }

    public void updateData(ArrayList<Pertandingan> items){
        this.commingSoonAdapter.updateData(items);
        commingSoonAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(int position) {

    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(CommingSoon.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStrOncoming = sh.makeServiceCall(oncomingUrl);

            Log.e(TAG, "Response from url: " + jsonStrOncoming);

            // Process data
            if (jsonStrOncoming != null) {
                try {

                    JSONObject jsonObjOncoming = new JSONObject(jsonStrOncoming);

                    // Getting JSON Array node
                    JSONArray oncomingEvents = jsonObjOncoming.getJSONArray("events");

                    // looping through oncoming events
                    for (int i = 0; i < oncomingEvents.length(); i++) {
                        JSONObject c = oncomingEvents.getJSONObject(i);

                        String idPertandingan = c.getString("idEvent");
                        String Pertandingan = c.getString("strEvent");
                        String waktuPertandingan = c.getString("dateEvent");
                        String TanggalPertandingan = c.getString("strTime");
                        String TuanRumah = c.getString("intAwayScore");
                        String TimTamu = c.getString("intHomeScore");
                        String idTuanRumah = c.getString("strHomeTeam");
                        String idTimTamu = c.getString("strAwayTeam");
                        String Lokasi = c.getString("strVenue");
                        String Cover = c.getString("strThumb");

                        // tmp hash map for single event
                        Pertandingan match = new Pertandingan();

                        // adding each child node to model

                        match.setIdPertandingan(idPertandingan);
                        match.setPertandingan(Pertandingan);
                        match.setWaktuPertandingan(waktuPertandingan);
                        match.setTanggalPertandingan(TanggalPertandingan);
                        match.setTuanRumah(idTuanRumah);
                        match.setTimTamu(idTimTamu);
                        match.setSkorTuanRumah(TuanRumah);
                        match.setSkorLawan(TimTamu);
                        match.setIdTuanRumah(idTuanRumah);
                        match.setIdTimTamu(idTimTamu);
                        match.setLokasi(Lokasi);
                        match.setCover(Cover);

                        // adding contact to contact list
                        oncomingEventList.add(match);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            updateData(oncomingEventList);


        }

    }

}
