package com.tencent.open;

import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

class e extends WebChromeClient
{
  e(h paramh)
  {
  }

  public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
  {
    Log.i("WebConsole", paramString1 + " -- From 222 line " + paramInt + " of " + paramString2);
    if (Build.VERSION.SDK_INT == 7)
      this.a.onConsoleMessage(paramString1);
  }

  public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
  {
    Log.i("WebConsole", paramConsoleMessage.message() + " -- From  111 line " + paramConsoleMessage.lineNumber() + " of " + paramConsoleMessage.sourceId());
    h localh;
    if (Build.VERSION.SDK_INT > 7)
    {
      localh = this.a;
      if (paramConsoleMessage != null)
        break label76;
    }
    label76: for (String str = ""; ; str = paramConsoleMessage.message())
    {
      localh.onConsoleMessage(str);
      return true;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.open.e
 * JD-Core Version:    0.6.0
 */