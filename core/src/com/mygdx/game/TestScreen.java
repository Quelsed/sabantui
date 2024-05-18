package com.mygdx.game;

import static com.badlogic.gdx.Input.OnscreenKeyboardType.URI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.utils.ScreenUtils;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestScreen implements Screen {
    HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
    String query = "https://translate.tatar/translate?lang=0&text=дом";
    private final int HEIGHT = Gdx.graphics.getHeight();
    private final int WIDTH = Gdx.graphics.getWidth();
    Vocabulary v = new Vocabulary();
    BitmapFont font;
    private final String[] vocabulary = v.getArray();
    int rand = (int) (Math.random() * vocabulary.length);
    int a = (int) (Math.random() * vocabulary.length);
    int b = (int) (Math.random() * vocabulary.length);
    int c = (int) (Math.random() * vocabulary.length);
    Screen sc;
    Main main;

    public TestScreen(Main main, Screen sc) {
        this.main = main;
        this.sc = sc;
    }

    Texture firstButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture secondButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture thirdButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture fourthButtonImg = new Texture("Buttons/Rectangle_grey.png");
    Texture rightButton=new Texture("Buttons/Rectangle_green.png");
    Texture wrongButton=new Texture("Buttons/Rectangle_red.png");

    Rectangle firstButton = new Rectangle(0, 0, WIDTH / 2f-10, HEIGHT / 3f-10);
    Rectangle secondButton = new Rectangle(WIDTH / 2f, 0, WIDTH / 2f-10, HEIGHT / 3f-10);
    Rectangle thirdButton = new Rectangle(0, HEIGHT / 3f, WIDTH / 2f-10, HEIGHT / 3f-10);
    Rectangle fourthButton = new Rectangle(WIDTH / 2f, HEIGHT / 3f, WIDTH / 2f-10, HEIGHT / 3f-10);

    Rectangle[] Buttons = new Rectangle[]{firstButton, secondButton, thirdButton, fourthButton};
    int[] puk;
    GlyphLayout layout = new GlyphLayout();
    float fontHeight, fontWidth;
    int[] Randint = new int[]{-1, -1, -1, -1};
    String[] translate = new String[4];
    int state=2;
    float timerToStart=1;
    int place=-1;
    int placeWrong=-1;

    @Override
    public void show() {

        //font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Tantular/Tantular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = "аәбвгдеёжҗзийклмнңоөпрстуүфхһцчшщъыьэюяАӘБВГДЕЁЖҖЗИЙКЛМНҢОӨПРСТУҮФХҺЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        parameter.size = 150;
        parameter.borderColor = Color.BLACK;
        parameter.color=Color.BLACK;
        parameter.borderWidth = 3;
        font = generator.generateFont(parameter);
        generator.dispose();
        while (a == rand) {
            a = (int) (Math.random() * vocabulary.length);
        }
        while (!(b != rand && b != a)) {
            b = (int) (Math.random() * vocabulary.length);
        }
        while (!(c != rand && c != a && c != b)) {
            c = (int) (Math.random() * vocabulary.length);
        }
        puk = new int[]{rand, a, b, c};
        for (int i = 0; i < 4; i++) {
            int k = (int) (Math.random() * 4);
            while (true) {
                if (Randint[k] == -1) {
                    Randint[k] = puk[i];
                    break;
                } else {
                    k = (int) (Math.random() * 4);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            translate[i] = vocabulary[Randint[i]];
        }
        //
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url("https://translate.tatar/translate?lang=1&text=җиһаз").build();
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener(){
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void cancelled() {
                System.out.println("cancelled");
            }
        });

    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        main.batch.begin();
        main.batch.draw(firstButtonImg, firstButton.x, firstButton.y, firstButton.width, firstButton.height);
        main.batch.draw(secondButtonImg, secondButton.x, secondButton.y, secondButton.width, secondButton.height);
        main.batch.draw(thirdButtonImg, thirdButton.x, thirdButton.y, thirdButton.width, thirdButton.height);
        main.batch.draw(fourthButtonImg, fourthButton.x, fourthButton.y, fourthButton.width, fourthButton.height);
        for (int i = 0; i < 4; i++) {
            if (place==-1&&Randint[i] == rand ) {
                place=i;
            }
            if (Gdx.input.justTouched() && Buttons[i].contains(Gdx.input.getX(), HEIGHT - Gdx.input.getY())&&state==2) {
                if (Randint[i] == rand) {
                    state =1;
                }
                else{
                    state=0;
                    placeWrong=i;
                    timerToStart+=1;
                }
            }
        }
        if(state==1){
            if(timerToStart<0)main.setScreen(sc);
            timerToStart-=delta;
            main.batch.draw(rightButton, Buttons[place].x, Buttons[place].y, Buttons[place].width, Buttons[place].height);
        }
        if(state==0){
            if(timerToStart<0)main.setScreen(new MenuScreen(main));
            timerToStart-=delta;
            main.batch.draw(rightButton, Buttons[place].x, Buttons[place].y, Buttons[place].width, Buttons[place].height);
            main.batch.draw(wrongButton, Buttons[placeWrong].x, Buttons[placeWrong].y, Buttons[placeWrong].width, Buttons[placeWrong].height);

        }

        for (int i = 0; i < 4; i++) {
            layout.setText(font, vocabulary[Randint[i]]);
            fontHeight = layout.height;
            fontWidth = layout.width;
            font.draw(main.batch, vocabulary[Randint[i]], Buttons[i].x + Buttons[i].width / 2f - fontWidth / 2f, Buttons[i].y + Buttons[i].height / 2f + fontHeight / 2f);
        }
        layout.setText(font, vocabulary[rand]);
        fontHeight = layout.height;
        fontWidth = layout.width;
        font.draw(main.batch, vocabulary[rand], WIDTH / 2f - fontWidth / 2f, HEIGHT * 2 / 3f + fontHeight / 2f + HEIGHT / 6f);


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
