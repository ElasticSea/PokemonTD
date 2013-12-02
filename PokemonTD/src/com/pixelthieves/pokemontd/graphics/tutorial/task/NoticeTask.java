package com.pixelthieves.pokemontd.graphics.tutorial.task;

import com.pixelthieves.core.behavior.task.LeafTask;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.tutorial.Notice;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;

/**
 * Created by Tomas on 12/2/13.
 */
public abstract class NoticeTask extends LeafTask<App> {

    protected final Tutorial tutorial;

    protected Notice notice;

    protected NoticeTask(Tutorial tutorial) {
        this.tutorial = tutorial;
        notice = buildNotice();
    }

    protected abstract Notice buildNotice();

    @Override
    public boolean doAction(App entity) {
        tutorial.setCurrentNotice(notice);
        return true;
    }
}
