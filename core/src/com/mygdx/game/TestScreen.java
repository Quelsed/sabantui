package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class TestScreen implements Screen {
    private int HEIGHT = Gdx.graphics.getHeight();
    BitmapFont font;
    private int WIDTH = Gdx.graphics.getWidth();
    Vocabulary v=new Vocabulary();
    private String[]vocabulary=v.getArray();
    int rand= (int) (Math.random()*vocabulary.length);
    Main main;
    public TestScreen(Main main) {
        this.main = main;
    }
    Texture img = new Texture("badlogic.jpg");

    Rectangle firstButton=new Rectangle(0,0,WIDTH/2f,HEIGHT/3f);
    Rectangle secondButton=new Rectangle(WIDTH/2f,0,WIDTH/2f,HEIGHT/3f);
    Rectangle thirdButton=new Rectangle(0,HEIGHT/3f,WIDTH/2f,HEIGHT/3f);
    Rectangle fourthButton=new Rectangle(WIDTH/2f,HEIGHT/3f,WIDTH/2f,HEIGHT/3f);

    Rectangle[] Buttons=new Rectangle[]{firstButton,secondButton,thirdButton,fourthButton};

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 1, 0, 1);
        main.batch.begin();
        for(int i=0;i<4;i++){
            if(Gdx.input.justTouched()&&Buttons[i].contains(Gdx.input.getX(),Gdx.input.getY())){

            }
        }
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
    public void dispose() {
        main.batch.dispose();
        img.dispose();
    }
}
