package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.pixelthieves.elementtd.Treasure;

public class TreasureComponent extends Component {

    private final Treasure treasure;

    public TreasureComponent(Treasure treasure) {
        this.treasure = treasure;
    }

    public Treasure getTreasure() {
        return treasure;
    }

}
