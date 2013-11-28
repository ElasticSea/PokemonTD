package com.pixelthieves.pokemontd.map;

import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Seda on 10/12/13.
 */

/**
 * A path pack containing individual paths.
 */
public class PathPack {

    private final List<Path> paths;

    public PathPack(List<List<Vector3>> paths, float width) {
        this.paths = new ArrayList<Path>();
        for (int i = 0; i < paths.size(); i++) {
            this.paths.add(new Path(paths.get(i), 0, width));
        }
    }

    public List<Path> getPaths() {
        return paths;
    }


    public Path get(int i) {
        return paths.get(i);
    }


    public int size() {
        return paths.size();
    }

    public Path getMain() {
        return paths.get(paths.size() / 2);
    }
}
