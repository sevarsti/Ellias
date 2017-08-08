package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatFroyo
{
  public static File getExternalCacheDir(Context paramContext)
  {
    return paramContext.getExternalCacheDir();
  }

  public static File getExternalFilesDir(Context paramContext, String paramString)
  {
    return paramContext.getExternalFilesDir(paramString);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.content.ContextCompatFroyo
 * JD-Core Version:    0.6.0
 */