package com.xkings.pokemontd.map;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.main.Assets;
import com.xkings.core.pathfinding.GenericBlueprint;
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
    private final int width;
    private final int height;
    private final TextureAtlas.AtlasRegion[][] fakeMap;
    private final GenericBlueprint<Entity> genericBlueprint;
    private final List<Vector3> centerPath;
    private final float pathOffset;
    private final int pathSize;
    private Vector3 position;
    private double direction;
    private final List<List<Vector3>> paths = createPats(PATHS);

    private List<List<Vector3>> createPats(int pathsCount) {
        List<List<Vector3>> paths = new ArrayList<List<Vector3>>();
        for (int i = 0; i < pathsCount; i++) {
            paths.add(new ArrayList<Vector3>());
        }
        return paths;
    }

    public static final int BLOCK_SIZE = 2;

    private enum MapTextures {
        Horizontal("pathHorizontal", true, false, true, false),
        Vertical("pathVertical", false, true, false, true),
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

    public MapBuilder(int width, int height, int x, int y, int pathSize,Direction direction) {
        this(width, height, x, y, pathSize,direction, DEFAULT_PATH_OFFSET);
    }

    public MapBuilder(int width, int height, int x, int y, int pathSize, Direction direction, float pathOffset) {
        this.width = width;
        this.height = height;
        this.position = new Vector3(x, y, 0);
        this.direction = direction.getAngle();
        this.pathOffset = pathOffset;
        this.pathSize = pathSize;
        genericBlueprint = new GenericBlueprint<Entity>(width * BLOCK_SIZE, height * BLOCK_SIZE);
        fakeMap = new TextureAtlas.AtlasRegion[width][height];
        centerPath = paths.get(paths.size() / 2);
        for (int i = 0; i < fakeMap.length; i++) {
            for (int j = 0; j < fakeMap[0].length; j++) {
                fakeMap[i][j] = Assets.getTexture("grass");
            }
        }
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
        createPoint(direction + PI, BLOCK_SIZE);

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
        fixTextures();
        return new MapData(genericBlueprint, new PathPack(paths), new TileMap(fakeMap, 2));
    }

    private void fixTextures() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = i * BLOCK_SIZE;
                int y = j * BLOCK_SIZE;
                if (!genericBlueprint.isWalkable(x, y)) {
                    for (MapTextures mapTextures : MapTextures.values()) {
                        boolean right = !genericBlueprint.isWalkable(x + BLOCK_SIZE, y);
                        boolean up = !genericBlueprint.isWalkable(x, y + BLOCK_SIZE);
                        boolean left = !genericBlueprint.isWalkable(x - BLOCK_SIZE, y);
                        boolean down = !genericBlueprint.isWalkable(x, y - BLOCK_SIZE);
                        if (mapTextures.match(right, up, left, down)) {
                            setTexture(x, y, mapTextures.getTexture());
                        }
                    }
                }
            }
        }
    }


    private void setTexture(int x, int y, TextureAtlas.AtlasRegion texture) {
        fakeMap[x / BLOCK_SIZE][y / BLOCK_SIZE] = texture;
    }

    private void createStraight() {
        writeToMap();
        createPoint(direction, BLOCK_SIZE / 2f);
        moveTo(getOffset(direction));
    }

    private void createPoint(double direction, float scale) {
        Vector3 center = new Vector3((position.x + 0.5f) * BLOCK_SIZE, (position.y + 0.5f) * BLOCK_SIZE, 0);
        Vector3 next = getOffset(direction).scl(scale).add(center);
        addPathSegment(next, this.direction);
    }


    private void writeToMap() {
        for (int i = 0; i < BLOCK_SIZE; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                genericBlueprint.setWalkable(App.pathBlock, (int) (position.x * BLOCK_SIZE + i),
                        (int) (position.y * BLOCK_SIZE + j));
            }
        }
    }

    private void moveTo(Vector3 offset) {
        position.add(offset);
    }

    private void createTurn(int koeficient) {
        direction += QUADRANT * koeficient;
        writeToMap();
        Vector3 corner = getOffset(direction).scl(BLOCK_SIZE / 2f).add(lastPoint());
        for (int i = 0; i <= SEGMENTS; i++) {
            double angle = direction + PI + SEGMENT * i * koeficient;
            float x = (float) (corner.x + Math.cos(angle) * BLOCK_SIZE / 2f);
            float y = (float) (corner.y + Math.sin(angle) * BLOCK_SIZE / 2f);
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