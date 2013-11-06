package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.entity.tower.TowerName;

/**
 * Created by Tomas on 10/8/13.
 */
public class Ui extends Gui {

    private final int width;
    private final TowerIcons towerIcons;
    private final ShopIcons shopIcons;
    private final EntityInfo entityInfo;
    private final GuiBox statusBar;
    private final GuiBox nextWaveInfo;
    private final GuiBox status;
    private final GuiBox abilityText;
    private int offset;

    public Ui(App app) {
        super(app);
        int statusBarHeight = squareSize / 5;
        int statusHeight = statusBarHeight * 4;

        width = Gdx.graphics.getWidth();

        int stripHeight = (int) (squareSize / 3f * 2f);
        offset = squareSize / 36;
        float statusHeightBlock = statusHeight / 5;
        float statusOffSet = statusHeightBlock / 2;
        statusHeight = (int) (statusHeightBlock * 4);

        squareSize = (squareSize / 3) * 3 + offset * 2;

        Rectangle pickTableRectangle =
                new Rectangle(width - squareSize, 0, squareSize, squareSize);
        towerIcons = new TowerIcons(this, pickTableRectangle, towerManager);
        shopIcons = new ShopIcons(this, pickTableRectangle);

        Vector2 statusBarDimensions = new Vector2(width, statusBarHeight);
        Vector2 statusDimensions = new Vector2(squareSize, statusHeight);

        statusBar = new StatusBar(this,
                new Rectangle(0, height - statusBarDimensions.y, statusBarDimensions.x, statusBarDimensions.y),
                shopIcons.offsetRectange.width, font);
        status = new Status(this,
                new Rectangle(width - statusDimensions.x, height - statusBar.height - statusOffSet - statusDimensions.y,
                        statusDimensions.x, statusDimensions.y), waveManager, interest, font);

        abilityText = new AbilityInfo(this, pickTableRectangle);

        nextWaveInfo = new WaveInfo(this, new Rectangle(0, 0, squareSize, squareSize), waveManager, font);
        entityInfo = new EntityInfo(this,
                new Rectangle(squareSize - offset, 0, width - (squareSize - offset) * 2, stripHeight), offset,
                shapeRenderer, spriteBatch, font, player, null);

        register(entityInfo);
        register(nextWaveInfo);
        register(status);
    }

    @Override
    public void render() {
        TowerName towerName = towerManager.getCurrentTowerName();
        unregister(towerIcons);
        unregister(shopIcons);
        if (towerName != null && towerName.equals(TowerName.Shop)) {
            register(shopIcons);
        } else {
            towerIcons.update(towerName);
            register(towerIcons);
        }
        super.render();
    }
}
