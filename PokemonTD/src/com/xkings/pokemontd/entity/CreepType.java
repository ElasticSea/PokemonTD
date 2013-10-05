package com.xkings.pokemontd.entity;

import com.xkings.core.main.AbstractAssets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum CreepType {

    DefaultCreep("default");
    private final AtlasRegion texture;

    private CreepType(String texture) {
        this.texture = AbstractAssets.getTexture(texture);
    }

    public AtlasRegion getTexture() {
        return texture;
    }
}
