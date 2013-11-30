package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/30/13.
 */
public class SpeedControlsBar extends GuiBox{
    private final SpeedControls speedControls;

    public SpeedControlsBar(Ui ui, float x, float y, float width, float height) {
        super(ui, new Rectangle(x,y,width,height));
        speedControls = new SpeedControls(ui,ui.getApp().getClock());
    }

    @Override
    public void render() {
        super.render();
        speedControls.render();
    }

    @Override
    public void refresh() {
        super.refresh();
        speedControls.set(this);
        speedControls.refresh();
    }
}
