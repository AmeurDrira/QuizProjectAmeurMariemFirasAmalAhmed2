package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.adapter.CustomAdapter;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Quiz;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.wrapper.ListItemWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by makni on 06/05/2016.
 */
public class ListeQuestionFragment extends Fragment implements View.OnClickListener {
    private static QuestionListner questionListener;
    private static ArrayList<Quiz> mquizs;
    private RecyclerView recyclerView;
    private CustomAdapter mAdapter;
    private List<ListItemWrapper> btmliste = new ArrayList<>();

    public static ListeQuestionFragment newInstance(ArrayList<Quiz> mquiz, QuestionListner qli) {

        ListeQuestionFragment Liste = new ListeQuestionFragment();
        questionListener = qli;
        mquizs = mquiz;
 /*       for (Quiz q : mquizs) {

            Log.v("iitff", q.getQuestion());
        }
*/


        return Liste;
    }

    @Override
    public void onClick(View v) {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listequestionfragmentlayout, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new CustomAdapter(btmliste);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);
        Log.v("eee", "" + mLayoutManager);


        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {

            public void onClick(View view, int position) {
                ListItemWrapper liste = btmliste.get(position);
                Toast.makeText(getContext(), liste.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                if (questionListener != null) {
                    questionListener.update(mquizs.get(liste.getId()));
                }
            }

            public void onLongClick(View view, int position) {

            }
        }));

        addBtm();


        return view;
    }

    public void addBtm() {
        ListItemWrapper liste;
        for (int i = 0; i < 9; i++) {
            liste = new ListItemWrapper(i, "q" + (i + 1));
            btmliste.add(liste);
        }

    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }


    public interface QuestionListner {
        public void update(Quiz mQuiz);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListeQuestionFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListeQuestionFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
