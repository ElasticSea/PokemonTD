package com.xkings.pokemontd.map;

import com.artemis.Entity;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.graphics.TileMap;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:03
 */

public class MapData {

    private final GenericBlueprint<Entity>  blueprint;
    private final Path path;
    private final TileMap tileMap;

    public MapData(GenericBlueprint<Entity>  blueprint, Path path, TileMap tileMap) {
        this.blueprint = blueprint;
        this.path = path;
        this.tileMap = tileMap;
    }

    public Path getPath() {
        return path;
    }

    public GenericBlueprint<Entity>  getBlueprint() {
        return blueprint;
    }

    public TileMap getTileMap() {
        return tileMap;
    }
}
