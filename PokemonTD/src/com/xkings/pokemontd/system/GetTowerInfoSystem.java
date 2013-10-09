package com.xkings.pokemontd.system;

import com.artemis.Aspect;
import com.artemis.Component;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RangeComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.SpriteComponent;

public class GetTowerInfoSystem extends EntityProcessingSystem {
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;

    public GetTowerInfoSystem() {
        super(Aspect.getAspectForAll(SpriteComponent.class));
    }

    public TextureAtlas.AtlasRegion getInfo(Entity entity){
        return spriteMapper.get(entity).getSprite();
    }

    @Override
    protected void process(Entity e) {

    }
}
