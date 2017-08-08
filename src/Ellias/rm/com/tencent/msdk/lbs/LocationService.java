package com.tencent.msdk.lbs;

import android.app.Activity;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;
import java.util.List;

public class LocationService
{
  static final int ERROR_GPS_TIMEOUT = 1;
  static final int ERROR_NOT_OPEN_GPS = 0;
  protected static final long GPS_TIMEOUT = 10000L;
  static final int RET_CODE_HAS_GPS_DATA = 1;
  static final int RET_CODE_NO_GPS_DATA;
  private static Activity sActivity;
  private static LocationService sInstance;
  final int STATUS_FAIL = 1;
  final int STATUS_SUCCESS = 0;
  ArrayList<CellIDInfo> cellInfoList;
  int cellInfoStatus;
  int gpsStatus;
  private double latitude;
  private LocatorListener listener;
  private double longitude;
  ArrayList<WifiInfo> wifiInfoList;
  int wifiStatus;

  private void GPSLocation()
  {
    LocationManager localLocationManager = (LocationManager)sActivity.getSystemService("location");
    try
    {
      if (localLocationManager.isProviderEnabled("gps"))
      {
        MyLocationListener localMyLocationListener = new MyLocationListener(sInstance, localLocationManager);
        Log.d("LocationService", "StartLocating");
        sActivity.runOnUiThread(new Runnable(localLocationManager, localMyLocationListener)
        {
          public void run()
          {
            this.val$locationManager.requestLocationUpdates("gps", 2000L, 100.0F, this.val$locationListener);
          }
        });
        sActivity.runOnUiThread(new Runnable(localLocationManager, localMyLocationListener)
        {
          public void run()
          {
            Handler localHandler = new Handler();
            localHandler.postDelayed(new Runnable(localHandler)
            {
              public void run()
              {
                Log.d("LocationService", "获取GPS超时");
                LocationService.this.gpsStatus = 1;
                LocationService.2.this.val$locationManager.removeUpdates(LocationService.2.this.val$locationListener);
                if ((LocationService.this.wifiStatus == 0) || (LocationService.this.cellInfoStatus == 0))
                {
                  Log.d("LocationService", "成功获取位置信息，但没有GPS数据");
                  LocationService.sInstance.onSuccess(0);
                }
                while (true)
                {
                  this.val$handler.removeCallbacks(this);
                  LocationService.2.this.val$locationManager.removeUpdates(LocationService.2.this.val$locationListener);
                  return;
                  if ((LocationService.this.wifiStatus != 1) || (LocationService.this.cellInfoStatus != 1))
                    continue;
                  Log.d("LocationService", "获取位置信息失败，GPS数据超时");
                  LocationService.sInstance.onFail(1);
                }
              }
            }
            , 10000L);
          }
        });
        return;
      }
      Log.d("LocationService", "GPS未开启");
      if ((this.wifiStatus == 0) || (this.cellInfoStatus == 0))
      {
        Log.d("LocationService", "成功获取位置信息，但没有GPS数据");
        sInstance.onSuccess(0);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Log.d("LocationService", "GPS获取异常");
      if ((this.wifiStatus == 0) || (this.cellInfoStatus == 0))
      {
        Log.d("LocationService", "成功获取位置信息，但没有GPS数据");
        sInstance.onSuccess(0);
        return;
        if ((this.wifiStatus == 1) && (this.cellInfoStatus == 1))
        {
          Log.d("LocationService", "获取位置信息失败，GPS数据超时");
          sInstance.onFail(0);
          return;
        }
      }
      else if ((this.wifiStatus == 1) && (this.cellInfoStatus == 1))
      {
        Log.d("LocationService", "获取位置信息失败，GPS数据超时");
        sInstance.onFail(1);
      }
    }
  }

  public static void Init(Activity paramActivity)
  {
    sActivity = paramActivity;
    if (sInstance == null)
      sInstance = new LocationService();
  }

  private void getCellIDInfo()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)sActivity.getSystemService("phone");
    if (localTelephonyManager == null)
    {
      Log.d("LocationService", "基站信息获取失败");
      this.cellInfoStatus = 1;
      return;
    }
    this.cellInfoList = new ArrayList();
    CellIDInfo localCellIDInfo1 = new CellIDInfo();
    int i = localTelephonyManager.getNetworkType();
    int j;
    int m;
    int n;
    List localList1;
    int i1;
    int i2;
    if ((i == 1) || (i == 2) || (i == 8) || (i == 3) || (i == 9) || (i == 10))
    {
      GsmCellLocation localGsmCellLocation = (GsmCellLocation)localTelephonyManager.getCellLocation();
      if (localGsmCellLocation == null)
      {
        Log.d("LocationService", "GsmCellLocation is null!!!");
        this.cellInfoStatus = 1;
        return;
      }
      j = 1;
      int k = localGsmCellLocation.getLac();
      m = Integer.valueOf(localTelephonyManager.getNetworkOperator().substring(0, 3)).intValue();
      n = Integer.valueOf(localTelephonyManager.getNetworkOperator().substring(3, 5)).intValue();
      localCellIDInfo1.cellId = localGsmCellLocation.getCid();
      localCellIDInfo1.mobileCountryCode = m;
      localCellIDInfo1.mobileNetworkCode = n;
      localCellIDInfo1.locationAreaCode = k;
      localCellIDInfo1.radioType = "gsm";
      localCellIDInfo1.rssi = 65535;
      Log.d("LocationService", localCellIDInfo1.toString());
      this.cellInfoList.add(localCellIDInfo1);
      localList1 = localTelephonyManager.getNeighboringCellInfo();
      i1 = localList1.size();
      Log.d("LocationService", "NeighboringCellInfo size = " + i1);
      i2 = 0;
    }
    while (i2 < i1)
    {
      CellIDInfo localCellIDInfo2 = new CellIDInfo();
      localCellIDInfo2.cellId = ((NeighboringCellInfo)localList1.get(i2)).getCid();
      localCellIDInfo2.mobileCountryCode = m;
      localCellIDInfo2.mobileNetworkCode = n;
      localCellIDInfo2.locationAreaCode = ((NeighboringCellInfo)localList1.get(i2)).getLac();
      localCellIDInfo2.rssi = ((NeighboringCellInfo)localList1.get(i2)).getRssi();
      Log.d("LocationService", "neighbor" + i2 + ":" + localCellIDInfo2.toString());
      this.cellInfoList.add(localCellIDInfo2);
      i2++;
      continue;
      if ((i != 4) && (i != 5) && (i != 6))
      {
        j = 0;
        if (i != 7)
          break;
      }
      else
      {
        CdmaCellLocation localCdmaCellLocation = (CdmaCellLocation)localTelephonyManager.getCellLocation();
        if (localCdmaCellLocation == null)
        {
          Log.d("LocationService", "CdmaCellLocation is null!!!");
          this.cellInfoStatus = 1;
          return;
        }
        j = 1;
        int i3 = localCdmaCellLocation.getNetworkId();
        int i4 = Integer.valueOf(localTelephonyManager.getNetworkOperator().substring(0, 3)).intValue();
        int i5 = localCdmaCellLocation.getSystemId();
        localCellIDInfo1.cellId = localCdmaCellLocation.getBaseStationId();
        localCellIDInfo1.mobileCountryCode = i4;
        localCellIDInfo1.mobileNetworkCode = i5;
        localCellIDInfo1.locationAreaCode = i3;
        localCellIDInfo1.radioType = "cdma";
        Log.d("LocationService", localCellIDInfo1.toString());
        this.cellInfoList.add(localCellIDInfo1);
        List localList2 = localTelephonyManager.getNeighboringCellInfo();
        int i6 = localList2.size();
        Log.d("LocationService", "NeighboringCellInfo size = " + i6);
        for (int i7 = 0; i7 < i6; i7++)
        {
          CellIDInfo localCellIDInfo3 = new CellIDInfo();
          localCellIDInfo3.cellId = ((NeighboringCellInfo)localList2.get(i7)).getCid();
          localCellIDInfo3.mobileCountryCode = i4;
          localCellIDInfo3.mobileNetworkCode = i5;
          localCellIDInfo3.locationAreaCode = i3;
          Log.d("LocationService", "neighbor" + i7 + ":" + localCellIDInfo3.toString());
          this.cellInfoList.add(localCellIDInfo3);
        }
      }
    }
    if (j == 1)
    {
      Activity localActivity = sActivity;
      3 local3 = new Runnable(localTelephonyManager)
      {
        public void run()
        {
          MyPhoneStateListener localMyPhoneStateListener = new MyPhoneStateListener(LocationService.sInstance, this.val$manager);
          this.val$manager.listen(localMyPhoneStateListener, 256);
        }
      };
      localActivity.runOnUiThread(local3);
      return;
    }
    this.cellInfoStatus = 1;
  }

  public static LocationService getInstance()
  {
    return sInstance;
  }

  private void getWifiInfo()
  {
    WifiManager localWifiManager = (WifiManager)sActivity.getSystemService("wifi");
    if (localWifiManager == null)
    {
      Log.d("LocationService", "WifiManager为空");
      this.wifiStatus = 1;
      return;
    }
    this.wifiInfoList = new ArrayList();
    List localList = localWifiManager.getScanResults();
    if (localList == null)
    {
      Log.d("LocationService", "未扫描到周围的WIFI热点");
      this.wifiStatus = 1;
      return;
    }
    int i = localList.size();
    Log.d("LocationService", "getScanResults size = " + i);
    for (int j = 0; j < i; j++)
    {
      WifiInfo localWifiInfo = new WifiInfo();
      localWifiInfo.mac = ((ScanResult)localList.get(j)).BSSID;
      localWifiInfo.rssi = ((ScanResult)localList.get(j)).level;
      Log.d("LocationService", "ScanResult" + j + ":" + localWifiInfo.toString());
      this.wifiInfoList.add(localWifiInfo);
    }
    if (i == 0)
    {
      this.wifiStatus = 1;
      return;
    }
    this.wifiStatus = 0;
  }

  public ArrayList<CellIDInfo> getCellInfoList()
  {
    return this.cellInfoList;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public ArrayList<WifiInfo> getWifiInfoList()
  {
    return this.wifiInfoList;
  }

  public void onFail(int paramInt)
  {
    Logger.d("onFail");
    if (this.listener != null)
      this.listener.onFail(paramInt);
  }

  public void onSuccess(int paramInt)
  {
    if (this.listener != null)
      this.listener.onSuccess(paramInt);
  }

  public void setLatitude(double paramDouble)
  {
    this.latitude = paramDouble;
  }

  public void setLongitude(double paramDouble)
  {
    this.longitude = paramDouble;
  }

  public void startLocating(LocatorListener paramLocatorListener)
  {
    Log.d("LocationService", "Start location");
    this.listener = paramLocatorListener;
    getCellIDInfo();
    getWifiInfo();
    GPSLocation();
  }

  static abstract interface LocatorListener
  {
    public abstract void onFail(int paramInt);

    public abstract void onSuccess(int paramInt);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.lbs.LocationService
 * JD-Core Version:    0.6.0
 */