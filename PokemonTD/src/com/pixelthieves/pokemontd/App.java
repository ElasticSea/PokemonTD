package com.pixelthieves.pokemontd;

import aurelienribon.tweenengine.Tween;
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
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.core.graphics.camera.BoundedCameraHandler;
import com.pixelthieves.core.graphics.camera.CameraHandler;
import com.pixelthieves.core.input.EnhancedGestureDetector;
import com.pixelthieves.core.input.GestureProcessor;
import com.pixelthieves.core.logic.Clock;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.core.logic.WorldUpdater;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.core.main.Game2D;
import com.pixelthieves.core.pathfinding.Blueprint;
import com.pixelthieves.core.tween.TweenManagerAdapter;
import com.pixelthieves.core.tween.Vector3Accessor;
import com.pixelthieves.pokemontd.component.ShopComponent;
import com.pixelthieves.pokemontd.graphics.CachedGaussianBlurRenderer;
import com.pixelthieves.pokemontd.graphics.GameRenderer;
import com.pixelthieves.pokemontd.graphics.GrayscaleRenderer;
import com.pixelthieves.pokemontd.graphics.TileMap;
import com.pixelthieves.pokemontd.graphics.ui.Ui;
import com.pixelthieves.pokemontd.graphics.ui.menu.Menu;
import com.pixelthieves.pokemontd.input.InGameInputProcessor;
import com.pixelthieves.pokemontd.manager.*;
import com.pixelthieves.pokemontd.map.MapBuilder;
import com.pixelthieves.pokemontd.map.MapData;
import com.pixelthieves.pokemontd.map.PathPack;
import com.pixelthieves.pokemontd.system.abilitySytems.damage.*;
import com.pixelthieves.pokemontd.system.abilitySytems.damage.hit.*;
import com.pixelthieves.pokemontd.system.autonomous.*;
import com.pixelthieves.pokemontd.system.resolve.*;
import com.pixelthieves.pokemontd.system.resolve.ui.GetCreep;
import com.pixelthieves.pokemontd.system.resolve.ui.GetTower;
import com.pixelthieves.pokemontd.system.trigger.ApplyBuffSystem;
import com.pixelthieves.pokemontd.system.trigger.ApplySunbeamSystem;
import com.pixelthieves.pokemontd.system.trigger.FireProjectilSystem;
import com.pixelthieves.pokemontd.tween.ColorAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App extends Game2D {

    public static final Random RANDOM = new Random();
    public static final Chance CHANCE = new Chance(RANDOM);
    public static final int WORLD_SCALE = 100;
    public static int WORLD_WIDTH;
    public static int WORLD_HEIGHT;
    public static Rectangle WORLD_RECT;
    public static final int PATH_SIZE = 2;
    public static final int INVISIBLE_INTERVAL = 5;
    public static final int INTEREST_INTERVAL = 15;
    private final GameService gameService;
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
    private InterestManager interestManager;
    private List<Updateable> filters = new ArrayList<Updateable>();
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
    private DeathSystem deathSystem;
    private GestureDetector menuInputProcessor;
    private GestureDetector uiInputProcessor;
    private GestureDetector inGameInputProcessor;
    private GestureDetector cameraMovementProcessor;
    private InputMultiplexer inputMultiplexer;
    private Difficulty difficulty = Difficulty.Easy;
    private WorldUpdater worldUpdater;
    private boolean finishedGame;

    public static TweenManagerAdapter getTweenManager() {
        return tweenManager;
    }

    private static TweenManagerAdapter initTweenManager() {
        TweenManagerAdapter manager = new TweenManagerAdapter();
        Tween.registerAccessor(Color.class, new ColorAccessor());
        Tween.registerAccessor(Vector3.class, new Vector3Accessor());
        return manager;
    }

    public App(GameService gameService, String... args) {
        super(args);
        this.gameService = gameService;
    }

    @Override
    protected void renderInternal() {
        if (sessionStarted) {
            if (!freezed) {
                clock.run();
                deathSystem.reviveDeath();
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
        initializeWorld();
        initializeContent();
        initializeManagers();
        initializeSystems();
        menu = new Menu(this);
        ui = new Ui(this, menu);
        initializeInput();
        initializeTween();
        gameRenderer = new GameRenderer(this, ui, map, camera);
        if (Gdx.graphics.isGL20Available()) {
            mainMenuGaimRenderer = new CachedGaussianBlurRenderer(gameRenderer, 20);
            frozenGameRenderer = new GrayscaleRenderer(gameRenderer);
        } else {
            mainMenuGaimRenderer = gameRenderer;
            frozenGameRenderer = gameRenderer;
        }
        menuRenderer = new MenuRenderer();
        currentGameRenderer = mainMenuGaimRenderer;


    }

    private void initializeWorld() {
        this.world = new World();
        if (worldUpdater == null) {
            this.worldUpdater = new WorldUpdater(world);
            this.clock.addService(worldUpdater);
        } else {
            this.worldUpdater.setWorld(world);
        }
    }

    private void initializeContent() {
        player = new Player(this, 50, 70);
    }

    private void initializeManagers() {
        if (STRESS_TEST != null) {
            this.waveManager = new WaveManager(this, pathPack, 0.01f, 0.01f, 0f);
        } else {
            this.waveManager = new WaveManager(this, pathPack, 75, 30, 120);
        }
        this.towerManager = new TowerManager(this, blueprint, player);
        this.creepManager = new CreepManager(this);
        this.invisibleManager = new InvisibleManager(this, clock, INVISIBLE_INTERVAL);
        this.interestManager = new InterestManager(this, player.getTreasure(), 2, INTEREST_INTERVAL);

        filters.add(waveManager.getFilter());
        filters.add(interestManager.getFilter());
        for (Updateable updateable : filters) {
            clock.addService(updateable);
        }

        // DISCUS: If you skip all waves before first wave reaches the end you can earn about 220k score solely on interest. That is the reason why we are removing interest from updateable filters.
        filters.remove(interestManager.getFilter());
    }

    private void initializeSystems() {
        deathSystem = new DeathSystem(gameService, player);
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
        world.setSystem(new FireTextInfo(ShopComponent.class), true);

        world.setSystem(new DamageOverPolySystem(), true);
        world.setSystem(new TargetingSystem());
        world.setSystem(new WaveSystem(player));
        world.setSystem(new FireProjectilSystem());
        world.setSystem(new TempLifeSystem());
        world.setSystem(deathSystem);
        world.setSystem(new HealingSystem());
        world.setSystem(new DotSystem());
        world.setSystem(new SlowSystem());
        world.setSystem(new IncreasingDamageSystem());
        world.setSystem(new ChangeDirectionSystem());
        world.setSystem(new DamageBuffSystem());
        world.setSystem(new SpeedBuffSystem());
        world.setSystem(new MovementSystem());
        world.setSystem(new IndestructibilitySystem(gameBlueprint));

        world.setSystem(new HitTempLifeSystem());
        world.setSystem(new HitDotSystem());
        world.setSystem(new HitSlowSystem());
        world.setSystem(new HitChangeDirection());
        world.setSystem(new HitNormalSystem());
        world.setSystem(new HitMoneySystem());
        world.setSystem(new HitLifeStealSystem());
        world.setSystem(new HitIncreasingDamageSystem());
        world.setSystem(new HitBonusSystem());
        world.setSystem(new ApplyBuffSystem());
        world.setSystem(new ApplySunbeamSystem());

        world.setSystem(new BubbleSystem(world));
        world.initialize();
    }

    private void initializeInput() {
        menuInputProcessor = new GestureDetector(menu);
        uiInputProcessor = new GestureDetector(ui);
        inGameInputProcessor = new GestureDetector(new InGameInputProcessor(towerManager, creepManager, cameraHandler));
        cameraMovementProcessor = new EnhancedGestureDetector(new GestureProcessor(cameraHandler));
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(menuInputProcessor);
        inputMultiplexer.addProcessor(uiInputProcessor);
        inputMultiplexer.addProcessor(inGameInputProcessor);
        inputMultiplexer.addProcessor(cameraMovementProcessor);
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

    public void updateFilters(float delta) {
        for (Updateable updateable : filters) {
            updateable.update(delta);
        }
    }

    public void makeGuiLarger() {
        ui.makeLarger();
    }

    public void makeGuiSmaller() {
        ui.makeSmaller();

    }

    public void resetGuiSize() {
        ui.resetSize();
    }

    public World getWorld() {
        return world;
    }

    public void endGame(boolean survived) {
        finishedGame = true;
        freeze(true);
        menu.switchCard(Menu.Type.END);
        if (survived) {
            if (player.getHealth() >= 100) {
                gameService.submitAchievement(Achievement.Healty);
            }
            if (player.getHealth() >= 50) {
                gameService.submitAchievement(Achievement.Champion);
            }
            if (player.getTreasure().getGold() >= 200000) {
                gameService.submitAchievement(Achievement.Trifty);
            }
            if (towerManager.getSoldTowers() == 0) {
                gameService.submitAchievement(Achievement.Keeper);
            }
        }
    }

    public GameService getGameSevice() {
        return gameService;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

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

    public InterestManager getInterestManager() {
        return interestManager;
    }

    public Clock getClock() {
        return clock;
    }

    public void triggerMenu(Menu.Type type) {
        menu.switchCard(type);
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
        inputMultiplexer.clear();
        if (freezed) {
            currentGameRenderer = frozenGameRenderer;
            inputMultiplexer.addProcessor(menuInputProcessor);
            inputMultiplexer.addProcessor(cameraMovementProcessor);
            clock.setSpeedMultiplier(1);
        } else {
            currentGameRenderer = gameRenderer;
            inputMultiplexer.addProcessor(menuInputProcessor);
            inputMultiplexer.addProcessor(uiInputProcessor);
            inputMultiplexer.addProcessor(inGameInputProcessor);
            inputMultiplexer.addProcessor(cameraMovementProcessor);
        }
    }

    public boolean isFreezed() {
        return freezed;
    }

    public boolean isSessionStarted() {
        return sessionStarted;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setSessionStarted(boolean sessionStarted) {
        this.sessionStarted = sessionStarted;
        currentGameRenderer = sessionStarted ? gameRenderer : mainMenuGaimRenderer;
        if (sessionStarted && !waveManager.isActive()) {
            waveManager.init(difficulty);
            if(!Configuration.PREFERENCES.getBoolean("tutorial_absolved")){
                menu.switchCard(Menu.Type.QUICK_TIP);
                System.out.println("LOOOL");
            }
        }
    }

    public void restart() {
        player.reset();
        initializeWorld();
        initializeSystems();
        setSessionStarted(false);
        waveManager.setActive(false);
        interestManager.getFilter().reset();
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

    public static Vector3 getBlockPositionOptimized(float worldX, float worldY, Vector3 store) {
        int blockX = (int) (worldX / App.WORLD_SCALE);
        int blockY = (int) (worldY / App.WORLD_SCALE);
        return store.set(blockX, blockY, 0);
    }

    public Ui getUi() {
        return ui;
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }
}
