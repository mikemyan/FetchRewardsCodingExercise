package com.example.fetch_rewards_coding_exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SortedByName extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Item> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_by_name);

        recyclerView = findViewById(R.id.nameRecyclerView);
        String itemsString = getIntent().getStringExtra("items");
        System.out.println(itemsString);
        try {
            JSONArray itemsJsonArray = new JSONArray(itemsString);
            for(int i = 0; i < itemsJsonArray.length(); i++) {
                JSONObject itemObj = itemsJsonArray.getJSONObject(i);
                Item item = new Item(itemObj.getInt("id"), itemObj.getInt("listId"), itemObj.getString("name"));
                items.add(item);


                IdNameRecycViewAdapter idNameRecycViewAdapter = new IdNameRecycViewAdapter(this, items);
                recyclerView.setAdapter(idNameRecycViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SortedByName.this));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private ArrayList<Item> parse(String s) throws JSONException {
//        ArrayList<Item> itemsArray = new ArrayList<>();
//        String stringWithoutBracket = s.substring(1, s.length()-1);
//        for (String token : stringWithoutBracket.split( ",")) {
//            JSONObject obj = new JSONObject(token.trim());
//            Item item = new Item(obj.getInt("id"), obj.getInt("listId"), obj.getString("name"));
//            itemsArray.add(item);
//        }
//        return itemsArray;
//    }

}