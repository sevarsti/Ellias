package com.tencent.tmassistantsdk.openSDK;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.b.b;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class a
{
  protected int a = 1;
  protected Context b = null;
  protected String c = null;
  protected int d = 0;
  protected ReferenceQueue e = new ReferenceQueue();
  protected ArrayList f = new ArrayList();
  protected b g = new b();

  public int a()
  {
    if (this.b == null)
      throw new Exception("you must initial openSDK,by calling initQQDownloaderOpenSDK method!");
    PackageManager localPackageManager = this.b.getPackageManager();
    if (localPackageManager != null)
      try
      {
        if (localPackageManager.getPackageInfo("com.tencent.android.qqdownloader", 0) != null)
        {
          int i = f.a().m();
          int j = this.a;
          int k = 0;
          if (j > i)
            k = 2;
          return k;
        }
        return 1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        l.b("BaseQQDownloaderOpenSDK", "packagename not found！");
      }
    return 1;
  }

  public long a(g paramg, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, int paramInt)
  {
    Map localMap;
    if (TextUtils.isEmpty(paramString2))
      localMap = a(paramg, paramBoolean1, paramBoolean2);
    while (true)
    {
      String str = a(paramInt, localMap);
      long l1 = System.currentTimeMillis();
      long l2 = l1 + 300000L;
      l.b("BaseQQDownloaderOpenSDK", "addDownloadTaskFromTaskList,hostPackageName=" + this.c + "; hostVersionCode=" + this.d + "; hostUserIdentity=" + "" + "; dataItemType=" + 0 + ";dataItemAction=" + str);
      return this.g.a(this.c, this.d, "", 0, str, l1, l2, 0, null);
      localMap = a(paramg, true, true);
      localMap.put("verifytype", paramString2);
    }
  }

  protected String a(int paramInt, Map paramMap)
  {
    String str1;
    String str2;
    Object localObject1;
    int i;
    label98: String str4;
    String str5;
    String str6;
    switch (paramInt)
    {
    default:
      str1 = "appdetails";
      str2 = "tpmast://" + str1 + "?";
      localObject1 = "";
      if ((paramMap != null) && (paramMap.size() > 0))
      {
        Iterator localIterator = paramMap.entrySet().iterator();
        i = 0;
        if (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          str4 = (String)localEntry.getKey();
          str5 = (String)localEntry.getValue();
          if ((TextUtils.isEmpty(str4)) || (TextUtils.isEmpty(str5)))
            break label310;
          if (i > 0)
            break;
          str6 = "";
        }
      }
    case 2:
    case 1:
    case 3:
    case 4:
    case 5:
    }
    label169: label310: for (Object localObject2 = (String)localObject1 + str6 + str4 + "=" + URLEncoder.encode(str5); ; localObject2 = localObject1)
    {
      i++;
      localObject1 = localObject2;
      break label98;
      str1 = "appdetails";
      break;
      str1 = "download";
      break;
      str1 = "appdetails";
      break;
      str1 = "updatedownload";
      break;
      str1 = "webview";
      break;
      str6 = "&";
      break label169;
      String str3 = str2 + (String)localObject1;
      l.b("BaseQQDownloaderOpenSDK", "path:" + str3);
      return a(str3);
    }
  }

  protected String a(String paramString)
  {
    String str = URLEncoder.encode(e.a(paramString));
    return "tmast://encrypt?encryptdata=" + str;
  }

  protected String a(boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = "";
    if ((paramBoolean1) && (paramBoolean2))
      str = "1;2";
    do
    {
      return str;
      if (paramBoolean2)
        return "2";
    }
    while (!paramBoolean1);
    return "1";
  }

  protected Map a(g paramg, boolean paramBoolean1, boolean paramBoolean2)
  {
    String str = a(paramBoolean1, paramBoolean2);
    HashMap localHashMap = new HashMap();
    localHashMap.put("hostpname", this.c);
    localHashMap.put("hostversioncode", String.valueOf(this.d));
    localHashMap.put("sngappid", paramg.a);
    localHashMap.put("appid", paramg.b);
    localHashMap.put("apkid", paramg.c);
    localHashMap.put("pname", paramg.f);
    localHashMap.put("via", paramg.e);
    localHashMap.put("uin", paramg.g);
    localHashMap.put("uintype", paramg.h);
    localHashMap.put("versioncode", String.valueOf(paramg.d));
    localHashMap.put("oplist", str);
    localHashMap.put("channelid", paramg.i);
    localHashMap.put("actionflag", paramg.j);
    localHashMap.put("sdkid", paramg.k);
    return localHashMap;
  }

  protected void a(g paramg, int paramInt1, int paramInt2, String paramString)
  {
    Iterator localIterator = this.f.iterator();
    while (localIterator.hasNext())
    {
      d locald = (d)((WeakReference)localIterator.next()).get();
      if (locald == null)
      {
        l.b("BaseQQDownloaderOpenSDK", "onDownloadStateChanged listener = null");
        continue;
      }
      locald.a(paramg, paramInt1, paramInt2, paramString);
    }
  }

  public boolean a(d paramd)
  {
    if (paramd == null)
      return false;
    while (true)
    {
      Reference localReference = this.e.poll();
      if (localReference == null)
        break;
      this.f.remove(localReference);
    }
    Iterator localIterator = this.f.iterator();
    while (localIterator.hasNext())
      if ((d)((WeakReference)localIterator.next()).get() == paramd)
        return true;
    WeakReference localWeakReference = new WeakReference(paramd, this.e);
    this.f.add(localWeakReference);
    return true;
  }

  public boolean b(d paramd)
  {
    if (paramd == null)
      return false;
    Iterator localIterator = this.f.iterator();
    while (localIterator.hasNext())
    {
      if ((d)((WeakReference)localIterator.next()).get() != paramd)
        continue;
      localIterator.remove();
      return true;
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.a
 * JD-Core Version:    0.6.0
 */