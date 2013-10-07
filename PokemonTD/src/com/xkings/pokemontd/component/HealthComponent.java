package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.Health;

public class HealthComponent extends Component {

    private Health health;

    public HealthComponent(Health health) {
        this.health = health;
    }

    public Health getHealth() {
        return health;
    }
}
