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
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Quiz;


public class QuestionFragment extends Fragment implements View.OnClickListener {
    private static Quiz mquiz;
    private RadioGroup radioGroup;
    private CoordinatorLayout coordinatorLayout;

    private Button mButtonN,mButtonq1,mButtonq2,mButtonq3,mButtonq4;


    public QuestionFragment() {

    }

    public static QuestionFragment newInstance(Quiz quiz) {
        QuestionFragment questionFragment = new QuestionFragment();
        mquiz = quiz;
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
        int res = getResources().getIdentifier(mquiz.getQuestion(), "string", getContext().getPackageName());
        mQuestion.setText(String.valueOf(getResources().getString(res)));


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

        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id
                .coordinatorLayout);

        mButtonN = (Button) view.findViewById(R.id.button);
        mButtonN.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        int selectRadio = 0;
        String reponse = "";
        reponseCorrect = String.valueOf(getResources().getString(getResources().getIdentifier(mquiz.getReponseCorrect(), "string", getContext().getPackageName())));

        switch (v.getId()) {
            case R.id.button1:

                    reponse = mButtonq1.getText().toString();

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