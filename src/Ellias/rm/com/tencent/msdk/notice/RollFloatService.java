package com.tencent.msdk.notice;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.tencent.msdk.tools.Logger;

public class RollFloatService extends Service
{
  private String ScrollMsg = "";
  private RollTextView autoScrollTextView = null;
  LinearLayout mFloatLayout;
  ImageView mFloatView;
  WindowManager mWindowManager;
  private ImageView rollImage = null;
  WindowManager.LayoutParams wmParams;

  private void createFloatView()
  {
    NoticeResID.loadScrollLayout(this);
    this.wmParams = new WindowManager.LayoutParams();
    getApplication();
    this.mWindowManager = ((WindowManager)getApplication().getSystemService("window"));
    Log.i(Logger.DEFAULT_TAG, "mWindowManager--->" + this.mWindowManager);
    this.wmParams.type = 2002;
    this.wmParams.format = 1;
    this.wmParams.flags = 8;
    this.wmParams.gravity = 51;
    this.wmParams.x = 0;
    this.wmParams.y = 0;
    this.wmParams.width = -1;
    this.wmParams.height = -2;
    this.mFloatLayout = ((LinearLayout)LayoutInflater.from(getApplication()).inflate(NoticeResID.layout_scroll_notice, null));
    this.mWindowManager.addView(this.mFloatLayout, this.wmParams);
    initMarquee();
    this.rollImage = ((ImageView)this.mFloatLayout.findViewById(NoticeResID.rollImage));
    this.rollImage.setImageResource(NoticeResID.notice_roll_drawable);
    this.mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
  }

  private void initMarquee()
  {
    String str = this.ScrollMsg;
    this.autoScrollTextView = ((RollTextView)this.mFloatLayout.findViewById(NoticeResID.marquee));
    this.autoScrollTextView.setText(str);
    this.autoScrollTextView.init(this.mWindowManager);
    this.autoScrollTextView.startScroll();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    Log.i(Logger.DEFAULT_TAG, "oncreat");
  }

  public void onDestroy()
  {
    if (this.mFloatLayout != null)
    {
      this.mWindowManager.removeView(this.mFloatLayout);
      this.mFloatLayout = null;
    }
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    super.onStartCommand(paramIntent, paramInt1, paramInt2);
    if (this.mFloatLayout != null)
      return 0;
    Log.i(Logger.DEFAULT_TAG, "onStartCommand");
    this.ScrollMsg = paramIntent.getStringExtra("rollMsg");
    createFloatView();
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.RollFloatService
 * JD-Core Version:    0.6.0
 */