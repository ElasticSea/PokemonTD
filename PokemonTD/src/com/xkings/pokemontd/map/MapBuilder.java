package com.xkings.pokemontd.map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.main.Assets;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.graphics.TileMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.PI;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

public class MapBuilder {

    public static final double QUADRANT = PI / 2.0;
    public static final int SEGMENTS = 9;
    public static final int PATHS = 11;
    public static final float DEFAULT_PATH_OFFSET = 1 / 4f;
    public static final float SEGMENT = (float) (QUADRANT / SEGMENTS);
    public static final int DEFAULT_MAP_OFFSET = 20;
    private final Rectangle mapOffset;
    private TileMap<TextureAtlas.AtlasRegion> textureMap;
    private Blueprint blueprint;
    private List<Vector3> centerPath;
    private final float pathOffset;
    private final int pathSize;
    private Vector3 position;
    private double direction;
    private final List<List<Vector3>> paths = createPats(PATHS);
    private int width;
    private int height;

    private List<List<Vector3>> createPats(int pathsCount) {
        List<List<Vector3>> paths = new ArrayList<List<Vector3>>();
        for (int i = 0; i < pathsCount; i++) {
            paths.add(new ArrayList<Vector3>());
        }
        return paths;
    }

    private enum MapTextures {
        Horizontal("pathHorizontal", true, false, true, false),
        Horizontal2("pathHorizontal", true, false, false, false),
        Horizontal3("pathHorizontal", false, false, true, false),
        Vertical("pathVertical", false, true, false, true),
        Vertical2("pathVertical", false, false, false, true),
        Vertical3("pathVertical", false, true, false, false),
        LeftUpRound("pathRound0", false, true, true, false),
        LeftDownRound("pathRound3", false, false, true, true),
        RightUpRound("pathRound1", true, true, false, false),
        RightDownRound("pathRound2", true, false, false, true);
        private final TextureAtlas.AtlasRegion texture;
        private final boolean right;
        private final boolean up;
        private final boolean left;
        private final boolean down;

        MapTextures(String name, boolean right, boolean up, boolean left, boolean down) {
            this.texture = Assets.getTexture(name);
            this.right = right;
            this.up = up;
            this.left = left;
            this.down = down;
        }

        public TextureAtlas.AtlasRegion getTexture() {
            return texture;
        }

        public boolean match(boolean right, boolean up, boolean left, boolean down) {
            return right == this.right && up == this.up && left == this.left && down == this.down;
        }
    }


    public enum Direction {
        LEFT(QUADRANT * 2), RIGHT(0), UP(QUADRANT), DOWN(QUADRANT * 3);
        private final double angle;

        private Direction(double angle) {
            this.angle = angle;
        }

        public double getAngle() {
            return angle;
        }


    }

    private final List<BuilderCommand> commands = new LinkedList<BuilderCommand>();

    public MapBuilder(int x, int y, int pathSize, Direction direction) {
        this(x, y, pathSize, direction, DEFAULT_PATH_OFFSET,
                new Rectangle(DEFAULT_MAP_OFFSET, DEFAULT_MAP_OFFSET, DEFAULT_MAP_OFFSET, DEFAULT_MAP_OFFSET));
    }

    public MapBuilder(int x, int y, int pathSize, Direction direction, float pathOffset, Rectangle mapOffset) {
        this.position = new Vector3(x, y, 0);
        this.direction = direction.getAngle();
        this.pathOffset = pathOffset;
        this.pathSize = pathSize;
        this.mapOffset = mapOffset;
    }

    public MapBuilder addLeft() {
        commands.add(BuilderCommand.LEFT);
        return this;
    }

    public MapBuilder addRight() {
        commands.add(BuilderCommand.RIGHT);
        return this;
    }

    public MapBuilder addStraight() {
        addStraight(1);
        return this;
    }


    public MapBuilder addStraight(int n) {
        for (int i = 0; i < n; i++) {
            commands.add(BuilderCommand.STRAIGHT);
        }
        return this;
    }

    public MapData build() {
        Vector3 dimensions = computeMapDimensions(direction, commands);
        width = (int) (dimensions.x + 1 + mapOffset.x + mapOffset.width);
        height = (int) (dimensions.y + mapOffset.y + mapOffset.height);
        position.y = dimensions.y - 1 + mapOffset.y;
        position.x = 2 + mapOffset.x;


        createPoint(direction + PI, pathSize);

        blueprint = new Blueprint(width * pathSize, height * pathSize, true);
        textureMap = new TileMap<TextureAtlas.AtlasRegion>();
        textureMap.addLevel(width, height, 2);
        textureMap.addLevel(width, height, 2);
        textureMap.addLevel(width, height, 2);
        textureMap.addLevel(width, height, 2);
        textureMap.addLevel(width * 2, height * 2, 1);

        centerPath = paths.get(paths.size() / 2);

        Vector3 entrancePosition = position.cpy().add(getOffset(direction + PI));
        createEntrance(entrancePosition);

        for (BuilderCommand builderCommand : commands) {
            switch (builderCommand) {
                case LEFT:
                    createTurn(1);
                    break;
                case RIGHT:
                    createTurn(-1);
                    break;
                case STRAIGHT:
                    createStraight();
                    break;
            }
        }
        entrancePosition = position.cpy();
        createEntrance(entrancePosition);
        fixTextures();
        createPoint(direction, 0);
        scaleTo(paths, App.WORLD_SCALE);
        return new MapData(blueprint, new PathPack(paths, (.5f - pathOffset) * pathSize), textureMap);
    }

    private void createEntrance(Vector3 position) {
        setTexture((int) position.x * pathSize, (int) position.y * pathSize, 3, Assets.getTexture("entrance"));
        setTexture((int) position.x * pathSize, (int) position.y * pathSize, 0, MapTextures.Vertical.getTexture());
    }

    private Vector3 computeMapDimensions(double direction, List<BuilderCommand> commands) {
        Vector3 position = new Vector3();
        Vector3 min = position.cpy();
        Vector3 max = position.cpy();

        position.add(getOffset(direction));
        setMaxMin(min, max, position);

        for (BuilderCommand builderCommand : commands) {
            switch (builderCommand) {
                case LEFT:
                    direction += QUADRANT;
                    break;
                case RIGHT:
                    direction -= QUADRANT;
                    break;
            }
            position.add(getOffset(direction));
            setMaxMin(min, max, position);


        }
        return max.sub(min);
    }

    private void setMaxMin(Vector3 min, Vector3 max, Vector3 position) {
        min.x = Math.min(min.x, position.x);
        min.y = Math.min(min.y, position.y);
        max.x = Math.max(max.x, position.x);
        max.y = Math.max(max.y, position.y);
    }

    private void scaleTo(List<List<Vector3>> paths, int worldScale) {
        for (List<Vector3> path : paths) {
            for (Vector3 point : path) {
                point.scl(worldScale);
            }
        }
    }

    private void fixTextures() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = i * pathSize;
                int y = j * pathSize;
                setTextureSafe(x, y, 0, Assets.getTexture("grass"));
                if (!blueprint.isWalkable(x, y)) {
                    for (MapTextures mapTextures : MapTextures.values()) {
                        boolean right = !blueprint.isWalkable(x + pathSize, y);
                        boolean up = !blueprint.isWalkable(x, y + pathSize);
                        boolean left = !blueprint.isWalkable(x - pathSize, y);
                        boolean down = !blueprint.isWalkable(x, y - pathSize);
                        if (mapTextures.match(right, up, left, down)) {
                            setTexture(x, y, 0, mapTextures.getTexture());
                        }
                    }
                }
            }
        }

        for (int i = 0; i < blueprint.getWidth(); i++) {
            for (int j = 0; j < blueprint.getHeight(); j++) {
                if (i < mapOffset.x * pathSize || i >= blueprint.getWidth() - mapOffset.width * pathSize ||
                        j < mapOffset.y * pathSize || j >= blueprint.getHeight() - mapOffset.height * pathSize) {
                    int level = 4;
                    if (getTexture(i, j, level - 1) == null) {
                        setTextureSafe(i, j, 2, Assets.getTexture("grass"));
                    }
                    if (blueprint.isWalkable(i, j - 1)) {
                        setTextureSmallSafe(i, j, level, Assets.getTexture("treeSub"));

                    } else if (!blueprint.isWalkable(i, j + 1)) {
                        setTextureSmallSafe(i, j, level, Assets.getTexture("treeTop"));
                    } else if (getTexture(i, j - 1, level - 1) != null || getTexture(i, j, level - 1) == null) {
                        setTextureSmallSafe(i, j, level, Assets.getTexture("tree"));
                        setTextureSmallSafe(i, j, level, Assets.getTexture("tree"));
                    }
                    blueprint.setWalkable(false, i, j);
                }

            }

        }

    }


    private void setTexture(int x, int y, int level, TextureAtlas.AtlasRegion texture) {
        textureMap.set(texture, x / pathSize, y / pathSize, level);
    }

    private void setTextureSafe(int x, int y, int level, TextureAtlas.AtlasRegion texture) {
        if (getTexture(x, y, level) == null) {
            textureMap.set(texture, x / pathSize, y / pathSize, level);
        }
    }

    private TextureAtlas.AtlasRegion getTextureSmall(int x, int y, int level) {
        return textureMap.get(x, y, level);
    }


    private void setTextureSmallSafe(int x, int y, int level, TextureAtlas.AtlasRegion texture) {
        if (getTextureSmall(x, y, level) == null) {
            textureMap.set(texture, x, y, level);
        }
    }


    private TextureAtlas.AtlasRegion getTexture(int x, int y, int level) {
        return textureMap.get(x / pathSize, y / pathSize, level);
    }


    private void createStraight() {
        writeToMap();
        createPoint(direction, pathSize / 2f);
        moveTo(getOffset(direction));
    }

    private void createPoint(double direction, float scale) {
        Vector3 center = new Vector3((position.x + 0.5f) * pathSize, (position.y + 0.5f) * pathSize, 0);
        Vector3 next = getOffset(direction).scl(scale).add(center);
        addPathSegment(next, this.direction);
    }


    private void writeToMap() {
        for (int i = 0; i < pathSize; i++) {
            for (int j = 0; j < pathSize; j++) {
                blueprint.setWalkable(false, (int) (position.x * pathSize + i), (int) (position.y * pathSize + j));
            }
        }
    }

    private void moveTo(Vector3 offset) {
        position.add(offset);
    }

    private void createTurn(int koeficient) {
        direction += QUADRANT * koeficient;
        writeToMap();
        Vector3 corner = getOffset(direction).scl(pathSize / 2f).add(lastPoint());
        for (int i = 0; i <= SEGMENTS; i++) {
            double angle = direction + PI + SEGMENT * i * koeficient;
            float x = (float) (corner.x + Math.cos(angle) * pathSize / 2f);
            float y = (float) (corner.y + Math.sin(angle) * pathSize / 2f);
            addCornerSegment(new Vector3(x, y, 0), corner, koeficient);
        }
        moveTo(getOffset(direction));
    }

    private void addCornerSegment(Vector3 segment, Vector3 origin, int koeficient) {
        for (int i = 0; i < paths.size(); i++) {
            Vector3 pathDistance = segment.cpy().sub(origin).scl(2);
            Vector3 offsetedPathDistance = pathDistance.cpy().scl(1 - pathOffset * 2f);
            Vector3 offset = pathDistance.cpy().scl(pathOffset);
            Vector3 segmentDistance = offsetedPathDistance.scl(1f / (paths.size() / 2) / 2f);
            int pathNumber = koeficient == 1 ? i : paths.size() - 1 - i;
            paths.get(pathNumber).add(segmentDistance.scl(i).add(origin).add(offset));
        }
    }


    private void addPathSegment(Vector3 segment, double direction) {
        Vector3 offset = getOffset(direction + QUADRANT);
        addCornerSegment(segment, offset.add(segment), 1);
    }

    private Vector3 getOffset(double direction) {
        return new Vector3((float) Math.cos(direction), (float) Math.sin(direction), 0);
    }

    private Vector3 lastPoint() {
        return centerPath.get(centerPath.size() - 1);
    }

}