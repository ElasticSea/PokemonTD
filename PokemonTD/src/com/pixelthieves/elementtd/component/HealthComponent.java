package com.pixelthieves.elementtd.component;

import com.artemis.Component;
import com.pixelthieves.elementtd.Health;

public class HealthComponent extends Component {

    private Health health;

    public HealthComponent(Health health) {
        this.health = health;
    }

    public Health getHealth() {
        return health;
    }

}
