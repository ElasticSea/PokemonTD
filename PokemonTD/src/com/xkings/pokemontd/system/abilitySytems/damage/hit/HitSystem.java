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
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.DamageComponent;
import com.xkings.pokemontd.component.HealthComponent;
import com.xkings.pokemontd.component.attack.effects.AbstractEffect;
import com.xkings.pokemontd.component.attack.projectile.ProjectileAbility;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class HitSystem<T extends EffectData, V extends AbstractEffect> extends EntityProcessingSystem {

    private final Class<T> effectDataClass;
    private final Class<V> effectClass;
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
    private ComponentMapper<V> effectMapper;
    private AoeSystem aoe;
    private T effectData;
    private Entity entity;
    private Entity target;


    public HitSystem(Class<T> effectDataClass, Class<V> effectClass) {
        super(Aspect.getAspectForAll(TargetComponent.class, effectDataClass));
        this.effectDataClass = effectDataClass;
        this.effectClass = effectClass;
    }

    @Override
    protected void initialize() {
        super.initialize();
        effectDataMapper = world.getMapper(effectDataClass);
        effectMapper = world.getMapper(effectClass);
    }

    public void initAoe() {
        aoe = new AoeSystem() {
        };
        world.setSystem(aoe, true);
    }

    @Override
    protected void process(Entity e) {
        Entity target = targetMapper.get(e).getTarget();

        if (positionMapper.get(target) == null) {
            e.deleteFromWorld();
            return;
        }
        ProjectileAbility projectileAbility = projectileMapper.get(e);
        if (projectileAbility.getType().equals(ProjectileAbility.Type.IMMEDIATE_ATTACK)) {
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
            if (projectileAbility.getAoe() > 0) {
                aoe.start(entityPosition, projectileAbility.getAoe());
            } else {
                hit(effectDataMapper.get(e), e, target);
            }
            e.deleteFromWorld();
        }
    }

    private void hit(T effectData, Entity e, Entity target) {
        V effect = effectMapper.get(target);
        if (effect == null) {
            if (App.CHANCE.happens(effectData.getChance())) {
                target.addComponent(createEffect(e, effectData));
                world.changedEntity(target);
            }
        } else {
            effect.reset();
        }
    }

    protected abstract Component createEffect(Entity e, T effectData);


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
            System.out.println(getActives().size());
        }

        @Override
        protected void process(Entity e) {
            if (position.dst(positionMapper.get(entity).getPoint()) <= aoe) {
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
