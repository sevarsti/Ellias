package oicq.wlogin_sdk.request;

import android.content.Context;
import oicq.wlogin_sdk.tools.util;

public class delete_expire_log extends Thread
{
  private Context context;

  public delete_expire_log(Context paramContext)
  {
    this.context = paramContext;
  }

  public void run()
  {
    util.deleteExpireLog(this.context);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.delete_expire_log
 * JD-Core Version:    0.6.0
 */