package com.tencent.msdk.notice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.TextView;
import com.tencent.msdk.tools.Logger;

public class RollTextView extends TextView
  implements View.OnClickListener
{
  public static final String TAG = RollTextView.class.getSimpleName();
  public boolean isStarting = false;
  private Paint paint = null;
  private float step = 0.0F;
  private float temp_view_plus_text_length = 0.0F;
  private float temp_view_plus_two_text_length = 0.0F;
  private String text = "";
  private int textColor = Color.parseColor("#B4B4B4");
  private float textLength = 0.0F;
  private float viewWidth = 0.0F;
  private float y = 15.0F;

  public RollTextView(Context paramContext)
  {
    super(paramContext);
    initView();
  }

  public RollTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }

  public RollTextView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView();
  }

  private void initView()
  {
    setOnClickListener(this);
  }

  public void init(WindowManager paramWindowManager)
  {
    this.paint = getPaint();
    this.text = getText().toString();
    this.paint.setColor(this.textColor);
    this.textLength = this.paint.measureText(this.text);
    this.viewWidth = getWidth();
    if ((this.viewWidth == 0.0F) && (paramWindowManager != null))
      this.viewWidth = paramWindowManager.getDefaultDisplay().getWidth();
    this.step = this.textLength;
    this.temp_view_plus_text_length = (this.viewWidth + this.textLength);
    this.temp_view_plus_two_text_length = (this.viewWidth + 2.0F * this.textLength);
    this.y = (getTextSize() + getPaddingTop());
    Logger.d("getTextSize:" + getTextSize() + ";getPaddingTop:" + getPaddingTop());
  }

  public void onClick(View paramView)
  {
    if (this.isStarting)
    {
      stopScroll();
      return;
    }
    startScroll();
  }

  public void onDraw(Canvas paramCanvas)
  {
    paramCanvas.drawText(this.text, this.temp_view_plus_text_length - this.step, this.y, this.paint);
    if (!this.isStarting)
      return;
    this.step = (2.0F + this.step);
    if (this.step > this.temp_view_plus_two_text_length)
      this.step = this.textLength;
    invalidate();
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    SavedState localSavedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(localSavedState.getSuperState());
    this.step = localSavedState.step;
    this.isStarting = localSavedState.isStarting;
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.step = this.step;
    localSavedState.isStarting = this.isStarting;
    return localSavedState;
  }

  public void startScroll()
  {
    this.isStarting = true;
    invalidate();
  }

  public void stopScroll()
  {
    this.isStarting = false;
    invalidate();
  }

  public static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public RollTextView.SavedState createFromParcel(Parcel paramParcel)
      {
        return new RollTextView.SavedState(paramParcel, null);
      }

      public RollTextView.SavedState[] newArray(int paramInt)
      {
        return new RollTextView.SavedState[paramInt];
      }
    };
    public boolean isStarting = false;
    public float step = 0.0F;

    private SavedState(Parcel paramParcel)
    {
      super();
      paramParcel.readBooleanArray(null);
      if ((0 != 0) && (null.length > 0))
        throw new NullPointerException();
      this.step = paramParcel.readFloat();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      boolean[] arrayOfBoolean = new boolean[1];
      arrayOfBoolean[0] = this.isStarting;
      paramParcel.writeBooleanArray(arrayOfBoolean);
      paramParcel.writeFloat(this.step);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.notice.RollTextView
 * JD-Core Version:    0.6.0
 */