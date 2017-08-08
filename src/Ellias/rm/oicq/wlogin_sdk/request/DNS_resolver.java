package oicq.wlogin_sdk.request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import oicq.wlogin_sdk.tools.util;

public class DNS_resolver
  implements Runnable
{
  private String domain;
  private InetSocketAddress inetSkAddr;
  private int port;

  public DNS_resolver(String paramString, int paramInt)
  {
    this.domain = paramString;
    this.port = paramInt;
  }

  public static InetSocketAddress get_DNS_resolver(String paramString, int paramInt, long paramLong)
  {
    try
    {
      DNS_resolver localDNS_resolver = new DNS_resolver(paramString, paramInt);
      Thread localThread = new Thread(localDNS_resolver);
      localThread.start();
      localThread.join(paramLong);
      InetSocketAddress localInetSocketAddress = localDNS_resolver.get();
      return localInetSocketAddress;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
    }
    return null;
  }

  public InetSocketAddress get()
  {
    monitorenter;
    try
    {
      InetSocketAddress localInetSocketAddress = this.inetSkAddr;
      monitorexit;
      return localInetSocketAddress;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void run()
  {
    try
    {
      set(new InetSocketAddress(this.domain, this.port));
      return;
    }
    catch (Exception localException)
    {
      StringWriter localStringWriter = new StringWriter();
      PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
      localException.printStackTrace(localPrintWriter);
      localPrintWriter.flush();
      localStringWriter.flush();
      util.LOGW("exception", localStringWriter.toString());
    }
  }

  public void set(InetSocketAddress paramInetSocketAddress)
  {
    monitorenter;
    try
    {
      this.inetSkAddr = paramInetSocketAddress;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.DNS_resolver
 * JD-Core Version:    0.6.0
 */