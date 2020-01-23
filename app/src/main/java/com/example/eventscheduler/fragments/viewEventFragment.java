package com.example.eventscheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.activity.ViewEventActivity;
import com.example.eventscheduler.adapter.EventAdapter;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class viewEventFragment extends Fragment {

    private RecyclerView recyclerView;
    //recyclerView = findViewById(R.id.recyclerView);
    public viewEventFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_view_event, container, false);
        View view = inflater.inflate(R.layout.fragment_view_event, container, false);

//        cat_recyclerview= view.findViewById(R.id.cat_recyclerview);
//        rv_product= view.findViewById(R.id.recyproduct);

        recyclerView = view.findViewById(R.id.recyclerView);


        getAllEvents();
//        CategoryAdapter categoryAdapter= new CategoryAdapter(getContext(),lstcat);
//        cat_recyclerview.setAdapter(categoryAdapter);
//        cat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
//
//        ProductAdapter productAdapter= new ProductAdapter(getContext(),lstproduct);
//        rv_product.setAdapter(productAdapter);
//        rv_product.setLayoutManager(new GridLayoutManager(getContext(),3));


        return view;
    }

    private void getAllEvents(){
        EventAPI eventAPI= Url.getInstance().create(EventAPI.class);
        Call<List<Event>> listCall=eventAPI.getEvent(Url.token);

        listCall.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(!response.isSuccessful()){
                   // Toast.makeText(viewEventFragment.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Event> eventList=response.body();

                for (Event event: eventList){
                    Log.d("Events","onResponse :"+event.toString());
                }

                EventAdapter eventsAdapter = new EventAdapter(getContext(),eventList);
                recyclerView.setAdapter(eventsAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


            }



            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                //Toast.makeText(ViewEventActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
