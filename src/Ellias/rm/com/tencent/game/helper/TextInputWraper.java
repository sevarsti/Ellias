package com.tencent.game.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

class TextInputWraper
  implements TextWatcher, TextView.OnEditorActionListener
{
  private m3eGLSurfaceView mMainView;

  public TextInputWraper(m3eGLSurfaceView paramm3eGLSurfaceView)
  {
    Log.d("m3eGLSurfaceView", "TextInputWraper");
    this.mMainView = paramm3eGLSurfaceView;
  }

  public void afterTextChanged(Editable paramEditable)
  {
    this.mMainView.sendTextInput(paramEditable);
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
  {
    Log.d("m3eGLSurfaceView:", "actionId:" + paramInt);
    this.mMainView.OkButton();
    this.mMainView.HideKeyBoard();
    return false;
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.TextInputWraper
 * JD-Core Version:    0.6.0
 */