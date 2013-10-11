package com.xkings.pokemontd.map;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

public class MapBuilder {

    private final int width;
    private final int height;
    private final int x;
    private final int y;
    private final Direction direction;

    public enum Direction {
        LEFT, RIGHT, UP, DONW;
    }

    private final List<BuilderCommand> commands = new LinkedList<BuilderCommand>();

    public MapBuilder(int width, int height, int x, int y, Direction direction) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public MapBuilder left() {
        commands.add(BuilderCommand.LEFT);
        return this;
    }

    public MapBuilder right() {
        commands.add(BuilderCommand.RIGHT);
        return this;
    }

    public MapBuilder straight() {
        commands.add(BuilderCommand.STRAIGHT);
        return this;
    }

    public MapData build() {
        return null;
    }

}
