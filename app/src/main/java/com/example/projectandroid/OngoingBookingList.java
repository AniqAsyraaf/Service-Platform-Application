package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OngoingBookingList extends AppCompatActivity {

    DatabaseHelper myDb;
    GridView gridView;
    ArrayList<OngoingBooking> list;
    OngoingBookingListAdapter adapter=null;
    String Email, date, time, servicename, status;
    int custID, serlistID, bookingID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ongoing_booking_list);
        Bundle bundle=getIntent().getExtras();

        Email=bundle.getString("Email");
        Intent mIntent = getIntent();
        custID= mIntent.getIntExtra("custID", 0);


        myDb=new DatabaseHelper(this);
        gridView=(GridView)findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new OngoingBookingListAdapter(this,R.layout.ongoing_booking,list);
        gridView.setAdapter(adapter);

        Cursor res=myDb.getSpecificDataService("select * from booking_table where CUSTOMER_ID=\'"+custID+"\';");
        list.clear();
        while (res.moveToNext())
        {
            bookingID=res.getInt(0);
            date=res.getString(1);
            time=res.getString(2);
            serlistID=res.getInt(3);
            status=res.getString(5);

            Cursor res1=myDb.getSpecificDataService("select SERVICE_NAME from serlist_table where ID=\'"+serlistID+"\';");

            while (res1.moveToNext())
            {
                servicename=res1.getString(0);
            }
            //if(status.equals("Accepted")||status.equals("Waiting"))
           // {
                list.add(new OngoingBooking(servicename,date,time,status,bookingID));
           // }

        }

        /*Cursor res1=myDb.getSpecificDataService("select * from service_provider_table where AREA=\'"+areacustomer+"\' and SERVICE_TYPE=\'"+serviceType+"\';");
        list.clear();

        while (res1.moveToNext())
        {
            int id=res1.getInt(0);
            String businessname=res1.getString(1);
            byte[] image=res1.getBlob(8);

            list.add(new Customer(id,businessname,image));
        }*/
        adapter.notifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                OngoingBooking ongoingBooking=list.get(position);
                int data=ongoingBooking.getBookingID();
                String status1=ongoingBooking.getStatus();
                /*Cursor res2=myDb.getSpecificDataService("select ID from service_provider_table where BUSINESS_NAME=\'"+data+"\';");

                while (res2.moveToNext())
                {
                    int id2=res2.getInt(0);
                    Intent intent=new Intent(OngoingBookingList.this, SerlistList.class);
                    intent.putExtra("id",data);
                    startActivity(intent);
                }*/
                if(status1.equals("Accepted")) {
                    Intent intent = new Intent(OngoingBookingList.this, ManageOngoingBooking.class);
                    intent.putExtra("id", data);
                    startActivity(intent);
                }
                if(status1.equals("Declined")){
                    showMessage("Declined", "Your order has been declined. Sorry for the inconvenience");
                }
                if(status1.equals("Completed")){
                    showMessage("Completed", "Your order has been completed");
                }
                if(status1.equals("Waiting")){
                    showMessage("Waiting", "Your order in on waiting line. Please be patient");
                }
                if(status1.equals("Cancelled")){
                    showMessage("Cancelled", "You has cancelled your order");
                }
            }



        });

    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
