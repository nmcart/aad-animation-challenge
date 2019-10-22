package com.natacha.carthias.meditate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MeditateActivity extends AppCompatActivity implements View.OnClickListener {
    // Variables
    ImageView imgViewMeditate;
    private int imgUrl;
    private String txtMeditation;
    private static final long START_TIME = 600000; // 10 minutes
    TextView chronometer;
    CountDownTimer mCountDownTimer;
    long mTimeLeftInMillis = START_TIME;
    Handler mMainHandler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_meditate);

        // Reference to components in XML
        ImageView imgViewBanner = findViewById(R.id.img_meditateBanner);
        imgViewMeditate = findViewById(R.id.img_meditate);
        TextView tvMeditation = findViewById(R.id.tv_meditate);
        chronometer = findViewById(R.id.chronometer);

        // Retrieve which meditation was passed with intent
        Intent intent = getIntent();
        txtMeditation = intent.getStringExtra("Meditation");
        imgUrl = intent.getIntExtra("Image", imgUrl);

        // Set Image and Meditation
        imgViewBanner.setImageResource(imgUrl);
        tvMeditation.setText(txtMeditation);

        // Meditate Transition Animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


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

         }
     }.start();


     //Animate Meditation Cloud
     imgViewMeditate.setImageResource(R.drawable.avd_cloud);
     Drawable drawable = imgViewMeditate.getDrawable();
     if (drawable instanceof Animatable) {

         final AnimatedVectorDrawable avd = (AnimatedVectorDrawable) drawable;
         avd.start();

         // Loop Animation
         mMainHandler = new Handler(Looper.getMainLooper());
         avd.registerAnimationCallback(new Animatable2.AnimationCallback() {
             @Override
             public void onAnimationEnd(Drawable drawable) {
                 runnable = new Runnable() {
                     @Override
                     public void run() {

                         avd.start();
                     }
                 };
                 mMainHandler.post(runnable);
             }

         });
     }

 }

    private void pauseTimer() {
        mCountDownTimer.cancel();

        // Stop Meditation Cloud Animation
        imgViewMeditate.setImageResource(R.drawable.ic_meditate);

    }



    private void resetTimer() {
        mTimeLeftInMillis = START_TIME;
        mCountDownTimer.cancel();
        mCountDownTimer.start();
        mCountDownTimer.cancel();
        updateCountDown();

        // Stop Meditation Cloud Animation
        imgViewMeditate.setImageResource(R.drawable.ic_meditate);
    }

    private void updateCountDown() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        chronometer.setText(timeLeftFormatted);
    }
}
