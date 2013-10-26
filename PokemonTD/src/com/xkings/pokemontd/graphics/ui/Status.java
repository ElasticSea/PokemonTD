package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.manager.Interest;
import com.xkings.pokemontd.manager.WaveManager;


/**
 * User: Seda
 * Date: 20.10.13
 * Time: 10:47
 */

public class Status extends GuiBox {
    private final SpriteBatch spriteBatch;
    private final DisplayText interestText;
    private final DisplayText waveText;
    private final DisplayText livesText;
    private final DisplayText moneyText;
    private final DisplayText waveTimeText;
    private final DisplayText interestTimeText;
    private final DisplayPicture livesPicture;
    private final ShapeRenderer shapeRenderer;
    private final DisplayPicture moneyPicture;
    private final WaveManager waveManager;
    private final Interest interest;

    Status(Rectangle rectangle, int offset, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
           WaveManager waveManager, Interest interest) {
        super(rectangle, offset, shapeRenderer);

        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.waveManager = waveManager;
        this.interest = interest;

        Vector2 textFieldCount = new Vector2(2, 4);
        Vector2 textSize = new Vector2(offsetRectange.width / textFieldCount.x, offsetRectange.
                height / textFieldCount.y);

        interestText = createDisplayBlock(0, 0, textSize, BitmapFont.HAlignment.LEFT);
        waveText = createDisplayBlock(0, 1, textSize, BitmapFont.HAlignment.LEFT);
        livesText = createDisplayBlock(0, 2, textSize, BitmapFont.HAlignment.LEFT);
        moneyText = createDisplayBlock(0, 3, textSize, BitmapFont.HAlignment.LEFT);

        interestTimeText = createDisplayBlock(1, 0, textSize, BitmapFont.HAlignment.RIGHT);
        waveTimeText = createDisplayBlock(1, 1, textSize, BitmapFont.HAlignment.RIGHT);
        livesPicture = createDisplayPicture(1, 2, textSize, BitmapFont.HAlignment.RIGHT);
        moneyPicture = createDisplayPicture(1, 3, textSize, BitmapFont.HAlignment.RIGHT);
    }

    private DisplayText createDisplayBlock(float x, float y, Vector2 size, BitmapFont.HAlignment alignment) {
        return new DisplayText(
                new Rectangle(offsetRectange.x + size.x * x, offsetRectange.y + size.y * y, size.x, size.y),
                shapeRenderer, spriteBatch, alignment);
    }

    private DisplayPicture createDisplayPicture(float x, float y, Vector2 size, BitmapFont.HAlignment alignment) {
        float shorterSize = Math.min(size.x, size.y);
        float xPosition = alignment.equals(BitmapFont.HAlignment.LEFT) ? size.x * x : size.x * (x + 1) - shorterSize;
        return new DisplayPicture(offsetRectange.x + xPosition, offsetRectange.y + size.y * y, shorterSize, shorterSize,
                shapeRenderer, spriteBatch);
    }

    @Override
    public void render() {
        super.render();
        interestText.render("Interest");
        interestTimeText.render(String.valueOf(interest.getRemainingTime()));
        waveText.render("Wave");
        waveTimeText.render(String.valueOf(waveManager.getRemainingTime()));
        livesText.render("Lives");
        livesPicture.render(Assets.getTexture("hearth"));
        moneyText.render("Money");
        moneyPicture.render(Assets.getTexture("coin"));
    }
}
