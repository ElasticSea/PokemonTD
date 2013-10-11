package com.xkings.pokemontd.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:07
 */

public class Path {
    private final List<Vector3> path;

    public Path(List<Vector3> path) {
        this.path = path;
    }

    public Path(Vector3... path) {
        this(Arrays.asList(path));
    }

    public List<Vector3> getPath() {
        return path;
    }
}
