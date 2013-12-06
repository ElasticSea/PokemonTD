package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.pixelthieves.core.behavior.task.LeafTask;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;

/**
 * Created by Tomas on 12/2/13.
 */
public abstract class RebuildableNoticeTask extends NoticeTask {

    protected RebuildableNoticeTask(Tutorial tutorial) {
        super(tutorial);
    }

    @Override
    public boolean doAction(App entity) {
        if (needRebuild()) {
            notice = buildNotice();
        }
        return super.doAction(entity);
    }

    protected abstract boolean needRebuild();

}
