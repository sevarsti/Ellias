package com.tencent.msdk.lbs;

import android.app.Activity;
import android.telephony.TelephonyManager;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.api.LoginRet;
import com.tencent.msdk.api.WGPlatform;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class LocationInfo
{
  String access_token = "";
  int address = 0;
  Attribute attribute = new Attribute();
  JSONArray cells = new JSONArray();
  Location location = new Location();
  int source = 0;
  String version = "";
  JSONArray wifis = new JSONArray();

  public LocationInfo()
  {
    LoginRet localLoginRet = new LoginRet();
    WGPlatform.WGGetLoginRecord(localLoginRet);
    this.access_token = localLoginRet.getAccessToken();
    this.address = 0;
    this.version = "0.1.0";
    this.source = 12345;
    TelephonyManager localTelephonyManager = (TelephonyManager)WeGame.getInstance().getActivity().getSystemService("phone");
    String str1 = localTelephonyManager.getDeviceId();
    String str2 = localTelephonyManager.getSubscriberId();
    this.attribute.setImei(str1);
    this.attribute.setImsi(str2);
    this.attribute.setPhonenum(localTelephonyManager.getLine1Number());
    this.attribute.setQq("10000");
  }

  public String toString()
  {
    try
    {
      String str = new JSONStringer().object().key("version").value(this.version).key("address").value(this.address).key("source").value(this.source).key("access_token").value(this.access_token).key("attribute").value(this.attribute).key("location").value(this.location).key("cells").value(this.cells).key("wifis").value(this.wifis).endObject().toString();
      return str;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return "";
  }

  public static class Attribute extends JSONObject
  {
    private String imei = "";
    private String imsi = "";
    private String phonenum = "";
    private String qq = "";

    public String getImei()
    {
      return this.imei;
    }

    public String getImsi()
    {
      return this.imsi;
    }

    public String getPhonenum()
    {
      return this.phonenum;
    }

    public String getQq()
    {
      return this.qq;
    }

    public void setImei(String paramString)
    {
      try
      {
        put("imei", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setImsi(String paramString)
    {
      try
      {
        put("imsi", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setPhonenum(String paramString)
    {
      try
      {
        put("phonenum", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setQq(String paramString)
    {
      try
      {
        put("qq", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }

  public static class Cell extends JSONObject
  {
    private int cellid = 0;
    private int lac = 0;
    private int mcc = 0;
    private int mnc = 0;
    private int rssi = 0;

    public int getCellid()
    {
      return this.cellid;
    }

    public int getLac()
    {
      return this.lac;
    }

    public int getMcc()
    {
      return this.mcc;
    }

    public int getMnc()
    {
      return this.mnc;
    }

    public int getRssi()
    {
      return this.rssi;
    }

    public void setCellid(int paramInt)
    {
      try
      {
        put("cellid", paramInt);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setLac(int paramInt)
    {
      try
      {
        put("lac", paramInt);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setMcc(int paramInt)
    {
      try
      {
        put("mcc", paramInt);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setMnc(int paramInt)
    {
      try
      {
        put("mnc", paramInt);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setRssi(int paramInt)
    {
      try
      {
        put("rss", paramInt);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }

  public static class Location extends JSONObject
  {
    private String additional = "";
    private double latitude = 0.0D;
    private double longitude = 0.0D;

    public String getAdditional()
    {
      return this.additional;
    }

    public double getLatitude()
    {
      return this.latitude;
    }

    public double getLongitude()
    {
      return this.longitude;
    }

    public void setAdditional(String paramString)
    {
      try
      {
        put("additional", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setLatitude(double paramDouble)
    {
      try
      {
        put("latitude", paramDouble);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setLongitude(double paramDouble)
    {
      try
      {
        put("longitude", paramDouble);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }

  public static class Wifi extends JSONObject
  {
    private String mac = "";
    private int rssi;

    public String getMac()
    {
      return this.mac;
    }

    public int getRssi()
    {
      return this.rssi;
    }

    public void setMac(String paramString)
    {
      try
      {
        put("mac", paramString);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }

    public void setRssi(int paramInt)
    {
      try
      {
        put("rssi", paramInt);
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.lbs.LocationInfo
 * JD-Core Version:    0.6.0
 */