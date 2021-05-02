package com.sliit.smartserviceassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DisplayPrice extends AppCompatActivity {
    private Button btn_launch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_price);

        // Google maps launch
        btn_launch = findViewById(R.id.btn_launch_stores);
        btn_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayPrice.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}