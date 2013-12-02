package com.pixelthieves.pokemontd.graphics.tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.ui.*;

/**
 * Created by Tomas on 12/2/13.
 */
public class Notice extends DisplayBlock {

    private final Orientation orientation;
    private final String message;
    private final int maxWidth;
    private final DisplayText text;
    private final Icon arrow;
    private final GuiBox backgroud;
    private final Placement placement;
    private final Rectangle target;
    private boolean firstRender = true;

    public enum Orientation {
        TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    public enum Placement {
        RELATIVE, STATIC;
    }

    public Notice(Gui gui, Rectangle target, Orientation orientation, String message, Placement placement) {
        super(gui);
        switch (orientation) {
            case TOP_LEFT:
                this.x = target.x + target.width;
                this.y = target.y;
                break;
            case TOP_RIGHT:
                this.x = target.x;
                this.y = target.y;
                break;
            case BOTTOM_LEFT:
                this.x = target.x + target.width;
                this.y = target.y + target.height;
                break;
            case BOTTOM_RIGHT:
                this.x = target.x;
                this.y = target.y + target.height;
                break;
        }
        this.target = target;
        this.orientation = orientation;
        this.message = message;
        this.placement = placement;
        this.maxWidth = Gdx.graphics.getWidth() / 4;
        this.text = new DisplayText(gui, new Rectangle(), gui.getFont(), BitmapFont.HAlignment.CENTER);
        this.backgroud = new GuiBox(gui, new Rectangle());
        arrow = new Icon(gui, new Rectangle()) {
            @Override
            public void process(float x, float y) {

            }
        };
        refresh();

        if (placement.equals(Placement.RELATIVE)) {
            setRenderer(this);
            setRenderer(backgroud);
            setRenderer(text);
            setRenderer(arrow);
        }
    }

    @Override
    public void render() {
        super.render();
        backgroud.render();
        text.render(message);
        arrow.render(Assets.getTexture(("arrow_" + orientation).toLowerCase().replaceAll("_", "")), "");

        this.shapeRenderer.setColor(Color.WHITE);
        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        this.shapeRenderer.rect(target.x, target.y, target.width, target.height);
        this.shapeRenderer.end();

        if (firstRender) {
            refresh();
            firstRender = false;
        }
    }

    private void setRenderer(DisplayBlock renderer) {
        renderer.setShapeRenderer(gui.getApp().getGameShapeRenderer());
        renderer.setSpriteBatch(gui.getApp().getGameSpriteBatch());
    }

    @Override
    public void refresh() {
        float ingameSize = App.WORLD_SCALE / 2;
        float inguiSize = gui.getSquareSize() / 4;
        float compensatingRatio = ingameSize / inguiSize;
        int arrowSize = (int) (placement.equals(Placement.RELATIVE) ? ingameSize : inguiSize);
        Vector2 offset = new Vector2();
        float textOffset = 0;
        switch (orientation) {
            case TOP_LEFT:
                offset.y = -1;
                textOffset = 1;
                break;
            case TOP_RIGHT:
                offset.y = -1;
                offset.x = -1;
                textOffset = -1;
                break;
            case BOTTOM_LEFT:
                textOffset = 1;
                break;
            case BOTTOM_RIGHT:
                offset.x = -1;
                textOffset = -1;
                break;
        }
        arrow.set(x + offset.x * arrowSize, y + offset.y * arrowSize, arrowSize, arrowSize);
        Vector2 textSize = new Vector2(text.getTextWidth() + arrowSize, Math.max(text.getTextHeight(), arrowSize));
        text.set(x + textOffset * arrowSize + offset.x * textSize.x, y + offset.y * textSize.y, textSize.x, textSize.y);
        if (placement.equals(Placement.RELATIVE)) {
            text.setScale(compensatingRatio);
        }
        backgroud.set(text);
        text.refresh();
    }
}
