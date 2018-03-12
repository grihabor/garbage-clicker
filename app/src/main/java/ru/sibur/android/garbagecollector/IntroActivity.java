package ru.sibur.android.garbagecollector;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class IntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        setContentView(R.layout.activity_intro);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                startActivity(intent);

            }
        }));

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }));

        Button achievementButton = findViewById(R.id.achievement_button);
        achievementButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity.this, AchievementActivity.class);
                startActivity(intent);

            }
        }));

    }



}
