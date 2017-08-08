package com.tencent.msdk.weixin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.LaunchFromWX.Req;
import com.tencent.mm.sdk.modelmsg.SendAuth.Resp;
import com.tencent.mm.sdk.modelmsg.ShowMessageFromWX.Req;
import com.tencent.mm.sdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.msdk.WeGame;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;

public class BaseWXEntryActivity extends Activity
  implements IWXAPIEventHandler
{
  private static final String TAG = BaseWXEntryActivity.class.getName();
  private String messageExt;
  private String platformId;

  private void TestPlatform(Intent paramIntent)
  {
    if ((paramIntent == null) || (paramIntent.getExtras() == null))
    {
      Logger.d(TAG, "wx intent is NULL");
      return;
    }
    Logger.d(paramIntent.getExtras());
    Logger.d(TAG, "intent content end");
  }

  private void initEntry()
  {
    WeGame.getInstance().api = WXAPIFactory.createWXAPI(this, WeGame.getInstance().wx_appid, true);
    WeGame.getInstance().api.registerApp(WeGame.getInstance().wx_appid);
    WeGame.getInstance().api.handleIntent(getIntent(), this);
  }

  private void setPlatformInfo(Intent paramIntent)
  {
    if ((paramIntent != null) && (paramIntent.getExtras() != null))
    {
      Bundle localBundle = getIntent().getExtras();
      this.platformId = localBundle.getString("platformId");
      this.messageExt = localBundle.getString("_wxobject_message_ext");
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    TestPlatform(getIntent());
    setPlatformInfo(getIntent());
    initEntry();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    TestPlatform(getIntent());
    setIntent(paramIntent);
    setPlatformInfo(paramIntent);
    initEntry();
  }

  public void onReq(BaseReq paramBaseReq)
  {
    Intent localIntent;
    if (paramBaseReq.openId == null)
    {
      Logger.d("OpenID Null");
      if (paramBaseReq.openId == null)
        paramBaseReq.openId = "";
      localIntent = getPackageManager().getLaunchIntentForPackage(getPackageName());
      localIntent.setFlags(268435456);
      localIntent.addFlags(536870912);
      localIntent.putExtra("wx_callback", "onReq");
      Logger.d("onReq" + this.messageExt);
      if (!(paramBaseReq instanceof LaunchFromWX.Req))
        break label248;
      localIntent.putExtra("wx_mediaTagName", "wgWXGameRecommend");
      LaunchFromWX.Req localReq1 = (LaunchFromWX.Req)paramBaseReq;
      localIntent.putExtra("messageExt", localReq1.messageExt);
      localIntent.putExtra("country", localReq1.country);
      localIntent.putExtra("lang", localReq1.lang);
    }
    while (true)
    {
      localIntent.putExtra("wx_transaction", paramBaseReq.transaction);
      localIntent.putExtra("wx_openId", paramBaseReq.openId);
      localIntent.putExtra("platformId", this.platformId);
      Logger.d("打印最终给msdk 的intent ---- s\n");
      Logger.d(localIntent);
      Logger.d("打印最终给msdk 的intent ---- e\n");
      startActivity(localIntent);
      finish();
      return;
      if ("".equals(paramBaseReq.openId))
      {
        Logger.d("OpenID is empty");
        break;
      }
      Logger.d("OpenID : " + paramBaseReq.openId);
      break;
      label248: if (!(paramBaseReq instanceof ShowMessageFromWX.Req))
        continue;
      ShowMessageFromWX.Req localReq = (ShowMessageFromWX.Req)paramBaseReq;
      localIntent.putExtra("wx_mediaTagName", ((WXAppExtendObject)localReq.message.mediaObject).extInfo);
      WXMediaMessage localWXMediaMessage = localReq.message;
      localIntent.putExtra("country", localReq.country);
      localIntent.putExtra("lang", localReq.lang);
      Logger.d("mediaMsg.messageExt" + localWXMediaMessage.messageExt);
      if (T.ckIsEmpty(localWXMediaMessage.messageExt))
      {
        localIntent.putExtra("messageExt", this.messageExt);
        continue;
      }
      localIntent.putExtra("messageExt", localWXMediaMessage.messageExt);
    }
  }

  public void onResp(BaseResp paramBaseResp)
  {
    Intent localIntent1 = getPackageManager().getLaunchIntentForPackage(getPackageName());
    try
    {
      Intent localIntent2 = new Intent(this, Class.forName(localIntent1.getComponent().getClassName()));
      localIntent2.setFlags(268435456);
      localIntent2.addFlags(536870912);
      Logger.d("getComponent" + localIntent1.getComponent().flattenToString());
      Logger.d("getComponent" + localIntent1.getComponent().getClassName());
      localIntent2.putExtra("wx_callback", "onResp");
      localIntent2.putExtra("wx_errCode", paramBaseResp.errCode);
      localIntent2.putExtra("wx_errStr", paramBaseResp.errStr);
      localIntent2.putExtra("wx_transaction", paramBaseResp.transaction);
      localIntent2.putExtra("wx_openId", paramBaseResp.openId);
      localIntent2.putExtra("platformId", this.platformId);
      if ((paramBaseResp instanceof SendAuth.Resp))
      {
        SendAuth.Resp localResp = (SendAuth.Resp)paramBaseResp;
        Logger.d("code: " + localResp.code);
        localIntent2.putExtra("wx_token", localResp.code);
      }
      startActivity(localIntent2);
      finish();
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.weixin.BaseWXEntryActivity
 * JD-Core Version:    0.6.0
 */