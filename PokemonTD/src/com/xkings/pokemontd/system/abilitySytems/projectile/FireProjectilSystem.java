package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.attack.projectile.ProjectileComponent;
import com.xkings.pokemontd.manager.ProjectileManager;
import com.xkings.pokemontd.system.ClosestEnemySystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class FireProjectilSystem extends EntityProcessingSystem {

    private final ClosestEnemySystem closestEnemySystem;
    private final ProjectileManager projectileManager;
    @Mapper
    ComponentMapper<SpeedComponent> speedMapper;
    @Mapper
    ComponentMapper<ProjectileComponent> projectileMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;


    public FireProjectilSystem(ClosestEnemySystem closestEnemySystem, ProjectileManager projectileManager) {
        super(Aspect.getAspectForAll(TimeComponent.class, SpeedComponent.class, ProjectileComponent.class,
                PositionComponent.class));
        this.closestEnemySystem = closestEnemySystem;
        this.projectileManager = projectileManager;
    }


    @Override
    protected void process(Entity entity) {
        Time time = timeMapper.get(entity).getTime(this.getClass());
        float speed = speedMapper.get(entity).getSpeed();

        time.increase(world.getDelta());
        while (time.getAvailableTime() >= speed) {
            time.decrease(speed);
            run(entity);
        }
    }

    private void run(Entity entity) {
        Vector3 position = positionMapper.get(entity).getPoint();
        float range = rangeMapper.get(entity).getRange();
        float speed = speedMapper.get(entity).getSpeed();

        closestEnemySystem.start(entity, position, range);
        Entity closestEnemy = closestEnemySystem.getClosestEntity();
        if (closestEnemy != null) {
            Vector3 closestEnemyPosition = positionMapper.get(closestEnemy).getPoint();
            float damage = damageMapper.get(entity).getDamage();
            ProjectileComponent projectileType = projectileMapper.get(entity);
            projectileManager.createProjectile(projectileType, position, damage, speed,closestEnemyPosition,
                    closestEnemy);
        }
    }

}
