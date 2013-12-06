package com.pixelthieves.pokemontd.system.resolve.ui;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.component.ShopComponent;
import com.pixelthieves.pokemontd.component.SpriteComponent;

public class LightUpShops extends EntityProcessingSystem {
    private final Player player;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;
    private boolean lightUp;

    public LightUpShops(Player player) {
        super(Aspect.getAspectForAll(ShopComponent.class));
        this.player = player;
    }

    @Override
    protected void process(Entity e) {
        SpriteComponent spriteComponent = spriteMapper.get(e);
        spriteComponent.clear();
        spriteComponent.add(lightUp ? "towers/litshop" : "towers/shop");

    }

    public void start() {
        this.lightUp = player.getFreeElements() > 0;
        this.process();
    }
}

