package com.example.eventscheduler.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.activity.DashboardActivity;
import com.example.eventscheduler.activity.EventDetailActivity;
import com.example.eventscheduler.activity.ViewEventActivity;
import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.url.Url;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;



public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ContactsViewHolder> implements Filterable {

    Context context;
    //ImageView img;
    public List<Event> eventsList;
    private List<Event> eventListFull;

    public EventAdapter(Context context, List<Event> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
        eventListFull=new ArrayList<>(eventsList);
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contactview,parent,false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, final int position) {
        holder.tvName.setText(eventsList.get(position).getName());
        holder.tvPhoneNo.setText(eventsList.get(position).getLocation());
      //  holder.tvPhoneNo.setText(String.valueOf(lstproduct.get(position).getRate()));
        String imgPath = Url.base_url + "uploads/" + eventsList.get(position).getImage();
        StrictMode();
        try {
            URL url=new URL(imgPath);
            holder.imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
        }

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,EventDetailActivity.class);
                intent.putExtra("event_image",eventsList.get(position).getImage());
                intent.putExtra("event_name",eventsList.get(position).getName());
                intent.putExtra("event_desc",eventsList.get(position).getDesc());
                intent.putExtra("event_location",eventsList.get(position).getLocation());
                intent.putExtra("author",eventsList.get(position).getAuthor().getUsername());

                ((DashboardActivity) context).startActivity(intent);

            }
        });

    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView tvName,tvPhoneNo;
        View view;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            imgProfile = itemView.findViewById(R.id.contactview_imgProfile);
            tvName = itemView.findViewById(R.id.contactview_tvName);
            tvPhoneNo = itemView.findViewById(R.id.contactview_tvPhoneNo);


        }
        public View getView(){
            return view;
        }
    }
    public Filter getFilter(){
      return evetsFilter;
    };
    private Filter evetsFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Event> filteredList= new ArrayList<>();

            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(eventListFull);
            }else{
                String filterPattern=constraint.toString().toLowerCase().trim();

                for(Event event1 : eventListFull){
                    if(event1.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(event1);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            eventsList.clear();
            eventsList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}