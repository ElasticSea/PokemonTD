package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class BuyThatTower extends NoticeTask {

    public BuyThatTower(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getBuyButton(), Notice.Orientation.BOTTOM_RIGHT, "Click to buy the tower",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        return entity.getTowerManager().getPlaceholderTower() != null;
    }

}
