package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ManageBooking extends AppCompatActivity {

    int bookingID=0;

    Button btnAccept, btnDecline;
    DatabaseHelper myDb;

    int bookingID2, custID, serlistID;
    String servicename, Email, status, custname, custaddress, custphone, bookdate, booktime, tempBookingID;
    double pricemin, pricemax;

    TextView name, pmin, pmax, date, time, cname, caddress, cphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_booking);
        Bundle bundle=getIntent().getExtras();
        //id=bundle.getInt("id");
        Intent mIntent = getIntent();
        bookingID= mIntent.getIntExtra("id", 0); //serlist id

        myDb=new DatabaseHelper(this);


        btnAccept=(Button)findViewById(R.id.button9);
        btnDecline=(Button)findViewById(R.id.button10);

        name=(TextView)findViewById(R.id.textView28);
        pmin=(TextView)findViewById(R.id.textView29);
        pmax=(TextView)findViewById(R.id.textView35);

        date=(TextView)findViewById(R.id.textView36);
        time=(TextView)findViewById(R.id.textView37);
        cname=(TextView)findViewById(R.id.textView38);
        caddress=(TextView)findViewById(R.id.textView39);
        cphone=(TextView)findViewById(R.id.textView40);

        //name.setText(String.valueOf(id));'

        //String tempID = id.toString();


        Cursor res=myDb.getSpecificDataService("select * from booking_table where ID=\'"+bookingID+"\';");

        while (res.moveToNext())
        {
            bookingID2=res.getInt(0);
            bookdate=res.getString(1);
            booktime=res.getString(2);
            serlistID=res.getInt(3);
            custID=res.getInt(4);
            status=res.getString(5);
        }

        Cursor res2=myDb.getSpecificDataService("select * from serlist_table where ID=\'"+serlistID+"\';");

        while (res2.moveToNext())
        {
            servicename=res2.getString(1);
            pricemin=res2.getDouble(3);
            pricemax=res2.getDouble(4);
        }

        Cursor res3=myDb.getSpecificDataService("select * from customer_table where ID=\'"+custID+"\';");

        while (res3.moveToNext())
        {
            custname=res3.getString(1);
            custphone=res3.getString(2);
            custaddress=res3.getString(7);

        }

        String tempmin = new Double(pricemin).toString();
        String tempmax = new Double(pricemax).toString();

        tempBookingID = new Integer(bookingID).toString();


        name.setText(servicename);
        pmin.setText(tempmin);
        pmax.setText(tempmax);
        date.setText(bookdate);
        time.setText(booktime);
        cname.setText(custname);
        caddress.setText(custaddress);
        cphone.setText(custphone);
    }


    public void acceptBtn(View v)
    {
        if(status.equals("Declined"))
        {
            showMessage("Error:Declined", "You have declined this order");
        }
        else
        {
            status="Accepted";
            boolean isUpdated = myDb.updateDataBookingStatus(tempBookingID,  status);
            if (isUpdated)
            {
                Toast.makeText(ManageBooking.this, "Update Successful", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(ManageBooking.this, "Update Not Successful", Toast.LENGTH_SHORT).show();
            showMessage("Accepted", "Accepted");
        }
        //finish();
        /*Intent intent = new Intent(BookService.this, CustomerMainMenu.class);
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);*/
        /*Intent intent = new Intent(BookService.this,CustomerMainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }
    public void declineBtn(View v)
    {
        if(status.equals("Accepted"))
        {
            showMessage("Error:Accepted", "You have accepted this order");
        }
        else
        {
            status="Declined";
            boolean isUpdated = myDb.updateDataBookingStatus(tempBookingID, status);
            if (isUpdated)
            {
                Toast.makeText(ManageBooking.this, "Update Successful", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(ManageBooking.this, "Update Not Successful", Toast.LENGTH_SHORT).show();
            showMessage("Declined", "Declined");
        }
        //finish();
        //Intent intent = new Intent(BookService.this, CustomerMainMenu.class);
        //intent.putExtra("Email",Email);
        ///intent.putExtra("custID",custID);
        //startActivity(intent);
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
