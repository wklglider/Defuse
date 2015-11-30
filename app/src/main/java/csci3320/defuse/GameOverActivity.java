package csci3320.defuse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over);
        gameOver();
    }

    public void gameOver(){
        final Intent newGame = new Intent(this,GameActivity.class);
        final Intent welcome = new Intent(this,WelcomeActivity.class);
        String msgTitle = "GAME OVER";

        //CREATE END GAME ALERT
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle(msgTitle);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        //start new game
                        finish();
                        startActivity(newGame);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //end game and go back to main menu
                        finish();
                        startActivity(welcome);
                    }
                });

        // create alert dialog
        final AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                alertDialog.show();
            }
        }, 3000);

    }
}
