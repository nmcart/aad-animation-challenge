package com.natacha.carthias.meditate;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ThemeSelectionActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_theme_selection);


        // Set on click listener to buttons
        findViewById(R.id.btn_light).setOnClickListener(this);
        findViewById(R.id.btn_dark).setOnClickListener(this);

        // Animation for buttons
        Button button1 = findViewById(R.id.btn_light);
        Button button2 = findViewById(R.id.btn_dark);
        int duration = 2000;
        ObjectAnimator.ofObject(button1, "backgroundColor", new ArgbEvaluator(), Color.parseColor("#CFA9FF"), Color.parseColor("#ABCEFF"))
                .setDuration(duration)
                .start();
        ObjectAnimator.ofObject(button2, "backgroundColor", new ArgbEvaluator(), Color.parseColor("#6C3EA0"), Color.parseColor("#7A8992"))
                .setDuration(duration)
                .start();


    }



        @Override
        public void onClick (View view){

            switch (view.getId()) {
                case R.id.btn_light:
                    // Animation for Light Button
                    int colourFrom = Color.parseColor("#CFA9FF");
                    int colourTo = Color.parseColor("#ABCEFF");
                    int duration = 1000;
                    ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), colourFrom, colourTo)
                            .setDuration(duration)
                            .start();

                    ThemeUtils.changeToTheme(this, ThemeUtils.LIGHT);

                    // ThemeSelection Transition Animation
                    overridePendingTransition(R.anim.fade_out,R.anim.fade_in);

                    startActivity(new Intent (ThemeSelectionActivity.this, InputFeelsActivity.class));
                    break;
                case R.id.btn_dark:
                    // Animation for Dark Button
                    colourFrom = Color.parseColor("#6C3EA0");
                    colourTo = Color.parseColor("#7A8992");
                    duration = 1000;
                    ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), colourFrom, colourTo)
                            .setDuration(duration)
                            .start();

                    ThemeUtils.changeToTheme(this, ThemeUtils.DARK);

                    // ThemeSelection Transition Animation
                    overridePendingTransition(R.anim.fade_out,R.anim.fade_in);

                    startActivity(new Intent (ThemeSelectionActivity.this, InputFeelsActivity.class));
                    break;
            }
        }

    @Override
    public void onBackPressed() {
        System.exit(1);
    }
}


