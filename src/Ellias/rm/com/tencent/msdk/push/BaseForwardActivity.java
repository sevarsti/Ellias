package com.tencent.msdk.push;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.tencent.msdk.push.req.ClickStateReq;
import com.tencent.msdk.push.req.ClickStateReq.Callback;
import com.tencent.msdk.tools.Logger;

public class BaseForwardActivity extends Activity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent1 = getPackageManager().getLaunchIntentForPackage(getPackageName());
    Logger.d("launchActivity :" + localIntent1.getComponent().getClassName());
    Intent localIntent2 = new Intent();
    localIntent2.setClassName(this, localIntent1.getComponent().getClassName());
    localIntent2.setFlags(268435456);
    localIntent2.addFlags(536870912);
    localIntent2.putExtras(getIntent());
    startActivity(localIntent2);
    if ((getIntent() != null) && (getIntent().getParcelableExtra("PUSH_MSG") != null))
    {
      ClickStateReq localClickStateReq = new ClickStateReq((MsgEntry)getIntent().getParcelableExtra("PUSH_MSG"));
      localClickStateReq.setmCallback(new ClickStateReq.Callback()
      {
        public void onFail()
        {
          Logger.d("ClickStateReq onFail");
        }

        public void onSuccess()
        {
          Logger.d("ClickStateReq onSuccess");
        }
      });
      localClickStateReq.send();
    }
    finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.BaseForwardActivity
 * JD-Core Version:    0.6.0
 */