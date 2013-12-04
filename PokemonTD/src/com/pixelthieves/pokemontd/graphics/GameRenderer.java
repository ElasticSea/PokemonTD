package com.pixelthieves.pokemontd.graphics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.pixelthieves.core.graphics.Renderable;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.graphics.ui.Ui;
import com.pixelthieves.pokemontd.manager.TowerManager;
import com.pixelthieves.pokemontd.map.MapData;
import com.pixelthieves.pokemontd.map.Path;
import com.pixelthieves.pokemontd.map.PathPack;
import com.pixelthieves.pokemontd.system.autonomous.*;

/**
 * Created by Tomas on 11/7/13.
 */
public class GameRenderer implements Renderable {

    private final App app;
    private final Camera camera;
    private final TileMap<TextureAtlas.AtlasRegion> tileMap;
    private final PathPack pathPack;
    private final TowerManager towerManager;
    private final SpriteBatch gameSpriteBatch;
    private final ShapeRenderer gameShapeRenderer;

    public GameRenderer(App app, MapData mapData, Camera camera) {
        this.app = app;
        this.camera = camera;
        this.towerManager = app.getTowerManager();
        this.gameSpriteBatch = app.getGameSpriteBatch();
        this.gameShapeRenderer = app.getGameShapeRenderer();
        this.tileMap = mapData.getTileMap();
        this.pathPack = mapData.getPathPack();
    }

    @Override
    public void render() {
        RenderSpriteSystem renderSpriteSystem = app.getWorld().getSystem(RenderSpriteSystem.class);
        RenderTextSystem renderTextSystem = app.getWorld().getSystem(RenderTextSystem.class);
        RenderRangeSystem renderRangeSystem = app.getWorld().getSystem(RenderRangeSystem.class);
        RenderDebugSystem renderDebugSystem = app.getWorld().getSystem(RenderDebugSystem.class);
        RenderHealthSystem renderHealthSystem = app.getWorld().getSystem(RenderHealthSystem.class);

        gameSpriteBatch.setProjectionMatrix(camera.combined);
        gameShapeRenderer.setProjectionMatrix(camera.combined);

        gameSpriteBatch.begin();
        drawMap(0);
        drawMap(1);
        gameSpriteBatch.end();
        towerManager.render(gameShapeRenderer);
        gameSpriteBatch.begin();
        renderSpriteSystem.process();
        renderTextSystem.process();
        drawMap(2);
        drawMap(4);
        drawMap(3);
        gameSpriteBatch.end();

        gameShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderRangeSystem.process();
        if (App.DEBUG != null) {
            renderDebugSystem.process();
            drawPath();
            drawGrid();
        }
        if (!app.isFreezed()) {
            renderHealthSystem.process();
        }
        gameShapeRenderer.end();
        if (app.isSessionStarted()) {
            app.getUi().render();
        }
    }

    private void drawGrid() {
        int height = tileMap.getHeight(0) * tileMap.getTileSize(0);
        int width = tileMap.getWidth(0) * tileMap.getTileSize(0);
        for (int i = 0; i < height; i++) {
            gameShapeRenderer.line(0, i * App.WORLD_SCALE, width * App.WORLD_SCALE, i * App.WORLD_SCALE);
        }
        for (int i = 0; i < width; i++) {
            gameShapeRenderer.line(i * App.WORLD_SCALE, 0, i * App.WORLD_SCALE, height * App.WORLD_SCALE);
        }
    }

    private void drawMap(int level) {
        for (int j = 0; j < tileMap.getWidth(level); j++) {
            for (int k = 0; k < tileMap.getHeight(level); k++) {
                int size = tileMap.getTileSize(level) * App.WORLD_SCALE;
                TextureAtlas.AtlasRegion atlasRegion = tileMap.get(j, k, level);
                if (atlasRegion != null) {
                    gameSpriteBatch.draw(atlasRegion, j * size, k * size, size, size);
                }
            }

        }
    }

    private void drawPath() {
        for (Path path : pathPack.getPaths()) {
            Vector3 lastPoint = null;
            for (Vector3 point : path.getPath()) {
                if (lastPoint != null) {
                    gameShapeRenderer.line(lastPoint.x, lastPoint.y, point.x, point.y);
                }
                lastPoint = point;
            }
        }
    }


}
