package com.pixelthieves.pokemontd;

import com.pixelthieves.core.services.AdService;
import com.pixelthieves.pokemontd.ads.ChartboostAdService;

import java.util.ArrayList;

/**
 * Created by Tomas on 12/13/13.
 */
public class CustomAdManager extends AdManager {

    private final ChartboostAdService chartBoost;
    private final RevMobAdService revmob;

    public CustomAdManager(MainActivity mainActivity) {
        super(mainActivity);
        this.chartBoost = new ChartboostAdService(activity);
        this.revmob = new RevMobAdService(activity);
        adService(chartBoost);
        adService(revmob);
    }

    @Override
    public void cacheAd(AdType adType) {
        switch (adType) {
            case Interestial:
                revmob.cacheAd(adType);
                break;
            case MoreApps:
                chartBoost.cacheAd(adType);
                break;
        }
    }

    @Override
    public void showAd(AdType adType) {
        switch (adType) {
            case Interestial:
                revmob.showAd(adType);
                break;
            case MoreApps:
                chartBoost.showAd(adType);
                break;
        }
    }
}
