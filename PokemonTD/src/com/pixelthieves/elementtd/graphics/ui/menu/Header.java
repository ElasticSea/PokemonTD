package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.graphics.ui.DisplayText;
import com.pixelthieves.elementtd.graphics.ui.Gui;
import com.pixelthieves.elementtd.graphics.ui.SimpleGuiBox;

/**
 * Created by Tomas on 11/21/13.
 */
public class Header extends SimpleGuiBox {
    private final DisplayText leftText;
    private final DisplayText rightText;

    public Header(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        leftText = new DisplayText(ui, new Rectangle(), ui.getFont(), BitmapFont.HAlignment.LEFT);
        rightText = new DisplayText(ui, new Rectangle(), ui.getFont(), BitmapFont.HAlignment.RIGHT);
    }

    public void render(String left, String right) {
        super.render();
        leftText.render(left);
        rightText.render(right);
    }

    @Override
    public Rectangle set(float x, float y, float width, float height) {
        Rectangle rect = super.set(x, y, width, height);
        leftText.set(new Rectangle(rect.x + height / 2f, rect.y, rect.width, rect.height));
        rightText.set(new Rectangle(rect.x - height / 2f, rect.y, rect.width, rect.height));
        return rect;
    }

    @Override
    public void refresh() {
        super.refresh();
        leftText.refresh();
        rightText.refresh();
    }
}
