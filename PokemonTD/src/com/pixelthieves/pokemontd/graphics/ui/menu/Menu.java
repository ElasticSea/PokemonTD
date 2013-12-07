package com.pixelthieves.pokemontd.graphics.ui.menu;

import com.badlogic.gdx.math.Rectangle;
import com.pixelthieves.pokemontd.App;
import com.pixelthieves.core.services.GameService;
import com.pixelthieves.pokemontd.graphics.ui.Button;
import com.pixelthieves.pokemontd.graphics.ui.Gui;
import com.pixelthieves.pokemontd.graphics.ui.InteractiveBlockParent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 10/8/13.
 */
public class Menu extends Gui {

    private final LeaderboardTabClose leaderboard;
    private final int menuWidth;
    public static int BUTTON_HEIGHT;
    private Button menu;

    public void setMenu(Button menu) {
        this.menu = menu;
    }

    public GameService getGameSevice() {
        return app.getGameSevice();
    }

    public enum Type {
        BLANK, INGAME, MAIN, END, LEADERBOARD, QUICK_TIP;
    }

    private final MenuTab signInBox;
    private final MenuTab menuBox;
    private final MenuTab inGameMenu;
    private final MenuTab endGame;
    private final MenuTab defaultCard;
    private final MenuTab quicktip;
    private MenuTab pickedCard;
    private final List<MenuTab> guiDialogRoots = new ArrayList<MenuTab>();

    public Menu(App app) {
        super(app);
        int height = (int) (squareSize * 1.5f);
        menuWidth = height / 3 * 4;
        int defaultButtonCount = 5;
        BUTTON_HEIGHT = height / defaultButtonCount;
        Rectangle rectangle = getRectangle(menuWidth, height);
        signInBox = new SignInBox(this, rectangle, defaultButtonCount);
        boolean canSignIn = getGameSevice().canSingIn();
        inGameMenu = new InGameMenu(this, rectangle, defaultButtonCount);
        menuBox = new MenuBox(this, rectangle, defaultButtonCount);
        endGame = canSignIn ? new SignInEndGame(this, rectangle, defaultButtonCount) :
                new ScoreTabClose(this, rectangle, defaultButtonCount);
        leaderboard = new LeaderboardTabClose(this, null, getRectangle(8),8,
                CloseExitTab.Type.EXIT);
        quicktip = new Basic(this, null, getRectangle((int) (menuWidth * 1.5f), (int) (height * 1.5f)));

        guiDialogRoots.add(signInBox);
        guiDialogRoots.add(inGameMenu);
        guiDialogRoots.add(menuBox);
        guiDialogRoots.add(endGame);
        guiDialogRoots.add(quicktip);

        defaultCard = canSignIn ? signInBox : menuBox;
        pickedCard = defaultCard;
    }

    public Rectangle getRectangle(int width, int height) {
        return new Rectangle(center.x - width / 2, center.y - height / 2, width, height);
    }

    public Rectangle getRectangle(int buttons) {
        return getRectangle(menuWidth, buttons * BUTTON_HEIGHT);
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
        if (pickedCard != null) {
            pickedCard.pan(x, y, deltaX, deltaY);
        }
        return isVisible();
    }

    public boolean isVisible() {
        return pickedCard != null;
    }

    private MenuTab getPickedCardByType(Type type) {
        switch (type) {
            case INGAME:
                return inGameMenu;
            case MAIN:
                return menuBox;
            case END:
                return endGame;
            case LEADERBOARD:
                return leaderboard;
            case QUICK_TIP:
                return quicktip;
            default:
                return null;
        }
    }


    public void switchCard(Type type) {
        switchCard(getPickedCardByType(type));
    }

    void switchCard(MenuTab card) {
        pickedCard = card;
    }

}
