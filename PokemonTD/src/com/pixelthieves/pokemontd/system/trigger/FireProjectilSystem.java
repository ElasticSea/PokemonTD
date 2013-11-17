package com.pixelthieves.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.pixelthieves.pokemontd.component.attack.effects.buff.BuffableSpeedComponent;
import com.pixelthieves.pokemontd.component.attack.projectile.HitAbility;
import com.pixelthieves.pokemontd.entity.Projectile;
import com.pixelthieves.pokemontd.system.resolve.FirstCreepSystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class FireProjectilSystem extends ApplyAbilitySystem<HitAbility> {

    @Mapper
    ComponentMapper<BuffableSpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;


    public FireProjectilSystem() {
        //super(HitAbility.class, ClosestCreepSystem.class);
        super(HitAbility.class, FirstCreepSystem.class);
    }

    @Override
    protected void processTarget(HitAbility ability, Entity entity, Entity target) {
        Vector3 position = positionMapper.get(entity).getPoint();
        float speed = speedMapper.get(entity).getSpeed();
        float damage = damageMapper.get(entity).getDamage();

        Vector3 closestEnemyPosition = positionMapper.get(target).getPoint();

        createProjectile(entity, ability, position, damage, speed, closestEnemyPosition, target);
    }

    public boolean createProjectile(Entity entity, HitAbility projectileType, Vector3 position, float damage,
                                    float speed, Vector3 targetPosition, Entity target) {
        switch (projectileType.getType()) {
            case PASS_THROUGH:
                Vector3 direction = targetPosition.cpy().sub(position).nor().scl(5 * App.WORLD_SCALE).add(position);
                Projectile.registerProjectile(world, entity, projectileType, position.x, position.y, damage, speed,
                        direction, target);
                break;

            case FOLLOW_TARGET:
            case IMMEDIATE_ATTACK:
            case IMMEDIATE_NOCONTACT_DAMAGE:
                Projectile.registerProjectile(world, entity, projectileType, position.x, position.y, damage, speed,
                        targetPosition, target);
                break;
        }
        return true;
    }

}
