package csci3320.defuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.concurrent.TimeUnit;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.content.Context;
import android.widget.EditText;
import android.content.Intent;

public class GameActivity extends AppCompatActivity {
    public String playerName;
    public DefuseGame defuseGame;
    boolean gameCancelled = false;
    boolean startNextRound;
    Button[] grid = new Button[9];
    Context context;
    TextView timer;
    TextView roundCounter;
    EditText response;
    Button btnHelp;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

//        //Get grid for each map button
//        for(int i = 0; i < grid.length; i++) {
//            String buttonName = "mapButton" + i;
//            int buttonID = context.getResources().getIdentifier(buttonName, "id", context.getPackageName());
//            grid[i] = (Button)findViewById(buttonID);
//        }

        grid[0] = (Button)findViewById(R.id.mapButton0);
        grid[1] = (Button)findViewById(R.id.mapButton1);
        grid[2] = (Button)findViewById(R.id.mapButton2);
        grid[3] = (Button)findViewById(R.id.mapButton3);
        grid[4] = (Button)findViewById(R.id.mapButton4);
        grid[5] = (Button)findViewById(R.id.mapButton5);
        grid[6] = (Button)findViewById(R.id.mapButton6);
        grid[7] = (Button)findViewById(R.id.mapButton7);
        grid[8] = (Button)findViewById(R.id.mapButton8);

        //Get element ready for the game
        timer = (TextView) findViewById( R.id.timerContent_textView );
        roundCounter = (TextView) findViewById( R.id.roundContent_textView );
//        response = (EditText) findViewById(R.id.result_editText);
        btnHelp = (Button)findViewById(R.id.helpButton);
        btnHome = (Button)findViewById(R.id.homeButton);

        //Game start
        startNewGame();
    }

    @Override
    public void onBackPressed(){
        gameCancelled = true;
        this.finish();
    }

    //Start a game
    public void startNewGame()
    {
        //create new game
        defuseGame = new DefuseGame();

        //clean up variables
//        txtScore.setText("");

        //start the countdown timer and start the round
        gameCancelled = false;
        TimerCountDown();
        startNextRound = true;
        NextRound(defuseGame);
    }

    public void gameOver(){
        String msgTitle = "GAME OVER";
        int finalScore = defuseGame.getScore();
        int index = isInTopRank(finalScore);

        if(index >= 0){
            msgTitle = "NEW HIGH SCORE!!!";
            setScore();//*************************************
        }

        //CREATE END GAME ALERT
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(msgTitle);

        // set dialog message
        alertDialogBuilder
                .setMessage("Final Score: " + finalScore + "\n\nPLAY AGAIN?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //start new game
                        startNewGame();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //end game and go back to main menu
                        GameActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    //Initial next round
    public void NextRound(DefuseGame game) {
        if (startNextRound) {
            //reset boolean for next round
            startNextRound = false;

            //Get maps and Greek Character
            Map[] mp = game.getMaps();
            GreekCharacter[] gc = game.getGreekChar();

            //Populate grid with maps and greek characters
            for (int i = 0; i < 9; i++) {
                grid[i].setBackgroundResource(mp[i].getMapImage());
                grid[i].setText(String.valueOf(gc[i].getCharName()));
            }
        }
    }

    //Start timer count down
    public void TimerCountDown (){
        final TextView timer = (TextView) findViewById( R.id.timerContent_textView );
        new CountDownTimer(90000, 50) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timer.setText("" + String.format("%02d : %02d",
                        TimeUnit.MILLISECONDS.toSeconds( millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMillis(millisUntilFinished) -
                                TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))));
                if(gameCancelled)
                    this.cancel();
            }

            public void onFinish() {
                gameOver();
                timer.setText("Time up!");
            }
        }.start();
    }

    //Calculate score
    public void calculateScore(String player, long remainingTime, int roundNumber){
//        DatabaseOperations dbOp = new DatabaseOperations(this);
//        dbOp.putInformation(player,levelPlayed,score);
    }

    public void setScore() {

    }

    public int isInTopRank(int score) {
        int rank = -1;
        return rank;
    }
    public void exit_game(View view) {
        onBackPressed();
    }
}
