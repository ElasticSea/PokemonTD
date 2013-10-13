package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.HealthComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitProjectileSystem extends EntityProcessingSystem {

  /*  @Mapper
    ComponentMapper<AttackComponent> damageMapper;    */
    @Mapper
    ComponentMapper<HealthComponent> healthMapper;
    @Mapper
    ComponentMapper<RotationComponent> rotationMapper;
    @Mapper
    ComponentMapper<TimeComponent> timeMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;


    public HitProjectileSystem() {
        super(Aspect.getAspectForAll(TargetComponent.class));
    }


    @Override
    protected void process(Entity entity) {
        Entity target = targetMapper.get(entity).getTarget();

        if (positionMapper.get(target) == null) {
            entity.deleteFromWorld();
            return;
        }
        Vector3 position = positionMapper.get(entity).getPoint();
        Vector3 position1 = positionMapper.get(target).getPoint();
        Vector3 size = sizeMapper.get(entity).getPoint();
        Vector3 size1 = sizeMapper.get(target).getPoint();

      /*  int attack = damageMapper.get(entity).getAttack();

        if (Collision.intersectRects(position, position1, size, size1)) {
            entity.deleteFromWorld();
            healthMapper.get(target).getHealth().decrees(attack);
        }   */
    }

}
