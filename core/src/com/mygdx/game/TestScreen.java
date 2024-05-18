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

import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TestScreen implements Screen {
    String query="https://translate.tatar";
    HttpsURLConnection connection=null;
    private final int HEIGHT = Gdx.graphics.getHeight();
    private final int WIDTH = Gdx.graphics.getWidth();
    Vocabulary v=new Vocabulary();
    BitmapFont font;
    private final String[]vocabulary=v.getArray();
    int rand= (int) (Math.random()*vocabulary.length);
    int a=(int) (Math.random()*vocabulary.length);
    int b=(int) (Math.random()*vocabulary.length);
    int c=(int) (Math.random()*vocabulary.length);
    Screen sc;
    Main main;
    public TestScreen(Main main,Screen sc) {
        this.main = main;
        this.sc=sc;
    }
    Texture firstButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture secondButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture thirdButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture fourthButtonImg = new Texture("Buttons/Rectangle_grey.png");

    Rectangle firstButton=new Rectangle(0,0,WIDTH/2f,HEIGHT/3f);
    Rectangle secondButton=new Rectangle(WIDTH/2f,0,WIDTH/2f,HEIGHT/3f);
    Rectangle thirdButton=new Rectangle(0,HEIGHT/3f,WIDTH/2f,HEIGHT/3f);
    Rectangle fourthButton=new Rectangle(WIDTH/2f,HEIGHT/3f,WIDTH/2f,HEIGHT/3f);

    Rectangle[] Buttons=new Rectangle[]{firstButton,secondButton,thirdButton,fourthButton};
    int[]puk;
    GlyphLayout layout = new GlyphLayout();
    float fontHeight,fontWidth;
    int[] Randint=new int[]{-1,-1,-1,-1};
    String[] translate=new String[4];

    @Override
    public void show() {
        //font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Tantular/Tantular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters="аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюяАӘБВГДЕЁЖҖЗИЙКЛМНҢОӨПРСТУҮФХҺЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        parameter.size = 150;
        parameter.borderColor = Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
        generator.dispose();
        while(a == rand){
            a=(int) (Math.random()*vocabulary.length);
        }
        while(!(b!=rand&&b!=a)){
            b=(int) (Math.random()*vocabulary.length);
        }
        while(!(c!=rand&&c!=a&&c!=b)){
            c=(int) (Math.random()*vocabulary.length);
        }
        puk=new int[]{rand,a,b,c};
        for(int i=0;i<4;i++){
            int k=(int)(Math.random()*4);
            System.out.println(k+"k");
            while(true) {
                if (Randint[k] == -1) {
                    Randint[k] = puk[i];
                    break;
                } else {
                    k=(int)(Math.random()*4);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            translate[i]=vocabulary[Randint[i]];
        }
        for (int i = 0; i < 4; i++) {
            try{
                connection=(HttpsURLConnection) new URL(query).openConnection();

                connection.setRequestMethod("GET/translate?lang=1&text="+translate[i]);
                connection.setUseCaches(false);
                connection.setConnectTimeout(250);
                connection.setReadTimeout(250);

                connection.connect();
                if(connection.HTTP_OK==connection.getResponseCode()){

                }

            }catch (Throwable cause){
                cause.printStackTrace();
            }finally {
                if(connection!=null){
                    connection.disconnect();
                }
            }
        }


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        main.batch.begin();
        for(int i=0;i<4;i++) {
            if (Gdx.input.justTouched() && Buttons[i].contains(Gdx.input.getX(), HEIGHT-Gdx.input.getY())) {
                if (Randint[i] == rand) {
                    main.setScreen(sc);
                }
            }
        }
        main.batch.draw(firstButtonImg, firstButton.x, firstButton.y,firstButton.width,firstButton.height);
        main.batch.draw(secondButtonImg, secondButton.x, secondButton.y,secondButton.width,secondButton.height);
        main.batch.draw(thirdButtonImg, thirdButton.x, thirdButton.y,thirdButton.width,thirdButton.height);
        main.batch.draw(fourthButtonImg, fourthButton.x, fourthButton.y,fourthButton.width,fourthButton.height);

        for(int i=0;i<4;i++) {
            layout.setText(font,vocabulary[Randint[i]]);
            fontHeight =layout.height;
            fontWidth=layout.width;
            font.draw(main.batch, vocabulary[Randint[i]], Buttons[i].x+Buttons[i].width/2f-fontWidth/2f, Buttons[i].y+Buttons[i].height/2f+fontHeight/2f);
        }
        layout.setText(font,vocabulary[rand]);
        fontHeight =layout.height;
        fontWidth=layout.width;
        font.draw(main.batch, vocabulary[rand], WIDTH/2f-fontWidth/2f,HEIGHT*2/3f+fontHeight/2f +HEIGHT/6f);


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
        firstButtonImg.dispose();
        secondButtonImg.dispose();
        thirdButtonImg.dispose();
        fourthButtonImg.dispose();
    }
}
