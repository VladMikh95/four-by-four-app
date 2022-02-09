package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
    private int timeToFinish;

    private boolean isGameOver;

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
        if (isGameOver) {
            preferences.edit().putString(MainActivity.FIELD_STATE_PREFERENCE, MainActivity.PREFERENCE_EMPTY).apply();
        } else {
            saveFieldState();
            preferences.edit().putInt(TIME_TO_END_PREFERENCES, timeToFinish).apply();
        }
        if (timer != null) {
            timer.cancel();
        }
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
            timeToFinish--;
            int minutes = timeToFinish / 60;
            int seconds = timeToFinish % 60;
            String time = String.format("%02d:%02d", minutes, seconds);
            textViewTimer.setText(time);
            if (timeToFinish <= 0) {
                timer.cancel();
                isGameOver =true;
                createAlertDialog(getResources().getString(R.string.message_lose));
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
        } else {
            countGame = preferences.getInt(MainActivity.TEN_MIN_GAME, 0);
            countGame++;
            preferences.edit().putInt(MainActivity.TEN_MIN_GAME, countGame).apply();
        }
    }

    private void addCountVictory(int timeMode) {
        int countVictory;
        if (timeMode == 0) {
            countVictory = preferences.getInt(MainActivity.NO_TIME_LIMIT_VICTORY, 0);
            countVictory++;
            preferences.edit().putInt(MainActivity.NO_TIME_LIMIT_VICTORY, countVictory).apply();
        } else if (timeMode == 1) {
            countVictory = preferences.getInt(MainActivity.THREE_MIN_VICTORY, 0);
            countVictory++;
            preferences.edit().putInt(MainActivity.THREE_MIN_VICTORY, countVictory).apply();
        } else if (timeMode == 2) {
            countVictory = preferences.getInt(MainActivity.FIVE_MIN_VICTORY, 0);
            countVictory++;
            preferences.edit().putInt(MainActivity.FIVE_MIN_VICTORY, countVictory).apply();
        } else {
            countVictory = preferences.getInt(MainActivity.TEN_MIN_VICTORY, 0);
            countVictory++;
            preferences.edit().putInt(MainActivity.TEN_MIN_VICTORY, countVictory).apply();
        }
    }


    private void startNewGame() {
        isGameOver = false;
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
        Log.i("abc", "time" + timeMode);
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
        StringBuilder builder = new StringBuilder();
        int tagImage;

        for (int i = 1; i <= 20; i++) {
            tagImage = (int) (determineImageView(i).getTag(TAG_IMAGE));
            builder.append(determineNumColor(tagImage));
        }
        preferences.edit().putString(MainActivity.FIELD_STATE_PREFERENCE, builder.toString()).apply();

    }

    //Метод возвращает из SharedPreferences сохраненное состояние поля
    private void getFieldState() {
        String fieldState = preferences.getString(MainActivity.FIELD_STATE_PREFERENCE, PREFERENCE_EMPTY);
        ImageView imageView;
        StringBuilder builder = new StringBuilder();

        if (fieldState.length() != 20) {
            testWin();
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

    private void isWin() {
        String tagIm1 = String.valueOf(imageView1.getTag(TAG_IMAGE));
        String tagIm3 = String.valueOf(imageView3.getTag(TAG_IMAGE));
        String tagIm12 = String.valueOf(imageView12.getTag(TAG_IMAGE));
        String tagIm15 = String.valueOf(imageView15.getTag(TAG_IMAGE));

        if (imageView7.getDrawable() == null
                && imageView10.getDrawable() == null
                && imageView11.getDrawable() == null
                && imageView14.getDrawable() == null
                && tagIm1.equals(String.valueOf(imageView2.getTag(TAG_IMAGE)))
                && tagIm1.equals(String.valueOf(imageView5.getTag(TAG_IMAGE)))
                && tagIm1.equals(String.valueOf(imageView6.getTag(TAG_IMAGE)))
                && tagIm3.equals(String.valueOf(imageView4.getTag(TAG_IMAGE)))
                && tagIm3.equals(String.valueOf(imageView8.getTag(TAG_IMAGE)))
                && tagIm3.equals(String.valueOf(imageView9.getTag(TAG_IMAGE)))
                && tagIm12.equals(String.valueOf(imageView13.getTag(TAG_IMAGE)))
                && tagIm12.equals(String.valueOf(imageView17.getTag(TAG_IMAGE)))
                && tagIm12.equals(String.valueOf(imageView18.getTag(TAG_IMAGE)))
                && tagIm15.equals(String.valueOf(imageView16.getTag(TAG_IMAGE)))
                && tagIm15.equals(String.valueOf(imageView19.getTag(TAG_IMAGE)))
                && tagIm15.equals(String.valueOf(imageView20.getTag(TAG_IMAGE)))) {

            isGameOver = true;
            addCountVictory(preferences.getInt(MainActivity.TIME_MODE, 0));
            createAlertDialog(getResources().getString(R.string.message_win));

        }
    }

    private void createAlertDialog(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_message, null);

        builder.setView(dialogView);
        TextView textViewMessageDialog = dialogView.findViewById(R.id.textViewDialogMessageTitle);
        Button buttonMenu = dialogView.findViewById(R.id.buttonDialogMenu);
        Button buttonNewGame = dialogView.findViewById(R.id.buttonDialogNewGame);
        final AlertDialog dialog = builder.create();
        
        textViewMessageDialog.setText(message);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                startNewGame();
            }
        });

        dialog.show();

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
                isWin();

            }
            chosenFigure.setBackgroundColor(getResources().getColor(R.color.trans));
            chosenFigure = null;
            isChosenFigure = false;
        }
    }

    private void testWin() {
        imageView1.setImageResource(R.drawable.color1);
        imageView1.setTag(TAG_IMAGE, R.drawable.color1);
        imageView2.setImageResource(R.drawable.color1);
        imageView2.setTag(TAG_IMAGE, R.drawable.color1);
        imageView5.setImageResource(R.drawable.color1);
        imageView5.setTag(TAG_IMAGE, R.drawable.color1);
        imageView6.setImageResource(R.drawable.color1);
        imageView6.setTag(TAG_IMAGE, R.drawable.color1);
        imageView3.setImageResource(R.drawable.color2);
        imageView3.setTag(TAG_IMAGE, R.drawable.color2);
        imageView4.setImageResource(R.drawable.color2);
        imageView4.setTag(TAG_IMAGE, R.drawable.color2);
        imageView8.setImageResource(R.drawable.color2);
        imageView8.setTag(TAG_IMAGE, R.drawable.color2);
        imageView9.setImageResource(R.drawable.color2);
        imageView9.setTag(TAG_IMAGE, R.drawable.color2);
        imageView12.setImageResource(R.drawable.color3);
        imageView12.setTag(TAG_IMAGE, R.drawable.color3);
        imageView13.setImageResource(R.drawable.color3);
        imageView13.setTag(TAG_IMAGE, R.drawable.color3);
        imageView17.setImageResource(R.drawable.color3);
        imageView17.setTag(TAG_IMAGE, R.drawable.color3);
        imageView18.setImageResource(R.drawable.color3);
        imageView18.setTag(TAG_IMAGE, R.drawable.color3);
        imageView11.setImageResource(R.drawable.color4);
        imageView11.setTag(TAG_IMAGE, R.drawable.color4);
        imageView14.setImageResource(R.drawable.color4);
        imageView14.setTag(TAG_IMAGE, R.drawable.color4);
        imageView16.setImageResource(R.drawable.color4);
        imageView16.setTag(TAG_IMAGE, R.drawable.color4);
        imageView20.setImageResource(R.drawable.color4);
        imageView20.setTag(TAG_IMAGE, R.drawable.color4);
        imageView7.setImageDrawable(null);
        imageView7.setTag(TAG_IMAGE, 0);
        imageView10.setImageDrawable(null);
        imageView10.setTag(TAG_IMAGE, 0);
        imageView15.setImageDrawable(null);
        imageView15.setTag(TAG_IMAGE, 0);
        imageView19.setImageDrawable(null);
        imageView19.setTag(TAG_IMAGE, 0);
    }
}