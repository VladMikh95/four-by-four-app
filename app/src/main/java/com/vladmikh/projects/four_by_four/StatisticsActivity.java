package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        TextView textViewNoTimeVictory = findViewById(R.id.textViewNoTimeVictory);
        TextView textViewNoTimeGame = findViewById(R.id.textViewNoTimeGame);
        TextView textViewNoTimePercent = findViewById(R.id.textViewNoTimePercent);
        TextView textViewThreeMinVictory = findViewById(R.id.textViewThreeMinVictory);
        TextView textViewThreeMinGame = findViewById(R.id.textViewThreeMinGame);
        TextView textViewThreeMinPercent = findViewById(R.id.textViewThreeMinPercent);
        TextView textViewFiveMinVictory = findViewById(R.id.textViewFiveMinVictory);
        TextView textViewFiveMinGame = findViewById(R.id.textViewFiveMinGame);
        TextView textViewFiveMinPercent = findViewById(R.id.textViewFiveMinPercent);
        TextView textViewTenMinVictory = findViewById(R.id.textViewTenMinVictory);
        TextView textViewTenMinGame = findViewById(R.id.textViewTenMinGame);
        TextView textViewTenMinPercent = findViewById(R.id.textViewTenMinPercent);

        int victory;
        int game;
        String percent;

        victory = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_VICTORY,0);
        game = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_GAME, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewNoTimeVictory.setText(String.valueOf(victory));
        textViewNoTimeGame.setText(String.valueOf(game));
        textViewNoTimePercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.THREE_MIN_VICTORY, 0);
        game = sharedPreferences.getInt(MainActivity.THREE_MIN_GAME, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewThreeMinVictory.setText(String.valueOf(victory));
        textViewThreeMinGame.setText(String.valueOf(game));
        textViewThreeMinPercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.FIVE_MIN_VICTORY, 0);
        game = sharedPreferences.getInt(MainActivity.FIVE_MIN_GAME, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewFiveMinVictory.setText(String.valueOf(victory));
        textViewFiveMinGame.setText(String.valueOf(game));
        textViewFiveMinPercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.TEN_MIN_VICTORY, 0);
        game = sharedPreferences.getInt(MainActivity.TEN_MIN_GAME, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewTenMinVictory.setText(String.valueOf(victory));
        textViewTenMinGame.setText(String.valueOf(game));
        textViewTenMinPercent.setText(percent);

    }

    private int calculatePercent (int victory, int game) {
        int result;
        if (game == 0) {
            result = 0;
        } else {
            result = victory * 100 / game;
        }
        return result;
    }
}