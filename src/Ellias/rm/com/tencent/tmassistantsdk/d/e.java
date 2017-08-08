package com.tencent.tmassistantsdk.d;

import com.qq.taf.jce.JceStruct;
import com.tencent.tmassistantsdk.e.g;
import com.tencent.tmassistantsdk.f.c.b;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.a;
import com.tencent.tmassistantsdk.protocol.jce.ReportLogRequest;
import com.tencent.tmassistantsdk.protocol.jce.ReportLogResponse;

public class e extends g
{
  protected d a = null;

  protected void a(JceStruct paramJceStruct1, JceStruct paramJceStruct2, int paramInt)
  {
    if (paramJceStruct2 == null)
      return;
    if ((this.a != null) && (paramInt == 0))
    {
      if ((paramJceStruct2 instanceof ReportLogResponse))
      {
        if (((ReportLogResponse)paramJceStruct2).ret == 0)
        {
          this.a.a(this, true);
          return;
        }
        this.a.a(this, false);
        return;
      }
      l.b("LogReportHttpRequest", "response isn't instanceof ReportLogResponse !");
      return;
    }
    l.b("LogReportHttpRequest", "mListener is null !");
  }

  public void a(d paramd)
  {
    this.a = paramd;
  }

  public boolean a(byte paramByte, b paramb)
  {
    if (paramb == null)
      return false;
    String str = f.b(f.a().b());
    int i = f.c(f.a().b());
    return super.a((ReportLogRequest)a.a(paramByte, paramb.b, str, i, ""));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.e
 * JD-Core Version:    0.6.0
 */