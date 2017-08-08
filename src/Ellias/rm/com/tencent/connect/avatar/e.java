package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Button;
import android.widget.ProgressBar;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import org.json.JSONException;
import org.json.JSONObject;

class e
  implements IUiListener
{
  e(ImageActivity paramImageActivity)
  {
  }

  public void onCancel()
  {
  }

  public void onComplete(Object paramObject)
  {
    ImageActivity.e(this.a).setEnabled(true);
    ImageActivity.e(this.a).setTextColor(-1);
    ImageActivity.f(this.a).setEnabled(true);
    ImageActivity.f(this.a).setTextColor(-1);
    ImageActivity.d(this.a).setVisibility(8);
    JSONObject localJSONObject = (JSONObject)paramObject;
    try
    {
      int j = localJSONObject.getInt("ret");
      i = j;
      if (i == 0)
      {
        ImageActivity.b(this.a, "设置成功", 0);
        this.a.a("10658", 0L);
        ImageActivity localImageActivity = this.a;
        if ((ImageActivity.k(this.a) != null) && (!"".equals(ImageActivity.k(this.a))))
        {
          Intent localIntent = new Intent();
          localIntent.setClassName(localImageActivity, ImageActivity.k(this.a));
          if (localImageActivity.getPackageManager().resolveActivity(localIntent, 0) != null)
            localImageActivity.startActivity(localIntent);
        }
        ImageActivity.a(this.a, 0, localJSONObject.toString(), null, null);
        ImageActivity.j(this.a);
        return;
      }
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        int i = -1;
      }
      ImageActivity.b(this.a, "设置出错了，请重新登录再尝试下呢：）", 1);
    }
  }

  public void onError(UiError paramUiError)
  {
    ImageActivity.e(this.a).setEnabled(true);
    ImageActivity.e(this.a).setTextColor(-1);
    ImageActivity.f(this.a).setEnabled(true);
    ImageActivity.f(this.a).setTextColor(-1);
    ImageActivity.f(this.a).setText("重试");
    ImageActivity.d(this.a).setVisibility(8);
    ImageActivity.a(this.a, true);
    ImageActivity.b(this.a, paramUiError.errorMessage, 1);
    this.a.a("10660", 0L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.connect.avatar.e
 * JD-Core Version:    0.6.0
 */