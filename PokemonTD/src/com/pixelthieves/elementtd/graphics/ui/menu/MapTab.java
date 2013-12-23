package com.pixelthieves.elementtd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.App;
import com.pixelthieves.elementtd.Difficulty;
import com.pixelthieves.elementtd.MapType;
import com.pixelthieves.elementtd.graphics.ui.DisplayText;
import com.pixelthieves.elementtd.graphics.ui.GuiBox;
import com.pixelthieves.elementtd.graphics.ui.InteractiveBlock;

import java.util.ArrayList;

/**
 * Created by Tomas on 11/19/13.
 */
class MapTab extends ChildTab {

    private final MenuButton[] buttons;
    private final ArrayList<MapThumbnail> thumbnails;

    MapTab(final Menu menu, MenuTab parent, Rectangle rectangle) {
        super(menu, parent, menu.getRectangle((int) (rectangle.height * MapType.values().length), (int) rectangle.height), Difficulty.values().length + 1);
        this.buttons = new MenuButton[Difficulty.values().length];

        this.thumbnails = new ArrayList<MapThumbnail>();

        for (final MapType mapType : MapType.values()) {
            MapThumbnail mapThumbnail =
                    new MapThumbnail(mapType, menu, x + mapType.ordinal() * rectangle.height,
                            y, rectangle.height, rectangle.height);
            register(mapThumbnail);
            thumbnails.add(mapThumbnail);
        }
        this.setRenderLines(false);
    }

    @Override
    public void render() {
        super.render();
       for (MapThumbnail thumbnail : thumbnails) {
           thumbnail.render();
        }
    }

    private static class MapThumbnail extends InteractiveBlock {

        private final MapType mapType;
        private final Menu menu;
        private final GuiBox back;
        private final DisplayText title;
        private final int offset;

        private MapThumbnail(MapType mapType, Menu menu, float x, float y, float width, float height) {
            super(menu, new Rectangle(x, y, width, height/6*7));
            this.menu = menu;
            this.mapType = mapType;
           this.back = new GuiBox(menu,this);
            int textHeight = (int) (height / 7);
            this.title = new DisplayText(menu,new Rectangle(x,y+height,width,textHeight),menu.getFont());
            offset = (int) (width/10);
        }

        @Override
        public void refresh() {

        }

        @Override
        public void process(float x, float y) {
            menu.switchCard((MenuTab) null);
            App app = menu.getApp();
            app.setSessionStarted(true);
            app.freeze(false);
            app.switchMap(mapType);

        }

        @Override
        public void render() {
            super.render();
            back.render();
            title.render(mapType.toString());
            spriteBatch.begin();
            spriteBatch.draw(mapType.getTexture(App.getAssets()), x+offset, y+offset, width-offset*2, width-offset*2);
            spriteBatch.end();
        }
    }
}
