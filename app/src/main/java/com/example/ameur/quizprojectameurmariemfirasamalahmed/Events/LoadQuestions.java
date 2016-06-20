package com.example.ameur.quizprojectameurmariemfirasamalahmed.Events;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Question;

/**
 * Created by !L-PAzZ0 on 18/06/2016.
 */
public class LoadQuestions {
    private Question question;
    private int code;
    public LoadQuestions(Question question,int code)

    {
        this.question=question;
        this.code=code;
    }
    public int getCode (){return  code;}
    public Question getQuestion() {
        return question;
    }
}
