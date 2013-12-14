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
    private AdHandler handler;
    private RevMob revmob;
    private RevMobFullscreen fullscreen;

    public RevMobAdService(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        // Starting RevMob session
        revmob = RevMob.start(activity);
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
    public void showAd(AdType adType) {
        if(adType.equals(AdType.Interestial)){
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    fullscreen.show();
                    setHandler(handler);
                }
            });
        }
    }

    @Override
    public void setHandler(final AdHandler handler) {
        this.handler = handler;
        if(fullscreen == null){
            fullscreen = revmob.createFullscreen(activity, new RevMobAdsListener() {
                @Override
                public void onRevMobAdReceived() {
                }

                @Override
                public void onRevMobAdNotReceived(String s) {
                    handler.onAdFailed(s);
                }

                @Override
                public void onRevMobAdDisplayed() {
                    handler.onAdDisplayed();
                }

                @Override
                public void onRevMobAdDismiss() {
                    handler.onAdClosed();
                }

                @Override
                public void onRevMobAdClicked() {
                    handler.onAdClicked();
                }

            });
            System.out.println("THIS IS FULLSCREEN: "+fullscreen);
        }
    }
}
