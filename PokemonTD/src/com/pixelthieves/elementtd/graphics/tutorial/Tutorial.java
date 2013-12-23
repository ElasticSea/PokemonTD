package com.pixelthieves.elementtd.graphics.tutorial;

import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.behavior.task.Selector;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.core.graphics.camera.CameraHandler;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.task.*;
import com.pixelthieves.elementtd.graphics.ui.*;
import com.pixelthieves.elementtd.system.resolve.ui.FindAttackTower;
import com.pixelthieves.elementtd.system.resolve.ui.FindShop;
import com.pixelthieves.elementtd.system.resolve.ui.FindUpgradeTower;


/**
 * Created by Tomas on 11/7/13.
 */
public class Tutorial extends DisplayBlock implements Updateable {

    private final App app;
    private final Ui ui;
    private Selector<App> beforeStart;
    private Selector<App> afterStart;
    private final Button leaveTutorial;
    private final DisplayText additionalText;
    private final CameraHandler cameraHandler;
    private Notice oldNotice;
    private Notice currentNotice;
    private boolean active;
    private Entity shop;
    private Entity tower;
    private Entity upgrade;
    private Rectangle shopRectangle;
    private Rectangle towerRectangle;

    public Tutorial(App app, Ui ui, CameraHandler cameraHandler) {
        super(ui);
        this.app = app;
        this.ui = ui;
        this.cameraHandler = cameraHandler;

        int width = ui.getSquareSize() * 2;
        int height = ui.getStatusBarHeight() * 2;
        Rectangle buttonRectangle = new Rectangle(ui.getCenter().x - width / 2, ui.getHeight() - height, width, height);
        Rectangle additionalRectangle =
                new Rectangle(buttonRectangle.x, buttonRectangle.y - height / 2, width, height / 2);
        leaveTutorial = new Button(ui, buttonRectangle, ui.getFont(), BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                setActive(false);
            }
        };
        additionalText = new DisplayText(ui, additionalRectangle, ui.getFont(), BitmapFont.HAlignment.CENTER);
        additionalText.setScale(0.6f);


        beforeStart = new Selector<App>();
        beforeStart.getControl().add(new FireFirstWave(this));
        beforeStart.getControl().add(new BuyShop(this));
        beforeStart.getControl().add(new PlaceShop(this));
        beforeStart.getControl().add(new PickShop(this));
        beforeStart.getControl().add(new CancelThat(this));
        beforeStart.getControl().add(new ConfirmThatUpgrade(this));
        beforeStart.getControl().add(new UpgradeThatTower(this));
        beforeStart.getControl().add(new BuyThatTower(this));
        beforeStart.getControl().add(new PlaceThatTower(this));
        beforeStart.getControl().add(new SelectInGameTower(this));
        beforeStart.getControl().add(new PickYourGodDamnTowerNoob(this));
        beforeStart.getControl().add(new EmptyNotice(this));

        afterStart = new Selector<App>();
        afterStart.getControl().add(new PickElement(this));
        afterStart.getControl().add(new TriggerFreeElement(this));
        afterStart.getControl().add(new EmptyNotice(this));

        ui.addRefreshable(this);
    }

    @Override
    public void render() {
        if (isActive()) {
            leaveTutorial.render("LEAVE INTERACTIVE TUTORIAL", Color.WHITE, GuiBox.lighterColor);
            additionalText.render("You can access tutorial any time in help section in menu.", Color.WHITE,
                    GuiBox.darkerColor);
            if (currentNotice != null) {
                currentNotice.render();
            }
        }
    }

    @Override
    public void refresh() {
        int width = ui.getSquareSize() * 2;
        int height = ui.getStatusBarHeight() * 2;
        Rectangle buttonRectangle = new Rectangle(ui.getCenter().x - width / 2, ui.getHeight() - height, width, height);
        Rectangle additionalRectangle =
                new Rectangle(buttonRectangle.x, buttonRectangle.y - height / 2, width, height / 2);
        leaveTutorial.set(buttonRectangle);
        additionalText.set(additionalRectangle);


        beforeStart = new Selector<App>();
        beforeStart.getControl().add(new FireFirstWave(this));
        beforeStart.getControl().add(new BuyShop(this));
        beforeStart.getControl().add(new PlaceShop(this));
        beforeStart.getControl().add(new PickShop(this));
        beforeStart.getControl().add(new CancelThat(this));
        beforeStart.getControl().add(new ConfirmThatUpgrade(this));
        beforeStart.getControl().add(new UpgradeThatTower(this));
        beforeStart.getControl().add(new BuyThatTower(this));
        beforeStart.getControl().add(new PlaceThatTower(this));
        beforeStart.getControl().add(new SelectInGameTower(this));
        beforeStart.getControl().add(new PickYourGodDamnTowerNoob(this));
        beforeStart.getControl().add(new EmptyNotice(this));

        afterStart = new Selector<App>();
        afterStart.getControl().add(new PickElement(this));
        afterStart.getControl().add(new TriggerFreeElement(this));
        afterStart.getControl().add(new EmptyNotice(this));
    }



    @Override
    public void update(float delta) {
        if (!app.getPlayer().getTreasure().hasElements()) {
            update();
            if (app.getWaveManager().getNextWave().getId() == 0) {
                beforeStart.doAction(app);
            } else {
                afterStart.doAction(app);
            }
        } else {
            setActive(false);
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            ui.register(leaveTutorial);
        } else {
            ui.unregister(leaveTutorial);
        }
    }

    public void setCurrentNotice(Notice currentNotice) {
        if (oldNotice != currentNotice) {
            oldNotice = currentNotice;
            if (currentNotice != null) {
                currentNotice.animate();
            }
            this.currentNotice = currentNotice;
        }
    }

    public Ui getUi() {
        return ui;
    }

    public Rectangle getShopRectangle() {
        return shopRectangle;
    }

    public Rectangle getTowerRectangle() {
        return towerRectangle;
    }

    public Rectangle getRectangle(Entity entity) {
        if (entity != null) {
            PositionComponent position = entity.getComponent(PositionComponent.class);
            SizeComponent size = entity.getComponent(SizeComponent.class);
            if (position != null && size != null) {
                return new Rectangle(position.getPoint().x - size.getPoint().x / 2,
                        position.getPoint().y - size.getPoint().y / 2, size.getPoint().x, size.getPoint().y);
            }
        }
        return null;
    }

    public void update(){
        FindShop findShop = app.getWorld().getSystem(FindShop.class);
        findShop.process();
        ImmutableBag<Entity> shops = findShop.getEntities();
        shop =  shops.size() == 0 ? null : shops.get(0);
        shopRectangle = getRectangle(shop);

        FindAttackTower findAttackTower = app.getWorld().getSystem(FindAttackTower.class);
        findAttackTower.process();
        ImmutableBag<Entity> towers = findAttackTower.getEntities();
        tower =  towers.size() == 0 ? null : towers.get(0);
        towerRectangle = getRectangle(tower);

        FindUpgradeTower findUpgradeTower = app.getWorld().getSystem(FindUpgradeTower.class);
        findUpgradeTower.process();
        ImmutableBag<Entity> upgrades = findUpgradeTower.getEntities();
        upgrade =  upgrades.size() == 0 ? null : upgrades.get(0);
    }
    public Entity getShop(){
        return shop;
    }

    public Entity getTower(){
        return tower;
    }

    public Entity getUpgrade() {
        return upgrade;
    }
}
