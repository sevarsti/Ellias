package com.tencent.open.yyb;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.a.a.d;
import java.util.HashMap;

public class MoreFloatingDialog extends Dialog
{
  private static final float NINE_PATCH_TOP = 9.0F;
  private static final float SHARE_ICON_SIZE = 28.0F;
  private static final String SOURCE_FLOATING_BG = "yyb_appdetail_bg_floatingwindow.9.png";
  private static final String SOURCE_FRIENDS_BG = "yyb_friends.png";
  private static final String SOURCE_QQ_BG = "yyb_qq.png";
  private static final String SOURCE_QZONE_BG = "yyb_qzone.png";
  private static final String SOURCE_WEIXIN_BG = "yyb_weixin.png";
  private float density;
  private RelativeLayout mContentView;
  private LinearLayout mRootView;
  private HashMap<String, TextView> mShareItems = new HashMap(4);
  private Rect ninePatchRect = new Rect(0, dip2px(9.0F), 0, 0);

  public MoreFloatingDialog(Context paramContext)
  {
    super(paramContext, 16973840);
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.density = localDisplayMetrics.density;
    d.b("openSDK_LOG", "-->(MoreFloatingDialog) : density = " + this.density);
    LinearLayout.LayoutParams localLayoutParams1 = new LinearLayout.LayoutParams(-2, -2);
    this.mContentView = new RelativeLayout(paramContext);
    this.mContentView.setLayoutParams(localLayoutParams1);
    this.mContentView.setBackgroundDrawable(b.a("yyb_appdetail_bg_floatingwindow.9.png", paramContext, this.ninePatchRect));
    this.mRootView = new LinearLayout(paramContext);
    LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(-1, -2);
    this.mRootView.setLayoutParams(localLayoutParams2);
    this.mShareItems.put("yyb_qq.png", addShareItemText(b.a("yyb_qq.png", getContext(), this.ninePatchRect), "QQ分享"));
    this.mShareItems.put("yyb_qzone.png", addShareItemText(b.a("yyb_qzone.png", getContext(), this.ninePatchRect), "空间分享"));
    this.mContentView.addView(this.mRootView, localLayoutParams2);
    setContentView(this.mContentView);
  }

  private void addHorizontalDivider()
  {
    ImageView localImageView = new ImageView(getContext());
    localImageView.setBackgroundColor(Color.parseColor("#33ffffff"));
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(dip2px(1.0F), -1);
    this.mRootView.addView(localImageView, localLayoutParams);
  }

  private TextView addShareItemText(Drawable paramDrawable, String paramString)
  {
    if (paramDrawable != null)
      paramDrawable.setBounds(0, 0, dip2px(28.0F), dip2px(28.0F));
    TextView localTextView = new TextView(getContext());
    localTextView.setTextColor(-1);
    localTextView.setCompoundDrawables(null, paramDrawable, null, null);
    localTextView.setTextSize(13.0F);
    localTextView.setCompoundDrawablePadding(dip2px(8.0F));
    localTextView.setPadding(0, dip2px(19.0F), 0, dip2px(19.0F));
    localTextView.setGravity(1);
    localTextView.setText(paramString);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, -2);
    localLayoutParams.weight = 1.0F;
    localLayoutParams.gravity = 1;
    this.mRootView.addView(localTextView, localLayoutParams);
    addHorizontalDivider();
    return localTextView;
  }

  public int dip2px(float paramFloat)
  {
    return (int)(0.5F + paramFloat * this.density);
  }

  public int getContentViewHeight()
  {
    return this.mContentView.getHeight();
  }

  public int getHeight()
  {
    return this.mContentView.getHeight();
  }

  public View getQQItem()
  {
    return (View)this.mShareItems.get("yyb_qq.png");
  }

  public View getQzoneItem()
  {
    return (View)this.mShareItems.get("yyb_qzone.png");
  }

  public View getTimelineItem()
  {
    return (View)this.mShareItems.get("yyb_friends.png");
  }

  public View getWXItem()
  {
    return (View)this.mShareItems.get("yyb_weixin.png");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.MoreFloatingDialog
 * JD-Core Version:    0.6.0
 */