package com.xkings.pokemontd.system.resolve;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;

public abstract class PickEntitySystem extends EntitySystem implements PickEntity {

    private final boolean sort;

    public PickEntitySystem(Aspect aspect, boolean sort) {
        super(aspect);
        this.sort = sort;
    }

    /**
     * Process a entity this system is interested in.
     *
     * @param e the entity to process.
     */
    protected abstract void process(Entity e);

    @Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
        if (sort) {

        }
        for (int i = 0, s = entities.size(); s > i; i++) {
            process(entities.get(i));
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

}
