package com.aaron.example.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0 = yellow, 1 = red
    int activePlayer = 0;
    
    // Bool to check to see if game is active
    boolean gameIsActive = true;

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6, 7, 8}, {0,3,6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view){
        // Method is ran based off of which view was clicked
        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive)
        {
            gameState[tappedCounter] = activePlayer;

            // The image will originally be off the screen
            counter.setTranslationY(-1000f);

            if (activePlayer == 0){
                // Import an image to the view clicked
                counter.setImageResource(R.drawable.bluechip);
                activePlayer = 1;
            } else{
                counter.setImageResource(R.drawable.redchip);
                activePlayer = 0;
            }
               counter.animate().translationYBy(1000f).rotation(360).setDuration(1000);

                for(int[] winningPosition: winningPositions){
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                            gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                            gameState[winningPosition[0]]!=2){

                        gameIsActive = false;

                        String winner = "Red";

                        if(gameState[winningPosition[0]] == 0){
                            winner = "Blue";
                        }
                        TextView winnerMessage = findViewById(R.id.winnerMessage);

                        winnerMessage.setText(winner + " has won!");

                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    } else{
                        boolean gameIsOver = true;
                        for(int counterState : gameState){
                            if(counterState ==2) gameIsOver = false;
                        }
                        if(gameIsOver){
                            TextView winnerMessage = findViewById(R.id.winnerMessage);
                            winnerMessage.setText("Draw!");

                            LinearLayout layout = findViewById(R.id.playAgainLayout);
                            layout.setVisibility(View.VISIBLE);
                        }
                    }
                }

        }
    }

    public void playAgain(View view){
        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        gameIsActive = true;
        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);


        for(int i =0; i< gridLayout.getChildCount(); i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
