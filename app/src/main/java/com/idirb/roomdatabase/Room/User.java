package com.idirb.roomdatabase.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "userTable")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String surname;
    @ColumnInfo(name = "birth_day") // pour modifier le nom de la column.
    int ddn;

    public User(String name, String surname, int ddn) {
        this.name = name;
        this.surname = surname;
        this.ddn = ddn;
    }

    // We add this constructor for updating user because the id is autoGenerate.
    @Ignore
    public User(int id, String name, String surname, int ddn) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.ddn = ddn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getDdn() {
        return ddn;
    }

    public void setDdn(int ddn) {
        this.ddn = ddn;
    }
}
