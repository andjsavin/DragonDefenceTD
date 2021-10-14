package com.dragon.defence.td.screens.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class BigDragon {
    Texture[] left, right;
    Rectangle rect;
    int state = 1;
    float velocity = 300f;
    int fps = 0;
    int cur = 0;

    public BigDragon() {
        left = new Texture[]{
                new Texture("dragonLeft1.png"),
                new Texture("dragonLeft2.png"),
                new Texture("dragonLeft3.png"),
                new Texture("dragonLeft4.png"),
                new Texture("dragonLeft5.png"),
                new Texture("dragonLeft6.png"),
                new Texture("dragonLeft7.png"),
                new Texture("dragonLeft8.png")
        };
        right = new Texture[]{
                new Texture("dragonRight1.png"),
                new Texture("dragonRight2.png"),
                new Texture("dragonRight3.png"),
                new Texture("dragonRight4.png"),
                new Texture("dragonRight5.png"),
                new Texture("dragonRight6.png"),
                new Texture("dragonRight7.png"),
                new Texture("dragonRight8.png"),
        };
        rect = new Rectangle(
                Gdx.graphics.getWidth(),
                (Gdx.graphics.getHeight() + Gdx.graphics.getWidth()) * 0.1f,
                Gdx.graphics.getWidth() * 0.410f, Gdx.graphics.getWidth() * 0.322f
        );
    }

    public void draw(SpriteBatch batch) {
        Texture[] t = state == 1 ? left : right;
        if (cur > 7) cur = 0;
        if (rect.x > Gdx.graphics.getWidth()) state = -1;
        else if (rect.x < -rect.width) state = 1;
        batch.draw(t[cur], rect.x, rect.y, rect.width, rect.height);
        rect.x += Gdx.graphics.getDeltaTime()*velocity*state;
        if (fps % 10 == 0) cur++;
        fps++;
    }

    public void dispose() {
        for (int i = 0; i < 8; i++) {
            left[i].dispose();
            right[i].dispose();
        }
    }
}
