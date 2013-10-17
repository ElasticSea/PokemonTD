package com.xkings.pokemontd.system.abilitySytems.projectile;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.ProjectileComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitProjectileSystem extends EntityProcessingSystem {

    private final AoeSystem aoE;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<ProjectileComponent> projectileMapper;


    public HitProjectileSystem(AoeSystem aoE) {
        super(Aspect.getAspectForAll(TargetComponent.class));
        this.aoE = aoE;
    }


    @Override
    protected void process(Entity entity) {
        Entity target = targetMapper.get(entity).getTarget();

        if (positionMapper.get(target) == null) {
            entity.deleteFromWorld();
            return;
        }
        Vector3 entityPosition = positionMapper.get(entity).getPoint();
        Vector3 targetPosition = positionMapper.get(target).getPoint();
        Vector3 entitySize = sizeMapper.get(entity).getPoint();
        Vector3 targetSize = sizeMapper.get(target).getPoint();

        if (Collision.intersectRects(entityPosition, targetPosition, entitySize, targetSize)) {
            ProjectileComponent projectile = projectileMapper.get(entity);
            float damage = damageMapper.get(entity).getDamage();
            if (projectile.getAoE() > 0) {
                aoE.start(targetPosition, damage, projectile.getAoE());
            } else {
                healthMapper.get(target).getHealth().decrees((int) damage);
            }
            entity.deleteFromWorld();
        }
    }

}
