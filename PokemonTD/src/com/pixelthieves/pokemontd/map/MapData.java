package com.pixelthieves.pokemontd.map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.pixelthieves.core.pathfinding.Blueprint;
import com.pixelthieves.pokemontd.graphics.TileMap;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:03
 */

/**
 * DTO holding map normal.
 */
public class MapData {

    private final Blueprint blueprint;
    private final Blueprint gameBlueprint;
    private final PathPack pathPack;
    private final TileMap<TextureAtlas.AtlasRegion> tileMap;

    public MapData(Blueprint blueprint, Blueprint gameBlueprint, PathPack pathPack, TileMap<TextureAtlas.AtlasRegion> tileMap) {
        this.blueprint = blueprint;
        this.gameBlueprint = gameBlueprint;
        this.pathPack = pathPack;
        this.tileMap = tileMap;
    }

    public PathPack getPathPack() {
        return pathPack;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public Blueprint getGameBlueprint() {
        return gameBlueprint;
    }

    public TileMap<TextureAtlas.AtlasRegion> getTileMap() {
        return tileMap;
    }
}
