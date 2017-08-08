package com.tencent.stat.a;

import android.content.Context;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class b extends e
{
  protected c a = new c();
  private long l = -1L;

  public b(Context paramContext, int paramInt, String paramString)
  {
    super(paramContext, paramInt);
    this.a.a = paramString;
  }

  public f a()
  {
    return f.d;
  }

  public void a(long paramLong)
  {
    this.l = paramLong;
  }

  public void a(Properties paramProperties)
  {
    if (paramProperties != null)
      this.a.c = ((Properties)paramProperties.clone());
  }

  public void a(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
      this.a.b = ((String[])paramArrayOfString.clone());
  }

  public boolean a(JSONObject paramJSONObject)
  {
    paramJSONObject.put("ei", this.a.a);
    if (this.l > 0L)
      paramJSONObject.put("du", this.l);
    if ((this.a.c == null) && (this.a.b == null))
      paramJSONObject.put("kv", new JSONObject());
    if (this.a.b != null)
    {
      JSONArray localJSONArray = new JSONArray();
      String[] arrayOfString = this.a.b;
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
        localJSONArray.put(arrayOfString[j]);
      paramJSONObject.put("ar", localJSONArray);
    }
    JSONObject localJSONObject1;
    JSONObject localJSONObject2;
    if (this.a.c != null)
    {
      localJSONObject1 = new JSONObject();
      try
      {
        Iterator localIterator = this.a.c.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          localJSONObject1.put(localEntry.getKey().toString(), localEntry.getValue().toString());
        }
      }
      catch (Exception localException)
      {
        localJSONObject2 = new JSONObject(this.a.c);
      }
    }
    while (true)
    {
      paramJSONObject.put("kv", localJSONObject2);
      return true;
      localJSONObject2 = localJSONObject1;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.b
 * JD-Core Version:    0.6.0
 */