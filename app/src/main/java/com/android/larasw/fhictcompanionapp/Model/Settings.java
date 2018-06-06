package com.android.larasw.fhictcompanionapp.Model;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.Image;

/**
 * Created by Res Non Verba on 14/09/2017.
 */

public class Settings {
    private int icon;
    private String name;

    public Settings(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
