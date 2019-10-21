package com.natacha.carthias.meditate;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Natacha Carthias.
 */

public class ThemeUtils {

    private static int Theme;

    public final static int LIGHT = 0;
    public final static int DARK = 1;


    public static void changeToTheme(Activity activity, int theme) {

    Theme = theme;
    activity.finish();
    activity.startActivity(new Intent(activity, activity.getClass()));

    }

    public static void onActivityCreateSetTheme(Activity activity) {
        switch (Theme) {
            default:
            case LIGHT:
                activity.setTheme(R.style.Light);
                break;
            case DARK:
                activity.setTheme(R.style.Dark);
                break;
        }
    }
    }
