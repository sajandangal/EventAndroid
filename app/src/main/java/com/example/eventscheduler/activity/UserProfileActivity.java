package com.example.eventscheduler.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eventscheduler.R;
import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.fragments.UpdateProfile;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.serverresponse.SignUpResponse;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;
import com.google.android.material.navigation.NavigationView;

import java.io.InputStream;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.eventscheduler.url.Url.token;

public class UserProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    TextView fname, lname,username;
//    private FrameLayout accountframe;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        fname = findViewById(R.id.firstname);
//        lname=findViewById(R.id.lstname);
//        //address=findViewById(R.id.uadd);
//        //phno=findViewById(R.id.uphone);
//        //email=findViewById(R.id.uemail);
//        username=findViewById(R.id.uname);
//
//        accountframe=findViewById(R.id.aframelayout);
//        loadCurrentUser();
//    }
//    private void loadCurrentUser() {
//        //user token access here from URL
//        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
//        Call<User>userCall= usersAPI.getUserDetails(token);
//
//        userCall.enqueue(new Callback<User>(){
//
//
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//
//                if (!response.isSuccessful()) {
//                    Toast.makeText(UserProfileActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                String firstName = response.body().getFirstName();
//                String lastName= response.body().getLastName();
//                //String uaddress= response.body().getAddress();
//                //String uphone= response.body().getPhoneno();
//                //String uemail= response.body().getEmail();
//                String susername=response.body().getUsername();
//                fname.setText(firstName);
//                lname.setText(lastName);
//                //address.setText(uaddress);
//                //phno.setText(uphone);
//                //email.setText(uemail);
//                username.setText(susername);
//            }
//
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//
//                Toast.makeText(UserProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    ImageView imgProgileImg;
    String uid;
    //Button btnlogout;
    TextView fname;
    TextView lname;
    TextView uname;
    ImageView imgProfileImg;
    Button btnUpdate;
    TextView finame;
    TextView liname;
    private String imageName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgProgileImg = findViewById(R.id.uProfile);
        fname = findViewById(R.id.ufirstName);
        lname=findViewById(R.id.ulastName);
        uname=findViewById(R.id.UpUsername);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // updateUser();
                Toast.makeText(UserProfileActivity.this,"Update",Toast.LENGTH_SHORT).show();

            }
        });
//        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawyerlayout);
        NavigationView navigationView=findViewById(R.id.nav_view);
       navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
//        imgProgileImg=navigationView.getHeaderView();
//        finame=navigationView.findViewById(R.id.Name);


        imgProfileImg= header.findViewById(R.id.Img);
        finame=header.findViewById(R.id.Name);
        liname=header.findViewById(R.id.lName);

        loadCurrentUser();


    }
    public void loadCurrentUser() {
        //user token access here from URL
        final UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        final Call<User> userCall = usersAPI.getUserDetails(token);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UserProfileActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                //String userid=response.body().getId();
                String imgPath = Url.imagePath +  response.body().getImage();
                String firstName = response.body().getFirstName();
                String lastName= response.body().getLastName();
                String userName= response.body().getUsername();
                //uid.equals(userid);
                fname.setText(firstName);
                lname.setText(lastName);
                uname.setText(userName);
                finame.setText(firstName);
                liname.setText(lastName);




                StrictModeClass.StrictMode();
                try {
                    URL url = new URL(imgPath);
                    imgProgileImg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                    imgProfileImg.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {


                Toast.makeText(UserProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void logout(){
        if(Url.token!="Bearer "){
            Url.token="Bearer ";
        }
        Intent i=new Intent(UserProfileActivity.this,LoginActivity.class);
        startActivity(i);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();


        switch (menuItem.getItemId()){
            case R.id.home:
                Intent intn=new Intent(UserProfileActivity.this,DashboardActivity.class);
                startActivity(intn);
                break;
            case R.id.addEvent:
                Intent i=new Intent(UserProfileActivity.this,AddEventActivity.class);
                startActivity(i);

    //            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new addEventFragment()).commit();

                break;
//
            case R.id.Cart:
                Intent in=new Intent(UserProfileActivity.this,ViewEventActivity.class);
                startActivity(in);
//                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;

            case R.id.iEvents:
                Intent ine = new Intent(UserProfileActivity.this, NotificationActivity.class);
                startActivity(ine);
                //           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;

            case  R.id.Map:
                Intent inten=new Intent(UserProfileActivity.this,MapsActivity.class);
                startActivity(inten);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;
            case  R.id.Account:
                Intent inte=new Intent(UserProfileActivity.this,UserProfileActivity.class);
                startActivity(inte);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;

            case R.id.Logout:
                Toast.makeText(UserProfileActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
                logout();
                break;



        }

        return true;
    }
    private void updateUser(){



        Log.d("hello", "Click bhayo!");


        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        //Call<SignUpResponse> signUpCall = usersAPI.updateProfile(token,user);

        User user = new User(uid.toString(),fname.getText().toString(),lname.getText().toString(), uname.getText().toString(),imageName);
//        Call<ResponseBody> call = userInterface.updateProfile(user);
        Call<ResponseBody> call = usersAPI.updateProfile(token, user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                Log.d("val", "sucess: ");

                if (response.isSuccessful()) {

                    Toast.makeText(UserProfileActivity.this, "profile updated success", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Toast.makeText(UserProfileActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                        Log.d("errr", response.errorBody().string() );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d("VAL", t.getLocalizedMessage());
            }
        });
    }
}
