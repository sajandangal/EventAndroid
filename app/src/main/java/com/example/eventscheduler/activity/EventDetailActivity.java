package com.example.eventscheduler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.url.Url;

import java.io.InputStream;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventDetailActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    String product_name;
    String product_desc;
   String product_rate;
   String u_name;
    CircleImageView product_img;
    String product_image;
    Button btnAtn;

    private TextView textViewName, textViewDesc, textViewRate,textAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        product_img= findViewById(R.id.productimg);
        textViewName=findViewById(R.id.eventName);
        textViewDesc = findViewById(R.id.eventDesc);
        textViewRate = findViewById(R.id.eventLocation);
        textAuthor=findViewById(R.id.eventUname);
        btnAtn = findViewById(R.id.atndBtn);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            product_image=extras.getString("event_image");
            product_name = extras.getString("event_name");
            product_desc = extras.getString("event_desc");
            product_rate = extras.getString("event_location");
            u_name=extras.getString("author");
            textViewName.setText(product_name);
            textViewDesc.setText(product_desc);
            textViewRate.setText(product_rate);
            textAuthor.setText(u_name);
//            product_img.setImageURI(URI.create(url.BASE_URL + "uploads/" + product_image));
            String imgPath = Url.base_url + "uploads/" + product_image;
            try {
                URL url=new URL(imgPath);
                product_img.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            } catch (Exception e) {
            }
        }



        //frameLayout=findViewById(R.id.pframelayout);
//        setFragment(new ProductDetailFragment());

        btnAtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventDetailActivity.this,"Event Attend Request",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
