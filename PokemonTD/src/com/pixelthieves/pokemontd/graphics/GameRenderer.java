package com.pixelthieves.pokemontd.graphics;

import com.artemis.World;
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
    private final Ui ui;
    private final Camera camera;
    private final SpriteBatch spriteBatch;
    private final ShapeRenderer shapeRenderer;
    private final TileMap<TextureAtlas.AtlasRegion> tileMap;
    private final PathPack pathPack;
    private final TowerManager towerManager;

    public GameRenderer(App app, Ui ui, MapData mapData, Camera camera) {
        this.app = app;
        this.ui = ui;
        this.camera = camera;
        this.towerManager = ui.getTowerManager();
        this.spriteBatch = app.getSpriteBatch();
        this.shapeRenderer = app.getShapeRenderer();
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

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawMap(0);
        drawMap(1);
        spriteBatch.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
        towerManager.render(shapeRenderer);
        spriteBatch.begin();
        renderSpriteSystem.process();
        renderTextSystem.process();
        drawMap(2);
        drawMap(4);
        drawMap(3);
        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderRangeSystem.process();
        if (App.DEBUG != null) {
            renderDebugSystem.process();
        drawPath();
        drawGrid();
        }
        if (!app.isFreezed()) {
            renderHealthSystem.process();
        }
        shapeRenderer.end();
        if (app.isSessionStarted()) {
            ui.render();
        }
    }

    private void drawGrid() {
            int height = tileMap.getHeight(0) * tileMap.getTileSize(0);
            int width = tileMap.getWidth(0) * tileMap.getTileSize(0);
            for (int i = 0; i < height; i++) {
                shapeRenderer.line(0, i * App.WORLD_SCALE, width * App.WORLD_SCALE, i * App.WORLD_SCALE);
            }
            for (int i = 0; i < width; i++) {
                shapeRenderer.line(i * App.WORLD_SCALE, 0, i * App.WORLD_SCALE, height * App.WORLD_SCALE);
            }
    }

    private void drawMap(int level) {
        for (int j = 0; j < tileMap.getWidth(level); j++) {
            for (int k = 0; k < tileMap.getHeight(level); k++) {
                int size = tileMap.getTileSize(level) * App.WORLD_SCALE;
                TextureAtlas.AtlasRegion atlasRegion = tileMap.get(j, k, level);
                if (atlasRegion != null) {
                    spriteBatch.draw(atlasRegion, j * size, k * size, size, size);
                }
            }

        }
    }

    private void drawPath() {
            for (Path path : pathPack.getPaths()) {
                Vector3 lastPoint = null;
                for (Vector3 point : path.getPath()) {
                    if (lastPoint != null) {
                        shapeRenderer.line(lastPoint.x, lastPoint.y, point.x, point.y);
                    }
                    lastPoint = point;
                }
        }
    }


}
