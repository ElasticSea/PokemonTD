package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.graphics.Renderable;
import com.xkings.pokemontd.Player;
import com.xkings.pokemontd.entity.tower.TowerType;
import com.xkings.pokemontd.manager.CreepManager;
import com.xkings.pokemontd.manager.Interest;
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
    private final ArrayList<InteractiveBlock> clickables;
    private final int width;
    private final int height;
    private final List<TowerIcon> towerIcons;
    private final EntityInfo entityInfo;
    private final GuiBox statusBar;
    private final GuiBox nextWaveInfo;
    private final GuiBox status;
    private final CreepManager creepManager;
    private InteractiveBlock displayBar;
    private InteractiveBlock towerTable;
    private List<TowerType> lastHierarchy;

    public Ui(Player player, WaveManager waveManager, CreepManager creepManager, TowerManager towerManager, float guiScale,Interest interest) {
        this.creepManager = creepManager;
        this.towerManager = towerManager;
        shapeRenderer = new ShapeRenderer();
        spriteBatch = new SpriteBatch();
        clickables = new ArrayList<InteractiveBlock>();


        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        float Height = (int) guiScale / 3*2.8f;
        int squareHeight = Gdx.graphics.getHeight()/4* (int) guiScale;
        int stripHeight = (int) (squareHeight/3f* 2f);
        int offset = squareHeight/36;
        float iconSize = (squareHeight - offset) / 3f;

        int statusSize = 48;
        statusBar = new StatusBar(player, new Rectangle(0, height - statusSize, width, statusSize), offset,
                shapeRenderer, spriteBatch);
        status = new Status(player, new Rectangle(width-squareHeight, height-statusBar.height*1.5f-squareHeight, width, squareHeight), offset, shapeRenderer, spriteBatch, waveManager, interest);


        nextWaveInfo = new WaveInfo(new Rectangle(0, 0, squareHeight, squareHeight), offset, shapeRenderer, spriteBatch,
                waveManager);
        displayBar =
                new GuiBox(new Rectangle(squareHeight - offset, 0, width - (squareHeight - offset)*2, stripHeight), offset,
                        shapeRenderer);
        //width-Gdx.graphics.getWidth()/5
        towerTable =
                new GuiBox(new Rectangle(Gdx.graphics.getWidth() - squareHeight , 0, squareHeight, squareHeight), offset, shapeRenderer);
        entityInfo = new EntityInfo(this, displayBar, shapeRenderer, spriteBatch);

        clickables.add(displayBar);
        clickables.add(towerTable);
        clickables.add(entityInfo);
        clickables.add(statusBar);
        clickables.add(nextWaveInfo);
        clickables.add(status);

        towerIcons = createTowerIcons(iconSize, towerTable);

        for (TowerIcon towerIcon : towerIcons) {
            clickables.add(towerIcon);
        }
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
        for (DisplayBlock displayBlock : clickables) {
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
        System.out.println("HI");
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
        for (InteractiveBlock interactiveBlock : clickables) {
            System.out.println(interactiveBlock);
            if (interactiveBlock.hit(x, Gdx.graphics.getHeight() - y)) {
                System.out.println("hit");
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
