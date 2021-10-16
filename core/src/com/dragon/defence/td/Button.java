package com.dragon.defence.td;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Button implements IFont {
    Texture textureDefault, texturePressed, texture;

    public Rectangle getR() {
        return r;
    }

    Rectangle r;
    String s;
    BitmapFont f;
    float scl;
    boolean state = false;

    public Button(Texture def, Texture press, Rectangle rec, String str, BitmapFont font, float scale) {
        r = rec;
        s = str;
        f = font;
        scl = scale;
        textureDefault = def;
        texturePressed = press;
        texture = textureDefault;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, r.x, r.y, r.width, r.height);
        drawFont(s, batch, f, scl, r.x + r.width*0.5f, r.y + r.height*0.5f);
    }

    public void setState(boolean s) {
        texture = s ? texturePressed : textureDefault;
        state = s;
    }

    public void dispose() {
        textureDefault.dispose();
        texturePressed.dispose();
        texture.dispose();
    }
}
