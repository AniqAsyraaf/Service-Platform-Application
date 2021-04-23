package com.example.projectandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectandroid.CustomerChooseServiceType;
import com.example.projectandroid.CustomerList;
import com.example.projectandroid.R;

public class CustomerMainMenu extends AppCompatActivity {
    String Email;
    int custID;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_main_menu);

        Bundle bundle=getIntent().getExtras();
        Email=bundle.getString("Email");
        Intent mIntent = getIntent();
        custID= mIntent.getIntExtra("custID", 0);


    }

    public void goToChooseServiceType(View v)
    {
        Intent intent=new Intent(CustomerMainMenu.this, CustomerChooseServiceType.class);
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);
    }

    public void goToOngoingBooking(View v)
    {
        Intent intent=new Intent(CustomerMainMenu.this, OngoingBookingList.class);
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);
    }

    public void goToProfile(View v)
    {
        Intent intent=new Intent(CustomerMainMenu.this, UpdateCustomer.class);
        intent.putExtra("service_name","Electrician");
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);
    }


}
