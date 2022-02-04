package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.vladmikh.projects.four_by_four.adapters.TimeSpinnerAdapter;

public class SettingsActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener, CustomSpinner.OnSpinnerEventsListener{

    private CustomSpinner spinnerTime;
    private ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        spinnerTime = findViewById(R.id.spinnerTime);
        spinnerTime.setSpinnerEventsListener(this);


        adapter = ArrayAdapter.createFromResource(this, R.array.timeSettings, R.layout.spinner_choosen_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTime.setAdapter(adapter);
        spinnerTime.setOnItemSelectedListener(this);


    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

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