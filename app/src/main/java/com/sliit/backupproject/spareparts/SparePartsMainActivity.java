package com.sliit.backupproject.spareparts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.sliit.backupproject.R;

public class SparePartsMainActivity extends AppCompatActivity {
    private Button btn_search;

    public Spinner spin_vehicle_model;
    public Spinner spin_vehicle_type;
    public Spinner spin_part_type;
    public Spinner spin_year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_parts_main);

        // Select vehicle model Spinner
        spin_vehicle_model = findViewById(R.id.spin_vehicle_model);
        String[] arraySpinner_model = new String[] {
                "Toyota", "Nissan", "KIA", "Suzuki", "Hyundai"
        };
        Spinner s = (Spinner) findViewById(R.id.spin_vehicle_model);
        ArrayAdapter<String> adapter_model = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_model);
        adapter_model.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter_model);

        // Select vehicle type Spinner
        spin_vehicle_type = findViewById(R.id.spin_vehicle_type);
        String[] arraySpinner_type = new String[] {
                "Corolla", "Corolla Hybrid", "Corolla Hatchback", "Prius", "Prius Prime",
                "Camry", "Camry Hybrid", "Avalon", "Avalon Hybrid", "Mirai", "86",
                "GR Supra", "RAV4 Hybrid", "RAV4 Prime", "Highlander Hybrid", "Venza",
                "Sienna", "RAV4", "Highlander", "R4 Runner", "Sequoia", "Land Cruiser", "C-HR"
        };
        Spinner s2 = (Spinner) findViewById(R.id.spin_vehicle_type);
        ArrayAdapter<String> adapter_type = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_type);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter_type);

        // Select vehicle spare part type Spinner
        spin_part_type = findViewById(R.id.spin_spare_part);
        String[] arraySpinner_part_type = new String[] {
                "Headlights", "Wind Screen", "Wipers", "Side Mirrors", "Signal Lights"
        };
        Spinner s3 = (Spinner) findViewById(R.id.spin_spare_part);
        ArrayAdapter<String> adapter_part_type = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_part_type);
        adapter_part_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(adapter_part_type);

        // Select year spinner
        spin_year = findViewById(R.id.spin_year);
        String[] arraySpinner_year = new String[] {
                "2021", "2022", "2023", "2024", "2025"
        };
        Spinner s4 = (Spinner) findViewById(R.id.spin_year);
        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner_year);
        adapter_part_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s4.setAdapter(adapter_year);

        // Search button
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = String.valueOf(spin_vehicle_model.getSelectedItem());
                String type = String.valueOf(spin_vehicle_type.getSelectedItem());
                String part = String.valueOf(spin_part_type.getSelectedItem());
                String year = String.valueOf(spin_year.getSelectedItem());

                Intent intent = new Intent(SparePartsMainActivity.this, DisplayPriceActivity.class);
                intent.putExtra("key_model", model);
                intent.putExtra("key_type", type);
                intent.putExtra("key_part", part);
                intent.putExtra("key_year", year);
                startActivity(intent);
            }
        });

    }

}