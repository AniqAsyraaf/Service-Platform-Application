package com.example.projectandroid;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class CustomerMenu extends AppCompatActivity{


    String serviceType, Email;
    TextView serviceTypeText;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_menu);

        Bundle bundle=getIntent().getExtras();
        serviceType=bundle.getString("service_name");
        Email=bundle.getString("Email");
        serviceTypeText=(TextView)findViewById(R.id.businessname);


        Cursor res=myDb.getSpecificDataService("select * from customer_table where EMAIL="+Email);
        String areacustomer=res.getString(6);
        serviceTypeText.setText(areacustomer);
    }
}
