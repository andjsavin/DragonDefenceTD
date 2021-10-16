package com.dragon.defence.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dragon.defence.td.screens.main.MainScreen;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    MainScreen ms;
    Preferences progressPrefs;


    @Override
    public void create() {
        batch = new SpriteBatch();
        progressPrefs = Gdx.app.getPreferences("Progress");
        ms = new MainScreen(new BitmapFont(Gdx.files.internal("font.fnt"),
                Gdx.files.internal("font.png"), false),
                new BitmapFont(Gdx.files.internal("font2.fnt"),
                        Gdx.files.internal("font2.png"), false));
        progressPrefs.putBoolean("Main Menu", true);
        for (int i = 1; i < 42; i++) {
            progressPrefs.putBoolean("Level" + i, false);
        }
        progressPrefs.flush();
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        batch.begin();

        if (progressPrefs.getBoolean("Main Menu", true)) ms.draw(batch);
        for (int i = 1; i < 42; i++) {
            if (progressPrefs.getBoolean("Level" + i, false)) ms.draw(batch);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
