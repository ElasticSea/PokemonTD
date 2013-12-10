package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.Element;
import com.pixelthieves.pokemontd.entity.tower.TowerName;
import com.pixelthieves.pokemontd.entity.tower.TowerType;
import com.pixelthieves.pokemontd.graphics.ui.DisplayBlock;
import com.pixelthieves.pokemontd.graphics.ui.DisplayText;
import com.pixelthieves.pokemontd.graphics.ui.Gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.pixelthieves.pokemontd.entity.tower.TowerName.*;

/**
 * Created by Tomas on 11/19/13.
 */
class AbilityTab extends ScrollableChildTab {

    public static final int OFFSET = 2;
    private int index;
    private Menu menu;
    private final List<DisplayBlock> entries = new ArrayList<DisplayBlock>();

    AbilityTab(final Menu menu, MenuTab parent, Rectangle rectangle, int count) {
        super(menu, parent, rectangle, count);
        this.menu = menu;
        index = OFFSET;
        this.setContent(new EntryTable(menu, rectangle));
    }


    private class EntryTable extends DisplayBlock {


        protected EntryTable(Gui gui, Rectangle rectangle) {
            super(gui, rectangle);
            createTowers("DUAL TOWERS", Noble, Sunny, Poison, Ice, Burst, Blow, Money, Pebble, Dizzy, Spell);
            addTextEntry("");
            createTowers("TRIPLE TOWERS", Grind, Meteor, Vampire, Disease, Confused, Charged, Stomp, Erruption, Hypnotic);
        }

        private void createTowers(String header, TowerName... names) {
            addTextEntry(header);
            addTextEntry("");

            for (TowerName name : names) {
                float size = menu.getFont().getBounds(names[0].toString()).height;
                DisplayBlock entry = new Entry(name, this, new Rectangle(size, height - size * index++, width - 2 * size, size));
                entry.refresh();
                entries.add(entry);
            }
        }

        private void addTextEntry(String header) {
            float size = menu.getFont().getBounds(header).height;
            DisplayBlock entry =
                    new HeaderEntry(header, this, new Rectangle(size, height - size * index++, width - 2 * size, size));
            entry.refresh();
            entries.add(entry);
        }

        @Override
        public void refresh() {
            for (DisplayBlock entry : entries) {
                entry.refresh();
            }
        }


        @Override
        public void render() {
            super.render();
            for (DisplayBlock entry : entries) {
                entry.render();
            }
        }

        @Override
        public float getHeight() {
            float minY = Float.MAX_VALUE;
            float maxY = Float.MIN_VALUE;
            for (DisplayBlock entry : entries) {
                minY = Math.min(minY, entry.y -  entry.height);
                maxY = Math.max(maxY, entry.y + entry.height * OFFSET);
            }
            return Math.abs(maxY - minY);
        }
    }

    private class Entry extends DisplayBlock {
        private final DisplayText towerName;
        private final List<DisplayText> elementsText = new ArrayList<DisplayText>();
        private final List<Element> elements = new ArrayList<Element>();
        private final TowerType type;
        private final EntryTable entryTable;
        private final Rectangle rectangle;

        private Entry(TowerName name, EntryTable entryTable, Rectangle rectangle) {
            super(menu, rectangle);
            this.entryTable = entryTable;
            this.rectangle = new Rectangle(rectangle);
            this.type = TowerType.getType(name);
            this.towerName = new DisplayText(menu, rectangle, menu.getFont(), BitmapFont.HAlignment.LEFT);

            for (Element element : Element.values()) {
                if (type.getCost().hasElement(element, 1)) {
                    elementsText.add(new DisplayText(menu, rectangle, menu.getFont(), BitmapFont.HAlignment.RIGHT));
                    elements.add(element);
                }
            }
        }

        @Override
        public void render() {
            towerName.render(type.getName().name());
            Iterator<Element> i = elements.iterator();
            for (DisplayText displayText : elementsText) {
                Element element = i.next();
                displayText.render(element.name(), element.getColor());
            }
        }

        @Override
        public void refresh() {
            this.x = entryTable.x + rectangle.x;
            this.y = entryTable.y + rectangle.y;

            towerName.set(this);
            for (DisplayText displayText : elementsText) {
                displayText.set(this);
            }

            float positionX = x + width;
            for (int i = elementsText.size() - 1; i >= 0; i--) {
                DisplayText text = elementsText.get(i);
                text.recalculatePosition(" " + elements.get(i).toString());
                text.setWidth(text.getTextWidth());
                positionX -= text.getWidth();
                text.x = positionX;
            }
        }
    }

    private class HeaderEntry extends DisplayBlock {
        private final DisplayText header;
        private final EntryTable entryTable;
        private final Rectangle rectangle;
        private final String headerText;

        private HeaderEntry(String headerText, EntryTable entryTable, Rectangle rectangle) {
            super(menu, rectangle);
            this.headerText = headerText;
            this.entryTable = entryTable;
            this.rectangle = new Rectangle(rectangle);
            this.header = new DisplayText(menu, rectangle, menu.getFont(), BitmapFont.HAlignment.CENTER);
        }

        @Override
        public void render() {
            header.render(headerText);
        }

        @Override
        public void refresh() {
            this.x = entryTable.x + rectangle.x;
            this.y = entryTable.y + rectangle.y;
            header.set(this);
        }
    }
}
