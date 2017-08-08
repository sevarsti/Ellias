package com.tencent.qqgamemi.plugin;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.db.EntityManager;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.QMiEntityManagerFactory;
import java.util.HashMap;
import java.util.List;

public class PluginItemProvider
{
  private static final String a = PluginItemProvider.class.getSimpleName();
  private Context b;
  private HashMap c = new HashMap();
  private EntityManager d;
  private EntityManager e;

  public PluginItemProvider(Context paramContext)
  {
    this.b = paramContext;
    this.e = QMiEntityManagerFactory.a(paramContext).a(PluginOnline.class, "PluginOnline");
  }

  private EntityManager c(String paramString)
  {
    EntityManager localEntityManager = (EntityManager)this.c.get(paramString);
    if (localEntityManager == null)
    {
      localEntityManager = d(paramString);
      this.c.put(paramString, localEntityManager);
    }
    return localEntityManager;
  }

  private EntityManager d(String paramString)
  {
    String str = paramString.replace(".", "_");
    LogUtil.d(a, "makeNewEntityManager " + str);
    return QMiEntityManagerFactory.a(this.b).a(PluginItem.class, str);
  }

  private void e(String paramString)
  {
    this.d = c(paramString);
  }

  public PluginItem a(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString2))
      return null;
    e(paramString2);
    return (PluginItem)this.d.findById(paramString1);
  }

  public void a(PluginOnline paramPluginOnline)
  {
    this.e.saveOrUpdate(paramPluginOnline);
  }

  public void a(List paramList, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return;
    e(paramString);
    this.d.saveOrUpdateAll(paramList);
  }

  public boolean a(String paramString)
  {
    PluginOnline localPluginOnline = (PluginOnline)this.e.findById(paramString);
    if (localPluginOnline != null)
      return localPluginOnline.isGetOnLineReady;
    return false;
  }

  public List b(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return null;
    e(paramString);
    return this.d.findAll();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.PluginItemProvider
 * JD-Core Version:    0.6.0
 */