package com.pay.http;

import android.os.Handler;
import android.os.Message;
import com.pay.common.tool.APLog;
import java.util.HashMap;

public class APHttpHandle extends Handler
{
  private static APHttpHandle a;
  private static Object b = new Object();
  private HashMap c = new HashMap();

  public static APHttpHandle getIntanceHandel()
  {
    synchronized (b)
    {
      if (a == null)
        a = new APHttpHandle();
      return a;
    }
  }

  public void handleMessage(Message paramMessage)
  {
    int i = paramMessage.what;
    APBaseHttpAns localAPBaseHttpAns = (APBaseHttpAns)paramMessage.obj;
    String str = localAPBaseHttpAns.getHttpReqKey();
    IAPHttpAnsObserver localIAPHttpAnsObserver = (IAPHttpAnsObserver)this.c.get(str);
    if (localIAPHttpAnsObserver == null)
    {
      APLog.i("HttpHandler", "observer is null");
      return;
    }
    unregister(str);
    switch (i)
    {
    default:
      return;
    case 3:
      localIAPHttpAnsObserver.onFinish(localAPBaseHttpAns);
      return;
    case 4:
      localIAPHttpAnsObserver.onError(localAPBaseHttpAns);
      return;
    case 5:
    }
    localIAPHttpAnsObserver.onStop(localAPBaseHttpAns);
  }

  public void register(String paramString, IAPHttpAnsObserver paramIAPHttpAnsObserver)
  {
    this.c.put(paramString, paramIAPHttpAnsObserver);
  }

  public void release()
  {
    a = null;
  }

  public void unregister(String paramString)
  {
    this.c.remove(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APHttpHandle
 * JD-Core Version:    0.6.0
 */