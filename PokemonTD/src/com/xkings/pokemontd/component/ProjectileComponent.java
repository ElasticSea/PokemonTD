package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.entity.ProjectileType;

public class ProjectileComponent extends Component {

    private ProjectileType projectileType;

    public ProjectileComponent(ProjectileType projectileType) {
        this.projectileType = projectileType;
    }

    public ProjectileType getProjectileType() {
        return projectileType;
    }
}
