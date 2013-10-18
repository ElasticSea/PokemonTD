package com.xkings.pokemontd;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.graphics.Shader;
import com.xkings.core.graphics.camera.BoundedCameraHandler;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.input.EnhancedGestureDetector;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.WorldUpdater;
import com.xkings.core.main.Game2D;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.input.InGameInputProcessor;
import com.xkings.pokemontd.manager.CreepManager;
import com.xkings.pokemontd.manager.ProjectileManager;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;
import com.xkings.pokemontd.map.MapBuilder;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;
import com.xkings.pokemontd.map.PathPack;
import com.xkings.pokemontd.system.ClosestEnemySystem;
import com.xkings.pokemontd.system.GetCreep;
import com.xkings.pokemontd.system.GetTower;
import com.xkings.pokemontd.system.abilitySytems.projectile.AoeSystem;
import com.xkings.pokemontd.system.abilitySytems.projectile.FireProjectilSystem;
import com.xkings.pokemontd.system.abilitySytems.projectile.HitProjectileSystem;
import com.xkings.pokemontd.system.autonomous.*;

import java.util.Random;

public class App extends Game2D {

    public static final Random RANDOM = new Random();
    public static final int WORLD_SCALE = 2;
    public static final int WORLD_WIDTH = 20;
    public static final int WORLD_HEIGHT = 24;
    public static final Rectangle WORLD_RECT =
            new Rectangle(0, 0, WORLD_WIDTH * WORLD_SCALE, WORLD_HEIGHT * WORLD_SCALE);
    public static final float WAVE_INTERVAL = 5f;
    public static final int PATH_SIZE = 2;
    public static Entity pathBlock;
    private DefaultRenderer renderer;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private TileMap tileMap;
    private CameraHandler cameraHandler;
    private World world;
    private Clock clock;
    private WaveManager waveManager;
    private TowerManager towerManager;
    private CreepManager creepManager;
    private RenderSpriteSystem renderSpriteSystem;
    private RenderTextSystem renderTextSystem;
    private RenderHealthSystem renderHealthSystem;
    private RenderDebugSystem renderDebugSystem;
    private ClosestEnemySystem closestEnemySystem;
    private AoeSystem aoeSystem;
    private GetTower getTowerSystem;
    private GetCreep getCreepSystem;
    private PathPack pathPack;
    private GenericBlueprint blueprint;
    private Player player;
    private static PokemonAssets assets;
    private ProjectileManager projectileManager;
    private Ui ui;

    public App(String[] args) {
        super(args);
    }

    @Override
    protected void renderInternal() {
        clock.run();
        renderer.render();
    }

    @Override
    protected void init(OrthographicCamera camera) {
        this.clock = Clock.createInstance("Logic", true, true);
        initializeWorld();
        assets = new PokemonAssets();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        MapData map = createTestMap();
        tileMap = map.getTileMap();
        blueprint = map.getBlueprint();
        pathPack = map.getPathPack();
        cameraHandler = new BoundedCameraHandler(camera, WORLD_RECT, 0.001f);

        initializeContent();
        initializeManagers();
        initializeSystems();
        ui = new Ui(player, waveManager, towerManager);
        renderer = new DefaultRenderer(ui, camera);
        initializeInput();
    }

    private void initializeInput() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(ui));
        inputMultiplexer.addProcessor(new EnhancedGestureDetector(
                new InGameInputProcessor(getCreepSystem, towerManager, creepManager, cameraHandler)));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void initializeWorld() {
        this.world = new World();
        // FIXME nasty hack
        this.pathBlock = world.createEntity();
        this.clock.addService(new WorldUpdater(world));
    }

    private void initializeContent() {
        player = new Player(9999, 9999, 0, 0, 0, 0, 0, 0, 0);
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(world, clock, pathPack, WAVE_INTERVAL);
        this.towerManager = new TowerManager(world, blueprint, player);
        this.creepManager = new CreepManager(world);
        this.projectileManager = new ProjectileManager(world, blueprint);
    }

    private void initializeSystems() {
        renderSpriteSystem = new RenderSpriteSystem(cameraHandler.getCamera());
        renderTextSystem = new RenderTextSystem(cameraHandler.getCamera());
        renderHealthSystem = new RenderHealthSystem(cameraHandler.getCamera());
        renderDebugSystem = new RenderDebugSystem(cameraHandler);
        closestEnemySystem = new ClosestEnemySystem(WaveComponent.class);
        aoeSystem = new AoeSystem();
        getTowerSystem = new GetTower();
        getCreepSystem = new GetCreep();

        world.setSystem(renderSpriteSystem, true);
        world.setSystem(renderTextSystem, true);
        world.setSystem(renderHealthSystem, true);
        world.setSystem(renderDebugSystem, true);
        world.setSystem(closestEnemySystem, true);
        world.setSystem(getTowerSystem, true);
        world.setSystem(getCreepSystem, true);
        world.setSystem(aoeSystem, true);
        world.setSystem(new MovementSystem());
        world.setSystem(new WaveSystem(player));
        world.setSystem(new FireProjectilSystem(closestEnemySystem, projectileManager));
        world.setSystem(new HitProjectileSystem(aoeSystem));
        world.setSystem(new DeathSystem(player));
        world.initialize();
    }


    private MapData createTestMap() {
        return new MapBuilder(WORLD_WIDTH / PATH_SIZE, WORLD_HEIGHT / PATH_SIZE, 3, 11, PATH_SIZE,
                MapBuilder.Direction.DOWN, 0.40f).addStraight().addRight().addStraight().addLeft().addStraight(
                2).addLeft().addStraight().addLeft().addRight().addStraight().addRight().addStraight(
                2).addRight().addStraight(3).addLeft().addStraight().addLeft().addStraight(5).addLeft().addStraight(
                6).addLeft().addStraight().addRight().addStraight().build();
    }

    @Override
    public void dispose() {

    }


    private class DefaultRenderer implements Renderable {

        private final SpriteBatch onScreenRasterRender;
        private final Renderable renderer;
        private final Camera camera;

        protected DefaultRenderer(Ui Ui, Camera camera) {
            this.camera = camera;
            onScreenRasterRender = new SpriteBatch();
            onScreenRasterRender.setShader(Shader.getShader("smoothText"));
            onScreenRasterRender.enableBlending();
            renderer = Ui;
        }

        @Override
        public void render() {
            drawMap();
            drawPath();
            drawGrid();
            renderSpriteSystem.process();
            renderTextSystem.process();
            renderHealthSystem.process();
            renderDebugSystem.process();
            renderer.render();
        }

        private void drawGrid() {
            if (DEBUG != null) {
                shapeRenderer.setProjectionMatrix(camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                int height = tileMap.getHeight() * tileMap.TILE_SIZE;
                int width = tileMap.getWidth() * tileMap.TILE_SIZE;
                for (int i = 0; i < height; i++) {
                    shapeRenderer.line(0, i * WORLD_SCALE, width * WORLD_SCALE, i * WORLD_SCALE);
                }
                for (int i = 0; i < width; i++) {
                    shapeRenderer.line(i * WORLD_SCALE, 0, i * WORLD_SCALE, height * WORLD_SCALE);
                }
                shapeRenderer.end();
            }
        }

        private void drawMap() {
            spriteBatch.setProjectionMatrix(camera.combined);
            spriteBatch.begin();
            for (int i = 0; i < tileMap.getWidth(); i++) {
                for (int j = 0; j < tileMap.getHeight(); j++) {
                    int size = tileMap.TILE_SIZE * WORLD_SCALE;
                    spriteBatch.draw(tileMap.get(i, j), i * size, j * size, size, size);
                }
            }
            spriteBatch.end();
        }

        private void drawPath() {
            if (DEBUG != null) {
                shapeRenderer.setProjectionMatrix(camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                for (Path path : pathPack.getPaths()) {
                    Vector3 lastPoint = null;
                    for (Vector3 point : path.getPath()) {
                        if (lastPoint != null) {
                            shapeRenderer.line(lastPoint.x, lastPoint.y, point.x, point.y);
                        }
                        lastPoint = point;
                    }
                }
                shapeRenderer.end();
            }
        }


    }

    public static PokemonAssets getAssets() {
        return assets;
    }
}
