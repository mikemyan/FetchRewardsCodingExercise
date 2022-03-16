package com.example.fetch_rewards_coding_exercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IdNameRecycViewAdapter extends RecyclerView.Adapter<IdNameRecycViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Item> items;
    public IdNameRecycViewAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sorted_name_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.idValueTextView.setText(String.valueOf(items.get(position).getId()));
        holder.nameValueTextView.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idValueTextView, nameValueTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idValueTextView = itemView.findViewById(R.id.id_value_textView);
            nameValueTextView = itemView.findViewById(R.id.name_value_textView);
        }
    }
}
