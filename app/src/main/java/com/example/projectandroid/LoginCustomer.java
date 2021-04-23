package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginCustomer extends AppCompatActivity{

    DatabaseHelper myDb;
    Button btnLogIn;
    Button btnViewAll;
    EditText editEmail, editPassword;
    Boolean validemail=false;
    Boolean validpassword=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_customer);
        myDb=new DatabaseHelper(this);
        btnLogIn=(Button)findViewById(R.id.button4);
        btnViewAll=(Button)findViewById(R.id.button2);
        editEmail=(EditText)findViewById(R.id.editTextTextEmailAddress2);
        editPassword=(EditText)findViewById(R.id.editTextTextPassword);
        validateLogIn();
        viewAll();

    }

    public void gotoRegisterCustomer(View v)
    {
        Intent intent=new Intent(LoginCustomer.this, RegisterCustomer.class);
        startActivity(intent);
    }
    public void validateLogIn()
    {

        btnLogIn.setOnClickListener(
                view -> {

                    Cursor res=myDb.getAllDataCustomer();
                    if (res.getCount()==0)
                    {
                        showMessage("Error","Nothing Found");
                        return;
                    }

                    String Email=editEmail.getText().toString();
                    String Password=editPassword.getText().toString();
                    while (res.moveToNext())
                    {
                        if(Email.equals(res.getString(3)))
                        {
                            validemail=true;
                            if(Password.equals(res.getString(4)))
                            {
                                validpassword=true;
                                break;
                            }
                        }
                        else
                        {
                            validemail=false;
                            validpassword=false;
                        }

                    }
                    if (validemail&&validpassword)
                    {
                        int custID=0;
                        Cursor res2=myDb.getSpecificDataService("select ID from customer_table where EMAIL=\'"+editEmail.getText().toString()+"\';");
                        while(res2.moveToNext())
                        {
                            custID=res2.getInt(0);
                        }
                        Intent intent=new Intent(LoginCustomer.this, CustomerMainMenu.class);
                        intent.putExtra("Email",editEmail.getText().toString());
                        intent.putExtra("custID",custID);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginCustomer.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }


                }
        );
    }


    public void viewAll()
    {
        btnViewAll.setOnClickListener(
                view -> {
                    Cursor res=myDb.getAllDataCustomer();
                    if (res.getCount()==0)
                    {
                        showMessage("Error","Nothing Found");
                        return;
                    }

                    StringBuilder buffer=new StringBuilder();
                    while (res.moveToNext())
                    {
                        buffer.append("ID: ").append(res.getString(0)).append("\n");
                        buffer.append("Name: ").append(res.getString(1)).append("\n");
                        buffer.append("Contact Num: ").append(res.getString(2)).append("\n");
                        buffer.append("Email: ").append(res.getString(3)).append("\n");
                        buffer.append("Password: ").append(res.getString(4)).append("\n");
                        buffer.append("State: ").append(res.getString(5)).append("\n");
                        buffer.append("Area: ").append(res.getString(6)).append("\n");
                        buffer.append("Address: ").append(res.getString(7)).append("\n");
                    }
                    showMessage("Data",buffer.toString());
                }
        );
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
