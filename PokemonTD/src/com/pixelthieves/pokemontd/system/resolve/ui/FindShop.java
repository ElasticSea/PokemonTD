package com.pixelthieves.pokemontd.system.resolve.ui;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.pixelthieves.pokemontd.component.ShopComponent;

public class FindShop extends EntitySystem {

    private ImmutableBag<Entity> entities;

    public FindShop() {
        super(Aspect.getAspectForAll(ShopComponent.class));
    }

    @Override
    protected final void processEntities(ImmutableBag<Entity> entities) {
        this.entities = entities;
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    public ImmutableBag<Entity> getEntities() {
        return entities;
    }
}

