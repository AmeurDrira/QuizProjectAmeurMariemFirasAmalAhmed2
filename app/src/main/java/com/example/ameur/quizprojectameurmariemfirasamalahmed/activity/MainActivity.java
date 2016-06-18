package com.example.ameur.quizprojectameurmariemfirasamalahmed.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.MySQLiteHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.PropositionsHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.QuestionsHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Provider.PropositionsContentProvider;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Provider.QuestionsContentProvider;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.R;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.core.Question;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ConfigFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ListFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.ListeQuestionFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.MainFragment;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.fragement.QuestionFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MainFragment.MainMenuListener, ConfigFragment.ConfigListener, ListeQuestionFragment.QuestionListner, ListFragment.ListedQuestionLiner {
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    private static String Correcte = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facebookSDKInitialize();
        shareDialog = new ShareDialog(this);
        launchMenu();
        createDB();
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

    public void launchListeStage() {
        //  getSupportFragmentManager().beginTransaction().replace(R.id.fragment_list, ListFragment.newInstance()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListFragment.newInstance(this)).commit();
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

    // populate Propositions Array from database depends of question id
    private ArrayList<String> generatePropositions(int idQuestion, int correctOne, String langue) {
        int idLangue;
        ArrayList<String> propositions = new ArrayList<>();
        String[] projection = {PropositionsHelper.COLUMN_ID, PropositionsHelper.COLUMN_QUESTION, PropositionsHelper.COLUMN_FRANCAIS, PropositionsHelper.COLUMN_ANGLAIS};
        Uri uri = PropositionsContentProvider.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, projection, PropositionsHelper.COLUMN_QUESTION + "=" + idQuestion, null, null);
        if (langue.equals("fr")) {
            idLangue = 2;
        } else {
            idLangue = 3;
        }
        while (cursor.moveToNext()) {
            propositions.add(cursor.getString(idLangue));
            if (cursor.getInt(0) == correctOne) {
                Correcte = cursor.getString(idLangue);
            }
        }
        return propositions;
    }

    // populate Question Array from database depends on level
    private ArrayList<Question> QuestionsLoader(int stage, String langue) {
        int idLangue;
        Question question;
        ArrayList<Question> qestions = new ArrayList<Question>();
        ArrayList<String> propositions;
        String[] projection = {QuestionsHelper.COLUMN_ID, QuestionsHelper.COLUMN_FRANCAIS, QuestionsHelper.COLUMN_ANGLAIS, QuestionsHelper.COLUMN_CORRECTE, QuestionsHelper.COLUMN_STAGE};
        Uri uri = QuestionsContentProvider.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, projection, QuestionsHelper.COLUMN_STAGE + "=" + stage, null, null);
        try {
            if (langue.equals("fr")) {
                idLangue = 1;
            } else {
                idLangue = 2;
            }
            while (cursor.moveToNext()) {
                question = new Question();
                question.setQuestion(cursor.getString(idLangue));
                question.setProposition(generatePropositions(cursor.getInt(0), cursor.getInt(3), langue));
                question.setCorrecte(Correcte);
                qestions.add(question);
            }
        } finally {
            cursor.close();
        }
        return qestions;
    }

    @Override
    public void update(Question question) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, QuestionFragment.newInstance(question)).commit();
    }

    @Override
    public void update(int NumStage) {
        String langue = Locale.getDefault().getLanguage();
        ArrayList<Question> questions = QuestionsLoader(NumStage, langue);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListeQuestionFragment.newInstance(questions, this)).commit();
    }

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
        moveTaskToBack(true);
    }

    public void createDB() {
        MySQLiteHelper myDbHelper = new MySQLiteHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            try {
                throw sqle;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    // Fin Firas
}