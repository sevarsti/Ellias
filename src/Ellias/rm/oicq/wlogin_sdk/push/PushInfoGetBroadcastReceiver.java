package oicq.wlogin_sdk.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.ArrayList;
import oicq.wlogin_sdk.tools.util;

public class PushInfoGetBroadcastReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    util.LOGD(push_client.PUSH_GET_UINFO_RECEIVED + ": recved");
    uin_app_info[] arrayOfuin_app_info;
    ArrayList localArrayList;
    if (paramIntent.getAction().equals(push_client.PUSH_GET_UINFO_RECEIVED))
    {
      arrayOfuin_app_info = push_client.loadUinPush(paramContext);
      if ((arrayOfuin_app_info != null) && (arrayOfuin_app_info.length > 0))
      {
        util.LOGD(push_client.PUSH_SET_UINFO_RECEIVED + ": send");
        localArrayList = new ArrayList();
      }
    }
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfuin_app_info.length)
      {
        Intent localIntent = new Intent(push_client.PUSH_SET_UINFO_RECEIVED);
        localIntent.putParcelableArrayListExtra("UINFO", localArrayList);
        paramContext.sendBroadcast(localIntent);
        return;
      }
      localArrayList.add(arrayOfuin_app_info[i]);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.PushInfoGetBroadcastReceiver
 * JD-Core Version:    0.6.0
 */