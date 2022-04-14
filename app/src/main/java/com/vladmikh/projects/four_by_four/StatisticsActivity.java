package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, CustomSpinner.OnSpinnerEventsListener{

    private TextView textViewNoTimeVictory;
    private TextView textViewNoTimeGame;
    private TextView textViewNoTimePercent;
    private TextView textViewOneMinVictory;
    private TextView textViewOneMinGame;
    private TextView textViewOneMinPercent;
    private TextView textViewThreeMinVictory;
    private TextView textViewThreeMinGame;
    private TextView textViewThreeMinPercent;
    private TextView textViewFiveMinVictory;
    private TextView textViewFiveMinGame;
    private TextView textViewFiveMinPercent;

    private SharedPreferences sharedPreferences;
    private CustomSpinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        //Делаем панель иструментов в качестве панели приложения
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Обработка нажатия кнопки назад в toolbar
        Button buttonBackToolbar = toolbar.findViewById(R.id.buttonBackToolbar);
        buttonBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        textViewNoTimeVictory = findViewById(R.id.textViewNoTimeVictory);
        textViewNoTimeGame = findViewById(R.id.textViewNoTimeGame);
        textViewNoTimePercent = findViewById(R.id.textViewNoTimePercent);
        textViewOneMinVictory = findViewById(R.id.textViewOneMinVictory);
        textViewOneMinGame = findViewById(R.id.textViewOneMinGame);
        textViewOneMinPercent = findViewById(R.id.textViewOneMinPercent);
        textViewThreeMinVictory = findViewById(R.id.textViewThreeMinVictory);
        textViewThreeMinGame = findViewById(R.id.textViewThreeMinGame);
        textViewThreeMinPercent = findViewById(R.id.textViewThreeMinPercent);
        textViewFiveMinVictory = findViewById(R.id.textViewFiveMinVictory);
        textViewFiveMinGame = findViewById(R.id.textViewFiveMinGame);
        textViewFiveMinPercent = findViewById(R.id.textViewFiveMinPercent);
        spinner = findViewById(R.id.spinnerGameRules);
        spinner.setSpinnerEventsListener(this);

        ArrayAdapter adapterGame = ArrayAdapter.createFromResource(this, R.array.gameSettings, R.layout.spinner_choosen_item);
        adapterGame.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(adapterGame);
        spinner.setOnItemSelectedListener(this);

        setResults();


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

    private void setResults() {
        int victory;
        int game;
        String percent;

        victory = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_VICTORY,0);
        game = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_GAME, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewNoTimeVictory.setText(String.valueOf(victory));
        textViewNoTimeGame.setText(String.valueOf(game));
        textViewNoTimePercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.ONE_MIN_VICTORY, 0);
        game = sharedPreferences.getInt(MainActivity.ONE_MIN_GAME, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewOneMinVictory.setText(String.valueOf(victory));
        textViewOneMinGame.setText(String.valueOf(game));
        textViewOneMinPercent.setText(percent);

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
    }

    private void setResultsPartition() {
        int victory;
        int game;
        String percent;

        victory = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_VICTORY_PARTITION,0);
        game = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_GAME_PARTITION, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewNoTimeVictory.setText(String.valueOf(victory));
        textViewNoTimeGame.setText(String.valueOf(game));
        textViewNoTimePercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.ONE_MIN_VICTORY_PARTITION, 0);
        game = sharedPreferences.getInt(MainActivity.ONE_MIN_GAME_PARTITION, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewOneMinVictory.setText(String.valueOf(victory));
        textViewOneMinGame.setText(String.valueOf(game));
        textViewOneMinPercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.THREE_MIN_VICTORY_PARTITION, 0);
        game = sharedPreferences.getInt(MainActivity.THREE_MIN_GAME_PARTITION, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewThreeMinVictory.setText(String.valueOf(victory));
        textViewThreeMinGame.setText(String.valueOf(game));
        textViewThreeMinPercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.FIVE_MIN_VICTORY_PARTITION, 0);
        game = sharedPreferences.getInt(MainActivity.FIVE_MIN_GAME_PARTITION, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewFiveMinVictory.setText(String.valueOf(victory));
        textViewFiveMinGame.setText(String.valueOf(game));
        textViewFiveMinPercent.setText(percent);
    }

    private void setResultsMobile() {
        int victory;
        int game;
        String percent;

        victory = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_VICTORY_MOBILE,0);
        game = sharedPreferences.getInt(MainActivity.NO_TIME_LIMIT_GAME_MOBILE, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewNoTimeVictory.setText(String.valueOf(victory));
        textViewNoTimeGame.setText(String.valueOf(game));
        textViewNoTimePercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.ONE_MIN_VICTORY_MOBILE, 0);
        game = sharedPreferences.getInt(MainActivity.ONE_MIN_GAME_MOBILE, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewOneMinVictory.setText(String.valueOf(victory));
        textViewOneMinGame.setText(String.valueOf(game));
        textViewOneMinPercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.THREE_MIN_VICTORY_MOBILE, 0);
        game = sharedPreferences.getInt(MainActivity.THREE_MIN_GAME_MOBILE, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewThreeMinVictory.setText(String.valueOf(victory));
        textViewThreeMinGame.setText(String.valueOf(game));
        textViewThreeMinPercent.setText(percent);

        victory = sharedPreferences.getInt(MainActivity.FIVE_MIN_VICTORY_MOBILE, 0);
        game = sharedPreferences.getInt(MainActivity.FIVE_MIN_GAME_MOBILE, 0);
        percent = calculatePercent(victory, game) + " %";
        textViewFiveMinVictory.setText(String.valueOf(victory));
        textViewFiveMinGame.setText(String.valueOf(game));
        textViewFiveMinPercent.setText(percent);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(i == 0) {
            setResults();
        } else if (i == 1) {
            setResultsPartition();
        } else {
            setResultsMobile();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPopupWindowOpened(Spinner spinner) {
        spinner.setBackground(getResources().getDrawable(R.drawable.spinner_time_up_style));
    }

    @Override
    public void onPopupWindowClosed(Spinner spinner) {
        spinner.setBackground(getResources().getDrawable(R.drawable.spinner_time_style));
    }
}