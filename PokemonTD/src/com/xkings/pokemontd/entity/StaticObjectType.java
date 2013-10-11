package com.xkings.pokemontd.entity;

import com.xkings.core.main.Assets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum StaticObjectType implements CommonDataType {

    DefaultStaticObject("default", 1);
    private final AtlasRegion texture;
    private final int size;

    private StaticObjectType(String texture, int size) {
        this.texture = Assets.getTexture(texture);
        this.size = size;
    }

    public AtlasRegion getTexture() {
        return texture;
    }

    public float getSize() {
        return size;
    }

}
