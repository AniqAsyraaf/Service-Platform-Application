package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewService extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btnAdd;
    Button btnCancel;
    EditText serviceName, serviceDesc, pricemin, pricemax;
    String Email;
    TextView textView;
    //Boolean validemail=false;
    //Boolean validpassword=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_service);
        Bundle bundle=getIntent().getExtras();
        Email=bundle.getString("Email");

        myDb = new DatabaseHelper(this);
        btnAdd = (Button) findViewById(R.id.button4);
        btnCancel = (Button) findViewById(R.id.button2);
        serviceName = (EditText) findViewById(R.id.editTextTextPersonName5);
        serviceDesc = (EditText) findViewById(R.id.editTextTextPersonName7);
        pricemin = (EditText) findViewById(R.id.editTextNumberDecimal);
        pricemax = (EditText) findViewById(R.id.editTextNumberDecimal2);
        //Toast.makeText(AddNewService.this, Email, Toast.LENGTH_SHORT).show(); ***tanya faiq, ni untuk apa?***
        //SubmitData();
    }

    public void addNewService(View v)
    {
        int id=0;
        Cursor res=myDb.getSpecificDataService("select ID from service_provider_table where EMAIL=\'"+Email+"\';");
        while(res.moveToNext())
        {
             id=res.getInt(0);
        }
        boolean isInserted = myDb.insertDataSerlist(serviceName.getText().toString(), serviceDesc.getText().toString(), Double.parseDouble(pricemin.getText().toString()), Double.parseDouble(pricemax.getText().toString()), id);
        if (isInserted)
        {
            Toast.makeText(AddNewService.this, "Insert Successful", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(AddNewService.this, "Insert Not Successful", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(AddNewService.this, ServiceMainMenu.class);
        intent.putExtra("Email",Email);
        startActivity(intent);
    }

    public void goToServiceMainMenu(View v)
    {
        Intent intent=new Intent(AddNewService.this, ServiceMainMenu.class);
        startActivity(intent);
    }

    /*public void SubmitData()
    {
        btnAdd.setOnClickListener(
                (view)->{
                    Cursor res=myDb.getSpecificDataService("select ID from service_provider_table where EMAIL=\'"+Email+"\';");
                    int id=Integer.parseInt(res.getString(0));

                    boolean isInserted = myDb.insertDataSerlist(serviceName.getText().toString(), serviceDesc.getText().toString(), Double.parseDouble(pricemin.getText().toString()), Double.parseDouble(pricemax.getText().toString()), id);
                    if (isInserted)
                    {
                        Toast.makeText(AddNewService.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(AddNewService.this, "Insert Not Successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddNewService.this, ServiceMainMenu.class);
                    startActivity(intent);

                }
        );*/
    }
//}