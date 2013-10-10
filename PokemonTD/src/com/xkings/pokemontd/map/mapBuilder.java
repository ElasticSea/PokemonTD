package com.xkings.pokemontd.map;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.main.Assets;
import com.xkings.core.pathfinding.Blueprint;
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

    private final int width;
    private final int height;
    private final TextureAtlas.AtlasRegion[][] fakeMap;
    private final GenericBlueprint<Entity> genericBlueprint;
    private Vector2 position;
    private float direction;
    public static final float QUADRANT = (float) (PI / 2f);
    private final List<Vector3> points = new ArrayList<Vector3>();
    public static final int BLOCK_SIZE = 2;


    public enum Direction {
        LEFT(QUADRANT * 2), RIGHT(0), UP(QUADRANT), DOWN(QUADRANT * 3);
        private final float angle;

        private Direction(float angle) {
            this.angle = angle;
        }

        public float getAngle() {
            return angle;
        }


    }

    private final List<BuilderCommand> commands = new LinkedList<BuilderCommand>();

    public MapBuilder(int width, int height, int x, int y, Direction direction) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(x, y);
        this.direction = direction.getAngle();
        Vector3 center = new Vector3(x + BLOCK_SIZE/2f, y + BLOCK_SIZE/2f, 0);
        Vector3 previous = new Vector3(center.x + (float) Math.cos(this.direction + PI) * BLOCK_SIZE/2f, center.y + (float) Math.sin(this.direction) * BLOCK_SIZE/2f, 0);
        points.add(previous);
        genericBlueprint = new GenericBlueprint<Entity>(width,height);
        fakeMap = new TextureAtlas.AtlasRegion[width/BLOCK_SIZE][height/BLOCK_SIZE];
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
                    createLeft();
                    break;
                case RIGHT:
                    createRight();
                    break;
                case STRAIGHT:
                    createStraight();
                    break;
            }
        }

        return new MapData(genericBlueprint, new Path(points), new TileMap(fakeMap,2));
    }

    private void createStraight() {
        moveTo((float) Math.cos(direction)*BLOCK_SIZE, (float) Math.sin(direction)*BLOCK_SIZE);
        Vector3 center = new Vector3(position.x + BLOCK_SIZE/2f, position.y + BLOCK_SIZE/2f, 0);
        Vector3 next = new Vector3(center.x + (float) Math.cos(this.direction) * BLOCK_SIZE/2f, center.y + (float) Math.sin(this.direction) * BLOCK_SIZE/2f, 0);
        points.add(next);
    }
                                        
    private void moveTo(float x, float y) {
        System.out.println(position);
        position.add(x, y);
        fakeMap[(int)(position.x/BLOCK_SIZE)][(int)(position.y/BLOCK_SIZE)] = Assets.getTexture("pathHorizontal");
        for (int i = 0; i < BLOCK_SIZE; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                genericBlueprint.setWalkable(App.pathBlock,(int)(position.x+i),(int)(position.y+j));
            }
        }

    }

    private void createRight() {
        direction -= QUADRANT;
        moveTo((float) Math.cos(direction)*BLOCK_SIZE, (float) Math.sin(direction)*BLOCK_SIZE);
        Vector3 center = new Vector3(position.x + BLOCK_SIZE/2f, position.y + BLOCK_SIZE/2f, 0);
        Vector3 next = new Vector3(center.x + (float) Math.cos(this.direction) * BLOCK_SIZE/2f, center.y + (float) Math.sin(this.direction) * BLOCK_SIZE/2f, 0);
        points.add(next);

    }

    private void createLeft() {
        direction += QUADRANT;
    }



<<<<<<< HEAD
    private void moveTo(float x, float y) {
        System.out.println(position);
        position.add(x, y);
        fakeMap[(int)(position.x/BLOCK_SIZE)][(int)(position.y/BLOCK_SIZE)] = Assets.getTexture("pathHorizontal");
        for (int i = 0; i < BLOCK_SIZE; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                genericBlueprint.setWalkable(App.pathBlock,(int)(position.x+i),(int)(position.y+j));
            }
        }

    }

    private void createRight() {
        direction -= QUADRANT;
        moveTo((float) Math.cos(direction)*BLOCK_SIZE, (float) Math.sin(direction)*BLOCK_SIZE);
        Vector3 center = new Vector3(position.x + BLOCK_SIZE/2f, position.y + BLOCK_SIZE/2f, 0);
        Vector3 next = new Vector3(center.x + (float) Math.cos(this.direction) * BLOCK_SIZE/2f, center.y + (float) Math.sin(this.direction) * BLOCK_SIZE/2f, 0);
        points.add(next);

    }

    private void createLeft() {
        direction += QUADRANT;
        moveTo((float) Math.cos(direction)*BLOCK_SIZE, (float) Math.sin(direction)*BLOCK_SIZE);
        Vector3 center = new Vector3(position.x + BLOCK_SIZE/2f, position.y + BLOCK_SIZE/2f, 0);
        Vector3 next = new Vector3(center.x + (float) Math.cos(this.direction) * BLOCK_SIZE/2f, center.y + (float) Math.sin(this.direction) * BLOCK_SIZE/2f, 0);
        points.add(next);
    }



=======
>>>>>>> mapBuilder function Straight has been created.
}