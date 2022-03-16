package com.example.fetch_rewards_coding_exercise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ListIdRecycViewAdapter extends RecyclerView.Adapter<ListIdRecycViewAdapter.ViewHolder> {
    Context context;
    ArrayList<Integer> sortedListId;
    //ArrayList<ArrayList<Item>> allSorted;
    ListIdClickListerer listIdClickListerer;
    //ArrayList<Item> sortedItems;
    //TreeMap<Integer, ArrayList<Item>> allSortedTreeMap;
    //Item[] sortedItemsArray;
    public ListIdRecycViewAdapter(Context context, ArrayList<Integer> sortedListId, ListIdClickListerer listIdClickListerer) {

        this.context = context;
        this.sortedListId = sortedListId;
        this.listIdClickListerer = listIdClickListerer;
        //this.allSorted = allSorted;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.sorted_listid_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.listIdText.setText(String.valueOf(sortedListId.get(position)));
        holder.sortedListIdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listIdClickListerer.listen(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sortedListId.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listIdText;
        ConstraintLayout sortedListIdLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listIdText = itemView.findViewById(R.id.listid_textView);
            sortedListIdLayout = itemView.findViewById(R.id.sortedListIdLayout);
        }
    }
    public interface ListIdClickListerer {
        void listen(int position);
    }
}
