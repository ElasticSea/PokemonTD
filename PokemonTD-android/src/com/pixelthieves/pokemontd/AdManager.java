package com.pixelthieves.pokemontd;

import android.app.Activity;
import com.pixelthieves.core.services.AdHandler;
import com.pixelthieves.core.services.AdService;

import java.util.ArrayList;

/**
 * Created by Tomas on 12/13/13.
 */
public abstract class AdManager implements AdService {

    private final ArrayList<AdService> services = new ArrayList<AdService>();
    protected final Activity activity;

    public AdManager(Activity activity) {
        this.activity = activity;
    }

    protected void adService(AdService adService) {
        services.add(adService);
    }

    @Override
    public void onCreate() {
        for (AdService service : services) {
            service.onCreate();
        }
    }

    @Override
    public void onStart() {
        for (AdService service : services) {
            service.onStart();
        }
    }

    @Override
    public void onResume() {
        for (AdService service : services) {
            service.onResume();
        }
    }

    @Override
    public void onPause() {
        for (AdService service : services) {
            service.onPause();
        }
    }

    @Override
    public void onStop() {
        for (AdService service : services) {
            service.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (AdService service : services) {
            service.onDestroy();
        }
    }

    @Override
    public void setAdHandler(AdHandler handler) {
        for (AdService service : services) {
            service.setAdHandler(handler != null ? handler : getDummyHandler());
        }
    }

    private AdHandler getDummyHandler() {
        return new AdHandler() {
            @Override
            public void onAdDisplayed() {
                System.out.println("Dummy Ad Handler : onAdDisplayed");
            }

            @Override
            public void onAdClicked() {
                System.out.println("Dummy Ad Handler : onAdClicked");
            }

            @Override
            public void onAdClosed() {
                System.out.println("Dummy Ad Handler : onAdClosed");
            }

            @Override
            public void onAdFailed(String message) {
                System.out.println("Dummy Ad Handler : onAdFailed");
            }
        };
    }
}
