package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;



import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Question;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Quiz;
import com.facebook.internal.CollectionMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class QuestionFragment extends Fragment implements View.OnClickListener {
    private static Question question;
    private RadioGroup radioGroup;
    private CoordinatorLayout coordinatorLayout;
    private RadioButton radioButton, radioButton2, radioButton3, radioButton4, radioGenderButton = null;
    private Button mButtonR;
    private ArrayList<String> propositions;

    public QuestionFragment() {

    }

    public static QuestionFragment newInstance(Question q) {
        QuestionFragment questionFragment = new QuestionFragment();
        question = q;
        return questionFragment;
    }

    private TextView mQuestion;
    Snackbar snackbar;
    int i = 0;
    String reponseCorrect;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        mQuestion = (TextView) view.findViewById(R.id.mQuestion);
        mQuestion.setText(question.getQuestion());

        propositions=generatePropositions(question.getProposition());
        radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        radioButton.setText(propositions.get(0));

        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        radioButton2.setText(propositions.get(1));

        radioButton3 = (RadioButton) view.findViewById(R.id.radioButton3);
        radioButton3.setText(propositions.get(2));

        radioButton4 = (RadioButton) view.findViewById(R.id.radioButton4);
        radioButton4.setText(propositions.get(3));

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id
                .coordinatorLayout);
        mButtonR = (Button) view.findViewById(R.id.button);
        mButtonR.setOnClickListener(this);

        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        return view;
    }
    public ArrayList<String> generatePropositions(ArrayList<String>propositions)
    {
        ArrayList<String> propos=new ArrayList<>();
        propos.add(question.getCorrecte());
        for (String p:propositions)
        {
            if ((p!=question.getCorrecte()) && (propos.size()<=4))
            {
                propos.add(p);
            }
        }
        Collections.shuffle(propos);
        return propos;
    }
    @Override
    public void onClick(View v) {
        int selectRadio = 0;
        String reponse = "";

        switch (v.getId()) {
            case R.id.button:
                selectRadio = radioGroup.getCheckedRadioButtonId();
                radioGenderButton = (RadioButton) getView().findViewById(selectRadio);

                if (null == radioGenderButton) {

                    snackbar = Snackbar
                            .make(coordinatorLayout, "Aucun proposition est selectionner", Snackbar.LENGTH_LONG);

                    snackbar.show();
                } else {
                    reponse = radioGenderButton.getText().toString();
                    reponseCorrect = question.getCorrecte();

                    if (reponseCorrect.equals(reponse)) {
                        snackbar = Snackbar
                                .make(coordinatorLayout, "Bravo", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        radioGroup.clearCheck();


                    } else {
                        snackbar = Snackbar
                                .make(coordinatorLayout, "Reponse fausse", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        radioGroup.clearCheck();
                    }

                }
                break;
        }
    }
}