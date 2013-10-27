package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.Renderable;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.entity.tower.TowerName;
import com.xkings.pokemontd.manager.CreepManager;
import com.xkings.pokemontd.manager.Interest;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;

import java.util.ArrayList;

/**
 * Created by Tomas on 10/8/13.
 */
public class Ui extends GestureDetector.GestureAdapter implements Renderable {

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final TowerManager towerManager;
    private final ArrayList<InteractiveBlock> clickables;
    private final int width;
    private final TowerIcons towerIcons;
    private final ShopIcons shopIcons;
    private int height = 0;
    private final EntityInfo entityInfo;
    private final GuiBox statusBar;
    private final GuiBox nextWaveInfo;
    private final GuiBox status;
    private final CreepManager creepManager;

    public Ui(Player player, WaveManager waveManager, CreepManager creepManager, TowerManager towerManager,
              float guiScale, Interest interest) {
        this.creepManager = creepManager;
        this.towerManager = towerManager;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        clickables = new ArrayList<InteractiveBlock>();
        height = Gdx.graphics.getHeight();
        float heightInInch = height / Gdx.graphics.getDensity() * 160 * 2;
        float squareHeight = MathUtils.clamp(Gdx.graphics.getDensity() * 160 * 2, height / 4, height / 3);
        float statusBarHeight = squareHeight / 5;
        float statusHeight = statusBarHeight * 4;


        width = Gdx.graphics.getWidth();

        float Height = (int) guiScale / 3 * 2.8f;
        int stripHeight = (int) (squareHeight / 3f * 2f);
        int offset = (int) squareHeight / 36;
        float iconSize = (squareHeight - offset) / 3f;
        float statusHeightBlock = statusHeight / 5;
        float statusOffSet = statusHeightBlock / 2;
        statusHeight = statusHeightBlock * 4;

        Vector2 statusBarDimensions = new Vector2(width, statusBarHeight);
        statusBar = new StatusBar(player,
                new Rectangle(0, this.height - statusBarDimensions.y, statusBarDimensions.x, statusBarDimensions.y),
                offset, shapeRenderer, spriteBatch);
        Vector2 statusDimensions = new Vector2(squareHeight, statusHeight);
        status = new Status(player, new Rectangle(width - statusDimensions.x,
                this.height - statusBar.height - statusOffSet - statusDimensions.y, statusDimensions.x,
                statusDimensions.y), offset, shapeRenderer, spriteBatch, waveManager, interest);


        nextWaveInfo = new WaveInfo(new Rectangle(0, 0, squareHeight, squareHeight), offset, shapeRenderer, spriteBatch,
                waveManager);
        entityInfo = new EntityInfo(this,
                new Rectangle(squareHeight - offset, 0, width - (squareHeight - offset) * 2, stripHeight), offset,
                shapeRenderer, spriteBatch);

        clickables.add(entityInfo);
        clickables.add(statusBar);


        clickables.add(nextWaveInfo);
        clickables.add(status);

        Rectangle pickTableRectangle =
                new Rectangle(Gdx.graphics.getWidth() - squareHeight, 0, squareHeight, squareHeight);
        towerIcons = new TowerIcons(pickTableRectangle, offset, shapeRenderer, spriteBatch, towerManager);
        shopIcons = new ShopIcons(pickTableRectangle, offset, shapeRenderer, spriteBatch, player);
    }


    @Override
    public void render() {
        TowerName towerName = towerManager.getCurrentTowerName();
        clickables.remove(towerIcons);
        clickables.remove(shopIcons);
            if (towerName != null &&towerName.equals(TowerName.Shop)) {
                clickables.add(shopIcons);
            } else {
                towerIcons.update(towerName);
                clickables.add(towerIcons);
            }
        for (DisplayBlock displayBlock : clickables) {
            displayBlock.render();
        }
    }


    @Override
    public boolean tap(float x, float y, int count, int button) {
        return checkUiHit(x, y);
    }

   /* @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        // TODO: Event if its a hit, other listener remembers pressed finger/button and as soon as it leaves UI hit
        // TODO: area, the camera will jump the distance between first press and current release.
        return checkUiHit(x, y);
    }      */

    private boolean checkUiHit(float x, float y) {
        boolean condition = false;
        for (InteractiveBlock interactiveBlock : clickables) {
            if (interactiveBlock.hit(x, Gdx.graphics.getHeight() - y)) {
                condition = true;
            }
        }
        return condition;
    }

    public void register(InteractiveBlock button) {
        clickables.add(button);
    }

    public CreepManager getCreepManager() {
        return creepManager;
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }
}
