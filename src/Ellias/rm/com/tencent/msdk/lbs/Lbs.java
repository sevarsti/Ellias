package com.tencent.msdk.lbs;

import android.app.Activity;
import com.tencent.msdk.WeGameNotifyGame;
import com.tencent.msdk.handle.MsdkThreadManager;
import com.tencent.msdk.remote.api.RelationRet;
import com.tencent.msdk.tools.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;

public class Lbs
{
  private Activity context;

  public Lbs(Activity paramActivity)
  {
    this.context = paramActivity;
  }

  public void getNearbyPlayer()
  {
    LocationService.Init(this.context);
    LocationService.getInstance().startLocating(new LocationService.LocatorListener()
    {
      public void onFail(int paramInt)
      {
        Logger.d("get location failed: " + paramInt);
        RelationRet localRelationRet = new RelationRet();
        if (paramInt == 0)
        {
          localRelationRet.flag = -4;
          localRelationRet.desc = "location service is closed!";
        }
        while (true)
        {
          WeGameNotifyGame.getInstance().OnGetNearbyPlayerCallback(localRelationRet);
          return;
          if (paramInt != 1)
            continue;
          localRelationRet.flag = -5;
          localRelationRet.desc = "get location failed";
        }
      }

      public void onSuccess(int paramInt)
      {
        Logger.d("" + paramInt);
        LocationInfo localLocationInfo = new LocationInfo();
        JSONArray localJSONArray1 = new JSONArray();
        Iterator localIterator1 = LocationService.getInstance().cellInfoList.iterator();
        while (localIterator1.hasNext())
        {
          CellIDInfo localCellIDInfo = (CellIDInfo)localIterator1.next();
          LocationInfo.Cell localCell = new LocationInfo.Cell();
          localCell.setCellid(localCellIDInfo.cellId);
          localCell.setLac(localCellIDInfo.locationAreaCode);
          localCell.setMcc(localCellIDInfo.mobileCountryCode);
          localCell.setMnc(localCellIDInfo.mobileNetworkCode);
          localCell.setRssi(localCellIDInfo.rssi);
          localJSONArray1.put(localCell);
        }
        localLocationInfo.location.setLatitude(LocationService.getInstance().getLatitude());
        localLocationInfo.location.setLongitude(LocationService.getInstance().getLongitude());
        localLocationInfo.location.setAdditional("0");
        localLocationInfo.cells = localJSONArray1;
        JSONArray localJSONArray2 = new JSONArray();
        Iterator localIterator2 = LocationService.getInstance().getWifiInfoList().iterator();
        while (localIterator2.hasNext())
        {
          WifiInfo localWifiInfo = (WifiInfo)localIterator2.next();
          LocationInfo.Wifi localWifi = new LocationInfo.Wifi();
          localWifi.setMac(localWifiInfo.mac);
          localWifi.setRssi(localWifiInfo.rssi);
          localJSONArray2.put(localWifi);
        }
        localLocationInfo.wifis = localJSONArray2;
        MsdkThreadManager.getInstance().getNearbyPlayer(localLocationInfo);
      }
    });
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.lbs.Lbs
 * JD-Core Version:    0.6.0
 */