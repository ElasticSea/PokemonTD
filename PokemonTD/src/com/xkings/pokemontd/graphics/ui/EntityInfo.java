package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TowerTypeComponent;

/**
 * Created by Tomas on 10/8/13.
 */
class EntityInfo extends DisplayBlock {

    private final SpriteBatch spriteBatch;
    private Entity entity;

    EntityInfo(Rectangle rectangle, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void render() {
        if (entity != null) {
            if (entity.getComponent(TowerTypeComponent.class) != null) {
                renderTower(entity);
            } else {
                renderCreep(entity);
            }
        }
    }

    private void drawText(String text, float x, float y) {
        App.getAssets().getPixelFont().draw(spriteBatch, text, rectangle.x + x, rectangle.y + y);
    }

    @Override
    public void process(float x, float y) {
    }

    public void update(Entity entity) {
        this.entity = entity;
    }

    private void renderCreep(Entity entity) {
        TextureAtlas.AtlasRegion atlasRegion = entity.getComponent(SpriteComponent.class).getSprite();
        renderCommon(atlasRegion, "Test", "Test", "Test");
    }

    private void renderTower(Entity entity) {
        TextureAtlas.AtlasRegion atlasRegion = entity.getComponent(SpriteComponent.class).getSprite();
        renderCommon(atlasRegion, "Test", "Test", "Test");
    }

    private void renderCommon(TextureAtlas.AtlasRegion atlasRegion, String name, String textA, String textB) {
        App.getAssets().getPixelFont().setScale(rectangle.height / 8 / 32);
        spriteBatch.begin();
        float offset = rectangle.height / 4;
        spriteBatch.draw(atlasRegion, rectangle.x + offset, rectangle.y + offset, offset * 2, offset * 2);
        drawText(name, rectangle.height, rectangle.height - offset);
        drawText(textA, rectangle.height, rectangle.height - offset * 2);
        drawText(textB, rectangle.height + offset * 3, rectangle.height - offset * 2);
        spriteBatch.end();
    }
}