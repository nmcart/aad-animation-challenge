package com.natacha.carthias.meditate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MeditateActivity extends AppCompatActivity implements View.OnClickListener {
    // Variables
    Meditation meditation;
    private int imgUrl;
    private String txtMeditation;
    private static final long START_TIME = 600000; // 10 minutes
    TextView chronometer;
    CountDownTimer mCountDownTimer;
    boolean mTimeRunning;
    long mTimeLeftInMillis = START_TIME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_meditate);

        // Reference to components in XML
        ImageView imgViewBanner = findViewById(R.id.img_meditateBanner);
        TextView tvMeditation = findViewById(R.id.tv_meditate);
        chronometer = findViewById(R.id.chronometer);



        // Retrieve which meditation was passed with intent
        Intent intent = getIntent();
        txtMeditation = intent.getStringExtra("Meditation");
        imgUrl = intent.getIntExtra("Image", imgUrl);

        // Set Image and Meditation
        imgViewBanner.setImageResource(imgUrl);
        tvMeditation.setText(txtMeditation);

    }

    // Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);

        // Hide Theme menu options
        menu.findItem(R.id.menu_day).setVisible(false);
        menu.findItem(R.id.menu_night).setVisible(false);
        this.invalidateOptionsMenu();

        return true;
    }

    // Allow user input with menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_select:
                startActivity(new Intent(this, MeditationListActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Assign methods to Chronometer buttons
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_start:
                    startTimer();
                break;
            case R.id.img_pause:
                pauseTimer();
                break;
            case R.id.img_reset:
                resetTimer();
                break;
        }
        updateCountDown();
    }

 //    Methods for Chronometer buttons
 private void startTimer() {
     mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
         @Override
         public void onTick(long millisUntilFinished) {
             mTimeLeftInMillis = millisUntilFinished;
             updateCountDown();
         }

         @Override
         public void onFinish() {
             mTimeRunning = false;
         }
     }.start();

     mTimeRunning = true;
 }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimeRunning = false;
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME;
        mCountDownTimer.cancel();
        updateCountDown();
    }

    private void updateCountDown() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        chronometer.setText(timeLeftFormatted);
    }
}
