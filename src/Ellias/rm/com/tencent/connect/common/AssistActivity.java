package com.tencent.connect.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AssistActivity extends Activity
{
  private static final String RESTART_FLAG = "RESTART_FLAG";
  private static final String TAG = "Donald";
  private static BaseApi sApiObject;
  private BaseApi mAPiObject;

  public static Intent getAssistActivityIntent(Context paramContext)
  {
    return new Intent(paramContext, AssistActivity.class);
  }

  public static void setApiObject(BaseApi paramBaseApi)
  {
    sApiObject = paramBaseApi;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    Log.d("Donald", "AssistActivity--onActivityResult--");
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.mAPiObject != null)
      this.mAPiObject.onActivityResult(paramInt1, paramInt2, paramIntent);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    Log.d("Donald", "AssistActivity--onCreate--");
    if (sApiObject == null)
      finish();
    int i;
    boolean bool;
    do
    {
      return;
      this.mAPiObject = sApiObject;
      sApiObject = null;
      i = this.mAPiObject.getActivityIntent().getIntExtra("key_request_code", 0);
      bool = false;
      if (paramBundle == null)
        continue;
      bool = paramBundle.getBoolean("RESTART_FLAG");
    }
    while (bool);
    startActivityForResult(this.mAPiObject.getActivityIntent(), i);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    Log.d("Donald", "AssistActivity--onSaveInstanceState--");
    paramBundle.putBoolean("RESTART_FLAG", true);
    super.onSaveInstanceState(paramBundle);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.common.AssistActivity
 * JD-Core Version:    0.6.0
 */