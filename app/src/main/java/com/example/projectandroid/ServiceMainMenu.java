package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceMainMenu extends AppCompatActivity {
    String Email;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_main_menu);

        Bundle bundle=getIntent().getExtras();
        Email=bundle.getString("Email");
    }

    public void goToAddService(View v)
    {
        Intent intent=new Intent(ServiceMainMenu.this, AddNewService.class);
        intent.putExtra("Email",Email);
        startActivity(intent);
    }

    public void goToBookingList(View v)
    {
        Intent intent=new Intent(ServiceMainMenu.this, BookingList.class);
        intent.putExtra("Email",Email);
        startActivity(intent);
    }

    public void goToBookingHistory(View v)
    {
        Intent intent=new Intent(ServiceMainMenu.this, BookingHistoryList.class);
        intent.putExtra("Email",Email);
        startActivity(intent);
    }



}