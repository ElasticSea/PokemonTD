package com.pixelthieves.elementtd.system.resolve.ui;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.Player;
import com.pixelthieves.elementtd.component.ShopComponent;
import com.pixelthieves.elementtd.component.SpriteComponent;

public class LightUpShops extends EntityProcessingSystem {
    private final Player player;
    private final App app;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;
    private boolean lightUp;

    public LightUpShops(App app, Player player) {
        super(Aspect.getAspectForAll(ShopComponent.class));
        this.app = app;
        this.player = player;
    }

    @Override
    protected void process(Entity e) {
        SpriteComponent spriteComponent = spriteMapper.get(e);
        spriteComponent.clear();
        String mapType = app.getMapType().toString().toLowerCase();
        spriteComponent.add("towers/" + mapType + (lightUp ? "on" : "off"));

    }

    public void start() {
        this.lightUp = player.getFreeElements() > 0;
        this.process();
    }
}

