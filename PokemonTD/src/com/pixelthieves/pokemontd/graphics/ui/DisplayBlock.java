package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.pokemontd.App;

/**
 * Created by Seda on 10/8/13.
 */
public abstract class DisplayBlock extends Rectangle implements Renderable, Refresheable {
    private final ShapeRenderer shapeRenderer;
    protected final Gui gui;

    protected DisplayBlock(Gui gui) {
        this(gui, 0, 0, 0, 0);
    }

    protected DisplayBlock(Gui gui, float x, float y, float width, float height) {
        this(gui, new Rectangle(x, y, width, height));

    }
    protected DisplayBlock(Gui gui, Rectangle rect) {
        super(rect);
        this.gui = gui;
        this.shapeRenderer = gui.getShapeRenderer();
    }

    @Override
    public void render() {
        if (App.DEBUG != null) {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }
    }
}