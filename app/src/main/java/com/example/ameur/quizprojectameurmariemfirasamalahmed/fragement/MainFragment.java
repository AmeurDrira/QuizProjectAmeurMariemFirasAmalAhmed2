package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;


public class MainFragment extends Fragment implements View.OnClickListener {

    private Button mResume;
    private Button mStart;
    private Button mConfig;
    private Button mShareFb;
    private Button mShareG;

    private static MainMenuListener mMainMenuListener;

    public static MainFragment newInstance(MainMenuListener listener) {
        MainFragment fragment = new MainFragment();
        mMainMenuListener = listener;
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
                mMainMenuListener.sharefb();
                break;
            case R.id.Config:
                mMainMenuListener.launchConfig();
                break;
            case R.id.Start:
                //commencer le jeu :)
                break;

        }

    }

    public interface MainMenuListener {
        public void sharefb();


        public void launchConfig();

    }

}
