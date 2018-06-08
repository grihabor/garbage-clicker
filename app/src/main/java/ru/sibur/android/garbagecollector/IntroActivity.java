package ru.sibur.android.garbagecollector;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
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
    GoogleSignInClient signInClient;
    public static final int RC_SIGN_IN = 1488;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_intro);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setUI(GoogleSignIn.getLastSignedInAccount(this));

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                setUI(account);
            } catch (ApiException e) {
                Log.e(TAG, e.getStatusCode() + " code");
                Toast.makeText(this, "google sign in fail", Toast.LENGTH_LONG).show();
            }

        }
    }

    void setUI(GoogleSignInAccount account) {
        Button playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener((view) -> {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Button exitButton = findViewById(R.id.exit_button);
        exitButton.setOnClickListener((view) -> finish());

        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener((view)->{
            Intent intent = new Intent(IntroActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
        /*
        Button achievementButton = findViewById(R.id.achievement_button);
        achievementButton.setOnClickListener((view) -> {
            AchievementsClient client = Games.getAchievementsClient(this, account);
            Task<Intent> task = client.getAchievementsIntent();
            task.addOnSuccessListener(this::startActivity);
        });
        */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this, gso);

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener((view) -> {
            Intent signInIntent = signInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        if (account != null) {
            signInButton.setVisibility(View.GONE);
            achievementButton.setVisibility(View.VISIBLE);
        } else {
            signInButton.setVisibility(View.VISIBLE);
            achievementButton.setVisibility(View.GONE);
        }

    }
}
