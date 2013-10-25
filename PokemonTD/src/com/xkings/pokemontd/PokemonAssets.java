package com.xkings.pokemontd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/7/13.
 */
public class PokemonAssets extends Assets {

    private final BitmapFont pixelFont;

    public PokemonAssets() {
        this.addAtlas(new TextureAtlas("data/textures/packed.atlas"));
        pixelFont = new BitmapFont(Gdx.files.internal("data/fonts/pixelFont.fnt"), getTexture("pixelFont"), false);
    }

    public BitmapFont getPixelFont() {
        return pixelFont;
    }
}
