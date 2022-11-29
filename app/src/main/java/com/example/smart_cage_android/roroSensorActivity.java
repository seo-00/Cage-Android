package com.example.smart_cage_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.smart_cage_android.databinding.ActivityRoroSensorBinding;

public class roroSensorActivity extends AppCompatActivity {

    ActivityRoroSensorBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding = ActivityRoroSensorBinding.inflate(getLayoutInflater());

        String[] items = {"온도/습도/조도", "온도", "습도", "조도"};

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        viewBinding.spinnerSensor.setAdapter(dataAdapter);

        viewBinding.spinnerSensor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //viewBinding.spinnerTest.setText(items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //viewBinding.spinnerTest.setText(items[0]);
            }

        });

        setContentView(viewBinding.getRoot());
    }
}