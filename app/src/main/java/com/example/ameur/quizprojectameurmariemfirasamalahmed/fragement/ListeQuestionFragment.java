package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

/**
 * Created by makni on 09/05/2016.
 */

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;




import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Quiz;



/**
 * Created by makni on 06/05/2016.
 */
public class ListeQuestionFragment extends DialogFragment implements View.OnClickListener {
    private static QuestionListner ql;
    private Button mButton1;
    private  Button mButton2;
    private  Button mButton3;
    private  Button mButton4;
    private  Button mButton5;
    private  Button mButton6;
    private  Button mButton7;
    private  Button mButton8;
    private  Button mButton9;

    private static ArrayList<Quiz> mquizs ;
    private static Quiz q;


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                ql.update(1);
                break;
            case R.id.bt2:
                ql.update(2);
                break;
            case R.id.bt3:
                ql.update(3);
                break;
            case R.id.bt4:
                ql.update(4);
                break;
            case R.id.bt5:
                ql.update(5);
                break;
            case R.id.bt6:
                ql.update(6);
                break;
            case R.id.bt7:
                ql.update(7);
                break;
            case R.id.bt8:
                ql.update(8);
                break;
            case R.id.bt9:
                ql.update(9);
                break;

        }
    }



    public static ListeQuestionFragment newInstance(ArrayList<Quiz> mquizs, String x,QuestionListner qli){

        ListeQuestionFragment Liste = new ListeQuestionFragment();
        ql=qli;
        x=x;
        mquizs=mquizs;
        Log.v("iit1",mquizs.get(1).getQuestion() );

        return Liste;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listequestionfragmentlayout, container, false);


        mButton1 = (Button) view.findViewById(R.id.bt1);
        mButton1.setOnClickListener(this);
        mButton2 = (Button) view.findViewById(R.id.bt2);
        mButton2.setOnClickListener(this);
        mButton3 = (Button) view.findViewById(R.id.bt3);
        mButton3.setOnClickListener(this);
        mButton4 = (Button) view.findViewById(R.id.bt4);
        mButton4.setOnClickListener(this);
        mButton5 = (Button) view.findViewById(R.id.bt5);
        mButton5.setOnClickListener(this);
        mButton6 = (Button) view.findViewById(R.id.bt6);
        mButton6.setOnClickListener(this);
        mButton7 = (Button) view.findViewById(R.id.bt7);
        mButton7.setOnClickListener(this);
        mButton8 = (Button) view.findViewById(R.id.bt8);
        mButton8.setOnClickListener(this);
        mButton9 = (Button) view.findViewById(R.id.bt9);
        mButton9.setOnClickListener(this);



        return view;
    }

    public interface QuestionListner
    {
        public void update(int r);


    }


}
