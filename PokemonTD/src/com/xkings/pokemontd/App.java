package com.xkings.pokemontd;

import aurelienribon.tweenengine.Tween;
import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.graphics.camera.BoundedCameraHandler;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.input.EnhancedGestureDetector;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.WorldUpdater;
import com.xkings.core.main.Assets;
import com.xkings.core.main.Game2D;
import com.xkings.core.pathfinding.Blueprint;
import com.xkings.core.tween.TweenManagerAdapter;
import com.xkings.core.tween.Vector3Accessor;
import com.xkings.pokemontd.graphics.AggresiveGaussianBlurRenderer;
import com.xkings.pokemontd.graphics.GameRenderer;
import com.xkings.pokemontd.graphics.GrayscaleRenderer;
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.graphics.ui.Menu;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.input.InGameInputProcessor;
import com.xkings.pokemontd.manager.*;
import com.xkings.pokemontd.map.MapBuilder;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.PathPack;
import com.xkings.pokemontd.system.abilitySytems.damage.*;
import com.xkings.pokemontd.system.abilitySytems.damage.hit.*;
import com.xkings.pokemontd.system.autonomous.*;
import com.xkings.pokemontd.system.resolve.*;
import com.xkings.pokemontd.system.resolve.ui.GetCreep;
import com.xkings.pokemontd.system.resolve.ui.GetTower;
import com.xkings.pokemontd.system.trigger.ApplyBuffSystem;
import com.xkings.pokemontd.system.trigger.ApplySunbeamSystem;
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
    private Renderable menuRenderer;
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
    private RenderRangeSystem renderRangeSystem;
    private PathPack pathPack;
    private Blueprint blueprint;
    private Player player;
    private Ui ui;
    private Interest interest;
    // Tweens
    private static TweenManagerAdapter tweenManager = initTweenManager();
    private Blueprint gameBlueprint;
    private Menu menu;
    private boolean freezed = false;
    private boolean sessionStarted = false;
    private Renderable gameRenderer;
    private Renderable mainMenuGaimRenderer;
    private Renderable currentGameRenderer;
    private Renderable frozenGameRenderer;

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
        if (sessionStarted) {
            if (!freezed) {
                clock.run();
            }
        }
        currentGameRenderer.render();
        menuRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        cameraHandler.update();
    }

    @Override
    protected void init(OrthographicCamera camera) {
        this.clock = Clock.createInstance("Logic", true, true);
        new Assets().addAtlas(new TextureAtlas("data/textures/packed.atlas"));
        initializeWorld();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        MapData map = createMap();
        tileMap = map.getTileMap();
        blueprint = map.getBlueprint();
        gameBlueprint = map.getGameBlueprint();
        pathPack = map.getPathPack();
        WORLD_WIDTH = blueprint.getWidth();
        WORLD_HEIGHT = blueprint.getHeight();
        WORLD_RECT = new Rectangle(0, 0, WORLD_WIDTH * WORLD_SCALE, WORLD_HEIGHT * WORLD_SCALE);
        cameraHandler = new BoundedCameraHandler(camera, WORLD_RECT, 0.2f);
        cameraHandler.move(0, Float.MAX_VALUE);
        initializeContent();
        initializeManagers();
        initializeSystems();
        ui = new Ui(this);
        menu = new Menu(this);
        initializeInput();
        initializeTween();
        gameRenderer = new GameRenderer(this, ui, map, world, camera);
        mainMenuGaimRenderer = new AggresiveGaussianBlurRenderer(gameRenderer, 12);
        frozenGameRenderer = new GrayscaleRenderer(gameRenderer);
        currentGameRenderer = mainMenuGaimRenderer;
        menuRenderer = new MenuRenderer();
    }

    private void initializeWorld() {
        this.world = new World();
        // FIXME nasty hack
        this.pathBlock = world.createEntity();
        this.clock.addService(new WorldUpdater(world));
    }

    private void initializeContent() {
        player = new Player(50, 15000, 10);
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(world, clock, pathPack, STRESS_TEST != null ? 0.01f : WAVE_INTERVAL);
        this.towerManager = new TowerManager(world, blueprint, player);
        this.creepManager = new CreepManager(world);
        this.invisibleManager = new InvisibleManager(world, clock, INVISIBLE_INTERVAL);
        this.interest = new Interest(clock, world, player.getTreasure(), towerManager, 2, INTEREST_INTERVAL);
    }

    private void initializeSystems() {
        renderSpriteSystem = new RenderSpriteSystem(cameraHandler.getCamera(), spriteBatch);
        renderTextSystem = new RenderTextSystem(spriteBatch);
        renderHealthSystem = new RenderHealthSystem(shapeRenderer);
        renderDebugSystem = new RenderDebugSystem(shapeRenderer);
        renderRangeSystem = new RenderRangeSystem(shapeRenderer, towerManager);

        world.setSystem(renderSpriteSystem, true);
        world.setSystem(renderTextSystem, true);
        world.setSystem(renderHealthSystem, true);
        if (DEBUG != null) {
            world.setSystem(renderDebugSystem, true);
        }
        world.setSystem(renderRangeSystem, true);
        world.setSystem(new ClosestCreepSystem(), true);
        world.setSystem(new ClosestSystemTower(), true);
        world.setSystem(new ClosestSystemTowerWithoutDamageBuff(), true);
        world.setSystem(new ClosestSystemTowerWithoutSpeedBuff(), true);
        world.setSystem(new FirstCreepSystem(), true);
        world.setSystem(new InvisibleSystem(), true);
        world.setSystem(new GetTower(), true);
        world.setSystem(new GetCreep(), true);
        world.setSystem(new FindShop(), true);

        world.setSystem(new DamageOverPolySystem(), true);
        world.setSystem(new TargetingSystem());
        world.setSystem(new WaveSystem(player));
        world.setSystem(new FireProjectilSystem());
        world.setSystem(new LifeStealSystem());
        world.setSystem(new DeathSystem(player));
        world.setSystem(new HealingSystem());
        world.setSystem(new DotSystem());
        world.setSystem(new SlowSystem());
        world.setSystem(new IncreasingDamageSystem());
        world.setSystem(new ChangeDirectionSystem());
        world.setSystem(new DamageBuffSystem());
        world.setSystem(new SpeedBuffSystem());
        world.setSystem(new MovementSystem());
        world.setSystem(new IndestructibilitySystem(gameBlueprint));

        world.setSystem(new HitLifeStealSystem());
        world.setSystem(new HitDotSystem());
        world.setSystem(new HitSlowSystem());
        world.setSystem(new HitChangeDirection());
        world.setSystem(new HitNormalSystem());
        world.setSystem(new HitMoneySystem());
        world.setSystem(new HitLifeSystem(player.getHealth()));
        world.setSystem(new HitIncreasingDamageSystem());
        world.setSystem(new HitBonusSystem());
        world.setSystem(new ApplyBuffSystem());
        world.setSystem(new ApplySunbeamSystem());

        world.setSystem(new BubbleSystem(world));
        world.initialize();
    }

    private void initializeInput() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(menu));
        inputMultiplexer.addProcessor(new GestureDetector(ui));
        inputMultiplexer.addProcessor(
                new EnhancedGestureDetector(new InGameInputProcessor(towerManager, creepManager, cameraHandler)));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void initializeTween() {
        clock.addService(tweenManager);
    }

    private MapData createMap() {
        return new MapBuilder(1, 11, PATH_SIZE, MapBuilder.Direction.DOWN, 0.40f,
                new Rectangle(1, 2, 1, 2)).addStraight().addRight().addLeft().addStraight(
                2).addLeft().addStraight().addLeft().addRight().addStraight().addRight().addStraight(
                2).addRight().addStraight(3).addLeft().addStraight().addLeft().addStraight(5).addLeft().addStraight(
                6).addLeft().addStraight(2).addRight().addStraight().build();
    }

    /*
private MapData createMap() {
return new MapBuilder(3, 11, PATH_SIZE, MapBuilder.Direction.DOWN, 0.40f,
 new Rectangle(1, 2, 1, 2)).addStraight().addRight().addStraight().addLeft().addStraight(
 2).addLeft().addStraight().addLeft().addRight().addStraight().addRight().addStraight(
 2).addRight().addStraight(3).addLeft().addStraight().addLeft().addStraight(5).addLeft().addStraight(
 6).addLeft().addStraight().addRight().addStraight().build();
}
     */
    private class MenuRenderer implements Renderable {


        @Override
        public void render() {
            menu.render();
        }


    }

    @Override
    public void dispose() {

    }

    public Player getPlayer() {
        return player;
    }

    public CreepManager getCreepManager() {
        return creepManager;
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public Interest getInterest() {
        return interest;
    }

    public Clock getClock() {
        return clock;
    }

    public void triggerMenu(Menu.Type type) {
        menu.triggerMenu(type);
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public void freeze(boolean freezed) {
        this.freezed = freezed;
        currentGameRenderer = freezed ? frozenGameRenderer : gameRenderer;
    }

    public boolean isFreezed() {
        return freezed;
    }

    public boolean isSessionStarted() {
        return sessionStarted;
    }

    public void setSessionStarted(boolean sessionStarted) {
        this.sessionStarted = sessionStarted;
        currentGameRenderer = sessionStarted ? gameRenderer : mainMenuGaimRenderer;
    }


    public static Vector3 getTowerPosition(float worldX, float worldY) {
        Vector3 block = getBlockPosition(worldX, worldY);
        return getTowerPositionByBlock(block.x, block.y);
    }

    public static Vector3 getTowerPositionByBlock(float blockX, float blockY) {
        float towerX = (blockX + .5f) * App.WORLD_SCALE;
        float towerY = (blockY + .5f) * App.WORLD_SCALE;
        return new Vector3(towerX, towerY, 0);
    }

    public static Vector3 getBlockPosition(float worldX, float worldY) {
        int blockX = (int) (worldX / App.WORLD_SCALE);
        int blockY = (int) (worldY / App.WORLD_SCALE);
        return new Vector3(blockX, blockY, 0);
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
