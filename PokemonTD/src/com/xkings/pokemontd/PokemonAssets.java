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

    private final BitmapFont smoothFont;
    private final BitmapFont pixelFont;

    public PokemonAssets() {
        this.addAtlas(new TextureAtlas("data/textures/packed.atlas"));
        TextureAtlas.AtlasRegion texture = getTexture("smooth_font");
        texture.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        smoothFont = new BitmapFont(Gdx.files.internal("data/fonts/smooth_font.fnt"), texture, false);
        pixelFont = new BitmapFont(Gdx.files.internal("data/fonts/pixelFont.fnt"), getTexture("pixelFont"), false);
    }

    public BitmapFont getSmoothFont() {
        return smoothFont;
    }

    public BitmapFont getPixelFont() {
        return pixelFont;
    }
}
