package com.xkings.pokemontd.graphics;

import com.xkings.pokemontd.Map;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

/**
 * Created by Tomas on 10/7/13.
 */
public class TileMap extends Map<AtlasRegion> {
    public final int TILE_SIZE;

    public TileMap(AtlasRegion[][] textureMap, int tileSize) {
        super(textureMap);
        this.TILE_SIZE = tileSize;
    }
}
