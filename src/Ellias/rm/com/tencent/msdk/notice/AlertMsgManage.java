package com.tencent.msdk.notice;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.msdk.stat.ReportEvent;
import com.tencent.msdk.stat.eEVENT_TYPE;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;
import com.tencent.msdk.webview.WebViewManager;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import java.io.File;

public class AlertMsgManage extends RelativeLayout
  implements View.OnClickListener
{
  private AlertMsgActivity mAlertAct;

  public AlertMsgManage(AlertMsgActivity paramAlertMsgActivity)
  {
    super(paramAlertMsgActivity);
    this.mAlertAct = paramAlertMsgActivity;
  }

  public void closeNotice()
  {
    this.mAlertAct.finish();
    NoticeManager.getInstance().deleteAlertNoticeItemFromList();
  }

  public void displayImageNotice(NoticeInfo paramNoticeInfo)
  {
    ((ImageView)this.mAlertAct.findViewById(NoticeResID.alertNoticeImage)).setImageResource(NoticeResID.notice_alert_drawable);
    Button localButton1 = (Button)this.mAlertAct.findViewById(NoticeResID.confirmbtn);
    Button localButton2 = (Button)this.mAlertAct.findViewById(NoticeResID.morebtn);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AlertMsgManage.this.closeNotice();
      }
    });
    if (!T.ckIsEmpty(paramNoticeInfo.mNoticeUrl))
      localButton2.setOnClickListener(new View.OnClickListener(paramNoticeInfo)
      {
        public void onClick(View paramView)
        {
          WebViewManager.getInstance().openUrl(this.val$noticeInfo.mNoticeUrl);
          AlertMsgManage.this.closeNotice();
          ReportEvent.ReportNoticeEvent(eEVENT_TYPE.eEVENT_NOTICE_MORE, this.val$noticeInfo.mNoticeId);
        }
      });
    ImageView localImageView = (ImageView)this.mAlertAct.findViewById(NoticeResID.noticeContent);
    if (eMSDK_SCREENDIR.eMSDK_SCREENDIR_LANDSCAPE == eMSDK_SCREENDIR.getEnum(this.mAlertAct.getResources().getConfiguration()))
    {
      if (T.ckIsEmpty(paramNoticeInfo.mNoticeHImgUrl))
      {
        Logger.e("mNoticeHImgUrl is null");
        return;
      }
      Uri localUri2 = Uri.fromFile(new File(paramNoticeInfo.mNoticeHImgUrl));
      if (localUri2 != null)
        localImageView.setImageURI(localUri2);
    }
    while (true)
    {
      ReportEvent.ReportNoticeEvent(eEVENT_TYPE.eEVENT_NOTICE_SHOW, paramNoticeInfo.mNoticeId);
      return;
      Logger.e("Uri is null");
      return;
      if (T.ckIsEmpty(paramNoticeInfo.mNoticeVImgUrl))
      {
        Logger.e("mNoticeHImgUrl is null");
        return;
      }
      Uri localUri1 = Uri.fromFile(new File(paramNoticeInfo.mNoticeVImgUrl));
      if (localUri1 == null)
        break;
      localImageView.setImageURI(localUri1);
    }
    Logger.e("Uri is null");
  }

  public void displayTextNotice(NoticeInfo paramNoticeInfo)
  {
    ((ImageView)this.mAlertAct.findViewById(NoticeResID.alertNoticeImage)).setImageResource(NoticeResID.notice_alert_drawable);
    ((TextView)this.mAlertAct.findViewById(NoticeResID.noticeTitle)).setText(paramNoticeInfo.mNoticeTitle);
    TextView localTextView = (TextView)this.mAlertAct.findViewById(NoticeResID.noticeContent);
    localTextView.setText(paramNoticeInfo.mNoticeContent);
    localTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
    Button localButton1 = (Button)this.mAlertAct.findViewById(NoticeResID.confirmbtn);
    Button localButton2 = (Button)this.mAlertAct.findViewById(NoticeResID.morebtn);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AlertMsgManage.this.closeNotice();
      }
    });
    if (!T.ckIsEmpty(paramNoticeInfo.mNoticeUrl))
      localButton2.setOnClickListener(new View.OnClickListener(paramNoticeInfo)
      {
        public void onClick(View paramView)
        {
          WebViewManager.getInstance().openUrl(this.val$noticeInfo.mNoticeUrl);
          AlertMsgManage.this.closeNotice();
          ReportEvent.ReportNoticeEvent(eEVENT_TYPE.eEVENT_NOTICE_MORE, this.val$noticeInfo.mNoticeId);
        }
      });
    ReportEvent.ReportNoticeEvent(eEVENT_TYPE.eEVENT_NOTICE_SHOW, paramNoticeInfo.mNoticeId);
  }

  public void displayWebNotice(NoticeInfo paramNoticeInfo)
  {
    ((ImageView)this.mAlertAct.findViewById(NoticeResID.alertNoticeImage)).setImageResource(NoticeResID.notice_alert_drawable);
    Button localButton1 = (Button)this.mAlertAct.findViewById(NoticeResID.confirmbtn);
    Button localButton2 = (Button)this.mAlertAct.findViewById(NoticeResID.morebtn);
    LinearLayout localLinearLayout1 = (LinearLayout)this.mAlertAct.findViewById(NoticeResID.noticeContentLine);
    LinearLayout localLinearLayout2 = (LinearLayout)this.mAlertAct.findViewById(NoticeResID.tempLoadLayer);
    LinearLayout localLinearLayout3 = (LinearLayout)this.mAlertAct.findViewById(NoticeResID.tempLoadFailed);
    localButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AlertMsgManage.this.closeNotice();
      }
    });
    if (!T.ckIsEmpty(paramNoticeInfo.mNoticeUrl))
      localButton2.setOnClickListener(new View.OnClickListener(paramNoticeInfo)
      {
        public void onClick(View paramView)
        {
          WebViewManager.getInstance().openUrl(this.val$noticeInfo.mNoticeUrl);
          AlertMsgManage.this.closeNotice();
          ReportEvent.ReportNoticeEvent(eEVENT_TYPE.eEVENT_NOTICE_MORE, this.val$noticeInfo.mNoticeId);
        }
      });
    WebView localWebView = (WebView)this.mAlertAct.findViewById(NoticeResID.noticeContent);
    localWebView.setWebViewClient(new WebViewClient(localLinearLayout2, localLinearLayout1, localLinearLayout3, localWebView)
    {
      boolean isFailed = false;

      public void onLoadResource(WebView paramWebView, String paramString)
      {
        Logger.d("onLoadResource");
      }

      public void onPageFinished(WebView paramWebView, String paramString)
      {
        Logger.d("onPageFinished");
        if (this.isFailed)
        {
          showError();
          return;
        }
        showContent();
      }

      public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
      {
        Logger.d("onPageStart");
        showLoading();
      }

      public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
      {
        Logger.d("onReceivedError");
        showError();
      }

      public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
      {
        Logger.d("shouldOverrideUrlLoading url:" + paramString);
        paramWebView.loadUrl(paramString);
        return true;
      }

      public void showContent()
      {
        Logger.d("showError");
        this.val$tempLoadLayer.setVisibility(8);
        this.val$noticeContentLine.setVisibility(0);
        this.val$tempLoadFailed.setVisibility(8);
      }

      public void showError()
      {
        Logger.d("showError");
        this.val$noticeContent.stopLoading();
        this.val$tempLoadLayer.setVisibility(8);
        this.val$noticeContentLine.setVisibility(8);
        this.val$tempLoadFailed.setVisibility(0);
        this.isFailed = true;
        this.val$tempLoadFailed.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramView)
          {
            AlertMsgManage.7.this.showLoading();
            AlertMsgManage.7.this.val$noticeContent.reload();
            AlertMsgManage.7.this.isFailed = false;
          }
        });
      }

      public void showLoading()
      {
        Logger.d("showLoading");
        this.val$tempLoadLayer.setVisibility(0);
        this.val$noticeContentLine.setVisibility(8);
        this.val$tempLoadFailed.setVisibility(8);
      }
    });
    localWebView.getSettings().setJavaScriptEnabled(true);
    localWebView.loadUrl(paramNoticeInfo.mNoticeContentWebUrl);
    ReportEvent.ReportNoticeEvent(eEVENT_TYPE.eEVENT_NOTICE_SHOW, paramNoticeInfo.mNoticeId);
  }

  public void onClick(View paramView)
  {
  }

  public void refresh()
  {
    invalidate();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.AlertMsgManage
 * JD-Core Version:    0.6.0
 */