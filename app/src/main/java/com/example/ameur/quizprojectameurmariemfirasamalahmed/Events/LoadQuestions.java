package com.example.ameur.quizprojectameurmariemfirasamalahmed.Events;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Question;

/**
 * Created by !L-PAzZ0 on 18/06/2016.
 */
public class LoadQuestions {
    private Question question;
    public LoadQuestions(Question question)
    {
        this.question=question;
    }

    public Question getQuestion() {
        return question;
    }
}