package com.tencent.component.os.info;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tencent.component.ComponentContext;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.NetworkManager;
import com.tencent.component.net.NetworkManager.NetStatusListener;

@PluginApi(a=6)
public class DeviceDash
  implements NetworkManager.NetStatusListener
{
  private static final DeviceDash a = new DeviceDash();
  private String b = null;

  public DeviceDash()
  {
    NetworkManager.a(this);
  }

  private String b()
  {
    StorageInfo localStorageInfo1 = StorageDash.d();
    StorageInfo localStorageInfo2 = StorageDash.c();
    Object[] arrayOfObject = new Object[2];
    String str1;
    if (localStorageInfo1 == null)
    {
      str1 = "N/A";
      arrayOfObject[0] = str1;
      if (localStorageInfo2 != null)
        break label55;
    }
    label55: for (String str2 = "N/A"; ; str2 = localStorageInfo2.toString())
    {
      arrayOfObject[1] = str2;
      return String.format("{IN : %s |EXT: %s}", arrayOfObject);
      str1 = localStorageInfo1.toString();
      break;
    }
  }

  @PluginApi(a=6)
  public static DeviceDash getInstance()
  {
    return a;
  }

  public String a()
  {
    Context localContext = ComponentContext.a();
    WindowManager localWindowManager = (WindowManager)localContext.getSystemService("window");
    TelephonyManager localTelephonyManager = (TelephonyManager)localContext.getSystemService("phone");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    StringBuilder localStringBuilder1 = new StringBuilder();
    try
    {
      String str2 = localTelephonyManager.getDeviceId();
      str1 = str2;
      localStringBuilder1.append("imei=").append(str1).append('&');
      localStringBuilder1.append("model=").append(Build.MODEL).append('&');
      localStringBuilder1.append("os=").append(Build.VERSION.RELEASE).append('&');
      localStringBuilder1.append("apilevel=").append(Build.VERSION.SDK_INT).append('&');
      localStringBuilder1.append("apn=").append(NetworkManager.a()).append('&');
      StringBuilder localStringBuilder2 = localStringBuilder1.append("sdcard=");
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        i = 1;
        localStringBuilder2.append(i).append('&');
        localStringBuilder1.append("sddouble=").append("0").append('&');
        localStringBuilder1.append("display=").append(localDisplayMetrics.widthPixels).append('*').append(localDisplayMetrics.heightPixels).append('&');
        localStringBuilder1.append("manu=").append(Build.MANUFACTURER).append('&');
        localStringBuilder1.append("storage=").append(b()).append('&');
        this.b = localStringBuilder1.toString();
        return this.b;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        String str1 = "N/A";
        continue;
        int i = 0;
      }
    }
  }

  public void a(String paramString1, String paramString2)
  {
    a();
  }

  @PluginApi(a=6)
  public String getDeviceInfo()
  {
    if ((this.b == null) || (this.b.length() < 1))
      return a();
    return this.b;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.os.info.DeviceDash
 * JD-Core Version:    0.6.0
 */