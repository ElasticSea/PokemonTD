package com.xkings.pokemontd;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector3;
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
import com.xkings.pokemontd.graphics.TileMap;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.input.EnhancedGestureProcessor;
import com.xkings.pokemontd.manager.ProjectileManager;
import com.xkings.pokemontd.manager.TowerManager;
import com.xkings.pokemontd.manager.WaveManager;
import com.xkings.pokemontd.map.MapBuilder;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;
import com.xkings.pokemontd.map.PathPack;
import com.xkings.pokemontd.system.*;
import com.xkings.pokemontd.system.abilitySytems.FireProjectilSystem;

public class App extends Game2D {


    public static final float WAVE_INTERVAL = 5f;
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
    private RenderSpriteSystem renderSpriteSystem;
    private RenderDebugSystem renderDebugSystem;
    private ClosestEnemySystem closestEnemySystem;
    private GetTowerInfoSystem getTowerInfoSystem;
    private PathPack pathPack;
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
        shapeRenderer = new ShapeRenderer();


        MapData map = createTestMap();
        tileMap = map.getTileMap();
        blueprint = map.getBlueprint();
        pathPack = map.getPathPack();
        this.cameraHandler = new BoundedCameraHandler(camera, tileMap.getWidth() * tileMap.TILE_SIZE,
                tileMap.getHeight() * tileMap.TILE_SIZE, 0.001f);

        initializeContent();
        initializeManagers();
        initializeSystems();
        ui = new Ui(player, waveManager, towerManager, camera);
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
        // FIXME nasty hack
        this.pathBlock = world.createEntity();
        this.clock.addService(new WorldUpdater(world));
    }

    private void initializeContent() {
        player = new Player(9999, 9999, 1, 2, 3, 0, 2, 1, 1);
    }

    private void initializeManagers() {
        this.waveManager = new WaveManager(world, clock, pathPack.getMain(), WAVE_INTERVAL);
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


    private MapData createTestMap() {
        return new MapBuilder(9, 12, 3, 11,
                MapBuilder.Direction.DOWN).addStraight().addRight().addStraight().addLeft().addStraight(
                2).addLeft().addStraight().addLeft().addRight().addStraight().addRight().addStraight(
                2).addRight().addStraight(3).addLeft().addStraight().addLeft().addStraight(5).addLeft().addStraight(
                6).addLeft().addStraight().addRight().addStraight().build();
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

            lifes.render(onScreenRasterRender);

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

    public static PokemonAssets getAssets() {
        return assets;
    }
}
