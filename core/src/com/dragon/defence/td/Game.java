package com.dragon.defence.td;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dragon.defence.td.screens.main.MainScreen;

public class Game extends ApplicationAdapter{
	SpriteBatch batch;
	MainScreen ms;
	BitmapFont font;
	Boolean[] screens = new Boolean[41];

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		ms = new MainScreen(new BitmapFont(Gdx.files.internal("font.fnt"),
				Gdx.files.internal("font.png"), false),
				new BitmapFont(Gdx.files.internal("font2.fnt"),
						Gdx.files.internal("font2.png"), false));
		screens[0] = true;
		for (int i = 1; i < 41; i++) {
			screens[i] = false;
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();

		if (screens[0]) ms.draw(batch);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
