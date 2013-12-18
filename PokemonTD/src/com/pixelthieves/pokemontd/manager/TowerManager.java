package com.pixelthieves.pokemontd.manager;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.utils.Bag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.Player;
import com.pixelthieves.pokemontd.component.TowerTypeComponent;
import com.pixelthieves.pokemontd.component.TreasureComponent;
import com.pixelthieves.pokemontd.component.UpgradeComponent;
import com.pixelthieves.pokemontd.component.attack.effects.AbstractEffect;
import com.pixelthieves.pokemontd.entity.StaticObject;
import com.pixelthieves.pokemontd.entity.tower.Tower;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.entity.tower.TowerType;
import com.pixelthieves.pokemontd.entity.tower.TowerTypeResolver;
import com.pixelthieves.pokemontd.graphics.ui.Clickable;
import com.pixelthieves.pokemontd.system.resolve.ui.GetTower;
import com.pixelthieves.pokemontd.system.resolve.ui.LightUpShops;

import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TowerManager implements Clickable {

    public static final Color TINT = new Color(1, 1, 1, 0.5f);
    private final App app;
    private final TowerTypeResolver resolver;
    private Entity placeholderTower;
    private Entity clickedTower;
    private int soldTowers;

    public void setPickedTower(TowerType towerType) {
        removePlaceholderTower();
        if (towerType != null) {
            if (clickedTower == null) {
                status = Status.PLACING_TOWER;
            }
            selectedTower = towerType;
        }
    }

    @Override
    public boolean hit(float x, float y) {
        clickedTower = app.getWorld().getSystem(GetTower.class).getEntity(x, y);
        switch (status) {
            case NONE:
                reset();
                break;
            case PLACING_TOWER:
                if (clickedTower == null) {
                    placeTower((int) x, (int) y);
                } else {
                    clickedTower = null;
                    //reset();
                }
                break;
            case MOVE_PLACEHOLDER:
                if (clickedTower == null) {
                    movePlaceholder((int) x, (int) y);
                } else {
                    clickedTower = null;
                }
                break;
        }
        return clickedTower != null;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean enabled) {

    }

    public void reset() {
        removePlaceholderTower();
        selectedTower = null;
        status = Status.NONE;
    }

    public void cancel() {
        reset();
        clickedTower = null;
    }

    public Entity getClicked() {
        return clickedTower;
    }

    public TowerType getSelectedTowerType() {
        return selectedTower;
    }

    public void buyNewOrUpgrade() {
        if (canAfford(selectedTower)) {
            if (clickedTower == null && placeholderTower != null) {
                buyNewTower();
            } else if (clickedTower != null) {
                upgradeTower();
            }
        }
        this.setStatus(Status.NONE);
        selectedTower = null;
    }

    private void upgradeTower() {
        purchaseTower(selectedTower);
        Vector3 towerPosition = clickedTower.getComponent(PositionComponent.class).getPoint();
        Entity entity = Tower.registerTower(app.getWorld(), selectedTower, towerPosition.x, towerPosition.y);
        entity.getComponent(UpgradeComponent.class).add(clickedTower.getComponent(UpgradeComponent.class));
        entity.getComponent(UpgradeComponent.class).add(
                clickedTower.getComponent(TowerTypeComponent.class).getTowerType());
        transferEffects(clickedTower, entity);
        clickedTower.deleteFromWorld();
        clickedTower = entity;
    }

    private void transferEffects(Entity from, Entity to) {
        Bag<Component> fillBag = new Bag<Component>();
        from.getComponents(fillBag);
        for (int i = 0; i < fillBag.size(); i++) {
            Component component = fillBag.get(i);
            if (component instanceof AbstractEffect) {
                to.addComponent(component);
                ((AbstractEffect) component).reattach();
            }
        }
        to.changedInWorld();
    }

    private void buyNewTower() {
        Vector3 placeholderPosition = placeholderTower.getComponent(PositionComponent.class).getPoint();
        purchaseTower(selectedTower);
        clickedTower = Tower.registerTower(app.getWorld(), selectedTower, placeholderPosition.x, placeholderPosition.y);
        removePlaceholderTower();
        notifyShop();
    }

    private void notifyShop() {
        if (selectedTower.getName().equals(TowerName.Shop)) {
            World world = app.getWorld();
            float oldDelta = world.getDelta();
            world.setDelta(0);
            world.process();
            world.setDelta(oldDelta);
            world.getSystem(LightUpShops.class).start();
        }
    }

    public Entity getPlaceholderTower() {
        return placeholderTower;
    }

    public void render(ShapeRenderer shapeRenderer) {
        if (placeholderTower != null) {
            /*   Gdx.gl.glEnable(GL20.GL_BLEND);
               int size = App.WORLD_SCALE;
               int offset = size / 20;
               shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
               shapeRenderer.setColor(new Color(Color.BLACK).sub(0, 0, 0, 0.8f));
               drawFreeSlots(shapeRenderer, size, offset);
               shapeRenderer.end();

               /*shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
               shapeRenderer.setColor(new Color(Color.BLACK).sub(0, 0, 0, 0.8f));
               drawFreeSlots(shapeRenderer, size, offset);
               shapeRenderer.end();               */
            /*  Gdx.gl.glDisable(GL20.GL_BLEND);    */
        }
    }

    private void drawFreeSlots(ShapeRenderer shapeRenderer, int size, int offset) {
        for (int i = 0; i < app.getMap().getBlueprint().getWidth(); i++) {
            for (int j = 0; j < app.getMap().getBlueprint().getHeight(); j++) {
                if (app.getMap().getBlueprint().isWalkable(i, j)) {
                    shapeRenderer.rect(i * size + offset, j * size + offset, size - offset * 2, size - offset * 2);
                }
            }
        }
    }

    public List<TowerType> getHierarchy(TowerName towerName) {
        return resolver.getHierarchy(towerName);
    }

    public TowerType getType(TowerName name) {
        return resolver.getType(name);
    }

    public enum Status {
        NONE, PLACING_TOWER, MOVE_PLACEHOLDER;
    }

    private Status status = Status.NONE;

    private final Player player;
    private TowerType selectedTower = null;

    public TowerManager(App app, Player player) {
        this.app = app;
        this.player = player;
        this.resolver = new TowerTypeResolver(app.getAssets());
    }

    public void init() {
        placeholderTower = null;
        clickedTower = null;
        soldTowers = 0;
        if (App.STRESS_TEST != null) {
            for (int i = 0; i < app.getMap().getBlueprint().getWidth(); i++) {
                for (int j = 0; j < app.getMap().getBlueprint().getHeight(); j++) {
                    if (app.getMap().getBlueprint().isWalkable(i, j)) {
                        TowerType tower;
                        do {
                            TowerName name = TowerName.values()[App.RANDOM.nextInt(TowerName.values().length)];
                            tower = resolver.getType(name);
                        } while (tower == null);
                        Vector3 towerPosition = App.getTowerPositionByBlock(i, j);
                        Tower.registerTower(app.getWorld(), tower, towerPosition.x, towerPosition.y);
                    }
                }
            }
        }
    }

    private boolean placeTower(int x, int y) {
        if (selectedTower != null && status != Status.MOVE_PLACEHOLDER) {
            Vector3 block = App.getBlockPosition(x, y);
            if (canAfford(selectedTower) && app.getMap().getBlueprint().isWalkable((int) block.x, (int) block.y)) {
                status = Status.MOVE_PLACEHOLDER;
                Vector3 towerPosition = App.getTowerPosition(x, y);
                createPlaceholder(towerPosition);
                return true;
            }
        }
        return false;
    }

    private void createPlaceholder(Vector3 towerPosition) {
        this.placeholderTower =
                StaticObject.registerFakeTower(app.getWorld(), selectedTower, towerPosition.x, towerPosition.y, TINT);
    }

    private void movePlaceholder(int x, int y) {
        Vector3 block = App.getBlockPosition(x, y);
        if (app.getMap().getBlueprint().isWalkable((int) block.x, (int) block.y)) {
            Vector3 desiredPosition = App.getTowerPosition(x, y);
            placeholderTower.getComponent(PositionComponent.class).getPoint().set(desiredPosition);
        }
    }

    private void removePlaceholderTower() {
        if (placeholderTower != null) {
            this.placeholderTower.deleteFromWorld();
            placeholderTower = null;
        }
    }

    public boolean sellTower() {
        // DISCUS If I cast entity from blueprint into Entity Entity clickedTower = blueprint.getWalkable(x,
        // y); It won't find a appropriate component.
        if (clickedTower != null) {
            TreasureComponent towerTreasure = clickedTower.getComponent(TreasureComponent.class);
            if (towerTreasure != null) {
                int goldWorthThoseUpgrades =
                        clickedTower.getComponent(UpgradeComponent.class).getGoldWorthThoseUpgrades();
                player.getTreasure().addGold(towerTreasure.getTreasure().getGold() + goldWorthThoseUpgrades);
                player.getTreasure().addElement(Element.SOUL, towerTreasure.getTreasure().getElement(Element.SOUL));
                if (!clickedTower.getComponent(TowerTypeComponent.class).getTowerType().getName().equals(
                        TowerName.Shop)) {
                    soldTowers++;
                }
                clickedTower.deleteFromWorld();
                clickedTower = null;

            }
            return true;
        }
        return false;
    }

    private void purchaseTower(TowerType towerType) {
        player.getTreasure().purchase(towerType.getCost());
    }

    public TowerName getCurrentTowerName() {
        // FIXME get hierarchy working
        TowerName towerName =
                clickedTower != null ? clickedTower.getComponent(TowerTypeComponent.class).getTowerType().getName() :
                        null;
        return towerName;
    }

    public boolean canAfford(TowerType towerType) {
        return towerType != null ? player.getTreasure().includes(towerType.getCost()) : false;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public int getSoldTowers() {
        return soldTowers;
    }
}
