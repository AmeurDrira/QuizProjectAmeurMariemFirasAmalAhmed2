package com.example.ameur.quizprojectameurmariemfirasamalahmed.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ConfigFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.MainFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MainFragment.MainMenuListener,ConfigFragment.ConfigListener {


    private CallbackManager callbackManager;
    private ShareDialog shareDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facebookSDKInitialize();
        shareDialog = new ShareDialog(this);

        launchMenu();
    }
    //cette fonction "launchMenu()" pour lancer le fragement Menu la premiere interface de notre jeu Quizz :)


    private void launchMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, MainFragment.newInstance(this)).commit();
    }
//cette fonction "facebookSDKInitialize()" pour instaliser le fenetre de partage  sur facebook :)

    private void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

//cette fonction "sharefb()" pour le partage sur facebook :)

    public void sharefb() {

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("QUIZZ SHARE")
                    .setImageUrl(Uri.parse("http://previews.123rf.com/images/vectorikart/vectorikart1411/vectorikart141100002/33142849-Conceptual-flat-icon-of-brain-and-idea-in-the-form-of-light-bulb-with-soft-shadow-on-a-blue-backgrou-Stock-Vector.jpg"))
                    .setContentDescription(
                            "simple share")
                    .build();

            shareDialog.show(linkContent);
        }
    }
//cette fonction "launchConfig()" pour lancer le dialog fragement de changement de langue :)

    public void launchConfig() {
        ConfigFragment configFragment = ConfigFragment.newInstance(this);
        configFragment.setCancelable(false);
        configFragment.show(getSupportFragmentManager(), "");
    }

    //cette fonction "changeLanguageSettings(String lang)" lang=en|fr pour le changement du langue du fr au en et vis vers ca :)
    public void changeLanguageSettings(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        this.getResources().updateConfiguration(config, this.getResources().getDisplayMetrics());

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}

