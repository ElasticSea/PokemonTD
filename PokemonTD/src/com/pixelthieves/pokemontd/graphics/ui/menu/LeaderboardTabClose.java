package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.pokemontd.graphics.ui.DisplayBlock;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;
import com.pixelthieves.pokemontd.graphics.ui.Gui;
import com.pixelthieves.pokemontd.graphics.ui.GuiBox;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Tomas on 11/19/13.
 */
class LeaderboardTabClose extends CloseExitTab {

    private final DisplayText leaderboardHeader;
    private final Leaderboard leaderboard;

    LeaderboardTabClose(final Menu menu, MenuTab parent, Rectangle rectangle, int count, Type type) {
        super(menu,parent, rectangle, type, false, count);
        leaderboardHeader = new DisplayText(menu, rects.get(0), menu.getFont());
        float buttonHeight2 = endButton != null ? endButton.height : closeButton.height;
        float leaderboardHeight = rectangle.height - (leaderboardHeader.height + buttonHeight2);
        leaderboard = new Leaderboard(menu, new Rectangle(x, y + buttonHeight2, width, leaderboardHeight), 10);
        this.setCloseTabWhenNotClicked(false);
        this.setRenderLines(false);
    }

    @Override
    public void render() {
        super.render();
        leaderboardHeader.render("Leaderboard");
        leaderboard.render();
    }

    private class Leaderboard extends DisplayBlock {
        private final Entry[] entries;
        private boolean refresh = true;

        Leaderboard(final Menu menu, Rectangle rectangle, int entriesCount) {
            super(menu, rectangle);
            this.entries = new Entry[entriesCount];
            float entryHeight = rectangle.height / entriesCount;
            for (int i = 0; i < entries.length; i++) {
                float offset = width / 20;
                entries[i] = new Entry(menu,
                        new Rectangle(x + offset, y + height - (i + 1) * entryHeight, width - offset * 2, entryHeight),
                        i + 1);
            }
        }

        @Override
        public void render() {
            if (menu.getGameSevice().isSignedIn() && refresh) {
                Map<String, String> data = menu.getGameSevice().getLeaderboard();
                if (data != null) {
                    Iterator<Map.Entry<String, String>> iterator = data.entrySet().iterator();
                    for (Entry entry : entries) {
                        entry.setEntry(iterator.hasNext() ? iterator.next() : null);
                    }
                    refresh = false;
                }
            }

            super.render();
            shapeRenderer.setColor(GuiBox.darkerColor);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            for (int i = 0; i <= entries.length; i++) {
                float offset = i * (height / entries.length);
                shapeRenderer.rect(x + segment, offset + y - LINE_HEIGHT / 2, width - segment * 2, LINE_HEIGHT);
            }
            shapeRenderer.end();
            for (Entry entry : entries) {
                entry.render();
            }
        }

        private static final float LINE_HEIGHT = 2;

        @Override
        public void refresh() {

        }

        private class Entry extends Rectangle implements Renderable {
            private final int id;
            private final DisplayText rank;
            private final DisplayText name;
            private final DisplayText score;
            private Map.Entry<String, String> entry;

            private Entry(Gui ui, Rectangle rectangle, int id) {
                super(rectangle);
                this.id = id;
                this.rank = new DisplayText(ui, new Rectangle(x, y, height, height), ui.getFont(),
                        BitmapFont.HAlignment.LEFT);
                Rectangle nameAndScore = new Rectangle(x + height, y, width - height, height);
                this.name = new DisplayText(ui, nameAndScore, ui.getFont(), BitmapFont.HAlignment.LEFT);
                this.score = new DisplayText(ui, nameAndScore, ui.getFont(), BitmapFont.HAlignment.RIGHT);
            }

            @Override
            public void render() {
                if (entry != null) {
                    rank.render(id + "#");
                    name.render(entry.getKey());
                    score.render(entry.getValue());
                }
            }

            public Map.Entry<String, String> getEntry() {
                return entry;
            }

            public void setEntry(Map.Entry<String, String> entry) {
                this.entry = entry;
            }
        }
    }
}
