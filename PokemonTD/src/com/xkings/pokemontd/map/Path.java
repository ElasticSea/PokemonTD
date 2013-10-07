package com.xkings.pokemontd.map;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:07
 */

public class Path {
    private final List<Vector2> path;

    public Path(List<Vector2> path) {
        this.path = path;
    }

    public Path(Vector2... path) {
        this(new ArrayList<Vector2>());
    }

    public List<Vector2> getPath() {
        return path;
    }
}
