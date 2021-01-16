package com.example.myapplication.league;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>{
    Context context;

    private ArrayList<Pertandingan> listRecyclerItem;

    interface ListItemClickListener{
        void onListItemClick(int position);
    }

    final private FavoriteAdapter.ListItemClickListener mOnClickListener;

    public FavoriteAdapter(Context ct, ArrayList<Pertandingan> listRecyclerItem, FavoriteAdapter.ListItemClickListener mOnClickListener) {
        context =ct;
        this.mOnClickListener = mOnClickListener;
        this.listRecyclerItem = listRecyclerItem;
    }

    @NonNull
    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_pertandingan,parent,false);
        return new FavoriteAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.MyViewHolder holder, int position) {

        holder.myText1.setText(listRecyclerItem.get(position).getPertandingan());
        holder.myText2.setText(listRecyclerItem.get(position).getTanggalPertandingan());
        holder.myText3.setText(listRecyclerItem.get(position).getLokasi());

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            myText1= itemView.findViewById(R.id.Judul);
            myText2= itemView.findViewById(R.id.Tanggal);
            myText3= itemView.findViewById(R.id.Lokasi);

        }

        @Override
        public void onClick(View v) {
            int position =getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }
}
