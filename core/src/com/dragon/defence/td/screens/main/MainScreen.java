package com.dragon.defence.td.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragon.defence.td.Button;
import com.dragon.defence.td.IFont;

public class MainScreen implements IFont, InputProcessor {
    Texture background;
    BitmapFont font, font2;
    Button newGameButton, settingsButton, chooseButton;
    BigDragon bigDragon;
    Music bigDragonFlap;
    boolean settings = false, chooseLevel = false;
    float sound = 1.0f;
    Preferences settingsPrefs;
    Preferences progressPrefs;
    SettingsView sv;

    public MainScreen(BitmapFont f1, BitmapFont f2) {
        font = f1;
        font2 = f2;
        progressPrefs = Gdx.app.getPreferences("Progress");
        int currentLevel = progressPrefs.getInteger("Current", 0);
        String gameText = "New Game";
        if (currentLevel > 0) gameText = "Continue";
        newGameButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        Gdx.graphics.getWidth()*0.03f,
                        Gdx.graphics.getHeight()*0.1f,
                        Gdx.graphics.getWidth()*0.3f,
                        Gdx.graphics.getWidth()*0.1f
                ),
                gameText, font2, 0.2f);
        settingsButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        Gdx.graphics.getWidth()*0.35f,
                        Gdx.graphics.getHeight()*0.1f,
                        Gdx.graphics.getWidth()*0.3f,
                        Gdx.graphics.getWidth()*0.1f
                ),
                "Settings", font2, 0.2f);
        chooseButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        Gdx.graphics.getWidth()*0.67f,
                        Gdx.graphics.getHeight()*0.1f,
                        Gdx.graphics.getWidth()*0.3f,
                        Gdx.graphics.getWidth()*0.1f
                ),
                "Choose level", font2, 0.2f);
        bigDragon = new BigDragon();
        background = new Texture("bg.png");
        bigDragonFlap = Gdx.audio.newMusic(Gdx.files.internal("sounds/big_dragon_wing_flap.wav"));
        settingsPrefs = Gdx.app.getPreferences("Global Settings");
        bigDragonFlap.setLooping(true);
        bigDragonFlap.setVolume(settingsPrefs.getFloat("sound", 1.0f));
        bigDragonFlap.play();
        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchKey(Input.Keys.BACK, true);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(background, 0, -1, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 1);
        if (!settings && !chooseLevel) {
            bigDragon.draw(batch);
            drawFont("Dragon", batch, font, 1.0f, Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.9f);
            drawFont("Defence", batch, font, 1.0f, Gdx.graphics.getWidth() * 0.5f, Gdx.graphics.getHeight() * 0.65f);
            newGameButton.draw(batch);
            settingsButton.draw(batch);
            chooseButton.draw(batch);
        }
        if (settings) sv.draw(batch);
    }

    public void dispose() {
        newGameButton.dispose();
        settingsButton.dispose();
        chooseButton.dispose();
        background.dispose();
        bigDragonFlap.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        newGameButton.setState(newGameButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        settingsButton.setState(settingsButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        chooseButton.setState(chooseButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        if (settings) {
            sv.backButton.setState(sv.backButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
            if (sv.slider.contains(screenX, Gdx.graphics.getHeight() - screenY)) sv.sliderTouched = true;
            if (sv.easy.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                sv.e = true;
                sv.m = false;
                sv.h = false;
                settingsPrefs.putInteger("Difficulty", 1);
                settingsPrefs.flush();
            }
            if (sv.medium.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                sv.e = false;
                sv.m = true;
                sv.h = false;
                settingsPrefs.putInteger("Difficulty", 2);
                settingsPrefs.flush();
            }
            if (sv.hard.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                sv.e = false;
                sv.m = false;
                sv.h = true;
                settingsPrefs.putInteger("Difficulty", 3);
                settingsPrefs.flush();
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        newGameButton.setState(false);
        settingsButton.setState(false);
        chooseButton.setState(false);
        if (settingsButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY) && !settings && !chooseLevel) {
            sv = new SettingsView(font2);
            sv.slider.x = settingsPrefs.getFloat("sound", 1.0f)*sv.r.width*0.4f -
                    sv.slider.width*0.5f + sv.r.x + sv.r.width*0.425f;
            settings = true;
            bigDragonFlap.stop();
        }
        if (settings) {
            if (sv.sliderTouched) {
                sv.sliderTouched = false;
                float vol = (sv.slider.x + sv.slider.width*0.5f - sv.r.x - sv.r.width*0.425f)/(sv.r.width*0.4f);
                if (vol > 1) vol = 1f;
                settingsPrefs.putFloat("sound", vol);
                settingsPrefs.flush();
            }
            if (sv.backButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                bigDragonFlap.setVolume(settingsPrefs.getFloat("sound", 1.0f));
                bigDragonFlap.play();
                sv.dispose();
                settings = false;
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        newGameButton.setState(newGameButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        settingsButton.setState(settingsButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        chooseButton.setState(chooseButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        if (settings) {
            sv.backButton.setState(sv.backButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
            if (sv.sliderTouched && screenX <= sv.r.x + sv.r.width*0.825f && screenX >= sv.r.x + sv.r.width*0.425f)
                sv.slider.x = screenX;
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
