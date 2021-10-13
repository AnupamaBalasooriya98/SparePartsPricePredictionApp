package com.sliit.backupproject.spareparts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.sliit.backupproject.R;

public class StoresListActivity extends AppCompatActivity {

    Button btn_enter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores_list);

        btn_enter = findViewById(R.id.btn_enter);
        listView = findViewById(R.id.list_stores);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}