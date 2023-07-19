package com.idirb.roomdatabase.Activity;

import static com.idirb.roomdatabase.Activity.MainActivity.mUserViewModel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.idirb.roomdatabase.R;
import com.idirb.roomdatabase.Room.User;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText edit_name, edit_surname, edit_age;
    private Button btnSave;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        int age = getIntent().getIntExtra("age", 0);

        edit_name = findViewById(R.id.edit_name);
        edit_surname = findViewById(R.id.edit_surname);
        edit_age = findViewById(R.id.edit_age);

        edit_name.setText(name);
        edit_surname.setText(surname);
        edit_age.setText(String.valueOf(age));

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_ = edit_name.getText().toString();
                String surname_ = edit_surname.getText().toString();
                String age_ = edit_age.getText().toString();

                if (!name_.equals("") && !surname_.equals("") && !age_.equals("")){

                    User s = new User(id, name_, surname_, Integer.parseInt(age_));
                    System.out.println("s : " + s);
                    mUserViewModel.update(s);
                    finish();

                }else {
                    Toast.makeText(UpdateUserActivity.this, "texts empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}