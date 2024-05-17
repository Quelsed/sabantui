package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class PulScreen implements Screen {
    private int HEIGHT = Gdx.graphics.getHeight();
    private int WIDTH = Gdx.graphics.getWidth();
    Main main;
    public PulScreen(Main main) {
        this.main = main;
    }
    Texture ropeImg= new Texture("rope.png");
    Texture background= new Texture("background.png");
    Texture numberOne= new Texture("Numbers/one.png");
    Texture numberTwo= new Texture("Numbers/two.png");
    Texture numberThree= new Texture("Numbers/three.png");
    Texture winWindow= new Texture("win.png");
    Texture loseWindow= new Texture("lose.png");
    Texture GO= new Texture("Numbers/go.png");
    Rectangle rope=new Rectangle(WIDTH/4f,0,WIDTH/2f,HEIGHT/1.3f);
    float timer=0;
    float startTimer=3;
    boolean stop=false;
    float timeToEnd=0.5f;
    @Override
    public void show() {

    }
    @Override
    public void render (float delta) {
        startTimer=startTimer>-1?startTimer-delta:-1;
        main.batch.begin();
        main.batch.draw(background, 0,0,WIDTH,HEIGHT);
        main.batch.draw(ropeImg, rope.x,rope.y,rope.width,rope.height);
        if(startTimer>2){
            main.batch.draw(numberThree, WIDTH/2f-WIDTH/12f,2*HEIGHT/3f-WIDTH/12f,WIDTH/6f,WIDTH/6f);
        }
        else if(2>=startTimer&&startTimer>1){
            main.batch.draw(numberTwo, WIDTH/2f-WIDTH/12f,2*HEIGHT/3f-WIDTH/12f,WIDTH/6f,WIDTH/6f);
        }
        else if(1>=startTimer&&startTimer>0){
            main.batch.draw(numberOne, WIDTH/2f-WIDTH/12f,2*HEIGHT/3f-WIDTH/12f,WIDTH/6f,WIDTH/6f);
        }
        else if(0>startTimer&&startTimer>-1){
            main.batch.draw(GO, WIDTH/2f-WIDTH/12f,2*HEIGHT/3f-WIDTH/12f,WIDTH/6f,WIDTH/6f);
        }
        if(rope.x<WIDTH/8f) {
            timeToEnd=timeToEnd>-1?timeToEnd-delta:-1;
            stop = true;
            if (Gdx.input.justTouched()&&timeToEnd<0) {
                main.setScreen(new MenuScreen(main));
            }
            main.batch.draw(winWindow, WIDTH/2f-WIDTH/12f,2*HEIGHT/3f-HEIGHT/12f,WIDTH/6f,HEIGHT/6f);
        }
        if(rope.x+rope.width>7*WIDTH/8f) {
            timeToEnd=timeToEnd>-1?timeToEnd-delta:-1;
            stop = true;
            if (Gdx.input.justTouched()&&timeToEnd<0) {
                main.setScreen(new MenuScreen(main));
            }
            main.batch.draw(loseWindow,WIDTH/2f-WIDTH/12f,2*HEIGHT/3f-HEIGHT/12f,WIDTH/6f,HEIGHT/6f);
        }
        if(startTimer==-1&&!stop){
            timer += delta;
            if (timer >= 0.3f) {
                timer = 0;
                rope.x += WIDTH / 200f;
            }
            if (Gdx.input.justTouched()) rope.x -= WIDTH / 200f;
        }
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
        ropeImg.dispose();
        background.dispose();
        numberOne.dispose();
        numberTwo.dispose();
        numberThree.dispose();
        winWindow.dispose();
        loseWindow.dispose();
        GO.dispose();
    }
}
