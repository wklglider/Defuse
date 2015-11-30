package csci3320.defuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        nameWatch();
    }

    public void loadHelpPage(View view) {
        Intent help = new Intent(this,HelpActivity.class);
        finish();
        startActivity(help);
    }

    public void loadScorePage(View view) {
        Intent score = new Intent(this,ScoreActivity.class);
        finish();
        startActivity(score);
    }

    public void start_new_game(View view) {
        EditText editTxt = (EditText)findViewById(R.id.playerName_editText);
        String name = editTxt.getText().toString();
        Intent newGame = new Intent(this,GameActivity.class);
        newGame.putExtra("name", name);
        finish();
        startActivity(newGame);
    }
    public void nameWatch() {
        final EditText txtName = (EditText) findViewById(R.id.playerName_editText);
        final Button btnStart = (Button) findViewById(R.id.start_button);

        TextWatcher nameWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (txtName.length() > 0) {
                    btnStart.setEnabled(true);
                } else btnStart.setEnabled(false);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        txtName.addTextChangedListener(nameWatcher);

        txtName.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (txtName.length() > 0) {
                        start_new_game(txtName);
                    }
                    return true;
                }
                return false;
            }
        });

    }
}
