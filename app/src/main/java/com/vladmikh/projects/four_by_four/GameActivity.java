package com.vladmikh.projects.four_by_four;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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

    private boolean isChosenFigure;
    private ImageView chosenFigure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageView1 = findViewById(R.id.imageView1);
        Log.i("abc", (String) imageView1.getTag());
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

        startNewGame();

    }

    public void startNewGame(){
        ArrayList<Integer> figuresColor = new ArrayList<>();
        int numImageView;
        ImageView imageView;

        for (int colorId = 1; colorId <= 4; colorId++) {
            for (int j = 0; j < 4; j++) {
                figuresColor.add(colorId);
            }
        }

        for (numImageView = 1; numImageView <= 6; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            imageView.setImageResource(determineColorId(figuresColor.get(random)));
            figuresColor.remove(random);
        }

        for (numImageView =8; numImageView <= 9; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            imageView.setImageResource(determineColorId(figuresColor.get(random)));
            figuresColor.remove(random);
        }

        for (numImageView = 12; numImageView <= 13; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            imageView.setImageResource(determineColorId(figuresColor.get(random)));
            figuresColor.remove(random);
        }

        for (numImageView = 15; numImageView <= 20; numImageView++) {
            int random = (int) (Math.random() * figuresColor.size());
            imageView = determineImageView(numImageView);
            imageView.setImageResource(determineColorId(figuresColor.get(random)));
            figuresColor.remove(random);
        }

        imageView7.setImageDrawable(null);
        imageView10.setImageDrawable(null);
        imageView11.setImageDrawable(null);
        imageView14.setImageDrawable(null);


    }

    private int determineColorId(int numOfColor) {
        int result;
        switch(numOfColor) {
            case 1:
              result = R.drawable.color1;
              break;
            case 2:
                result = R.drawable.color2;
                break;
            case 3:
                result = R.drawable.color3;
                break;
            default:
                result = R.drawable.color4;
        }
        return result;
    }

    private ImageView determineImageView(int numOfImageView) {
        ImageView result;
        switch(numOfImageView) {
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
            default:
                result = imageView20;
        }
        return result;
    }

    private boolean isNearImageView (String numImageView) {
        boolean result = false;
        return result;
    }

    public void onClickChooseFigure(View view) {
        ImageView imageView = (ImageView) view;
        if (!isChosenFigure)  {
            if (imageView.getDrawable() != null) {
                chosenFigure = imageView;
                imageView.setBackgroundColor(getResources().getColor(R.color.grey));

                isChosenFigure = true;
            }

        } else  {
            if (chosenFigure.getId() != imageView.getId() && imageView.getDrawable() == null) {
                imageView.setImageDrawable(chosenFigure.getDrawable());
                chosenFigure.setImageDrawable(null);
            }
            chosenFigure.setBackgroundColor(getResources().getColor(R.color.white));
            chosenFigure = null;
            isChosenFigure = false;
        }
    }
}