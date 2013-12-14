package com.pixelthieves.pokemontd;

import android.app.Activity;
import com.pixelthieves.core.services.AdHandler;
import com.pixelthieves.core.services.AdService;
import com.revmob.RevMob;
import com.revmob.RevMobAdsListener;
import com.revmob.ads.fullscreen.RevMobFullscreen;

/**
 * Created by Tomas on 12/13/13.
 */
public class RevMobAdService implements AdService {
    private final Activity activity;
    private RevMob revmob;
    private RevMobFullscreen cache;
    private LiveListener listener;
    private AdHandler adHandler;

    public RevMobAdService(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        // Starting RevMob session
        revmob = RevMob.start(activity);
        listener = new LiveListener();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        this.onCreate();
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
        if (adType.equals(AdType.Interestial)) {
            cache = revmob.createFullscreen(activity, listener);
        }
    }

    @Override
    public void showAd(AdType adType) {
        if (adType.equals(AdType.Interestial)) {
            if (cache != null) {
                cache.show();
            } else {
                revmob.showFullscreen(activity, listener);
            }
        }
    }

    @Override
    public void setAdHandler(AdHandler adHandler) {
        this.adHandler = adHandler;
    }

    private class LiveListener implements RevMobAdsListener {
        @Override
        public void onRevMobAdReceived() {

        }

        @Override
        public void onRevMobAdNotReceived(String s) {
            if (adHandler != null) {
                adHandler.onAdFailed(s);
            }
        }

        @Override
        public void onRevMobAdDisplayed() {
            if (adHandler != null) {
                adHandler.onAdDisplayed();
            }
        }

        @Override
        public void onRevMobAdDismiss() {
            if (adHandler != null) {
                adHandler.onAdClosed();
            }
        }

        @Override
        public void onRevMobAdClicked() {
            if (adHandler != null) {
                adHandler.onAdClicked();
            }
        }
    }
}
