package com.example.ameur.quizprojectameurmariemfirasamalahmed.Events;

/**
 * Created by !L-PAzZ0 on 20/06/2016.
 */
public class ScoreUpdate {
    private int score;
    private int code;
    public ScoreUpdate(int score,int code)
    {
        this.score=score;
        this.code=code;

    }
    public int getCode()
    {
        return code;
    }
    public int getScore() {
        return score;
    }
 /*   public void SetScore(int score) { this.score=score; }*/
}
