package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;
import com.pixelthieves.pokemontd.manager.TowerManager;


/**
 * Created by Tomas on 12/2/13.
 */
public class PlaceThatTower extends NoticeTask {

    public PlaceThatTower(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Vector2 spot = tutorial.getSweetTowerSpot();

        Ui ui = tutorial.getUi();
        return new Notice(ui, App.getTowerRectangleByBlock(spot.x, spot.y), Notice.Orientation.TOP_LEFT,
                "Place tower near the path", Notice.Placement.RELATIVE);
    }

    @Override
    public boolean checkConditions(App entity) {
        TowerManager towerManager = entity.getTowerManager();
        return towerManager.getSelectedTowerType() != null && towerManager.getTowersCount() == 0;
    }

}
