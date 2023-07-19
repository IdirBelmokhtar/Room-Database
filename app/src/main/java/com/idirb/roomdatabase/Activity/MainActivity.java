package com.idirb.roomdatabase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.idirb.roomdatabase.Dialog.DialogAddUser;
import com.idirb.roomdatabase.R;
import com.idirb.roomdatabase.Adapter.RecyclerViewUserAdapter;
import com.idirb.roomdatabase.Room.User;
import com.idirb.roomdatabase.Room.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static UserViewModel mUserViewModel;
    private RecyclerView mRecyclerView;
    private RecyclerViewUserAdapter recyclerViewUserAdapter;
    private FloatingActionButton floating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);

        // View Model
        mUserViewModel = new ViewModelProvider(MainActivity.this).get(UserViewModel.class);
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // Update UI (User Interface)
                Toast.makeText(MainActivity.this, "onChanged", Toast.LENGTH_LONG).show();

                // RecyclerView & RecyclerViewUserAdapter
                recyclerViewUserAdapter = new RecyclerViewUserAdapter(users, MainActivity.this, mUserViewModel);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                        RecyclerView.VERTICAL, false));
                mRecyclerView.setHasFixedSize(true); // make all items of recyclerView with same size.
                mRecyclerView.setAdapter(recyclerViewUserAdapter);
            }
        });

        // Floating btn
        floating = findViewById(R.id.floating);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogAddUser dialogAddUser = new DialogAddUser((Activity) MainActivity.this);
                dialogAddUser.getBtn().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = dialogAddUser.getName().getText().toString();
                        String surname = dialogAddUser.getSurname().getText().toString();
                        int age = Integer.parseInt(dialogAddUser.getAge().getText().toString());

                        mUserViewModel.insert(new User(name, surname, age));

                        dialogAddUser.dismiss();
                    }
                });

                dialogAddUser.build();
                dialogAddUser.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialogAddUser.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Delete item
                int pos = viewHolder.getAdapterPosition();
                mUserViewModel.delete(recyclerViewUserAdapter.getUserPosition(pos));
            }
        }).attachToRecyclerView(mRecyclerView);
    }
}