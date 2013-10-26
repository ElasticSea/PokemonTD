package com.xkings.pokemontd.system.trigger;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RangeComponent;
import com.xkings.pokemontd.component.attack.AbilityComponent;
import com.xkings.pokemontd.system.ClosestSystem;

/**
 * Created by Tomas on 10/4/13.
 */
public class ApplyAbilitySystem extends IntervalAbilitySystem {

    private Class<? extends ClosestSystem> target;
    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<RangeComponent> rangeMapper;
    private ClosestSystem closestSystem;


    public ApplyAbilitySystem(Class<? extends AbilityComponent> ability, Class<? extends ClosestSystem> target) {
        super(ability);
        this.target = target;
    }

    @Override
    protected void initialize() {
        if (target != null) {
            this.closestSystem = world.getSystem(target);
        }
    }

    @Override
    protected void run(Entity entity) {
        Vector3 position = positionMapper.get(entity).getPoint();
        float range = rangeMapper.get(entity).getRange();

        if (target != null && target != closestSystem.getClass()) {
            this.closestSystem = world.getSystem(target);
        }
        closestSystem.start(entity, position, range);
        Entity closestEnemy = closestSystem.getClosestEntity();
        if (closestEnemy != null) {
            processTarget(entity, closestEnemy);
        }
    }

    public void setClosestEntityAlgorithm(Class<? extends ClosestSystem> target) {
        this.target = target;
    }

    protected void processTarget(Entity entity, Entity target) {

    }
}
