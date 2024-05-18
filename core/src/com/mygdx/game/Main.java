package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;


public class Main extends Game {
    SpriteBatch batch;
    Texture post_image, lumber_l_image, lumber_r_image, root_left, root_right;
    boolean turn = false;
    Rectangle[] roots_list = new Rectangle[2];

    @Override
    public void create() {
        batch = new SpriteBatch();
        post_image = new Texture("Untitled Project-Photoroom.png-Photoroom.png");
        lumber_l_image = new Texture("climbing1-Photoroom.png-Photoroom.png");
        lumber_r_image = new Texture("climbing-Photoroom.png-Photoroom.png");
        root_left = new Texture("root_left.png");
        root_right = new Texture("root_right.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(root_left, Gdx.graphics.getWidth() / 2 - root_left.getWidth(), Gdx.graphics.getHeight() / 5 * 2, Gdx.graphics.getWidth() / 5 * 2, Gdx.graphics.getHeight() / 7);
        batch.draw(post_image, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getHeight() / 2, -Gdx.graphics.getHeight() / 9, Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + Gdx.graphics.getHeight() / 9);
        if (Gdx.input.justTouched()) {
            int cor = Gdx.input.getX();
            if (cor < Gdx.graphics.getWidth() / 2)
                turn = false;
            else
                turn = true;
        }
        if (!turn)
            batch.draw(lumber_l_image, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getHeight() / 8 * 3, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        else
            batch.draw(lumber_r_image, Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
