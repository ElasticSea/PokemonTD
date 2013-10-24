package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.Treasure;

public class ShopComponent extends Component {

    private final Treasure treasure;

    public ShopComponent(Treasure treasure) {
        this.treasure = treasure;
    }

    public Treasure getTreasure() {
        return treasure;
    }

}
