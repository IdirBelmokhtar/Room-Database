package com.idirb.roomdatabase.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;
import com.idirb.roomdatabase.R;

public class DialogAddUser extends Dialog {

    private TextInputEditText name, surname, age;
    private Button btn;

    public DialogAddUser(Activity activity) {
        super(activity, androidx.appcompat.R.style.ThemeOverlay_AppCompat_Dialog);
        setContentView(R.layout.add_user_dialog);

        this.name = findViewById(R.id.edittextName);
        this.surname = findViewById(R.id.edittextSurname);
        this.age = findViewById(R.id.edittextAge);
        this.btn = findViewById(R.id.add_new_user);

    }

    public TextInputEditText getName() {
        return name;
    }

    public TextInputEditText getSurname() {
        return surname;
    }

    public TextInputEditText getAge() {
        return age;
    }

    public Button getBtn() {
        return btn;
    }

    public void build(){
        show();
    }
}
