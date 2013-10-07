package com.xkings.pokemontd;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.main.Game2D;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;

public class App extends Game2D{

    private MapData map;

    @Override
    protected void renderInternal() {

    }

    @Override
    protected void init(OrthographicCamera camera) {
         this.map = createTestMap();
    }

    private MapData createTestMap() {
        boolean[][] booleanMap = new boolean[][]{
                {true, true, true,  true,  false, false, true,  true,  true,  true },
                {true, true, true,  true,  false, false, true,  true,  true,  true },
                {true, true, false, false, false, false, true,  true,  true,  true },
                {true, true, false, false, false, false, true,  true,  true,  true },
                {true, true, false, false,  true,  true, false, false, false, false},
                {true, true, false, false,  true,  true, false, false, false, false},
                {true, true, false, false, false, false, false, false, true,  true },
                {true, true, false, false, false, false, false, false, true,  true },
                {true, true, true,  true,  true,  true,  true,  true,  true,  true },
                {true, true, true,  true,  true,  true,  true,  true,  true,  true }
        };
        Blueprint testBlueprint = new Blueprint(booleanMap);
        Path testPath = new Path(
                new Vector2(5,0),
                new Vector2(5,3),
                new Vector2(3,7),
                new Vector2(7,7),
                new Vector2(7,5),
                new Vector2(10,5)
                );
        return new MapData(testBlueprint, testPath);
    }

    @Override
    public void dispose() {

    }
}
