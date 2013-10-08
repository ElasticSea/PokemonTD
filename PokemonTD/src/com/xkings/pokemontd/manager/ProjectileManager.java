package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.entity.Projectile;
import com.xkings.pokemontd.entity.ProjectileType;

/**
 * Created by Tomas on 10/7/13.
 */
public class ProjectileManager {

    private final Blueprint blueprint;
    private final World world;

    public ProjectileManager(World world, Blueprint blueprint) {
        this.world = world;
        this.blueprint = blueprint;
    }

    public boolean createProjectile(ProjectileType projectileType, Vector3 position, Vector3 targetPosition,
                                    Entity target) {
        Projectile.registerProjectile(world, projectileType, position.x, position.y, targetPosition, target);
        return true;
    }
}
