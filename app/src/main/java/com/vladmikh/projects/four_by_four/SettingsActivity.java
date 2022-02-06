package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class SettingsActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, CustomSpinner.OnSpinnerEventsListener{

    private CustomSpinner spinnerTime;
    private ArrayAdapter adapter;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerTime.setSpinnerEventsListener(this);


        adapter = ArrayAdapter.createFromResource(this, R.array.timeSettings, R.layout.spinner_choosen_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTime.setAdapter(adapter);
        spinnerTime.setOnItemSelectedListener(this);


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        sharedPreferences.edit().putInt(MainActivity.TIME_MODE, i).apply();
        Toast.makeText(this, String.valueOf(sharedPreferences.getInt(MainActivity.TIME_MODE, 0)), Toast.LENGTH_SHORT).show();
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