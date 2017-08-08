package com.tencent.open.yyb;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.tencent.a.a.d;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.Util;
import com.tencent.utils.Util.Statistic;
import org.json.JSONObject;

public class b
{
  public static Drawable a(String paramString, Context paramContext)
  {
    return a(paramString, paramContext, new Rect(0, 0, 0, 0));
  }

  // ERROR //
  public static Drawable a(String paramString, Context paramContext, Rect paramRect)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 26	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: astore_3
    //   5: aload_3
    //   6: invokevirtual 30	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   9: astore 4
    //   11: aload 4
    //   13: aload_0
    //   14: invokevirtual 36	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   17: astore 12
    //   19: aload 12
    //   21: astore 6
    //   23: aload 6
    //   25: ifnonnull +24 -> 49
    //   28: aload 6
    //   30: ifnull +8 -> 38
    //   33: aload 6
    //   35: invokevirtual 41	java/io/InputStream:close	()V
    //   38: aconst_null
    //   39: areturn
    //   40: astore 18
    //   42: aload 18
    //   44: invokevirtual 44	java/io/IOException:printStackTrace	()V
    //   47: aconst_null
    //   48: areturn
    //   49: aload_0
    //   50: ldc 46
    //   52: invokevirtual 52	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   55: ifeq +75 -> 130
    //   58: aload 6
    //   60: invokestatic 58	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   63: astore 15
    //   65: aload 15
    //   67: ifnull +42 -> 109
    //   70: aload 15
    //   72: invokevirtual 64	android/graphics/Bitmap:getNinePatchChunk	()[B
    //   75: astore 16
    //   77: new 66	android/graphics/drawable/NinePatchDrawable
    //   80: dup
    //   81: aload_3
    //   82: invokevirtual 70	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   85: aload 15
    //   87: aload 16
    //   89: aload_2
    //   90: aconst_null
    //   91: invokespecial 73	android/graphics/drawable/NinePatchDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;[BLandroid/graphics/Rect;Ljava/lang/String;)V
    //   94: astore 10
    //   96: aload 6
    //   98: ifnull +8 -> 106
    //   101: aload 6
    //   103: invokevirtual 41	java/io/InputStream:close	()V
    //   106: aload 10
    //   108: areturn
    //   109: aload 6
    //   111: ifnull -73 -> 38
    //   114: aload 6
    //   116: invokevirtual 41	java/io/InputStream:close	()V
    //   119: aconst_null
    //   120: areturn
    //   121: astore 17
    //   123: aload 17
    //   125: invokevirtual 44	java/io/IOException:printStackTrace	()V
    //   128: aconst_null
    //   129: areturn
    //   130: aload 6
    //   132: aload_0
    //   133: invokestatic 79	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   136: astore 13
    //   138: aload 13
    //   140: astore 10
    //   142: goto -46 -> 96
    //   145: astore 14
    //   147: aload 14
    //   149: invokevirtual 44	java/io/IOException:printStackTrace	()V
    //   152: goto -46 -> 106
    //   155: astore 8
    //   157: aconst_null
    //   158: astore 9
    //   160: aload 8
    //   162: invokevirtual 44	java/io/IOException:printStackTrace	()V
    //   165: ldc 81
    //   167: ldc 83
    //   169: invokestatic 89	com/tencent/a/a/d:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   172: aload 9
    //   174: ifnull +78 -> 252
    //   177: aload 9
    //   179: invokevirtual 41	java/io/InputStream:close	()V
    //   182: aconst_null
    //   183: astore 10
    //   185: goto -79 -> 106
    //   188: astore 11
    //   190: aload 11
    //   192: invokevirtual 44	java/io/IOException:printStackTrace	()V
    //   195: aconst_null
    //   196: astore 10
    //   198: goto -92 -> 106
    //   201: astore 5
    //   203: aconst_null
    //   204: astore 6
    //   206: aload 6
    //   208: ifnull +8 -> 216
    //   211: aload 6
    //   213: invokevirtual 41	java/io/InputStream:close	()V
    //   216: aload 5
    //   218: athrow
    //   219: astore 7
    //   221: aload 7
    //   223: invokevirtual 44	java/io/IOException:printStackTrace	()V
    //   226: goto -10 -> 216
    //   229: astore 5
    //   231: goto -25 -> 206
    //   234: astore 5
    //   236: aload 9
    //   238: astore 6
    //   240: goto -34 -> 206
    //   243: astore 8
    //   245: aload 6
    //   247: astore 9
    //   249: goto -89 -> 160
    //   252: aconst_null
    //   253: astore 10
    //   255: goto -149 -> 106
    //
    // Exception table:
    //   from	to	target	type
    //   33	38	40	java/io/IOException
    //   114	119	121	java/io/IOException
    //   101	106	145	java/io/IOException
    //   11	19	155	java/io/IOException
    //   177	182	188	java/io/IOException
    //   11	19	201	finally
    //   211	216	219	java/io/IOException
    //   49	65	229	finally
    //   70	96	229	finally
    //   130	138	229	finally
    //   160	172	234	finally
    //   49	65	243	java/io/IOException
    //   70	96	243	java/io/IOException
    //   130	138	243	java/io/IOException
  }

  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    CookieSyncManager.createInstance(paramContext);
    CookieManager localCookieManager = CookieManager.getInstance();
    localCookieManager.setAcceptCookie(true);
    boolean bool = Uri.parse(paramString1).getHost().toLowerCase().endsWith(".qq.com");
    String str = null;
    if (bool)
      str = ".qq.com";
    localCookieManager.setCookie(paramString1, b("logintype", "MOBILEQ", str));
    localCookieManager.setCookie(paramString1, b("qopenid", paramString2, str));
    localCookieManager.setCookie(paramString1, b("qaccesstoken", paramString3, str));
    localCookieManager.setCookie(paramString1, b("openappid", paramString4, str));
    CookieSyncManager.getInstance().sync();
  }

  public static void a(String paramString1, String paramString2, String paramString3)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("uin", "1000");
    localBundle.putString("action", paramString2);
    localBundle.putString("appid", paramString1);
    localBundle.putString("via", paramString3);
    new b(null).execute(new Bundle[] { localBundle });
  }

  private static String b(String paramString1, String paramString2, String paramString3)
  {
    String str1 = paramString1 + "=" + paramString2;
    if (paramString3 != null)
    {
      String str2 = str1 + "; path=/";
      str1 = str2 + "; domain=" + paramString3;
    }
    return str1;
  }

  public static class a
  {
    public String a;
    public String b;
    public long c;
  }

  private static class b extends AsyncTask<Bundle, Void, Void>
  {
    protected Void a(Bundle[] paramArrayOfBundle)
    {
      if (paramArrayOfBundle == null)
        return null;
      try
      {
        int i = Util.parseJson(HttpUtils.openUrl2(null, "http://analy.qq.com/cgi-bin/mapp_apptrace", "GET", paramArrayOfBundle[0]).response).getInt("ret");
        d.b("openSDK_LOG", "-->(ViaAsyncTask)doInBackground : ret = " + i);
        return null;
      }
      catch (Exception localException)
      {
        d.b("openSDK_LOG", "-->(ViaAsyncTask)doInBackground : Exception = " + localException.toString());
        localException.printStackTrace();
      }
      return null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.b
 * JD-Core Version:    0.6.0
 */