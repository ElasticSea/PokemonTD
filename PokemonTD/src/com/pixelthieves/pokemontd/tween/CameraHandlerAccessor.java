package com.pixelthieves.pokemontd.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.pixelthieves.core.graphics.camera.CameraHandler;

/**
 * User: Tomas <br>
 * Date: 7/22/13 <br>
 * Time: 2:26 PM <br>
 */
public class CameraHandlerAccessor implements TweenAccessor<CameraHandler> {

    public static final int SET = 1;
    public static final int ZOOM = 2;

    @Override
    public int getValues(CameraHandler target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case SET:
                returnValues[0] = target.getCamera().position.x;
                returnValues[1] = target.getCamera().position.y;
                return 2;
            case ZOOM:
                returnValues[0] = target.getCamera().zoom;
                return 1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(CameraHandler target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case SET:
                target.set(newValues[0], newValues[1]);
                break;
            case ZOOM:
                target.zoom(newValues[0]);
                break;
        }
    }
}