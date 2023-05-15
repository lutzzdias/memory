package com.lutzzdias.carlist;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    GridView gridview;
    ImageView currentImage;
    private Integer hitCount = 0;
    private Integer playCount = 0;
    final int[] images = new int[] {R.drawable.cat_1, R.drawable.cat_2, R.drawable.cat_3, R.drawable.cat_4, R.drawable.cat_5, R.drawable.cat_6, R.drawable.cat_7, R.drawable.cat_8, R.drawable.cat_9, R.drawable.cat_10, R.drawable.cat_11, R.drawable.cat_12, R.drawable.cat_13, R.drawable.cat_14, R.drawable.cat_15, R.drawable.cat_16, R.drawable.cat_17, R.drawable.cat_18};
    Integer[] imagePositions = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: Show all images for 2 seconds on startup

        // gridView adapter
        GridViewAdapter adapter = new GridViewAdapter(this);

        // shuffle img positions
        List<Integer> imgPositionsList = Arrays.asList(imagePositions);
        Collections.shuffle(imgPositionsList);
        imgPositionsList.toArray(imagePositions);

        // gridView
        gridview = findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener((parent, view, position, id) -> {
            playCount++;
            if (currentPosition < 0) {
                currentPosition = position;
                currentImage = (ImageView) view;
                ((ImageView) view).setImageResource(images[imagePositions[currentPosition]]);
            } else {
                if(currentPosition == position){
                    currentImage.setImageResource(R.drawable.hidden);
                } else if (imagePositions[currentPosition] != imagePositions[position]){
                    // TODO: Show both images for 2s then set both to hidden
                    ((ImageView) view).setImageResource(images[imagePositions[position]]);
                    ((ImageView) view).setImageResource(R.drawable.hidden);
                    currentImage.setImageResource(R.drawable.hidden);
                } else {
                    ((ImageView) view).setImageResource(images[imagePositions[currentPosition]]);
                    hitCount++;

                    if (hitCount == 18) {
                        // TODO: define game over UI
                        // following line is working
                        System.out.println("GAME OVER, YOU WIN");

                        Double score = (hitCount / Double.parseDouble(playCount.toString())) * 100;
                        // TODO: Show score in a new screen or modal
                        System.out.printf("SCORE: %.2f\n", score);
                    }
                }
                currentPosition = -1;
            }
        });
    }
}