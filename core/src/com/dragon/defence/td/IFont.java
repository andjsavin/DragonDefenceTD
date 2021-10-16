package com.dragon.defence.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IFont {
    default void drawFont(String text, SpriteBatch batch, BitmapFont font, float scale, float x, float y) {
        font.getData().setScale(scale);
        final GlyphLayout layout = new GlyphLayout(font, text);
        font.draw(batch, layout, x - layout.width * 0.5f, y + layout.height * 0.5f);
    }
}
