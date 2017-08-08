package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatConfig;
import com.tencent.stat.common.k;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONObject;

public class a extends e
{
  Map<String, ?> a = null;

  public a(Context paramContext, int paramInt, Map<String, ?> paramMap)
  {
    super(paramContext, paramInt);
    this.a = paramMap;
  }

  public f a()
  {
    return f.e;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    k.a(paramJSONObject, "qq", StatConfig.getQQ());
    if ((this.a != null) && (this.a.size() > 0))
    {
      Iterator localIterator = this.a.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramJSONObject.put((String)localEntry.getKey(), localEntry.getValue());
      }
    }
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.a
 * JD-Core Version:    0.6.0
 */