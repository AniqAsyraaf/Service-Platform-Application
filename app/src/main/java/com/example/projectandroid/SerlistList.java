package com.example.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SerlistList extends AppCompatActivity {

    DatabaseHelper myDb;
    GridView gridView;
    ArrayList<Serlist> list;
    SerlistListAdapter adapter=null;
    int id, custID;
    String Email;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_list_menu);
        Bundle bundle=getIntent().getExtras();
        Intent mIntent = getIntent();
        id= mIntent.getIntExtra("id", 0);
        custID= mIntent.getIntExtra("custID", 0);
        Email=bundle.getString("Email");

        myDb=new DatabaseHelper(this);
        gridView=(GridView)findViewById(R.id.gridView);
        list=new ArrayList<>();
        adapter=new SerlistListAdapter(this,R.layout.service_list,list);
        gridView.setAdapter(adapter);


        Cursor res1=myDb.getSpecificDataService("select * from serlist_table where Company_ID=\'"+id+"\';");
        list.clear();

        while (res1.moveToNext())
        {
            int id1=res1.getInt(0);
            String servicename=res1.getString(1);
            String description=res1.getString(2);
            double price_min=res1.getDouble(3);
            double price_max=res1.getDouble(4);
            int company_id=res1.getInt(5);


            list.add(new Serlist(id1, servicename, description, price_min, price_max, company_id));
        }
        adapter.notifyDataSetChanged();




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Serlist serlist=list.get(position);
                int id1 = serlist.getId();

                Intent intent=new Intent(SerlistList.this, BookService.class);
                intent.putExtra("id",id1);
                intent.putExtra("custID",custID);
                startActivity(intent);

                /*Cursor res2=myDb.getSpecificDataService("select * from serlist_table where ID=\'"+id+"\';");

                while (res2.moveToNext())
                {
                    int id2=res2.getInt(0);
                    Intent intent=new Intent(SerlistList.this, BookService.class);
                    intent.putExtra("id",id2);
                    startActivity(intent);
                }*/

            }


        });


    }
    /*public void viewAll(int id)
    {
        Cursor res=myDb.getSpecificDataService("select * from serlist_table where Company_ID=\'"+id+"\';");
        if (res.getCount()==0)
        {
            showMessage("Error","Nothing Found");
            return;
        }

        StringBuilder buffer=new StringBuilder();
        while (res.moveToNext())
        {
            buffer.append("ID: ").append(res.getString(0)).append("\n");
            buffer.append("Service Name: ").append(res.getString(1)).append("\n");
            buffer.append("Description: ").append(res.getString(2)).append("\n");
            buffer.append("Price_min: ").append(res.getString(3)).append("\n");
            buffer.append("Price_max: ").append(res.getString(4)).append("\n");
            buffer.append("Company_id: ").append(res.getString(5)).append("\n");
        }
        showMessage("Data",buffer.toString());

    }
    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/

}
