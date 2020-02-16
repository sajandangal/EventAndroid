package com.example.wearos;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.text);
        String message = getIntent().getStringExtra("message");
        if(message == null || message.equalsIgnoreCase("")){
            message = "Hello World";
        }
        mTextView.setText(message);
    }
}
