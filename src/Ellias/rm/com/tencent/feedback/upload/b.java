package com.tencent.feedback.upload;

import android.content.Context;
import com.tencent.feedback.anr.a;
import com.tencent.feedback.common.b.f;
import com.tencent.feedback.common.e;
import com.tencent.feedback.common.h;
import common.RequestPackage;
import qimei.QIMeiInfoPackage;

public final class b extends AbstractUploadDatas
{
  private Context d = null;
  private RequestPackage e = null;

  public b(Context paramContext)
  {
    super(paramContext, 1111, 15);
    this.d = paramContext;
  }

  public final RequestPackage a()
  {
    e.b("rqdp{  QIMEIUploadDatas.getUploadRequestPackage() start}", new Object[0]);
    if (this.e != null)
      return this.e;
    h localh;
    try
    {
      localh = h.a(this.d);
      if (localh == null)
      {
        e.c("rqdp{  QIMEIInfo instance is null, return}", new Object[0]);
        return null;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    while (true)
    {
      return this.e;
      QIMeiInfoPackage localQIMeiInfoPackage;
      if (localh == null)
        localQIMeiInfoPackage = null;
      int i;
      int j;
      byte[] arrayOfByte;
      while (true)
      {
        f localf = com.tencent.feedback.common.b.c.a(this.c).b();
        i = localf.f();
        j = localf.g();
        String str6 = localf.d();
        arrayOfByte = a.a(localQIMeiInfoPackage.toByteArray(), j, i, str6);
        if (arrayOfByte != null)
          break;
        e.d("rqdp{  encodeDatasByZipAndEncry failed!}", new Object[0]);
        return null;
        localQIMeiInfoPackage = new QIMeiInfoPackage();
        String str1 = localh.b();
        if (str1 == null)
          str1 = "";
        localQIMeiInfoPackage.imei = str1;
        String str2 = localh.d();
        if (str2 == null)
          str2 = "";
        localQIMeiInfoPackage.imsi = str2;
        String str3 = localh.c();
        if (str3 == null)
          str3 = "";
        localQIMeiInfoPackage.mac = str3;
        String str4 = localh.e();
        if (str4 == null)
          str4 = "";
        localQIMeiInfoPackage.androidId = str4;
        String str5 = localh.a();
        if (str5 == null)
          str5 = "";
        localQIMeiInfoPackage.qIMEI = str5;
      }
      com.tencent.feedback.common.c localc = com.tencent.feedback.common.c.p();
      this.e = a.a(this.a, localc, arrayOfByte, j, i);
    }
  }

  public final void done(boolean paramBoolean)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.upload.b
 * JD-Core Version:    0.6.0
 */