package com.tenpay.tenpayplugin;

import android.os.Handler;
import com.tenpay.a.a.b;
import org.json.JSONObject;

public class TenpayNewCardActivity$MyBLCallbackListener
  implements b
{
  public TenpayNewCardActivity$MyBLCallbackListener(TenpayNewCardActivity paramTenpayNewCardActivity)
  {
  }

  public void onBLCallback(String paramString, int paramInt, JSONObject paramJSONObject)
  {
    if (this.a.mHandler != null)
      this.a.mHandler.post(new TenpayNewCardActivity.MyBLCallbackListener.1(this, paramInt, paramJSONObject));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.MyBLCallbackListener
 * JD-Core Version:    0.6.0
 */