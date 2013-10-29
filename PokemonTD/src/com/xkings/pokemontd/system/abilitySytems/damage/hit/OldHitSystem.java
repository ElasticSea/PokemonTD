package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Aspect;
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
import com.xkings.pokemontd.component.attack.projectile.ProjectileAbility;
import com.xkings.pokemontd.component.attack.projectile.data.AoeComponent;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class OldHitSystem<T extends EffectData> extends EntityProcessingSystem {

    private final Class<T> effectDataClass;
    @Mapper
    ComponentMapper<DamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<ProjectileAbility> projectileMapper;

    private ComponentMapper<T> effectDataMapper;


    public OldHitSystem(Class<T> effectDataClass) {
        super(Aspect.getAspectForAll(TargetComponent.class, effectDataClass).exclude(AoeComponent.class));
        this.effectDataClass = effectDataClass;
    }

    public OldHitSystem(Class<T> effectDataClass, Class<? extends EffectData> effectData) {
        super(Aspect.getAspectForAll(TargetComponent.class, effectDataClass, effectData));
        this.effectDataClass = effectDataClass;
    }

    @Override
    protected void initialize() {
        super.initialize();
        effectDataMapper = world.getMapper(effectDataClass);
    }

    @Override
    protected void process(Entity e) {
        Entity target = targetMapper.get(e).getTarget();

        if (positionMapper.get(target) == null) {
            e.deleteFromWorld();
            return;
        }
        if (projectileMapper.get(e).getType().equals(ProjectileAbility.Type.IMMEDIATE_ATTACK)) {
            hit(effectDataMapper.get(e), e, target);
        }

        Vector3 entityPosition = positionMapper.get(e).getPoint();
        Vector3 targetPosition = positionMapper.get(target).getPoint();
        Vector3 entitySize = sizeMapper.get(e).getPoint();
        Vector3 targetSize = sizeMapper.get(target).getPoint();

        if (Collision.intersectRects(entityPosition, targetPosition, entitySize, targetSize)) {
            hit(effectDataMapper.get(e), e, target);
        }
    }


    private void hit(T effectData, Entity e, Entity target) {
        onHit(effectData, e, target);
        e.deleteFromWorld();
    }

    public abstract void onHit(T effectData, Entity entity, Entity target);

}
