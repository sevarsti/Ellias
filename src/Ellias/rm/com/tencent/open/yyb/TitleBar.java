package com.tencent.open.yyb;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class TitleBar extends RelativeLayout
{
  public static final float BACKBTN_LEFT_MARGIN = 20.0F;
  private static final float BACK_BTN_HEIGHT = 18.0F;
  private static final float BACK_BTN_WIDTH = 11.0F;
  private static final float BACK_PADDING_BOTTOM = 7.0F;
  private static final float BACK_PADDING_LEFT = 15.0F;
  private static final float BACK_PADDING_RIGHT = 20.0F;
  private static final float BACK_PADDING_TOP = 7.0F;
  private static final int ID_BACK_BTN = 10000;
  public static final float SHAREBTN_RIGHT_MARGIN = 10.0F;
  private static final float SHARE_BTN_SIZE = 52.0F;
  private static final String SOURCE_BACK_BTN = "yyb_icon_back.png";
  private static final String SOURCE_MORE_BTN = "yyb_appdetail_showmore.png";
  private static final String SOURCE_TITLEBAR = "yyb_topbar.9.png";
  public static final float TITLEBAR_HEIGHT = 51.0F;
  private ImageView backBtn;
  private RelativeLayout backLayout;
  private float density;
  private ImageView shareBtn;
  private TextView title;

  public TitleBar(Context paramContext)
  {
    super(paramContext);
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.density = localDisplayMetrics.density;
    setLayoutParams(new RelativeLayout.LayoutParams(-1, dip2px(51.0F)));
    setBackgroundDrawable(b.a("yyb_topbar.9.png", paramContext));
    initBackLayout();
    initShareBtn();
  }

  private int dip2px(float paramFloat)
  {
    return (int)(0.5F + paramFloat * this.density);
  }

  private void initBackBtn()
  {
    this.backBtn = new ImageView(getContext());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(dip2px(11.0F), dip2px(18.0F));
    localLayoutParams.addRule(15);
    localLayoutParams.leftMargin = dip2px(20.0F);
    this.backBtn.setId(10000);
    this.backBtn.setLayoutParams(localLayoutParams);
    this.backBtn.setClickable(true);
    this.backBtn.setBackgroundDrawable(b.a("yyb_icon_back.png", getContext()));
    this.backBtn.setPadding(dip2px(15.0F), dip2px(7.0F), dip2px(20.0F), dip2px(7.0F));
    this.backLayout.addView(this.backBtn);
  }

  private void initBackLayout()
  {
    this.backLayout = new RelativeLayout(getContext());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -1);
    this.backLayout.setLayoutParams(localLayoutParams);
    addView(this.backLayout);
    initBackBtn();
    initTitle();
  }

  private void initShareBtn()
  {
    this.shareBtn = new ImageView(getContext());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(dip2px(52.0F), dip2px(52.0F));
    localLayoutParams.addRule(15);
    localLayoutParams.addRule(11);
    ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
    this.shareBtn.setLayoutParams(localLayoutParams);
    this.shareBtn.setClickable(true);
    this.shareBtn.setBackgroundDrawable(b.a("yyb_appdetail_showmore.png", getContext()));
    addView(this.shareBtn);
  }

  private void initTitle()
  {
    this.title = new TextView(getContext());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(15);
    localLayoutParams.addRule(1, 10000);
    localLayoutParams.leftMargin = dip2px(20.0F);
    this.title.setTextColor(Color.parseColor("#fefefe"));
    this.title.setTextSize(20.0F);
    this.title.setShadowLayer(2.0F, 0.0F, 2.0F, Color.parseColor("#2E000000"));
    this.backLayout.addView(this.title, localLayoutParams);
  }

  public RelativeLayout getBackBtn()
  {
    return this.backLayout;
  }

  public ImageView getSharBtn()
  {
    return this.shareBtn;
  }

  public void setTitle(String paramString)
  {
    this.title.setText(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.yyb.TitleBar
 * JD-Core Version:    0.6.0
 */