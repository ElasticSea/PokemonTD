package com.xkings.pokemontd.system.abilitySytems.damage;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.pokemontd.component.HealthComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public abstract class AoeSystem extends EntityProcessingSystem {

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    private float aoe;
    private Vector3 position;

    public AoeSystem() {
        super(Aspect.getAspectForAll(PositionComponent.class));
    }

    @Override
    protected void begin() {
        System.out.println(getActives().size());
    }

    @Override
    protected void process(Entity entity) {
        throw new Error();
       /* if (position.dst(positionMapper.get(entity).getPoint()) <= aoe) {
            processAoe(entity);
        }          */
    }

    protected abstract void processAoe(Entity entity);

    public void start(Vector3 position, float aoe) {
        this.position = position;
        this.aoe = aoe;
        this.process();
    }

}
