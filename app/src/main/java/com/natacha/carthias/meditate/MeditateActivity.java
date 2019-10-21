package com.natacha.carthias.meditate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MeditateActivity extends AppCompatActivity {
    Meditation meditation;
    private int imgUrl;
    private String txtMeditation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_meditate);

        // Reference to components in XML
        ImageView imgViewBanner = findViewById(R.id.img_meditateBanner);
        ImageView imgViewMeditate = findViewById(R.id.img_meditate);
        TextView tvMeditation = findViewById(R.id.tv_meditate);

        Intent intent = getIntent();
        txtMeditation = intent.getStringExtra("Meditation");
        imgUrl = intent.getIntExtra("Image", imgUrl);


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
        switch(item.getItemId()) {
            case R.id.menu_select:
                startActivity(new Intent (this, MeditationListActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
