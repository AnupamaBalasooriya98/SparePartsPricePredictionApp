package com.sliit.backupproject.spareparts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.backupproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayPriceActivity extends AppCompatActivity {

    SparePartsMainActivity sparePartsMainActivity = new SparePartsMainActivity();

    private Button btn_map;
    private Button btn_videos;
    private TextView avgPrice;

    private String model;
    private String type;
    private String part;
    private String year;

    private TextView topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_price);

        // Google maps button
        btn_map = findViewById(R.id.btn_launch_stores);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPriceActivity.this, StoresListActivity.class);
                startActivity(intent);
            }
        });

        // Get vehicle details from the main activity interface
        model = getIntent().getStringExtra("key_model");
        type = getIntent().getStringExtra("key_type");
        part = getIntent().getStringExtra("key_part");
        year = getIntent().getStringExtra("key_year");

        // Youtube videos button
        btn_videos = findViewById(R.id.btn_launch_videos);
        btn_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUrl("https://www.youtube.com/results?search_query=" +
                                model + "+" + type + "+" + part + "+replacement");
            }
        });

        topic = findViewById(R.id.txt_spare_part_name);
        topic.setText(model + " " + type + " " + part + " for the year of " + year);

        avgPrice = findViewById(R.id.txt_avg_price);

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
                if(!response.isSuccessful()) {
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

    }

    private void getUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}