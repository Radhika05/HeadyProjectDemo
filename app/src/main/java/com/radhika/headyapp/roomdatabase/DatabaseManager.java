package com.radhika.headyapp.roomdatabase;

import android.content.Context;

import androidx.room.Room;

public class DatabaseManager {
    private static final String DATABASE_NAME = "heady_db";
    private static DatabaseManager mInstance;
    private ProductDatabase productDatabase;


    private DatabaseManager(Context mCtx) {
        productDatabase = Room.databaseBuilder(mCtx, ProductDatabase.class, DATABASE_NAME).build();
    }

    public static synchronized DatabaseManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new DatabaseManager(mCtx);
        }
        return mInstance;
    }

    public ProductDatabase getAppDatabase() {
        return productDatabase;
    }

}
