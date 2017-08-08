package com.tencent.component.db.annotation;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public abstract interface GenerationType
{

  @PluginApi(a=4)
  public static final int ASSIGN = 1;

  @PluginApi(a=4)
  public static final int AUTO_INCREMENT = 3;

  @PluginApi(a=4)
  public static final int UUID = 2;
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.annotation.GenerationType
 * JD-Core Version:    0.6.0
 */