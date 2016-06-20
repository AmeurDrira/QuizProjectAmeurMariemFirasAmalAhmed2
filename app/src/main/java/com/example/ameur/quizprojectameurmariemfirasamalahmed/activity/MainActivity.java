package com.example.ameur.quizprojectameurmariemfirasamalahmed.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.MySQLiteHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.PropositionsHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Database.QuestionsHelper;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.EventProvider.EventBus;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.LanguageSettings;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.Launch;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.LoadQuestions;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.PostStage;
import com.example.ameur.quizprojectameurmariemfirasamalahmed.Events.ScoreUpdate;
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
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.plus.PlusShare;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{
    private static String Correcte = "";
    public static int score=0;
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;
    public static MediaPlayer mPlayer;
    private Bus mbus= EventBus.getInstance();
    public final static String SCORE_USER="quiz.tn.SCORE";
    public final static String MEILEURE="quiz.projet.tn.FINAL";
    public ArrayList<Question> questions;
    private int nombreQ=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score=RetrieveScore();
        Log.v("score",String.valueOf(score));
        facebookSDKInitialize();
        shareDialog = new ShareDialog(this);
        launchMenu();
       createDB();
    }

    //lors du lancement de l'application je lance une musique
    @Override
    protected void onStart() {
        super.onStart();
        mPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound_track);
        try {
            mPlayer.setLooping(true);
            if (getSharedPreferences("quiz", 0).getBoolean("status", false) == true)
                mPlayer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences shared = getSharedPreferences("UserData",Context.MODE_PRIVATE);
        SharedPreferences.Editor write = shared.edit();
        write.putInt(SCORE_USER,score);
        int sf=shared.getInt(MEILEURE,0);
        if (sf<score)
            write.putInt(MEILEURE,score);
        write.commit();

    }
    @Override
    protected void onResume() {
        super.onResume();
        mbus.register(this);
    }

    @Override
    protected void onPause() {
        mbus.unregister(this);
        super.onPause();
    }
    //lors de Sortie  de l'application j'arret la  musique
    public  int RetrieveScore()
    {
        SharedPreferences shared =getSharedPreferences("UserData", Context.MODE_PRIVATE);
        int score=shared.getInt(SCORE_USER,0);
        return score;
    }

    //cette fonction "launchMenu()" pour lancer le fragement Menu la premiere interface de notre jeu Quizz :)
    private void launchMenu() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, MainFragment.newInstance(mbus)).commit();
    }

    //cette fonction "facebookSDKInitialize()" pour instaliser le fenetre de partage  sur facebook :)
    private void facebookSDKInitialize() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    //cette fonction "sharefb()" pour le partage sur facebook :)
    public void sharefb() {

        String text = "Look at my awesome picture";
        //Uri imageUri = Uri.parse("http://previews.123rf.com/images/vectorikart/vectorikart1411/vectorikart141100002/33142849-Conceptual-flat-icon-of-brain-and-idea-in-the-form-of-light-bulb-with-soft-shadow-on-a-blue-backgrou-Stock-Vector.jpg");
        Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/health");

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        for (final ResolveInfo app : activityList) {
            if ((app.activityInfo.name).contains("facebook")) {
                final ActivityInfo activity = app.activityInfo;
                final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
                shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                shareIntent.setComponent(name);
                startActivity(shareIntent);
                break;
            }
        }

    }

    //cette fonction "launchConfig()" pour lancer le dialog fragement de changement de langue :)
    public void launchConfig() {
        ConfigFragment configFragment = ConfigFragment.newInstance(mbus);
        configFragment.setCancelable(false);
        configFragment.show(getSupportFragmentManager(), "");
    }

    //cette fonction "shareG()" pour le partage sur GooglePlus :)
    public void shareG() {
        Uri selectedImage = Uri.parse("android.resource://" + getPackageName() + "/drawable/health");

        Intent shareIntent = new PlusShare.Builder(this)
                .setType("text/plain")
                .setText("QUIZZ SHARE")
                .setContentUrl(Uri.parse("https://developers.google.com/+/"))
                .setStream(selectedImage)
                .getIntent();

        startActivityForResult(shareIntent, 0);


    }

    public void launchListeStage() {
           getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListFragment.newInstance(mbus)).addToBackStack("ListFragment").commit();

    }

    //cette fonction "changeLanguageSettings(String lang)" lang=en|fr pour le changement du langue du fr au en et vis vers ca :)
    @Subscribe public void changeLanguageSettings(LanguageSettings ls) {

        Locale locale = new Locale(ls.getLanguage());
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

    @Subscribe
    public void updateQuestion(LoadQuestions lq) {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, QuestionFragment.newInstance(lq.getQuestion(),mbus,lq.getCode())).addToBackStack("QCM").commit();

    }
    @Subscribe public void UpdateScore (ScoreUpdate su)
    {
        int niveau;
        nombreQ++;
        if (nombreQ<10)
        {
            score += su.getScore();
            if (su.getCode() > 8) {
                niveau = 0;
            }
            else {
                niveau = su.getCode() + 1;
            }
            Question q = questions.get(niveau);
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            Fragment fragment = QuestionFragment.newInstance(q, mbus, niveau);
            trans.replace(R.id.main_layout, fragment, "QCM").commit();
        }
        else
        {
            Log.v("score",String.valueOf(score));
            getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListFragment.newInstance(mbus)).addToBackStack("ListFragment").commit();
        }
    }

    @Subscribe
    public void updateStage(PostStage ps)
    {
        nombreQ=1;
        String langue = Locale.getDefault().getLanguage();
         questions =QuestionsLoader(ps.getStage(),langue);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, ListeQuestionFragment.newInstance(questions, mbus)).commit();

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
    }

    public void createDB()
    {
        MySQLiteHelper myDbHelper = new MySQLiteHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDataBase();

        }catch(java.sql.SQLException sqle){

            try {
                throw sqle;
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }

        }
    }

    //Fragment launcher
    @Subscribe
    public void fragmentLauncher(Launch launch)
    {
        Log.v("press",launch.getBesoin());
        switch (launch.getBesoin())
        {
            case "fcbk":
                sharefb();
                break;
            case "config":
                launchConfig();
                break;
            case "google":
                shareG();
                break;
            case "listeStage":
                launchListeStage();
                break;
            default:
                break;
        }

    }


}