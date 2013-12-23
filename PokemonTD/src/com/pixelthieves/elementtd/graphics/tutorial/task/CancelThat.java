package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.artemis.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.component.TowerTypeComponent;
import com.pixelthieves.elementtd.entity.tower.TowerName;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class CancelThat extends RebuildableNoticeTask {

    private Rectangle cancel;

    public CancelThat(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected boolean needRebuild() {
        return cancel != tutorial.getUi().getCancleButton();
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        cancel = ui.getCancleButton();
        return new Notice(ui, cancel, Notice.Orientation.BOTTOM_RIGHT, "Click here to cancel", Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        if (tutorial.getShop() != null) {
            Entity clicked = entity.getTowerManager().getClicked();
            if (clicked != null) {
                TowerTypeComponent component = clicked.getComponent(TowerTypeComponent.class);
                if (component != null) {
                    return component.getTowerType().getName().equals(TowerName.Shop);
                }
            }
            return false;
        }
        return true;
    }

}
