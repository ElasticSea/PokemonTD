package com.xkings.pokemontd.entity;

import com.xkings.core.main.AbstractAssets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum StaticObjectType {

    DefaultStaticObject("default", 1);
    private final AtlasRegion texture;
    private final int size;

    private StaticObjectType(String texture, int size) {
        this.texture = AbstractAssets.getTexture(texture);
        this.size = size;
    }

    public AtlasRegion getTexture() {
        return texture;
    }

    public int getSize() {
        return size;
    }

}
