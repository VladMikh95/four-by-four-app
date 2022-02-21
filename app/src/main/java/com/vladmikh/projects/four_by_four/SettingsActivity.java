package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SettingsActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, CustomSpinner.OnSpinnerEventsListener{

    private SharedPreferences sharedPreferences;
    private CustomSpinner spinnerTime;
    private CustomSpinner spinnerGame;
    private Switch switchSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerTime.setSpinnerEventsListener(this);

        spinnerGame = findViewById(R.id.spinnerGame);
        spinnerGame.setSpinnerEventsListener(this);

        switchSound = findViewById(R.id.switchSettings);
        switchSound.setChecked(sharedPreferences.getInt(MainActivity.TURNING_SOUND, 0) == 0);



        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.timeSettings, R.layout.spinner_choosen_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTime.setAdapter(adapter);
        spinnerTime.setSelection(sharedPreferences.getInt(MainActivity.TIME_MODE, 0));
        spinnerTime.setOnItemSelectedListener(this);

        ArrayAdapter adapterGame = ArrayAdapter.createFromResource(this, R.array.gameSettings, R.layout.spinner_choosen_item);
        adapterGame.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerGame.setAdapter(adapterGame);
        spinnerGame.setSelection(sharedPreferences.getInt(MainActivity.GAME_MODE, 0));
        spinnerGame.setOnItemSelectedListener(this);

        switchSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    chooseSound(MainActivity.SOUND_ON);
                } else {
                    chooseSound(MainActivity.SOUND_OFF);
                }
            }
        });
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.equals(spinnerTime)) {
            saveTimeMode(i);
        } else {
           saveGameMode(i);
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

    private void chooseSound(int sound) {
        sharedPreferences.edit().putInt(MainActivity.TURNING_SOUND, sound).apply();
        String s = String.valueOf(sharedPreferences.getInt(MainActivity.TURNING_SOUND, 0));
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    private void saveTimeMode(int i) {
        if (sharedPreferences.getInt(MainActivity.TIME_MODE, -1) == i) {
            return;
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MainActivity.TIME_MODE, i);
            editor.putString(MainActivity.FIELD_STATE_PREFERENCE,MainActivity.PREFERENCE_EMPTY);
            editor.apply();
            Toast.makeText(this, String.valueOf(sharedPreferences.getInt(MainActivity.TIME_MODE, 0)), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveGameMode(int i) {
        if (sharedPreferences.getInt(MainActivity.GAME_MODE, -1) == i) {
            return;
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MainActivity.GAME_MODE, i);
            editor.putString(MainActivity.FIELD_STATE_PREFERENCE,MainActivity.PREFERENCE_EMPTY);
            editor.apply();
            Toast.makeText(this, sharedPreferences.getInt(MainActivity.GAME_MODE, 0) + "abc", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSoundOff(View view) {
        chooseSound(1);
        switchSound.setChecked(false);
    }

    public void onClickSoundOn(View view) {
        chooseSound(0);
        switchSound.setChecked(true);
    }

}