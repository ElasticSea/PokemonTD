package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.manager.Interest;
import com.xkings.pokemontd.manager.WaveManager;


/**
 * User: Seda
 * Date: 20.10.13
 * Time: 10:47
 */

public class Status extends GuiBox {
    private final DisplayText interestText;
    private final DisplayText waveText;
    private final DisplayText livesText;
    private final DisplayText moneyText;
    private final DisplayText waveTimeText;
    private final DisplayText interestTimeText;
    private final DisplayPicture livesPicture;
    private final DisplayPicture moneyPicture;
    private final WaveManager waveManager;
    private final Interest interest;

    private final Player player;
    private final BitmapFont font;
    private final Vector2 textFieldCount;
    private Vector2 textSize;

    Status(Ui ui, Rectangle rectangle, WaveManager waveManager, Interest interest, BitmapFont font) {
        super(ui, rectangle);

        offsetRectange.x += offset;
        offsetRectange.width -= offset * 2;

        this.player = ui.getPlayer();
        this.waveManager = waveManager;
        this.interest = interest;
        this.font = font;

        textFieldCount = new Vector2(2, 4);
        interestText = createDisplayBlock(BitmapFont.HAlignment.LEFT);
        waveText = createDisplayBlock(BitmapFont.HAlignment.LEFT);
        livesText = createDisplayBlock(BitmapFont.HAlignment.LEFT);
        moneyText = createDisplayBlock(BitmapFont.HAlignment.LEFT);


        interestTimeText = createDisplayBlock(BitmapFont.HAlignment.RIGHT);
        waveTimeText = createDisplayBlock(BitmapFont.HAlignment.RIGHT);
        livesPicture = createDisplayPicture();
        moneyPicture = createDisplayPicture();
        refresh();
    }

    private void refresh(DisplayText text, int x, int y, Vector2 size) {
        text.set(offsetRectange.x + size.x * x, offsetRectange.y + size.y * y, size.x, size.y);
        text.refresh();
    }

    private void refresh(DisplayPicture picture, int x, int y, Vector2 size, BitmapFont.HAlignment alignment) {
        float shorterSize = Math.min(size.x, size.y);
        float xPosition = alignment.equals(BitmapFont.HAlignment.LEFT) ? size.x * x : size.x * (x + 1) - shorterSize;
        picture.set(offsetRectange.x + xPosition, offsetRectange.y + size.y * y, shorterSize, shorterSize);
        picture.refresh();
    }

    private DisplayText createDisplayBlock(BitmapFont.HAlignment alignment) {
        return new DisplayText(ui, new Rectangle(), font, alignment);
    }

    private DisplayPicture createDisplayPicture() {
        return new DisplayPicture(ui, new Rectangle());
    }

    @Override
    public void render() {
        super.render();
        interestText.render("Interest");
        interestTimeText.render(String.valueOf(interest.getRemainingTime()));
        waveText.render("Wave in");
        waveTimeText.render(String.valueOf(waveManager.getRemainingTime()));
        livesText.render(String.valueOf(player.getHealth().getCurrentHealth()));
        livesPicture.render(Assets.getTexture("hearth"), "", textSize.y * 0.7f, true);
        moneyText.render(String.valueOf(player.getTreasure().getGold()));
        moneyPicture.render(Assets.getTexture("coin"), "", textSize.y * 0.7f, true);
    }

    @Override
    public void refresh() {
        super.refresh();
        textSize = new Vector2(offsetRectange.width / textFieldCount.x, offsetRectange.
                height / textFieldCount.y);
        refresh(interestText, 0, 0, textSize);
        refresh(waveText, 0, 1, textSize);
        refresh(livesText, 0, 2, textSize);
        refresh(moneyText, 0, 3, textSize);
        refresh(interestTimeText, 1, 0, textSize);
        refresh(waveTimeText, 1, 1, textSize);
        refresh(livesPicture, 1, 2, textSize, BitmapFont.HAlignment.RIGHT);
        refresh(moneyPicture, 1, 3, textSize, BitmapFont.HAlignment.RIGHT);
    }
}
