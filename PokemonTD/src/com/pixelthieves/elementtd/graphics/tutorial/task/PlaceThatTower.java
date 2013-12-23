package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;
import com.pixelthieves.elementtd.manager.TowerManager;


/**
 * Created by Tomas on 12/2/13.
 */
public class PlaceThatTower extends NoticeTask {

    public PlaceThatTower(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, App.getTowerRectangleByBlock(4,15), Notice.Orientation.TOP_LEFT,
                "Place tower near the path", Notice.Placement.RELATIVE);
    }

    @Override
    public boolean checkConditions(App entity) {
        TowerManager towerManager = entity.getTowerManager();
        return towerManager.getSelectedTowerType() != null && tutorial.getTower() == null;
    }

}
