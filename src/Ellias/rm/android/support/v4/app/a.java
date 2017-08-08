package android.support.v4.app;

import android.os.Handler;
import android.os.Message;

class a extends Handler
{
  a(PluginFragmentActivity paramPluginFragmentActivity)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      super.handleMessage(paramMessage);
    case 1:
      do
        return;
      while (!this.a.i);
      this.a.doReallyStop(false);
      return;
    case 2:
    }
    this.a.onResumeFragments();
    this.a.e.execPendingActions();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.app.a
 * JD-Core Version:    0.6.0
 */