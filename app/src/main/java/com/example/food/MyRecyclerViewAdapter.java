package com.example.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends  RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder>{

    private List<Getterforview> list;
    private Context context;

    public MyRecyclerViewAdapter(List<Getterforview> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.MyHolder holder, int position) {

        holder.itempid.setText(list.get(position).getId()+"");
        holder.itempname.setText(list.get(position).getProductName()+"");
        holder.itempamount.setText(list.get(position).getProductAmount()+"");
        holder.itempdoe.setText(list.get(position).getProductDoe()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView itempid,itempname,itempamount,itempdoe;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            itempid=itemView.findViewById(R.id.itempid);
            itempname=itemView.findViewById(R.id.itempname);
            itempamount=itemView.findViewById(R.id.itempamount);
            itempdoe=itemView.findViewById(R.id.itempdoe);
        }
    }
}
