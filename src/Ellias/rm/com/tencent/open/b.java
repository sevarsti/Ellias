package com.tencent.open;

import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;

public class b extends Dialog
{
  private TextView a;
  private TextView b;
  private Button c;
  private Button d;

  public b(Context paramContext)
  {
    super(paramContext);
    ColorDrawable localColorDrawable = new ColorDrawable();
    localColorDrawable.setAlpha(0);
    getWindow().setBackgroundDrawable(localColorDrawable);
    setContentView(a(paramContext));
  }

  private Drawable a(String paramString, Context paramContext)
  {
    AssetManager localAssetManager = paramContext.getApplicationContext().getAssets();
    InputStream localInputStream;
    Object localObject;
    IOException localIOException2;
    try
    {
      localInputStream = localAssetManager.open(paramString);
      if (localInputStream == null)
        return null;
      if (paramString.endsWith(".9.png"))
      {
        Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
        if (localBitmap != null)
        {
          NinePatchDrawable localNinePatchDrawable = new NinePatchDrawable(localBitmap, localBitmap.getNinePatchChunk(), new Rect(), null);
          return localNinePatchDrawable;
        }
      }
    }
    catch (IOException localIOException1)
    {
      localObject = null;
      localIOException2 = localIOException1;
    }
    while (true)
    {
      localIOException2.printStackTrace();
      return localObject;
      return null;
      Drawable localDrawable = Drawable.createFromStream(localInputStream, paramString);
      localObject = localDrawable;
      try
      {
        localInputStream.close();
        return localObject;
      }
      catch (IOException localIOException3)
      {
      }
    }
  }

  private View a(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f = localDisplayMetrics.density;
    (int)(60.0F * f);
    (int)(60.0F * f);
    (int)(14.0F * f);
    (int)(18.0F * f);
    (int)(6.0F * f);
    (int)(18.0F * f);
    RelativeLayout localRelativeLayout = new RelativeLayout(paramContext);
    RelativeLayout.LayoutParams localLayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
    this.a = new TextView(paramContext);
    this.a.setTextSize(17.0F);
    this.a.setId(10);
    this.a.getPaint().setFakeBoldText(true);
    localLayoutParams1.addRule(14);
    localLayoutParams1.setMargins(0, 20, 0, 0);
    localRelativeLayout.addView(this.a, localLayoutParams1);
    this.b = new TextView(paramContext);
    this.b.setTextSize(16.0F);
    this.b.setIncludeFontPadding(false);
    localLayoutParams1.setMargins(0, 20, 0, 0);
    this.b.setLines(2);
    this.b.setId(11);
    this.b.setMinWidth((int)(185.0F * f));
    RelativeLayout.LayoutParams localLayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams2.addRule(3, 10);
    localRelativeLayout.addView(this.b, localLayoutParams2);
    View localView = new View(paramContext);
    localView.setBackgroundColor(Color.rgb(214, 214, 214));
    localView.setId(12);
    RelativeLayout.LayoutParams localLayoutParams3 = new RelativeLayout.LayoutParams(-2, 2);
    localLayoutParams3.addRule(3, 11);
    localLayoutParams3.setMargins(0, 10, 0, (int)(12.0F * f));
    localRelativeLayout.addView(localView, localLayoutParams3);
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    RelativeLayout.LayoutParams localLayoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams4.addRule(5, 12);
    localLayoutParams4.addRule(7, 12);
    localLayoutParams4.addRule(3, 12);
    this.c = new Button(paramContext);
    Drawable localDrawable1 = a("buttonNegt.png", paramContext);
    this.c.setBackgroundDrawable(localDrawable1);
    this.c.setTextColor(Color.rgb(36, 97, 131));
    this.c.setTextSize(18.0F);
    this.c.setId(14);
    LinearLayout.LayoutParams localLayoutParams5 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams5.weight = 1.0F;
    localLayoutParams5.rightMargin = (int)(14.0F * f);
    localLayoutParams5.leftMargin = (int)(4.0F * f);
    localLinearLayout.addView(this.c, localLayoutParams5);
    this.d = new Button(paramContext);
    this.d.setTextSize(18.0F);
    this.d.setTextColor(Color.rgb(255, 255, 255));
    Drawable localDrawable2 = a("buttonPost.png", paramContext);
    this.d.setBackgroundDrawable(localDrawable2);
    LinearLayout.LayoutParams localLayoutParams6 = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams6.weight = 1.0F;
    localLayoutParams6.rightMargin = (int)(4.0F * f);
    localLinearLayout.addView(this.d, localLayoutParams6);
    localRelativeLayout.addView(localLinearLayout, localLayoutParams4);
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams((int)(279.0F * f), (int)(163.0F * f));
    localRelativeLayout.setPadding((int)(10.0F * f), 0, (int)(10.0F * f), (int)(12.0F * f));
    localRelativeLayout.setLayoutParams(localLayoutParams);
    localRelativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
    PaintDrawable localPaintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
    localPaintDrawable.setCornerRadius(f * 5.0F);
    localRelativeLayout.setBackgroundDrawable(localPaintDrawable);
    return localRelativeLayout;
  }

  public b a(View.OnClickListener paramOnClickListener)
  {
    this.d.setOnClickListener(paramOnClickListener);
    return this;
  }

  public b a(String paramString)
  {
    this.a.setText(paramString);
    return this;
  }

  public b b(View.OnClickListener paramOnClickListener)
  {
    this.c.setOnClickListener(paramOnClickListener);
    return this;
  }

  public b b(String paramString)
  {
    this.b.setText(paramString);
    return this;
  }

  public b c(String paramString)
  {
    this.c.setText(paramString);
    return this;
  }

  public b d(String paramString)
  {
    this.d.setText(paramString);
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.b
 * JD-Core Version:    0.6.0
 */