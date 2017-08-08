package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.webkit.WebChromeClient;

public abstract class h extends Dialog
{
  protected a jsBridge;

  @SuppressLint({"NewApi"})
  protected final WebChromeClient mChromeClient = new e(this);

  public h(Context paramContext)
  {
    super(paramContext);
  }

  public h(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
  }

  protected abstract void onConsoleMessage(String paramString);

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.jsBridge = new a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.h
 * JD-Core Version:    0.6.0
 */