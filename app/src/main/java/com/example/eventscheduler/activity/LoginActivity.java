package com.example.eventscheduler.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.bll.LoginBLL;
import com.example.eventscheduler.broadcastreceiver.BroadCastReceiver;
import com.example.eventscheduler.createChannel.CreateChannel;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_PREF = "USER_PREF" ;
    public static final String KEY_UNAME = "KEY_UNAME";
    public static final String KEY_PWD = "KEY_PWD";

    private Button btnLogin;
    private NotificationManagerCompat notificationManagerCompat;
    private EditText etUsername, etPassword;
    private TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_login);
        notificationManagerCompat= NotificationManagerCompat.from(this);
        CreateChannel channel= new CreateChannel(this);
        channel.createChannel();

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        tvSignup = findViewById(R.id.tvSignup);
        btnLogin = findViewById(R.id.btnLogin);
        SPshow();
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                }
        });
    }


    public void SPsave() {
        String uname  = etUsername.getText().toString();
        String upwd  = etPassword.getText().toString();

        SharedPreferences sharedPreferences = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_UNAME, uname);
        editor.putString(KEY_PWD, upwd);
//        if(Url.token!="Bearer "){
//            Url.token="Bearer ";
//        }
        editor.commit();
    }

    public void SPshow() {
//        StringBuilder str = new StringBuilder();
        SharedPreferences sharedPreferences = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        if(Url.token != "Bearer "){
            Url.token = "Bearer ";
        }

        if (sharedPreferences.contains(KEY_UNAME)) {
            etUsername.setText(sharedPreferences.getString(KEY_UNAME, ""));
        }
        if (sharedPreferences.contains(KEY_PWD)) {
            etPassword.setText(sharedPreferences.getString(KEY_PWD, ""));
        }

    }
    private void login() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            SPsave();
            DisplayNotification();
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etUsername.requestFocus();
        }
    }


    private void DisplayNotification() {

        Notification notification= new NotificationCompat.Builder(this, CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_notifications_none_black_24dp)
                .setContentTitle("Event Scheduler")
                .setContentText("Login Success:" + etUsername.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1,notification);


    }
    BroadCastReceiver broadCastReceiver= new BroadCastReceiver(this);

    protected void onStart(){
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadCastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCastReceiver);
    }
}