package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;
import com.pixelthieves.pokemontd.system.resolve.ui.FindShop;


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
