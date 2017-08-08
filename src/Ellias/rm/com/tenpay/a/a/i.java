package com.tenpay.a.a;

import android.content.Context;
import android.os.Bundle;
import com.tenpay.a.b.a;
import com.tenpay.a.b.b;
import org.json.JSONObject;

class i extends f
{
  i(h paramh, int paramInt, Context paramContext, JSONObject paramJSONObject, String paramString)
  {
  }

  public void run()
  {
    boolean bool = true;
    StringBuilder localStringBuilder = new StringBuilder();
    switch (this.c)
    {
    case 8:
    case 10:
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 9:
    case 11:
    }
    while (true)
    {
      if ((this.f != null) && (!"".equals(this.f)))
      {
        localStringBuilder.append("&skey=");
        localStringBuilder.append(this.f);
      }
      this.a = new b(g.a(this.d));
      Bundle localBundle = this.a.a(localStringBuilder.toString());
      this.b.a(this.d, this.c, localBundle, bool, this.b.d);
      return;
      localStringBuilder.append(this.b.j(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.i(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.h(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.g(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.f(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.e(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.d(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.c(this.d, this.e));
      bool = false;
      continue;
      localStringBuilder.append(this.b.b(this.d, this.e));
      continue;
      localStringBuilder.append(this.b.a(this.d, this.e));
      bool = false;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.a.i
 * JD-Core Version:    0.6.0
 */