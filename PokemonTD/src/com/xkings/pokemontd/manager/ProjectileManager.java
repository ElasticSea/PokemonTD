package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.component.attack.ProjectileComponent;
import com.xkings.pokemontd.entity.Projectile;

/**
 * Created by Tomas on 10/7/13.
 */
public class ProjectileManager {

    private final GenericBlueprint blueprint;
    private final World world;

    public ProjectileManager(World world, GenericBlueprint blueprint) {
        this.world = world;
        this.blueprint = blueprint;
    }

    public boolean createProjectile(ProjectileComponent projectileType, Vector3 position, float damage,
                                    Vector3 targetPosition, Entity target) {
        switch (projectileType.getType()) {
            case FOLLOW_TARGET:
                Projectile.registerProjectile(world, projectileType, position.x, position.y, damage, targetPosition,
                        target);
                break;
            case LAST_KNOWN_PLACE:
                break;
            case AHEAD_TARGET:
                break;
        }
        return true;
    }
}
