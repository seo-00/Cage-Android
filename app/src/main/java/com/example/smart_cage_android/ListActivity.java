package com.example.smart_cage_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    private Button btn_move;
    private Button roro;
    private Button btn_cage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        btn_move = findViewById(R.id.nextpagebtn);
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this , MainActivity.class);
                startActivity(intent); //activity 이동
            }
        });

        btn_cage = findViewById(R.id.btn_cage);
        btn_cage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (ListActivity.this, CageManagementActivity.class);
                startActivity(intent);
            }
        });

        roro = findViewById(R.id.testbtn1);
        roro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this , RoRoActivity.class);
                startActivity(intent); //activity 이동
            }
        });

    }

}