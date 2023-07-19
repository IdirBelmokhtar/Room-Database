package com.idirb.roomdatabase.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.idirb.roomdatabase.Activity.UpdateUserActivity;
import com.idirb.roomdatabase.R;
import com.idirb.roomdatabase.Room.User;
import com.idirb.roomdatabase.Room.UserViewModel;

import java.util.List;

public class RecyclerViewUserAdapter extends RecyclerView.Adapter<RecyclerViewUserAdapter.RecyclerViewHolder> {

    List<User> users;
    Context context;
    UserViewModel usersViewModel;

    public RecyclerViewUserAdapter(List<User> users, Context context, UserViewModel usersViewModel){ // We can use Constructor
        this.users = users;
        this.context = context;
        this.usersViewModel = usersViewModel;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewUserAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item_recyclerview, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewUserAdapter.RecyclerViewHolder holder, int position) {
        User user = users.get(position);

        holder.user_name.setText(user.getName());
        holder.user_surname.setText(user.getSurname());
        holder.user_age.setText(String.valueOf(user.getDdn()));
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usersViewModel.delete(user);
            }
        });
        holder.user_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateUserActivity.class);
                intent.putExtra("id", user.getId());
                intent.putExtra("name", user.getName());
                intent.putExtra("surname", user.getSurname());
                intent.putExtra("age", user.getDdn());
                ((Activity) context).startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        System.out.println("users size :" + users.size());
        return users.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView user_name, user_surname, user_age;
        private ImageButton imgDelete;
        private LinearLayout user_layout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);
            user_surname = itemView.findViewById(R.id.user_surname);
            user_age = itemView.findViewById(R.id.user_age);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            user_layout = itemView.findViewById(R.id.user_layout);

        }
    }

    public User getUserPosition(int pos){
        return users.get(pos);
    }
}
