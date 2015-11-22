package csci3320.defuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
    }

    public void loadHelpPage(View view) {
        Intent help = new Intent(this,HelpActivity.class);
        startActivity(help);
    }

    public void loadScorePage(View view) {
        Intent score = new Intent(this,ScoreActivity.class);
        startActivity(score);
    }

    public void start_new_game(View view) {
        EditText editTxt = (EditText)findViewById(R.id.playerName_editText);
        String name = editTxt.getText().toString();
        Intent newGame = new Intent(this,GameActivity.class);
        newGame.putExtra("name", name);
        if (!name.isEmpty()) {
            startActivity(newGame);
        }
    }
}
