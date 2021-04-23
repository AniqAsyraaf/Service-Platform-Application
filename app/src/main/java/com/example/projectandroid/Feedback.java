package com.example.projectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {

    private TextView txtRate;
    private RatingBar ratingBar;
    private Button submitBtn;
    DatabaseHelper myDb;
    int custID, companyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        Intent mIntent = getIntent();

        companyID= mIntent.getIntExtra("companyID", 0); //company id
        custID= mIntent.getIntExtra("custID", 0); //customer id
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        submitBtn=(Button)findViewById(R.id.button);
        txtRate=(TextView)findViewById(R.id.textView);
        myDb=new DatabaseHelper(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtRate.setText("Your rating is "+ratingBar.getRating());

                boolean isInserted = myDb.insertDataRating(ratingBar.getRating(), custID, companyID);
                if (isInserted)
                {
                    Toast.makeText(Feedback.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Feedback.this, "Insert Not Successful", Toast.LENGTH_SHORT).show();

            }
        });



    }
}