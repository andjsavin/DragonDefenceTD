package com.dragon.defence.td.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragon.defence.td.Button;
import com.dragon.defence.td.IFont;

import java.nio.channels.spi.SelectorProvider;

public class SettingsView implements IFont {
    Rectangle r, slider, easy, medium, hard;
    Texture bg, soundBarTexture, soundSliderTexture, unchecked, checked;
    Button soundButton, difficultyButton, backButton;
    boolean sliderTouched = false;
    boolean e = false, m = true, h = false;
    BitmapFont font;

    public SettingsView(BitmapFont f) {
        font = f;
        r = new Rectangle(Gdx.graphics.getWidth()*0.15f,
                Gdx.graphics.getHeight()*0.15f,
                Gdx.graphics.getWidth()*0.7f,
                Gdx.graphics.getHeight()*0.7f);
        bg = new Texture("settings_bg.png");
        soundBarTexture = new Texture("sound_bar.png");
        soundSliderTexture = new Texture("sound_slider.png");
        soundButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        r.x + r.width*0.1f,
                        r.y + r.height*0.8f - r.width*0.1f,
                        r.width*0.3f,
                        r.width*0.1f
                ), "Sound", font, 0.2f);
        difficultyButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        r.x + r.width*0.1f,
                        r.y + r.height*0.8f - r.width*0.2f,
                        r.width*0.3f,
                        r.width*0.1f
                ), "Difficulty", font, 0.2f);
        backButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        r.x + r.width*0.395f,
                        r.y + r.height*0.15f,
                        r.width*0.21f,
                        r.width*0.07f
                ), "Back", font, 0.2f);
        slider = new Rectangle(r.x + r.width*0.85f - r.width*0.025f,
                r.y + r.height*0.8f - r.width*0.075f,
                r.width*0.05f, r.width*0.05f);
        easy = new Rectangle(r.x + r.width*0.5f,
                r.y + r.height*0.8f - r.width*0.175f,
                r.width*0.05f, r.width*0.05f);
        medium = new Rectangle(r.x + r.width*0.655f,
                r.y + r.height*0.8f - r.width*0.175f,
                r.width*0.05f, r.width*0.05f);
        hard = new Rectangle(r.x + r.width*0.813f,
                r.y + r.height*0.8f - r.width*0.175f,
                r.width*0.05f, r.width*0.05f);
        checked = new Texture("checkbox_checked.png");
        unchecked = new Texture("checkbox_empty.png");
    }

    public void draw(SpriteBatch batch) {
        batch.draw(bg, r.x, r.y, r.width, r.height);
        soundButton.draw(batch);
        backButton.draw(batch);
        difficultyButton.draw(batch);
        batch.draw(soundBarTexture, r.x + r.width*0.45f, r.y + r.height*0.8f - r.width*0.06f,
                r.width*0.4f, r.width*0.02f);
        batch.draw(soundSliderTexture, slider.x, slider.y, slider.width, slider.height);
        drawFont("E:", batch, font, 0.2f, r.x + r.width*0.465f, r.y + r.height*0.8f - r.width*0.15f);
        drawFont("M:", batch, font, 0.2f, r.x + r.width*0.61f, r.y + r.height*0.8f - r.width*0.15f);
        drawFont("H:", batch, font, 0.2f, r.x + r.width*0.775f, r.y + r.height*0.8f - r.width*0.15f);
        if (e) batch.draw(checked, easy.x, easy.y, easy.width, easy.height);
        else batch.draw(unchecked, easy.x, easy.y, easy.width, easy.height);
        if (m) batch.draw(checked, medium.x, medium.y, medium.width, medium.height);
        else batch.draw(unchecked, medium.x, medium.y, medium.width, medium.height);
        if (h) batch.draw(checked, hard.x, hard.y, hard.width, hard.height);
        else batch.draw(unchecked, hard.x, hard.y, hard.width, hard.height);
    }

    public void dispose() {
        soundBarTexture.dispose();
        soundSliderTexture.dispose();
        unchecked.dispose();
        bg.dispose();
        checked.dispose();
    }
}
