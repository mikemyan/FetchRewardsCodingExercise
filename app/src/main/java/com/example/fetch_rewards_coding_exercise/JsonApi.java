package com.example.fetch_rewards_coding_exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {
    @GET("hiring.json")
    Call<List<Item>> getItems();
}
