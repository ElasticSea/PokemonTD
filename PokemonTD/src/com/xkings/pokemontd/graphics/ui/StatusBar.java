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
    private final MusicUi musicUi;

    StatusBar(Player player, Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
              float squareHeight, BitmapFont font) {
        super(rectangle, offset, shapeRenderer);
        this.player = player;
        Rectangle rect = new Rectangle(x + width - squareHeight, offsetRectange.y, squareHeight - 3 * offset,
                offsetRectange.height);
        musicUi = new MusicUi(x,y,height*3,height);
        scoreTitleText = new DisplayText(rect, shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.LEFT);
        scoreText = new DisplayText(rect, shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.RIGHT);
    }

    @Override
    public void render() {
        super.render();
        scoreTitleText.render("Score");
        scoreText.render(player.getScore().toString());
    }

}
