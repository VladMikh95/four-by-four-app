package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private Button buttonResume;

    private AdView adView; //переменная для баннера
    private static final String APPLICATION_ID = "ca-app-pub-8930311370509397~5824143913"; //идентификатор приложения

    private SharedPreferences sharedPreferences;

    public static final String SHARED_PREFERENCES_NAME = "shared_preferences";
    public static final String FIELD_STATE_PREFERENCE = "field_state";
    public static final String TIME_MODE = "time_mode";
    public static final String NO_TIME_LIMIT_VICTORY = "noTimeLimitVictory";
    public static final String NO_TIME_LIMIT_GAME = "noTimeLimitGame";
    public static final String ONE_MIN_VICTORY = "oneMinVictory";
    public static final String ONE_MIN_GAME = "oneMinGame";
    public static final String THREE_MIN_VICTORY = "threeMinVictory";
    public static final String THREE_MIN_GAME = "threeMinGame";
    public static final String FIVE_MIN_VICTORY = "fiveMinVictory";
    public static final String FIVE_MIN_GAME = "fiveMinGame";

    public static final String NO_TIME_LIMIT_VICTORY_PARTITION = "noTimeLimitVictoryPartition";
    public static final String NO_TIME_LIMIT_GAME_PARTITION = "noTimeLimitGamePartition";
    public static final String ONE_MIN_VICTORY_PARTITION = "oneMinVictoryPartition";
    public static final String ONE_MIN_GAME_PARTITION = "oneMinGamePartition";
    public static final String THREE_MIN_VICTORY_PARTITION = "threeMinVictoryPartition";
    public static final String THREE_MIN_GAME_PARTITION = "threeMinGamePartition";
    public static final String FIVE_MIN_VICTORY_PARTITION = "fiveMinVictoryPartition";
    public static final String FIVE_MIN_GAME_PARTITION = "fiveMinGamePartition";

    public static final String TURNING_SOUND = "turningSound";
    public static final int SOUND_ON = 0;
    public static final int SOUND_OFF = 1;

    public static final String GAME_MODE = "game_mode";

    public static final String PREFERENCE_EMPTY = "empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Подключение рекламного баннера - начало
        MobileAds.initialize(this, APPLICATION_ID);
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        //Подключение рекламного баннера - конец

        //Делаем панель иструментов в качестве панели приложения
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        buttonResume = findViewById(R.id.buttonResume);
    }

    //Exiting the application
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("abc", "start");
        if (sharedPreferences.getString(FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY).length() == 20) {
            buttonResume.setClickable(true);
            buttonResume.setBackgroundColor(getResources().getColor(R.color.MediumSeaGreen));
        } else {
            buttonResume.setClickable(false);
            buttonResume.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }
        String s = sharedPreferences.getString(MainActivity.FIELD_STATE_PREFERENCE, "s");
        Log.i("abc", s);
    }

    public void onClickPlayNewGame(View view) {
        sharedPreferences.edit().putString(FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY).apply();
        Intent intent;
        if (sharedPreferences.getInt(MainActivity.GAME_MODE, 0)== 0) {
            intent = new Intent(this, GameActivity.class);
        } else {
            intent = new Intent(this, GamePartitionActivity.class);
        }
        startActivity(intent);
    }

    public void onClickReadGameRules(View view) {
        Intent intent = new Intent(this, GameRulesActivity.class);
        startActivity(intent);
    }

    public void onClickResume(View view) {
        Intent intent;
        if (sharedPreferences.getInt(MainActivity.GAME_MODE, 0)== 0) {
            intent = new Intent(this, GameActivity.class);
        } else {
            intent = new Intent(this, GamePartitionActivity.class);
        }
        startActivity(intent);
    }

    public void onClickSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void onClickStatistics(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        startActivity(intent);
    }
}