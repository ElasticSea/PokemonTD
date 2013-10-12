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

    public static final double CIRCLE = PI * 2;
    public static final double QUADRANT = PI / 2.0;
    public static final int SEGMENTS = 3;
    public static final float SEGMENT = (float) (QUADRANT / SEGMENTS);
    private final int width;
    private final int height;
    private final TextureAtlas.AtlasRegion[][] fakeMap;
    private final GenericBlueprint<Entity> genericBlueprint;
    private Vector3 position;
    private double direction;
    private final List<Vector3> points = new ArrayList<Vector3>();
    public static final int BLOCK_SIZE = 2;
    private double lastDirection;


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

    public MapBuilder(int width, int height, int x, int y, Direction direction) {
        this.width = width;
        this.height = height;
        this.position = new Vector3(x, y, 0);
        this.direction = direction.getAngle();
        this.lastDirection = this.direction;
        Vector3 center = new Vector3((x + 0.5f) * BLOCK_SIZE, (y + 0.5f) * BLOCK_SIZE, 0);

        Vector3 previous = new Vector3(center.x + (float) Math.cos(this.direction + PI) * BLOCK_SIZE,
                center.y + (float) Math.sin(this.direction) * BLOCK_SIZE / 2f, 0);
        points.add(previous);
        genericBlueprint = new GenericBlueprint<Entity>(width * BLOCK_SIZE, height * BLOCK_SIZE);
        fakeMap = new TextureAtlas.AtlasRegion[width][height];
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
        commands.add(BuilderCommand.STRAIGHT);
        return this;
    }

    public MapData build() {
        for (BuilderCommand builderCommand : commands) {
            switch (builderCommand) {
                case LEFT:
                    createTurn(1);
                    break;
                case RIGHT:
                    createTurn(-1);
                    break;
                case STRAIGHT:
                    createPoint();
                    break;
            }
        }
        fixTextures();
        return new MapData(genericBlueprint, new Path(points), new TileMap(fakeMap, 2));
    }

    private void fixTextures() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = i * BLOCK_SIZE;
                int y = j * BLOCK_SIZE;
                fixTexture(x, y);
            }
        }
    }

    private void fixTexture(int x, int y) {
        if (!genericBlueprint.isWalkable(x, y)) {
            if (!genericBlueprint.isWalkable(x + BLOCK_SIZE, y)) {
                if (!genericBlueprint.isWalkable(x, y + BLOCK_SIZE)) {
                    setTexture(x, y, "pathRound1");
                } else if (!genericBlueprint.isWalkable(x, y - BLOCK_SIZE)) {
                    setTexture(x, y, "pathRound2");
                } else if (!genericBlueprint.isWalkable(x - BLOCK_SIZE, y)) {
                    setTexture(x, y, "pathHorizontal");
                }
            } else if (!genericBlueprint.isWalkable(x - BLOCK_SIZE, y)) {
                if (!genericBlueprint.isWalkable(x, y + BLOCK_SIZE)) {
                    setTexture(x, y, "pathRound0");
                } else if (!genericBlueprint.isWalkable(x, y - BLOCK_SIZE)) {
                    setTexture(x, y, "pathRound3");
                } else if (!genericBlueprint.isWalkable(x + BLOCK_SIZE, y)) {
                    setTexture(x, y, "pathHorizontal");

                }
            } else if (!genericBlueprint.isWalkable(x, y + BLOCK_SIZE)) {
                if (!genericBlueprint.isWalkable(x + BLOCK_SIZE, y)) {
                    setTexture(x, y, "pathRound1");
                } else if (!genericBlueprint.isWalkable(x - BLOCK_SIZE, y)) {
                    setTexture(x, y, "pathRound0");
                } else if (!genericBlueprint.isWalkable(x, y - BLOCK_SIZE)) {
                    setTexture(x, y, "pathVertical");

                }
            } else if (!genericBlueprint.isWalkable(x, y - BLOCK_SIZE)) {
                if (!genericBlueprint.isWalkable(x + BLOCK_SIZE, y)) {
                    setTexture(x, y, "pathRound2");
                } else if (!genericBlueprint.isWalkable(x - BLOCK_SIZE, y)) {
                    setTexture(x, y, "pathRound3");
                } else if (!genericBlueprint.isWalkable(x, y + BLOCK_SIZE)) {
                    setTexture(x, y, "pathVertical");

                }
            }
        }
    }

    private void setTexture(int x, int y, String name) {
        fakeMap[x / BLOCK_SIZE][y / BLOCK_SIZE] = Assets.getTexture(name);
    }

    private void createPoint() {
        writeToMap();
        Vector3 center = new Vector3((position.x + 0.5f) * BLOCK_SIZE, (position.y + 0.5f) * BLOCK_SIZE, 0);
        Vector3 next = getOffset(direction).scl(BLOCK_SIZE / 2f).add(center);
        points.add(next);
        moveTo(getOffset(direction));
    }

    private Vector3 getOffset(double direction) {
        return new Vector3((float) Math.cos(direction), (float) Math.sin(direction), 0);
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
        for (int i = 1; i <= SEGMENTS; i++) {
            double angle = direction + PI + SEGMENT * i * koeficient;
            float x = (float) (corner.x + Math.cos(angle) * BLOCK_SIZE / 2f);
            float y = (float) (corner.y + Math.sin(angle) * BLOCK_SIZE / 2f);
            points.add(new Vector3(x, y, 0));
        }
        moveTo(getOffset(direction));
    }

    private Vector3 lastPoint() {
        return points.get(points.size() - 1);
    }

}