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
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;

import java.util.Locale;


public class ConfigFragment extends DialogFragment {
    private static ConfigListener mConfigListener;
    private RadioButton radiolangugeFR, radiolangugeEN, radioGenderButton = null;
    private RadioGroup radioGroup;
    private String reponse;

    private int selectRadio = 0;
    private Switch switchSound;

    public ConfigFragment() {
    }

    public static ConfigFragment newInstance(ConfigListener listener) {
        ConfigFragment fragment = new ConfigFragment();
        mConfigListener = listener;
        return fragment;
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


        switchSound = (Switch) dialogView.findViewById(R.id.switchButtonSound);
        if (getActivity().getSharedPreferences("quiz", 0).getBoolean("status", false) == true) {
            switchSound.setChecked(true);

        } else {
            switchSound.setChecked(false);

        }


        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getActivity().getSharedPreferences("quiz", 0).edit().putBoolean("status", isChecked).commit();
            }
        });

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
