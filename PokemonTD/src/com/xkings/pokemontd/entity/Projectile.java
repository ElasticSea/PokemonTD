package com.xkings.pokemontd.entity;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.AttackComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.map.Path;

/**
 * Created by Tomas on 10/5/13.
 */
public class Projectile extends ConcreteEntity {

    private Projectile(World world, ProjectileType projectileType, float x, float y, Vector3 targetPosition,
                       Entity target) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0, 0, 0));
        addComponent(new PathComponent(new Path(new Vector3(x, y, 0), targetPosition)));
        addComponent(new SpriteComponent(projectileType.getTexture()));
        addComponent(new SizeComponent(projectileType.getSize(), projectileType.getSize(), 0));
        addComponent(new SpeedComponent(projectileType.getSpeed()));
        addComponent(new AttackComponent(1));
        addComponent(new TimeComponent());
        addComponent(new TargetComponent(target));
    }


    public static void registerProjectile(World world, ProjectileType projectileType, float x, float y,
                                          Vector3 targetPosition, Entity target) {
        Projectile projectile = new Projectile(world, projectileType, x, y, targetPosition, target);
        projectile.register();
    }
}
