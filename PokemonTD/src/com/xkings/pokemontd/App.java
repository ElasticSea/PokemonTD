package com.xkings.pokemontd;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.graphics.camera.BoundedCameraHandler;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.graphics.camera.Renderer;
import com.xkings.core.input.EnhancedGestureDetector;
import com.xkings.core.input.GestureProcessor;
import com.xkings.core.main.Assets;
import com.xkings.core.main.Game2D;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class App extends Game2D {

    private DefaultRenderer renderer;
    private SpriteBatch spriteBatch;
    private TileMap tileMap;
    private CameraHandler cameraHandler;

    @Override
    protected void renderInternal() {
        renderer.render();
    }

    @Override
    protected void init(OrthographicCamera camera) {
        new Assets().addAtlas(new TextureAtlas("data/textures/packed.atlas"));
        spriteBatch = new SpriteBatch();
        renderer = new DefaultRenderer(camera);
        MapData map = createTestMap();
        tileMap = map.getTileMap();
       this.cameraHandler = new BoundedCameraHandler(camera, tileMap.getWidth()* tileMap.TILE_SIZE,
                tileMap.getHeight()* tileMap.TILE_SIZE, 0.0001f);
        initializeInput();
    }


    private void initializeInput() {
        Gdx.input.setInputProcessor(new EnhancedGestureDetector(new GestureProcessor(cameraHandler)));
    }


    private MapData createTestMap() {
        boolean[][] booleanMap = new boolean[][]{{true, true, true, true, false, false, true, true, true, true},
                {true, true, true, true, false, false, true, true, true, true},
                {true, true, false, false, false, false, true, true, true, true},
                {true, true, false, false, false, false, true, true, true, true},
                {true, true, false, false, true, true, false, false, false, false},
                {true, true, false, false, true, true, false, false, false, false},
                {true, true, false, false, false, false, false, false, true, true},
                {true, true, false, false, false, false, false, false, true, true},
                {true, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true, true, true}};
        AtlasRegion grassTexture = Assets.getTexture("grass");
        AtlasRegion pathRound0 = Assets.getTexture("pathRound0");
        AtlasRegion pathRound1 = Assets.getTexture("pathRound1");
        AtlasRegion pathRound2 = Assets.getTexture("pathRound2");
        AtlasRegion pathRound3 = Assets.getTexture("pathRound3");
        AtlasRegion pathHorizontal = Assets.getTexture("pathHorizontal");
        AtlasRegion pathVertical = Assets.getTexture("pathVertical");
        TileMap tileMap = new TileMap(
                new AtlasRegion[][]{
                        {grassTexture, grassTexture, pathHorizontal, grassTexture, grassTexture},
                        {grassTexture, pathRound1, pathRound3, grassTexture, grassTexture},
                        {grassTexture, pathHorizontal, grassTexture, pathRound1, pathVertical},
                        {grassTexture, pathRound0, pathVertical, pathRound3, grassTexture},
                        {grassTexture, grassTexture, grassTexture, grassTexture, grassTexture}}, 2);
        Blueprint testBlueprint = new Blueprint(booleanMap);
        Path testPath =
                new Path(new Vector2(5, 0), new Vector2(5, 3), new Vector2(3, 7), new Vector2(7, 7), new Vector2(7, 5),
                        new Vector2(10, 5));
        return new MapData(testBlueprint, testPath, tileMap);
    }

    @Override
    public void dispose() {

    }


    private class DefaultRenderer extends Renderer {

        protected DefaultRenderer(Camera camera) {
            super(camera);
        }

        @Override
        public void render() {
            spriteBatch.setProjectionMatrix(camera.combined);
            spriteBatch.begin();
            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    spriteBatch.draw(tileMap.get(i, j), i * tileMap.TILE_SIZE, j * tileMap.TILE_SIZE,
                            tileMap.TILE_SIZE, tileMap.TILE_SIZE);
                }
            }
            spriteBatch.end();
        }
    }
}
