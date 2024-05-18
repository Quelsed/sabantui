package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.ScreenUtils;


public class ClimberScreen implements Screen {
    Main main;
    int tap = 0;

    public ClimberScreen(Main main) {
        this.main = main;
    }

    Texture post_image, lumber_l_image, lumber_r_image, root_left, root_right, clouds;
    boolean turn = false;
    Rectangle[] roots_list = new Rectangle[3];
    Texture[] textures_list = new Texture[3];
    Rectangle[] clouds_list = new Rectangle[2];
    Texture[] clouds_texture = new Texture[2];
    boolean firstTap = true;
    boolean getKicked = false;
    GlyphLayout layout = new GlyphLayout();
    Rectangle retryButton=new Rectangle(Gdx.graphics.getWidth()/4f,Gdx.graphics.getHeight()/2f-Gdx.graphics.getHeight()/6f,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/3f);
    Texture retry=new Texture("Buttons/Rectangle_grey.png");
    float timer=-1;
    BitmapFont font;
    float gameTimer=10f;
    int point=0;
    Texture rectTimer=new Texture("Rectangle_timer.png");
    Texture rectTimerGreen=new Texture("Rectangle_timer_green.png");
    Rectangle rectangle_timer=new Rectangle(Gdx.graphics.getWidth()/4f,8*Gdx.graphics.getHeight()/9f,Gdx.graphics.getWidth()/2f,Gdx.graphics.getHeight()/9f);
    Rectangle getRectangle_timer_green=new Rectangle(Gdx.graphics.getWidth()/4f*1.05f,8*Gdx.graphics.getHeight()/9f+Gdx.graphics.getHeight()/90f,Gdx.graphics.getWidth()/2f-Gdx.graphics.getWidth()/40f,Gdx.graphics.getHeight()/9f-Gdx.graphics.getHeight()/45f);

    @Override
    public void show() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Tantular/Tantular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюяАӘБВГДЕЁЖҖЗИЙКЛМНҢОӨПРСТУҮФХҺЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        parameter.size = 60;
        parameter.borderColor = Color.BLACK;
        parameter.color=Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
        generator.dispose();
        post_image = new Texture("Untitled Project-Photoroom.png-Photoroom.png");
        lumber_l_image = new Texture("climbing1-Photoroom.png-Photoroom.png");
        lumber_r_image = new Texture("climbing-Photoroom.png-Photoroom.png");
        root_left = new Texture("root_left.png");
        root_right = new Texture("root_right.png");
        clouds = new Texture("clouds.JPG");

        roots_list[0] = new Rectangle(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5 * 2, Gdx.graphics.getHeight() / 5 * 2, Gdx.graphics.getWidth() / 5 * 2, Gdx.graphics.getHeight() / 7);
        roots_list[1] = new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 5 * 4, Gdx.graphics.getWidth() / 5 * 2, Gdx.graphics.getHeight() / 7);
        roots_list[2] = new Rectangle(Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5 * 2, Gdx.graphics.getHeight() / 5 * 6, Gdx.graphics.getWidth() / 5 * 2, Gdx.graphics.getHeight() / 7);

        textures_list[0] = root_left;
        textures_list[1] = root_right;
        textures_list[2] = root_left;

        clouds_list[0] = new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth());
        clouds_list[1] = new Rectangle(0, Gdx.graphics.getWidth(), Gdx.graphics.getWidth(), Gdx.graphics.getWidth());

        clouds_texture[0]=clouds;
        clouds_texture[1]=clouds;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        main.batch.begin();
        if(timer>0) {timer-=delta;
            for (int i = 0; i < 2; i++) {
                clouds_list[i].y -= Gdx.graphics.getHeight() / 5f * 2*delta;
                if (clouds_list[i].y < -Gdx.graphics.getWidth()) {
                    clouds_list[i].y += 2 * Gdx.graphics.getWidth();
                }
            }
        }
        if(!getKicked)gameTimer-=delta;
        if(Gdx.input.justTouched()&&getKicked&&retryButton.contains(Gdx.input.getX(),Gdx.input.getY()))main.setScreen(new MenuScreen(main));
        if (Gdx.input.justTouched()&&!getKicked) {
            point++;
            gameTimer+=0.5f;
            gameTimer=gameTimer>=10f?10f:gameTimer;
            gameTimer=gameTimer<0?0:gameTimer;
            timer=0.1f;
            tap = firstTap ? tap - 1 : tap;
            firstTap = false;
            tap++;
            tap %= 3;
            int cor = Gdx.input.getX();
            int rand;
            roots_list[0].y -= Gdx.graphics.getHeight() / 5 * 2;
            roots_list[1].y -= Gdx.graphics.getHeight() / 5 * 2;
            roots_list[2].y -= Gdx.graphics.getHeight() / 5 * 2;
            if (roots_list[0].y < 0) {
                rand = (int) (Math.random() * 2);
                roots_list[0].y = Gdx.graphics.getHeight() / 5 * 4;
                if (rand == 1) {
                    if (textures_list[0] == root_left) {
                        textures_list[0] = root_right;
                        roots_list[0].x += Gdx.graphics.getWidth() / 5 * 2;
                    }
                } else {
                    if (textures_list[0] == root_right) {
                        textures_list[0] = root_left;
                        roots_list[0].x -= Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
            } else if (roots_list[1].y < 0) {
                rand = (int) (Math.random() * 2);
                roots_list[1].y = Gdx.graphics.getHeight() / 5 * 4;
                if (rand == 1) {
                    if (textures_list[1] == root_left) {
                        textures_list[1] = root_right;
                        roots_list[1].x += Gdx.graphics.getWidth() / 5 * 2;
                    }
                } else {
                    if (textures_list[1] == root_right) {
                        textures_list[1] = root_left;
                        roots_list[1].x -= Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
            } else if (roots_list[2].y < 0) {
                rand = (int) (Math.random() * 2);
                roots_list[2].y = Gdx.graphics.getHeight() / 5 * 4;
                if (rand == 1) {
                    if (textures_list[2] == root_left) {
                        textures_list[2] = root_right;
                        roots_list[2].x += Gdx.graphics.getWidth() / 5 * 2;
                    }
                } else {
                    if (textures_list[2] == root_right) {
                        textures_list[2] = root_left;
                        roots_list[2].x -= Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
            }
            if (cor < Gdx.graphics.getWidth() / 2)
                turn = false;
            else
                turn = true;
        }
        if(gameTimer<=0)getKicked=true;
        if (!getKicked && !firstTap && turn && roots_list[tap].x == Gdx.graphics.getWidth() / 2 || !getKicked && !firstTap && !turn && roots_list[tap].x == Gdx.graphics.getWidth() / 2 - Gdx.graphics.getWidth() / 5 * 2) {
            getKicked=true;
            for (int i = 0; i < 3; i++) {
                roots_list[i].y += Gdx.graphics.getHeight() / 3 - Gdx.graphics.getHeight() / 12;
            }
            //main.setScreen(new MenuScreen(main));
        }
        main.batch.draw(clouds_texture[0],clouds_list[0].x,clouds_list[0].y,clouds_list[0].width,clouds_list[0].height);
        main.batch.draw(clouds_texture[1],clouds_list[1].x,clouds_list[1].y,clouds_list[1].width,clouds_list[1].height);
        for (int i = 0; i < 3; i++) {
            main.batch.draw(textures_list[i], (float) roots_list[i].getX(), (float) roots_list[i].getY(), (float) roots_list[i].getWidth(), (float) roots_list[i].getHeight());
        }
        main.batch.draw(post_image, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getHeight() / 2, -Gdx.graphics.getHeight() / 9, Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + Gdx.graphics.getHeight() / 9);
        if (!turn) {
            main.batch.draw(lumber_l_image, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getHeight() / 8 * 3, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        } else {
            main.batch.draw(lumber_r_image, Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        }
        if(getKicked) {
            main.batch.draw(retry, retryButton.x, retryButton.y, retryButton.width, retryButton.height);
            layout.setText(font,"Нажмите чтобы выйти в главное меню");
            font.draw(main.batch,"Нажмите чтобы выйти в главное меню", retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/4f+layout.height/2);
            layout.setText(font,"Ваш результат: "+Integer.toString(point) );
            font.draw(main.batch,"Ваш результат: "+Integer.toString(point),retryButton.x+retryButton.width/2f-layout.width/2f,retryButton.y+retryButton.height/2f+layout.height/2);
        }
        main.batch.draw(rectTimer,rectangle_timer.x,rectangle_timer.y,rectangle_timer.width,rectangle_timer.height);
        main.batch.draw(rectTimerGreen,getRectangle_timer_green.x,getRectangle_timer_green.y,gameTimer/10f*getRectangle_timer_green.width,getRectangle_timer_green.height);

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
