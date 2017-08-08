package com.tencent.tmassistantsdk.d;

import com.tencent.tmassistantsdk.e.d;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.a;
import com.tencent.tmassistantsdk.protocol.jce.GetSettingsRequest;
import com.tencent.tmassistantsdk.protocol.jce.GetSettingsResponse;
import com.tencent.tmassistantsdk.protocol.jce.SettingsCfg;
import com.tencent.tmassistantsdk.protocol.jce.StatCfg;
import java.util.ArrayList;

public class c
  implements com.tencent.tmassistantsdk.e.f
{
  protected static c a = null;
  protected d b = null;

  public static c a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new c();
      c localc = a;
      return localc;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(GetSettingsRequest paramGetSettingsRequest, GetSettingsResponse paramGetSettingsResponse, boolean paramBoolean)
  {
    this.b = null;
    if (paramBoolean)
    {
      if ((paramGetSettingsResponse.settings != null) && (paramGetSettingsResponse.settings.size() > 0))
      {
        SettingsCfg localSettingsCfg = (SettingsCfg)paramGetSettingsResponse.settings.get(0);
        if ((localSettingsCfg != null) && (localSettingsCfg.cfg != null))
        {
          StatCfg localStatCfg = (StatCfg)a.a(localSettingsCfg.cfg, StatCfg.class);
          if (localStatCfg == null)
            break label91;
          com.tencent.tmassistantsdk.g.f.a().a(localStatCfg.netType);
        }
      }
      while (true)
      {
        l.b("GetSettingEngine", "response.settings is null !");
        return;
        label91: l.b("GetSettingEngine", "response  StatCfg is null !");
      }
    }
    l.b("GetSettingEngine", "get settings failed!");
  }

  public void b()
  {
    monitorenter;
    try
    {
      if (this.b != null)
      {
        this.b.b();
        this.b = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void c()
  {
    if (this.b != null)
      return;
    this.b = new d();
    this.b.a(this);
    this.b.a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.d.c
 * JD-Core Version:    0.6.0
 */