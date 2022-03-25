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

        //Делаем панель иструментов в качестве панели приложения
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Обработка нажатия кнопки назад в toolbar
        Button buttonBackToolbar = toolbar.findViewById(R.id.buttonBackToolbar);
        buttonBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

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
    }

    private void saveTimeMode(int i) {
        if (sharedPreferences.getInt(MainActivity.TIME_MODE, -1) == i) {
            return;
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(MainActivity.TIME_MODE, i);
            editor.putString(MainActivity.FIELD_STATE_PREFERENCE,MainActivity.PREFERENCE_EMPTY);
            editor.apply();
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