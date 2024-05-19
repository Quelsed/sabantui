package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    private int HEIGHT = Gdx.graphics.getHeight();
    private int WIDTH = Gdx.graphics.getWidth();
    Main main;
    float timeToStart = 0.5f;
    Rectangle ropeButton=new Rectangle(WIDTH/10f,5f*HEIGHT/9f-100,WIDTH/5f+200,HEIGHT/3f);
    Rectangle fishButton=new Rectangle(WIDTH*9/10f-WIDTH/5f,5f*HEIGHT/9f-100,WIDTH/5f,HEIGHT/3f);
    Rectangle potButton=new Rectangle(WIDTH*9/10f-WIDTH/5f,100,WIDTH/5f,HEIGHT/3f);
    Rectangle climberButton=new Rectangle(WIDTH/10f,HEIGHT/9f,WIDTH/5f,HEIGHT/3f);

    public MenuScreen(Main main) {
        this.main = main;
    }

    Texture field = new Texture("field.JPG");
    Texture rope = new Texture("rope.png");
    Texture fish = new Texture("field.JPG");
    Texture pot = new Texture("places/PotsGroup.png");
    Texture climber = new Texture("places/climberPlace.png");


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        timeToStart = timeToStart > -1 ? timeToStart - delta : -1;
        if(Gdx.input.justTouched() &&ropeButton.contains(Gdx.input.getX(), HEIGHT-Gdx.input.getY())) {
            if ( timeToStart < 0) {
                main.setScreen(new TestScreen(main,new PulScreen(main)));
            }
        }
        if(Gdx.input.justTouched() &&climberButton.contains(Gdx.input.getX(), HEIGHT-Gdx.input.getY())) {
            if ( timeToStart < 0) {
                main.setScreen(new TestScreen(main,new ClimberScreen(main)));
            }
        }
        if(Gdx.input.justTouched() &&fishButton.contains(Gdx.input.getX(), HEIGHT-Gdx.input.getY())) {
            if ( timeToStart < 0) {
                main.setScreen(new TestScreen(main,new FishScreen(main)));
            }
        }
        if(Gdx.input.justTouched() &&potButton.contains(Gdx.input.getX(), HEIGHT-Gdx.input.getY())) {
            if ( timeToStart < 0) {
                main.setScreen(new TestScreen(main,new PotScreen(main)));
            }
        }
        ScreenUtils.clear(0, 1, 0, 1);
        main.batch.begin();
        main.batch.draw(field, 0, 0,WIDTH,HEIGHT);
        main.batch.draw(rope, ropeButton.x, ropeButton.y,(ropeButton.width-200)*1.5f,ropeButton.height*2f);
        main.batch.draw(climber, climberButton.x, climberButton.y,climberButton.width,climberButton.height*1.5f);
        main.batch.draw(pot, potButton.x, potButton.y,potButton.width,potButton.height);
        main.batch.draw(fish, fishButton.x, fishButton.y,fishButton.width,fishButton.height);

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
        field.dispose();
    }


}
