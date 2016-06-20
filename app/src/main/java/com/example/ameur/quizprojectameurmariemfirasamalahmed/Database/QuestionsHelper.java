package com.example.ameur.quizprojectameurmariemfirasamalahmed.Database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by !l-PazZ0 on 07/05/2016.
 */
public class QuestionsHelper {
    public static final String TABLE_NAME = "Question";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FRANCAIS = "Francais";
    public static final String COLUMN_ANGLAIS = "Anglais";
    public static final String COLUMN_CORRECTE = "Correcte";
    public static final String COLUMN_STAGE = "stage";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_FRANCAIS + " text not null, " + COLUMN_ANGLAIS + " text not null, " + COLUMN_CORRECTE + " integer not null, " + COLUMN_STAGE + " integer not null);";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
