package com.xkings.pokemontd.graphics;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.xkings.core.graphics.Renderable;
import com.xkings.pokemontd.App;
import com.xkings.pokemontd.graphics.ui.GuiBox;
import com.xkings.pokemontd.graphics.ui.Ui;
import com.xkings.pokemontd.map.MapData;
import com.xkings.pokemontd.map.Path;
import com.xkings.pokemontd.map.PathPack;
import com.xkings.pokemontd.system.autonomous.*;

/**
 * Created by Tomas on 11/7/13.
 */
public class GameRenderer implements Renderable {

    private final App app;
    private final Ui ui;
    private final Camera camera;
    private final SpriteBatch spriteBatch;
    private final ShapeRenderer shapeRenderer;
    private final RenderTextSystem renderTextSystem;
    private final RenderSpriteSystem renderSpriteSystem;
    private final RenderRangeSystem renderRangeSystem;
    private final RenderDebugSystem renderDebugSystem;
    private final RenderHealthSystem renderHealthSystem;
    private final TileMap<TextureAtlas.AtlasRegion> tileMap;
    private final PathPack pathPack;

    public GameRenderer(App app, Ui ui, MapData mapData, World world, Camera camera) {
        this.app = app;
        this.ui = ui;
        this.camera = camera;
        this.spriteBatch = app.getSpriteBatch();
        this.shapeRenderer = app.getShapeRenderer();
        this.renderSpriteSystem = world.getSystem(RenderSpriteSystem.class);
        this.renderTextSystem = world.getSystem(RenderTextSystem.class);
        this.renderRangeSystem = world.getSystem(RenderRangeSystem.class);
        this.renderDebugSystem = world.getSystem(RenderDebugSystem.class);
        this.renderHealthSystem = world.getSystem(RenderHealthSystem.class);
        this.tileMap = mapData.getTileMap();
        this.pathPack = mapData.getPathPack();
    }

    @Override
    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawMap(0);
        drawMap(1);
        renderSpriteSystem.process();
        renderTextSystem.process();
        drawMap(2);
        drawMap(4);
        drawMap(3);
        spriteBatch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        renderRangeSystem.process();
        if (App.DEBUG != null) {
            renderDebugSystem.process();
        }
        drawPath();
        drawGrid();
        if (!app.isFreezed()) {
            renderHealthSystem.process();
        }
        shapeRenderer.end();
        if (app.isSessionStarted()) {
            ui.render();
        }
    }

    private void drawGrid() {
        if (App.DEBUG != null) {
            int height = tileMap.getHeight(0) * tileMap.getTileSize(0);
            int width = tileMap.getWidth(0) * tileMap.getTileSize(0);
            for (int i = 0; i < height; i++) {
                shapeRenderer.line(0, i * App.WORLD_SCALE, width * App.WORLD_SCALE, i * App.WORLD_SCALE);
            }
            for (int i = 0; i < width; i++) {
                shapeRenderer.line(i * App.WORLD_SCALE, 0, i * App.WORLD_SCALE, height * App.WORLD_SCALE);
            }
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
        if (App.DEBUG != null) {
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


}
