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
    //Rectangle[] roots_list = new Rectangle[2];

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this));
    }
}
