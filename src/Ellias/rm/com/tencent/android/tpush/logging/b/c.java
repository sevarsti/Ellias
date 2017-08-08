package com.tencent.android.tpush.logging.b;

import android.content.Context;
import com.tencent.android.tpush.service.i;
import java.io.File;

public final class c
{
  private static Context a = null;

  public static final Context a()
  {
    if (a == null)
      a = i.e();
    return a;
  }

  public static final void a(Context paramContext)
  {
    a = paramContext;
  }

  public static final File b()
  {
    return a().getFilesDir();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.b.c
 * JD-Core Version:    0.6.0
 */