package com.tencent.feedback.anr;

import com.tencent.feedback.common.service.a;
import java.util.HashMap;
import java.util.Map;

final class d
  implements e
{
  d(a parama, boolean paramBoolean)
  {
  }

  public final boolean a(long paramLong)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(paramLong);
    com.tencent.feedback.common.e.b("process end %d", arrayOfObject);
    return false;
  }

  public final boolean a(long paramLong1, long paramLong2, String paramString)
  {
    com.tencent.feedback.common.e.b("new process %s", new Object[] { paramString });
    this.a.a = paramLong1;
    this.a.b = paramString;
    this.a.c = paramLong2;
    return this.b;
  }

  public final boolean a(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    com.tencent.feedback.common.e.b("new thread %s", new Object[] { paramString1 });
    if (this.a.d == null)
      this.a.d = new HashMap();
    Map localMap = this.a.d;
    String[] arrayOfString = new String[3];
    arrayOfString[0] = paramString2;
    arrayOfString[1] = paramString3;
    arrayOfString[2] = paramInt;
    localMap.put(paramString1, arrayOfString);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.anr.d
 * JD-Core Version:    0.6.0
 */