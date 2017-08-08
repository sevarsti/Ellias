package com.tencent.beacon.upload;

import android.content.Context;
import com.tencent.beacon.a.d;
import com.tencent.beacon.c.a.b;

public final class c extends a
{
  private Context d = null;
  private b e = null;

  public c(Context paramContext)
  {
    super(paramContext, 0, 102);
    this.d = paramContext;
  }

  public final b a()
  {
    int i = 1;
    com.tencent.beacon.d.a.b("QIMEIUploadDatas.getUploadRequestPackage() start}", new Object[0]);
    if (this.e != null)
      return this.e;
    com.tencent.beacon.b.a locala;
    try
    {
      locala = com.tencent.beacon.b.a.a(this.d);
      if (locala != null)
        break label56;
      com.tencent.beacon.d.a.c("QIMEIInfo instance is null, return}", new Object[0]);
      return null;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return this.e;
    label56: Object localObject;
    label63: String str6;
    int j;
    if (locala == null)
    {
      localObject = null;
      com.tencent.beacon.a.b.e locale = com.tencent.beacon.a.b.c.a(this.c).b();
      str6 = "*^@K#K@!";
      if (locale == null)
        break label287;
      i = locale.f();
      j = locale.g();
      str6 = locale.h();
    }
    while (true)
    {
      byte[] arrayOfByte = com.tencent.beacon.a.e.a(localObject.a(), j, i, str6);
      if (arrayOfByte == null)
      {
        com.tencent.beacon.d.a.d("encodeDatasByZipAndEncry failed!", new Object[0]);
        return null;
        com.tencent.beacon.c.c.a locala1 = new com.tencent.beacon.c.c.a();
        String str1 = locala.b();
        if (str1 == null)
          str1 = "";
        locala1.b = str1;
        String str2 = locala.d();
        if (str2 == null)
          str2 = "";
        locala1.d = str2;
        String str3 = locala.c();
        if (str3 == null)
          str3 = "";
        locala1.c = str3;
        String str4 = locala.e();
        if (str4 == null)
          str4 = "";
        locala1.e = str4;
        String str5 = locala.a();
        if (str5 == null)
          str5 = "";
        locala1.a = str5;
        localObject = locala1;
        break label63;
      }
      d locald = d.m();
      this.e = com.tencent.beacon.a.e.a(this.a, locald, arrayOfByte, j, i);
      break;
      label287: j = i;
    }
  }

  public final void a(boolean paramBoolean)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.upload.c
 * JD-Core Version:    0.6.0
 */