package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.Launch;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.squareup.otto.Bus;


public class MainFragment extends Fragment implements View.OnClickListener {

    private Button mResume;
    private Button mStart;
    private Button mConfig;
    private Button mShareFb;
    private Button mShareG;
    private static Bus eventBus;

    public static MainFragment newInstance(Bus Bus) {
        MainFragment fragment = new MainFragment();
        eventBus = Bus;
        return fragment;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mShareFb = (Button) view.findViewById(R.id.shareFb);
        mShareFb.setOnClickListener(this);
        mShareG = (Button) view.findViewById(R.id.shareG);
        mShareG.setOnClickListener(this);
        mResume = (Button) view.findViewById(R.id.Resume);
        mResume.setOnClickListener(this);
        mConfig = (Button) view.findViewById(R.id.Config);
        mConfig.setOnClickListener(this);
        mStart = (Button) view.findViewById(R.id.Start);
        mStart.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shareFb:
                eventBus.post(new Launch("fcbk"));
                break;
            case R.id.Config:
                eventBus.post(new Launch("config"));
                break;
            case R.id.shareG:
                eventBus.post(new Launch("google"));
                break;
            case R.id.Start:
                eventBus.post(new Launch("listeStage"));
                break;


        }

    }



}
