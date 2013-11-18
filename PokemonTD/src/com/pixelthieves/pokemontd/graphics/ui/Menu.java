package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.GameService;

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

    public enum Type {
        BLANK, INGAME, MAIN, END;
    }

    private final MenuTab signInBox;
    private final MenuTab menuBox;
    private final MenuTab inGameMenu;
    private final MenuTab endGame;
    private final MenuTab defaultCard;
    private MenuTab pickedCard;
    private final List<MenuTab> guiDialogRoots = new ArrayList<MenuTab>();

    public Menu(App app) {
        super(app);
        buttonHeight = squareSize / 5;
        int menuWidth = squareSize / 3 * 4;
        Rectangle rectangle = new Rectangle(center.x - menuWidth / 2, center.y - squareSize / 2, menuWidth, squareSize);
        signInBox = new SignInBox(this, rectangle);
        inGameMenu = new InGameMenu(this, rectangle);
        menuBox = new MenuBox(this, rectangle);
        endGame = new EndGame(this, rectangle);

        guiDialogRoots.add(signInBox);
        guiDialogRoots.add(inGameMenu);
        guiDialogRoots.add(menuBox);
        guiDialogRoots.add(endGame);

        defaultCard = signInBox;
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
        for (MenuTab menu : guiDialogRoots) {
            menu.pan(x, y, deltaX, deltaY);
        }
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
            case END:
                return endGame;
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
            guiButton.render("GRAPHICAL INTERFACE");
            musicButton.render(theme.isPlaying() ? "MUTE MUSIC" : "PLAY MUSIC");
        }
    }

    private class Tutorial extends ChildTab {

        private final float textBlockHeight;
        private final float textBlockY;
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

            textBlockHeight = rectangle.height - buttonHeight - buttonHeight * 2;
            textBlockY = rectangle.y + buttonHeight + buttonHeight;
            text = new DisplayText(ui,
                    new Rectangle(rectangle.x + segment, textBlockY, rectangle.width - segment * 2, textBlockHeight),
                    ui.getFont(), BitmapFont.HAlignment.LEFT, true);

            register(text);
            this.setRenderLines(false);
        }

        @Override
        public void render() {
            super.render();
            text.render(tutorialText);
        }

        @Override
        public void pan(float x, float y, float deltaX, float deltaY) {
            float overlap = text.getTextHeight() - textBlockHeight;
            if (overlap > 0) {
                text.y = MathUtils.clamp(text.y - deltaY, textBlockY, textBlockY + overlap);
            }
            super.pan(x, y, deltaX, deltaY);
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

    private class InGameMenu extends CommonMenu {

        private final Button pause;

        InGameMenu(Gui ui, Rectangle rectangle) {
            super(ui, rectangle, true);
            pause = new MenuButton(ui, rects.get(0)) {
                @Override
                public void process(float x, float y) {
                    app.freeze(!app.isFreezed());
                }
            };

            register(pause);
        }

        @Override
        public void render() {
            super.render();
            pause.render(app.isFreezed() ? "RESUME" : "PAUSE");
        }
    }

    private class SignInBox extends MenuTab {

        private final MenuButton signInButton;
        private final MenuButton skipButton;

        SignInBox(Gui ui, Rectangle rectangle) {
            super(null, ui, rectangle);
            signInButton = new MenuButton(ui, rects.get(0)) {
                @Override
                public void process(float x, float y) {
                    app.getGameSevice().signIn();
                }
            };
            skipButton = new MenuButton(ui, rects.get(rects.size() - 1)) {
                @Override
                public void process(float x, float y) {
                    switchCard(menuBox);
                    app.getGameSevice().skipSignIn();
                }
            };
            register(signInButton);
            register(skipButton);
            this.setCloseTabWhenNotClicked(false);
        }

        @Override
        public void render() {
            if (app.getGameSevice().isSignedIn()) {
                switchCard(menuBox);
                return;
            }

            super.render();
            signInButton.render("SING IN");
            skipButton.render("SKIP");
        }
    }

    private class MenuBox extends CommonMenu {

        private final Button startGame;

        MenuBox(Gui ui, Rectangle rectangle) {
            super(ui, rectangle, false);
            startGame = new MenuButton(ui, rects.get(0)) {
                @Override
                public void process(float x, float y) {
                    app.setSessionStarted(true);
                    app.freeze(false);
                    close();
                }
            };
            register(startGame);
            this.setCloseTabWhenNotClicked(false);
        }

        @Override
        public void render() {
            super.render();
            startGame.render("PLAY GAME");
        }
    }

    private class CommonMenu extends ExitTab {

        private final Options options;
        private final Tutorial tutorial;
        private final MenuButton optionsButton;
        private final MenuButton tutorialButton;
        private final MenuButton signInButton;

        CommonMenu(Gui ui, Rectangle rectangle, boolean closeButton) {
            super(ui, rectangle, closeButton);
            tutorial = new Tutorial(this, ui, new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
            options = new Options(this, ui, rectangle);
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

            signInButton = new MenuButton(ui, rects.get(3)) {
                @Override
                public void process(float x, float y) {
                    GameService gameSevice = app.getGameSevice();
                    if (gameSevice.isSignedIn()) {
                        gameSevice.signOut();
                    } else {
                        gameSevice.signIn();
                    }
                }
            };
            register(optionsButton);
            register(tutorialButton);
            register(signInButton);
            cards.add(options);
            cards.add(tutorial);
        }

        @Override
        public void render() {
            super.render();
            optionsButton.render("OPTIONS");
            tutorialButton.render("TUTORIAL");
            signInButton.render(app.getGameSevice().isSignedIn() ? "SIGN OUT" : "SIGN IN");
        }
    }

    private class EndGame extends ExitTab {

        private final DisplayText score;
        private final DisplayText congratulations;
        private final MenuButton publishScoreButton;

        EndGame(Gui ui, Rectangle rectangle) {
            super(ui, rectangle, false);
            congratulations = new DisplayText(ui, rects.get(0), ui.getFont(), BitmapFont.HAlignment.CENTER);
            score = new DisplayText(ui, rects.get(1), ui.getFont(), BitmapFont.HAlignment.CENTER);

            publishScoreButton = new MenuButton(ui, rects.get(rects.size()-2)) {
                @Override
                public void process(float x, float y) {
                    app.getGameSevice().submitScore(app.getPlayer().getScore().getScore());
                }
            };
            register(publishScoreButton);
            this.setCloseTabWhenNotClicked(false);
            this.setRenderLines(false);
        }

        @Override
        public void render() {
            super.render();
            congratulations.render("CONGRATULATIONS");
            score.render(app.getPlayer().getScore() + "");
            publishScoreButton.render("PUBLISH SCORE");
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
        protected List<MenuTab> cards = new ArrayList<MenuTab>();

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

        public void pan(float x, float y, float deltaX, float deltaY) {
            for (MenuTab card : cards) {
                card.pan(x, y, deltaX, deltaY);
            }
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
