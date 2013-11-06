package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.manager.CreepManager;
import com.xkings.pokemontd.manager.Interest;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;

import java.util.ArrayList;

/**
 * Created by Tomas on 11/5/13.
 */
public abstract class Gui extends GestureDetector.GestureAdapter implements Renderable {
    private final ArrayList<InteractiveElement> clickables = new ArrayList<InteractiveElement>();
    protected final BitmapFont font = Assets.createFont("pixelFont");
    protected final ShapeRenderer shapeRenderer;
    protected final SpriteBatch spriteBatch;
    protected final TowerManager towerManager;
    protected final WaveManager waveManager;
    private final CreepManager creepManager;
    protected final Interest interest;
    protected final App app;
    protected final int height;
    protected final int width;
    protected final Vector2 center;
    protected int squareSize;
    private int offset;
    protected Player player;

    public Gui(App app) {
        this.app = app;
        this.player = app.getPlayer();
        this.creepManager = app.getCreepManager();
        this.towerManager = app.getTowerManager();
        this.waveManager = app.getWaveManager();
        this.interest = app.getInterest();
        this.shapeRenderer = new ShapeRenderer();
        this.spriteBatch = new SpriteBatch();
        height = Gdx.graphics.getHeight();
        width = Gdx.graphics.getWidth();
        squareSize = (int) MathUtils.clamp(Gdx.graphics.getDensity() * 160 * 2, height / 4, height / 3.3f);
        offset = squareSize / 36;
        center = new Vector2(width/2f, height/2f);
        font.setScale(Math.round(squareSize / 90));
    }

    @Override
    public void render() {
        for (DisplayBlock displayBlock : clickables) {
            displayBlock.render();
        }
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return checkUiHit(x, y);
    }

    protected boolean checkUiHit(float x, float y) {
        boolean condition = false;
        for (Clickable clickable : clickables) {
            if (clickable.hit(x, y)) {
                condition = true;
            }
        }
        return condition;
    }

    public void register(InteractiveElement button) {
        clickables.add(button);
    }

    public void unregister(InteractiveElement button) {
        clickables.remove(button);
    }

    public CreepManager getCreepManager() {
        return creepManager;
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
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

    public App getApp() {
        return app;
    }
}
