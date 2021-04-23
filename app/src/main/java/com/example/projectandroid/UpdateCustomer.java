package com.example.projectandroid;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateCustomer extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btnUpdateData;
    EditText editName;
    EditText editContactNumber;
    EditText editEmail;
    EditText editPassword;
    EditText editAddress;
    Spinner state,area;
    String Email,setName,setContact,setEmail,setPassword,setState,setArea,setAddress;
    int custID,setID;




    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> myAdapterstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.update_profile_customer);
        Bundle bundle=getIntent().getExtras();
        Email=bundle.getString("Email");
        Intent mIntent = getIntent();
        custID= mIntent.getIntExtra("custID", 0);
        myDb=new DatabaseHelper(this);
        editName=(EditText)findViewById(R.id.editTextTextPersonName3);
        editContactNumber=(EditText)findViewById(R.id.editTextTextPersonName6);
        editEmail=(EditText)findViewById(R.id.editTextTextEmailAddress);
        editPassword=(EditText)findViewById(R.id.editTextTextPersonName8);
        btnUpdateData=(Button)findViewById(R.id.button);
        editAddress=(EditText)findViewById(R.id.editTextTextPersonName);

        state=(Spinner)findViewById(R.id.spinner);
        area=(Spinner)findViewById(R.id.spinner2);

        Cursor res2=myDb.getSpecificDataService("select * from customer_table where ID=\'"+custID+"\';");
        while(res2.moveToNext())
        {
            setID=res2.getInt(0);
            setName=res2.getString(1);
            setContact=res2.getString(2);
            setEmail=res2.getString(3);
            setPassword=res2.getString(4);
            setState=res2.getString(5);
            setArea=res2.getString(6);
            setAddress=res2.getString(7);
        }

        myAdapterstate=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.state));
        myAdapterstate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(myAdapterstate);


        for(int i= 0; i < state.getAdapter().getCount(); i++)
        {
            if(state.getAdapter().getItem(i).toString().contains(setState))
            {
                state.setSelection(i);
            }
        }


        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position==0)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.None));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==1)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Johor));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==2)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Melaka));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==3)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Negeri_Sembilan));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==4)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Selangor));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==5)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Kedah));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==6)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Perlis));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==7)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Pulau_Pinang));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==8)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Perak));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==9)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Kelantan));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==10)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Terengganu));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==11)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Pahang));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==12)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Sabah));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==13)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Sarawak));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==14)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.W_P_Kuala_Lumpur));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==15)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.W_P_Labuan));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==16)
                {
                    dataAdapter = new ArrayAdapter<>(UpdateCustomer.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.W_P_Putrajaya));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                area.setAdapter(dataAdapter);
                for(int i= 0; i < area.getAdapter().getCount(); i++)
                {
                    if(area.getAdapter().getItem(i).toString().contains(setArea))
                    {
                        area.setSelection(i);
                    }
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        editName.setText(setName);
        editContactNumber.setText(setContact);
        editEmail.setText(setEmail);
        editPassword.setText(setPassword);
        editAddress.setText(setAddress);




        UpdateData();

    }

    public Boolean validate(String state, String area)
    {
        boolean result=false;

        String name=editName.getText().toString();
        String contactnum=editContactNumber.getText().toString();
        String email=editEmail.getText().toString();
        String password=editPassword.getText().toString();
        String address=editAddress.getText().toString();


        if(name.isEmpty()||contactnum.isEmpty()||email.isEmpty()||password.isEmpty()||state.equals("None")||area.equals("None")||address.isEmpty())
        {
            String show="is empty";
            if(name.isEmpty())
            {
                String tempstring=" name ";
                show=tempstring+show;
            }
            if(contactnum.isEmpty())
            {
                String tempstring=" contact number ";
                show=tempstring+show;
            }
            if(email.isEmpty())
            {
                String tempstring=" email ";
                show=tempstring+show;
            }
            if(password.isEmpty())
            {
                String tempstring=" password ";
                show=tempstring+show;
            }
            if(state.equals("None"))
            {
                String tempstring=" state ";
                show=tempstring+show;
            }
            if(area.equals("None"))
            {
                String tempstring=" area ";
                show=tempstring+show;
            }
            if(contactnum.isEmpty())
            {
                String tempstring=" contact number ";
                show=tempstring+show;
            }
            Toast.makeText(UpdateCustomer.this, show, Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }

        return result;
    }

    public void UpdateData()
    {
        btnUpdateData.setOnClickListener(
                (view)->{
                    if(validate(state.getSelectedItem().toString(),area.getSelectedItem().toString())) {
                        String tempmid = Integer.valueOf(setID).toString();
                        boolean isUpdated= myDb.updateDataCustomer(tempmid,editName.getText().toString(), editContactNumber.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString(),state.getSelectedItem().toString(),area.getSelectedItem().toString(),editAddress.getText().toString());
                        if (isUpdated)
                            Toast.makeText(UpdateCustomer.this, "Update Successful", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(UpdateCustomer.this, "Update Not Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(UpdateCustomer.this, CustomerMainMenu.class);
                        intent.putExtra("Email",Email);
                        intent.putExtra("custID",custID);
                        startActivity(intent);
                    }
                }
        );
    }

}