package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
class GuiBox extends DisplayBlock {

    public static final float darker = 0.0f;
    public static final float lighter = 0.1f;
    public static final Color darkerColor = new Color(darker, darker, darker, 1);
    public static final Color lighterColor = new Color(lighter, lighter, lighter, 1);
    protected final int offset;
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
        shapeRenderer.setColor(darkerColor);
        shapeRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        shapeRenderer.setColor(lighterColor);
        shapeRenderer.rect(rect.x + offset, rect.y + offset, rect.width - offset * 2, rect.height - offset * 2);
        shapeRenderer.end();
    }

    @Override
    public void process(float x, float y) {

    }
}