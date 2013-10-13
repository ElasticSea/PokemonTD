package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.entity.TowerType;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;

import java.util.ArrayList;
import java.util.Iterator;
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
    private final GuiBox menuBar;
    private final List<TowerIcon> towerIcons;
    private final EntityInfo entityInfo;
    private final GuiBox statusBar;
    private final GuiBox nextWaveInfo;
    private DisplayBlock displayBar;
    private DisplayBlock towerTable;
    private Icon sellBlock;
    private List<TowerType> lastHierarchy;

    public Ui(Player player, WaveManager waveManager, TowerManager towerManager, Camera camera) {
        this.camera = camera;
        this.towerManager = towerManager;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        displayBlocks = new ArrayList<DisplayBlock>();


        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        int guiHeight = Gdx.graphics.getHeight() / 3;

        int stripHeight = guiHeight / 3 * 2;
        int squareHeight = guiHeight;
        int offset = guiHeight / 35;
        float iconSize = (squareHeight - offset) / 3f;

        int statusSize = 48;
        statusBar = new StatusBar(player, new Rectangle(0, height - statusSize, width, statusSize), statusSize / 8,
                shapeRenderer, spriteBatch);
        menuBar = new GuiBox(new Rectangle(width / 2f - iconSize * 1.5f, height - iconSize, iconSize * 3, iconSize), 0,
                shapeRenderer);
        nextWaveInfo = new WaveInfo(new Rectangle(0, 0, squareHeight, squareHeight), offset, shapeRenderer,
                spriteBatch,waveManager);
        displayBar = new GuiBox(new Rectangle(squareHeight-offset, 0, width-squareHeight+offset, stripHeight), offset,
                shapeRenderer);
        towerTable =
                new GuiBox(new Rectangle(width - squareHeight, 0, squareHeight, squareHeight), offset, shapeRenderer);
        entityInfo = new EntityInfo(displayBar.rectangle, spriteBatch, towerManager.getCurrentTowerInfo());
        displayBlocks.add(displayBar);
        displayBlocks.add(towerTable);
      //  displayBlocks.add(menuBar);
        displayBlocks.add(entityInfo);
        displayBlocks.add(statusBar);
        displayBlocks.add(nextWaveInfo);

        towerIcons = createTowerIcons(iconSize, towerTable.rectangle);

        for (TowerIcon towerIcon : towerIcons) {
            displayBlocks.add(towerIcon);
        }
       // placeMenuIcons(iconSize, menuBar.rectangle);
        // update(lastHierarchy);
    }

    private void placeMenuIcons(float size, Rectangle rect) {
        sellBlock = new Icon(new Rectangle(rect.x, rect.y, size, size), spriteBatch, Assets.getTexture("coin")) {
            @Override
            public void process(float x, float y) {
                towerManager.toggleSellingTowers();
            }
        };
        displayBlocks.add(sellBlock);

    }

    private List<TowerIcon> createTowerIcons(float size, Rectangle rect) {
        List<TowerIcon> towerIcons = new ArrayList<TowerIcon>();
        for (int i = 0; i < 9; i++) {
            towerIcons.add(createTowerIcon(rect, i, null, size));
        }
        return towerIcons;
    }

    private TowerIcon createTowerIcon(Rectangle rect, int position, TowerType tower, float size) {
        int x = (int) (rect.x + position % 3 * size);
        int y = (int) (rect.y + position / 3 * size);
        TowerIcon towerIcon =
                new TowerIcon(new Rectangle(x, rect.height - y - size, size, size), tower, spriteBatch, towerManager) {
                    @Override
                    public void process(float x, float y) {
                        towerManager.setPickedTower(towerType);
                    }
                };

        return towerIcon;
    }


    @Override
    public void render() {
        if (lastHierarchy != towerManager.getCurrentTree()) {
            lastHierarchy = towerManager.getCurrentTree();
            update(lastHierarchy);
        }
        for (DisplayBlock displayBlock : displayBlocks) {
            displayBlock.render();
        }
    }

    private void update(List<TowerType> hierarchy) {
        Iterator<TowerType> hierarchyIterator = hierarchy.iterator();
        for (TowerIcon towerIcon : towerIcons) {
            if (hierarchyIterator.hasNext()) {
                towerIcon.towerType = hierarchyIterator.next();
            } else {
                towerIcon.towerType = null;
            }
        }
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
