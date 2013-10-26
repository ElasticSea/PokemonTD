package com.xkings.pokemontd.graphics;

import com.xkings.pokemontd.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/7/13.
 */
public class TileMap<E> {
    private final List<Map<E>> levels;
    private final List<Integer> tileSize;

    public TileMap() {
        this.levels = new ArrayList<Map<E>>();
        this.tileSize = new ArrayList<Integer>();
    }

    public void addLevel(int width, int height, int tileSize) {
        this.levels.add(new Map<E>(new Object[width][height]));
        this.tileSize.add(tileSize);
    }

    public void set(E element, int x, int y, int level) {
        levels.get(level).set(element, x, y);
    }

    public E get(int x, int y, int level) {
        return levels.get(level).get(x, y);
    }

    public int getHeight(int index) {
        return levels.get(index).getHeight();
    }

    public int getWidth(int index) {
        return levels.get(index).getWidth();
    }

    public int getLevels() {
        return levels.size();
    }

    public int getTileSize(int index) {
        return this.tileSize.get(index);
    }
}
