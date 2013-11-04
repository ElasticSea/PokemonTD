package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.logic.Clock;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/28/13.
 */
public class MenuUi extends GuiBox {

    private final Music theme;
    private final Icon muteButton;
    private final Button slower;
    private final DisplayText speed;
    private final Button faster;
    private final Clock clock;
    private boolean mute;

    public MenuUi(Ui ui, float x, float y, float width, float height, BitmapFont font) {
        this(ui, new Rectangle(x, y, width, height), font);
    }

    public MenuUi(final Ui ui, Rectangle rect, BitmapFont font) {
        super(ui, rect);
        clock = ui.getApp().getClock();
        float size = offsetRectange.width / 4f;
        muteButton = new Icon(ui, new Rectangle(offsetRectange.x, offsetRectange.y, size, size)) {

            @Override
            public void process(float x, float y) {
                if (!mute) {
                    mute = true;
                    theme.setVolume(0);
                } else {
                    mute = false;
                    theme.setVolume(1);
                }
            }
        };
        slower = new Button(ui, new Rectangle(offsetRectange.x + size, offsetRectange.y, size, size), font,
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(clock.getSpeedMultiplier()/2);

            }
        };
        speed = new DisplayText(ui, new Rectangle(offsetRectange.x + size * 2, offsetRectange.y, size, size), font,
                BitmapFont.HAlignment.CENTER);

        faster = new Button(ui, new Rectangle(offsetRectange.x + size * 3, offsetRectange.y, size, size), font,
                BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                clock.setSpeedMultiplier(clock.getSpeedMultiplier()*2);
            }
        };
        theme = Assets.getMusic("mainTheme.ogg");
        theme.setLooping(true);
        theme.play();
        Ui.register(muteButton);
        Ui.register(slower);
        Ui.register(faster);
    }


    @Override
    public void render() {
        super.render();
        muteButton.render(Assets.getTexture(mute ? "unmute" : "mute"), "", height * 0.5f, true);
        slower.render("<<");
        double currentMultiplier = clock.getSpeedMultiplier();
        speed.render("x" + (currentMultiplier >= 1 ? (int) currentMultiplier : "1/" + (int) (1 / currentMultiplier)));
        faster.render(">>");
    }
}
