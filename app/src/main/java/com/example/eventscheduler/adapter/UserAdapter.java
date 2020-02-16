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
import com.example.eventscheduler.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    Context context;
    public List<User> userList;


    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public void setUsers(ArrayList<User> userList) {
        this.userList = new ArrayList<>();
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.bind(userList.get(position));
//        final User user=userList.get(position);
//        holder.rd_user.setText(userList.get(position).getUsername());
//        //holder.rd_user.setBackgroundColor(user.isSelected() ? Color.CYAN : Color.WHITE);
//        holder.rd_user.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(user.isSelected()){
//                    InvitedUsers.add(userList.get(position).getId());
//                }
//                else{
//                    InvitedUsers.remove(userList.get(position).getId());
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        void bind(final User user) {
            imageView.setVisibility(user.isChecked() ? View.VISIBLE : View.GONE);
            textView.setText(user.getUsername());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    user.setChecked(!user.isChecked());
                    imageView.setVisibility(user.isChecked() ? View.VISIBLE : View.GONE);
                }
            });
        }
//        TextView rd_user;
//        View view;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            view=itemView;
//            rd_user=itemView.findViewById(R.id.textView);
//        }
//
//
//        public void bind(User user) {
//        }
    }

    public ArrayList<User> getSelected() {
        ArrayList<User> selected = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).isChecked()) {
                selected.add(userList.get(i));
            }
        }
        return selected;
    }

    public ArrayList<String> ins(){
        ArrayList<String> data=new ArrayList<>();
        for (int i=0; i<getSelected().size();i++){
            data.add(getSelected().get(i).getId());
        }
        return data;
    }


}

