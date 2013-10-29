package com.xkings.pokemontd.system.resolve;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.xkings.pokemontd.component.ShopComponent;

public class FindShop extends EntitySystem {
    private Entity shop;

    /**
     * Finds desired shop on given coordinates
     */
    public FindShop() {
        super(Aspect.getAspectForAll(ShopComponent.class));
    }


    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        shop = null;
        if (entities.size() != 0) {
            shop = entities.get(0);
        }
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    public Entity getShop() {
        return shop;
    }
}

