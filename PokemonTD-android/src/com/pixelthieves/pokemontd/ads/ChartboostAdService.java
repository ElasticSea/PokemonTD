package com.pixelthieves.pokemontd.ads;

import android.app.Activity;
import android.provider.Settings;
import android.util.Log;
import com.chartboost.sdk.Libraries.CBOrientation;
import com.pixelthieves.core.services.AdHandler;
import com.pixelthieves.core.services.AdService;

/**
 * Created by Tomas on 12/11/13.
 */
public class ChartboostAdService implements AdService {
    private Activity activity;

    public ChartboostAdService(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void setAdHandler(AdHandler handler) {
        this.cb.setDelegate(new CustomChartboostDelegate(handler));
    }

    @Override
    public void showAd(AdType adType) {
        switch (adType) {
            case Interestial:
                this.cb.showInterstitial();
                break;
            case MoreApps:
                this.cb.showMoreApps();
                break;
        }

       /* Log.i(TAG, "showInterstitial");
        String toastStr = "Loading Interstitial";
        if (cb.hasCachedInterstitial()) toastStr = "Loading Interstitial From Cache";
        Toast.makeText(this, toastStr, Toast.LENGTH_SHORT).show();    */
    }

    private void showInterestial() {

    }

    private void showMoreApps() {
    }


    // Create the Chartboost object
    private com.chartboost.sdk.Chartboost cb;

    // This is used to display Toast messages and is not necessary for your app
    private static final String TAG = "Chartboost";

    /*
     * Initialize Chartboost in your onCreate method
     *
     * Make sure you use your own App ID & App Signature
     * You can create these in the Chartboost dashboard (Apps > Add New App...)
     */

    public void onCreate(){
        /*
         * Configure Chartboost
		 *
		 * Copy/Paste the 4 lines below into your project
		 *
		 * cb.onCreate() should only be called once when your activity is created
		 * format: cb.onCreate(Context, String appId, String appSignature, ChartboostDelegate)
		 *
		 * If you are not using delegate methods, pass null into the 4th parameter
		 */
        this.cb = com.chartboost.sdk.Chartboost.sharedChartboost();
        String appId = "52a86d809ddc35732456e534";
        String appSignature = "a4771e3c163dda48b6a527c79a6272fec7c54f12";
        this.cb.onCreate(activity, appId, appSignature, null);

		/*
		 *  Use activities instead of a view
		 *
		 *  Implement cb.setImpressionsUseActivities(true);
		 *
		 *  Note:
		 *  - Be sure to add the activity to your AndroidManifest.xml
		 */
        this.cb.setOrientation(CBOrientation.LANDSCAPE);
        this.cb.setImpressionsUseActivities(true);

		/*
		 * Notify the beginning of a user session
		 *
		 * You may place cb.startSession() in onCreate(), onStart(), or onResume()
		 */

        //Pro Tip: Use code below to print Android ID in log:
        String android_id =
                Settings.Secure.getString(activity.getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.e(TAG, android_id);

    }

    /*
     * Add cb.onStart(this) to your activity's onStart() method
     *
     * If you do not have an onStart() method, create one using the code below
     */
    public void onStart() {
        this.cb.onStart(activity);
        this.cb.startSession();
    }

    /*
     * Add cb.onStop(this) to your activity's onStop() method
     *
     * If you do not have an onStop() method, create one using the code below
     */
    public void onStop() {
        this.cb.onStop(activity);
    }

    /*
     * Add cb.onDestroy(this) to your activity's onDestroy() method
     *
     * If you do not have an onDestroy() method, create one using the code below
     */
    public void onDestroy() {
        this.cb.onDestroy(activity);
    }

    @Override
    public void cacheAd(AdType adType) {

    }

}
