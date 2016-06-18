package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

/**
 * Created by makni on 17/05/2016.
 */
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
<<<<<<< HEAD

    private Button mButtonN,mButtonq1,mButtonq2,mButtonq3,mButtonq4;

=======
    private RadioButton radioButton, radioButton2, radioButton3, radioButton4, radioGenderButton = null;
    private Button mButtonR;
    private ArrayList<String> propositions;
>>>>>>> 2e3797060e33b2875d4b106d57faf42947957ccd

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

<<<<<<< HEAD
        mButtonq1 = (Button) view.findViewById(R.id.button1);
        mButtonq1.setText(String.valueOf(getResources().getString(getResources().getIdentifier(mquiz.getReponseUn(), "string", getContext().getPackageName()))));
        mButtonq1.setOnClickListener(this);

        mButtonq2 = (Button) view.findViewById(R.id.button2);
        mButtonq2.setText(String.valueOf(getResources().getString(getResources().getIdentifier(mquiz.getReponseDeux(), "string", getContext().getPackageName()))));
        mButtonq2.setOnClickListener(this);

        mButtonq3 = (Button) view.findViewById(R.id.button3);
        mButtonq3.setText(String.valueOf(getResources().getString(getResources().getIdentifier(mquiz.getReponseTrois(), "string", getContext().getPackageName()))));
        mButtonq3.setOnClickListener(this);

        mButtonq4 = (Button) view.findViewById(R.id.button4);
        mButtonq4.setText(String.valueOf(getResources().getString(getResources().getIdentifier(mquiz.getReponseQuatre(), "string", getContext().getPackageName()))));
        mButtonq4.setOnClickListener(this);
=======
        propositions=generatePropositions(question.getProposition());
        radioButton = (RadioButton) view.findViewById(R.id.radioButton);
        radioButton.setText(propositions.get(0));

        radioButton2 = (RadioButton) view.findViewById(R.id.radioButton2);
        radioButton2.setText(propositions.get(1));

        radioButton3 = (RadioButton) view.findViewById(R.id.radioButton3);
        radioButton3.setText(propositions.get(2));

        radioButton4 = (RadioButton) view.findViewById(R.id.radioButton4);
        radioButton4.setText(propositions.get(3));
>>>>>>> 2e3797060e33b2875d4b106d57faf42947957ccd

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id
                .coordinatorLayout);

        mButtonN = (Button) view.findViewById(R.id.button);
        mButtonN.setOnClickListener(this);


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
        reponseCorrect = String.valueOf(getResources().getString(getResources().getIdentifier(mquiz.getReponseCorrect(), "string", getContext().getPackageName())));

        switch (v.getId()) {
            case R.id.button1:

<<<<<<< HEAD
                    reponse = mButtonq1.getText().toString();
=======
                    snackbar.show();
                } else {
                    reponse = radioGenderButton.getText().toString();
                    reponseCorrect = question.getCorrecte();
>>>>>>> 2e3797060e33b2875d4b106d57faf42947957ccd

                    if (reponseCorrect.equals(reponse)) {
                        snackbar = Snackbar
                                .make(coordinatorLayout, "Bravo", Snackbar.LENGTH_LONG);

                        snackbar.show();
                                   } else {
                        snackbar = Snackbar
                                .make(coordinatorLayout, "Reponse fausse", Snackbar.LENGTH_LONG);

                        snackbar.show();
                                     }

                break;
            case R.id.button2:

                reponse = mButtonq2.getText().toString();

                if (reponseCorrect.equals(reponse)) {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Bravo", Snackbar.LENGTH_LONG);

                    snackbar.show();
                } else {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Reponse fausse", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }

                break;
            case R.id.button3:

                reponse = mButtonq3.getText().toString();

                if (reponseCorrect.equals(reponse)) {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Bravo", Snackbar.LENGTH_LONG);

                    snackbar.show();
                } else {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Reponse fausse", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }

                break;
            case R.id.button4:

                reponse = mButtonq4.getText().toString();

                if (reponseCorrect.equals(reponse)) {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Bravo", Snackbar.LENGTH_LONG);

                    snackbar.show();
                } else {
                    snackbar = Snackbar
                            .make(coordinatorLayout, "Reponse fausse", Snackbar.LENGTH_LONG);

                    snackbar.show();
                }

                break;

            case R.id.button:
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                break;        }
    }
}