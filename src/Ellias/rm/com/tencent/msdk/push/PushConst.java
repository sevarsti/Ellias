package com.tencent.msdk.push;

import android.os.Environment;
import java.io.File;

public class PushConst
{
  protected static final int PUSH_ALARM_REQUEST_CODE = 8888;
  public static final int PUSH_DEAULT_POLLING_INTERVAL = 600000;
  public static final String PUSH_ROOT_DIR = Environment.getExternalStorageDirectory().getPath() + "/Tencent/msdk/";
  public static final String VERSION = "1.0";
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.PushConst
 * JD-Core Version:    0.6.0
 */