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
    private final MenuUi menuUi;

    StatusBar(Ui ui, Rectangle rectangle, float squareHeight, BitmapFont font) {
        super(ui, rectangle);
        this.player = ui.getPlayer();
        Rectangle rect = new Rectangle(x + width - squareHeight, offsetRectange.y, squareHeight - 3 * offset,
                offsetRectange.height);
        float height1 = height * 1.5f;
        menuUi = new MenuUi(ui, x, y - height * 0.5f, height1 * 3 + offset * 2, height1, font);
        scoreTitleText = new DisplayText(ui, rect, font, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(ui, rect, font, BitmapFont.HAlignment.RIGHT);
    }

    @Override
    public void render() {
        super.render();
        menuUi.render();
        scoreTitleText.render("Score");
        scoreText.render(player.getScore().toString());
    }

}
