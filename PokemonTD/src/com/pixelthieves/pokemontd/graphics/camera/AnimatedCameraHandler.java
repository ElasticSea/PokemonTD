package com.pixelthieves.pokemontd.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pixelthieves.core.graphics.camera.BoundedCameraHandler;

/**
 * Created by Tomas on 12/2/13.
 */
public class AnimatedCameraHandler extends BoundedCameraHandler {
    public AnimatedCameraHandler(OrthographicCamera camera, int width, int height, float zoomMax) {
        super(camera, width, height, zoomMax);
    }


}
