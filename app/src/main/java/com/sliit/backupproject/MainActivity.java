package com.sliit.backupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.sliit.backupproject.insurance.InsuranceMainActivity;
import com.sliit.backupproject.maintenancedetails.MaintenanceMainActivity;
import com.sliit.backupproject.repairmethods.RepairMethodsMainActivity;
import com.sliit.backupproject.spareparts.SparePartsMainActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_insurance;
    private Button btn_maintenance;
    private Button btn_repair_methods;
    private Button btn_spare_parts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Insurance button
        btn_insurance = findViewById(R.id.btn_insurance);
        btn_insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsuranceMainActivity.class);
                startActivity(intent);
            }
        });

        // Maintenance button
        btn_maintenance = findViewById(R.id.btn_maintenance);
        btn_maintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MaintenanceMainActivity.class);
                startActivity(intent);
            }
        });

        // Repair Methods button
        btn_repair_methods = findViewById(R.id.btn_repair_methods);
        btn_repair_methods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RepairMethodsMainActivity.class);
                startActivity(intent);
            }
        });

        // Spare Parts button
        btn_spare_parts = findViewById(R.id.btn_spare_parts);
        btn_spare_parts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SparePartsMainActivity.class);
                startActivity(intent);
            }
        });

    }
}