package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.PreferenceChangeEvent;

public class GameActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private ImageView imageView8;
    private ImageView imageView9;
    private ImageView imageView10;
    private ImageView imageView11;
    private ImageView imageView12;
    private ImageView imageView13;
    private ImageView imageView14;
    private ImageView imageView15;
    private ImageView imageView16;
    private ImageView imageView17;
    private ImageView imageView18;
    private ImageView imageView19;
    private ImageView imageView20;

    private TextView textViewTimer;

    private boolean isChosenFigure;
    private ImageView chosenFigure;

    private Timer timer;
    private boolean isTimeRunning;
    private int timeToFinish;

    private SharedPreferences preferences;
    private static final String PREFERENCE_EMPTY = "empty";
    private static final int TAG_IMAGE = R.id.tagImage;
    private static final String TIME_TO_END_PREFERENCES = "timeToEnd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        preferences = getSharedPreferences(MainActivity.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);
        imageView17 = findViewById(R.id.imageView17);
        imageView18 = findViewById(R.id.imageView18);
        imageView19 = findViewById(R.id.imageView19);
        imageView20 = findViewById(R.id.imageView20);
        textViewTimer = findViewById(R.id.textViewTimer);

        getFieldState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preferences.getInt(MainActivity.TIME_MODE, 0) == 0) {
            textViewTimer.setVisibility(View.INVISIBLE);
        } else {
            timeToFinish = preferences.getInt(TIME_TO_END_PREFERENCES, 1);
            textViewTimer.setVisibility(View.VISIBLE);
            startTime();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveFieldState();
        preferences.edit().putInt(TIME_TO_END_PREFERENCES, timeToFinish).apply();
        if (timer != null) {
            timer.cancel();
        }
        isTimeRunning = false;
    }

    private void startTime() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerMethod();
            }
        }, 0, 1000);
    }

    private void setStartTime(int timeMode) {
        if (timeMode == 1) {
            timeToFinish = 180;
        } else if (timeMode == 2) {
            timeToFinish = 300;
        } else if (timeMode == 3) {
            timeToFinish = 600;
        }
        preferences.edit().putInt(TIME_TO_END_PREFERENCES, timeToFinish).apply();
    }

    private void timerMethod() {
        this.runOnUiThread(timerTick);
    }

    private Runnable timerTick = new Runnable() {
        @Override
        public void run() {
            Log.i("abc", String.valueOf(timeToFinish));
            timeToFinish--;
            int minutes = timeToFinish / 60;
            int seconds = timeToFinish % 60;
            String time = String.format("%02d:%02d", minutes, seconds);
            textViewTimer.setText(time);
            if (timeToFinish <= 0) {
                timer.cancel();
            }
        }
    };

    private void addCountGame(int timeMode) {
        int countGame;
        if (timeMode == 0) {
            countGame = preferences.getInt(MainActivity.NO_TIME_LIMIT_GAME, 0);
            countGame++;
            preferences.edit().putInt(MainActivity.NO_TIME_LIMIT_GAME, countGame).apply();
        } else if (timeMode == 1) {
            countGame = preferences.getInt(MainActivity.THREE_MIN_GAME, 0);
            countGame++;
            preferences.edit().putInt(MainActivity.THREE_MIN_GAME, countGame).apply();
        } else if (timeMode == 2) {
            countGame = preferences.getInt(MainActivity.FIVE_MIN_GAME, 0);
            countGame++;
            preferences.edit().putInt(MainActivity.FIVE_MIN_GAME, countGame).apply();
        } else  {
            countGame = preferences.getInt(MainActivity.TEN_MIN_GAME, 0);
            countGame++;
            preferences.edit().putInt(MainActivity.TEN_MIN_GAME, countGame).apply();
        }
    }


    private void startNewGame() {
        ArrayList<Integer> figuresColor = new ArrayList<>();
        int numImageView;
        ImageView imageView;
        int tagImage;

        for (int colorId = 1; colorId <= 4; colorId++) {
            for (int j = 0; j < 4; j++) {
                figuresColor.add(colorId);
            }
        }

        for (numImageView = 1; numImageView <= 6; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            tagImage = determineColorId(figuresColor.get(random));
            imageView.setImageResource(tagImage);
            imageView.setTag(TAG_IMAGE, tagImage);
            figuresColor.remove(random);
        }

        for (numImageView = 8; numImageView <= 9; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            tagImage = determineColorId(figuresColor.get(random));
            imageView.setImageResource(tagImage);
            imageView.setTag(TAG_IMAGE, tagImage);
            figuresColor.remove(random);
        }

        for (numImageView = 12; numImageView <= 13; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            tagImage = determineColorId(figuresColor.get(random));
            imageView.setImageResource(tagImage);
            imageView.setTag(TAG_IMAGE, tagImage);
            figuresColor.remove(random);
        }

        for (numImageView = 15; numImageView <= 20; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            tagImage = determineColorId(figuresColor.get(random));
            imageView.setImageResource(tagImage);
            imageView.setTag(TAG_IMAGE, tagImage);
            figuresColor.remove(random);
        }

        imageView7.setImageDrawable(null);
        imageView7.setTag(TAG_IMAGE, 0);
        imageView10.setImageDrawable(null);
        imageView10.setTag(TAG_IMAGE, 0);
        imageView11.setImageDrawable(null);
        imageView11.setTag(TAG_IMAGE, 0);
        imageView14.setImageDrawable(null);
        imageView14.setTag(TAG_IMAGE, 0);

        int timeMode = preferences.getInt(MainActivity.TIME_MODE, 0);
        addCountGame(timeMode);
        setStartTime(timeMode);
    }

    private int determineColorId(int numOfColor) {
        int result;
        switch (numOfColor) {
            case 1:
                result = R.drawable.color1;
                break;
            case 2:
                result = R.drawable.color2;
                break;
            case 3:
                result = R.drawable.color3;
                break;
            case 4:
                result = R.drawable.color4;
                break;
            default:
                result = 0;
                break;
        }
        return result;
    }

    private int determineNumColor(int tagImage) {
        int result;
        if (tagImage == R.drawable.color1) {
            result = 1;
        } else if (tagImage == R.drawable.color2) {
            result = 2;
        } else if (tagImage == R.drawable.color3) {
            result = 3;
        } else if (tagImage == R.drawable.color4) {
            result = 4;
        } else {
            result = 0;
        }
        return result;
    }

    //Метод сохраняет в SharedPreferences текущее состояние поля
    private void saveFieldState() {
        Log.i("abc", "save" + preferences.getString(MainActivity.FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY));
        StringBuilder builder = new StringBuilder();
        int tagImage;

        for (int i = 1; i <= 20; i++) {
            tagImage = (int) (determineImageView(i).getTag(TAG_IMAGE));
            builder.append(determineNumColor(tagImage));
        }
        preferences.edit().putString(MainActivity.FIELD_STATE_PREFERENCE, builder.toString()).apply();
        Log.i("abc", "save2" + preferences.getString(MainActivity.FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY));

    }

    //Метод возвращает из SharedPreferences сохраненное состояние поля
    private void getFieldState() {
        String fieldState = preferences.getString(MainActivity.FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY);
        ImageView imageView;
        Log.i("abc", "get" + fieldState);
        StringBuilder builder = new StringBuilder();

        if (fieldState.length() != 20) {
            startNewGame();
        } else {
            int tagImage;
            for (int i = 1; i <= 20; i++) {

                imageView = determineImageView(i);
                tagImage = determineColorId(Character.getNumericValue(fieldState.charAt(i - 1)));
                imageView.setImageResource(tagImage);
                imageView.setTag(TAG_IMAGE, tagImage);
                builder.append(determineNumColor((int) imageView.getTag(TAG_IMAGE)));
            }
        }
        Log.i("abc", "get2" + builder.toString());

    }

    //Возвращает ImageView по числу
    private ImageView determineImageView(int numOfImageView) {
        ImageView result;
        switch (numOfImageView) {
            case 1:
                result = imageView1;
                break;
            case 2:
                result = imageView2;
                break;
            case 3:
                result = imageView3;
                break;
            case 4:
                result = imageView4;
                break;
            case 5:
                result = imageView5;
                break;
            case 6:
                result = imageView6;
                break;
            case 7:
                result = imageView7;
                break;
            case 8:
                result = imageView8;
                break;
            case 9:
                result = imageView9;
                break;
            case 10:
                result = imageView10;
                break;
            case 11:
                result = imageView11;
                break;
            case 12:
                result = imageView12;
                break;
            case 13:
                result = imageView13;
                break;
            case 14:
                result = imageView14;
                break;
            case 15:
                result = imageView15;
                break;
            case 16:
                result = imageView16;
                break;
            case 17:
                result = imageView17;
                break;
            case 18:
                result = imageView18;
                break;
            case 19:
                result = imageView19;
                break;
            case 20:
                result = imageView20;
                break;
            default:
                result = new ImageView(this);
                result.setId(-1);
        }
        return result;
    }

    private boolean isNearImageView(ImageView imageView, String numImageView) {
        ImageView imageViewNear;
        String[] nums = numImageView.split(" ");

        for (String num : nums) {
            imageViewNear = determineImageView(Integer.parseInt(num));
            if (imageView.equals(imageViewNear)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWin() {
        boolean result = false;

        if (imageView7 == null && imageView10 == null && imageView11 == null && imageView14 == null) {
            if (imageView1.getDrawable().equals(imageView2.getDrawable())
                    && imageView1.getDrawable().equals(imageView5.getDrawable())
                    && imageView1.getDrawable().equals(imageView6.getDrawable())) {

                if (imageView3.getDrawable().equals(imageView4.getDrawable())
                        && imageView3.getDrawable().equals(imageView8.getDrawable())
                        && imageView3.getDrawable().equals(imageView9.getDrawable())) {

                    if (imageView12.getDrawable().equals(imageView13.getDrawable())
                            && imageView12.getDrawable().equals(imageView17.getDrawable())
                            && imageView12.getDrawable().equals(imageView18.getDrawable())) {

                        if (imageView15.getDrawable().equals(imageView15.getDrawable())
                                && imageView15.getDrawable().equals(imageView19.getDrawable())
                                && imageView15.getDrawable().equals(imageView20.getDrawable())) {

                            result = true;

                        }

                    }
                }
            }
        }

        return result;
    }

    public void onClickChooseFigure(View view) {
        ImageView imageView = (ImageView) view;
        if (!isChosenFigure) {
            if (imageView.getDrawable() != null) {
                chosenFigure = imageView;
                imageView.setBackgroundResource(R.drawable.chosen_field);
                isChosenFigure = true;
            }

        } else {
            if (chosenFigure.getId() != imageView.getId()
                    && imageView.getDrawable() == null
                    && isNearImageView(imageView, chosenFigure.getTag().toString())) {
                int tagImage = (int) chosenFigure.getTag(TAG_IMAGE);

                imageView.setImageResource(tagImage);
                imageView.setTag(TAG_IMAGE, tagImage);
                chosenFigure.setImageDrawable(null);
                chosenFigure.setTag(TAG_IMAGE, 0);

            }
            chosenFigure.setBackgroundColor(getResources().getColor(R.color.trans));
            chosenFigure = null;
            isChosenFigure = false;
        }
    }

    private void test1() {
        imageView1.setImageResource(R.drawable.color1);
        ImageView imageView;

        for (int i = 2; i <= 20; i++) {
            imageView = determineImageView(i);
            imageView.setImageDrawable(null);
        }
    }
}