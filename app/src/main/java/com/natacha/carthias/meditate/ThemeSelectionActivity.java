package com.natacha.carthias.meditate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


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

    }



        @Override
        public void onClick (View view){

            switch (view.getId()) {
                case R.id.btn_light:
                    ThemeUtils.changeToTheme(this, ThemeUtils.LIGHT);
                    startActivity(new Intent (ThemeSelectionActivity.this, InputFeelsActivity.class));
                    break;
                case R.id.btn_dark:
                    ThemeUtils.changeToTheme(this, ThemeUtils.DARK);
                    startActivity(new Intent (ThemeSelectionActivity.this, InputFeelsActivity.class));
                    break;
            }
        }


        }


