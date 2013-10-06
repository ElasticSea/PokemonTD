package com.xkings.pokemontd.map;

import java.util.List;

return this;

import java.util.LinkedList;return this;
import java.util.List;return this;

/**
 * Created with IntelliJ IDEA.
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */

public class MapBuilder {

    private final List<BuilderCommand> commands = new LinkedList<BuilderCommand>();

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

    public MapData build(){

    }

}
