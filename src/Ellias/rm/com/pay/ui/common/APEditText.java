package com.pay.ui.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.pay.AndroidPay;
import com.pay.tool.APCommMethod;

public class APEditText extends LinearLayout
{
  private EditText a;
  private ImageButton b;
  private Drawable c;
  private Drawable d;

  public APEditText(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.a = new EditText(paramContext);
    this.b = new ImageButton(paramContext);
    this.c = getResources().getDrawable(APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_inputbg_high"));
    this.d = getResources().getDrawable(APCommMethod.getDrawableId(AndroidPay.singleton().applicationContext, "unipay_pic_inputbg_normal"));
    this.b.setImageResource(APCommMethod.getDrawableId(paramContext, "unipay_pic_del"));
    this.b.setPadding(0, 0, 0, 0);
    this.b.setBackgroundColor(8);
    this.a.setHint("请输入");
    this.a.setTextSize(15.0F);
    this.a.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 100.0F));
    addView(this.a);
    addView(this.b);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    setGravity(16);
    setLayoutParams(localLayoutParams);
    this.b.setOnClickListener(new g(this));
    this.a.setOnFocusChangeListener(new h(this));
  }

  public void setHighLight()
  {
    setBackgroundDrawable(this.c);
  }

  public void setNotLight()
  {
    setBackgroundDrawable(this.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APEditText
 * JD-Core Version:    0.6.0
 */