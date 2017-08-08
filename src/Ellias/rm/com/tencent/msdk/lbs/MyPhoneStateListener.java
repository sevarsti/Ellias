package com.tencent.msdk.lbs;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.ArrayList;

class MyPhoneStateListener extends PhoneStateListener
{
  private LocationService mLocationService;
  private TelephonyManager mTelephonyManager;

  public MyPhoneStateListener(LocationService paramLocationService, TelephonyManager paramTelephonyManager)
  {
    this.mLocationService = paramLocationService;
    this.mTelephonyManager = paramTelephonyManager;
  }

  public void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
  {
    super.onSignalStrengthsChanged(paramSignalStrength);
    if (paramSignalStrength.isGsm())
    {
      Log.d("LocationService", "onSignalStrengthsChanged gsm");
      if (paramSignalStrength.getGsmSignalStrength() != 99)
        ((CellIDInfo)this.mLocationService.cellInfoList.get(0)).rssi = (-113 + 2 * paramSignalStrength.getGsmSignalStrength());
    }
    while (true)
    {
      Log.d("LocationService", "signalStrengthValue = " + ((CellIDInfo)this.mLocationService.cellInfoList.get(0)).rssi);
      LocationService localLocationService = this.mLocationService;
      this.mLocationService.getClass();
      localLocationService.cellInfoStatus = 0;
      this.mTelephonyManager.listen(this, 0);
      return;
      ((CellIDInfo)this.mLocationService.cellInfoList.get(0)).rssi = paramSignalStrength.getGsmSignalStrength();
      continue;
      Log.d("LocationService", "onSignalStrengthsChanged cdma");
      ((CellIDInfo)this.mLocationService.cellInfoList.get(0)).rssi = paramSignalStrength.getCdmaDbm();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.lbs.MyPhoneStateListener
 * JD-Core Version:    0.6.0
 */