package com.dragon.defence.td.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
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
    long flapId;
    boolean flapping = false;

    public MainScreen(BitmapFont f1, BitmapFont f2) {
        font = f1;
        font2 = f2;
        newGameButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        Gdx.graphics.getWidth()*0.03f,
                        Gdx.graphics.getHeight()*0.1f,
                        Gdx.graphics.getWidth()*0.3f,
                        Gdx.graphics.getWidth()*0.1f
                ),
                "New Game", font2, 0.2f);
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
        bigDragonFlap.setLooping(true);
        bigDragonFlap.play();
        Gdx.input.setInputProcessor(this);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(background, 0, -1, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() + 1);
        bigDragon.draw(batch);
        drawFont("Dragon", batch, font, 1.0f, Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight()*0.9f);
        drawFont("Defence", batch, font, 1.0f, Gdx.graphics.getWidth()*0.5f, Gdx.graphics.getHeight()*0.65f);
        newGameButton.draw(batch);
        settingsButton.draw(batch);
        chooseButton.draw(batch);
    }

    public void dispose() {
        newGameButton.dispose();
        settingsButton.dispose();
        chooseButton.dispose();
        font.dispose();
        background.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        newGameButton.setState(newGameButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        settingsButton.setState(settingsButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
        chooseButton.setState(chooseButton.getR().contains(screenX, Gdx.graphics.getHeight() - screenY));
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
