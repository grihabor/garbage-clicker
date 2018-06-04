package ru.sibur.android.garbagecollector;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

/**
 * Главное меню
 *
 * + Играть
 * + Достижения
 * + Выход
 */

public class IntroActivity extends AppCompatActivity {
    private String TAG = "INTRO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_intro);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener((view) -> {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener((view) -> finish());

        Button achievementButton = findViewById(R.id.achievement_button);
        achievementButton.setOnClickListener((view) -> {
            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
            AchievementsClient client = Games.getAchievementsClient(this, account);
            Task<Intent> task = client.getAchievementsIntent();
            task.addOnSuccessListener(this::startActivity);
        });

        signIn();
    }

    void signIn() {
        GoogleSignInAccount testAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (testAccount == null) {
            GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder();
            GoogleSignInOptions options = builder.build();
            GoogleSignInClient client = GoogleSignIn.getClient(this, options);

            Task<GoogleSignInAccount> silentSignInTask = client.silentSignIn();
            silentSignInTask.addOnFailureListener(e -> {
                Log.e(TAG, e.getMessage());
                Intent signIntent = client.getSignInIntent();
                startActivity(signIntent);
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(signIntent);
                task.addOnSuccessListener(result -> Log.e(TAG, "Success"));
                task.addOnFailureListener(ex -> Log.e(TAG, ex.getMessage()));
            });
        }
    }
}
