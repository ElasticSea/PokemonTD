package com.xkings.pokemontd.system.abilitySytems.damage.hit;

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
import com.xkings.pokemontd.component.attack.projectile.ProjectilAbility;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class HitSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<ProjectilAbility> projectileMapper;


    public HitSystem(Class<? extends Component> filter) {
        super(Aspect.getAspectForAll(TargetComponent.class, filter));
    }


    @Override
    protected void process(Entity e) {
        Entity target = targetMapper.get(e).getTarget();

        if (positionMapper.get(target) == null) {
            e.deleteFromWorld();
            return;
        }
        if (projectileMapper.get(e).getType().equals(ProjectilAbility.Type.IMMEDIATE_ATTACK)) {
            hit(e, target);
        }

        Vector3 entityPosition = positionMapper.get(e).getPoint();
        Vector3 targetPosition = positionMapper.get(target).getPoint();
        Vector3 entitySize = sizeMapper.get(e).getPoint();
        Vector3 targetSize = sizeMapper.get(target).getPoint();

        if (Collision.intersectRects(entityPosition, targetPosition, entitySize, targetSize)) {
            hit(e, target);
        }
    }

    private void hit(Entity e, Entity target) {
        onHit(e, target);
        e.deleteFromWorld();
    }

    public abstract void onHit(Entity entity, Entity target);

}
