package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.pokemontd.graphics.ui.menu.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/27/13.
 */
public abstract class PickTable<E extends InteractiveBlock> extends HeaderGuiBox {

    public static final int COLUMNS = 3;
    protected final List<E> pickIcons;

    PickTable(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        pickIcons = createPickIcons();
        refresh();
    }

    private List<E> createPickIcons() {
        List<E> pickIcons = new ArrayList<E>();
        for (int i = 0; i < 9; i++) {
            pickIcons.add(createPickIcon());
        }
        return pickIcons;
    }

    protected abstract E createPickIcon();

    @Override
    public boolean hit(float x, float y) {
        System.out.println("HIT");
        for (Clickable clickable : pickIcons) {
            if (clickable.hit(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render() {
        super.render();
        for (E e : pickIcons) {
            e.render();
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        for (int i = 0; i < 9; i++) {
            Vector2 size = new Vector2(offsetRectange.width / COLUMNS, offsetRectange.height / COLUMNS);
            int x = (int) (i % COLUMNS * size.x);
            int y = (int) (i / COLUMNS * size.y);
            pickIcons.get(i).set(offsetRectange.x + x, offsetRectange.y + offsetRectange.height - (y + size.y), size.x,
                    size.y);
            pickIcons.get(i).refresh();
        }
    }
}
