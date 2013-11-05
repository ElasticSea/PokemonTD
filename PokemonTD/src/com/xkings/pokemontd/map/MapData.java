package com.xkings.pokemontd.map;

import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.graphics.TileMap;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:03
 */

/**
 * DTO holding map data.
 */
public class MapData {

    private final Blueprint blueprint;
    private final Blueprint gameBlueprint;
    private final PathPack pathPack;
    private final TileMap tileMap;

    public MapData(Blueprint blueprint, Blueprint gameBlueprint, PathPack pathPack, TileMap tileMap) {
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

    public TileMap getTileMap() {
        return tileMap;
    }
}
