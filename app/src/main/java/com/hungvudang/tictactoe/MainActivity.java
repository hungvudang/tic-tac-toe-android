package com.hungvudang.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /*
        gameActor = 0: red
        gameActor = 1: blue
    */
    boolean gameActor = true;
    int[] gameStages = {2,2,2,2,2,2,2,2,2};
    int[][] gameStageWins = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClick(View view) {

        int index_tagView = Integer.parseInt(view.getTag().toString());
        if (! gameOver){
            if (gameStages[index_tagView] == 2){
                if (gameActor) {
                    ((ImageView)view).setImageResource(R.drawable.circle_blue);
                    gameStages[index_tagView] = 1;
                } else {
                    ((ImageView)view).setImageResource(R.drawable.circle_red);
                    gameStages[index_tagView] = 0;
                }

                view.setScaleX(1.5f);
                view.setScaleY(1.5f);
                view.animate().scaleX(0.9f).scaleY(0.9f).setDuration(500);
            }

            int checkOver = checkGameOver();
            switch(checkOver){
                case 1: {
                    break;
                }
                case 2: {
                    // gameActor = true --> Blue is winner;
                    // else Red is winner;

                    if (gameActor){
                        System.out.println("Blue is winner");
                        Toast.makeText(MainActivity.this, "Blue is winner",Toast.LENGTH_LONG).show();
                    } else {
                        System.out.println("Red is winner");
                        Toast.makeText(MainActivity.this, "Red is winner",Toast.LENGTH_LONG).show();
                    }
                    gameOver = true;
                    break;
                }
                case 3: {
                    System.out.println("Drawn !");
                    Toast.makeText(MainActivity.this, "Drawn !",Toast.LENGTH_LONG).show();
                    gameOver = true;
                    break;
                }
            }

        }

        // change player
        gameActor = !gameActor;

    }

    private int checkGameOver(){
        /*
            return 1: continue;
            return 2: Someone win;
            return 3: drawn
        */
        for (int[] stageWin : gameStageWins){
            if (    gameStages[stageWin[0]] == gameStages[stageWin[1]] &&
                    gameStages[stageWin[1]] == gameStages[stageWin[2]] &&
                    gameStages[stageWin[0]] != 2)
            {
                // Someone win
                return 2;
            }
        }

        for (int stage : gameStages){
            // Continue game
            if (stage == 2){
                return 1;
            }
        }
        // Drawn
        return 3;
    }
}