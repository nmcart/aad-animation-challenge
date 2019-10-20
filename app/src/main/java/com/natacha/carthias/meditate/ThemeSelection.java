package com.natacha.carthias.meditate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.UiModeManager.MODE_NIGHT_YES;

public class ThemeSelection extends AppCompatActivity {

    Button btn1 = findViewById(R.id.btn_dark);
    Button btn2 = findViewById(R.id.btn_light);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_selection);

    }

    public void ChangeTheme(View view) {
        switch (view.getId())
        {
            case R.id.btn_dark:
                setTheme(R.style.Dark);
                recreate();
                setContentView(view);
            case R.id.btn_light:
                setTheme(R.style.Light);
                recreate();
                setContentView(view);
                break;
        }
        finish();
        startActivity(new Intent(this, getClass()));
    }
}
