package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;
import com.xkings.pokemontd.map.Path;

/**
 * Created by Tomas on 10/5/13.
 */
public class PathComponent extends Component {

    private final Path path;
    private int position;

    public PathComponent(Path path) {
        this.path = path;
        this.position = 0;
    }

    public Vector3 get() {
        return path.getPath().get(position);
    }

    public void next() {
        this.position++;
    }

    public Path getPath() {
        return path;
    }

    public boolean isFinished() {
        return position == path.getPath().size();
    }

    public void reset() {
        position = 0;
    }
}
