package com.tencent.msdk.lbs;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

class MyLocationListener
  implements LocationListener
{
  private LocationManager mLocationManager;
  private LocationService mLocationService;

  public MyLocationListener(LocationService paramLocationService, LocationManager paramLocationManager)
  {
    this.mLocationService = paramLocationService;
    this.mLocationManager = paramLocationManager;
  }

  public void onLocationChanged(Location paramLocation)
  {
    Log.d("LocationService", "GPS获取成功");
    Log.d("LocationService", "经度:" + paramLocation.getLongitude());
    Log.d("LocationService", "纬度:" + paramLocation.getLatitude());
    this.mLocationService.setLongitude(paramLocation.getLongitude());
    this.mLocationService.setLatitude(paramLocation.getLatitude());
    this.mLocationService.onSuccess(1);
    this.mLocationManager.removeUpdates(this);
  }

  public void onProviderDisabled(String paramString)
  {
    Log.d("LocationService", "onProviderDisabled");
  }

  public void onProviderEnabled(String paramString)
  {
    Log.d("LocationService", "onProviderEnabled");
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.lbs.MyLocationListener
 * JD-Core Version:    0.6.0
 */