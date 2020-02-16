package com.example.eventscheduler.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.adapter.UserAdapter;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.model.User;
import com.example.eventscheduler.serverresponse.ImageResponse;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.eventscheduler.url.Url.token;

public class AddEventActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView recyclerView_user;
    List<User> userList;
    UserAdapter userAdapter;
    Button btnAddNotes;
    EditText etNote;
    EditText etDesc;
    EditText etLoc;
    ArrayList<User> rv_users;

    //User Details in Navigation
    private CircleImageView imgProfile;
    String imagePath;
    private String imageName = "";
    ImageView imgProfileImg;
    //Button btnlogout;
    TextView finame;
    TextView liname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
//        imgProgileImg=navigationView.getHeaderView();
//        finame=navigationView.findViewById(R.id.Name);


        imgProfileImg = header.findViewById(R.id.Img);
        finame = header.findViewById(R.id.Name);
        liname = header.findViewById(R.id.lName);
        loadCurrentUser();
        recyclerView_user = findViewById(R.id.recyclerView_user);

        getAllUsers();

        btnAddNotes = findViewById(R.id.btnAddNote);
        etNote = findViewById(R.id.etNote);
        etLoc = findViewById(R.id.etLoc);
        etDesc = findViewById(R.id.etDesc);
        imgProfile = findViewById(R.id.imgEvent);


        btnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //User List Toast
                // Select();
                saveImageOnly();
                addNotes();
            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void addNotes() {
        //Task notes = new Task(etNote.getText().toString());

        EventAPI noteAPI = Url.getInstance().create(EventAPI.class);

        Call<Void> voidCall = noteAPI.addEvent(Url.token, etNote.getText().toString(), etDesc.getText().toString(), etLoc.getText().toString(), imageName,userAdapter.ins());

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddEventActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddEventActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEventActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (menuItem.getItemId()) {
            case R.id.home:
                Intent intn = new Intent(AddEventActivity.this, DashboardActivity.class);
                startActivity(intn);
                break;
            case R.id.addEvent:
                Intent i = new Intent(AddEventActivity.this, AddEventActivity.class);
                startActivity(i);

                //         getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new addEventFragment()).commit();

                break;
//
            case R.id.Cart:
                Intent in = new Intent(AddEventActivity.this, ViewEventActivity.class);
                startActivity(in);
                //           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;
            case R.id.iEvents:
                Intent ine = new Intent(AddEventActivity.this, NotificationActivity.class);
                startActivity(ine);
                //           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;

            case  R.id.Map:
                Intent inten=new Intent(AddEventActivity.this,MapsActivity.class);
                startActivity(inten);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;
            case R.id.Account:
                Intent inte = new Intent(AddEventActivity.this, UserProfileActivity.class);
                startActivity(inte);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;

            case R.id.Logout:
                Toast.makeText(AddEventActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                logout();
                break;


        }

        return true;
    }

    private void logout() {
        if (Url.token != "Bearer ") {
            Url.token = "Bearer ";
        }
        Intent i = new Intent(AddEventActivity.this, LoginActivity.class);
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
                    Toast.makeText(AddEventActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
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


                Toast.makeText(AddEventActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    } private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void getAllUsers() {
        final UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<List<User>> listCall = usersAPI.getUsers(Url.token);

        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddEventActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                userList = response.body();
                for (User user : userList) {
                    Log.d("Users", "onResponse :" + user.toString());
                }
                userAdapter = new UserAdapter(AddEventActivity.this,userList);
                recyclerView_user.setAdapter(userAdapter);
                recyclerView_user.setLayoutManager(new LinearLayoutManager(AddEventActivity.this));
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


    }

}

