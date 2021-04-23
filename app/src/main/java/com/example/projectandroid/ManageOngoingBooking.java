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

public class ManageOngoingBooking extends AppCompatActivity {

    int bookingID=0;

    Button btnComplete, btnCancel;
    DatabaseHelper myDb;

    int bookingID2, custID, serlistID, companyID;
    String servicename, Email, status, providername, providerphone, bookdate, booktime, tempBookingID;
    double pricemin, pricemax;

    TextView name, pmin, pmax, date, time, pname, pphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_ongoing_booking);
        Bundle bundle=getIntent().getExtras();
        //id=bundle.getInt("id");
        Intent mIntent = getIntent();
        bookingID= mIntent.getIntExtra("id", 0); //serlist id

        myDb=new DatabaseHelper(this);


        btnComplete=(Button)findViewById(R.id.button9);
        btnCancel=(Button)findViewById(R.id.button10);

        name=(TextView)findViewById(R.id.textView28);
        pmin=(TextView)findViewById(R.id.textView29);
        pmax=(TextView)findViewById(R.id.textView35);

        date=(TextView)findViewById(R.id.textView36);
        time=(TextView)findViewById(R.id.textView37);
        pname=(TextView)findViewById(R.id.textView38);
        pphone=(TextView)findViewById(R.id.textView39);

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
            companyID= res2.getInt(5);
        }

        Cursor res3=myDb.getSpecificDataService("select * from service_provider_table where ID=\'"+companyID+"\';");

        while (res3.moveToNext())
        {
            providername=res3.getString(1);
            providerphone=res3.getString(3);
        }

        String tempmin = new Double(pricemin).toString();
        String tempmax = new Double(pricemax).toString();

        tempBookingID = new Integer(bookingID).toString();


        name.setText(servicename);
        pmin.setText(tempmin);
        pmax.setText(tempmax);
        date.setText(bookdate);
        time.setText(booktime);
        pname.setText(providername);
        pphone.setText(providerphone);
    }


    public void completeBtn(View v)
    {

        if(status.equals("Cancelled"))
        {
            showMessage("Error:Cancelled", "You have cancelled this order");
        }
        else
        {
            status="Completed";
            boolean isUpdated = myDb.updateDataBookingStatus(tempBookingID,  status);
            if (isUpdated)
            {
                Toast.makeText(ManageOngoingBooking.this, "Update Successful", Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(ManageOngoingBooking.this, "Update Not Successful", Toast.LENGTH_SHORT).show();
            showMessage("Completed", "Completed");
            Intent intent = new Intent(ManageOngoingBooking.this,Feedback.class);
            intent.putExtra("custID", custID);
            intent.putExtra("companyID", companyID);
            startActivity(intent);
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
    public void cancelBtn1(View v)
    {
        if(status.equals("Completed"))
        {
            showMessage("Error:Completed", "This order has been completed");
        }
        else
        {
            status="Cancelled";
            boolean isUpdated = myDb.updateDataBookingStatus(tempBookingID, status);
            if (isUpdated)
            {
                Toast.makeText(ManageOngoingBooking.this, "Update Successful", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(ManageOngoingBooking.this, "Update Not Successful", Toast.LENGTH_SHORT).show();
            showMessage("Cancelled", "Cancelled");
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
