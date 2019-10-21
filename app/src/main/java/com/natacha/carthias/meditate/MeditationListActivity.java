package com.natacha.carthias.meditate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MeditationListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load theme
        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_meditation_list);


        // Set up the RecyclerView
        RecyclerView mRecyclerView  = findViewById(R.id.rv_meditations);
        mRecyclerView.setAdapter(new MeditationAdapter(this,getData()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList getData() {

        ArrayList<Meditation> meditationsList = new ArrayList<>();

        // Data to populate RecyclerView use Meditation class
        Meditation meditation = new Meditation();
        meditation.setMeditation("Calming");
        meditation.setImage(R.drawable.img_calming);
        meditationsList.add(meditation);

        meditation = new Meditation();
        meditation.setMeditation("Boosting");
        meditation.setImage(R.drawable.img_boosting);
        meditationsList.add(meditation);;

        meditation = new Meditation();
        meditation.setMeditation("Restful");
        meditation.setImage(R.drawable.img_restful);
        meditationsList.add(meditation);

        return meditationsList;
    }


    // Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);

        // Hide Select Meditation menu option
        MenuItem item = menu.findItem(R.id.menu_select);
        item.setVisible(false);
        this.invalidateOptionsMenu();

        return true;
    }

    // Allow user input with menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_day:
                ThemeUtils.changeToTheme(this, ThemeUtils.LIGHT);
                startActivity(new Intent(this, this.getClass()));
                break;
            case R.id.menu_night:
                ThemeUtils.changeToTheme(this, ThemeUtils.DARK);
                startActivity(new Intent (this, this.getClass()));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
