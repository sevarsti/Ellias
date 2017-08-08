package oicq.wlogin_sdk.sharemem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import oicq.wlogin_sdk.request.request_app_signature;
import oicq.wlogin_sdk.request.request_global;
import oicq.wlogin_sdk.tools.util;

public class ShareMemInfoGetBroadcastReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals(sharemem_client.SHAREMEM_GET_UINFO_RECEIVED))
    {
      util.LOGI(sharemem_client.SHAREMEM_GET_UINFO_RECEIVED + ": recved");
      try
      {
        if (request_global._sharemem == null)
        {
          request_global localrequest_global = new request_global(paramContext.getApplicationContext());
          localrequest_global.set_context(paramContext.getApplicationContext());
          localrequest_global.init();
          request_app_signature localrequest_app_signature = new request_app_signature(localrequest_global);
          request_global._sharemem = new sharemem_client();
          request_global._sharemem.init(localrequest_global._context, localrequest_app_signature);
          return;
        }
        request_global._sharemem.reinit();
        return;
      }
      catch (Exception localException)
      {
        util.printException(localException);
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.sharemem.ShareMemInfoGetBroadcastReceiver
 * JD-Core Version:    0.6.0
 */