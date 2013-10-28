package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/28/13.
 */
public class MusicUI extends DisplayBlock {

    private final Music theme;

    public MusicUI(float x, float y, float width, float height) {
        this(new Rectangle(x,y,width,height));
    }

    public MusicUI(Rectangle rect) {
        super(rect);
        theme = Assets.getMusic("mainTheme.ogg");
        theme.setLooping(true);
        theme.play();
    }

    @Override
    public void render() {

    }
}

