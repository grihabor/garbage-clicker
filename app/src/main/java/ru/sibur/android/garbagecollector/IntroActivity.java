package ru.sibur.android.garbagecollector;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
    private static final int SIGN_IN_REQUEST_CODE = 9001;
    private GoogleSignInClient client;

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


        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
        builder = builder.requestEmail();
        GoogleSignInOptions options = builder.build();
        client = GoogleSignIn.getClient(this, options);

        signIn();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    void signIn() {
        //GoogleSignInAccount testAccount = GoogleSignIn.getLastSignedInAccount(this);
        //if (testAccount == null) {
            //Task<GoogleSignInAccount> silentSignInTask = client.silentSignIn();
            //silentSignInTask.addOnFailureListener(e -> {
                //Log.e(TAG, e.getMessage());
                Intent signIntent = client.getSignInIntent();

                startActivityForResult(signIntent, SIGN_IN_REQUEST_CODE);
            //});
        //}
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            accountTask.addOnFailureListener(ex -> Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show());
            handleSignInResult(accountTask);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Toast.makeText(this, e.getStatusCode() + "code", Toast.LENGTH_SHORT).show();
        }
    }
}
