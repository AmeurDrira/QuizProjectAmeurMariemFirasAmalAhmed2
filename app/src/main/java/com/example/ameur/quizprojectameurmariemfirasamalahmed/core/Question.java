package com.example.ameur.quizprojectameurmariemfirasamalahmed.core;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by !l-PazZ0 on 28/04/2016.
 */
public class Question implements Serializable {

    @SerializedName("Question")
    private String question;

    @SerializedName("Proposition")
    private ArrayList<String> proposition;

    @SerializedName("VraiProp")
    private String correcte;


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<String> getProposition() {
        return proposition;
    }

    public void setProposition(ArrayList<String> proposition) {
        this.proposition = proposition;
    }

    public String getCorrecte() {
        return correcte;
    }

    public void setCorrecte(String correcte) {
        this.correcte = correcte;
    }


}
