package com.sliit.backupproject.spareparts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.backupproject.R;

public class DisplayPriceActivity extends AppCompatActivity {

    private Button btn_map;
    private Button btn_videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_price);

        // Google maps button
        btn_map = findViewById(R.id.btn_launch_stores);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPriceActivity.this, NearBySparePartsShopsGoogleMapsActivity.class);
                startActivity(intent);
            }
        });

        // Youtube videos button
        btn_videos = findViewById(R.id.btn_launch_videos);
        btn_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPriceActivity.this, SuggestYoutubeVideosActivity.class);
                startActivity(intent);
            }
        });

    }
}