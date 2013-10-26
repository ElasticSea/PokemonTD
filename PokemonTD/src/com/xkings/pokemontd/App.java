package com.xkings.pokemontd;

import aurelienribon.tweenengine.Tween;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.core.tween.TweenManagerAdapter;
import com.xkings.core.tween.Vector3Accessor;
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.input.InGameInputProcessor;
import com.xkings.pokemontd.manager.*;
import com.xkings.pokemontd.map.MapBuilder;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;
import com.xkings.pokemontd.map.PathPack;
import com.xkings.pokemontd.system.*;
import com.xkings.pokemontd.system.abilitySytems.projectile.*;
import com.xkings.pokemontd.system.abilitySytems.projectile.hit.*;
import com.xkings.pokemontd.system.autonomous.*;
import com.xkings.pokemontd.system.trigger.ApplyBuffSystem;
import com.xkings.pokemontd.system.trigger.FireProjectilSystem;
import com.xkings.pokemontd.tween.ColorAccessor;

import java.util.Random;

public class App extends Game2D {

    public static final Random RANDOM = new Random();
    public static final Chance CHANCE = new Chance(RANDOM);
    public static final int WORLD_SCALE = 100;
    public static int WORLD_WIDTH;
    public static int WORLD_HEIGHT;
    public static Rectangle WORLD_RECT;
    public static final float WAVE_INTERVAL = 75f;
    public static final int PATH_SIZE = 2;
    public static final int INVISIBLE_INTERVAL = 5;
    public static final int INTEREST_INTERVAL = 15;
    public static Entity pathBlock;
    private DefaultRenderer renderer;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private TileMap<TextureAtlas.AtlasRegion> tileMap;
    private CameraHandler cameraHandler;
    private World world;
    private Clock clock;
    private WaveManager waveManager;
    private TowerManager towerManager;
    private CreepManager creepManager;
    private InvisibleManager invisibleManager;
    private RenderSpriteSystem renderSpriteSystem;
    private RenderTextSystem renderTextSystem;
    private RenderHealthSystem renderHealthSystem;
    private RenderDebugSystem renderDebugSystem;
    private PathPack pathPack;
    private Blueprint blueprint;
    private Player player;
    private static PokemonAssets assets;
    private Ui ui;
    private Interest interest;
    // Tweens
    private static TweenManagerAdapter tweenManager = initTweenManager();

    public static TweenManagerAdapter getTweenManager() {
        return tweenManager;
    }

    private static TweenManagerAdapter initTweenManager() {
        TweenManagerAdapter manager = new TweenManagerAdapter();
        Tween.registerAccessor(Color.class, new ColorAccessor());
        Tween.registerAccessor(Vector3.class, new Vector3Accessor());
        return manager;
    }

    public App(String... args) {
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

        MapData map = createMap();
        tileMap = map.getTileMap();
        blueprint = map.getBlueprint();
        pathPack = map.getPathPack();
        WORLD_WIDTH = blueprint.getWidth();
        WORLD_HEIGHT = blueprint.getHeight();
        WORLD_RECT = new Rectangle(0, 0, WORLD_WIDTH * WORLD_SCALE, WORLD_HEIGHT * WORLD_SCALE);
        cameraHandler = new BoundedCameraHandler(camera, WORLD_RECT, 0.2f);

        initializeContent();
        initializeManagers();
        initializeSystems();
        ui = new Ui(player, waveManager, creepManager, towerManager, 1, interest);
        renderer = new DefaultRenderer(ui, camera);
        initializeInput();
        initializeTween();
    }

    private void initializeWorld() {
        this.world = new World();
        // FIXME nasty hack
        this.pathBlock = world.createEntity();
        this.clock.addService(new WorldUpdater(world));
    }

    private void initializeContent() {
        player = new Player(9999, 99990, 0, 0, 0, 0, 0, 0, 0);
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(world, clock, pathPack, WAVE_INTERVAL);
        this.towerManager = new TowerManager(world, blueprint, player);
        this.creepManager = new CreepManager(world);
        this.invisibleManager = new InvisibleManager(world, clock, INVISIBLE_INTERVAL);
        this.interest = new Interest(clock, world, player.getTreasure(), towerManager, 2, INTEREST_INTERVAL);
    }

    private void initializeSystems() {
        renderSpriteSystem = new RenderSpriteSystem(cameraHandler.getCamera());
        renderTextSystem = new RenderTextSystem(cameraHandler.getCamera());
        renderHealthSystem = new RenderHealthSystem(cameraHandler.getCamera());
        renderDebugSystem = new RenderDebugSystem(cameraHandler, towerManager);

        world.setSystem(renderSpriteSystem, true);
        world.setSystem(renderTextSystem, true);
        world.setSystem(renderHealthSystem, true);
        world.setSystem(renderDebugSystem, true);
        world.setSystem(new ClosestCreepSystem(), true);
        world.setSystem(new ClosestTowerSystem(), true);
        world.setSystem(new ClosestTowerWithoutDamageBuffSystem(), true);
        world.setSystem(new AoeSystem(), true);
        world.setSystem(new InvisibleSystem(), true);
        world.setSystem(new GetTower(), true);
        world.setSystem(new GetCreep(), true);
        world.setSystem(new FindShop(), true);
        world.setSystem(new MovementSystem());
        world.setSystem(new WaveSystem(player));
        world.setSystem(new FireProjectilSystem());
        world.setSystem(new LifeStealSystem());
        world.setSystem(new DeathSystem(player));
        world.setSystem(new HealingSystem());
        world.setSystem(new DotSystem());
        world.setSystem(new SlowSystem());
        world.setSystem(new BuffSystem());

        world.setSystem(new HitLifeStealSystem());
        world.setSystem(new HitDotSystem());
        world.setSystem(new HitNormalSystem());
        world.setSystem(new HitAoeSystem());
        world.setSystem(new HitSlowSystem());
        world.setSystem(new HitBonusSystem());
        world.setSystem(new ApplyBuffSystem());

        world.setSystem(new BubbleSystem(world));
        world.initialize();
    }

    private void initializeInput() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(ui));
        inputMultiplexer.addProcessor(
                new EnhancedGestureDetector(new InGameInputProcessor(towerManager, creepManager, cameraHandler)));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void initializeTween() {
        clock.addService(tweenManager);
    }


    private MapData createMap() {
        return new MapBuilder(3, 11, PATH_SIZE, MapBuilder.Direction.DOWN, 0.40f,
                new Rectangle(1, 1, 1, 2)).addStraight().addRight().addStraight().addLeft().addStraight(
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
            drawMap(0);
            drawMap(1);
            drawPath();
            drawGrid();
            renderSpriteSystem.process();
            renderTextSystem.process();
            renderHealthSystem.process();
            renderDebugSystem.process();
            drawMap(2);
            drawMap(4);
            drawMap(3);
            renderer.render();
        }

        private void drawGrid() {
            if (DEBUG != null) {
                shapeRenderer.setProjectionMatrix(camera.combined);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                int height = tileMap.getHeight(0) * tileMap.getTileSize(0);
                int width = tileMap.getWidth(0) * tileMap.getTileSize(0);
                for (int i = 0; i < height; i++) {
                    shapeRenderer.line(0, i * WORLD_SCALE, width * WORLD_SCALE, i * WORLD_SCALE);
                }
                for (int i = 0; i < width; i++) {
                    shapeRenderer.line(i * WORLD_SCALE, 0, i * WORLD_SCALE, height * WORLD_SCALE);
                }
                shapeRenderer.end();
            }
        }

        private void drawMap(int level) {
            spriteBatch.setProjectionMatrix(camera.combined);
            spriteBatch.begin();
            for (int j = 0; j < tileMap.getWidth(level); j++) {
                for (int k = 0; k < tileMap.getHeight(level); k++) {
                    int size = tileMap.getTileSize(level) * WORLD_SCALE;
                    TextureAtlas.AtlasRegion atlasRegion = tileMap.get(j, k, level);
                    if (atlasRegion != null) {
                        spriteBatch.draw(atlasRegion, j * size, k * size, size, size);
                    }
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
