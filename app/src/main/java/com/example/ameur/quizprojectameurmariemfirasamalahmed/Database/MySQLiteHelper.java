package com.example.ameur.quizprojectameurmariemfirasamalahmed.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by !l-PazZ0 on 07/05/2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quiz.db";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        QuestionsHelper.onCreate(db);
        PropositionsHelper.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        QuestionsHelper.onUpgrade(db, oldVersion, newVersion);
        PropositionsHelper.onUpgrade(db, oldVersion, newVersion);
    }
}
