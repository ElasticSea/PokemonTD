package com.xkings.pokemontd.map;

import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/12/13.
 */
public class PathPack {

    private final List<Path> paths;

    public PathPack(List<List<Vector3>> paths) {
        this.paths = new ArrayList<Path>();
        for (int i = 0; i < paths.size(); i++) {
            this.paths.add(new Path(paths.get(i)));
        }
    }

    public List<Path> getPaths() {
        return paths;
    }

    public Path getMain() {
        return paths.get(paths.size() / 2);
    }
}
