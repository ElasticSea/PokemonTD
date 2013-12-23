package com.pixelthieves.elementtd;

/**
 * Created by Tomas on 12/13/13.
 */
public class RevmobVungleAdManager extends AdManager {

    private final RevMobAdService revmob;
    private final VungleAdService vungle;

    public RevmobVungleAdManager(MainActivity mainActivity) {
        super(mainActivity);
        this.revmob = new RevMobAdService(activity);
        this.vungle = new VungleAdService(activity);
        adService(revmob);
        adService(vungle);
    }

    @Override
    public void cacheAd(AdType adType) {
        revmob.cacheAd(adType);
    }

    @Override
    public void showAd(AdType adType) {
        switch (adType) {
            case Interestial: revmob.showAd(adType);
                break;
            case MoreApps:
                break;
            case Video:  vungle.showAd(adType);
                break;
        }

    }
}
