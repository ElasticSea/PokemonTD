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

   // private final Icon muteButton;
    private final Clock clock;
    private final SpeedControls speedControls;

    public MenuUi(Ui ui, float x, float y, float width, float height, BitmapFont font) {
        this(ui, new Rectangle(x, y, width, height), font);
    }

    public MenuUi(final Ui ui, Rectangle rect, BitmapFont font) {
        super(ui, rect);
        clock = ui.getApp().getClock();
        //muteButton = new MuteButton(ui, offsetRectange.x, offsetRectange.y, size, size) ;
       speedControls = new SpeedControls(ui, offsetRectange.x, offsetRectange.y, offsetRectange.width, offsetRectange.height, clock);
     //   ui.register(muteButton);
    }


    @Override
    public void render() {
      //  super.render();
     //   muteButton.render(Assets.getTexture(mute ? "unmute" : "mute"), "", height * 0.5f, true);
        speedControls.render();
    }
}
