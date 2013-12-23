package com.pixelthieves.elementtd;

import java.lang.ref.WeakReference;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

public class LocationValet
{
    private final WeakReference<ILocationValetListener> _valetListener;

    private final LocationManager _locationManager;
    private LocationListener _listener;

    private Location _lastGoodLocation;
    private boolean hasNetworkPermission;
    private boolean hasGpsPermission;

    public LocationValet(Context context, ILocationValetListener listener)
    {
        _valetListener = new WeakReference<LocationValet.ILocationValetListener>(listener);

        _locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        _listener = new ValetLocationListener();

        hasNetworkPermission = context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        hasGpsPermission = context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    public Boolean startAquire(Boolean immediateResult)
    {
        if(immediateResult)
        {
            if(hasGpsPermission)
                updateValetListeners(_locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
            else if(hasNetworkPermission)
                updateValetListeners(_locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
        }

        if(!Build.FINGERPRINT.startsWith("generic") && hasNetworkPermission)
            _locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 0, _listener);
        else if(hasGpsPermission)
            _locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, _listener);
        else
            return false;
        return true;
    }
    public void stopAquire()
    {
        _locationManager.removeUpdates(_listener);

    }

    private void updateValetListeners(Location l)
    {
        _lastGoodLocation = l;

        if(_valetListener.get() != null)
            _valetListener.get().onBetterLocationFound(l);
    }

    private class ValetLocationListener implements LocationListener
    {

        public void onLocationChanged(Location location)
        {
            if(isBetterLocation(location, _lastGoodLocation))
            {
                updateValetListeners(location);
            }
        }

        public void onProviderDisabled(String provider)
        {
        }

        public void onProviderEnabled(String provider)
        {
        }

        public void onStatusChanged(String provider, int status, Bundle extras)
        {
        }

    }

    private static final int TWO_MINUTES = 1000 * 60 * 2;

    /** Determines whether one Location reading is better than the current Location fix
     *
     * @param location
     *            The new Location that you want to evaluate
     * @param currentBestLocation
     *            The current Location fix, to which you want to compare the new one */
    protected boolean isBetterLocation(Location location, Location currentBestLocation)
    {
        if(currentBestLocation == null)
        {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if(isSignificantlyNewer)
        {
            return true;
            // If the new location is more than two minutes older, it must be worse
        }
        else if(isSignificantlyOlder)
        {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if(isMoreAccurate)
        {
            return true;
        }
        else if(isNewer && !isLessAccurate)
        {
            return true;
        }
        else if(isNewer && !isSignificantlyLessAccurate && isFromSameProvider)
        {
            return true;
        }
        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2)
    {
        if(provider1 == null)
        {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    public interface ILocationValetListener
    {
        void onBetterLocationFound(Location l);
    }
}