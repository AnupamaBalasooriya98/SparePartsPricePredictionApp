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

        // Ged vehicle details from the main activity interface
        model = String.valueOf(sparePartsMainActivity.spin_vehicle_model.getSelectedItemPosition());
        type = String.valueOf(sparePartsMainActivity.spin_vehicle_type.getSelectedItemPosition());
        part = String.valueOf(sparePartsMainActivity.spin_part_type.getSelectedItemPosition());

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
//        topic.setText(model, TextView.BufferType.EDITABLE);
        topic.setText("Toyota Corolla Headlight");

    }

    private void getUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}