package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    Main main;

    public MenuScreen(Main main) {
        this.main = main;
    }
    Texture img= new Texture("badlogic.jpg");
    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        ScreenUtils.clear(1, 0, 0, 1);
        main.batch.begin();
        main.batch.draw(img, 0, 0);
        main.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        main.batch.dispose();
        img.dispose();
    }


}
