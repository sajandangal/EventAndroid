package com.example.eventscheduler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.model.Category;
import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.url.Url;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.cViewHolder>{
    Context context;
    ImageView img;
    public List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.cViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_item,parent,false);
        return new CategoryAdapter.cViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.cViewHolder catholder, int position) {
        Category category = categoryList.get(position);
//        holder.imgProfile.setImageURI("http://10.0.2.2:3000/uploads/imageFile-1580175534508.jpg");
//        holder.imgProfile.setImageURI(Uri.parse("http://10.0.2.2:3000/uploads/imageFile-1580175534508.jpg"));
//        Picasso.load(Uri.parse("http://10.0.2.2:3000/uploads/imageFile-1580175534508.jpg")).into(holder.imgProfile);


        //holder.imgProfile.setImageResource(events.getImage().hashCode());
        //holder.imgProfile.setImageResource(events.getImage());
//        StrictModeClass.StrictMode();
//        try {
//            String imgPath = events.getImage();
//            System.out.println("the image url is: "+imgPath);
//            URL url = new URL(imgPath);
//            System.out.println("the image url is: "+url);
//
//            holder.imgProfile.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //holder.imgProfile.setImageResource(events.getImage());
        //holder.imgProfile.setImageResource(contacts.getImageId());
        catholder.tvName.setText(category.getName());
        //holder.tvPhoneNo.setText(category.getLocation());

        // Picasso.get().load(imgPath).into(imgProgileImg);

//        Picasso.get()
//            .load(events.getImage())
//            .into(holder.imgProfile);

        Picasso.get()
                .load(Url.imagePath + category.getImage())
                .into(catholder.imgProfile);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class cViewHolder extends RecyclerView.ViewHolder{

        CircleImageView imgProfile;
        TextView tvName,tvPhoneNo;
        public cViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.catimage);
            tvName = itemView.findViewById(R.id.catname);
            //tvPhoneNo = itemView.findViewById(R.id.contactview_tvPhoneNo);
        }
    }


}
