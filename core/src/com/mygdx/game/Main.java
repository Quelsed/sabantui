package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.Rectangle;


public class Main extends Game {
    SpriteBatch batch;
    Texture post_image, lumber_l_image, lumber_r_image, root_left, root_right, clouds;
    boolean turn = false;
    Rectangle[] roots_list = new Rectangle[3];
    Texture[] textures_list = new Texture[3];
    Rectangle[] clouds_list = new Rectangle[2];
    Texture[] clouds_texture = new Texture[2];

    @Override
    public void create() {
        batch = new SpriteBatch();
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

        clouds_list[0] = new Rectangle(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        clouds_list[1] = new Rectangle(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        for (int i = 0; i < 3; i++) {
            batch.draw(textures_list[i], (float) roots_list[i].getX(), (float) roots_list[i].getY(), (float) roots_list[i].getWidth(), (float) roots_list[i].getHeight());
        }
        batch.draw(post_image, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getHeight() / 2, -Gdx.graphics.getHeight() / 9, Gdx.graphics.getHeight(), Gdx.graphics.getHeight() + Gdx.graphics.getHeight() / 9);
        if (Gdx.input.justTouched()) {
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
                }
                else {
                    if (textures_list[0] == root_right) {
                        textures_list[0] = root_left;
                        roots_list[0].x -= Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
            }
            else if (roots_list[1].y < 0) {
                rand = (int) (Math.random() * 2);
                roots_list[1].y = Gdx.graphics.getHeight() / 5 * 4;
                if (rand == 1) {
                    if (textures_list[1] == root_left) {
                        textures_list[1] = root_right;
                        roots_list[1].x += Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
                else {
                    if (textures_list[1] == root_right) {
                        textures_list[1] = root_left;
                        roots_list[1].x -= Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
            }
            else if (roots_list[2].y < 0) {
                rand = (int) (Math.random() * 2);
                roots_list[2].y = Gdx.graphics.getHeight() / 5 * 4;
                if (rand == 1) {
                    if (textures_list[2] == root_left) {
                        textures_list[2] = root_right;
                        roots_list[2].x += Gdx.graphics.getWidth() / 5 * 2;
                    }
                }
                else {
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
        if (!turn) {
            batch.draw(lumber_l_image, Gdx.graphics.getWidth() / 2 - Gdx.graphics.getHeight() / 8 * 3, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        } else {
            batch.draw(lumber_r_image, Gdx.graphics.getWidth() / 2, 0, Gdx.graphics.getHeight() / 3, Gdx.graphics.getHeight() / 3);
        }
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
