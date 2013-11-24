package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.graphics.ui.menu.Header;

/**
 * Created by Tomas on 10/8/13.
 */
public class HeaderGuiBox extends GuiBox {

    private final Header header;
    private String leftHeaderText = "";
    private String rightHeaderText = "";

    public HeaderGuiBox(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        header = new Header(ui, new Rectangle());
    }


    public void setLeftHeaderText(String leftHeaderText) {
        this.leftHeaderText = leftHeaderText;
    }

    public void setRightHeaderText(String rightHeaderText) {
        this.rightHeaderText = rightHeaderText;
    }

    @Override
    public void render() {
        super.render();
        header.render(leftHeaderText, rightHeaderText);
    }

    @Override
    public void refresh() {
        super.refresh();
        header.set(x, y + height, width, width / 10);
        header.refresh();
    }
}
