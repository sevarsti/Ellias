package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONObject;

public class d extends e
{
  private String a;
  private int l;
  private int m = 100;

  public d(Context paramContext, int paramInt1, int paramInt2, Throwable paramThrowable)
  {
    super(paramContext, paramInt1);
    Throwable localThrowable1;
    if (paramThrowable != null)
      localThrowable1 = new Throwable(paramThrowable);
    try
    {
      StackTraceElement[] arrayOfStackTraceElement1 = localThrowable1.getStackTrace();
      if ((arrayOfStackTraceElement1 != null) && (arrayOfStackTraceElement1.length > this.m))
      {
        StackTraceElement[] arrayOfStackTraceElement2 = new StackTraceElement[this.m];
        for (int i = 0; i < this.m; i++)
          arrayOfStackTraceElement2[i] = arrayOfStackTraceElement1[i];
        localThrowable1.setStackTrace(arrayOfStackTraceElement2);
      }
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
      localThrowable1.printStackTrace(localPrintWriter);
      this.a = localStringWriter.toString();
      this.l = paramInt2;
      localPrintWriter.close();
      return;
    }
    catch (Throwable localThrowable2)
    {
      while (true)
        localThrowable2.printStackTrace();
    }
  }

  public d(Context paramContext, int paramInt1, String paramString, int paramInt2, int paramInt3)
  {
    super(paramContext, paramInt1);
    if (paramString != null)
    {
      if (paramInt3 <= 0)
        paramInt3 = StatConfig.getMaxReportEventLength();
      if (paramString.length() > paramInt3)
        break label47;
    }
    label47: for (this.a = paramString; ; this.a = paramString.substring(0, paramInt3))
    {
      this.l = paramInt2;
      return;
    }
  }

  public f a()
  {
    return f.c;
  }

  public void a(long paramLong)
  {
    this.c = paramLong;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    k.a(paramJSONObject, "er", this.a);
    paramJSONObject.put("ea", this.l);
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.d
 * JD-Core Version:    0.6.0
 */