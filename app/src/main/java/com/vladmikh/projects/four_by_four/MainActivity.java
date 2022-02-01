package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonResume;

    private SharedPreferences sharedPreferences;

    public static final String SHARED_PREFERENCES_NAME = "shared_preferences";
    public static final String FIELD_STATE_PREFERENCE = "field_state";
    private static final String PREFERENCE_EMPTY = "empty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        buttonResume = findViewById(R.id.buttonResume);

        if (sharedPreferences.getString(FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY).length() != 20) {
            buttonResume.setClickable(false);
            buttonResume.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }
    }

    public void onClickPlayNewGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void onClickReadGameRules(View view) {
        Intent intent = new Intent(this, GameRulesActivity.class);
        startActivity(intent);
    }

    public void onClickResume(View view) {
        if (sharedPreferences.getString(FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY).length() != 20) {
            view.setClickable(false);
            view.setBackgroundColor(getResources().getColor(R.color.LightGreen));
        }
    }
}