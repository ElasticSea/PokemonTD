package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/28/13.
 */
public class MenuUi extends GuiBox {

    private final Music theme;
    private final Icon muteButton;
    private boolean mute;

    public MenuUi(Ui ui, float x, float y, float width, float height) {
        this(ui, new Rectangle(x, y, width, height));
    }

    public MenuUi(Ui ui, Rectangle rect) {
        super(ui, rect);
        muteButton = new Icon(ui,
                new Rectangle(offsetRectange.x, offsetRectange.y, offsetRectange.height, offsetRectange.height)) {

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
        theme = Assets.getMusic("mainTheme.ogg");
        theme.setLooping(true);
        theme.play();
        Ui.register(muteButton);
    }


    @Override
    public void render() {
        super.render();
        muteButton.render(Assets.getTexture(mute ? "unmute" : "mute"), height * 0.5f, true);
    }
}
