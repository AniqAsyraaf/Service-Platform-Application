package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity {

    DatabaseHelper myDb;
    GridView gridView;
    ArrayList<Customer> list;
    CustomerListAdapter adapter=null;
    String serviceType, Email;
    String areacustomer=null;
    int custID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_menu);

        Bundle bundle=getIntent().getExtras();
        serviceType=bundle.getString("service_name");
        Email=bundle.getString("Email");
        Intent mIntent = getIntent();
        custID= mIntent.getIntExtra("custID", 0);


        myDb=new DatabaseHelper(this);
        gridView=(GridView)findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new CustomerListAdapter(this,R.layout.customer_menu_2,list);
        gridView.setAdapter(adapter);

        Cursor res=myDb.getSpecificDataService("select AREA from customer_table where EMAIL=\'"+Email+"\';");
        while (res.moveToNext())
        {
            areacustomer=res.getString(0);
        }
        Cursor res1=myDb.getSpecificDataService("select * from service_provider_table where AREA=\'"+areacustomer+"\' and SERVICE_TYPE=\'"+serviceType+"\';");
        list.clear();

        while (res1.moveToNext())
        {
            int id=res1.getInt(0);
            String businessname=res1.getString(1);
            byte[] image=res1.getBlob(8);

            float rating, totalrating=0, avgrating=0;
            int counter=0;
            //rating
            Cursor res3=myDb.getSpecificDataService("select * from rating_table where COMPANY_ID=\'"+id+"\';");
            while (res3.moveToNext())
            {
                counter++;
                rating=res3.getFloat(1);
                totalrating=totalrating+rating;
            }
            avgrating=totalrating/counter;
            list.add(new Customer(id,businessname,image,avgrating));
        }


        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Customer customer=list.get(position);
                String data=customer.getBusinessname();
                Cursor res2=myDb.getSpecificDataService("select ID from service_provider_table where BUSINESS_NAME=\'"+data+"\';");

                while (res2.moveToNext())
                {
                    int id2=res2.getInt(0);
                    Intent intent=new Intent(CustomerList.this, SerlistList.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("custID",custID);
                    intent.putExtra("Email", Email);
                    startActivity(intent);
                }

            }


        });


    }

}
