package com.example.eventscheduler.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventscheduler.R;
import com.example.eventscheduler.adapter.CategoryAdapter;
import com.example.eventscheduler.adapter.EventAdapter;
import com.example.eventscheduler.api.CategoryApi;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.model.Category;
import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.url.Url;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class homeFragment extends Fragment {
    public static List<Category> lstcat= new ArrayList<>();
    public static List<Event> eventsList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    public homeFragment() {
        // Required empty public constructor
    }

    private RecyclerView cat_recyclerview, rv_events;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        cat_recyclerview= view.findViewById(R.id.cat_recyclerview);
        rv_events= view.findViewById(R.id.recyproduct);


//        CategoryAdapter categoryAdapter= new CategoryAdapter(getContext(),lstcat);
//        cat_recyclerview.setAdapter(categoryAdapter);
//        cat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
//
//        EventAdapter eventAdapter= new EventAdapter(getContext(),eventsList);
//        rv_events.setAdapter(eventAdapter);
//        rv_events.setLayoutManager(new GridLayoutManager(getContext(),3));
        getAllEvents();
        getAllCategory();

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
                rv_events.setAdapter(eventsAdapter);
                rv_events.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


            }



            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                //Toast.makeText(ViewEventActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
///Category
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

            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(),catList);
            cat_recyclerview.setAdapter(categoryAdapter);
            cat_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));


        }



        @Override
        public void onFailure(Call<List<Category>> call, Throwable t) {
            //Toast.makeText(ViewEventActivity.this, "Error : " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    });

}

}
