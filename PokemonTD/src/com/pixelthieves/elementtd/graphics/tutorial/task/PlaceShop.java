package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.entity.tower.TowerName;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;
import com.pixelthieves.elementtd.manager.TowerManager;


/**
 * Created by Tomas on 12/2/13.
 */
public class PlaceShop extends NoticeTask {

    public PlaceShop(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, App.getTowerRectangleByBlock(7, 18), Notice.Orientation.TOP_LEFT,
                "Place the shop somewhere on the map", Notice.Placement.RELATIVE);
    }

    @Override
    public boolean checkConditions(App entity) {
        TowerManager towerManager = entity.getTowerManager();
        return towerManager.getSelectedTowerType() != null &&
                towerManager.getSelectedTowerType().getName().equals(TowerName.Shop);
    }

}
