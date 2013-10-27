package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.Player;


/**
 * Created by Tomas on 10/11/13.
 */
public class StatusBar extends GuiBox {
    private final Player player;
    private final DisplayText scoreTitleText;
    private final DisplayText scoreText;

    StatusBar(Player player, Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
              float squareHeight) {
        super(rectangle, offset, shapeRenderer);
        this.player = player;
        Rectangle rect = new Rectangle(x + width - squareHeight, offsetRectange.y, squareHeight - 3 * offset,
                offsetRectange.height);
        scoreTitleText = new DisplayText(rect, shapeRenderer, spriteBatch, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(rect, shapeRenderer, spriteBatch, BitmapFont.HAlignment.RIGHT);
    }

    @Override
    public void render() {
        super.render();
        scoreTitleText.render("Score");
        scoreText.render(player.getScore().toString());
    }

}
