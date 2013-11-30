package com.pixelthieves.pokemontd.map;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.core.pathfinding.Blueprint;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.TileMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.PI;

/**
 * User: Seda
 * Date: 5.10.13
 * Time: 12:03
 */

/**
 * Class responsible for map generation.
 */
public class MapBuilder {

    public static final double QUADRANT = PI / 2.0;
    public static final int SEGMENTS = 20;
    public static final int PATHS = 11;
    public static final float DEFAULT_PATH_OFFSET = 1 / 4f;
    public static final float SEGMENT = (float) (QUADRANT / SEGMENTS);
    public static final int DEFAULT_MAP_OFFSET = 20;
    private final Rectangle mapOffset;
    private TileMap<TextureAtlas.AtlasRegion> textureMap;
    private Blueprint blueprint;
    private List<Vector3> centerPath;
    private final float pathOffset;
    private final int pathWidth;
    private Vector3 position;
    private double direction;
    private final List<List<Vector3>> paths = createPats(PATHS);
    private int width;
    private int height;
    private Blueprint gameBlueprint;

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

    /**
     * Convinient class for translating direction to appropriate angle.
     */
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

   /* public MapBuilder(int x, int y, int pathWidth, Direction direction) {
        this(x, y, pathWidth, direction, DEFAULT_PATH_OFFSET,
                new Rectangle(DEFAULT_MAP_OFFSET, DEFAULT_MAP_OFFSET, DEFAULT_MAP_OFFSET, DEFAULT_MAP_OFFSET));
    } */

    /**
     * Map builder constructor sets prerequisites before map generation.
     *
     * @param x          starting coordinate
     * @param y          starting coordinate
     * @param pathWidth  width of path in blocks.
     * @param direction  initial direction of path generation.
     * @param pathOffset path offset within a path block. Larger offset leads to smaller path width.
     * @param mapOffset  creates an offset that will be populated with trees textures.
     */
    public MapBuilder(int x, int y, int pathWidth, Direction direction, float pathOffset, Rectangle mapOffset) {
        this.position = new Vector3(x, y, 0);
        this.direction = direction.getAngle();
        this.pathOffset = pathOffset;
        this.pathWidth = pathWidth;
        this.mapOffset = mapOffset;
    }

    /**
     * Adds left building block.
     *
     * @return current instance for convinience
     */
    public MapBuilder addLeft() {
        commands.add(BuilderCommand.LEFT);
        return this;
    }

    /**
     * Adds right building block.
     *
     * @return current instance for convinience
     */
    public MapBuilder addRight() {
        commands.add(BuilderCommand.RIGHT);
        return this;
    }

    /**
     * Adds right building block.
     *
     * @return current instance for convinience
     */
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

    /**
     * Triggers the map generation. Generation starts on coordinates [x, y] and continues along starting direction,
     * direction than changes based on accumulated build commands.
     *
     * @return generated map normal
     */
    public MapData build() {
        Vector3 dimensions = computeMapDimensions(direction, commands);
        width = (int) (dimensions.x + 1 + mapOffset.x + mapOffset.width);
        height = (int) (dimensions.y + mapOffset.y + mapOffset.height);
        position.y = dimensions.y - 1 + mapOffset.y;
        position.x = 1 + mapOffset.x;


        createPoint(direction + PI, pathWidth);

        blueprint = new Blueprint(0, 0, width * pathWidth, height * pathWidth, true);
        gameBlueprint = new Blueprint((int) mapOffset.x * pathWidth, (int) mapOffset.y * pathWidth,
                (int) (dimensions.x + 1) * pathWidth, (int) (dimensions.y + 0) * pathWidth, true);
        textureMap = new TileMap<TextureAtlas.AtlasRegion>();
        textureMap.addLevel(width, height, pathWidth);
        textureMap.addLevel(width, height, pathWidth);
        textureMap.addLevel(width, height, pathWidth);
        textureMap.addLevel(width, height, pathWidth);
        textureMap.addLevel(width * pathWidth, height * pathWidth, 1);

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
        return new MapData(blueprint, gameBlueprint, new PathPack(paths, (.5f - pathOffset) * pathWidth), textureMap);
    }

    private void createEntrance(Vector3 position) {
        setTexture((int) position.x * pathWidth, (int) position.y * pathWidth, 3, Assets.getTexture("entrance"));
        setTexture((int) position.x * pathWidth, (int) position.y * pathWidth, 0, MapTextures.Vertical.getTexture());
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
                int x = i * pathWidth;
                int y = j * pathWidth;
                setTextureSafe(x, y, 0, Assets.getTexture("grass"));
                if (!blueprint.isWalkable(x, y)) {
                    for (MapTextures mapTextures : MapTextures.values()) {
                        boolean right = !blueprint.isWalkable(x + pathWidth, y);
                        boolean up = !blueprint.isWalkable(x, y + pathWidth);
                        boolean left = !blueprint.isWalkable(x - pathWidth, y);
                        boolean down = !blueprint.isWalkable(x, y - pathWidth);
                        if (mapTextures.match(right, up, left, down)) {
                            setTexture(x, y, 0, mapTextures.getTexture());
                        }
                    }
                }
            }
        }

        for (int i = 0; i < blueprint.getWidth(); i++) {
            for (int j = 0; j < blueprint.getHeight(); j++) {
                if (i < mapOffset.x * pathWidth || i >= blueprint.getWidth() - mapOffset.width * pathWidth ||
                        j < mapOffset.y * pathWidth || j >= blueprint.getHeight() - mapOffset.height * pathWidth) {
                    int level = 4;
                    if (getTexture(i, j, level - 1) == null) {
                        setTextureSafe(i, j, 2, Assets.getTexture("grass"));
                    }
                    if (blueprint.isWalkable(i, j - 1)) {
                        setTextureSmallSafe(i, j, level, Assets.getTexture("treeSub"));

                    } else if (gameBlueprint.isWalkable(i, j + 1)) {
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
        textureMap.set(texture, x / pathWidth, y / pathWidth, level);
    }

    private void setTextureSafe(int x, int y, int level, TextureAtlas.AtlasRegion texture) {
        if (getTexture(x, y, level) == null) {
            textureMap.set(texture, x / pathWidth, y / pathWidth, level);
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
        return textureMap.get(x / pathWidth, y / pathWidth, level);
    }


    private void createStraight() {
        writeToMap();
        createPoint(direction, pathWidth / 2f);
        moveTo(getOffset(direction));
    }

    private void createPoint(double direction, float scale) {
        Vector3 center = new Vector3((position.x + 0.5f) * pathWidth, (position.y + 0.5f) * pathWidth, 0);
        Vector3 next = getOffset(direction).scl(scale).add(center);
        addPathSegment(next, this.direction);
    }


    private void writeToMap() {
        for (int i = 0; i < pathWidth; i++) {
            for (int j = 0; j < pathWidth; j++) {
                blueprint.setWalkable(false, (int) (position.x * pathWidth + i), (int) (position.y * pathWidth + j));
            }
        }
    }

    private void moveTo(Vector3 offset) {
        position.add(offset);
    }

    private void createTurn(int koeficient) {
        direction += QUADRANT * koeficient;
        writeToMap();
        Vector3 corner = getOffset(direction).scl(pathWidth / 2f).add(lastPoint());
        for (int i = 0; i <= SEGMENTS; i++) {
            double angle = direction + PI + SEGMENT * i * koeficient;
            float x = (float) (corner.x + Math.cos(angle) * pathWidth / 2f);
            float y = (float) (corner.y + Math.sin(angle) * pathWidth / 2f);
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