package oicq.wlogin_sdk.request;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import oicq.wlogin_sdk.tools.util;

public class http_connect_ontime
  implements Runnable
{
  private HttpURLConnection conn;
  private boolean ret = false;

  public http_connect_ontime(HttpURLConnection paramHttpURLConnection)
  {
    this.conn = paramHttpURLConnection;
  }

  public static boolean connect_ontime(HttpURLConnection paramHttpURLConnection, long paramLong)
  {
    try
    {
      http_connect_ontime localhttp_connect_ontime = new http_connect_ontime(paramHttpURLConnection);
      Thread localThread = new Thread(localhttp_connect_ontime);
      localThread.start();
      localThread.join(paramLong);
      boolean bool = localhttp_connect_ontime.get();
      return bool;
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
    return false;
  }

  public boolean get()
  {
    monitorenter;
    try
    {
      boolean bool = this.ret;
      monitorexit;
      return bool;
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
      this.conn.connect();
      this.ret = true;
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

  public void set(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.ret = paramBoolean;
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
 * Qualified Name:     oicq.wlogin_sdk.request.http_connect_ontime
 * JD-Core Version:    0.6.0
 */