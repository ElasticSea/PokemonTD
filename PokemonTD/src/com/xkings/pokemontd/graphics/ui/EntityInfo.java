package com.xkings.pokemontd.graphics.ui;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.SpriteComponent;
import com.xkings.pokemontd.component.TowerTypeComponent;
import com.xkings.pokemontd.component.WaveComponent;

/**
 * Created by Tomas on 10/8/13.
 */
class EntityInfo extends InteractiveBlock {

    private final SpriteBatch spriteBatch;
    private final Button sell;
    private final DisplayText textA;
    private final DisplayText textB;
    private Entity entity;
    private float offset;

    EntityInfo(final Ui ui, Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        offset = rectangle.height / 4;
        textA = new DisplayText(new Rectangle(rectangle.x + offset * 3, rectangle.y + offset, offset * 2, offset),
                shapeRenderer, spriteBatch, "textA");
        textB = new DisplayText(new Rectangle(rectangle.x + offset * 3, rectangle.y + offset * 2, offset * 2, offset),
                shapeRenderer, spriteBatch, "textB");
        sell = new Button(new Rectangle(rectangle.x + offset * 5, rectangle.y + offset, offset * 2, offset),
                shapeRenderer, spriteBatch, "SELL") {
            @Override
            public void process(float x, float y) {
                ui.getTowerManager().sellTower();
            }
        };
        ui.register(sell);
    }

    @Override
    public void render() {
        if (entity != null) {
            if (entity.getComponent(TowerTypeComponent.class) != null) {
                renderTower(entity);
            } else if (entity.getComponent(WaveComponent.class) != null) {
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
        renderCommon(atlasRegion, "Test");
    }

    private void renderTower(Entity entity) {
        TextureAtlas.AtlasRegion atlasRegion = entity.getComponent(SpriteComponent.class).getSprite();
        renderCommon(atlasRegion, "Test");
        sell.render();
    }

    private void renderCommon(TextureAtlas.AtlasRegion atlasRegion, String name) {
        App.getAssets().getPixelFont().setScale(rectangle.height / 8 / 32);
        spriteBatch.begin();
        spriteBatch.draw(atlasRegion, rectangle.x + offset, rectangle.y + offset, offset * 2, offset * 2);
        spriteBatch.end();
        // drawText(name, rectangle.height, rectangle.height - offset);
        textA.render();
        textB.render();
    }


}