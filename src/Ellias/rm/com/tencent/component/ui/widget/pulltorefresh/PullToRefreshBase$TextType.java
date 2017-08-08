package com.tencent.component.ui.widget.pulltorefresh;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public enum PullToRefreshBase$TextType
{
  protected int mIntValue;

  static
  {
    BOTH = new TextType("BOTH", 2, 3);
    TextType[] arrayOfTextType = new TextType[3];
    arrayOfTextType[0] = MAIN;
    arrayOfTextType[1] = SUB;
    arrayOfTextType[2] = BOTH;
    $VALUES = arrayOfTextType;
  }

  private PullToRefreshBase$TextType(int arg3)
  {
    int j;
    this.mIntValue = j;
  }

  @PluginApi(a=6)
  public boolean isMain()
  {
    return (this == MAIN) || (this == BOTH);
  }

  @PluginApi(a=6)
  public boolean isSub()
  {
    return (this == SUB) || (this == BOTH);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshBase.TextType
 * JD-Core Version:    0.6.0
 */