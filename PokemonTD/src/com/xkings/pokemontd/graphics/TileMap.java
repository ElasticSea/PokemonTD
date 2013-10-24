package com.xkings.pokemontd.graphics;

import com.xkings.pokemontd.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TileMap<E> {
    private final List<Map<E>> levels;
    public final int TILE_SIZE;

    public TileMap(int width, int height, int levels, int tileSize) {
        this.levels = new ArrayList<Map<E>>(levels);
        this.TILE_SIZE = tileSize;
        for (int i = 0; i < levels; i++) {
            this.levels.add(new Map<E>(new Object[width][height]));
        }
    }

    public void set(E element, int x, int y, int level) {
        levels.get(level).set(element, x, y);
    }

    public E get(int x, int y, int level) {
        return levels.get(level).get(x, y);
    }

    public int getHeight() {
        return levels.get(0).getHeight();
    }

    public int getWidth() {
        return levels.get(0).getWidth();
    }

    public int getLevels() {
        return levels.size();
    }
}
