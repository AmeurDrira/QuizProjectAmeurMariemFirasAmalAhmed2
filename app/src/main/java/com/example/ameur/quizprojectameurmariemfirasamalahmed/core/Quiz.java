package com.example.ameur.quizprojectameurmariemfirasamalahmed.core;

import java.io.Serializable;

/**
 * Created by ameur on 01/05/16.
 */
public class Quiz implements Serializable {
    private int niveau;
    private String question;
    private String reponseUn;
    private String reponseDeux;
    private String reponseTrois;
    private String reponseQuatre;
    private String reponseCorrect;

    public Quiz() {
    }

    public Quiz(int niveau,String reponseUn, String reponseDeux, String reponseTrois, String reponseQuatre, String reponseCorrect, String question) {
        this.niveau=niveau;
        this.reponseUn = reponseUn;
        this.reponseDeux = reponseDeux;
        this.reponseTrois = reponseTrois;
        this.reponseQuatre = reponseQuatre;
        this.reponseCorrect = reponseCorrect;
        this.question = question;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponseUn() {
        return reponseUn;
    }

    public void setReponseUn(String reponseUn) {
        this.reponseUn = reponseUn;
    }

    public String getReponseDeux() {
        return reponseDeux;
    }

    public void setReponseDeux(String reponseDeux) {
        this.reponseDeux = reponseDeux;
    }

    public String getReponseTrois() {
        return reponseTrois;
    }

    public void setReponseTrois(String reponseTrois) {
        this.reponseTrois = reponseTrois;
    }

    public String getReponseCorrect() {
        return reponseCorrect;
    }

    public void setReponseCorrect(String reponseCorrect) {
        this.reponseCorrect = reponseCorrect;
    }

    public String getReponseQuatre() {
        return reponseQuatre;
    }

    public void setReponseQuatre(String reponseQuatre) {
        this.reponseQuatre = reponseQuatre;
    }
}

