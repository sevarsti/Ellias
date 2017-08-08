package com.tencent.android.tpush;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.android.tpush.logging.TLog;

public class XGPushActivity extends Activity
{
  private void broadcastToTPushService(Intent paramIntent)
  {
    Intent localIntent = new Intent("com.tencent.android.tpush.action.PUSH_CLICK.RESULT");
    localIntent.putExtras(paramIntent);
    localIntent.putExtra("packName", paramIntent.getPackage());
    localIntent.putExtra("clickTime", System.currentTimeMillis() / 1000L);
    sendBroadcast(localIntent);
  }

  private void pushClickedResult(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("activity");
    TLog.i("XGPushMessage", "activity intent =" + paramIntent + "activity = " + str + "intent.getFlags()" + paramIntent.getFlags());
    Intent localIntent = new Intent();
    localIntent.addFlags(paramIntent.getFlags());
    localIntent.setClassName(getApplicationContext(), str);
    paramIntent.putExtra("tag.tpush.MSG", "true");
    localIntent.putExtras(paramIntent);
    localIntent.putExtra("tag.tpush.NOTIFIC", XGPushManager.onClickResult(this));
    TLog.e("XGPushMessage", "notifaction intent flag:" + localIntent.getFlags());
    try
    {
      startActivity(localIntent);
      label137: finish();
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      break label137;
    }
  }

  private void showAlertDialog(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("url");
    new AlertDialog.Builder(this).setTitle("提示").setCancelable(false).setMessage("继续浏览网站?").setPositiveButton("确认", new b(this, str, paramIntent)).setNegativeButton("取消", new a(this, paramIntent)).show();
    TLog.e("TPush", "onCreate");
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    pushClickedResult(getIntent());
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void onStart()
  {
    super.onStart();
  }

  protected void onStop()
  {
    super.onStop();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.XGPushActivity
 * JD-Core Version:    0.6.0
 */