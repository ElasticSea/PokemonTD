package com.pixelthieves.pokemontd.component;

import com.artemis.Component;

/**
 * Created by Seda on 10/4/13.
 */
public class NameComponent extends Component {

    private final String name;

    public NameComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
