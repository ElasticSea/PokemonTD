package com.xkings.pokemontd;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.graphics.camera.BoundedCameraHandler;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.graphics.camera.Renderer;
import com.xkings.core.input.EnhancedGestureDetector;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.WorldUpdater;
import com.xkings.core.main.Assets;
import com.xkings.core.main.Game2D;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.input.EnhancedGestureProcessor;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;
import com.xkings.pokemontd.system.MovementSystem;
import com.xkings.pokemontd.system.RenderSpriteSystem;

import static com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class App extends Game2D {

    private DefaultRenderer renderer;
    private SpriteBatch spriteBatch;
    private TileMap tileMap;
    private CameraHandler cameraHandler;
    private World world;
    private Clock clock;
    private WaveManager waveManager;
    private TowerManager towerManager;
    private RenderSpriteSystem renderSpriteSystem;
    private Path path;
    private Blueprint blueprint;

    @Override
    protected void renderInternal() {
        clock.run();
        renderer.render();
    }

    @Override
    protected void init(OrthographicCamera camera) {
        new Assets().addAtlas(new TextureAtlas("data/textures/packed.atlas"));
        this.clock = Clock.createInstance("Logic", true, true);
        spriteBatch = new SpriteBatch();
        renderer = new DefaultRenderer(camera);
        MapData map = createTestMap();
        tileMap = map.getTileMap();
        blueprint = map.getBlueprint();
        path = map.getPath();
        this.cameraHandler = new BoundedCameraHandler(camera, tileMap.getWidth() * tileMap.TILE_SIZE,
                tileMap.getHeight() * tileMap.TILE_SIZE, 0.0001f);
        initializeWorld();
        initializeManagers();
        initializeSystems();
        initializeInput();

    }

    private void initializeInput() {
        Gdx.input.setInputProcessor(
                new EnhancedGestureDetector(new EnhancedGestureProcessor(towerManager, cameraHandler)));
    }

    private void initializeWorld() {
        this.world = new World();
        this.clock.addService(new WorldUpdater(world));
    }

    private void initializeManagers() {
        this.waveManager = WaveManager.createInstance(world, clock, path, 5f);
        this.towerManager = new TowerManager(world, blueprint);
    }

    private void initializeSystems() {
        renderSpriteSystem = new RenderSpriteSystem(cameraHandler.getCamera());
        world.setSystem(renderSpriteSystem, true);
        world.setSystem(new MovementSystem());
        world.initialize();
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
                new AtlasRegion[][]{{grassTexture, grassTexture, pathHorizontal, grassTexture, grassTexture},
                        {grassTexture, pathRound1, pathRound3, grassTexture, grassTexture},
                        {grassTexture, pathHorizontal, grassTexture, pathRound1, pathVertical},
                        {grassTexture, pathRound0, pathVertical, pathRound3, grassTexture},
                        {grassTexture, grassTexture, grassTexture, grassTexture, grassTexture}}, 2);
        Blueprint testBlueprint = new Blueprint(booleanMap);
        Path testPath = new Path(new Vector3(0, 5, 0), new Vector3(3, 5, 0), new Vector3(3, 3, 0), new Vector3(7, 3, 0),
                new Vector3(7, 7, 0), new Vector3(5, 7, 0), new Vector3(5, 10, 0));
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
                    spriteBatch.draw(tileMap.get(i, j), i * tileMap.TILE_SIZE, j * tileMap.TILE_SIZE, tileMap.TILE_SIZE,
                            tileMap.TILE_SIZE);
                }
            }
            spriteBatch.end();
            renderSpriteSystem.process();
        }
    }
}
