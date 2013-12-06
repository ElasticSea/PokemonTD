package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class SelectInGameTower extends RebuildableNoticeTask {

    private Rectangle tower;

    public SelectInGameTower(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected boolean needRebuild() {
        return tower != tutorial.getTowerRectangle();
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        tower = tutorial.getTowerRectangle();
        return new Notice(ui, tower != null ? tower : new Rectangle(), Notice.Orientation.BOTTOM_RIGHT,
                "Click on the tower", Notice.Placement.RELATIVE);
    }

    @Override
    public boolean checkConditions(App entity) {
        return tutorial.getTower() != null;
    }

}
