package com.pixelthieves.elementtd;

import com.pixelthieves.core.services.AdHandler;
import com.pixelthieves.core.services.AdService;

/**
 * Created by Tomas on 12/14/13.
 */
public class DummyAdService implements AdService {
    private AdHandler handler;

    public DummyAdService(MainActivity mainActivity) {
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void cacheAd(AdType adType) {

    }

    @Override
    public void showAd(AdType adType) {
        if(handler!=null){
        handler.onAdClosed();
        }
    }

    @Override
    public void setAdHandler(AdHandler handler) {
              this.handler = handler;
    }
}
