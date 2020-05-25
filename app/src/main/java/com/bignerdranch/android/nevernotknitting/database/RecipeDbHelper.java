package com.bignerdranch.android.nevernotknitting.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.nevernotknitting.Recipe;
import com.bignerdranch.android.nevernotknitting.database.RecipeDbSchema.RecipeTable;

public class RecipeDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "recipeDatabase.db";

    public RecipeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create recipes table
        db.execSQL("create table " + RecipeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                RecipeTable.Cols.UUID + ", " +
                RecipeTable.Cols.NAME + ", " +
                RecipeTable.Cols.TYPE + ", " +
                RecipeTable.Cols.LEVEL + ", " +
                RecipeTable.Cols.DESCRIPTION + ", " +
                RecipeTable.Cols.KNITTED +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RecipeTable.NAME);
        onCreate(db);
    }

}

