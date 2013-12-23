package com.pixelthieves.elementtd.ads;

import com.chartboost.sdk.ChartboostDelegate;
import com.pixelthieves.core.services.AdHandler;

/**
 * Created by Tomas on 12/11/13.
 */
public class CustomChartboostDelegate implements ChartboostDelegate {
    private final AdHandler adHandler;

    public CustomChartboostDelegate(AdHandler adHandler) {
        this.adHandler = adHandler;
    }

    /*
         * Chartboost delegate methods
		 *
		 * Implement the delegate methods below to finely control Chartboost's behavior in your app
		 *
		 * Minimum recommended: shouldDisplayInterstitial()
		 */


    /*
     * shouldDisplayInterstitial(String location)
     *
     * This is used to control when an interstitial should or should not be displayed
     * If you should not display an interstitial, return false
     *
     * For example: during gameplay, return false.
     *
     * Is fired on:
     * - showInterstitial()
     * - Interstitial is loaded & ready to display
     */
    @Override
    public boolean shouldDisplayInterstitial(String location) {
        return true;
    }

    /*
     * shouldRequestInterstitial(String location)
     *
     * This is used to control when an interstitial should or should not be requested
     * If you should not request an interstitial from the server, return false
     *
     * For example: user should not see interstitials for some reason, return false.
     *
     * Is fired on:
     * - cacheInterstitial()
     * - showInterstitial() if no interstitial is cached
     *
     * Notes:
     * - We do not recommend excluding purchasers with this delegate method
     * - Instead, use an exclusion list on your campaign so you can control it on the fly
     */
    @Override
    public boolean shouldRequestInterstitial(String location) {
        return true;
    }

    /*
     * didCacheInterstitial(String location)
     *
     * Passes in the location name that has successfully been cached
     *
     * Is fired on:
     * - cacheInterstitial() success
     * - All assets are loaded
     *
     * Notes:
     * - Similar to this is: cb.hasCachedInterstitial(String location)
     * Which will return true if a cached interstitial exists for that location
     */
    @Override
    public void didCacheInterstitial(String location) {
    }

    /*
     * didFailToLoadInterstitial(String location)
     *
     * This is called when an interstitial has failed to load for any reason
     *
     * Is fired on:
     * - cacheInterstitial() failure
     * - showInterstitial() failure if no interstitial was cached
     *
     * Possible reasons:
     * - No network connection
     * - No publishing campaign matches for this user (go make a new one in the dashboard)
     */
    @Override
    public void didFailToLoadInterstitial(String location) {
        adHandler.onAdFailed(
                "This is called when an interstitial has failed to load for any reason[ " + location + "]");
    }

    /*
     * didDismissInterstitial(String location)
     *
     * This is called when an interstitial is dismissed
     *
     * Is fired on:
     * - Interstitial click
     * - Interstitial close
     *
     * #Pro Tip: Use the delegate method below to immediately re-cache interstitials
     */
    @Override
    public void didDismissInterstitial(String location) {
    }

    /*
     * didCloseInterstitial(String location)
     *
     * This is called when an interstitial is closed
     *
     * Is fired on:
     * - Interstitial close
     */
    @Override
    public void didCloseInterstitial(String location) {
        adHandler.onAdClosed();
    }

    /*
     * didClickInterstitial(String location)
     *
     * This is called when an interstitial is clicked
     *
     * Is fired on:
     * - Interstitial click
     */
    @Override
    public void didClickInterstitial(String location) {
        adHandler.onAdClicked();
    }

    /*
     * didShowInterstitial(String location)
     *
     * This is called when an interstitial has been successfully shown
     *
     * Is fired on:
     * - showInterstitial() success
     */
    @Override
    public void didShowInterstitial(String location) {
    }

    /*
     * didFailToLoadURL(String location)
     *
     * This is called when a url after a click has failed to load for any reason
     *
     * Is fired on:
     * - Interstitial click
     * - More-Apps click
     *
     * Possible reasons:
     * - No network connection
     * - no valid activity to launch
     * - unable to parse url
     */
    @Override
    public void didFailToLoadUrl(String url) {
        adHandler.onAdFailed("This is called when a url after a click has failed to load for any reason [" + url + "]");
    }

		/*
         * More Apps delegate methods
		 */

    /*
     * shouldDisplayLoadingViewForMoreApps()
     *
     * Return false to prevent the pretty More-Apps loading screen
     *
     * Is fired on:
     * - showMoreApps()
     */
    @Override
    public boolean shouldDisplayLoadingViewForMoreApps() {
        return true;
    }

    /*
     * shouldRequestMoreApps()
     *
     * Return false to prevent a More-Apps page request
     *
     * Is fired on:
     * - cacheMoreApps()
     * - showMoreApps() if no More-Apps page is cached
     */
    @Override
    public boolean shouldRequestMoreApps() {
        return true;
    }

    /*
     * shouldDisplayMoreApps()
     *
     * Return false to prevent the More-Apps page from displaying
     *
     * Is fired on:
     * - showMoreApps()
     * - More-Apps page is loaded & ready to display
     */
    @Override
    public boolean shouldDisplayMoreApps() {
        return true;
    }

    /*
     * didFailToLoadMoreApps()
     *
     * This is called when the More-Apps page has failed to load for any reason
     *
     * Is fired on:
     * - cacheMoreApps() failure
     * - showMoreApps() failure if no More-Apps page was cached
     *
     * Possible reasons:
     * - No network connection
     * - No publishing campaign matches for this user (go make a new one in the dashboard)
     */
    @Override
    public void didFailToLoadMoreApps() {
        adHandler.onAdFailed("This is called when the More-Apps page has failed to load for any reason");
    }

    /*
     * didCacheMoreApps()
     *
     * Is fired on:
     * - cacheMoreApps() success
     * - All assets are loaded
     */
    @Override
    public void didCacheMoreApps() {
    }

    /*
     * didDismissMoreApps()
     *
     * This is called when the More-Apps page is dismissed
     *
     * Is fired on:
     * - More-Apps click
     * - More-Apps close
     */
    @Override
    public void didDismissMoreApps() {
    }

    /*
     * didCloseMoreApps()
     *
     * This is called when the More-Apps page is closed
     *
     * Is fired on:
     * - More-Apps close
     */
    @Override
    public void didCloseMoreApps() {
        adHandler.onAdClosed();
    }

    /*
     * didClickMoreApps()
     *
     * This is called when the More-Apps page is clicked
     *
     * Is fired on:
     * - More-Apps click
     */
    @Override
    public void didClickMoreApps() {
        adHandler.onAdClicked();
    }

    /*
     * didShowMoreApps()
     *
     * This is called when the More-Apps page has been successfully shown
     *
     * Is fired on:
     * - showMoreApps() success
     */
    @Override
    public void didShowMoreApps() {
    }

    /*
     * shouldRequestInterstitialsInFirstSession()
     *
     * Return false if the user should not request interstitials until the 2nd startSession()
     *
     */
    @Override
    public boolean shouldRequestInterstitialsInFirstSession() {
        return true;
    }
}
