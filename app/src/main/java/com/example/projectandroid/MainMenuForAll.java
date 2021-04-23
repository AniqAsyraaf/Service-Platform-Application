package com.example.projectandroid;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

    public class MainMenuForAll extends AppCompatActivity{
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_menu_for_all);

        }

        public void gotoLogInService(View v)
        {
            Intent intent=new Intent(MainMenuForAll.this, LoginService.class);
            startActivity(intent);
        }
        public void gotoLogInCustomer(View v)
        {
            Intent intent=new Intent(MainMenuForAll.this, LoginCustomer.class);
            startActivity(intent);
        }

}
