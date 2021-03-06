package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.elementtd.entity.creep.CreepType;
import com.pixelthieves.elementtd.graphics.ui.menu.Header;
import com.pixelthieves.elementtd.manager.WaveManager;

/**
 * Created by Tomas on 10/11/13.
 */
public class WaveInfo extends GuiBox {
    private final BitmapFont pixelFont;
    private final WaveManager waveManager;
    private final DisplayText waveText;
    private final DisplayText waveNumberText;
    private final DisplayText abilityText;
    private final DisplayPicture creepTexture;
    private final Button nextWave;
    public static final Color NEXTWAVE_COLOR = new Color(Color.GREEN).mul(0.6f, 0.6f, 0.6f, 1);
    private final Header header;

    WaveInfo(final Ui ui, Rectangle rectangle, WaveManager waveManager, BitmapFont font) {
        super(ui, rectangle);
        this.waveManager = waveManager;
        this.pixelFont = font;
        this.header = new Header(ui, new Rectangle());
        float textHeight = height / 7f;
        Rectangle scaled =
                new Rectangle(offsetRectange.x + offset, offsetRectange.y + offset, offsetRectange.width - offset * 2,
                        offsetRectange.height - offset * 2);
        float quarterSize = scaled.height / 4f;
        Rectangle waveRectangle =
                new Rectangle(scaled.x, scaled.height + scaled.y - textHeight, scaled.width, textHeight);
        Rectangle abilityRectangle = new Rectangle(scaled.x, scaled.y + quarterSize, scaled.width, textHeight);
        this.waveText = new DisplayText(ui, waveRectangle, font, BitmapFont.HAlignment.LEFT);
        this.waveNumberText = new DisplayText(ui, waveRectangle, font, BitmapFont.HAlignment.RIGHT);
        this.abilityText = new DisplayText(ui, abilityRectangle, font, BitmapFont.HAlignment.CENTER);
        this.creepTexture =
                new DisplayPicture(ui, scaled.x + quarterSize, scaled.y + quarterSize * 1.5f, quarterSize * 2,
                        quarterSize * 2);

        float offsetBlocks = height / 8;

        Rectangle nextWaveRectangle = new Rectangle(0, 0, width, offsetBlocks * 1.5f);
        nextWave = new Button(ui, nextWaveRectangle, font, BitmapFont.HAlignment.CENTER) {
            @Override
            public void process(float x, float y) {
                ui.getWaveManager().triggerNextWave();
            }
        };

        ui.register(nextWave);
    }

    @Override
    public void render() {
        super.render();
        CreepType nextWave = waveManager.getNextWave();
        if (nextWave != null) {
            abilityText.render(nextWave.getAbilityType().toString());
            creepTexture.render(nextWave.getTexture(), "");
            this.nextWave.render(nextWave.getId() == 0 ? "START GAME" : "NEXT WAVE", Color.WHITE, NEXTWAVE_COLOR);
            header.render("Next Wave", String.valueOf(nextWave.getId() + 1));
        } else {
            header.render("", "");
        }
    }

    @Override
    public void refresh() {
        super.refresh();
        float textHeight = height / 7f;
        Rectangle scaled = new Rectangle(x + offset * 2, y + offset, width - offset * 4, height - offset * 2);
        float quarterSize = scaled.height / 4f;
        Rectangle waveRectangle =
                new Rectangle(scaled.x, scaled.height + scaled.y - textHeight, scaled.width, textHeight);

        float offsetBlocks = height / 8;
        this.waveText.set(waveRectangle);
        this.waveNumberText.set(waveRectangle);
        this.abilityText.set(scaled.x, scaled.y + quarterSize, scaled.width, textHeight);
        this.creepTexture.set(scaled.x + quarterSize, scaled.y + quarterSize * 1.5f, quarterSize * 2, quarterSize * 2);
        this.nextWave.set(x, y, width, offsetBlocks * 1.5f);
        this.header.set(x, y + height, width, width / 10);
        waveText.refresh();
        waveNumberText.refresh();
        abilityText.refresh();
        creepTexture.refresh();
        nextWave.refresh();
        header.refresh();
    }

    public Rectangle getNextWaveButton() {
        return nextWave;
    }
}
