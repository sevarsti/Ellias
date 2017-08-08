package com.tencent.android.tpush.service.cache;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import com.tencent.android.tpush.common.f;
import com.tencent.android.tpush.data.a;
import com.tencent.android.tpush.data.b;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.data.OptStrategyList;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.channel.exception.NullReturnException;
import com.tencent.android.tpush.service.channel.protocol.AppInfo;
import com.tencent.android.tpush.service.channel.protocol.UnregInfo;
import com.tencent.android.tpush.service.i;
import com.tencent.mid.api.MidService;
import com.tencent.mid.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class CacheManager
{
  private static CacheManager a = new CacheManager();

  public static boolean UninstallInfoByPkgName(String paramString)
  {
    TLog.v("XGService", "@@ UninstallInfoByPkgName(" + paramString + ")");
    if ((i.e() != null) && (!c.a(paramString)))
    {
      a(paramString, c(paramString, ".reg"), 2);
      return true;
    }
    TLog.e("XGService", ">>> uninstall registerInfo : " + paramString + " failed!");
    return false;
  }

  public static boolean UninstallInfoSuccessByPkgName(String paramString)
  {
    TLog.v("XGService", "@@ UninstallInfoSuccessByPkgName(" + paramString + ")");
    if ((i.e() != null) && (!c.a(paramString)))
    {
      a(paramString, c(paramString, ".reg"), 4);
      return true;
    }
    TLog.e("XGService", ">>> uninstall registerInfo : " + paramString + " failed!");
    return false;
  }

  public static boolean UnregisterInfoByPkgName(String paramString)
  {
    TLog.v("XGService", "@@ UnregisterInfoByPkgName(" + paramString + ")");
    if ((i.e() != null) && (!c.a(paramString)))
    {
      a(paramString, c(paramString, ".reg"), 1);
      return true;
    }
    TLog.e("XGService", ">>> unregister registerInfo : " + paramString + " failed!");
    return false;
  }

  public static boolean UnregisterInfoSuccessByPkgName(String paramString)
  {
    TLog.v("XGService", "@@ UnregisterInfoSuccessByPkgName(" + paramString + ")");
    if ((i.e() != null) && (!c.a(paramString)))
    {
      a(paramString, c(paramString, ".reg"), 3);
      return true;
    }
    TLog.e("XGService", ">>> unregister registerInfo : " + paramString + " failed!");
    return false;
  }

  private static b a(String paramString1, String paramString2)
  {
    TLog.v("XGService", "@@ getRegisterInfoByPkgNameFromSettings(" + paramString1 + ")");
    Context localContext = i.e();
    b localb = null;
    if (localContext != null)
    {
      boolean bool = c.a(paramString1);
      localb = null;
      if (!bool)
        localb = b(c.c(i.e(), paramString2), paramString1);
    }
    return localb;
  }

  private static String a(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (c.a(paramString)))
      return Rijndael.decrypt(c.c(paramContext, paramString));
    return null;
  }

  private static List a(Context paramContext, String paramString, int paramInt)
  {
    ArrayList localArrayList;
    if ((paramContext != null) && (!c.a(paramString)))
    {
      String str = a(paramContext, paramString);
      if (!c.a(str))
      {
        String[] arrayOfString1 = str.split("\\|");
        if ((arrayOfString1 != null) && (arrayOfString1.length > 0))
        {
          localArrayList = new ArrayList();
          int i = arrayOfString1.length;
          int j = 0;
          while (true)
            if (j < i)
            {
              String[] arrayOfString2 = arrayOfString1[j].split(":");
              a locala;
              if ((arrayOfString2 != null) && (arrayOfString2.length > 0))
              {
                locala = new a();
                locala.a = arrayOfString2[0];
                if (arrayOfString2.length != 2);
              }
              try
              {
                locala.b = Integer.parseInt(arrayOfString2[1]);
                locala.c = paramInt;
                localArrayList.add(locala);
                j++;
              }
              catch (NumberFormatException localNumberFormatException)
              {
                while (true)
                  TLog.e("TPush", localNumberFormatException.toString());
              }
            }
          if (localArrayList.size() != 0)
            break label172;
        }
      }
    }
    return null;
    label172: return localArrayList;
  }

  private static void a(String paramString)
  {
    TLog.v("XGService", "@@ removeRegisterInfoByPkgNameFromSettings(" + paramString + ")");
    if (i.e() != null)
      c.a(i.e(), c(paramString, ".reg"), "");
  }

  private static void a(String paramString1, String paramString2, int paramInt)
  {
    TLog.v("XGService", "@@ getRegisterInfoByPkgNameFromSettings(" + paramString1 + ")");
    b localb = a(paramString1, c(paramString1, ".reg"));
    if (localb != null)
      a(localb, paramString2, paramInt);
  }

  private static boolean a(Context paramContext, String paramString, int paramInt, List paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      List localList = a(paramContext, paramString, paramInt);
      if (localList != null)
      {
        Iterator localIterator1 = localList.iterator();
        while (localIterator1.hasNext())
        {
          a locala = (a)localIterator1.next();
          Iterator localIterator2 = paramList.iterator();
          while (localIterator2.hasNext())
          {
            if (!locala.a((a)localIterator2.next()))
              continue;
            localList.remove(locala);
          }
        }
        localList.addAll(paramList);
        return a(paramContext, paramString, localList);
      }
      return a(paramContext, paramString, paramList);
    }
    return false;
  }

  private static boolean a(Context paramContext, String paramString, int paramInt, String[] paramArrayOfString)
  {
    if ((paramContext != null) && (c.a(paramString)) && (paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      List localList = a(paramContext, paramString, paramInt);
      if (localList != null)
      {
        Iterator localIterator = localList.iterator();
        while (localIterator.hasNext())
        {
          a locala = (a)localIterator.next();
          int i = paramArrayOfString.length;
          for (int j = 0; j < i; j++)
          {
            String str = paramArrayOfString[j];
            if (!locala.a(str))
              continue;
            TLog.v("TPush", ">>> delete ip " + str + " @[" + paramInt + "]");
            localList.remove(locala);
          }
        }
        return a(paramContext, paramString, localList);
      }
    }
    return false;
  }

  private static boolean a(Context paramContext, String paramString, List paramList)
  {
    if ((paramContext != null) && (c.a(paramString)) && (paramList != null) && (paramList.size() > 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        a locala = (a)localIterator.next();
        localStringBuilder.append(locala.a);
        if (locala.b > 0)
          localStringBuilder.append(locala.b);
        localStringBuilder.append("|");
      }
      if (localStringBuilder.length() > 0)
        localStringBuilder.deleteCharAt(-1 + localStringBuilder.length());
      if (localStringBuilder.length() > 0)
      {
        TLog.v("TPush", ">>> update ip success. @[" + paramString + "]");
        return c.a(paramContext, ".com.tencent.tpush.cache.adv.ip", Rijndael.encrypt(localStringBuilder.toString()));
      }
    }
    TLog.v("TPush", ">>> update ip fail. @[" + paramString + "]");
    return false;
  }

  private static boolean a(b paramb, String paramString, int paramInt)
  {
    TLog.v("XGService", "@@ addRegisterInfoToSettings(" + paramb + ")");
    if ((i.e() != null) && (paramb != null))
    {
      String str = Rijndael.encrypt(paramb.a + "|" + paramb.b + "|" + paramb.c + "|" + paramInt + "|" + System.currentTimeMillis());
      if (!c.a(str))
      {
        c.a(i.e(), paramString, str);
        TLog.i("XGService", ">> update registerInfo " + paramb.d + " success.");
        return true;
      }
    }
    TLog.e("XGService", ">> add registerInfo failed!");
    return false;
  }

  public static boolean addAdvancedIP(Context paramContext, List paramList)
  {
    return a(paramContext, ".com.tencent.tpush.cache.adv.ip", 0, paramList);
  }

  public static void addOptKey(Context paramContext, String paramString)
  {
    TLog.v("XGService", "@@ addOptKey(" + paramContext.getPackageName() + ")");
    try
    {
      HashSet localHashSet2 = getOptKeyList(paramContext);
      localHashSet1 = localHashSet2;
      localHashSet1.add(paramString);
      addOptKeyList(paramContext, localHashSet1);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        HashSet localHashSet1 = new HashSet();
    }
  }

  public static void addOptKeyList(Context paramContext, HashSet paramHashSet)
  {
    if (paramContext != null)
      TLog.v("XGService", "@@ addOptKeyList(" + paramContext.getPackageName() + ")");
    try
    {
      c.a(paramContext, ".com.tencent.tpush.cache.keylist", Rijndael.encrypt(f.a(paramHashSet)));
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public static boolean addOptStrategy(StrategyItem paramStrategyItem)
  {
    monitorenter;
    while (true)
    {
      OptStrategyList localOptStrategyList1;
      try
      {
        String str = c.f(i.e());
        TLog.v("XGService", "@@ addOptStrategy(" + str + ")");
        try
        {
          OptStrategyList localOptStrategyList2 = getOptStrategyList(i.e(), str);
          localOptStrategyList1 = localOptStrategyList2;
          if (paramStrategyItem.d() != 1)
            break label152;
          TLog.i("XGService", ">> http");
          if (paramStrategyItem.f() == 0)
          {
            TLog.i("XGService", ">> no redirected");
            localOptStrategyList1.d(paramStrategyItem);
            boolean bool = addOptStrategyList(i.e(), str, localOptStrategyList1);
            monitorexit;
            return bool;
          }
        }
        catch (Exception localException)
        {
          TLog.e("XGService", ">> Can not get OptStrategyList from local", localException);
          localOptStrategyList1 = new OptStrategyList();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      TLog.i("XGService", ">> redirected");
      localOptStrategyList1.c(paramStrategyItem);
      continue;
      label152: TLog.i("XGService", ">> tcp");
      if (paramStrategyItem.f() == 0)
      {
        TLog.i("XGService", ">> no redirected");
        localOptStrategyList1.b(paramStrategyItem);
        continue;
      }
      TLog.i("XGService", ">> redirected");
      localOptStrategyList1.a(paramStrategyItem);
    }
  }

  public static boolean addOptStrategyList(Context paramContext, String paramString, OptStrategyList paramOptStrategyList)
  {
    monitorenter;
    int i = 0;
    if (paramContext != null)
    {
      i = 0;
      if (paramString != null)
        break label20;
    }
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        label20: TLog.v("XGService", "@@ addOptStrategyList(" + paramContext.getPackageName() + "," + paramString + ")");
        addOptKey(paramContext, paramString);
        String str = paramString + ".com.tencent.tpush.cache.redirect";
        try
        {
          paramOptStrategyList.a(System.currentTimeMillis());
          boolean bool = c.a(paramContext, str, Rijndael.encrypt(f.a(paramOptStrategyList)));
          i = bool;
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
          i = 0;
        }
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public static boolean addRegisterInfo(b paramb)
  {
    return a(paramb, c(paramb.d, ".reg"), 0);
  }

  public static boolean addSecondaryIP(Context paramContext, List paramList)
  {
    return a(paramContext, ".com.tencent.tpush.cache.sec.ip", 1, paramList);
  }

  public static boolean addServerItems(Context paramContext, String paramString, ArrayList paramArrayList)
  {
    if ((paramContext == null) || (paramString == null))
      return false;
    TLog.v("XGService", "@@ addServerItems(" + paramContext.getPackageName() + "," + paramString + "," + paramArrayList + ")");
    saveDomainKey(paramContext, paramString);
    String str = paramString + ".com.tencent.tpush.cache.server";
    try
    {
      boolean bool = c.a(paramContext, str, Rijndael.encrypt(f.a(paramArrayList)));
      return bool;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return false;
  }

  private static b b(String paramString1, String paramString2)
  {
    try
    {
      String str = Rijndael.decrypt(paramString1);
      if (!c.a(str))
      {
        String[] arrayOfString = str.split("\\|");
        if (arrayOfString.length < 5)
          return null;
        b localb = new b();
        localb.a = Long.parseLong(arrayOfString[0]);
        localb.b = arrayOfString[1];
        localb.c = arrayOfString[2];
        localb.e = Integer.parseInt(arrayOfString[3]);
        localb.f = Long.parseLong(arrayOfString[4]);
        localb.d = paramString2;
        return localb;
      }
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return null;
  }

  private static String c(String paramString1, String paramString2)
  {
    return paramString1 + ".com.tencent.tpush.cache" + paramString2;
  }

  public static void clearDomainServerItem(Context paramContext)
  {
    TLog.v("XGService", "@@ clearDomainServerItem(" + paramContext.getPackageName() + ")");
    try
    {
      ArrayList localArrayList2 = getDomainKeyList(paramContext);
      localArrayList1 = localArrayList2;
      localArrayList1.add(String.valueOf(3));
      localArrayList1.add(String.valueOf(1));
      localArrayList1.add(String.valueOf(2));
      Iterator localIterator = localArrayList1.iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        TLog.i("XGService", ">> server key=" + str1);
        String str2 = str1 + ".com.tencent.tpush.cache.server";
        try
        {
          c.a(paramContext, str2, "");
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
        }
      }
    }
    catch (NullReturnException localNullReturnException)
    {
      while (true)
        ArrayList localArrayList1 = new ArrayList();
    }
  }

  public static void clearOptKeyList(Context paramContext)
  {
    if (paramContext != null)
      c.a(paramContext, ".com.tencent.tpush.cache.keylist", "");
  }

  public static boolean deleteAdvancedIP(Context paramContext, String[] paramArrayOfString)
  {
    return a(paramContext, ".com.tencent.tpush.cache.adv.ip", 0, paramArrayOfString);
  }

  public static boolean deteleSecondaryIP(Context paramContext, String[] paramArrayOfString)
  {
    return a(paramContext, ".com.tencent.tpush.cache.sec.ip", 1, paramArrayOfString);
  }

  public static List getAdvancedIP(Context paramContext)
  {
    return a(paramContext, ".com.tencent.tpush.cache.adv.ip", 0);
  }

  public static String getDomain(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getDomain(" + paramContext.getPackageName() + ")");
      return c.c(paramContext, ".com.tencent.tpush.cache.domain");
    }
    return "";
  }

  public static ArrayList getDomainKeyList(Context paramContext)
  {
    if (paramContext == null)
      throw new NullReturnException("getDomainKeyList return null,because ctx is null");
    TLog.v("XGService", "@@ getDomainKeyList(" + paramContext.getPackageName() + ")");
    try
    {
      Object localObject = f.a(Rijndael.decrypt(c.c(paramContext, ".com.tencent.tpush.cache.domain.key")));
      if ((localObject instanceof ArrayList))
        return (ArrayList)localObject;
      throw new NullReturnException("getDomainKeyList return null,because object not instance of ArrayList<?>");
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    throw new NullReturnException("getDomainKeyList return null，deseriallize err", localException);
  }

  public static CacheManager getInstance()
  {
    return a;
  }

  public static long getLastLoadIpTime(Context paramContext)
  {
    long l = 0L;
    if (paramContext != null)
      l = c.b(paramContext, ".com.tencent.tpush.cache.load.ip.last.time", l);
    return l;
  }

  public static HashSet getOptKeyList(Context paramContext)
  {
    if (paramContext == null)
      throw new NullReturnException("getOptKeyList return null,because ctx is null");
    TLog.v("XGService", "@@ getOptKeyList(" + paramContext.getPackageName() + ")");
    try
    {
      Object localObject = f.a(Rijndael.decrypt(c.c(paramContext, ".com.tencent.tpush.cache.keylist")));
      if ((localObject instanceof HashSet))
        return (HashSet)localObject;
      throw new NullReturnException("getOptKeyList return null,because object not instance of ArrayList<?>");
    }
    catch (Exception localException)
    {
    }
    throw new NullReturnException("getOptKeyList return null，deseriallize err", localException);
  }

  public static OptStrategyList getOptStrategyList(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null))
    {
      StringBuffer localStringBuffer = new StringBuffer("getStrategy return null,contex is null(");
      if (paramContext == null);
      for (boolean bool = true; ; bool = false)
        throw new NullReturnException(bool + ") and key=" + paramString);
    }
    TLog.v("XGService", "@@ getOptStrategyList(" + paramContext.getPackageName() + "," + paramString + ")");
    String str = paramString + ".com.tencent.tpush.cache.redirect";
    try
    {
      Object localObject = f.a(Rijndael.decrypt(c.c(paramContext, str)));
      if ((localObject instanceof OptStrategyList))
        return (OptStrategyList)localObject;
      throw new NullReturnException("getStrategy return null, because serializer object is not instanceof OptStrategyList");
    }
    catch (Exception localException)
    {
    }
    throw new NullReturnException("getOptStrategyList return null,deserialize err", localException);
  }

  public static String getQua(Context paramContext, long paramLong)
  {
    String str = "";
    if (paramContext != null)
      str = Rijndael.decrypt(c.c(paramContext, ".com.tencent.tpush.cache.qua." + paramLong));
    return str;
  }

  public static List getRegisterInfo()
  {
    TLog.v("XGService", "@@ getRegisterInfo()");
    Context localContext = i.e();
    Object localObject = null;
    if (localContext != null)
    {
      List localList = c.a(i.e());
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            String str = ((ResolveInfo)localIterator.next()).activityInfo.packageName;
            TLog.i("XGService", ">> registerInfo : " + str);
            b localb = getRegisterInfoByPkgName(str);
            if ((localb == null) || (localb.e >= 1))
              continue;
            localArrayList.add(localb);
          }
          localObject = localArrayList;
        }
      }
    }
    return localObject;
  }

  public static b getRegisterInfoByPkgName(String paramString)
  {
    return a(paramString, c(paramString, ".reg"));
  }

  public static List getSecondaryIP(Context paramContext)
  {
    return a(paramContext, ".com.tencent.tpush.cache.sec.ip", 1);
  }

  public static ArrayList getServerItems(Context paramContext, String paramString)
  {
    TLog.v("XGService", "@@ getServerItems(" + paramContext + "," + paramString + ")");
    if (paramString == null)
      throw new NullReturnException("getServerItems return null,because key is null");
    String str = paramString + ".com.tencent.tpush.cache.server";
    try
    {
      Object localObject = f.a(Rijndael.decrypt(c.c(paramContext, str)));
      if ((localObject instanceof ArrayList))
        return (ArrayList)localObject;
      throw new NullReturnException("getServerItems return null,because object not instance of Arraylist<?>");
    }
    catch (Exception localException)
    {
    }
    throw new NullReturnException("getServerItem return null,deseriallize err", localException);
  }

  public static ArrayList getSpeedTestList(Context paramContext)
  {
    if (paramContext == null)
      throw new NullReturnException("getSpeedTestList return null ,because ctx is null");
    String str = Rijndael.decrypt(c.c(paramContext, ".com.tencent.tpush.cache.speed.test"));
    try
    {
      Object localObject = f.a(str);
      if ((localObject instanceof ArrayList))
        return (ArrayList)localObject;
      throw new NullReturnException("getSpeedTestList return null ,because instanceof err");
    }
    catch (Exception localException)
    {
    }
    throw new NullReturnException("getSpeedTestList return null ,because deserialize err", localException);
  }

  public static long getTestSpeedTime(Context paramContext)
  {
    long l = 0L;
    if (paramContext != null)
      l = c.b(paramContext, "Channel.SpeedDetector.LastActivationTimestamp", l);
    return l;
  }

  public static String getToken(Context paramContext)
  {
    String str = null;
    if (paramContext != null)
      str = MidService.getLocalMidOnly(paramContext);
    if (c.a(str))
      str = "0";
    TLog.e("XGService", "token==" + str);
    return str;
  }

  public static ArrayList getUninstallAndUnregisterInfo()
  {
    TLog.v("XGService", ">>> getUninstallInfo.");
    Context localContext = i.e();
    Object localObject = null;
    if (localContext != null)
    {
      List localList = c.a(i.e());
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            String str = ((ResolveInfo)localIterator.next()).activityInfo.packageName;
            TLog.i("XGService", ">> registerInfo : " + str);
            b localb = getRegisterInfoByPkgName(str);
            if ((localb == null) || (localb.e <= 0) || (localb.e >= 3))
              continue;
            UnregInfo localUnregInfo = new UnregInfo();
            localUnregInfo.appInfo = new AppInfo(localb.a, localb.b, c.c(localb.d), 0);
            localUnregInfo.isUninstall = (byte)localb.e;
            localUnregInfo.timestamp = localb.f;
            localArrayList.add(localUnregInfo);
          }
          localObject = localArrayList;
        }
      }
    }
    return localObject;
  }

  public static List getUninstallInfo()
  {
    TLog.v("XGService", ">>> getUninstallInfo.");
    Context localContext = i.e();
    Object localObject = null;
    if (localContext != null)
    {
      List localList = c.a(i.e());
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            String str = ((ResolveInfo)localIterator.next()).activityInfo.packageName;
            TLog.i("XGService", ">> registerInfo : " + str);
            b localb = getRegisterInfoByPkgName(str);
            if ((localb == null) || (localb.e <= 1) || (localb.e >= 3))
              continue;
            localArrayList.add(localb);
          }
          localObject = localArrayList;
        }
      }
    }
    return localObject;
  }

  public static List getUnregisterInfo()
  {
    TLog.v("XGService", ">>> getUnregisterInfo.");
    Context localContext = i.e();
    Object localObject = null;
    if (localContext != null)
    {
      List localList = c.a(i.e());
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          ArrayList localArrayList = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            String str = ((ResolveInfo)localIterator.next()).activityInfo.packageName;
            TLog.i("XGService", ">> registerInfo : " + str);
            b localb = getRegisterInfoByPkgName(str);
            if ((localb == null) || (localb.e <= 0) || (localb.e >= 2))
              continue;
            localArrayList.add(localb);
          }
          localObject = localArrayList;
        }
      }
    }
    return localObject;
  }

  public static void removeRegisterInfoByPkgName(String paramString)
  {
    TLog.v("XGService", "@@ removeRegisterInfoByPkgName(" + paramString + ")");
    a(paramString);
  }

  public static void saveDomain(Context paramContext, String paramString)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ clearCacheServerItems(" + paramContext.getPackageName() + ")");
      c.a(paramContext, ".com.tencent.tpush.cache.domain", paramString);
    }
  }

  public static void saveDomainKey(Context paramContext, String paramString)
  {
    if (paramContext != null)
      TLog.v("XGService", "@@ saveDomainKey(" + paramContext.getPackageName() + "," + paramString + ")");
    try
    {
      ArrayList localArrayList2 = getDomainKeyList(paramContext);
      localArrayList1 = localArrayList2;
      localArrayList1.add(paramString);
      saveDomainKeyList(paramContext, localArrayList1);
      return;
    }
    catch (Exception localException)
    {
      while (true)
      {
        TLog.e("XGService", localException.toString());
        ArrayList localArrayList1 = new ArrayList();
      }
    }
  }

  public static void saveDomainKeyList(Context paramContext, ArrayList paramArrayList)
  {
    String str;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ saveDomainKeyList(" + paramContext.getPackageName() + "," + paramArrayList + ")");
      str = "";
      if (paramArrayList == null);
    }
    try
    {
      str = f.a(paramArrayList);
      c.a(paramContext, ".com.tencent.tpush.cache.domain.key", Rijndael.encrypt(str));
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public static boolean saveLoadIpTime(Context paramContext, long paramLong)
  {
    if ((paramContext != null) && (paramLong > 0L))
      return c.a(paramContext, ".com.tencent.tpush.cache.load.ip.last.time", paramLong);
    return false;
  }

  public static void saveSpeedTestList(Context paramContext, ArrayList paramArrayList)
  {
    if (paramContext == null)
      return;
    try
    {
      c.a(paramContext, ".com.tencent.tpush.cache.speed.test", Rijndael.encrypt(f.a(paramArrayList)));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public static boolean setQua(Context paramContext, long paramLong, String paramString)
  {
    if ((paramContext != null) && (!c.a(paramString)) && (paramLong > 0L))
      return c.a(paramContext, ".com.tencent.tpush.cache.qua." + paramLong, Rijndael.encrypt(paramString));
    return false;
  }

  public static boolean setTestSpeedTime(Context paramContext, long paramLong)
  {
    if ((paramContext != null) && (paramLong > 0L))
      return c.a(paramContext, "Channel.SpeedDetector.LastActivationTimestamp", paramLong);
    return false;
  }

  public static boolean setToken(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!c.a(paramString)))
    {
      TLog.v("XGService", "@@ setToken(" + paramContext.getPackageName() + "," + paramString + ")");
      if (!paramString.equals(getToken(paramContext)))
      {
        TLog.i("XGService", ">> save " + paramString);
        Util.updateIfLocalInvalid(paramContext, paramString);
        return true;
      }
    }
    return false;
  }

  public static boolean updateAdvancedIP(Context paramContext, List paramList)
  {
    return a(paramContext, ".com.tencent.tpush.cache.adv.ip", paramList);
  }

  public static boolean updateSecondaryIP(Context paramContext, List paramList)
  {
    return a(paramContext, ".com.tencent.tpush.cache.sec.ip", paramList);
  }

  public static void updateUnregUninList(Context paramContext, ArrayList paramArrayList)
  {
    if ((paramContext != null) && (paramArrayList != null) && (paramArrayList.size() > 0))
    {
      TLog.v("XGService", "@@ updateUnregUninList(" + paramContext.getPackageName() + "," + paramArrayList + ")");
      List localList1 = getUnregisterInfo();
      List localList2 = getUninstallInfo();
      if (localList1 != null)
        for (int i = 0; i < paramArrayList.size(); i++)
        {
          UnregInfo localUnregInfo = (UnregInfo)paramArrayList.get(i);
          if (localUnregInfo.isUninstall == 1)
            for (int j = 0; j < localList1.size(); j++)
            {
              b localb2 = (b)localList1.get(j);
              if (localb2.a != localUnregInfo.appInfo.accessId)
                continue;
              a(localb2.d, c(localb2.d, ".reg"), 3);
              TLog.i("XGService", ">> update unreg of " + localb2.d + " from reconnback list.");
            }
          if (localUnregInfo.isUninstall != 2)
            continue;
          Iterator localIterator = localList2.iterator();
          while (localIterator.hasNext())
          {
            b localb1 = (b)localIterator.next();
            if (localb1.a != localUnregInfo.appInfo.accessId)
              continue;
            TLog.i("XGService", ">> remove uninstall of " + localb1.d + " from reconnback list.");
            a(localb1.d, c(localb1.d, ".reg"), 4);
          }
        }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.cache.CacheManager
 * JD-Core Version:    0.6.0
 */