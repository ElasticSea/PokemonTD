package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.entity.tower.TowerName;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class BuyShop extends NoticeTask {

    public BuyShop(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getBuyButton(), Notice.Orientation.BOTTOM_RIGHT, "Click to buy the shop",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        return entity.getTowerManager().getPlaceholderTower() != null && tutorial.getShop() == null && entity.getTowerManager().getSelectedTowerType().getName().equals(
                TowerName.Shop);
    }

}
