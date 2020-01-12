package com.example.eventscheduler.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.eventscheduler.R;

import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.eventscheduler.url.Url.getInstance;
import static com.example.eventscheduler.url.Url.token;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//User nav
ImageView imgProgileImg;
    //Button btnlogout;
    TextView finame;
    TextView lname;
    //--------------------
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    Fragment selectedFragment=null;
    private ActionBarDrawerToggle mToggle;
    private FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawyerlayout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
//        imgProgileImg=navigationView.getHeaderView();
//        finame=navigationView.findViewById(R.id.Name);


        imgProgileImg= header.findViewById(R.id.Img);
        finame=header.findViewById(R.id.Name);
        lname=header.findViewById(R.id.lName);
        //imgProgileImg = navigationView.findViewById(R.id.imgProgileImg);
      //  fname = navigationView.findViewById(R.id.btnName);
       // lname=navigationView.findViewById(R.id.btnlName);
        loadCurrentUser();






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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();

        switch (menuItem.getItemId()){
            case R.id.addEvent:
                Intent i=new Intent(DashboardActivity.this,AddEventActivity.class);
                startActivity(i);
                //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new DashboardFragment()).commit();

                break;
//
            case R.id.Cart:
                Intent in=new Intent(DashboardActivity.this,ViewEventActivity.class);
                startActivity(in);
               // getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new CartFragment()).commit();
                break;
            case  R.id.Account:
                Intent inte=new Intent(DashboardActivity.this,UserProfileActivity.class);
                startActivity(inte);
                //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new AccountFragment()).commit();
                break;

            case R.id.Logout:
                Toast.makeText(DashboardActivity.this,"clcikd",Toast.LENGTH_SHORT).show();
                logout();
                break;



        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //here is rough code....



//    public void profile(View view){
//        Intent intent=new Intent(DashboardActivity.this,UserProfileActivity.class);
//        startActivity(intent);
//    }

    private void logout(){
        if(Url.token!="Bearer "){
            Url.token="Bearer ";
        }
        Intent i=new Intent(DashboardActivity.this,LoginActivity.class);
        startActivity(i);
    }










    public void loadCurrentUser() {
        //user token access here from URL
        final UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final Call<User> userCall = usersAPI.getUserDetails(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = Url.imagePath +  response.body().getImage();
                String firstName = response.body().getFirstName();
                String lastName= response.body().getLastName();
                finame.setText(firstName);
                lname.setText(lastName);



                // Picasso.get().load(imgPath).into(imgProgileImg);

                StrictModeClass.StrictMode();
                try {
                    URL url = new URL(imgPath);
                    imgProgileImg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


                Toast.makeText(DashboardActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        findViewById(R.id.btnLogout).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                token=null;
//                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
//                intent.putExtra("finish", true); // if you are checking for this in your other Activities
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
//                        Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
//
//            }
//
//
//        });


    }


}
