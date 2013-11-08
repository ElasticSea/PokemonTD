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
    private Rectangle square;

    StatusBar(final Ui ui, Rectangle rectangle, Rectangle square, BitmapFont font) {
        super(ui, rectangle);
        ui.register(this);
        this.player = ui.getPlayer();
        this.square = square;
        Rectangle scoreRect = new Rectangle(x + width - square.height, offsetRectange.y, square.height - 3 * offset,
                offsetRectange.height);
        menuButton =
                new Button(ui, offsetRectange.x, offsetRectange.y, offsetRectange.height * 2, offsetRectange.height,
                        ui.getFont()) {
                    @Override
                    public void process(float x, float y) {
                        ui.getApp().triggerMenu(Menu.Type.INGAME);
                    }
                };
        speedControls =
                new SpeedControls(ui, offsetRectange.x + offsetRectange.height * 2, offsetRectange.y, height * 4.5f,
                        offsetRectange.height, ui.getApp().getClock());
        // menuUi = new MenuUi(ui, x, y , height*4.5f, height, font);
        scoreTitleText = new DisplayText(ui, scoreRect, font, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(ui, scoreRect, font, BitmapFont.HAlignment.RIGHT);
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

    @Override
    public void refresh() {
        super.refresh();
        Rectangle scoreRect = new Rectangle(x + width - square.height, offsetRectange.y, square.height - 3 * offset,
                offsetRectange.height);
        scoreTitleText.set(scoreRect);
        scoreText.set(scoreRect);
        menuButton.set(offsetRectange.x, offsetRectange.y, offsetRectange.height * 2, offsetRectange.height);
        menuButton.refresh();
        speedControls.set(offsetRectange.x + offsetRectange.height * 2, offsetRectange.y, height * 4.5f,
                offsetRectange.height);
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
