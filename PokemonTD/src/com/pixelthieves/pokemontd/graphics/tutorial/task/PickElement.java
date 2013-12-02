package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.artemis.Entity;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.component.TowerTypeComponent;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class PickElement extends NoticeTask {

    private boolean rebuild = true;

    public PickElement(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getTowerIcon(), Notice.Orientation.BOTTOM_RIGHT, "Click on the element",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean doAction(App entity) {
        if (rebuild) {
            notice = buildNotice();
            rebuild = false;
        }
        return super.doAction(entity);
    }

    @Override
    public boolean checkConditions(App entity) {
        if (entity.getPlayer().getFreeElements() > 0) {
            Entity clicked = entity.getTowerManager().getClicked();
            if (clicked != null) {
                TowerTypeComponent component = clicked.getComponent(TowerTypeComponent.class);
                if (component != null) {
                    return component.getTowerType().getName().equals(TowerName.Shop);
                }
            }
        }
        return false;
    }

}
