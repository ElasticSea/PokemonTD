package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class PickYourGodDamnTowerNoob extends NoticeTask {

    public PickYourGodDamnTowerNoob(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, ui.getTowerIcon(0), Notice.Orientation.BOTTOM_RIGHT, "Pick one of the towers",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        return entity.getTowerManager().getClicked() == null;
    }

}
