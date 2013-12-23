package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.logic.Clock;

/**
 * Created by Tomas on 11/6/13.
 */
public class SpeedControls extends DisplayBlock {
    private final Button slower;
    private final Button speed;
    private final Button faster;
    private final Clock clock;
    private float currentMultiplierCache;
    private String currentMultiplierTextCache;

    public SpeedControls(Gui ui,  final Clock clock) {
        super(ui, new Rectangle());
        this.clock = clock;
        slower = new Button(ui, new Rectangle(), ui.getFont(), BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(clock.getSpeedMultiplier() / 2);

            }
        };
        speed = new Button(ui, new Rectangle(), ui.getFont(),
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(1);
            }
        };

        faster = new Button(ui, new Rectangle(), ui.getFont(),
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(clock.getSpeedMultiplier() * 2);
            }
        };
        ui.register(slower);
        ui.register(speed);
        ui.register(faster);
    }

    @Override
    public void render() {
        slower.render("<<");
        double currentMultiplier = clock.getSpeedMultiplier();
        if (currentMultiplierCache != clock.getSpeedMultiplier()) {
            currentMultiplierCache = clock.getSpeedMultiplier();
            currentMultiplierTextCache =
                    "x" + (currentMultiplier >= 1 ? (int) currentMultiplier : "1/" + (int) (1 / currentMultiplier));
        }
        speed.render(currentMultiplierTextCache);
        faster.render(">>");
    }

    @Override
    public void refresh() {
        int buttonWidth = (int) (width / 3);
        slower.set(x, y, buttonWidth, height);
        slower.refresh();
        speed.set(x + buttonWidth, y, buttonWidth, height);
        speed.refresh();
        faster.set(x + buttonWidth * 2, y, buttonWidth, height);
        faster.refresh();
    }
}
