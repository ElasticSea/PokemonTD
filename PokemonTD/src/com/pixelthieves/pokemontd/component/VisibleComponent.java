package com.pixelthieves.pokemontd.component;

import com.artemis.Component;

/**
 * Created by Seda on 10/13/13.
 */
public class VisibleComponent extends Component {
    private boolean visible;

    public VisibleComponent(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
