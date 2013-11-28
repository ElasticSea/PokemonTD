package com.pixelthieves.pokemontd.component;

import com.artemis.Component;
import com.pixelthieves.pokemontd.map.Path;

/**
 * Created by Seda on 10/5/13.
 */
public class PathComponent extends Component {

    private final Path path;

    public PathComponent(Path path) {
        this.path = path;
    }

    public Path getPath() {
        return path;
    }
}
