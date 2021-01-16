package com.example.myapplication.league;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommingSoonAdapter extends RecyclerView.Adapter<CommingSoonAdapter.MyViewHolder> {

    Context context;

    private ArrayList<Pertandingan> listRecyclerItem;

    interface ListItemClickListener{
        void onListItemClick(int position);
    }

    final private ListItemClickListener mOnClickListener;

    public CommingSoonAdapter(Context ct, ArrayList<Pertandingan> listRecyclerItem, ListItemClickListener mOnClickListener) {
        context =ct;
        this.mOnClickListener = mOnClickListener;
        this.listRecyclerItem = listRecyclerItem;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pertandingan,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommingSoonAdapter.MyViewHolder holder, final int position) {

        holder.myText1.setText(listRecyclerItem.get(position).getPertandingan());
        holder.myText2.setText(listRecyclerItem.get(position).getTanggalPertandingan());
        holder.myText3.setText(listRecyclerItem.get(position).getLokasi());
        holder.myCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, Detail.class);
                myIntent.putExtra("judul", listRecyclerItem.get(position).getPertandingan());
                myIntent.putExtra("cover", listRecyclerItem.get(position).getCover());
                myIntent.putExtra("skor1", listRecyclerItem.get(position).getSkorTuanRumah());
                myIntent.putExtra("skor2", listRecyclerItem.get(position).getSkorLawan());
                myIntent.putExtra("tim1", listRecyclerItem.get(position).getTuanRumah());
                myIntent.putExtra("tim2", listRecyclerItem.get(position).getTimTamu());
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public void updateData(ArrayList<Pertandingan> items){
        listRecyclerItem = items;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView myText1, myText2, myText3;
        CardView myCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            myText1= itemView.findViewById(R.id.Judul);
            myText2= itemView.findViewById(R.id.Tanggal);
            myText3= itemView.findViewById(R.id.Lokasi);
            myCard= itemView.findViewById(R.id.card);

        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }
}
