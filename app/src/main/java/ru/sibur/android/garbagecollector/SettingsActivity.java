package ru.sibur.android.garbagecollector;


import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class SettingsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_settings);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        StateStorage storage = new StateStorage(this, Constant.PREF_NAME);

        CheckBox musicBox = findViewById(R.id.musicCheckBox);
        CheckBox soundBox = findViewById(R.id.soundCheckBox);
        musicBox.setChecked(storage.getMusicShouldBe());
        soundBox.setChecked(storage.getSoundsShouldBe());
        musicBox.setOnClickListener((v) -> storage.setMusicShouldBe(musicBox.isChecked()));

        soundBox.setOnClickListener((v) -> storage.setSoundsShouldBe(soundBox.isChecked()));


    }
}
