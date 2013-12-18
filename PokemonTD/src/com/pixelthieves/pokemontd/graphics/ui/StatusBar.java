package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.graphics.ui.menu.Menu;


/**
 * Created by Tomas on 10/11/13.
 */
public class StatusBar extends GuiBox {
    private final Player player;
    private final DisplayText scoreTitleText;
    private final DisplayText scoreText;
    private final Button menuButton;
    private final GuiBox menuBackground;
    private Rectangle square;
    private int scoreCache = -1;
    private String scoreTextCache;

    StatusBar(final Ui ui, final Menu otherUi, Rectangle rectangle, Rectangle square, BitmapFont font) {
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
        scoreTitleText = new DisplayText(ui, new Rectangle(), font, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(ui, new Rectangle(), font, BitmapFont.HAlignment.RIGHT);
        otherUi.setMenu(menuButton);
        refresh();
    }

    @Override
    public void render() {
        super.render();
        if (scoreCache != player.getScore()) {
            scoreCache = player.getScore();
            scoreTextCache = scoreCache + "";
        }
        scoreTitleText.render("Score");
        scoreText.render(scoreTextCache);
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
        menuBackground.refresh();
        menuButton.refresh();
        scoreTitleText.refresh();
        scoreText.refresh();
    }

    public void setSquare(Rectangle square) {
        this.square = square;
    }
}
