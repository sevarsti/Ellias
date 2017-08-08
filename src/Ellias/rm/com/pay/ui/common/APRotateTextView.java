package com.pay.ui.common;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.pay.tool.APLog;

public class APRotateTextView extends TextView
{
  private int a;
  private int b;
  private int c = 10;
  private int d = 7;

  public APRotateTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.a = paramAttributeSet.getAttributeIntValue("http://www.tencent.com/apk/res/midas", "rotate", 0);
    this.b = paramAttributeSet.getAttributeIntValue("http://www.tencent.com/apk/res/midas", "bgColor", -16776961);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    Path localPath = new Path();
    localPath.moveTo(getMeasuredWidth() / 2, 0.0F);
    localPath.lineTo(getMeasuredWidth(), 3 * getMeasuredHeight() / 4);
    localPath.lineTo(getMeasuredWidth(), 0.0F);
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new PathShape(localPath, getMeasuredWidth(), getMeasuredHeight()));
    localShapeDrawable.getPaint().setColor(this.b);
    localShapeDrawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    localShapeDrawable.draw(paramCanvas);
    int i = APUICommonMethod.dip2px(getContext(), this.c);
    int j = APUICommonMethod.dip2px(getContext(), this.d);
    paramCanvas.rotate(this.a, getMeasuredWidth() - i, getMeasuredHeight() - j);
    super.onDraw(paramCanvas);
  }

  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    String str = paramCharSequence.toString().trim();
    byte[] arrayOfByte = str.getBytes();
    APLog.d("aa2:", str + ": " + arrayOfByte.length);
    if (arrayOfByte.length != str.length())
      if (str.length() > 3)
        str = str.substring(0, 3) + "...";
    while (true)
    {
      super.setText(str, paramBufferType);
      return;
      if (arrayOfByte.length <= 6)
        continue;
      str = new String(arrayOfByte, 0, 6) + "...";
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APRotateTextView
 * JD-Core Version:    0.6.0
 */