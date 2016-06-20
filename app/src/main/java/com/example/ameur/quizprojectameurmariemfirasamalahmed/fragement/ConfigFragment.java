package com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.activity.MainActivity;


import java.util.Locale;


public class ConfigFragment extends DialogFragment {
    private RadioButton radiolangugeFR, radiolangugeEN, radioGenderButton = null;
    private RadioGroup radioGroup;
    private String reponse;
    private Switch mSwitchButtonSound;

    private int selectRadio = 0;

    private static ConfigListener mConfigListener;

    public static ConfigFragment newInstance(ConfigListener listener) {
        ConfigFragment fragment = new ConfigFragment();
        mConfigListener = listener;
        return fragment;
    }


    public ConfigFragment() {
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {


        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View dialogView = layoutInflater.inflate(R.layout.fragment_config, null);


        radioGroup = (RadioGroup) dialogView.findViewById(R.id.radioGroup);
        String languesDef = Locale.getDefault().getLanguage();
        if (languesDef.equals("en"))
            radioGroup.check(R.id.radiolangugeEN);
        if (languesDef.equals("fr"))
            radioGroup.check(R.id.radiolangugeFr);


        radiolangugeFR = (RadioButton) dialogView.findViewById(R.id.radiolangugeFr);
        radiolangugeFR.setText("Français");

        radiolangugeEN = (RadioButton) dialogView.findViewById(R.id.radiolangugeEN);
        radiolangugeEN.setText("English");


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {

                        selectRadio = radioGroup.getCheckedRadioButtonId();
                        Log.v("selecRadio", String.valueOf(selectRadio));
                        radioGenderButton = (RadioButton) dialogView.findViewById(selectRadio);
                        reponse = radioGenderButton.getText().toString();
                        if (!reponse.isEmpty() && reponse.equals("English")) {
                            mConfigListener.changeLanguageSettings("en");
                        } else if (!reponse.isEmpty() && reponse.equals("Français")) {
                            mConfigListener.changeLanguageSettings("fr");
                        }

                    }
                }
        ).setNegativeButton(android.R.string.cancel, null).setTitle(R.string.titleconfig).setView(dialogView);

        return builder.create();

    }

    public interface ConfigListener {
        public void changeLanguageSettings(String lang);

    }


}
