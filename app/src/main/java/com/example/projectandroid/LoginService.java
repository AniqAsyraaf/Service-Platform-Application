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

public class LoginService extends AppCompatActivity{

    DatabaseHelper myDb;
    Button btnLogIn;
    Button btnViewAll;
    EditText editEmail, editPassword;
    Boolean validemail=false;
    Boolean validpassword=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_service);
        myDb=new DatabaseHelper(this);
        btnLogIn=(Button)findViewById(R.id.button4);
        btnViewAll=(Button)findViewById(R.id.button2);
        editEmail=(EditText)findViewById(R.id.editTextTextEmailAddress2);
        editPassword=(EditText)findViewById(R.id.editTextTextPassword);
        validateLogIn();
        viewAll();

    }

    public void gotoRegisterService(View v)
    {
        Intent intent=new Intent(LoginService.this, RegisterService.class);
        startActivity(intent);
    }
    public void validateLogIn()
    {

        btnLogIn.setOnClickListener(
                view -> {

                    Cursor res=myDb.getAllDataService();
                    if (res.getCount()==0)
                    {
                        showMessage("Error","Nothing Found");
                        return;
                    }

                    String Email=editEmail.getText().toString();
                    String Password=editPassword.getText().toString();
                    while (res.moveToNext())
                    {
                        if(Email.equals(res.getString(4)))
                        {
                            validemail=true;
                            if(Password.equals(res.getString(5)))
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
                        Intent intent=new Intent(LoginService.this, ServiceMainMenu.class);
                        intent.putExtra("Email",editEmail.getText().toString());
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginService.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }


                }
        );
    }

    public void viewAll()
    {
        btnViewAll.setOnClickListener(
                view -> {
                    Cursor res=myDb.getAllDataService();
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
                        buffer.append("Service Type: ").append(res.getString(2)).append("\n");
                        buffer.append("Contact Num: ").append(res.getString(3)).append("\n");
                        buffer.append("Email: ").append(res.getString(4)).append("\n");
                        buffer.append("Password: ").append(res.getString(5)).append("\n");
                        buffer.append("State: ").append(res.getString(6)).append("\n");
                        buffer.append("Area: ").append(res.getString(7)).append("\n\n");
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
