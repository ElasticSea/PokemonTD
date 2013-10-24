package com.xkings.pokemontd.manager;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.attack.projectile.ProjectileComponent;
import com.xkings.pokemontd.entity.Projectile;

/**
 * Created by Tomas on 10/7/13.
 */
public class ProjectileManager {

    private final World world;

    public ProjectileManager(World world) {
        this.world = world;
    }

    public boolean createProjectile(ProjectileComponent projectileType, Vector3 position, float damage, float speed,
                                    Vector3 targetPosition, Entity target) {
        switch (projectileType.getType()) {
            case FOLLOW_TARGET:
                Projectile.registerProjectile(world, projectileType, position.x, position.y, damage, speed,
                        targetPosition, target);
                break;


            case PASS_THROUGH:
                Vector3 direction = targetPosition.cpy().sub(position).nor().scl(5 * App.WORLD_SCALE).add(position);
                Projectile.registerProjectile(world, projectileType, position.x, position.y, damage, speed, direction,
                        target);
                break;
            case IMMEDIATE_ATTACK:
                Projectile.registerProjectile(world, projectileType, position.x, position.y, damage, speed, targetPosition,
                        target);
                break;
        }
        return true;
    }
}
