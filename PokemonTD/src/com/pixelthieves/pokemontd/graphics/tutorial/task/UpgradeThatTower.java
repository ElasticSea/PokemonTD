package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;
import com.pixelthieves.pokemontd.manager.TowerManager;


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
        return new Notice(ui, ui.getTowerIcon(), Notice.Orientation.BOTTOM_RIGHT, "Select a tower upgrade",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        TowerManager towerManager = entity.getTowerManager();
        return towerManager.getTowersCount() > 0 && towerManager.getClicked() != null;
    }

}
