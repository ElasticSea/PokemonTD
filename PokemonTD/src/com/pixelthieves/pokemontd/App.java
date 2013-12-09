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
import com.pixelthieves.core.logic.UpdateFilter;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.core.logic.WorldUpdater;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.core.main.Game2D;
import com.pixelthieves.core.services.Achievement;
import com.pixelthieves.core.services.AdService;
import com.pixelthieves.core.services.GameService;
import com.pixelthieves.core.tween.TweenManagerAdapter;
import com.pixelthieves.core.tween.Vector3Accessor;
import com.pixelthieves.pokemontd.component.ShopComponent;
import com.pixelthieves.pokemontd.graphics.CachedGaussianBlurRenderer;
import com.pixelthieves.pokemontd.graphics.GameRenderer;
import com.pixelthieves.pokemontd.graphics.GrayscaleRenderer;
import com.pixelthieves.pokemontd.graphics.tutorial.Tutorial;
import com.pixelthieves.pokemontd.graphics.ui.Ui;
import com.pixelthieves.pokemontd.graphics.ui.menu.Menu;
import com.pixelthieves.pokemontd.input.InGameInputProcessor;
import com.pixelthieves.pokemontd.manager.*;
import com.pixelthieves.pokemontd.map.MapBuilder;
import com.pixelthieves.pokemontd.map.MapData;
import com.pixelthieves.pokemontd.system.abilitySytems.damage.*;
import com.pixelthieves.pokemontd.system.abilitySytems.damage.hit.*;
import com.pixelthieves.pokemontd.system.autonomous.*;
import com.pixelthieves.pokemontd.system.resolve.*;
import com.pixelthieves.pokemontd.system.resolve.ui.*;
import com.pixelthieves.pokemontd.system.trigger.ApplyBuffSystem;
import com.pixelthieves.pokemontd.system.trigger.ApplySunbeamSystem;
import com.pixelthieves.pokemontd.system.trigger.FireProjectilSystem;
import com.pixelthieves.pokemontd.tween.CameraHandlerAccessor;
import com.pixelthieves.pokemontd.tween.ColorAccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class App extends Game2D {

    public static final Random RANDOM = new Random();
    public static final Chance CHANCE = new Chance(RANDOM);
    public static final int WORLD_SCALE = 100;
    public static final int PATH_SIZE = 2;
    public static final int INVISIBLE_INTERVAL = 5;
    public static final int INTEREST_INTERVAL = 15;
    public static final MapType defaultMapType = MapType.Winter;
    private final GameService gameService;
    private final AdService adService;
    private Renderable menuRenderer;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private SpriteBatch gameSpriteBatch;
    private ShapeRenderer gameShapeRenderer;
    private BoundedCameraHandler cameraHandler;
    private World world;
    private Clock clock;
    private WaveManager waveManager;
    private TowerManager towerManager;
    private CreepManager creepManager;
    private RenderSpriteSystem renderSpriteSystem;
    private RenderTextSystem renderTextSystem;
    private RenderHealthSystem renderHealthSystem;
    private RenderDebugSystem renderDebugSystem;
    private RenderRangeSystem renderRangeSystem;
    private Player player;
    private Ui ui;
    private InterestManager interestManager;
    private List<Updateable> filters = new ArrayList<Updateable>();
    // Tweens
    private static TweenManagerAdapter tweenManager = initTweenManager();
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
    public static Difficulty DIFFICULTY = null;
    private WorldUpdater worldUpdater;
    private boolean finishedGame;
    private Tutorial tutorial;
    private MapData map;

    public static TweenManagerAdapter getTweenManager() {
        return tweenManager;
    }

    private static TweenManagerAdapter initTweenManager() {
        TweenManagerAdapter manager = new TweenManagerAdapter();
        Tween.registerAccessor(Color.class, new ColorAccessor());
        Tween.registerAccessor(Vector3.class, new Vector3Accessor());
        Tween.registerAccessor(BoundedCameraHandler.class, new CameraHandlerAccessor());
        return manager;
    }

    public App(GameService gameService, AdService adService, String... args) {
        super(args);
        this.gameService = gameService;
        this.adService = adService;
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
        tutorial.render();
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


        gameSpriteBatch = new SpriteBatch();
        gameShapeRenderer = new ShapeRenderer();

        map = createMap(defaultMapType);
        cameraHandler = new BoundedCameraHandler(camera,
                new Rectangle(0, 0, map.getBlueprint().getWidth() * WORLD_SCALE,
                        map.getBlueprint().getHeight() * WORLD_SCALE), 0.2f);
        cameraHandler.move(0, Float.MAX_VALUE);
        initializeWorld();
        initializeContent();
        initializeManagers();
        initializeSystems();
        initializeUserInterface();
        initializeInput();
        initializeTween();
        gameRenderer = new GameRenderer(this, camera);
        if (Gdx.graphics.isGL20Available()) {
            mainMenuGaimRenderer = new CachedGaussianBlurRenderer(gameRenderer, 20);
            frozenGameRenderer = new GrayscaleRenderer(gameRenderer);
        } else {
            mainMenuGaimRenderer = gameRenderer;
            frozenGameRenderer = gameRenderer;
        }
        currentGameRenderer = mainMenuGaimRenderer;
        tutorial = new Tutorial(this, ui, cameraHandler);
        menuRenderer = new MenuRenderer();
        clock.addService(tutorial);
    }

    private void initializeUserInterface() {
        menu = new Menu(this);
        ui = new Ui(this, menu);
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
            this.waveManager = new WaveManager(this, 0.01f, 0.01f, 0f);
        } else {
            this.waveManager = new WaveManager(this, 75, 30, 120);
        }
        this.towerManager = new TowerManager(this, player);
        this.creepManager = new CreepManager(this);

        clock.addService(new UpdateFilter(new InvisibleManager(this), INVISIBLE_INTERVAL));
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
        renderSpriteSystem = new RenderSpriteSystem(cameraHandler.getCamera(), gameSpriteBatch);
        renderTextSystem = new RenderTextSystem(gameSpriteBatch);
        renderHealthSystem = new RenderHealthSystem(gameShapeRenderer);
        renderDebugSystem = new RenderDebugSystem(gameShapeRenderer);
        renderRangeSystem = new RenderRangeSystem(gameShapeRenderer, towerManager);

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
        world.setSystem(new LightUpShops(player), true);
        world.setSystem(new FindUpgradeTower(), true);
        world.setSystem(new FindAttackTower(), true);
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
        world.setSystem(new IndestructibilitySystem(this));

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

    private MapData createMap(MapType mapType) {
        switch (mapType) {
            case Summer:
                return new MapBuilder(1, 11, PATH_SIZE, MapBuilder.Direction.DOWN, 0.40f, new Rectangle(1, 2, 1, 2),
                        mapType.name().toLowerCase()).addStraight().addRight().addLeft().addStraight(
                        2).addLeft().addStraight().addLeft().addRight().addStraight().addRight().addStraight(
                        2).addRight().addStraight(3).addLeft().addStraight().addLeft().addStraight(
                        5).addLeft().addStraight(6).addLeft().addStraight(2).addRight().addStraight().build();
            case Winter:
                return new MapBuilder(1, 11, PATH_SIZE, MapBuilder.Direction.DOWN, 0.40f, new Rectangle(1, 2, 1, 2),
                        mapType.name().toLowerCase()).addStraight().addLeft().addRight().addRight().addStraight().addLeft().addStraight(
                        1).addLeft().addStraight().addLeft().addRight().addStraight().addRight().addStraight(
                        2).addRight().addStraight(3).addLeft().addStraight().addLeft().addStraight(
                        5).addLeft().addStraight(6).addLeft().addStraight(1).addRight().addStraight().build();
        }
        return null;
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
        this.DIFFICULTY = difficulty;
    }

    public Difficulty getDifficulty() {
        return DIFFICULTY;
    }

    public void activateTutorial(boolean active) {
        tutorial.setActive(active);
    }

    public CameraHandler getCameraHandler() {
        return cameraHandler;
    }

    public void leaveGame() {
        Callable action = new Callable() {
            @Override
            public Object call() throws Exception {
                Gdx.app.exit();
                return null;
            }

        };

        adService.showAd(action);


    }

    public void switchMap(MapType mapType) {
        map = createMap(mapType);
        cameraHandler.setBounds(new Rectangle(0, 0, map.getBlueprint().getWidth() * WORLD_SCALE,
                map.getBlueprint().getHeight() * WORLD_SCALE));
        cameraHandler.move(0, Float.MAX_VALUE);
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

    public ShapeRenderer getGameShapeRenderer() {
        return gameShapeRenderer;
    }

    public SpriteBatch getGameSpriteBatch() {
        return gameSpriteBatch;
    }

    public MapData getMap() {
        return map;
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
            waveManager.init(DIFFICULTY);
            if (!Configuration.PREFERENCES.getBoolean("tutorial_absolved")) {
                Configuration.PREFERENCES.putBoolean("tutorial_absolved", true);
                Configuration.PREFERENCES.flush();
                tutorial.setActive(true);
            }
        }
    }

    public void restart() {
        tutorial.setActive(false);
        player.reset();
        initializeWorld();
        initializeSystems();
     /*   initializeUserInterface();
        initializeInput();    */
        setSessionStarted(false);
        waveManager.setActive(false);
        towerManager.restartTowerStats();
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

    public static Rectangle getTowerRectangleByBlock(float blockX, float blockY) {
        return new Rectangle(blockX * App.WORLD_SCALE, blockY * App.WORLD_SCALE, App.WORLD_SCALE, App.WORLD_SCALE);
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
