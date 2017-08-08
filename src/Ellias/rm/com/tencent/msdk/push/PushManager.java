package com.tencent.msdk.push;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.tencent.msdk.SimpleCallback;
import com.tencent.msdk.Singleton;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.push.db.PushClientDbModel;
import com.tencent.msdk.push.req.ClientRegReq;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.SharedPreferencesTool;
import com.tencent.msdk.tools.T;
import java.util.Iterator;
import java.util.List;

public class PushManager
{
  public static final Singleton<PushManager> gDefault = new Singleton()
  {
    protected PushManager create()
    {
      return new PushManager(null);
    }
  };

  private boolean ckContainsService(String paramString)
  {
    if (!T.ckIsEmpty(paramString))
    {
      List localList = ((ActivityManager)WeGame.getInstance().getActivity().getSystemService("activity")).getRunningServices(400);
      Logger.d("services number: " + localList.size());
      Iterator localIterator = localList.iterator();
      while (localIterator.hasNext())
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
        if ((!paramString.equals(localRunningServiceInfo.service.getClassName())) || (!".msdk_push_v_1".equals(localRunningServiceInfo.process)))
          continue;
        Logger.d("service existed!");
        return true;
      }
    }
    return false;
  }

  public static boolean needRegiste()
  {
    return T.ckIsEmpty(PushClientDbModel.getMatKey());
  }

  public boolean isAppRegistered(Context paramContext, String paramString)
  {
    if (T.ckIsEmpty(PushClientDbModel.getMatKey()))
    {
      Logger.d("isAppRegistered no matKey");
      return false;
    }
    return SharedPreferencesTool.getBoolean(paramContext, paramString, false);
  }

  public boolean isAppUserRegistered(Context paramContext, String paramString1, String paramString2)
  {
    if (T.ckIsEmpty(PushClientDbModel.getMatKey()))
    {
      Logger.d("isAppUserRegistered no matKey");
      return false;
    }
    return SharedPreferencesTool.getBoolean(paramContext, paramString1 + ":" + paramString2, false);
  }

  public void registerAppOnce(Activity paramActivity, String paramString1, String paramString2)
  {
    String str = paramString1 + "|" + paramString2;
    if (!isAppRegistered(paramActivity, str))
    {
      ClientRegReq localClientRegReq = new ClientRegReq(str);
      localClientRegReq.setmCallback(new SimpleCallback()
      {
        public void onFail()
        {
          Logger.d("AppUser Reg Fail, qqAppId: " + WeGame.getInstance().qq_appid + "; wxAppId:" + WeGame.getInstance().wx_appid);
        }

        public void onSuccess()
        {
          Logger.d("called");
          PushManager.this.setAppRegistered(WeGame.getInstance().getActivity(), WeGame.getInstance().qq_appid, WeGame.getInstance().wx_appid);
          PushManager.this.startPushPoll(WeGame.getInstance().getActivity(), WeGame.getInstance().qq_appid, WeGame.getInstance().wx_appid);
        }
      });
      localClientRegReq.send();
      return;
    }
    Logger.d("AppId " + str + " registered");
    startPushPoll(WeGame.getInstance().getActivity(), WeGame.getInstance().qq_appid, WeGame.getInstance().wx_appid);
  }

  public void registerAppUserOnce(Activity paramActivity, String paramString1, String paramString2)
  {
    if (!isAppUserRegistered(paramActivity, paramString1, paramString2))
    {
      ClientRegReq localClientRegReq = new ClientRegReq(paramString1, paramString2);
      localClientRegReq.setmCallback(new SimpleCallback(paramString1, paramString2)
      {
        public void onFail()
        {
          Logger.d("AppUser Reg Fail, AppId: " + this.val$appId + "; OpenId" + this.val$openId);
        }

        public void onSuccess()
        {
          PushManager.this.setAppUserRegistered(WeGame.getInstance().getActivity(), this.val$appId, this.val$openId);
          PushManager.this.startPushPoll(WeGame.getInstance().getActivity(), WeGame.getInstance().qq_appid, WeGame.getInstance().wx_appid);
        }
      });
      localClientRegReq.send();
      return;
    }
    Logger.d("AppId " + paramString1 + "; OpenID: " + paramString2 + " registered");
    startPushPoll(WeGame.getInstance().getActivity(), WeGame.getInstance().qq_appid, WeGame.getInstance().wx_appid);
  }

  public void setAppRegistered(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferencesTool.putBoolean(paramContext, paramString1 + "|" + paramString2, true);
  }

  public void setAppUserRegistered(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferencesTool.putBoolean(paramContext, paramString1 + ":" + paramString2, true);
  }

  public void startPushPoll(Activity paramActivity, String paramString1, String paramString2)
  {
    Logger.d("called");
    if (needRegiste())
    {
      Logger.d("matKey not existed, need register");
      return;
    }
    Logger.d("called");
    startPushService();
  }

  public void startPushService()
  {
    Logger.d("called");
    if (!ckContainsService(HttpPushService.class.getName()))
    {
      Intent localIntent = new Intent(WeGame.getInstance().getActivity(), HttpPushService.class);
      WeGame.getInstance().getActivity().startService(localIntent);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.PushManager
 * JD-Core Version:    0.6.0
 */