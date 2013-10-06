package com.xkings.pokemontd.map;

import com.xkings.core.pathfinding.Blueprint;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:03
 */

public class MapData {

    private final Blueprint blueprint;
    private final Path path;

    public MapData(Blueprint blueprint, Path path) {
        this.blueprint = blueprint;
        this.path = path;
    }

    public Path getPath() {
        return path;
    }

    public Blueprint getBlueprint(){
        return blueprint;
    }
}
