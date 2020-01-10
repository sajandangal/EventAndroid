package com.example.eventscheduler.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.eventscheduler.url.Url.token;

public class UserProfileActivity extends AppCompatActivity {
    ImageView imgProgileImg;
    //Button btnlogout;
    TextView fname;
    TextView lname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        imgProgileImg = findViewById(R.id.imgProgileImg);
        fname = findViewById(R.id.btnName);
        lname=findViewById(R.id.btnlName);
        loadCurrentUser();


    }
    private void loadCurrentUser() {
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
                String imgPath = Url.imagePath +  response.body().getImage();
                String firstName = response.body().getFirstName();
                String lastName= response.body().getLastName();
                fname.setText(firstName);
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


                Toast.makeText(UserProfileActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
