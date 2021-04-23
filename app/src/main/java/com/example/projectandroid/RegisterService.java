package com.example.projectandroid;

import android.Manifest;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterService extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btnSubmitData, btnLogo;
    Spinner editServiceType,state,area;
    EditText editBusinessName;
    EditText editContactNumber;
    EditText editEmail;
    EditText editPassword;
    ImageView imgLogo;

    final int REQUEST_CODE_GALLERY=999;

    ArrayAdapter<String> dataAdapter;
    ArrayAdapter<String> myAdapterstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.register_service);
        myDb=new DatabaseHelper(this);
        editBusinessName=(EditText)findViewById(R.id.editTextTextPersonName3);
        editServiceType=(Spinner)findViewById(R.id.spinner1);
        editContactNumber=(EditText)findViewById(R.id.editTextTextPersonName6);
        editEmail=(EditText)findViewById(R.id.editTextTextEmailAddress);
        editPassword=(EditText)findViewById(R.id.editTextTextPersonName8);
        btnLogo=(Button)findViewById(R.id.logo);
        btnSubmitData=(Button)findViewById(R.id.button);
        imgLogo=(ImageView)findViewById(R.id.imageView8);


        ArrayAdapter<String> myAdapter=new ArrayAdapter<>(RegisterService.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Service_Provider_Type));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editServiceType.setAdapter(myAdapter);

        state=(Spinner)findViewById(R.id.state);
        area=(Spinner)findViewById(R.id.area);

        myAdapterstate=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.state));
        myAdapterstate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(myAdapterstate);


        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position==0)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.None));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==1)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Johor));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==2)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Melaka));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==3)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Negeri_Sembilan));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==4)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Selangor));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==5)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Kedah));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==6)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Perlis));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==7)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Pulau_Pinang));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==8)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Perak));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==9)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Kelantan));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==10)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Terengganu));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==11)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Pahang));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==12)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Sabah));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==13)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.Sarawak));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==14)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.W_P_Kuala_Lumpur));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==15)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.W_P_Labuan));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                if (position==16)
                {
                    dataAdapter = new ArrayAdapter<>(RegisterService.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.W_P_Putrajaya));
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                }
                area.setAdapter(dataAdapter);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        btnLogo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                ActivityCompat.requestPermissions(RegisterService.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
            }
        });


        SubmitData();

    }

    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap=((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray=stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,int[] grantResults)
    {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"You don't have permissions to access file",Toast.LENGTH_SHORT);
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==REQUEST_CODE_GALLERY && resultCode==RESULT_OK && data!=null)
        {
            Uri uri=data.getData();

            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);

                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgLogo.setImageBitmap(bitmap);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public Boolean validate(String state, String area)
    {
        boolean result=false;

        String businessname=editBusinessName.getText().toString();
        String servicetype=editServiceType.getSelectedItem().toString();
        String contactnum=editContactNumber.getText().toString();
        String email=editEmail.getText().toString();
        String password=editPassword.getText().toString();


        if(businessname.isEmpty()||servicetype.equals("None")||contactnum.isEmpty()||email.isEmpty()||password.isEmpty()||state.equals("None")||area.equals("None"))
        {
            String show="is empty";
            if(businessname.isEmpty())
            {
                String tempstring=" business name ";
                show=tempstring+show;
            }
            if(servicetype.equals("None"))
            {
                String tempstring=" service type ";
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
            Toast.makeText(RegisterService.this, show, Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }

        return result;
    }

    public void SubmitData()
    {
        btnSubmitData.setOnClickListener(
                (view)->{
                    if(validate(state.getSelectedItem().toString(),area.getSelectedItem().toString())) {
                        boolean isInserted = myDb.insertDataService(editBusinessName.getText().toString(), editServiceType.getSelectedItem().toString(), editContactNumber.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString(), state.getSelectedItem().toString(), area.getSelectedItem().toString(),imageViewToByte(imgLogo));
                        if (isInserted)
                        {
                            Toast.makeText(RegisterService.this, "Register Successful, Please Login", Toast.LENGTH_SHORT).show();
                            imgLogo.setImageResource(R.mipmap.ic_launcher);
                        }
                        else
                            Toast.makeText(RegisterService.this, "Register Not Successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterService.this, LoginService.class);
                        startActivity(intent);
                    }
                }
        );
    }

}