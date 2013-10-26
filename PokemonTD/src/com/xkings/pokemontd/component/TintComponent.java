package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public class TintComponent extends Component {

    private Color tint;

    public TintComponent() {
        this(Color.WHITE);
    }

    public TintComponent(Color tint) {
        this.tint = new Color(tint);
    }

    public Color getTint() {
        return tint;
    }
}
