package com.sliit.backupproject.spareparts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.backupproject.R;

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
                Intent intent = new Intent(DisplayPriceActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        // Get vehicle details from the main activity interface
        model = getIntent().getStringExtra("key_model");
        type = getIntent().getStringExtra("key_type");
        part = getIntent().getStringExtra("key_part");
        year = getIntent().getStringExtra("key_year");

        Intent intent = new Intent(DisplayPriceActivity.this, Predictor.class);
        intent.putExtra("key_model", model);
        intent.putExtra("key_type", type);
        intent.putExtra("key_part", part);
        intent.putExtra("key_year", year);
        startActivity(intent);

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
//        avgPrice.setText("Rs. 35375");
//        Call method from Predictor class

    }

    private void getUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}