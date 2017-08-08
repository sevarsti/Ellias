package com.tencent.stat.a;

import android.content.Context;
import com.tencent.stat.StatGameUser;
import com.tencent.stat.common.k;
import org.json.JSONObject;

public class g extends e
{
  private StatGameUser a = null;

  public g(Context paramContext, int paramInt, StatGameUser paramStatGameUser)
  {
    super(paramContext, paramInt);
    this.a = paramStatGameUser.clone();
  }

  public f a()
  {
    return f.g;
  }

  public boolean a(JSONObject paramJSONObject)
  {
    if (this.a == null)
      return false;
    k.a(paramJSONObject, "wod", this.a.getWorldName());
    k.a(paramJSONObject, "gid", this.a.getAccount());
    k.a(paramJSONObject, "lev", this.a.getLevel());
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.a.g
 * JD-Core Version:    0.6.0
 */