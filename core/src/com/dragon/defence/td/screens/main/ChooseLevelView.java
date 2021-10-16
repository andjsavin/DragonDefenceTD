package com.dragon.defence.td.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.dragon.defence.td.Button;
import com.dragon.defence.td.IFont;

public class ChooseLevelView implements IFont {
    Texture bg, leftTexture, rightTexture, lock;
    Button[] levelButtons;
    Button backButton;
    BitmapFont font;
    Rectangle r, left, right;
    int curPage = 0;
    Preferences progressPrefs;
    int curLevel;

    public ChooseLevelView(BitmapFont f) {
        progressPrefs = Gdx.app.getPreferences("Progress");
        curLevel = progressPrefs.getInteger("Current", 0);
        bg = new Texture("settings_bg.png");
        lock = new Texture("lock.png");
        font = f;
        r = new Rectangle(Gdx.graphics.getWidth() * 0.15f,
                Gdx.graphics.getHeight() * 0.15f,
                Gdx.graphics.getWidth() * 0.7f,
                Gdx.graphics.getHeight() * 0.7f);
        backButton = new Button(new Texture("default_button.png"),
                new Texture("pressed_button.png"),
                new Rectangle(
                        r.x + r.width * 0.395f,
                        r.y + r.height * 0.15f,
                        r.width * 0.21f,
                        r.width * 0.07f
                ), "Back", font, 0.2f);
        leftTexture = new Texture("left_arrow.png");
        rightTexture = new Texture("right_arrow.png");
        left = new Rectangle(r.x + r.width * 0.15f,
                r.y + r.height * 0.15f, r.width * 0.14f, r.width * 0.07f);
        right = new Rectangle(r.x + r.width * 0.71f,
                r.y + r.height * 0.15f, r.width * 0.14f, r.width * 0.07f);
        levelButtons = new Button[41];
        int counter = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    counter++;
                    if (counter == 42) break;
                    levelButtons[counter - 1] = new Button(new Texture("default_button.png"),
                            new Texture("pressed_button.png"),
                            new Rectangle(
                                    r.x + r.width * 0.1f + k * r.width * 0.28f,
                                    r.y + r.height * 0.8f - r.width * 0.1f - j * r.width * 0.08f,
                                    r.width * 0.24f,
                                    r.width * 0.08f
                            ), "Level " + counter, font, 0.2f);
                }
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(bg, r.x, r.y, r.width, r.height);
        if (curPage != 0) batch.draw(leftTexture, left.x, left.y, left.width, left.height);
        if (curPage != 6) batch.draw(rightTexture, right.x, right.y, right.width, right.height);
        if (curPage != 6) {
            for (int i = 0; i < 6; i++) {
                levelButtons[6 * curPage + i].draw(batch);
                if (6 * curPage + i + 1 > curLevel) {
                    batch.draw(lock,
                            levelButtons[6 * curPage + i].getR().x + levelButtons[6 * curPage + i].getR().width * 0.5f - r.width * 0.018f,
                            levelButtons[6 * curPage + i].getR().y + levelButtons[6 * curPage + i].getR().height * 0.5f - r.width * 0.018f * 50f / 37f,
                            r.width * 0.036f, r.width * 0.036f * 50f / 37f);
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                levelButtons[6 * curPage + i].draw(batch);
                if (6 * curPage + i + 1 > curLevel) {
                    batch.draw(lock,
                            levelButtons[6 * curPage + i].getR().x + levelButtons[6 * curPage + i].getR().width * 0.5f - r.width * 0.018f,
                            levelButtons[6 * curPage + i].getR().y + levelButtons[6 * curPage + i].getR().height * 0.5f - r.width * 0.018f * 50f / 37f,
                            r.width * 0.036f, r.width * 0.036f * 50f / 37f);
                }
            }
        }
        backButton.draw(batch);
    }

    public void dispose() {
        bg.dispose();
        backButton.dispose();
        leftTexture.dispose();
        rightTexture.dispose();
        lock.dispose();
        for (Button levelButton : levelButtons) {
            levelButton.dispose();
        }
    }
}
