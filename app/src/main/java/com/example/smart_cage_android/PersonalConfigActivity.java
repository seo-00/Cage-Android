package com.example.smart_cage_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PersonalConfigActivity extends AppCompatActivity {

    private TextView tv_id, tv_name, tv_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_config);

        tv_id = findViewById(R.id.tv_id);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");

        tv_id.setText(userID);

        tv_name = findViewById(R.id.tv_name);
        String userName = intent.getStringExtra("userName");

        tv_id.setText(userName);
    }
}