package com.xkings.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RangeComponent;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.system.resolve.ClosestSystem;
import com.xkings.pokemontd.system.resolve.PickEntitySystem;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class ApplyAbilitySystem<T extends AbilityComponent> extends IntervalAbilitySystem<T> {

    private Class<? extends PickEntitySystem> resolveTargetSystem;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;

    private PickEntitySystem closestSystem;


    public ApplyAbilitySystem(Class<T> ability, Class<? extends PickEntitySystem> resolveTargetSystem) {
        super(ability);
        this.resolveTargetSystem = resolveTargetSystem;
    }

    @Override
    protected void initialize() {
        super.initialize();
        if (resolveTargetSystem != null) {
            this.closestSystem = world.getSystem(resolveTargetSystem);
        }
    }

    @Override
    protected void run(T ability, Entity entity) {
        Vector3 position = positionMapper.get(entity).getPoint();
        float range = rangeMapper.get(entity).getRange();

        if (resolveTargetSystem != null && resolveTargetSystem != closestSystem.getClass()) {
            this.closestSystem = world.getSystem(resolveTargetSystem);
        }
        closestSystem.start(entity, position, range);
        Entity closestEnemy = closestSystem.getPickedEntity();
        if (closestEnemy != null) {
            processTarget(ability, entity, closestEnemy);
        }
    }

    public void setClosestEntityAlgorithm(Class<? extends ClosestSystem> resolveTargetSystem) {
        this.resolveTargetSystem = resolveTargetSystem;
    }

    protected abstract void processTarget(T ability, Entity entity, Entity target);
}
