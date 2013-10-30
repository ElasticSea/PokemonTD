package com.xkings.pokemontd.system.abilitySytems.damage.hit;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.*;
import com.xkings.core.utils.Collision;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.buff.BuffableDamageComponent;
import com.xkings.pokemontd.component.attack.projectile.HitAbility;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class HitSystem<T extends EffectData> extends EntityProcessingSystem {

    private final Class<T> effectDataClass;
    @Mapper
    ComponentMapper<BuffableDamageComponent> damageMapper;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<TargetComponent> targetMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<HitAbility> projectileMapper;

    private ComponentMapper<T> effectDataMapper;
    private AoeSystem aoe;
    private T effectData;
    private Entity entity;
    private Entity target;


    public HitSystem(Class<T> effectDataClass) {
        super(Aspect.getAspectForAll(TargetComponent.class, effectDataClass));
        this.effectDataClass = effectDataClass;
    }

    @Override
    protected void initialize() {
        super.initialize();
        effectDataMapper = world.getMapper(effectDataClass);
    }


    public void setAoe(AoeSystem aoe) {
        this.aoe = aoe;
        world.setSystem(this.aoe, true);
    }

    @Override
    protected void process(Entity e) {
        Entity target = targetMapper.get(e).getTarget();

        if (positionMapper.get(target) == null) {
            e.deleteFromWorld();
            return;
        }
        HitAbility hitAbility = projectileMapper.get(e);
        if (hitAbility.getType().equals(HitAbility.Type.IMMEDIATE_ATTACK)) {
            hit(effectDataMapper.get(e), e, target);
        }

        Vector3 entityPosition = positionMapper.get(e).getPoint();
        Vector3 targetPosition = positionMapper.get(target).getPoint();
        Vector3 entitySize = sizeMapper.get(e).getPoint();
        Vector3 targetSize = sizeMapper.get(target).getPoint();

        if (Collision.intersectRects(entityPosition, targetPosition, entitySize, targetSize)) {
            this.effectData = effectDataMapper.get(e);
            this.entity = e;
            this.target = target;
            if (hitAbility.getAoe() > 0) {
                aoe.start(targetPosition, hitAbility.getAoe());
            } else {
                hit(effectDataMapper.get(e), e, target);
            }
            e.deleteFromWorld();
        }
    }

    protected abstract void hit(T effectData, Entity e, Entity target);

    public abstract class AoeSystem extends EntityProcessingSystem {

        @Mapper
        ComponentMapper<PositionComponent> positionMapper;
        private float aoe;
        private Vector3 position;

        public AoeSystem() {
            super(Aspect.getAspectForAll(HealthComponent.class, PositionComponent.class));
        }

        @Override
        protected void begin() {
            System.out.println("LOL NOBODY: "+getActives().size());
        }

        @Override
        protected void process(Entity e) {
            if (position.dst(positionMapper.get(e).getPoint()) <= aoe) {
                hit(effectData, entity, e);
            }
        }


        public void start(Vector3 position, float aoe) {
            this.position = position;
            this.aoe = aoe;
            this.process();
        }

    }

}