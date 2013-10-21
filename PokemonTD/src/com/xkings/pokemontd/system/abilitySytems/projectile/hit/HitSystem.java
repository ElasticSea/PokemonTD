package com.xkings.pokemontd.system.abilitySytems.projectile.hit;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.core.component.TargetComponent;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.DamageComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class HitSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;


    public HitSystem(Class<? extends Component> filter) {
        super(Aspect.getAspectForAll(TargetComponent.class, filter));
    }


    @Override
    protected void process(Entity entity) {
        System.out.println(targetMapper);
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
            onHit(entity, target);
            entity.deleteFromWorld();
        }
    }

    public void onHit(Entity entity, Entity target) {
    }

}
