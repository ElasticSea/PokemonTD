package com.pixelthieves.elementtd;

import com.pixelthieves.elementtd.ads.ChartboostAdService;

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
