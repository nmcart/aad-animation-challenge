package com.natacha.carthias.meditate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class InputFeelsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgViewCalm;
    ImageView imgViewBoost;
    ImageView imgViewRest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_input_feels);


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
                break;
            case R.id.menu_night:
                ThemeUtils.changeToTheme(this, ThemeUtils.DARK);
                startActivity(new Intent (this, this.getClass()));
                break;
            case R.id.menu_select:
                startActivity(new Intent (this, MeditationListActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Start Meditate Activity passing the relevant Meditation and Image
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_calm:
                Intent intent = new Intent(InputFeelsActivity.this, MeditateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Meditation", "Calming");
                bundle.putInt("Image",R.drawable.img_calming);
                intent.putExtras(bundle);
                startActivity(intent);
              break;
            case R.id.img_boost:
                Intent intent1 = new Intent(InputFeelsActivity.this, MeditateActivity.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("Meditation", "Boosting");
                bundle1.putInt("Image",R.drawable.img_boosting);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.img_rest:
                Intent intent2 = new Intent(InputFeelsActivity.this, MeditateActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("Meditation", "Restful");
                bundle2.putInt("Image",R.drawable.img_restful);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
        }

    }
}
