package com.pixelthieves.elementtd;

import android.app.Activity;
import android.os.Build;
import com.pixelthieves.core.services.AdHandler;
import com.pixelthieves.core.services.AdService;
import com.vungle.sdk.VunglePub;

/**
 * Created by Tomas on 12/18/13.
 */
public class VungleAdService implements AdService {
    private final Activity activity;

    public VungleAdService(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        // get your App ID from the app's main page on the Vungle Dashboard after setting up your app
        final String app_id = "com.pixelthieves.pokemontd";

        // initialize the Publisher SDK
        VunglePub.init(activity, app_id);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {
        VunglePub.onResume();
    }

    @Override
    public void onPause() {
        VunglePub.onPause();
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
        if (adType.equals(AdType.Video) && android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            VunglePub.displayAdvert();
        }
    }

    @Override
    public void setAdHandler(final AdHandler adHandler) {
        VunglePub.setEventListener(new VunglePub.EventListener() {
            @Override
            public void onVungleView(double watchedSeconds, double totalAdSeconds) {
                if (adHandler != null) {
                    adHandler.onAdClicked();
                }

            }

            @Override
            public void onVungleAdStart() {
                if (adHandler != null) {
                    adHandler.onAdClosed();
                }

            }

            @Override
            public void onVungleAdEnd() {
                if (adHandler != null) {
                    adHandler.onAdClosed();
                }
            }
        });
    }
}
