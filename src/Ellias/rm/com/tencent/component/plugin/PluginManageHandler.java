package com.tencent.component.plugin;

import android.content.Intent;
import android.net.Uri;
import android.os.IInterface;

public abstract interface PluginManageHandler extends IInterface
{
  public abstract Intent a(String paramString, Uri paramUri);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.PluginManageHandler
 * JD-Core Version:    0.6.0
 */