package com.example.eventscheduler.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.activity.EventDetailActivity;
import com.example.eventscheduler.activity.NotificationActivity;
import com.example.eventscheduler.model.Notification;
import com.example.eventscheduler.url.Url;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ContactsViewHolder> {

    Context context;
    //ImageView img;
    public List<Notification> eventsList;


    public NotificationAdapter(Context context, List<Notification> eventsList) {
        this.context = context;
        this.eventsList = eventsList;

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
        holder.tvName.setText(eventsList.get(position).getEvents().getName());
        holder.tvPhoneNo.setText(eventsList.get(position).getEvents().getLocation());
        //  holder.tvPhoneNo.setText(String.valueOf(lstproduct.get(position).getRate()));
        String imgPath = Url.base_url + "uploads/" + eventsList.get(position).getEvents().getImage();
        StrictMode();
        try {
            URL url=new URL(imgPath);
            holder.imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
        }

        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("event_image",eventsList.get(position).getEvents().getImage());
                intent.putExtra("event_name",eventsList.get(position).getEvents().getName());
                intent.putExtra("event_desc",eventsList.get(position).getEvents().getDesc());
                intent.putExtra("event_location",eventsList.get(position).getEvents().getLocation());
                intent.putExtra("author",eventsList.get(position).getEvents().getAuthor().getUsername());

                ((NotificationActivity) context).startActivity(intent);

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

}