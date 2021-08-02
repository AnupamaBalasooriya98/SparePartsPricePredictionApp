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

public class DisplayPriceActivity extends AppCompatActivity {

    SparePartsMainActivity sparePartsMainActivity = new SparePartsMainActivity();

    private Button btn_map;
    private Button btn_videos;

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
                getUrl("https://www.google.lk/maps/search/spare+parts+stores/" +
                        "@6.1980526,80.025728,12z/data=!4m5!2m4!5m2!4e8!10e2!6e6");
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

    }

    private void getUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}