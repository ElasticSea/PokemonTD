package com.xkings.pokemontd.component;

import com.artemis.Component;

public class HealthComponent extends Component {

    private final int health;

    public HealthComponent(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
