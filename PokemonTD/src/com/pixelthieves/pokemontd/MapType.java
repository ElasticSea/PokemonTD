package com.pixelthieves.pokemontd;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.pixelthieves.core.main.Assets;

/**
 * Created by Tomas on 12/9/13.
 */
public enum MapType {
    Summer, Winter;

    public TextureAtlas.AtlasRegion getTexture(Assets assets) {
        return assets.getTexture("map/" + this.name().toLowerCase() + "/thumbnail");
    }
}
