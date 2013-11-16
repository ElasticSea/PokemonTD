package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.main.Assets;
import com.xkings.pokemontd.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class Menu extends Gui {

    private final int buttonHeight;
    private Button menu;

    public void setMenu(Button menu) {
        this.menu = menu;
    }

    public Button getMenu() {
        return menu;
    }

    public enum Type {
        BLANK, INGAME, MAIN;
    }

    private final MenuTab inGameMenu;
    private final MenuTab defaultCard;
    private MenuTab pickedCard;
    private final MenuTab menuBox;

    public Menu(App app) {
        super(app);
        buttonHeight = squareSize / 5;
        int menuWidth = squareSize / 3 * 4;
        Rectangle rectangle = new Rectangle(center.x - menuWidth / 2, center.y - squareSize / 2, menuWidth, squareSize);
        inGameMenu = new InGameMenu(this, rectangle);
        menuBox = new MenuBox(this, rectangle);

        defaultCard = menuBox;
        pickedCard = defaultCard;
    }

    @Override
    public void render() {
        if (isVisible()) {
            super.render();
            pickedCard.render();
        }
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        InteractiveBlockParent cardBeforeClick = pickedCard;
        if (menu.hit(x, y)) {
            return true;
        }

        if (isVisible()) {
            if (!pickedCard.hit(x, y) && pickedCard.isCloseTabWhenNotClicked()) {
                pickedCard = null;
            }
        }
        return cardBeforeClick != null;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return isVisible();
    }

    public boolean isVisible() {
        return pickedCard != null;
    }

    public void triggerMenu(Type type) {
        this.pickedCard = getPickedCardByType(type);
    }


    private MenuTab getPickedCardByType(Type type) {
        switch (type) {
            case INGAME:
                return inGameMenu;
            case MAIN:
                return menuBox;
            default:
                return null;
        }
    }


    private class Options extends ChildTab {

        private final Button guiButton;
        private final Button musicButton;
        private final GUI gui;
        private final Music theme;

        Options(MenuTab parent, Gui ui, Rectangle rectangle) {
            super(parent, ui, rectangle);

            theme = Assets.getMusic("theme.ogg");
            theme.setLooping(true);
            theme.setVolume(0.2f);
            // theme.play();

            gui = new GUI(this, ui, rectangle);
            guiButton = new MenuButton(ui, rects.get(0)) {
                @Override
                public void process(float x, float y) {
                    switchCard(gui);
                }
            };

            musicButton = new MenuButton(ui, rects.get(1)) {
                @Override
                public void process(float x, float y) {
                    if (theme.isPlaying()) {
                        theme.stop();
                    } else {
                        theme.play();
                    }
                }
            };

            register(guiButton);
            register(musicButton);
        }

        @Override
        public void render() {
            super.render();
            guiButton.render("GRAPHICAL USER INTERFACE");
            musicButton.render(theme.isPlaying() ? "MUTE MUSIC" : "PLAY MUSIC");
        }
    }

    private class Tutorial extends ChildTab {

        private String tutorialText =
                "In Pokemon tower defense the goal is to prevents the creeps from reaching the end of the path by building towers and surviving all creep waves. Every time a creep gets through the whole way, you loose life.\n\n" +
                        "Information about current live count, your gold, what the next wave will be and interest can be seen in the upper right box.\n\n" +
                        "To build a tower pick one of the towers in the lower right box, place it where you want it on the map, and conform it by pressing BUY button.\n\n" +
                        "You can also upgrade the towers by clicking at already built towers and then choosing an upgrade in the lower right box.\n\n" +
                        "To build some towers you need more than just gold - elements {WATER, FIRE, NATURE, LIGHT, DARKNESS, PURE, SOUL}. To get the elements you need, build a shop tower, which can be also found in lower right corner along with other basic tower types. When you choose particular element a creep carrying the element will spawn. To claim element you need to kill this creep.\n\n" +
                        "It´s generally good idea to save your money, because each 15 seconds you got interest.";
        private final DisplayText text;

        Tutorial(MenuTab parent, Gui ui, Rectangle rectangle) {
            super(parent, ui, rectangle);

            text = new DisplayText(ui, new Rectangle(rectangle.x + segment, rectangle.y + buttonHeight + buttonHeight,
                    rectangle.width - segment * 2, rectangle.height - buttonHeight - buttonHeight * 2), ui.getFont(),
                    BitmapFont.HAlignment.LEFT, true);

            register(text);
            this.setRenderLines(false);
        }

        @Override
        public void render() {
            super.render();
            text.render(tutorialText);
        }
    }

    private class GUI extends ChildTab {

        private final MenuButton minus;
        private final MenuButton plus;
        private final DisplayText guiSize;
        private final MenuButton defaultGuiSize;

        GUI(MenuTab parent, Gui ui, Rectangle rectangle) {
            super(parent, ui, rectangle);
            Rectangle rect = rects.get(0);
            minus = new MenuButton(ui, new Rectangle(rect.x, rect.y, rect.width / 4, rect.height)) {
                @Override
                public void process(float x, float y) {
                    app.makeGuiSmaller();
                }
            };
            plus = new MenuButton(ui, new Rectangle(rect.x + rect.width * 3 / 4, rect.y, rect.width / 4, rect.height)) {
                @Override
                public void process(float x, float y) {
                    app.makeGuiLarger();
                }
            };

            guiSize =
                    new DisplayText(ui, new Rectangle(minus.x + minus.width, rect.y, rect.width / 2, rect.height), font,
                            BitmapFont.HAlignment.CENTER);

            defaultGuiSize = new MenuButton(ui, rects.get(1)) {
                @Override
                public void process(float x, float y) {
                    app.resetGuiSize();
                }
            };

            register(minus);
            register(plus);
            register(guiSize);
            register(defaultGuiSize);
        }

        @Override
        public void render() {
            super.render();
            minus.render("-");
            plus.render("+");
            guiSize.render("GUI SIZE");
            defaultGuiSize.render("RESET");
        }
    }

    private class InGameMenu extends ExitTab {

        private final Button pause;
        private final Options options;
        private final Tutorial tutorial;
        private final MenuButton tutorialButton;
        private final MenuButton optionsButton;

        InGameMenu(Gui ui, Rectangle rectangle) {
            super(ui, rectangle, true);
            options = new Options(this, ui, rectangle);
            float halfWidth = rectangle.width * 1.5f;
            float halfHeight = rectangle.height * 1.5f;
            tutorial = new Tutorial(this, ui,
                    new Rectangle(center.x - halfWidth, center.y - halfHeight, halfWidth * 2,
                            halfHeight * 2));
            pause = new MenuButton(ui, rects.get(0)) {
                @Override
                public void process(float x, float y) {
                    app.freeze(!app.isFreezed());
                }
            };
            tutorialButton = new MenuButton(ui, rects.get(1)) {
                @Override
                public void process(float x, float y) {
                    switchCard(tutorial);
                }
            };
            optionsButton = new MenuButton(ui, rects.get(2)) {
                @Override
                public void process(float x, float y) {
                    switchCard(options);
                }
            };

            register(pause);
            register(tutorialButton);
            register(optionsButton);
        }

        @Override
        public void render() {
            super.render();
            pause.render(app.isFreezed() ? "RESUME" : "PAUSE");
            tutorialButton.render("TUTORIAL");
            optionsButton.render("OPTIONS");
        }
    }

    private class MenuBox extends ExitTab {

        private final Button startGame;
        private final Options options;
        private final Tutorial tutorial;
        private final MenuButton optionsButton;
        private final MenuButton tutorialButton;

        MenuBox(Gui ui, Rectangle rectangle) {
            super(ui, rectangle, false);
            float halfWidth = rectangle.width * 1.5f;
            float halfHeight = rectangle.height * 1.5f;
            tutorial = new Tutorial(this, ui,
                    new Rectangle(center.x - halfWidth, center.y - halfHeight, halfWidth * 2,
                            halfHeight * 2));
            options = new Options(this, ui, rectangle);
            startGame = new MenuButton(ui, rects.get(0)) {
                @Override
                public void process(float x, float y) {
                    app.setSessionStarted(true);
                    app.freeze(false);
                    close();
                }
            };
            tutorialButton = new MenuButton(ui, rects.get(1)) {
                @Override
                public void process(float x, float y) {
                    switchCard(tutorial);
                }
            };
            optionsButton = new MenuButton(ui, rects.get(2)) {
                @Override
                public void process(float x, float y) {
                    switchCard(options);
                }
            };
            register(startGame);
            register(optionsButton);
            register(tutorialButton);
            this.setCloseTabWhenNotClicked(false);
        }

        @Override
        public void render() {
            super.render();
            startGame.render("PLAY GAME");
            optionsButton.render("OPTIONS");
            tutorialButton.render("TUTORIAL");
        }
    }

    private class MenuTab extends GuiBox {

        private static final float LINE_HEIGHT = 2;
        protected final List<Rectangle> rects;
        protected final int count;
        private final MenuTab parent;
        protected final float segment;
        private boolean closeTabWhenNotClicked = true;
        private boolean renderLines = true;

        MenuTab(MenuTab parent, Gui ui, Rectangle rectangle) {
            super(ui, rectangle);
            this.parent = parent;
            count = (int) (rectangle.height / buttonHeight);
            segment = width / count;
            rects = getRects(count);
        }

        private List<Rectangle> getRects(int count) {
            List<Rectangle> rects = new ArrayList<Rectangle>();
            for (int i = 0; i < count; i++) {
                int offset = buttonHeight * (i + 1);
                rects.add(new Rectangle(x, y + height - offset, width, buttonHeight));
            }
            return rects;
        }

        @Override
        public void render() {
            super.render();
            if (isRenderLines()) {
                shapeRenderer.setColor(GuiBox.darkerColor);
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                for (int i = 1; i < count; i++) {
                    float offset = i * (height / count);
                    shapeRenderer.rect(x + segment, offset + y - LINE_HEIGHT / 2, width - segment * 2, LINE_HEIGHT);
                }
                shapeRenderer.end();
            }
        }

        public boolean isCloseTabWhenNotClicked() {
            if (parent == null) {
                return closeTabWhenNotClicked;
            } else {
                return parent.isCloseTabWhenNotClicked();
            }
        }

        public void setCloseTabWhenNotClicked(boolean closeTabWhenNotClicked) {
            if (parent == null) {
                this.closeTabWhenNotClicked = closeTabWhenNotClicked;
            } else {
                parent.setCloseTabWhenNotClicked(closeTabWhenNotClicked);
            }
        }

        public boolean isRenderLines() {
            return renderLines;
        }

        public void setRenderLines(boolean renderLines) {
            this.renderLines = renderLines;
        }

        public void close() {
            switchCard(parent);
        }
    }

    private class ChildTab extends MenuTab {

        private final MenuButton close;

        ChildTab(MenuTab parent, Gui ui, Rectangle rectangle) {
            super(parent, ui, rectangle);
            Rectangle rect2 = rects.get(rects.size() - 1);
            close = new MenuButton(ui, rect2) {
                @Override
                public void process(float x, float y) {
                    close();
                }
            };
            register(close);
        }

        @Override
        public void render() {
            super.render();
            close.render("CLOSE");
        }
    }

    private class ExitTab extends MenuTab {

        private MenuButton close;
        private final MenuButton exit;

        ExitTab(Gui ui, Rectangle rectangle, boolean closeButton) {
            super(null, ui, rectangle);
            Rectangle rect2 = rects.get(rects.size() - 1);
            Rectangle closeRect = new Rectangle(rect2.x, rect2.y, rect2.width / 2, rect2.height);
            Rectangle exitRect = new Rectangle(rect2.x + rect2.width / 2, rect2.y, rect2.width / 2, rect2.height);

            if (closeButton) {
                close = new MenuButton(ui, closeRect) {
                    @Override
                    public void process(float x, float y) {
                        close();
                    }
                };
                register(close);
            } else {
                exitRect = rect2;
            }

            exit = new MenuButton(ui, exitRect) {
                @Override
                public void process(float x, float y) {
                    System.exit(0);
                }
            };
            register(exit);
        }

        @Override
        public void render() {
            super.render();
            if (close != null) {
                close.render("CLOSE");
            }
            exit.render("EXIT");
        }
    }

    private abstract class MenuButton extends Button {

        protected MenuButton(Gui gui, Rectangle rect) {
            super(gui, rect, font, BitmapFont.HAlignment.CENTER);
        }
    }

    private void switchCard(MenuTab card) {
        pickedCard = card;
    }

}
