package com.example.eventscheduler.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.eventscheduler.R;
//import com.example.eventscheduler.adapters.RegisterVP_Adapter;
//import com.example.eventscheduler.fragments.RegIFragment;
//import com.example.eventscheduler.fragments.RegIIFragment;
import com.google.android.material.tabs.TabLayout;

public class RegisterActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tab;
    private TextView tvGotoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        viewPager = findViewById(R.id.viewpager);
//        tab = findViewById(R.id.tab_register);
//        tvGotoLogin = findViewById(R.id.tvLogin);
//
//        tvGotoLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent goBack = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(goBack);
//            }
//        });
//
//        RegisterVP_Adapter adapter = new RegisterVP_Adapter(getSupportFragmentManager(), 1);
//
//        adapter.addFragment(new RegIFragment(), "PHONE");
//        adapter.addFragment(new RegIIFragment(), "EMAIL");
//
//        viewPager.setAdapter(adapter);
//        tab.setupWithViewPager(viewPager);

    }
}
