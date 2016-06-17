package com.example.ameur.quizprojectameurmariemfirasamalahmed.wrapper;

import java.io.Serializable;

/**
 * Created by makni on 17/05/2016.
 */
public class ListItemWrapper implements Serializable {


    private int mId;
    private String mTitle;
    private int score;


    public ListItemWrapper() {

    }


    public ListItemWrapper(int id, String title,int score) {

        mId=id;
        mTitle = title;
        this.score=score;



    }
    public ListItemWrapper(int id, String title) {

        mId=id;
        mTitle = title;




    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

