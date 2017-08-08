package com.tencent.android.tpush;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import com.tencent.android.tpush.common.i;
import com.tencent.android.tpush.logging.TLog;

class j
  implements Runnable
{
  private Context a;
  private Intent b;
  private XGIOperateCallback c;
  private int d;

  public j(XGIOperateCallback paramXGIOperateCallback, Context paramContext, Intent paramIntent, int paramInt)
  {
    TLog.v("TPush", ">>> Create callback runnable. intent:" + paramIntent);
    this.c = paramXGIOperateCallback;
    this.a = paramContext;
    this.b = paramIntent;
    this.d = paramInt;
  }

  public void run()
  {
    String str;
    Parcelable[] arrayOfParcelable;
    if (this.d == 1)
      if ((this.c != null) && (this.b != null))
      {
        str = this.b.getStringExtra("data");
        arrayOfParcelable = this.b.getParcelableArrayExtra("storage");
        int i = this.b.getIntExtra("operation", -1);
        TLog.v("TPush", ">>> Callback runnable running @operation " + i);
        switch (i)
        {
        default:
        case 0:
        case 1:
        }
      }
    do
    {
      return;
      if ((arrayOfParcelable != null) && (this.a != null))
        i.a(this.a, arrayOfParcelable);
      this.c.onSuccess(str, this.b.getIntExtra("flag", -1));
      return;
      if ((arrayOfParcelable != null) && (this.a != null))
        i.a(this.a, arrayOfParcelable);
      this.c.onFail(str, this.b.getIntExtra("code", -1), this.b.getStringExtra("msg"));
      return;
    }
    while ((this.d != 0) || (this.b == null));
    switch (this.b.getIntExtra("operation", -1))
    {
    default:
      return;
    case 100:
      XGPushManager.b(this.a, this.b, this.c);
      return;
    case 101:
    }
    XGPushManager.a(this.a, this.b, this.c);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.j
 * JD-Core Version:    0.6.0
 */