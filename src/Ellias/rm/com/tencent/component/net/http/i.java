package com.tencent.component.net.http;

import com.tencent.component.ComponentContext;
import com.tencent.component.net.http.strategy.AsyncRetryHandler;
import com.tencent.component.net.http.strategy.StrategyInfo;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.protocol.HttpContext;

class i
  implements AsyncRetryHandler
{
  private static HashSet a = new HashSet();
  private static HashSet b = new HashSet();
  private static final int c = 3;

  static
  {
    a.add(NoHttpResponseException.class);
    a.add(UnknownHostException.class);
    a.add(SocketException.class);
    b.add(InterruptedIOException.class);
    b.add(SSLException.class);
  }

  private boolean a(HashSet paramHashSet, Throwable paramThrowable)
  {
    if ((paramThrowable != null) && (paramHashSet != null))
    {
      Iterator localIterator = paramHashSet.iterator();
      while (localIterator.hasNext())
        if (((Class)localIterator.next()).isInstance(paramThrowable))
          return true;
    }
    return false;
  }

  public StrategyInfo getStrategyInfo(int paramInt, HttpContext paramHttpContext)
  {
    return null;
  }

  public boolean isNeedRetry(AsyncHttpResult.Status paramStatus, int paramInt, HttpContext paramHttpContext)
  {
    Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    boolean bool1;
    boolean bool2;
    if ((localBoolean != null) && (localBoolean.booleanValue()))
    {
      bool1 = true;
      if (paramInt <= 3)
        break label104;
      bool2 = false;
    }
    while (true)
    {
      if (DebugUtil.a(ComponentContext.a()))
        LogUtil.d("HttpTemplate", "isNeedRetry[sent:" + bool1 + ",retry:" + bool2 + ",executionCount:" + paramInt + "]");
      return bool2;
      bool1 = false;
      break;
      label104: if (a(b, paramStatus.getFailException()))
      {
        bool2 = false;
        continue;
      }
      if (a(a, paramStatus.getFailException()))
      {
        bool2 = true;
        continue;
      }
      if (!bool1)
      {
        bool2 = true;
        continue;
      }
      bool2 = true;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.i
 * JD-Core Version:    0.6.0
 */