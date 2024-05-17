package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    Main main;
    float timeToStart=0.5f;

    public MenuScreen(Main main) {
        this.main = main;
    }
    Texture img= new Texture("badlogic.jpg");
    @Override
    public void show() {

    }

    @Override
    public void render (float delta) {
        timeToStart=timeToStart>-1?timeToStart-delta:-1;
        if(Gdx.input.justTouched()&&timeToStart<0){
            main.setScreen(new PulScreen(main));
        }
        ScreenUtils.clear(0, 1, 0, 1);
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
