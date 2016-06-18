package com.example.ameur.quizprojectameurmariemfirasamalahmed.Database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by !l-PazZ0 on 07/05/2016.
 */
public class PropositionsHelper {
    public static final String TABLE_NAME = "Proposition";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION="id_question";
    public static final String COLUMN_FRANCAIS = "Francais";
    public static final String COLUMN_ANGLAIS="Anglais";
    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_QUESTION +" integer not null, " + COLUMN_FRANCAIS + " text not null, " + COLUMN_ANGLAIS + " text not null);";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
