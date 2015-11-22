package csci3320.defuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
    }

    public void loadWelcomePage(View view) {
        Intent welcome = new Intent(this,WelcomeActivity.class);
        startActivity(welcome);
        finish();
    }
}
