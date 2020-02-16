package com.example.eventscheduler.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.adapter.NotificationAdapter;
import com.example.eventscheduler.api.NotificationApi;
import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.model.Notification;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.eventscheduler.url.Url.token;

public class NotificationActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView;
    List<Notification> eventList;
    NotificationAdapter eventsAdapter;
    ImageView imgProfileImg;
    //Button btnlogout;
    TextView finame;
    TextView liname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recyclerView);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
//        imgProgileImg=navigationView.getHeaderView();
//        finame=navigationView.findViewById(R.id.Name);


        imgProfileImg = header.findViewById(R.id.Img);
        finame = header.findViewById(R.id.Name);
        liname = header.findViewById(R.id.lName);
        loadCurrentUser();


        getAllNotification();

    }


    private void getAllNotification() {
        NotificationApi notificationApi = Url.getInstance().create(NotificationApi.class);
        Call<List<Notification>> listCall = notificationApi.getNotify(token);

        listCall.enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(NotificationActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                eventList = response.body();

                for (Notification event : eventList) {
                    Log.d("Notification", "onResponse :" + event.toString());
                }

                eventsAdapter = new NotificationAdapter(NotificationActivity.this, eventList);
                recyclerView.setAdapter(eventsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));


            }


            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {
                Toast.makeText(NotificationActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void loadCurrentUser() {
        //user token access here from URL
        final UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final Call<User> userCall = usersAPI.getUserDetails(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(NotificationActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = Url.imagePath + response.body().getImage();
                String firstName = response.body().getFirstName();
                String lastName = response.body().getLastName();

                finame.setText(firstName);
                liname.setText(lastName);


                StrictModeClass.StrictMode();
                try {
                    URL url = new URL(imgPath);

                    imgProfileImg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


                Toast.makeText(NotificationActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void logout() {
        if (Url.token != "Bearer ") {
            Url.token = "Bearer ";
        }
        Intent i = new Intent(NotificationActivity.this, LoginActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent intn = new Intent(NotificationActivity.this, DashboardActivity.class);
                startActivity(intn);
                break;
            case R.id.addEvent:
                Intent i = new Intent(NotificationActivity.this, AddEventActivity.class);
                startActivity(i);

                //         getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new addEventFragment()).commit();

                break;
//
            case R.id.Cart:
                Intent in = new Intent(NotificationActivity.this, ViewEventActivity.class);
                startActivity(in);
                //           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;

            case R.id.iEvents:
                Intent ine = new Intent(NotificationActivity.this, NotificationActivity.class);
                startActivity(ine);
                //           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;

            case  R.id.Map:
                Intent inten=new Intent(NotificationActivity.this,MapsActivity.class);
                startActivity(inten);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;
            case R.id.Account:
                Intent inte = new Intent(NotificationActivity.this, UserProfileActivity.class);
                startActivity(inte);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;

            case R.id.Logout:
                Toast.makeText(NotificationActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                logout();
                break;


        }

        return true;
    }
}