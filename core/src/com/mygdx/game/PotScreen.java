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


public class PotScreen implements Screen {
    Main main;

    public PotScreen(Main main) {
        this.main = main;
    }
    Texture pot, background;
    BitmapFont font;
    BitmapFont font1;
    GlyphLayout layout = new GlyphLayout();
    float freeze = 0.5f;
    Rectangle potik = new Rectangle(0, 0, Gdx.graphics.getWidth()/5f, Gdx.graphics.getWidth()/5f);
    boolean ris = true;
    float c1, c2;
    float gameTimer=20f;
    Texture rectTimer=new Texture("Rectangle_timer.png");
    Texture rectTimerGreen=new Texture("Rectangle_timer_green.png");
    Rectangle rectangle_timer=new Rectangle(Gdx.graphics.getWidth()/4f,8*Gdx.graphics.getHeight()/9f,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/9f);
    Rectangle getRectangle_timer_green=new Rectangle(Gdx.graphics.getWidth()/4f*1.05f,8*Gdx.graphics.getHeight()/9f+Gdx.graphics.getHeight()/90f,Gdx.graphics.getWidth()/2f-Gdx.graphics.getWidth()/40f,Gdx.graphics.getHeight()/9f-Gdx.graphics.getHeight()/45f);
    Rectangle retryButton=new Rectangle(Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/2f-Gdx.graphics.getHeight()/6f,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/3f);
    Texture retry=new Texture("Buttons/Rectangle_grey.png");

    int point=0;
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
        pot = new Texture("POT-Photoroom.png-Photoroom.png");
        background = new Texture("potsfield.JPG");
    }

    @Override
    public void render(float delta) {
        gameTimer=gameTimer<0?0:gameTimer;
        gameTimer-=delta;
        ScreenUtils.clear(1,1,1,1);
        main.batch.begin();
        main.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (ris&&freeze>0&&gameTimer>0)
            main.batch.draw(pot, potik.x, potik.y, potik.width, potik.height);
        freeze-=delta;
        if(freeze<=0&&gameTimer>=0) {
            potik.x = (int) (Math.random() * (Gdx.graphics.getWidth() - potik.width));
            potik.y = (int) (Math.random() * (Gdx.graphics.getHeight() - potik.height));

        }
        if(Gdx.input.justTouched()&&gameTimer<=0&&retryButton.contains(Gdx.input.getX(),Gdx.input.getY()))main.setScreen(new MenuScreen(main));

        if(gameTimer>=0&&Gdx.input.justTouched()&&!potik.contains(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())){
            point--;
        }
        point= Math.max(point, 0);
        if (ris&&gameTimer>=0&&Gdx.input.justTouched() && potik.contains(Gdx.input.getX(),Gdx.graphics.getHeight()- Gdx.input.getY())) {
            ris = false;
            point++;
        }
        if(freeze<=0) ris=true;
        c1 = (float) (Math.random() * 0.9 + 0.4);
        c2 = (float) (Math.random() * 1.3);
        freeze=freeze<-c2?c1:freeze;

        if(gameTimer<=0) {
            main.batch.draw(retry, retryButton.x, retryButton.y, retryButton.width, retryButton.height);
            layout.setText(font,"Нажмите чтобы выйти в главное меню");
            font.draw(main.batch,"Нажмите чтобы выйти в главное меню", retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/4f+layout.height/2);
            layout.setText(font1,"Ваш результат: "+Integer.toString(point) );
            font1.draw(main.batch,"Ваш результат: "+Integer.toString(point),retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/2f+layout.height/2);
        }
        main.batch.draw(rectTimer,rectangle_timer.x,rectangle_timer.y,rectangle_timer.width,rectangle_timer.height);
        main.batch.draw(rectTimerGreen,getRectangle_timer_green.x,getRectangle_timer_green.y,gameTimer/20f*getRectangle_timer_green.width,getRectangle_timer_green.height);

        layout.setText(font,Integer.toString((int)(point)));
        font.draw(main.batch,Integer.toString((int)(point)),Gdx.graphics.getWidth() / 2f- layout.width/2,99*Gdx.graphics.getHeight()/100f-50);
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
