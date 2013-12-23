package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;
import com.pixelthieves.elementtd.manager.TowerManager;


/**
 * Created by Tomas on 12/2/13.
 */
public class UpgradeThatTower extends NoticeTask {

    public UpgradeThatTower(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getTowerIcon(0), Notice.Orientation.BOTTOM_RIGHT, "Select a tower upgrade",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        TowerManager towerManager = entity.getTowerManager();
        return tutorial.getShop() != null && tutorial.getTower() != null && towerManager.getClicked() != null;
    }

}
