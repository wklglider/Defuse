package csci3320.defuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class FinishActivity extends AppCompatActivity {

    int status;

    public FinishActivity(int status) {
        this.status = status;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_finish);
        if (status == 1) {
            View background = findViewById(R.id.finishBackground);
            background.setBackgroundColor(0xffffff);
            ImageView imageView = (ImageView) findViewById(R.id.gameEndsImage);
            imageView.setImageResource(R.drawable.bg_game_win);
        } else {
            View background = findViewById(R.id.finishBackground);
            background.setBackgroundColor(0x000000);
            ImageView imageView = (ImageView) findViewById(R.id.gameEndsImage);
            imageView.setImageResource(R.drawable.bg_game_over);
        }
    }
}
