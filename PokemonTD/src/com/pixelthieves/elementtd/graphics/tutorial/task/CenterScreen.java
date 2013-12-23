package com.pixelthieves.elementtd.graphics.tutorial.task;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.graphics.tutorial.Notice;
import com.pixelthieves.elementtd.graphics.tutorial.Tutorial;
import com.pixelthieves.elementtd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class CenterScreen extends NoticeTask {

    public CenterScreen(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        return new Notice(ui, new Rectangle(ui.getCenter().x,ui.getCenter().y,0,0), Notice.Orientation.TOP_LEFT, "Click here to deselect",
                Notice.Placement.STATIC);
    }

    @Override
    public boolean checkConditions(App entity) {
        return true;
    }


}
