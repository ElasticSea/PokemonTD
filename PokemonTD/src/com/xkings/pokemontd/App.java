package com.xkings.pokemontd;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.xkings.core.graphics.Renderable;
import com.xkings.core.graphics.ScreenText;
import com.xkings.core.graphics.Shader;
import com.xkings.core.graphics.camera.BoundedCameraHandler;
import com.xkings.core.graphics.camera.CameraHandler;
import com.xkings.core.input.EnhancedGestureDetector;
import com.xkings.core.logic.Clock;
import com.xkings.core.logic.WorldUpdater;
import com.xkings.core.main.Game2D;
import com.xkings.core.pathfinding.GenericBlueprint;
import com.xkings.pokemontd.component.WaveComponent;
import com.xkings.pokemontd.entity.Player;
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.input.EnhancedGestureProcessor;
import com.xkings.pokemontd.manager.ProjectileManager;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;
import com.xkings.pokemontd.system.*;

public class App extends Game2D {


    public static final float WAVE_INTERVAL = 5f;
    public static Entity pathBlock;
    private DefaultRenderer renderer;
    private SpriteBatch spriteBatch;
    private TileMap tileMap;
    private CameraHandler cameraHandler;
    private World world;
    private Clock clock;
    private WaveManager waveManager;
    private TowerManager towerManager;
    private RenderSpriteSystem renderSpriteSystem;
    private RenderDebugSystem renderDebugSystem;
    private ClosestEnemySystem closestEnemySystem;
    private GetTowerInfoSystem getTowerInfoSystem;
    private Path path;
    private GenericBlueprint blueprint;
    private Player player;
    private static PokemonAssets assets;
    private ProjectileManager projectileManager;
    private Ui ui;

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


        MapData map = buildMap();
        tileMap = map.getTileMap();
        blueprint = map.getBlueprint();
        path = map.getPath();
        this.cameraHandler = new BoundedCameraHandler(camera, tileMap.getWidth() * tileMap.TILE_SIZE,
                tileMap.getHeight() * tileMap.TILE_SIZE, 0.001f);

        initializeContent();
        initializeManagers();
        initializeSystems();
        ui = new Ui(player, waveManager,towerManager, camera);
        renderer = new DefaultRenderer(ui, camera);
        initializeInput();
    }

    private void initializeInput() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(new GestureDetector(ui));
        inputMultiplexer.addProcessor(
                new EnhancedGestureDetector(new EnhancedGestureProcessor(towerManager, cameraHandler)));
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void initializeWorld() {
        this.world = new World();
        pathBlock = world.createEntity();
        this.clock.addService(new WorldUpdater(world));
    }

    private void initializeContent() {
        player = new Player(9999, 9999, 1, 2, 3, 0, 2, 1, 1);
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(world, clock, path, WAVE_INTERVAL);
        this.towerManager = new TowerManager(world, blueprint, player);
        this.projectileManager = new ProjectileManager(world, blueprint);
    }

    private void initializeSystems() {
        renderSpriteSystem = new RenderSpriteSystem(cameraHandler.getCamera());
        renderDebugSystem = new RenderDebugSystem(cameraHandler);
        closestEnemySystem = new ClosestEnemySystem(WaveComponent.class);

        world.setSystem(closestEnemySystem, true);
        world.setSystem(renderSpriteSystem, true);
        world.setSystem(renderDebugSystem, true);
        world.setSystem(new MovementSystem());
        world.setSystem(new WaveSystem(player));
        world.setSystem(new FireProjectilSystem(closestEnemySystem, projectileManager));
        world.setSystem(new HitProjectileSystem());
        world.setSystem(new DeathSystem(player));
        world.initialize();
    }

/*
    private MapData createTestMap() {
        // FIXME nasty hack
        Entity pathBlock = world.createEntity();
        Entity[][] entityMap = new Entity[][]{
                {null, null, null, null, pathBlock, pathBlock, null, null, null, null},
                {null, null, null, null, pathBlock, pathBlock, null, null, null, null},
                {null, null, pathBlock, pathBlock, pathBlock, pathBlock, null, null, null, null},
                {null, null, pathBlock, pathBlock, pathBlock, pathBlock, null, null, null, null},
                {null, null, pathBlock, pathBlock, null, null, pathBlock, pathBlock, pathBlock, pathBlock},
                {null, null, pathBlock, pathBlock, null, null, pathBlock, pathBlock, pathBlock, pathBlock},
                {null, null, pathBlock, pathBlock, pathBlock, pathBlock, pathBlock, pathBlock, null, null},
                {null, null, pathBlock, pathBlock, pathBlock, pathBlock, pathBlock, pathBlock, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}};
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
        GenericBlueprint<Entity> testBlueprint = new GenericBlueprint<Entity>(entityMap);
        Path testPath = new Path(new Vector3(0, 5, 0), new Vector3(3, 5, 0), new Vector3(3, 3, 0), new Vector3(7, 3, 0),
                new Vector3(7, 7, 0), new Vector3(5, 7, 0), new Vector3(5, 10, 0));
        return new MapData(testBlueprint, testPath, tileMap);
    }
*/

    public MapData buildMap() {
        return new MapBuilder(15, 15, 0, 8, MapBuilder.Direction.RIGHT).addStraight().addLeft().addRight()
                .addStraight().addRight().build();
    }

    @Override
    public void dispose() {

    }


    private class DefaultRenderer implements Renderable {

        private final ScreenText lifes;
        private final SpriteBatch onScreenRasterRender;
        private final Renderable renderer;
        private final Camera camera;

        protected DefaultRenderer(Ui Ui, Camera camera) {
            this.camera = camera;
            lifes = new ScreenText(assets.getSmoothFont(), BitmapFont.HAlignment.RIGHT);
            onScreenRasterRender = new SpriteBatch();
            onScreenRasterRender.setShader(Shader.getShader("smoothText"));
            onScreenRasterRender.enableBlending();
            renderer = Ui;
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
            renderDebugSystem.process();

            renderer.render();

         /*   lifes.addInfo("Lifes: " + player.getHealth().getLives());
            lifes.addInfo("Gold: " + player.getTreasure().getGold());
            lifes.addInfo("Next Wave in: " + waveManager.getRemainingTime());     */
            lifes.render(onScreenRasterRender);
        }


    }

    public static PokemonAssets getAssets() {
        return assets;
    }
}
