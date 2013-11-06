package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.xkings.pokemontd.App;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class Menu extends Gui {

    public enum Type {
        BLANK, INGAME, MAIN;
    }

    private final MenuTab inGameMenu;
    private final MenuTab defaultCard;
    private MenuTab pickedCard;
    private final MenuTab menuBox;

    public Menu(App app) {
        super(app);
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


    private class InGameMenu extends MenuTab {

        private final Button exit;
        private final Button pause;

        InGameMenu(Gui ui, Rectangle rectangle) {
            super(ui, rectangle);
            exit = new Button(ui, rects.get(rects.size() - 1), ui.getFont(), BitmapFont.HAlignment.CENTER) {
                @Override
                public void process(float x, float y) {
                    System.exit(0);
                }
            };
            pause = new Button(ui, rects.get(0), ui.getFont(), BitmapFont.HAlignment.CENTER) {
                @Override
                public void process(float x, float y) {
                    app.freeze(!app.isFreezed());
                }
            };
            register(exit);
            register(pause);
        }

        @Override
        public void render() {
            super.render();
            exit.render("EXIT");
            pause.render(app.isFreezed() ? "RESUME" : "PAUSE");
        }
    }

    private class MenuBox extends MenuTab {

        private final Button exit;
        private final Button startGame;

        MenuBox(Gui ui, Rectangle rectangle) {
            super(ui, rectangle);
            exit = new Button(ui, rects.get(rects.size() - 1), ui.getFont(), BitmapFont.HAlignment.CENTER) {
                @Override
                public void process(float x, float y) {
                    System.exit(0);
                }
            };
            startGame = new Button(ui, rects.get(0), ui.getFont(), BitmapFont.HAlignment.CENTER) {
                @Override
                public void process(float x, float y) {
                    app.setSessionStarted(true);
                    app.freeze(false);
                    switchCard(null);
                }
            };
            register(exit);
            register(startGame);
            this.setCloseTabWhenNotClicked(false);
        }

        @Override
        public void render() {
            super.render();
            exit.render("EXIT");
            startGame.render("PLAY GAME");
        }
    }

    private class MenuTab extends GuiBox {

        protected final List<Rectangle> rects;
        private boolean closeTabWhenNotClicked = true;

        MenuTab(Gui ui, Rectangle rectangle) {
            super(ui, rectangle);
            rects = getRects(offsetRectange, 5);
        }

        private List<Rectangle> getRects(Rectangle rect, int count) {
            int buttonHeight = (int) (rect.height / count);
            List<Rectangle> rects = new ArrayList<Rectangle>();
            for (int i = 0; i < count; i++) {
                int offset = buttonHeight * (i + 1);
                rects.add(new Rectangle(offsetRectange.x, offsetRectange.y + offsetRectange.height - offset,
                        offsetRectange.width, buttonHeight));
            }
            return rects;
        }

        public boolean isCloseTabWhenNotClicked() {
            return closeTabWhenNotClicked;
        }

        public void setCloseTabWhenNotClicked(boolean closeTabWhenNotClicked) {
            this.closeTabWhenNotClicked = closeTabWhenNotClicked;
        }
    }

    private void switchCard(MenuTab card) {
        pickedCard = card;
    }

}
