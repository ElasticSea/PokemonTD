package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.pixelthieves.elementtd.Treasure;

public class ShopComponent extends Component {

    private final Treasure treasure;

    public ShopComponent(Treasure treasure) {
        this.treasure = treasure;
    }

    public Treasure getTreasure() {
        return treasure;
    }

}
