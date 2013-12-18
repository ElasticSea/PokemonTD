package com.pixelthieves.pokemontd;

import com.pixelthieves.pokemontd.ads.ChartboostAdService;

/**
 * Created by Tomas on 12/13/13.
 */
public class RevmobOnlyAdManager extends AdManager {

    private final RevMobAdService revmob;

    public RevmobOnlyAdManager(MainActivity mainActivity) {
        super(mainActivity);
        this.revmob = new RevMobAdService(activity);
        adService(revmob);
    }

    @Override
    public void cacheAd(AdType adType) {
        revmob.cacheAd(adType);
    }

    @Override
    public void showAd(AdType adType) {
        revmob.showAd(adType);
    }
}
