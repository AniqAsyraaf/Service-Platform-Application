package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerChooseService extends AppCompatActivity {

    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list);
        Bundle bundle=getIntent().getExtras();
        id=bundle.getInt("id");

        Intent intent=new Intent(CustomerChooseService.this, SerlistList.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }



}
