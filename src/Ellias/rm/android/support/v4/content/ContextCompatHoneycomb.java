package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import java.io.File;

class ContextCompatHoneycomb
{
  public static File getObbDir(Context paramContext)
  {
    return paramContext.getObbDir();
  }

  static void startActivities(Context paramContext, Intent[] paramArrayOfIntent)
  {
    paramContext.startActivities(paramArrayOfIntent);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     android.support.v4.content.ContextCompatHoneycomb
 * JD-Core Version:    0.6.0
 */