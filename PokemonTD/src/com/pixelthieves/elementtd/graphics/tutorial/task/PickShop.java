package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class PickShop extends NoticeTask {

    public PickShop(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getTowerIcon(2), Notice.Orientation.BOTTOM_RIGHT, "Pick shop tower",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        return tutorial.getShop() == null &&  entity.getTowerManager().getSelectedTowerType() == null && entity.getTowerManager().getClicked() == null;
    }

}
