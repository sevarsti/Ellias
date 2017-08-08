package com.tenpay.tenpayplugin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import com.tenpay.a.c.a;
import java.io.File;

public class TenpayUtil
{
  private static TenpayCallback a;

  public static void gotoTenpay(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, byte[] paramArrayOfByte, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt2, String paramString9, String paramString10, int paramInt3, TenpayCallback paramTenpayCallback, ResultReceiver paramResultReceiver)
  {
    Intent localIntent = new Intent(paramContext, TenpayPluginActivity.class);
    localIntent.putExtra("uin", paramString1);
    localIntent.putExtra("skey", paramString2);
    localIntent.putExtra("skey_type", paramString3);
    localIntent.putExtra("token_id", paramString4);
    localIntent.putExtra("pay_type", paramInt1);
    localIntent.putExtra("image_id", paramArrayOfByte);
    localIntent.putExtra("amount_title", paramString5);
    localIntent.putExtra("amount", paramString6);
    localIntent.putExtra("amount_mark", paramString7);
    localIntent.putExtra("price", paramString8);
    localIntent.putExtra("vip_image_id", paramInt2);
    localIntent.putExtra("vip_price", paramString9);
    localIntent.putExtra("discount", paramString10);
    localIntent.putExtra("orientation", paramInt3);
    localIntent.putExtra("receiver", paramResultReceiver);
    a = paramTenpayCallback;
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public static void gotoTenpay(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, byte[] paramArrayOfByte, String paramString5, String paramString6, String paramString7, String paramString8, int paramInt2, String paramString9, String paramString10, TenpayCallback paramTenpayCallback, ResultReceiver paramResultReceiver)
  {
    Intent localIntent = new Intent(paramContext, TenpayPluginActivity.class);
    localIntent.putExtra("uin", paramString1);
    localIntent.putExtra("skey", paramString2);
    localIntent.putExtra("skey_type", paramString3);
    localIntent.putExtra("token_id", paramString4);
    localIntent.putExtra("pay_type", paramInt1);
    localIntent.putExtra("image_id", paramArrayOfByte);
    localIntent.putExtra("amount_title", paramString5);
    localIntent.putExtra("amount", paramString6);
    localIntent.putExtra("amount_mark", paramString7);
    localIntent.putExtra("price", paramString8);
    localIntent.putExtra("vip_image_id", paramInt2);
    localIntent.putExtra("vip_price", paramString9);
    localIntent.putExtra("discount", paramString10);
    localIntent.putExtra("receiver", paramResultReceiver);
    a = paramTenpayCallback;
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public static boolean hasCallback()
  {
    return true;
  }

  public static void onCallback(Activity paramActivity, int paramInt, Bundle paramBundle)
  {
    ((ResultReceiver)paramActivity.getIntent().getParcelableExtra("receiver")).send(paramInt, paramBundle);
  }

  public static boolean onTenpayInited(Activity paramActivity)
  {
    if (a != null)
    {
      a.onTenpayInited(paramActivity);
      return true;
    }
    return false;
  }

  public static void setTenpaySoPath(String paramString)
  {
    a.a = paramString;
    if (a.a != null)
      try
      {
        File localFile = new File(paramString);
        if (localFile.exists())
        {
          if (localFile.isDirectory())
            return;
          System.load(a.a);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayUtil
 * JD-Core Version:    0.6.0
 */