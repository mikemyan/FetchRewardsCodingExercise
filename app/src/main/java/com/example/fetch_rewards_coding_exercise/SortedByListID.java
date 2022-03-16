package com.example.fetch_rewards_coding_exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SortedByListID extends AppCompatActivity implements ListIdRecycViewAdapter.ListIdClickListerer {


    public TreeMap<Integer, ArrayList<Item>> sortedListIDTreeMap = new TreeMap();
    public TreeMap<Integer, ArrayList<Item>> allSortedTreeMap = sortedListIDTreeMap;
    public Set<Integer> sortedListIDSet = sortedListIDTreeMap.keySet();
    public ArrayList<Integer> sortedListIDArray = new ArrayList<>();
    public ArrayList<Item> sortedItems = new ArrayList<>();
    //public Item sortedItemsArray[] = new Item[sortedItems.size()];
    public ArrayList<ArrayList<Item>> allSorted = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorted_by_list_id);

        recyclerView = findViewById(R.id.listIdRecyclerView);

        Retrofit retro = new Retrofit.Builder()
                .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retro.create(JsonApi.class);

        Call<List<Item>> request = jsonApi.getItems();

        request.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SortedByListID.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Item> items = response.body();
                //System.out.println(items);
                for (Item item : items) {
                    int objListId = item.getListId();
                    //System.out.println(objListId);
                    String objName = item.getName();
                    //System.out.println(objName);
                    if (objName != null) {
                        if (!(objName.isEmpty())) {
                            if (sortedListIDTreeMap.get(objListId) == null) {
                                sortedListIDTreeMap.put(objListId, new ArrayList<Item>());
                            }
                            sortedListIDTreeMap.get(objListId).add(item);
                        }
                    }
                }
                sortedListIDArray.addAll(sortedListIDSet);
                for (Integer listId: sortedListIDArray) {
                    TreeMap<Integer, Item> sortedNameTreeMap = new TreeMap();
                    ArrayList<Item> unsortedItems = sortedListIDTreeMap.get(listId);
                    for (Item item : unsortedItems) {
                        Integer objId = item.getId();
                        sortedNameTreeMap.put(objId, item);
                    }
                    //ArrayList<Item> sortedItems = new ArrayList<>();
                    for (Map.Entry<Integer, Item> entry : sortedNameTreeMap.entrySet()) {
                        Item item = entry.getValue();
                        sortedItems.add(item);
                    }
                    allSortedTreeMap.put(listId, sortedItems);

                    allSorted.add(sortedItems);
                    //System.out.println(allSorted);
                }

//                for (int i = 0; i < sortedItems.size(); i++) {
//                    sortedItemsArray[i] = sortedItems.get(i);
//                }
                //System.out.println(sortedListIDArray);
                ListIdRecycViewAdapter listIdRecycViewAdapter = new ListIdRecycViewAdapter(SortedByListID.this, sortedListIDArray, SortedByListID.this);
                recyclerView.setAdapter(listIdRecycViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SortedByListID.this));
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                Toast.makeText(SortedByListID.this, "Cannot Retrieve Data, Please Try Again.", Toast.LENGTH_SHORT).show();
                Log.e(t.getMessage(),"Cannot Retrieve Data, Please Try Again.");
            }
        });
    }

    @Override
    public void listen(int position) {
        JSONArray allSortedJson= new JSONArray(allSorted);
        Intent intent = new Intent(this, SortedByName.class);
        intent.putExtra("items", allSortedJson.toString());
        startActivity(intent);
    }
}