package csci3320.defuse;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ClipData;
import android.view.DragEvent;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import android.widget.Button;


public class GameActivity extends AppCompatActivity {
    public DefuseGame defuseGame;
    boolean gameCancelled = false;
    boolean startNextRound;
    ArrayList<Map> quesMapList = new ArrayList<>();
    Button[] grid = new Button[9];
    Button[] quesGrid = new Button[3];
    String[] answer = new String[3];
    boolean[] ansResult = new boolean[3];
    TextView timer;
    TextView roundCounter;
    int rCounter = 1;
    ImageView[] lead = new ImageView[10];
    ImageView bomb;
//    EditText response;
    Button btnHelp;
    Button btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);

        findViewById(R.id.mapButton0).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton1).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton2).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton3).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton4).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton5).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton6).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton7).setOnLongClickListener(longListener);
        findViewById(R.id.mapButton8).setOnLongClickListener(longListener);

        findViewById(R.id.recieverButton0).setOnDragListener(dragListener);
        findViewById(R.id.recieverButton1).setOnDragListener(dragListener);
        findViewById(R.id.recieverButton2).setOnDragListener(dragListener);

        //Get grid for each map button
        grid[0] = (Button)findViewById(R.id.mapButton0);
        grid[1] = (Button)findViewById(R.id.mapButton1);
        grid[2] = (Button)findViewById(R.id.mapButton2);
        grid[3] = (Button)findViewById(R.id.mapButton3);
        grid[4] = (Button)findViewById(R.id.mapButton4);
        grid[5] = (Button)findViewById(R.id.mapButton5);
        grid[6] = (Button)findViewById(R.id.mapButton6);
        grid[7] = (Button)findViewById(R.id.mapButton7);
        grid[8] = (Button)findViewById(R.id.mapButton8);

        //Get questionGrid for the receive buttons
        quesGrid[0] = (Button)findViewById(R.id.recieverButton0);
        quesGrid[1] = (Button)findViewById(R.id.recieverButton1);
        quesGrid[2] = (Button)findViewById(R.id.recieverButton2);

        //Get element ready for the game
        timer = (TextView) findViewById( R.id.timerContent_textView );
        roundCounter = (TextView) findViewById( R.id.roundContent_textView );

        //Get lead element image view for the lead burning
        lead[0] = (ImageView) findViewById(R.id.ropeImageView0);
        lead[1] = (ImageView) findViewById(R.id.ropeImageView1);
        lead[2] = (ImageView) findViewById(R.id.ropeImageView2);
        lead[3] = (ImageView) findViewById(R.id.ropeImageView3);
        lead[4] = (ImageView) findViewById(R.id.ropeImageView4);
        lead[5] = (ImageView) findViewById(R.id.ropeImageView5);
        lead[6] = (ImageView) findViewById(R.id.ropeImageView6);
        lead[7] = (ImageView) findViewById(R.id.ropeImageView7);
        lead[8] = (ImageView) findViewById(R.id.ropeImageView8);
        lead[9] = (ImageView) findViewById(R.id.ropeImageView9);
        bomb = (ImageView) findViewById(R.id.bombaImageView);

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

        //start the countdown timer and start the round
        gameCancelled = false;
        TimerCountDown();
        rCounter = 1;

        //Reset burning lead
        bomb.setBackgroundResource(R.drawable.bomb);
        lead[0].setBackgroundResource(R.drawable.fire_rope);
        for (int i = 1; i < lead.length; i++) {
            lead[i].setBackgroundResource(R.drawable.rope);
        }

        startNextRound = true;
        NextRound(defuseGame);
    }

    public void gameOver(){
        String msgTitle = "GAME OVER";
        int finalScore = defuseGame.getScore();
        int index = isInTopRank(finalScore);

        //***************************************************************************  GameOverActivity Fail only

        if(index >= 0){
            msgTitle = "NEW HIGH SCORE!!!";
            setScore();
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

            //Refresh round count
            roundCounter.setText(String.format("%d / 5", rCounter));

            //Get maps and Greek Character
            Map[] mp = game.getMaps();
            GreekCharacter[] gc = game.getGreekChar();

            //Populate grid with maps and greek characters
            for (int i = 0; i < grid.length; i++) {
                grid[i].setBackgroundResource(mp[i].getMapImage());
                grid[i].setText(String.valueOf(gc[i].getCharName()));
                mp[i].setGreekChar(gc[i].getCharName());
            }

            //Populate question grid with maps only
            quesMapList.clear();
            Collections.addAll(quesMapList, mp);
            Collections.shuffle(quesMapList);
            for (int i = 0; i < quesGrid.length; i++) {
                quesGrid[i].setText("");
                quesGrid[i].setBackgroundResource(quesMapList.get(i).getMapImage());
                answer[i] = quesMapList.get(i).getGreekChar();
                ansResult[i] = false;
            }
        }
    }

    //Start timer count down
    public void TimerCountDown (){
        final TextView timer = (TextView) findViewById( R.id.timerContent_textView );
        final long totalTime = 90000;
        new CountDownTimer(totalTime, 50) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {
                timer.setText("" + String.format("%02d : %02d : %02d",
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                                TimeUnit.MILLISECONDS.toMillis(millisUntilFinished)
                                        - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)))
                );
                //Update burning lead images
                leadBurningEffect(millisUntilFinished, totalTime);

                timer.setText(String.format("%2d : %02d : %02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toMillis(millisUntilFinished)
                                - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))));

                leadBurningEffect(millisUntilFinished,totalTime);

                if(gameCancelled)
                    this.cancel();
            }

            public void onFinish() {
                gameOver();
                timer.setText(R.string.time_up);
            }
        }.start();
    }

    //Update burning lead images
    public void leadBurningEffect(long timeRemaining, long totalTime) {
        int percentage = (int)((double)timeRemaining / totalTime * 100.0);
        switch(percentage) {
            case 99:
            case 95:
                lead[0].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 89:
            case 85:
                lead[0].setBackgroundResource(0);
                lead[1].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 79:
            case 75:
                lead[0].setBackgroundResource(0);
                lead[1].setBackgroundResource(0);
                lead[2].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 69:
            case 65:
                lead[0].setBackgroundResource(0);
                lead[1].setBackgroundResource(0);
                lead[2].setBackgroundResource(0);
                lead[3].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 59:
            case 55:
                lead[1].setBackgroundResource(0);
                lead[2].setBackgroundResource(0);
                lead[3].setBackgroundResource(0);
                lead[4].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 49:
            case 45:
                lead[2].setBackgroundResource(0);
                lead[3].setBackgroundResource(0);
                lead[4].setBackgroundResource(0);
                lead[5].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 39:
            case 35:
                lead[3].setBackgroundResource(0);
                lead[4].setBackgroundResource(0);
                lead[5].setBackgroundResource(0);
                lead[6].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 29:
            case 25:
                lead[4].setBackgroundResource(0);
                lead[5].setBackgroundResource(0);
                lead[6].setBackgroundResource(0);
                lead[7].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 19:
            case 15:
                lead[5].setBackgroundResource(0);
                lead[6].setBackgroundResource(0);
                lead[7].setBackgroundResource(0);
                lead[8].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 9:
            case 5:
                lead[6].setBackgroundResource(0);
                lead[7].setBackgroundResource(0);
                lead[8].setBackgroundResource(0);
                lead[9].setBackgroundResource(R.drawable.fire_rope);
                break;
            case 1:
            case 0:
                lead[7].setBackgroundResource(0);
                lead[8].setBackgroundResource(0);
                lead[9].setBackgroundResource(0);
                bomb.setBackgroundResource(R.drawable.small_boom);
                break;
        }
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

    public void checkAnswers() {
        for (int i = 0; i < 3; i++) {
            if (ansResult[i] == false && quesGrid[i].getText() != "") {
                if(quesGrid[i].getText() == answer[i]) {
                    quesGrid[i].setText("✓");
                    quesGrid[i].setTextColor(Color.GREEN);
                    ansResult[i] = true;
                } else {
                    quesGrid[i].setText("X");
                    quesGrid[i].setTextColor(Color.RED);
                }
            }
        }

        if(ansResult[0] && ansResult[1] && ansResult[2]) {
            rCounter++;
            if(rCounter == 5) {
                //***************************************************************************  GameOverActivity Succeed only
            } else {
                startNextRound = true;
                NextRound(defuseGame);
            }
        }
    }

    OnLongClickListener longListener = new OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {
            TextView fruit = (TextView) v;
            Toast.makeText(GameActivity.this, "Text long clicked - " +fruit.getText() , Toast.LENGTH_SHORT).show();

            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);

            ClipData data = ClipData.newPlainText("", "");
            v.startDrag(data, myShadowBuilder, fruit, 0);

            return true;
        }

    };

    OnDragListener dragListener = new OnDragListener()
    {
        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            int dragEvent = event.getAction();
            TextView dropText = (TextView) v;

            switch(dragEvent)
            {
                case DragEvent.ACTION_DRAG_ENTERED:
                    //dropText.setTextColor(Color.GREEN);
                    break;

                case DragEvent.ACTION_DRAG_EXITED:
                    //dropText.setTextColor(Color.RED);
                    break;

                case DragEvent.ACTION_DROP:
                    TextView draggedText = (TextView)event.getLocalState();
                    dropText.setText(draggedText.getText());

                    //Check the three answer are all correct or not
                    checkAnswers();
                    break;
            }

            return true;
        }

    };

}
