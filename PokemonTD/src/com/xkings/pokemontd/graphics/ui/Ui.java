package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.graphics.Renderable;
import com.xkings.pokemontd.entity.TowerType;
import com.xkings.pokemontd.manager.TowerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class Ui extends GestureDetector.GestureAdapter implements Renderable {

    private final ShapeRenderer shapeRenderer;
    private final SpriteBatch spriteBatch;
    private final TowerManager towerManager;
    private final Camera camera;
    private final ArrayList<DisplayBlock> displayBlocks;
    private final int width;
    private final int height;
    private DisplayBlock displayBar;
    private DisplayBlock towerTable;

    public Ui(TowerManager towerManager, Camera camera) {
        this.camera = camera;
        this.towerManager = towerManager;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        displayBlocks = new ArrayList<DisplayBlock>();


        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight() / 3;

        int stripHeight = height / 3 * 2;
        int squareHeight = height;
        int offset = height / 35;

        displayBar = new GuiBox(new Rectangle(0, 0, width, stripHeight), offset, shapeRenderer);
        towerTable =
                new GuiBox(new Rectangle(width - squareHeight, 0, squareHeight, squareHeight), offset, shapeRenderer);
        displayBlocks.add(displayBar);
        displayBlocks.add(towerTable);

        placeTowerIcons(towerManager.getCurrentTree(), (towerTable.rectangle.width - offset) / 3f,
                towerTable.rectangle);

    }

    private void placeTowerIcons(List<TowerType> hierarchy, float size, Rectangle rect) {
        for (int i = 0; i < hierarchy.size(); i++) {
            TowerType tower = hierarchy.get(i);
            if (towerManager.canAfford(tower)) {
                placeTowerIcon(rect, i, tower, size);
            }
        }
    }

    @Override
    public void render() {
        for (DisplayBlock displayBlock : displayBlocks) {
            displayBlock.render();
        }
    }

    private void placeTowerIcon(Rectangle rect, int position, TowerType tower, float size) {
        int x = (int) (rect.x + position % 3 * size);
        int y = (int) (rect.y + position / 3 * size);
        displayBlocks.add(new TowerIcon(new Rectangle(x, rect.height - y - size, size, size), tower, spriteBatch) {
            @Override
            public void process(float x, float y) {
                towerManager.setCurrentTower(towerType);
            }
        });
    }


    @Override
    public boolean tap(float x, float y, int count, int button) {
        return checkUiHit(x, y);
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        // TODO: Event if its a hit, other listener remembers pressed finger/button and as soon as it leaves UI hit
        // TODO: area, the camera will jump the distance between first press and current release.
        return checkUiHit(x, y);
    }

    private boolean checkUiHit(float x, float y) {
        boolean condition = false;
        for (DisplayBlock displayBlock : displayBlocks) {
            if (displayBlock.hit(x, Gdx.graphics.getHeight() - y)) {
                condition = true;
            }
        }
        return condition;
    }

}
