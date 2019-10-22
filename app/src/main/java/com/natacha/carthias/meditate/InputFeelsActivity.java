package com.natacha.carthias.meditate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class InputFeelsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgViewCalm;
    ImageView imgViewBoost;
    ImageView imgViewRest;
    Scene scene1;
    Scene scene2;
    Scene currentScene;
    ViewGroup sceneRoot;
    Transition transition;
    int click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_input_feels);


        imgViewCalm = findViewById(R.id.img_calm);
        imgViewBoost = findViewById(R.id.img_boost);
        imgViewRest = findViewById(R.id.img_rest);

        sceneRoot = (ViewGroup) findViewById(R.id.sceneRoot);


        // InputFeels Transition Animation
        scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, this);
        scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2,this);

        transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_in);

        scene1.enter();
        currentScene = scene1;
    }

    // Create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings_menu, menu);
        return true;
    }

    // Allow user input with menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_day:
                ThemeUtils.changeToTheme(this, ThemeUtils.LIGHT);
                startActivity(new Intent (this, this.getClass()));

                //Theme Change Fade Transition
                overridePendingTransition(R.anim.fade_out,  R.anim.fade_in);

                break;
            case R.id.menu_night:
                ThemeUtils.changeToTheme(this, ThemeUtils.DARK);
                startActivity(new Intent (this, this.getClass()));

                //Theme Change Fade Transition
                overridePendingTransition(R.anim.fade_out,  R.anim.fade_in);

                break;
            case R.id.menu_select:
                startActivity(new Intent (this, MeditationListActivity.class));

                // MeditationList Slide Transition Animation
                overridePendingTransition(R.anim.slide_in, R.anim.nothing);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Start Meditate Activity passing the relevant Meditation and Image
    @Override
    public void onClick(View view) {

        // InputFeels Slide Transition Animation on first click
        // Start Meditate Activity on second click
        switch (view.getId()) {
            case R.id.img_calm:
                Intent intent = new Intent(InputFeelsActivity.this, MeditateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Meditation", "Calming");
                bundle.putInt("Image",R.drawable.img_calming);
                intent.putExtras(bundle);
                if (currentScene == scene1) {
                    TransitionManager.go(scene2, transition);
                    currentScene = scene2;
                    click=click+1;}
                else if (currentScene == scene2) {
                    TransitionManager.go(scene1, transition);
                    currentScene = scene1;
                    click=click+1;
                }
                if (click == 2) {
                startActivity(intent);
                    click = 0;}
              break;
            case R.id.img_boost:
                intent = new Intent(InputFeelsActivity.this, MeditateActivity.class);
                bundle = new Bundle();
                bundle.putString("Meditation", "Boosting");
                bundle.putInt("Image",R.drawable.img_boosting);
                intent.putExtras(bundle);
                if (currentScene == scene1) {
                    TransitionManager.go(scene2, transition);
                    currentScene = scene2;
                    click=click+1;}
                else if (currentScene == scene2) {
                    TransitionManager.go(scene1, transition);
                    currentScene = scene1;
                    click=click+1;
                }
                if (click == 2) {
                    startActivity(intent);
                    click = 0;}
                break;
            case R.id.img_rest:
                intent = new Intent(InputFeelsActivity.this, MeditateActivity.class);
                bundle = new Bundle();
                bundle.putString("Meditation", "Restful");
                bundle.putInt("Image",R.drawable.img_restful);
                intent.putExtras(bundle);
                if (currentScene == scene1) {
                    TransitionManager.go(scene2, transition);
                    currentScene = scene2;
                    click=click+1;}
                else if (currentScene == scene2) {
                    TransitionManager.go(scene1, transition);
                    currentScene = scene1;
                    click=click+1;
                }
                if (click == 2) {
                    startActivity(intent);
                click = 0;}
                break;
        }

        }

    }

