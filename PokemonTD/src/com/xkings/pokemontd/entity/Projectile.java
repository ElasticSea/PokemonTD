package com.xkings.pokemontd.entity;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.entity.ConcreteEntity;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.PathComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.component.attack.projectile.ProjectileComponent;
import com.xkings.pokemontd.map.Path;

/**
 * Created by Tomas on 10/5/13.
 */
public class Projectile extends ConcreteEntity {

    private Projectile(World world, ProjectileComponent projectileType, float x, float y, float damage, float speed,
                       Vector3 targetPosition, Entity target) {
        super(world);
        addComponent(new PositionComponent(x, y, 0));
        addComponent(new RotationComponent(0, 0, 0));
        addComponent(new PathComponent(new Path(new Vector3(x, y, 0), targetPosition)));
        addComponent(new SpriteComponent(projectileType.getTexture()));
        addComponent(new SizeComponent(projectileType.getSize(), projectileType.getSize(), 0));
        addComponent(new SpeedComponent(projectileType.getSpeed()));
        addComponent(new DamageComponent(damage, speed));
        addComponent(projectileType);
        addComponent(new TimeComponent());
        addComponent(new TargetComponent(target));
        for (AbilityComponent abilityComponent : projectileType.getAbility()) {
            addComponent(abilityComponent);
        }
    }

    public static void registerProjectile(World world, ProjectileComponent projectileType, float x, float y,
                                          float damage, float speed, Vector3 targetPosition, Entity target) {
        Projectile projectile = new Projectile(world, projectileType, x, y, damage, speed, targetPosition, target);
        projectile.register();
    }
}
