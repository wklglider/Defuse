package csci3320.defuse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    public ArrayList<DatabaseOperations.UserScore> highScores;
    DatabaseOperations.UserScoreAdapter adapter;

    //final StableArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_score);

        DatabaseOperations dbOp = new DatabaseOperations(this);
        highScores = dbOp.getInformation();


        adapter = dbOp.new UserScoreAdapter(this,R.layout.listview_item_row,highScores);

        ListView scoresListView = (ListView) findViewById(R.id.listView);

        TextView scoresHeaderView = new TextView(this);

        //Set header
        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row,null);
        scoresListView.addHeaderView(header);

        //Set footer

        //Set adapter
        scoresListView.setAdapter(adapter);


    }

    public void loadWelcomePage(View view) {
        Intent welcome = new Intent(this,WelcomeActivity.class);
        finish();
        startActivity(welcome);
    }
}
