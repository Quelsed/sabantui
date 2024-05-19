package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class PulScreen implements Screen {
    private int HEIGHT = Gdx.graphics.getHeight();
    private int WIDTH = Gdx.graphics.getWidth();

    float startTimer = 4;
    float timeToEnd = 0.5f;
    BitmapFont font;
    BitmapFont font1;
    GlyphLayout layout = new GlyphLayout();
    boolean stop = false;
    Rectangle retryButton = new Rectangle(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 2f - Gdx.graphics.getHeight() / 6f, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 3f);
    Texture retry = new Texture("Buttons/Rectangle_grey.png");

    Main main;

    public PulScreen(Main main) {
        this.main = main;
    }

    Texture ropeImg = new Texture("rope.png");
    Texture background = new Texture("background.png");
    Texture numberOne = new Texture("Numbers/one.png");
    Texture numberTwo = new Texture("Numbers/two.png");
    Texture numberThree = new Texture("Numbers/three.png");
    Texture winWindow = new Texture("win.png");
    Texture loseWindow = new Texture("lose.png");
    Texture GO = new Texture("Numbers/go.png");

    Rectangle rope = new Rectangle(WIDTH / 4f, 0, WIDTH / 2f, HEIGHT / 1.3f);

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
        if(Gdx.input.justTouched()&&stop&&retryButton.contains(Gdx.input.getX(),Gdx.input.getY()))main.setScreen(new MenuScreen(main));
        startTimer = startTimer > -1 ? startTimer - delta : -1;
        main.batch.begin();
        main.batch.draw(background, 0, 0, WIDTH, HEIGHT);
        main.batch.draw(ropeImg, rope.x, rope.y, rope.width, rope.height);
        if (startTimer <= -1 && !stop) {
            rope.x += WIDTH / 50f * delta;
            if (Gdx.input.justTouched()) rope.x -= WIDTH / 200f;
        }
        if (stop) {
            main.batch.draw(retry, retryButton.x, retryButton.y, retryButton.width, retryButton.height);
            layout.setText(font, "Нажмите чтобы выйти в главное меню");
            font.draw(main.batch, "Нажмите чтобы выйти в главное меню", retryButton.x + retryButton.width / 2f - layout.width / 2f, retryButton.y + retryButton.height / 4f + layout.height / 2);
            }
        if (rope.x < WIDTH / 8f) {
            stop = true;
            layout.setText(font1, "Вы победили");
            font1.draw(main.batch, "Вы победили", retryButton.x + retryButton.width / 2f - layout.width / 2f, retryButton.y + retryButton.height / 2f + layout.height / 2+50);
        }
        if (rope.x + rope.width > 7 * WIDTH / 8f) {
            stop = true;
            layout.setText(font1, "Вы проиграли");
            font1.draw(main.batch, "Вы проиграли", retryButton.x + retryButton.width / 2f - layout.width / 2f, retryButton.y + retryButton.height / 2f + layout.height / 2+50);
        }
        if(startTimer>0){
            if((int)(startTimer)>0) {
                layout.setText(font1, Integer.toString((int) (startTimer)));
                font1.draw(main.batch, Integer.toString((int) (startTimer)), Gdx.graphics.getWidth() / 2f - layout.width / 2, 2 * Gdx.graphics.getHeight() / 3f);
            }
            else{
                layout.setText(font1, "Киттек");
                font1.draw(main.batch, "Киттек", Gdx.graphics.getWidth() / 2f - layout.width / 2, 2 * Gdx.graphics.getHeight() / 3f);

            }
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
    public void dispose() {
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
