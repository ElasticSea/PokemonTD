package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
class GuiBox extends InteractiveBlock {

    public static final float darker = 0.0f;
    public static final float lighter = 0.1f;
    public static final Color darkerColor = new Color(darker, darker, darker, 1f);
    public static final Color lighterColor = new Color(lighter, lighter, lighter, 1f);
    protected final int offset;
    protected final ShapeRenderer shapeRenderer;
    protected final Rectangle offsetRectange;
    protected final SpriteBatch spriteBatch;
    protected final Ui ui;

    GuiBox(Ui ui,Rectangle rectangle) {
        super(rectangle);
        this.ui = ui;
        this.offset = ui.getOffset();
        this.shapeRenderer = ui.getShapeRenderer();
        this.spriteBatch = ui.getSpriteBatch();
        this.offsetRectange = new Rectangle(x + offset, y + offset, width - offset * 2, height - offset * 2);
    }

    @Override
    public void render() {
        drawRect(offset);
    }

    private void drawRect(int offset) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(darkerColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(lighterColor);
        shapeRenderer.rect(x + offset, y + offset, width - offset * 2, height - offset * 2);
        shapeRenderer.end();
    }

    @Override
    public void process(float x, float y) {

    }
}