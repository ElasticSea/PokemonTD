package com.xkings.pokemontd.entity;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Tomas on 10/9/13.
 */
public class CurrentTowerInfo {
    private TextureAtlas.AtlasRegion atlasRegion;

    public TextureAtlas.AtlasRegion getAtlasRegion() {
        return atlasRegion;
    }

    public void setAtlasRegion(TextureAtlas.AtlasRegion atlasRegion) {
        this.atlasRegion = atlasRegion;
    }

    public void clear() {
        atlasRegion = null;
    }
}
