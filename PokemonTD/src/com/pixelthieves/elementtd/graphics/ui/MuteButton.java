package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.audio.Music;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.elementtd.App;

/**
 * Created by Tomas on 11/6/13.
 */
public class MuteButton extends Icon {
    private final Music theme;
    private boolean mute;

    MuteButton(Gui ui, float x, float y, float width, float height) {
        super(ui, x, y, width, height);
        theme = Assets.getMusic("mainTheme.ogg");
        theme.setLooping(true);
        theme.play();
    }

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

    @Override
    public void render() {
        super.render(App.getAssets().getTexture(mute ? "unmute" : "mute"), "", height * 0.5f, true);
    }
}
