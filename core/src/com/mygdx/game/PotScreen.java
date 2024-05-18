package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;

public class PotScreen implements Screen {
    Main main;

    public PotScreen(Main main) {
        this.main = main;
    }
    Texture pot, background;

    float freeze = 0.5f;
    int cord_x = 0, cord_y = 0;
    Rectangle potik = new Rectangle((int) cord_x, (int) cord_y, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
    boolean ris = true;
    float c1, c2;
    @Override
    public void show() {
        pot = new Texture("POT-Photoroom.png-Photoroom.png");
        background = new Texture("potsfield.JPG");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,1,1,1);
        main.batch.begin();
        main.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (ris&&freeze>0)
            main.batch.draw(pot, potik.x, potik.y, potik.width, potik.height);
        freeze-=delta;
        if(freeze<=0) {
            potik.x = (int) (Math.random() * (Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/4));
            potik.y = (int) (Math.random() * (Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/4));

        }
        if (Gdx.input.isTouched() && potik.contains(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())) {
            ris = false;
        }
        if(freeze<=0) ris=true;
        c1 = (float) (Math.random() * 0.9 + 0.4);
        c2 = (float) (Math.random() * 1.5);
        freeze=freeze<-c2?c1:freeze;
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
    public void dispose() {
        main.batch.dispose();
    }
}
