package com.example.eventscheduler.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.adapter.EventAdapter;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewEventActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);


        recyclerView = findViewById(R.id.recyclerView);

        // Create a list of contacts to display in RecyclerView
        //List<Event> contactsList = new ArrayList<>();
        // Adding all the contacts object in list
        //contactsList.add(new Event("Dahayang Rai" ));
        //contactsList.add(new Event("Bhuwan KC" ));
        //contactsList.add(new Event("Saugat Malla" ));

        getAllEvents();
//        EventAdapter contactsAdapter = new EventAdapter(this,contactsList);
//        recyclerView.setAdapter(contactsAdapter);
//
//        //Display all the contacts in linear layour (vertically)
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getAllEvents(){
        EventAPI eventAPI= Url.getInstance().create(EventAPI.class);
        Call<List<Event>> listCall=eventAPI.getEvent(Url.token);

        listCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(ViewEventActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Event> eventList=response.body();

                for (Event event: eventList){
                    Log.d("Events","onResponse :"+event.toString());
                }

                EventAdapter eventsAdapter = new EventAdapter(ViewEventActivity.this,eventList);
                recyclerView.setAdapter(eventsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ViewEventActivity.this));


            }



            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Toast.makeText(ViewEventActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}