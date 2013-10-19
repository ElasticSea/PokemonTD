package com.xkings.pokemontd.map;

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
    private int position;

    public Path(Vector3... path) {
        this(Arrays.asList(path), 0);
    }

    public Path(Path path) {
        this(path.getPath(), path.getPosition());
    }

    public Path(List<Vector3> path, int position) {
        this.path = path;
        this.position = position;
    }

    public List<Vector3> getPath() {
        return path;
    }

    public Vector3 get() {
        return path.get(position);
    }

    public void next() {
        this.position++;
    }

    public boolean isFinished() {
        return position == path.size();
    }

    public void reset() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }
}
