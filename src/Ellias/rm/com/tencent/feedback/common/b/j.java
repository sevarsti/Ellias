package com.tencent.feedback.common.b;

import android.content.Context;
import com.tencent.feedback.common.a.f;
import com.tencent.feedback.common.d;
import com.tencent.feedback.common.e;
import com.tencent.feedback.upload.AbstractUploadDatas;
import common.RequestPackage;
import java.util.ArrayList;
import userinfo.SummaryInfo;
import userinfo.UserInfoPackage;

public final class j extends AbstractUploadDatas
{
  private h d = null;
  private byte e = -1;

  public j(Context paramContext, h paramh, byte paramByte)
  {
    super(paramContext, 1111, 110);
    this.d = paramh;
    this.e = paramByte;
  }

  public static UserInfoPackage a(Context paramContext, h paramh, byte paramByte)
  {
    if ((paramh == null) || (paramContext == null))
      return null;
    String str1 = com.tencent.feedback.common.a.h(paramContext);
    f[] arrayOff = paramh.a(str1);
    if ((arrayOff == null) || (arrayOff.length <= 0))
      return null;
    paramh.a(arrayOff);
    d.a(paramContext);
    String str2 = d.b(paramContext);
    String str3 = d.d(paramContext);
    String str4 = com.tencent.feedback.anr.a.d(d.m() + str2 + str3);
    String str5 = d.e(paramContext);
    ArrayList localArrayList = new ArrayList(arrayOff.length);
    int i = arrayOff.length;
    int j = 0;
    while (j < i)
    {
      f localf = arrayOff[j];
      byte b;
      switch (localf.b())
      {
      default:
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Integer.valueOf(localf.b());
        e.c("rqdp{  unknown app state :%d ,drop it}", arrayOfObject2);
        j++;
        break;
      case 1:
        b = 1;
      case 2:
      case 3:
        while (true)
        {
          SummaryInfo localSummaryInfo = new SummaryInfo(localf.a(), b, localf.e(), localf.c());
          Object[] arrayOfObject1 = new Object[4];
          arrayOfObject1[0] = localf.c();
          arrayOfObject1[1] = localf.e();
          arrayOfObject1[2] = Long.valueOf(localf.a());
          arrayOfObject1[3] = Byte.valueOf(b);
          e.b("rqdp{  loc st :%s , uid:%s ,  tm:%d , st:%d }", arrayOfObject1);
          localArrayList.add(localSummaryInfo);
          break;
          b = 2;
          continue;
          b = 3;
        }
      }
    }
    if (localArrayList.size() > 0)
    {
      UserInfoPackage localUserInfoPackage = new UserInfoPackage();
      localUserInfoPackage.guid = str4;
      localUserInfoPackage.imei = str2;
      localUserInfoPackage.mac = str5;
      localUserInfoPackage.proceName = str1;
      localUserInfoPackage.list = localArrayList;
      localUserInfoPackage.type = paramByte;
      return localUserInfoPackage;
    }
    return null;
  }

  private RequestPackage e()
  {
    monitorenter;
    try
    {
      UserInfoPackage localUserInfoPackage = a(this.c, this.d, this.e);
      localObject2 = null;
      if (localUserInfoPackage == null);
      while (true)
      {
        return localObject2;
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = localUserInfoPackage.guid;
        arrayOfObject[1] = localUserInfoPackage.proceName;
        arrayOfObject[2] = localUserInfoPackage.imei;
        arrayOfObject[3] = localUserInfoPackage.mac;
        e.b("rqdp{   guid =} %s rqdp{ procName=} %s rqdp{ imei=}%s rqdp{ mac=}%s", arrayOfObject);
        RequestPackage localRequestPackage = a(this.c, this.a, localUserInfoPackage.toByteArray());
        localObject2 = localRequestPackage;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        localThrowable.printStackTrace();
        Object localObject2 = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final RequestPackage a()
  {
    monitorenter;
    try
    {
      RequestPackage localRequestPackage = e();
      monitorexit;
      return localRequestPackage;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void done(boolean paramBoolean)
  {
    monitorenter;
    monitorexit;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.b.j
 * JD-Core Version:    0.6.0
 */