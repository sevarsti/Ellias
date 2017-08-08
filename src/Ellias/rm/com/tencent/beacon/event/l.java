package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.a.a.e;
import java.util.HashMap;

public final class l
  implements Runnable
{
  private Context a;

  public l(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
  }

  public final void run()
  {
    e locale = com.tencent.beacon.applog.a.c(this.a);
    Context localContext;
    if (locale != null)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("A43", locale.c());
      localHashMap.put("A44", locale.d());
      localHashMap.put("A41", locale.a());
      localHashMap.put("A42", locale.b());
      m.a("rqd_useInfoEvent", true, 0L, 0L, localHashMap, true);
      localContext = this.a;
      if (localContext != null)
        break label180;
    }
    while (true)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = "rqd_useInfoEvent";
      arrayOfObject[1] = Long.valueOf(locale.a());
      arrayOfObject[2] = Long.valueOf(locale.b());
      com.tencent.beacon.d.a.e("%s %d %d", arrayOfObject);
      return;
      label180: com.tencent.beacon.a.a.a.a(localContext, new int[] { 8 }, -1L, 9223372036854775807L);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.l
 * JD-Core Version:    0.6.0
 */