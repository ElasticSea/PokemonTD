package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
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
    private static final ArrayList<InteractiveBlock> clickables = new ArrayList<InteractiveBlock>();
    private final int width;
    private final TowerIcons towerIcons;
    private final ShopIcons shopIcons;
    private final BitmapFont font = Assets.createFont("pixelFont");
    private final WaveManager waveManager;
    private final Interest interest;
    private final App app;
    private int height = 0;
    private final EntityInfo entityInfo;
    private final GuiBox statusBar;
    private final GuiBox nextWaveInfo;
    private final GuiBox status;
    private final GuiBox abilityText;
    private final CreepManager creepManager;
    private int offset;
    private Player player;

    public Ui(App app) {
        this.app = app;
        this.player = app.getPlayer();
        this.creepManager = app.getCreepManager();
        this.towerManager = app.getTowerManager();
        this.waveManager = app.getWaveManager();
        this.interest = app.getInterest();
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        int squareHeight = (int) MathUtils.clamp(Gdx.graphics.getDensity() * 160 * 2, height / 4, height / 3.3f);
        int statusBarHeight = squareHeight / 5;
        int statusHeight = statusBarHeight * 4;

        width = Gdx.graphics.getWidth();

        int stripHeight = (int) (squareHeight / 3f * 2f);
        offset = squareHeight / 36;
        float statusHeightBlock = statusHeight / 5;
        float statusOffSet = statusHeightBlock / 2;
        statusHeight = (int) (statusHeightBlock * 4);

        squareHeight = (squareHeight / 3) * 3 + offset * 2;

        Rectangle pickTableRectangle =
                new Rectangle(Gdx.graphics.getWidth() - squareHeight, 0, squareHeight, squareHeight);
        towerIcons = new TowerIcons(this, pickTableRectangle, towerManager);
        shopIcons = new ShopIcons(this, pickTableRectangle);

        Vector2 statusBarDimensions = new Vector2(width, statusBarHeight);
        Vector2 statusDimensions = new Vector2(squareHeight, statusHeight);

        System.out.println(statusHeight);
        font.setScale(Math.round(statusHeight / 60));
        statusBar = new StatusBar(this,
                new Rectangle(0, this.height - statusBarDimensions.y, statusBarDimensions.x, statusBarDimensions.y),
                shopIcons.offsetRectange.width, font);
        status = new Status(this, new Rectangle(width - statusDimensions.x,
                this.height - statusBar.height - statusOffSet - statusDimensions.y, statusDimensions.x,
                statusDimensions.y), waveManager, interest, font);
        abilityText = new AbilityInfo(this,
                new Rectangle(0, this.height - statusBarDimensions.y, statusBarDimensions.x, statusBarDimensions.y),
                squareHeight);


        nextWaveInfo = new WaveInfo(this, new Rectangle(0, 0, squareHeight, squareHeight), waveManager, font);
        entityInfo = new EntityInfo(this,
                new Rectangle(squareHeight - offset, 0, width - (squareHeight - offset) * 2, stripHeight), offset,
                shapeRenderer, spriteBatch, font, player);

        clickables.add(entityInfo);
        clickables.add(statusBar);


        clickables.add(nextWaveInfo);
        clickables.add(status);
    }

    @Override
    public void render() {
        TowerName towerName = towerManager.getCurrentTowerName();
        clickables.remove(towerIcons);
        clickables.remove(shopIcons);
        if (towerName != null && towerName.equals(TowerName.Shop)) {
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

    private boolean checkUiHit(float x, float y) {
        boolean condition = false;
        for (InteractiveBlock interactiveBlock : clickables) {
            if (interactiveBlock.hit(x, Gdx.graphics.getHeight() - y)) {
                condition = true;
            }
        }
        return condition;
    }

    public static void register(InteractiveBlock button) {
        clickables.add(button);
    }

    public CreepManager getCreepManager() {
        return creepManager;
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public int getOffset() {
        return offset;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public Player getPlayer() {
        return player;
    }

    public BitmapFont getFont() {
        return font;
    }

    public GuiBox getRectangle() {
        return abilityText;
    }

    public App getApp() {
        return app;
    }
}
