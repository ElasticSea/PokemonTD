package com.xkings.pokemontd.entity;

import com.xkings.core.main.AbstractAssets;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/5/13.
 */
public enum TowerType {

    DefaultTower("default");
    private final AtlasRegion texture;

    private TowerType(String texture) {
        this.texture = AbstractAssets.getTexture(texture);
    }

    public AtlasRegion getTexture() {
        return texture;
    }
}
