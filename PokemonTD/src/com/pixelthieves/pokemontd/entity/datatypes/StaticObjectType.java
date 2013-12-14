package com.pixelthieves.pokemontd.entity.datatypes;

import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public class StaticObjectType implements CommonDataType {

    private final AtlasRegion texture;
    private final float size;

    public StaticObjectType(Assets assets, String texture, float size) {
        this.texture = assets.getTexture(texture);
        this.size = size;
    }

    public AtlasRegion getTexture() {
        return texture;
    }

    public float getSize() {
        return size;
    }


}
