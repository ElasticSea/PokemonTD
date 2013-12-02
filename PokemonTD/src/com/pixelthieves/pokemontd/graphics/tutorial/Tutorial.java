package com.pixelthieves.pokemontd.graphics.tutorial;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.core.behavior.task.Selector;
import com.pixelthieves.core.component.PositionComponent;
import com.pixelthieves.core.component.SizeComponent;
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.core.graphics.camera.CameraHandler;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.task.*;
import com.pixelthieves.pokemontd.graphics.ui.Button;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;
import com.pixelthieves.pokemontd.graphics.ui.GuiBox;
import com.pixelthieves.pokemontd.graphics.ui.Ui;


/**
 * Created by Tomas on 11/7/13.
 */
public class Tutorial implements Renderable, Updateable {

    private final App app;
    private final Ui ui;
    private final Selector<App> beforeStart;
    private final Selector<App> afterStart;
    private final Button leaveTutorial;
    private final DisplayText additionalText;
    private final CameraHandler cameraHandler;
    private Notice oldNotice;
    private Notice currentNotice;
    private boolean active;

    public Tutorial(App app, Ui ui, CameraHandler cameraHandler) {
        this.app = app;
        this.ui = ui;
        this.cameraHandler = cameraHandler;

        int width = ui.getWidth() / 2;
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
        leaveTutorial.setScale(2);


        beforeStart = new Selector<App>();
        beforeStart.getControl().add(new FireFirstWave(this));
        beforeStart.getControl().add(new BuyShop(this));
        beforeStart.getControl().add(new PlaceShop(this));
        beforeStart.getControl().add(new PickShop(this));
        beforeStart.getControl().add(new ConfirmThatUpgrade(this));
        beforeStart.getControl().add(new UpgradeThatTower(this));
        beforeStart.getControl().add(new BuyThatTower(this));
        beforeStart.getControl().add(new PlaceThatTower(this));
        beforeStart.getControl().add(new PickYourGodDamnTowerNoob(this));
        beforeStart.getControl().add(new EmptyNotice(this));

        afterStart = new Selector<App>();
        afterStart.getControl().add(new PickElement(this));
        afterStart.getControl().add(new TriggerFreeElement(this));
        afterStart.getControl().add(new EmptyNotice(this));

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
    public void update(float delta) {
        if (!app.getPlayer().getTreasure().hasElements()) {
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

    public Rectangle getShop() {
        Entity shop = app.getTowerManager().getShop();
        if (shop != null) {
            PositionComponent position = shop.getComponent(PositionComponent.class);
            SizeComponent size = shop.getComponent(SizeComponent.class);
            if (position != null && size != null) {
                return new Rectangle(position.getPoint().x - size.getPoint().x / 2,
                        position.getPoint().y - size.getPoint().y / 2, size.getPoint().x, size.getPoint().y);
            }
        }
        return null;
    }

    public CameraHandler getCameraHandler() {
        return cameraHandler;
    }
}
