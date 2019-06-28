package com.radhika.headyapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Child_Category {


    @PrimaryKey(autoGenerate = true)
    private int ids;

    @ColumnInfo(name = "id")
    private int id;


    @ColumnInfo(name = "catId")
    public int categoty_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoty_id() {
        return categoty_id;
    }

    public void setCategoty_id(int categoty_id) {
        this.categoty_id = categoty_id;
    }
    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }



}
