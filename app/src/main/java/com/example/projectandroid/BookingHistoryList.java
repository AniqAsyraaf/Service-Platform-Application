package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookingHistoryList extends AppCompatActivity {

    DatabaseHelper myDb;
    GridView gridView;
    ArrayList<BookingHistory> list;
    BookingHistoryListAdapter adapter=null;

    String Email;

    int providerID;
    int serlistID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_history_list);

        Bundle bundle=getIntent().getExtras();
        Email=bundle.getString("Email");
        //Intent mIntent = getIntent();
        //custID= mIntent.getIntExtra("custID", 0);


        myDb=new DatabaseHelper(this);
        gridView=(GridView)findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new BookingHistoryListAdapter(this,R.layout.booking_history,list);
        gridView.setAdapter(adapter);

        Cursor res=myDb.getSpecificDataService("select ID from service_provider_table where EMAIL=\'"+Email+"\';");
        while (res.moveToNext())
        {
            providerID=res.getInt(0);
        }

        Cursor res1=myDb.getSpecificDataService("select * from serlist_table where Company_ID=\'"+providerID+"\';");
        while (res1.moveToNext())
        {
            serlistID=res1.getInt(0);

            //list.add(new Customer(id,businessname,image));
        }

        Cursor res2=myDb.getSpecificDataService("select * from booking_table where SERLIST_ID=\'"+serlistID+"\';");
        list.clear();
        while (res2.moveToNext())
        {
            int bookingID=res2.getInt(0);
            String date=res2.getString(1);
            String time=res2.getString(2);
            int serlistID2=res2.getInt(3);
            int custID=res2.getInt(4);
            String status=res2.getString(5);

            Cursor res3=myDb.getSpecificDataService("select SERVICE_NAME from serlist_table where ID=\'"+serlistID2+"\';");
            String servicename=null;
            while (res3.moveToNext())
            {
                servicename=res3.getString(0);

                //list.add(new Customer(id,businessname,image));
            }

            Cursor res4=myDb.getSpecificDataService("select NAME from customer_table where ID=\'"+custID+"\';");
            String customername=null;
            while (res4.moveToNext())
            {
                customername=res4.getString(0);

                //list.add(new Customer(id,businessname,image));
            }
            if(status.equals("Completed")||status.equals("Declined")||status.equals("Cancelled"))
            {
                list.add(new BookingHistory(servicename, date, time, customername, status, bookingID));
            }
        }



        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                BookingHistory bookingHistory=list.get(position);
                int data=bookingHistory.getBookingID();

                /*Cursor res2=myDb.getSpecificDataService("select ID from booking_table where =\'"+data+"\';");

                while (res2.moveToNext())
                {
                    int id2=res2.getInt(0);
                    Intent intent=new Intent(CustomerList.this, SerlistList.class);
                    intent.putExtra("id",id2);
                    intent.putExtra("custID",custID);
                    intent.putExtra("Email", Email);
                    startActivity(intent);
                }*/
            }


        });


    }
}
