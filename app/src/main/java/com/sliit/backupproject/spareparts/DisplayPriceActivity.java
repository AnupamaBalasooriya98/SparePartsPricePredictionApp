package com.sliit.backupproject.spareparts;

import android.content.Intent;
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

        // Set topic according to the selected details
//        model = String.valueOf(sparePartsMainActivity.spin_vehicle_model.getSelectedItemPosition());
//        type = String.valueOf(sparePartsMainActivity.spin_vehicle_type.getSelectedItemPosition());
//        part = String.valueOf(sparePartsMainActivity.spin_part_type.getSelectedItemPosition());

        topic = findViewById(R.id.txt_spare_part_name);
//        topic.setText(model, TextView.BufferType.EDITABLE);
        topic.setText("Toyota Corolla Headlight");

    }
}