package com.example.eventscheduler.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.adapter.CategoryAdapter;
import com.example.eventscheduler.adapter.EventAdapter;
import com.example.eventscheduler.api.CategoryApi;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.fragments.viewEventFragment;
import com.example.eventscheduler.model.Category;
import com.example.eventscheduler.model.Event;
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

<<<<<<< HEAD
=======
import static com.example.eventscheduler.R.*;
import static com.example.eventscheduler.R.string.*;
>>>>>>> origin/testing
import static com.example.eventscheduler.url.Url.token;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RecyclerView cat_recyclerview, rv_events;
    List<Event> eventList;
    EventAdapter eventsAdapter;

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

<<<<<<< HEAD
        setContentView(R.layout.activity_dashboard);

=======
        setContentView(layout.activity_dashboard);



        mDrawerLayout= (DrawerLayout) findViewById(id.drawyerlayout);
        mToggle= new ActionBarDrawerToggle(this, mDrawerLayout,  open, close);
>>>>>>> origin/testing

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=findViewById(id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
<<<<<<< HEAD

=======
>>>>>>> origin/testing



        imgProgileImg= header.findViewById(id.Img);
        finame=header.findViewById(id.Name);
        lname=header.findViewById(id.lName);
        //imgProgileImg = navigationView.findViewById(R.id.imgProgileImg);
      //  fname = navigationView.findViewById(R.id.btnName);
       // lname=navigationView.findViewById(R.id.btnlName);

<<<<<<< HEAD
        cat_recyclerview= findViewById(R.id.cat_recyclerview);
        rv_events= findViewById(R.id.recyproduct);
=======
        cat_recyclerview= findViewById(id.cat_recyclerview);
        rv_events= findViewById(id.recyproduct);
>>>>>>> origin/testing
        loadCurrentUser();
        //getAllEvents();
        getAllCategory();
        EventAPI eventAPI= Url.getInstance().create(EventAPI.class);
        Call<List<Event>> listCall=eventAPI.getEvent(Url.token);

        listCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                eventList=response.body();

                for (Event event: eventList){
                    Log.d("Events","onResponse :"+event.toString());
                }

                eventsAdapter = new EventAdapter(DashboardActivity.this,eventList);
                rv_events.setAdapter(eventsAdapter);
                rv_events.setLayoutManager(new LinearLayoutManager(DashboardActivity.this));


            }





            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();

        switch (menuItem.getItemId()){
            case R.id.home:
                Intent inm=new Intent(DashboardActivity.this,DashboardActivity.class);
                startActivity(inm);

                //        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new addEventFragment()).commit();

                break;
            case R.id.addEvent:
                Intent i=new Intent(DashboardActivity.this,AddEventActivity.class);
                startActivity(i);

        //        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new addEventFragment()).commit();

                break;
//
            case R.id.Cart:
//                Intent in=new Intent(DashboardActivity.this,ViewEventActivity.class);
//                startActivity(in);
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
               break;
            case R.id.iEvents:
                Intent ine = new Intent(DashboardActivity.this, NotificationActivity.class);
                startActivity(ine);
                //           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new viewEventFragment()).commit();
                break;


<<<<<<< HEAD
            case  R.id.Map:
=======
            case id.map:
>>>>>>> origin/testing
                Intent inten=new Intent(DashboardActivity.this,MapsActivity.class);
                startActivity(inten);
                //   getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new profileFragment()).commit();
                break;
            case  R.id.Account:
                Intent inte=new Intent(DashboardActivity.this,UserProfileActivity.class);
                startActivity(inte);
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


    }



    private void getAllCategory(){
        CategoryApi categoryApi= Url.getInstance().create(CategoryApi.class);
        Call<List<Category>> listCall=categoryApi.getCategory(Url.token);

        listCall.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(!response.isSuccessful()){
                    // Toast.makeText(viewEventFragment.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Category> catList=response.body();

                for (Category category: catList){
                    Log.d("Category","onResponse :"+category.toString());
                }

                CategoryAdapter categoryAdapter = new CategoryAdapter(DashboardActivity.this,catList);
                cat_recyclerview.setAdapter(categoryAdapter);
                cat_recyclerview.setLayoutManager(new LinearLayoutManager(DashboardActivity.this,RecyclerView.HORIZONTAL,false));


            }



            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                //Toast.makeText(ViewEventActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_item,menu);
<<<<<<< HEAD
        MenuItem searchItem=menu.findItem(R.id.action_search);
=======
        MenuItem searchItem=menu.findItem(id.action_search);
>>>>>>> origin/testing
        SearchView searchView =(SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               eventsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
