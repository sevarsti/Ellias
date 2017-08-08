package com.tencent.connect.avatar;

import android.os.Handler;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

class a
  implements IUiListener
{
  a(ImageActivity paramImageActivity)
  {
  }

  private void a(int paramInt)
  {
    if (ImageActivity.m(this.a) < 2)
      ImageActivity.n(this.a);
  }

  public void onCancel()
  {
  }

  public void onComplete(Object paramObject)
  {
    JSONObject localJSONObject = (JSONObject)paramObject;
    int i = -1;
    try
    {
      i = localJSONObject.getInt("ret");
      if (i == 0)
      {
        String str = localJSONObject.getString("nickname");
        ImageActivity.l(this.a).post(new Runnable(str)
        {
          public void run()
          {
            ImageActivity.b(a.this.a, this.a);
          }
        });
        this.a.a("10659", 0L);
      }
      while (true)
      {
        if (i != 0)
          a(i);
        return;
        this.a.a("10661", 0L);
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public void onError(UiError paramUiError)
  {
    a(0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.avatar.a
 * JD-Core Version:    0.6.0
 */