package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class FishScreen implements Screen {
    Rectangle rectangle_timer=new Rectangle(Gdx.graphics.getWidth()/4f,8*Gdx.graphics.getHeight()/9f,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/9f);
    Rectangle getRectangle_timer_green=new Rectangle(Gdx.graphics.getWidth()/4f*1.05f,8*Gdx.graphics.getHeight()/9f+Gdx.graphics.getHeight()/90f,Gdx.graphics.getWidth()/2f-Gdx.graphics.getWidth()/40f,Gdx.graphics.getHeight()/9f-Gdx.graphics.getHeight()/45f);
    Rectangle retryButton=new Rectangle(Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/2f-Gdx.graphics.getHeight()/6f,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/3f);
    Texture retry=new Texture("Buttons/Rectangle_grey.png");
    Main main;
    int stage = (int) (Math.random() * 3);
    Texture rightArm = new Texture("hand-Photoroom 1.png");
    Texture leftArm = new Texture("hand-Photoroom 2.png");
    Texture normalFish = new Texture("fish.png");
    Texture diedFish = new Texture("fishDied.png");
    GlyphLayout layout = new GlyphLayout();
    Texture rectTimer=new Texture("Rectangle_timer.png");
    Texture rectTimerGreen=new Texture("Rectangle_timer_green.png");
    Rectangle fish = new Rectangle(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    float timer = 2;
    float rightTimer = -1, leftTimer = -1;
    float gameTimer = 20;
    int firstScore = 0, secondScore = 0;

    public FishScreen(Main main) {
        this.main = main;
    }
    BitmapFont font;
    BitmapFont font1;

    @Override
    public void show() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Tantular/Tantular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюяАӘБВГДЕЁЖҖЗИЙКЛМНҢОӨПРСТУҮФХҺЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        parameter.size = 60;
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
        generator.dispose();
        FreeTypeFontGenerator generator1 = new FreeTypeFontGenerator(Gdx.files.internal("Tantular/Tantular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.characters = "аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюяАӘБВГДЕЁЖҖЗИЙКЛМНҢОӨПРСТУҮФХҺЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        parameter1.size = 150;
        parameter1.borderColor = Color.BLACK;
        parameter1.color = Color.BLACK;
        parameter1.borderWidth = 3;
        font1 = generator1.generateFont(parameter1);
        generator.dispose();
    }

    @Override
    public void render(float delta) {
        gameTimer-=delta;
        if(gameTimer<0)gameTimer=0;
        timer -= delta;
        if (timer < 0&&gameTimer>0) {
            stage = (int) (Math.random() * 3);
            timer = 2;
        }
        ScreenUtils.clear(1, 1, 1, 1);
        main.batch.begin();
        if (gameTimer>0&&leftTimer<0&&rightTimer<0&&Gdx.input.justTouched() ) {
            if (Gdx.input.getX() <= Gdx.graphics.getWidth() / 2) {
                timer = 0;
                firstScore += stage != 0 ? 1 : -1;
                leftTimer = 0.1f;
            } else {
                timer = 0;
                secondScore += stage != 0 ? 1 : -1;
                rightTimer = 0.1f;
            }
        }
        if (stage != 0&&rightTimer<0&&leftTimer<0&&timer<1.9f) {
            main.batch.draw(normalFish, fish.x, fish.y, fish.width, fish.height);
        } else if(stage==0&&rightTimer<0&&leftTimer<0&&timer<1.9f){
            main.batch.draw(diedFish, fish.x, fish.y, fish.width, fish.height);
        }
        if (rightTimer >= 0) {
            rightTimer -= delta;
            main.batch.draw(rightArm, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);
        }
        if (leftTimer >= 0) {
            leftTimer -= delta;
            main.batch.draw(leftArm, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 3);

        }
        if(Gdx.input.justTouched()&&gameTimer<=0&&retryButton.contains(Gdx.input.getX(),Gdx.input.getY()))main.setScreen(new MenuScreen(main));

        if(gameTimer<=0) {
            main.batch.draw(retry, retryButton.x, retryButton.y, retryButton.width, retryButton.height);
            layout.setText(font,"Нажмите чтобы выйти в главное меню");
            font.draw(main.batch,"Нажмите чтобы выйти в главное меню", retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/4f+layout.height/2);
            if(firstScore>secondScore){
                layout.setText(font1,"Победил игрок 1");
                font1.draw(main.batch,"Победил игрок 1",retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/2f+layout.height/2);
            }
            if(secondScore>firstScore){

                    layout.setText(font1,"Победил игрок 2");
                    font1.draw(main.batch,"Победил игрок 2",retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/2f+layout.height/2);

            }
            if(firstScore==secondScore){
                layout.setText(font1,"Ничья");
                font1.draw(main.batch,"Ничья",retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/2f+layout.height/2);
            }
            }
        firstScore=firstScore<0?0:firstScore;
        secondScore=secondScore<0?0:secondScore;
        main.batch.draw(rectTimer,rectangle_timer.x,rectangle_timer.y,rectangle_timer.width,rectangle_timer.height);
        main.batch.draw(rectTimerGreen,getRectangle_timer_green.x,getRectangle_timer_green.y,gameTimer/20f*getRectangle_timer_green.width,getRectangle_timer_green.height);
        layout.setText(font,Integer.toString(firstScore));
        font.draw(main.batch,Integer.toString(firstScore),retryButton.x+retryButton.width/2f-layout.width/2f-500,Gdx.graphics.getHeight()-50);
        layout.setText(font,Integer.toString(secondScore));
        font.draw(main.batch,Integer.toString(secondScore),retryButton.x+retryButton.width/2f-layout.width/2f+500,Gdx.graphics.getHeight()-50);


        main.batch.end();
    }

        @Override
        public void resize ( int width, int height){

        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void hide () {

        }

        @Override
        public void dispose () {
            main.batch.dispose();
        }
    }
