package com.xkings.pokemontd.map;

import com.badlogic.gdx.math.Vector3;

import java.util.Arrays;
import java.util.List;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:07
 */

/**
 * Structure containing path and position on it.
 */
public class Path implements Comparable<Path> {
    private final List<Vector3> path;
    private final float width;
    private int position;
    private float toTravel;
    private boolean changedDirection;

    public Path(Vector3... path) {
        this(Arrays.asList(path), 0, 0);
    }

    public Path(Path path) {
        this(path.getPath(), path.getPosition(), path.getWidth());
    }

    public Path(List<Vector3> path, int position, float width) {
        this.path = path;
        this.position = position;
        this.width = width;
    }

    public List<Vector3> getPath() {
        return path;
    }

    public Vector3 get() {
        return path.get(position);
    }

    public void next() {
        if (changedDirection) {
            if (position == 0) {
                changedDirection = false;
                next();
            } else {
                this.position--;
            }
        } else {
            this.position++;
        }
    }

    public boolean isFinished() {
        return !changedDirection ? position == path.size() : false;
    }

    public void reset() {
        position = 0;
    }

    public int getPosition() {
        return position;
    }

    public void changeDirection() {
        if (!changedDirection && position > 0) {
            changedDirection = true;
            next();
        } else {
            changedDirection = false;
        }
    }

    public float getWidth() {
        return width;
    }

    public void setToTravel(float toTravel) {
        this.toTravel = toTravel;
    }

    public float getToTravel() {
        return toTravel;
    }

    public boolean isChangedDirection() {
        return changedDirection;
    }

    @Override
    public int compareTo(Path o) {
        if (this.position > o.position) {
            return 1;
        } else if (this.position < o.position) {
            return -1;
        } else {
            if (this.getToTravel() > o.getToTravel()) {
                return 1;
            } else if (this.getToTravel() > o.getToTravel()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
