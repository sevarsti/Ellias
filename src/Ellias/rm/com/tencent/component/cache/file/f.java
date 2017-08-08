package com.tencent.component.cache.file;

import android.content.Context;
import android.widget.Toast;
import com.tencent.component.plugin.PluginContextWrapper;
import com.tencent.component.utils.ResourceUtil;

class f
  implements Runnable
{
  f(FileStorageHandler paramFileStorageHandler, Context paramContext)
  {
  }

  public void run()
  {
    Toast.makeText(PluginContextWrapper.a(this.a), ResourceUtil.b("appfw_low_storage_warning"), 1).show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.file.f
 * JD-Core Version:    0.6.0
 */