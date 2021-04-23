package com.example.projectandroid;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CustomerChooseServiceType extends AppCompatActivity{

    String Email;
    int custID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_choose_service_type);
        Bundle bundle=getIntent().getExtras();
        Email=bundle.getString("Email");
        Intent mIntent = getIntent();
        custID= mIntent.getIntExtra("custID", 0);

    }

    public void gotoElectrician(View v)
    {
        Intent intent=new Intent(CustomerChooseServiceType.this, CustomerList.class);
        intent.putExtra("service_name","Electrician");
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);
    }
    public void gotoTechnician(View v)
    {
        Intent intent=new Intent(CustomerChooseServiceType.this, CustomerList.class);
        intent.putExtra("service_name","Technician");
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);
    }
    public void gotoPlumber(View v)
    {
        Intent intent=new Intent(CustomerChooseServiceType.this, CustomerList.class);
        intent.putExtra("service_name","Plumber");
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);
    }


}
