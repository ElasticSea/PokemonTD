package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.Player;


/**
 * Created by Tomas on 10/11/13.
 */
public class StatusBar extends GuiBox {
    private final Player player;
    private final DisplayText scoreTitleText;
    private final DisplayText scoreText;
    private final SpeedControls speedControls;
    private final Button menuButton;

    StatusBar(final Ui ui, Rectangle rectangle, float squareHeight, BitmapFont font) {
        super(ui, rectangle);
        ui.register(this);
        this.player = ui.getPlayer();
        Rectangle rect = new Rectangle(x + width - squareHeight, offsetRectange.y, squareHeight - 3 * offset,
                offsetRectange.height);
        menuButton = new Button(ui, offsetRectange.x, offsetRectange.y, offsetRectange.height*2, offsetRectange.height,
                ui.getFont()) {
            @Override
            public void process(float x, float y) {
                ui.getApp().triggerMenu(Menu.Type.INGAME);
            }
        };
        speedControls = new SpeedControls(ui, offsetRectange.x+offsetRectange.height*2, offsetRectange.y,  height*4.5f, offsetRectange.height, ui.getApp().getClock());
       // menuUi = new MenuUi(ui, x, y , height*4.5f, height, font);
        scoreTitleText = new DisplayText(ui, rect, font, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(ui, rect, font, BitmapFont.HAlignment.RIGHT);
        ui.register(menuButton);
    }

    @Override
    public void render() {
        super.render();
        scoreTitleText.render("Score");
        scoreText.render(player.getScore().toString());
        speedControls.render();
        menuButton.render("MENU");
    }

}
