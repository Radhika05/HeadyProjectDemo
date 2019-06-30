package com.radhika.headyapp.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
@ForeignKey(entity = Categories.class, parentColumns = "id", childColumns = "id")
public class Child_Category {

    @PrimaryKey
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
}
