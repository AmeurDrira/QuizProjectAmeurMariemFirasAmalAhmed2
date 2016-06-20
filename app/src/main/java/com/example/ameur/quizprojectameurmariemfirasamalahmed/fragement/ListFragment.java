package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.PostStage;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.adapter.StageAdapter;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.wrapper.ListItemWrapper;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private StageAdapter mAdapter;
    private List<ListItemWrapper> btmlist = new ArrayList<>();
    private TextView mtxt;
    private static Bus eventBus;

    public static ListFragment newInstance(Bus Bus) {

        ListFragment Liste = new ListFragment();
        eventBus = Bus;
        return Liste;
    }


    @Override
    public void onClick(View v) {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);


        mtxt = (TextView) view.findViewById(R.id.txtView);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new StageAdapter(btmlist);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        Log.v("eee", "" + mLayoutManager);


        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                ListItemWrapper liste1 = btmlist.get(position);
                Toast.makeText(getContext(), liste1.getTitle() + "is selected", Toast.LENGTH_SHORT).show();

                // Log.v("eee",""+liste1.getId());
                eventBus.post(new PostStage(liste1.getId()));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        addB();


        return view;
    }

    public void addB() {
        ListItemWrapper liste1;
        for (int i = 1; i < 6; i++) {
            liste1 = new ListItemWrapper(i, "Stage " + i);
            btmlist.add(liste1);
        }

    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }




    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ListFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ListFragment.ClickListener clickListener) {
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