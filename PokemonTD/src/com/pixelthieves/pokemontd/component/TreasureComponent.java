package com.pixelthieves.pokemontd.component;

import com.artemis.Component;
import com.pixelthieves.pokemontd.Treasure;

public class TreasureComponent extends Component {

    private final Treasure treasure;

    public TreasureComponent(Treasure treasure) {
        this.treasure = treasure;
    }

    public Treasure getTreasure() {
        return treasure;
    }

}
