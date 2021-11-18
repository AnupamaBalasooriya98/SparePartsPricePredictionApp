package com.sliit.backupproject.spareparts;

import android.widget.TextView;

import android.content.Intent;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Predictor {
    private TextView avgPrice;
    private String model, type, year;

    TextView predictPrice() {
        // Predict price for the requested part
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    avgPrice.setText("Rs. " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += post.getPrice();

                    avgPrice.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                avgPrice.setText(t.getMessage());
            }
        });

        return avgPrice;
    }

    void predictPrices() {

        SparePartsMainActivity activitySparePartsMain = new SparePartsMainActivity();

        // Get vehicle details from the main activity interface
//        model = getIntent().getStringExtra("key_model");
//        type = getIntent().getStringExtra("key_type");
//        year = getIntent().getStringExtra("key_year");
    }

}
