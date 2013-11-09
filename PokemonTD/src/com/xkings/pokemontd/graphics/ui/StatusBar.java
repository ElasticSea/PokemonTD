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
    private final GuiBox menuBackground;
    private Rectangle square;

    StatusBar(final Ui ui, Rectangle rectangle, Rectangle square, BitmapFont font) {
        super(ui, rectangle);
        ui.register(this);
        this.player = ui.getPlayer();
        this.square = square;
        menuBackground = new GuiBox(ui, new Rectangle());
        menuButton = new Button(ui, menuBackground, ui.getFont()) {
            @Override
            public void process(float x, float y) {
                ui.getApp().triggerMenu(Menu.Type.INGAME);
            }
        };
        speedControls =
                new SpeedControls(ui, x + menuBackground.width, y, height * 4.5f, height, ui.getApp().getClock());
        scoreTitleText = new DisplayText(ui, new Rectangle(), font, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(ui, new Rectangle(), font, BitmapFont.HAlignment.RIGHT);
        ui.register(menuButton);
        refresh();
    }

    @Override
    public void render() {
        super.render();
        scoreTitleText.render("Score");
        scoreText.render(player.getScore().toString());
        speedControls.render();
        menuBackground.render();
        menuButton.render("MENU");
    }

    @Override
    public void refresh() {
        super.refresh();
        Rectangle scoreRect = new Rectangle(x + width - square.height, offsetRectange.y, square.height - 3 * offset,
                offsetRectange.height);
        scoreTitleText.set(scoreRect);
        scoreText.set(scoreRect);
        menuBackground.set(x - ui.getOffset(), y - height, height * 2 + ui.getOffset(), height * 2 + ui.getOffset());
        menuButton.set(menuBackground);
        speedControls.set(x + menuButton.width, y, height * 4f, height);
        menuBackground.refresh();
        menuButton.refresh();
        speedControls.refresh();
        scoreTitleText.refresh();
        scoreText.refresh();
    }

    public void setSquare(Rectangle square) {
        this.square = square;
    }

    public Rectangle getSquare() {
        return square;
    }
}
