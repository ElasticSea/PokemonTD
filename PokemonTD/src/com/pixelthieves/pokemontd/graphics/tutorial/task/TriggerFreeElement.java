package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;


/**
 * Created by Tomas on 12/2/13.
 */
public class TriggerFreeElement extends NoticeTask {

    private boolean rebuild = true;

    public TriggerFreeElement(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    protected Notice buildNotice() {
        Ui ui = tutorial.getUi();
        Rectangle shop = tutorial.getShop();
        return new Notice(ui, shop != null ? shop : new Rectangle(), Notice.Orientation.BOTTOM_RIGHT,
                "Click on the shop", Notice.Placement.RELATIVE);
    }

    @Override
    public boolean doAction(App entity) {
        if (rebuild) {
            notice = buildNotice();
            rebuild = false;
        }
        return super.doAction(entity);
    }

    @Override
    public boolean checkConditions(App entity) {
        return entity.getPlayer().getFreeElements() > 0 && tutorial.getShop() != null;
    }

}
