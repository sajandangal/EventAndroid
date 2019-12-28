package com.example.eventscheduler.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventscheduler.R;

import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.eventscheduler.url.Url.getInstance;
import static com.example.eventscheduler.url.Url.token;

public class DashboardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);





        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, AddEventActivity.class));
            }
        });

        findViewById(R.id.btnView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ViewEventActivity.class));
            }
        });

        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(token!="Bearer "){
                    token="Bearer ";

                }

                    Intent intent = new Intent(DashboardActivity.this,SplashActivity.class);

                    startActivity(intent);
                    finish();
            }
        });

        findViewById(R.id.btnUserprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DashboardActivity.this,UserProfileActivity.class);
                startActivity(i);
                finish();
            }
        });




    }



    //here is rough code....



//    public void profile(View view){
//        Intent intent=new Intent(DashboardActivity.this,UserProfileActivity.class);
//        startActivity(intent);
//    }


}
