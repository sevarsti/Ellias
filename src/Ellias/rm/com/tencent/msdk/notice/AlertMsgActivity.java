package com.tencent.msdk.notice;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.tencent.msdk.tools.Logger;

public class AlertMsgActivity extends Activity
{
  private AlertMsgManage mAlertMsgManage = null;
  private NoticeInfo mNoticeInfo = null;

  private void resizeView()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    getWindow().setFormat(-3);
  }

  private void showNotice()
  {
    Logger.d("AlertActivity:Id" + this.mNoticeInfo.mNoticeId + ";Url:" + this.mNoticeInfo.mNoticeUrl + ";Type:" + this.mNoticeInfo.mNoticeContentType);
    if ((this.mNoticeInfo.mNoticeUrl == null) || (this.mNoticeInfo.mNoticeUrl.equals("")))
      switch (1.$SwitchMap$com$tencent$msdk$notice$eMSG_CONTENTTYPE[this.mNoticeInfo.mNoticeContentType.ordinal()])
      {
      default:
      case 1:
      case 2:
      case 3:
      }
    while (true)
    {
      resizeView();
      Logger.d("AlertActivity:" + this.mNoticeInfo.mNoticeTitle);
      return;
      NoticeResID.loadTextLayout(this);
      setContentView(NoticeResID.layout_text_notice);
      this.mAlertMsgManage.displayTextNotice(this.mNoticeInfo);
      continue;
      NoticeResID.loadImageLayout(this);
      setContentView(NoticeResID.layout_image_notice);
      this.mAlertMsgManage.displayImageNotice(this.mNoticeInfo);
      continue;
      NoticeResID.loadWebLayout(this);
      setContentView(NoticeResID.layout_web_notice);
      this.mAlertMsgManage.displayWebNotice(this.mNoticeInfo);
      continue;
      switch (1.$SwitchMap$com$tencent$msdk$notice$eMSG_CONTENTTYPE[this.mNoticeInfo.mNoticeContentType.ordinal()])
      {
      default:
        break;
      case 1:
        NoticeResID.loadTextLayout(this);
        setContentView(NoticeResID.layout_text_notice_url);
        this.mAlertMsgManage.displayTextNotice(this.mNoticeInfo);
        break;
      case 2:
        NoticeResID.loadImageLayout(this);
        setContentView(NoticeResID.layout_image_notice_url);
        this.mAlertMsgManage.displayImageNotice(this.mNoticeInfo);
        break;
      case 3:
        NoticeResID.loadWebLayout(this);
        setContentView(NoticeResID.layout_web_notice_url);
        this.mAlertMsgManage.displayWebNotice(this.mNoticeInfo);
      }
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    Logger.d("onConfigurationChanged");
    showNotice();
  }

  protected void onCreate(Bundle paramBundle)
  {
    Logger.d("AlertActivity init");
    super.onCreate(paramBundle);
    this.mNoticeInfo = ((NoticeInfo)getIntent().getParcelableExtra("alertMsg"));
    this.mAlertMsgManage = new AlertMsgManage(this);
    showNotice();
    Logger.d("AlertActivity init finished");
  }

  protected void onDestroy()
  {
    Logger.d("Notice activity onDestory");
    super.onDestroy();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    Logger.d(paramIntent);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.AlertMsgActivity
 * JD-Core Version:    0.6.0
 */