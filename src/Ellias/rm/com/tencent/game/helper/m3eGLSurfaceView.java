package com.tencent.game.helper;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

class m3eGLSurfaceView extends GLSurfaceView
{
  private static final int HANDLER_CLOSE_IME_KEYBOARD = 3;
  private static final int HANDLER_OPEN_IME_KEYBOARD = 2;
  private static int e1x = 0;
  private static int e2x = 0;
  private static Handler handler;
  m3eRenderer mRenderer;
  private TextView mTextField;
  private TextInputWraper textInputWraper = new TextInputWraper(this);

  public m3eGLSurfaceView(Context paramContext)
  {
    super(paramContext);
    Log.e("m3e", "m3eGLSurfaceView");
    this.mRenderer = new m3eRenderer((Activity)paramContext);
    setRenderer(this.mRenderer);
    initView();
  }

  public m3eGLSurfaceView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Log.e("m3e", "m3eGLSurfaceView2");
    this.mRenderer = new m3eRenderer((Activity)paramContext);
    setRenderer(this.mRenderer);
    initView();
  }

  public static void _ImeSetKeyboardText(String paramString)
  {
  }

  public static void _ImeSetOKButtonText(String paramString)
  {
  }

  public static void _ImeShowOKButton(int paramInt)
  {
  }

  public static void closeIMEKeuBoard()
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    handler.sendMessage(localMessage);
  }

  private void hideImeKeyBoard()
  {
    ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.mTextField.getWindowToken(), 0);
  }

  private void initView()
  {
    handler = new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        switch (paramMessage.what)
        {
        default:
        case 2:
        case 3:
        }
        do
        {
          do
          {
            return;
            Log.d("m3eGLSurfaceView", "HANDLER_OPEN_IME_KEYBOARD");
          }
          while ((m3eGLSurfaceView.this.mTextField == null) || (!m3eGLSurfaceView.this.mTextField.requestFocus()));
          m3eGLSurfaceView.this.showImeKeyBoard((String)paramMessage.obj, paramMessage.arg1, paramMessage.arg2);
          return;
          Log.d("m3eGLSurfaceView", "HANDLER_OPEN_IME_KEYBOARD");
        }
        while (m3eGLSurfaceView.this.mTextField == null);
        m3eGLSurfaceView.this.hideImeKeyBoard();
      }
    };
  }

  private static native void nativeHideKeyBoard();

  private static native void nativeKeyboardHeightChange();

  private static native void nativeOkButtonClick();

  private static native void nativeOnTouch(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  private static native void nativeSendInputText(String paramString);

  public static void openIMEKeyboard(String paramString, int paramInt1, int paramInt2)
  {
    Log.d("m3eGLSurfaceView", "openIMEKeyboard");
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = paramString;
    localMessage.arg1 = paramInt1;
    localMessage.arg2 = paramInt2;
    handler.sendMessage(localMessage);
  }

  private void setTextFieldAttr(int paramInt)
  {
    if ((paramInt & 0x2) != 0)
    {
      this.mTextField.setInputType(12290);
      return;
    }
    int i = 1;
    if ((paramInt & 0x1) != 0)
      i = 129;
    if ((paramInt & 0x4) != 0)
      i |= 32;
    if ((paramInt & 0x8) != 0)
      i |= 131072;
    if ((paramInt & 0x10) != 0)
      i |= 16;
    this.mTextField.setInputType(i);
  }

  private void showImeKeyBoard(String paramString, int paramInt1, int paramInt2)
  {
    setTextFieldAttr(paramInt1);
    TextView localTextView = this.mTextField;
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    arrayOfInputFilter[0] = new InputFilter.LengthFilter(paramInt2);
    localTextView.setFilters(arrayOfInputFilter);
    this.mTextField.removeTextChangedListener(this.textInputWraper);
    this.mTextField.setText("");
    this.mTextField.append(paramString);
    this.mTextField.addTextChangedListener(this.textInputWraper);
    ((InputMethodManager)getContext().getSystemService("input_method")).showSoftInput(this.mTextField, 0);
  }

  public void HideKeyBoard()
  {
    nativeHideKeyBoard();
  }

  public void OkButton()
  {
    nativeOkButtonClick();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    super.onKeyDown(paramInt, paramKeyEvent);
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (m3eActivity.s_isPogressing == true);
    while (true)
    {
      return true;
      int i = paramMotionEvent.getPointerCount();
      int[] arrayOfInt1 = new int[i];
      int[] arrayOfInt2 = new int[i];
      int[] arrayOfInt3 = new int[i];
      for (int j = 0; j < i; j++)
      {
        arrayOfInt1[j] = paramMotionEvent.getPointerId(j);
        arrayOfInt2[j] = (int)paramMotionEvent.getX(j);
        arrayOfInt3[j] = (int)paramMotionEvent.getY(j);
      }
      int k = 0xFF & paramMotionEvent.getAction();
      int m = 0;
      switch (k)
      {
      case 4:
      default:
        return true;
      case 0:
        int i11 = paramMotionEvent.getPointerId(0);
        int i12 = arrayOfInt2[0];
        int i13 = arrayOfInt3[0];
        Log.d("nativeOnTouch", "press down, id = " + i11 + ", x=" + i12 + ", y=" + i13);
        nativeOnTouch(0, i11, i12, i13);
        e1x = i12;
        return true;
      case 5:
        int i7 = paramMotionEvent.getAction() >> 8;
        int i8 = paramMotionEvent.getPointerId(i7);
        int i9 = (int)paramMotionEvent.getX(i7);
        int i10 = (int)paramMotionEvent.getY(i7);
        Log.d("nativeOnTouch", "press down, id = " + i8 + ", x=" + i9 + ", y=" + i10);
        nativeOnTouch(0, i8, i9, i10);
        return true;
      case 2:
        Log.d("nativeOnTouch", "pointerNumber = " + i);
        while (m < i)
        {
          Log.d("nativeOnTouch", "move, id = " + arrayOfInt1[m] + ", xs[i]=" + arrayOfInt2[m] + ", ys[i]=" + arrayOfInt3[m]);
          nativeOnTouch(1, arrayOfInt1[m], arrayOfInt2[m], arrayOfInt3[m]);
          m++;
        }
      case 6:
        int i3 = paramMotionEvent.getAction() >> 8;
        int i4 = paramMotionEvent.getPointerId(i3);
        int i5 = (int)paramMotionEvent.getX(i3);
        int i6 = (int)paramMotionEvent.getY(i3);
        Log.d("nativeOnTouch", "up, id = " + i4 + ", x=" + i5 + ", y=" + i6);
        nativeOnTouch(2, i4, i5, i6);
        return true;
      case 1:
        int n = paramMotionEvent.getPointerId(0);
        int i1 = arrayOfInt2[0];
        int i2 = arrayOfInt3[0];
        Log.d("nativeOnTouch", "up, id = " + n + ", x=" + i1 + ", y=" + i2);
        nativeOnTouch(2, n, i1, i2);
        e2x = i1;
        m3eActivity.Fling(e1x, e2x);
        return true;
      case 3:
      }
      while (m < i)
      {
        Log.d("nativeOnTouch", "move, id = " + arrayOfInt1[m] + ", xs[i]=" + arrayOfInt2[m] + ", ys[i]=" + arrayOfInt3[m]);
        nativeOnTouch(2, arrayOfInt1[m], arrayOfInt2[m], arrayOfInt3[m]);
        m++;
      }
    }
  }

  public void sendTextInput(CharSequence paramCharSequence)
  {
    nativeSendInputText(paramCharSequence.toString());
  }

  public void setTextField(EditText paramEditText)
  {
    this.mTextField = paramEditText;
    this.mTextField.setOnEditorActionListener(this.textInputWraper);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eGLSurfaceView
 * JD-Core Version:    0.6.0
 */