package com.xkings.pokemontd.system.autonomous;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.component.PositionComponent;
import com.xkings.core.component.RotationComponent;
import com.xkings.core.component.SizeComponent;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TintComponent;

/**
 * Created by Tomas on 10/4/13.
 */
public class RenderSpriteSystem extends EntityProcessingSystem {
    private final Camera camera;
    private final SpriteBatch spriteBatch = new SpriteBatch();

    @Mapper
    ComponentMapper<PositionComponent> positionMapper;
    @Mapper
    ComponentMapper<SizeComponent> sizeMapper;
    @Mapper
    ComponentMapper<RotationComponent> rotationMapper;
    @Mapper
    ComponentMapper<SpriteComponent> spriteMapper;
    @Mapper
    ComponentMapper<TintComponent> tintMapper;

    public RenderSpriteSystem(Camera camera) {
        super(Aspect.getAspectForAll(PositionComponent.class, SizeComponent.class, SpriteComponent.class));
        this.camera = camera;
    }

    @Override
    protected void process(Entity e) {
        PositionComponent positionComponent = positionMapper.get(e);
        Vector3 size = sizeMapper.get(e).getPoint();
        TextureAtlas.AtlasRegion sprite = spriteMapper.get(e).getSprite();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        float x = positionComponent.getPoint().x - size.x / 2f;
        float y = positionComponent.getPoint().y - size.y / 2f;
        if (tintMapper.has(e)) {
            spriteBatch.setColor(tintMapper.get(e).getTint());
        } else {
            spriteBatch.setColor(Color.WHITE);
        }
        if (rotationMapper.has(e)) {
            Vector3 rotation = rotationMapper.get(e).getPoint();
            spriteBatch.draw(sprite, x, y, size.x / 2f, size.y / 2f, size.x, size.y, 1f, 1f, rotation.x);
        } else {
            spriteBatch.draw(sprite, x, y, size.x, size.y);
        }
        spriteBatch.end();
    }

}
