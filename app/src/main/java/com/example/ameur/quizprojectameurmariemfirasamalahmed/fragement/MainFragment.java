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
                mMainMenuListener.sharefb();//c'est l'appel de la methode pour partager sur fb :)
                break;
            case R.id.Config:
                mMainMenuListener.launchConfig();//c'est l'appel de la methode pour changer la langue et active/desactiver Sound:)
                break;
            case R.id.Start:
<<<<<<< HEAD
                mMainMenuListener.launchList();
=======
                mMainMenuListener.launchListeStage();
>>>>>>> master
                break;

        }

    }
//C'est notre listener
    public interface MainMenuListener {
        public void sharefb();
<<<<<<< HEAD

    public void launchList();
=======
>>>>>>> master
        public void launchConfig();
        public void launchListeStage();

    }

}
