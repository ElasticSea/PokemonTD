package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

/**
 * Created by Tomas on 10/5/13.
 */
public class PathComponent extends Component {

    private final List<Vector2> path;

    public PathComponent(List<Vector2> path) {
        this.path = path;
    }

    public List<Vector2> getPath() {
        return path;
    }
}
