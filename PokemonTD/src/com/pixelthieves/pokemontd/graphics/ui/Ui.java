package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Configuration;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.graphics.ui.menu.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class Ui extends Gui {

    private final int width;
    private final TowerIcons towerIcons;
    private final ShopIcons shopIcons;
    private final EntityInfo entityInfo;
    private final StatusBar statusBar;
    private final GuiBox nextWaveInfo;
    private final GuiBox status;
    private final AbilityInfo abilityInfo;
    private final List<GuiBox> boxes = new ArrayList<GuiBox>();
    private final SpeedControlsBar speedControlsBar;
    private float defaultSize;
    private int offset;

    public Ui(App app, Menu menu) {
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

        Rectangle pickTableRectangle = new Rectangle(width - squareSize, 0, squareSize, squareSize);
        towerIcons = new TowerIcons(this, pickTableRectangle, towerManager);
        shopIcons = new ShopIcons(this, pickTableRectangle);

        Vector2 statusBarDimensions = new Vector2(width, statusBarHeight);
        Vector2 statusDimensions = new Vector2(squareSize, statusHeight);

        statusBar = new StatusBar(this, menu,
                new Rectangle(0, height - statusBarDimensions.y, statusBarDimensions.x, statusBarDimensions.y),
                shopIcons, font);
        status = new Status(this,
                new Rectangle(width - statusDimensions.x, height - statusBar.height - statusOffSet - statusDimensions.y,
                        statusDimensions.x, statusDimensions.y), waveManager, interestManager, font);

       speedControlsBar = new SpeedControlsBar(this, status.x, status.y - status.height/4, status.width, status.height);

        abilityInfo = new AbilityInfo(this, pickTableRectangle);

        nextWaveInfo = new WaveInfo(this, new Rectangle(0, 0, squareSize, squareSize), waveManager, font);
        entityInfo = new EntityInfo(this,
                new Rectangle(squareSize - offset, 0, width - (squareSize - offset) * 2, stripHeight), shapeRenderer,
                spriteBatch, font, player);

        register(entityInfo);
        register(nextWaveInfo);
        register(status);
        register(speedControlsBar);

        boxes.add(towerIcons);
        boxes.add(shopIcons);
        boxes.add(statusBar);
        boxes.add(status);
        boxes.add(speedControlsBar);
        boxes.add(abilityInfo);
        boxes.add(nextWaveInfo);
        boxes.add(entityInfo);

        defaultSize = Configuration.PREFERENCES.getFloat("gui_scale");
        resetSize();
    }

    @Override
    public void render() {
        TowerName towerName = towerManager.getCurrentTowerName();
        unregister(towerIcons);
        unregister(shopIcons);
        if (towerName != null && towerName.equals(TowerName.Shop)) {
            register(shopIcons);
            shopIcons.setLeftHeaderText("Elements");
        } else {
            towerIcons.update(towerName);
            towerIcons.setLeftHeaderText(towerName == null ? "Towers" : "Upgrades");
            register(towerIcons);
        }
        unregister(abilityInfo);
        register(abilityInfo);
        super.render();
    }

    public void makeLarger() {
        scale(squareSize + offset);
    }

    public void makeSmaller() {
        scale(squareSize - offset);
    }

    public void resetSize() {
        scale(defaultSize != 0 ? defaultSize : recommendedSize);
    }

    private void saveScale(float scale) {
        Configuration.PREFERENCES.putFloat("gui_scale", scale);
        Configuration.PREFERENCES.flush();
    }

    public void scale(float scale) {
        super.scale(scale);
        saveScale(scale);

        int statusBarHeight = squareSize / 5;
        int statusHeight = statusBarHeight * 4;
        int stripHeight = (int) (squareSize / 3f * 2f);
        offset = squareSize / 36;
        float statusHeightBlock = statusHeight / 5;
        float statusOffSet = statusHeightBlock / 2;
        statusHeight = (int) (statusHeightBlock * 4);
        Vector2 statusBarDimensions = new Vector2(width, statusBarHeight);
        Vector2 statusDimensions = new Vector2(squareSize, statusHeight);

        towerIcons.set(width - squareSize, 0, squareSize, squareSize);
        shopIcons.set(width - squareSize, 0, squareSize, squareSize);
        abilityInfo.set(width - squareSize, 0, squareSize, squareSize);
        statusBar.set(0, height - statusBarDimensions.y, statusBarDimensions.x, statusBarDimensions.y);
        statusBar.setSquare(towerIcons);
        status.set(width - statusDimensions.x, height - statusBar.height - statusOffSet - statusDimensions.y,
                statusDimensions.x, statusDimensions.y);
        float size = status.height / 3;
        speedControlsBar.set(status.x, status.y - size - statusOffSet, status.width, size);
        nextWaveInfo.set(0, 0, squareSize, squareSize);
        entityInfo.set(squareSize - offset, 0, width - (squareSize - offset) * 2, stripHeight);
        for (GuiBox guiBox : boxes) {
            guiBox.setOffset(offset);
            guiBox.refresh();
        }
    }

    public AbilityInfo getAbilityInfo() {
        return abilityInfo;
    }
}
