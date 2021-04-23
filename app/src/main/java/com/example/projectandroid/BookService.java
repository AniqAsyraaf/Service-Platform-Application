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

public class BookService extends AppCompatActivity {

    int serlistID=0;
    EditText date, time;
    Button btnConfirm, btnCancel;
    DatabaseHelper myDb;

    int id2, custID;
    String servicename, description, Email, status;
    double pricemin, pricemax;

    TextView name, desc, pmin, pmax;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_service);
        Bundle bundle=getIntent().getExtras();
        //id=bundle.getInt("id");
        Intent mIntent = getIntent();
        Email=bundle.getString("Email");
        serlistID= mIntent.getIntExtra("id", 0); //serlist id
        custID= mIntent.getIntExtra("custID", 0); //customer id
        myDb=new DatabaseHelper(this);

        date=(EditText)findViewById(R.id.editTextDate);
        time=(EditText)findViewById(R.id.editTextTime);
        btnConfirm=(Button)findViewById(R.id.button7);
        btnCancel=(Button)findViewById(R.id.button8);

        name=(TextView)findViewById(R.id.textView20);
        desc=(TextView)findViewById(R.id.textView22);
        pmin=(TextView)findViewById(R.id.textView23);
        pmax=(TextView)findViewById(R.id.textView24);

        //name.setText(String.valueOf(id));'

        //String tempID = id.toString();


        Cursor res2=myDb.getSpecificDataService("select * from serlist_table where ID=\'"+serlistID+"\';");

        while (res2.moveToNext())
        {
            id2=res2.getInt(0);

            servicename=res2.getString(1);
            description=res2.getString(2);
            pricemin=res2.getDouble(3);
            pricemax=res2.getDouble(4);
        }

        String tempmin = new Double(pricemin).toString();
        String tempmax = new Double(pricemax).toString();

        name.setText(servicename);
        desc.setText(description);
        pmin.setText(tempmin);
        pmax.setText(tempmax);
    }


    public void confirmBtn(View v)
    {

        status="Waiting";
        boolean isInserted = myDb.insertDataBooking(date.getText().toString(), time.getText().toString(), serlistID, custID, status);
        if (isInserted)
        {
            Toast.makeText(BookService.this, "Insert Successful", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(BookService.this, "Insert Not Successful", Toast.LENGTH_SHORT).show();

        finish();
        /*Intent intent = new Intent(BookService.this, CustomerMainMenu.class);
        intent.putExtra("Email",Email);
        intent.putExtra("custID",custID);
        startActivity(intent);*/
        /*Intent intent = new Intent(BookService.this,CustomerMainMenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
    }
    public void cancelBtn(View v)
    {
        //showMessage("Cancel", "Cancel");
        finish();
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
