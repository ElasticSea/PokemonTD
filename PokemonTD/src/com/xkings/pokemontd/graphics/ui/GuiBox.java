package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
class GuiBox extends DisplayBlock {

    private final int offset;
    private final ShapeRenderer shapeRenderer;

    GuiBox(Rectangle rectangle, int offset, ShapeRenderer shapeRenderer) {
        super(rectangle);
        this.offset = offset;
        this.shapeRenderer = shapeRenderer;
    }

    @Override
    public void render() {
        drawRect(rectangle, offset);
    }

    private void drawRect(Rectangle rect, int offset) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(rect.x + offset, rect.y + offset, rect.width - offset * 2, rect.height - offset * 2);
        shapeRenderer.end();
    }

    @Override
    public void process(float x, float y) {

    }
}