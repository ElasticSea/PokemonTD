package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.logic.Clock;

/**
 * Created by Tomas on 11/6/13.
 */
public class SpeedControls implements Renderable {
    private final Button slower;
    private final Button speed;
    private final Button faster;
    private final Clock clock;

    public SpeedControls(Gui ui, float x, float y, float width, float height, final Clock clock) {
        this.clock = clock;
        int buttonWidth = (int) (width / 3);
        slower = new Button(ui, new Rectangle(x, y, buttonWidth, height), ui.getFont(),
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(clock.getSpeedMultiplier()/2);

            }
        };
        speed = new Button(ui, new Rectangle(x + buttonWidth, y, buttonWidth, height), ui.getFont(),
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(1);
            }
        };

        faster = new Button(ui, new Rectangle(x+buttonWidth*2, y, buttonWidth, height), ui.getFont(),
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(clock.getSpeedMultiplier()*2);
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
        speed.render("x" + (currentMultiplier >= 1 ? (int) currentMultiplier : "1/" + (int) (1 / currentMultiplier)));
        faster.render(">>");
    }
}
