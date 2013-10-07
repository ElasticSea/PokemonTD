package com.xkings.pokemontd;

/**
 * Created by Tomas on 10/7/13.
 */
public class Map<T> {
    private Object[][] map;

    public Map(Object[][] map) {
        this.map = map;
    }

    public int getWidth() {
        return map.length;
    }

    public int getHeight() {
        return map[0].length;
    }

    public T get(int x, int y) {
        return (T) map[x][y];
    }

    public void set(T element, int x, int y) {
        map[x][y] = element;
    }
}
