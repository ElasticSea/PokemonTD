package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public class TintComponent extends Component {

    private Color tint;

    public TintComponent(Color tint) {
        this.tint = tint;
    }

    public Color getTint() {
        return tint;
    }
}
