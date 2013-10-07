package com.xkings.pokemontd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/7/13.
 */
public class PokemonAssets extends Assets {

    private final BitmapFont smoothFont;

    public PokemonAssets() {
        this.addAtlas(new TextureAtlas("data/textures/packed.atlas"));
        smoothFont =
                new BitmapFont(Gdx.files.internal("data/fonts/smooth_font.fnt"), getTexture("smooth_font"), false);
    }

    public BitmapFont getSmoothFont() {
        return smoothFont;
    }
}
