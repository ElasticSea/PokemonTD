package com.xkings.pokemontd.map;

import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.graphics.TileMap;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:03
 */

public class MapData {

    private final Blueprint blueprint;
    private final Path path;
    private final TileMap tileMap;

    public MapData(Blueprint blueprint, Path path, TileMap tileMap) {
        this.blueprint = blueprint;
        this.path = path;
        this.tileMap = tileMap;
    }

    public Path getPath() {
        return path;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public TileMap getTileMap() {
        return tileMap;
    }
}
