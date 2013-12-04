package com.pixelthieves.pokemontd.system.resolve.ui;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pixelthieves.pokemontd.component.ShopComponent;
import com.pixelthieves.pokemontd.component.SpriteComponent;

public class LightUpShops extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;
    private boolean lightUp;

    public LightUpShops() {
        super(Aspect.getAspectForAll(ShopComponent.class));
    }

    @Override
    protected void process(Entity e) {
        SpriteComponent spriteComponent = spriteMapper.get(e);
        spriteComponent.clear();
        spriteComponent.add(lightUp ? "litshop" : "shop");

    }

    public void start(boolean lightUp) {
        this.lightUp = lightUp;
        this.process();
    }
}

