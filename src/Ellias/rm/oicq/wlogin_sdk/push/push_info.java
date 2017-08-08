package oicq.wlogin_sdk.push;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class push_info
{
  TreeMap<uin_appid, push_uin_app_info> _uin_appid_map = new TreeMap();

  public uin_app_info get(long paramLong1, long paramLong2, long paramLong3)
  {
    monitorenter;
    try
    {
      push_uin_app_info localpush_uin_app_info = (push_uin_app_info)this._uin_appid_map.get(new uin_appid(paramLong1, paramLong2, paramLong3));
      if (localpush_uin_app_info != null);
      for (uin_app_info localuin_app_info = localpush_uin_app_info._info; ; localuin_app_info = null)
        return localuin_app_info;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public uin_app_info get(uin_appid paramuin_appid)
  {
    monitorenter;
    try
    {
      push_uin_app_info localpush_uin_app_info = (push_uin_app_info)this._uin_appid_map.get(paramuin_appid);
      if (localpush_uin_app_info != null);
      for (uin_app_info localuin_app_info = localpush_uin_app_info._info; ; localuin_app_info = null)
        return localuin_app_info;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public push_service_callback get_callback(long paramLong1, long paramLong2, long paramLong3)
  {
    monitorenter;
    try
    {
      push_uin_app_info localpush_uin_app_info = (push_uin_app_info)this._uin_appid_map.get(new uin_appid(paramLong1, paramLong2, paramLong3));
      if (localpush_uin_app_info != null);
      for (push_service_callback localpush_service_callback = localpush_uin_app_info._callback; ; localpush_service_callback = null)
        return localpush_service_callback;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public uin_app_info get_first()
  {
    monitorenter;
    try
    {
      boolean bool = this._uin_appid_map.isEmpty();
      if (bool);
      for (uin_app_info localuin_app_info = null; ; localuin_app_info = ((push_uin_app_info)this._uin_appid_map.get(this._uin_appid_map.firstKey()))._info)
        return localuin_app_info;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public uin_appid get_first_key()
  {
    monitorenter;
    try
    {
      boolean bool = this._uin_appid_map.isEmpty();
      if (bool);
      for (uin_appid localuin_appid = null; ; localuin_appid = (uin_appid)this._uin_appid_map.firstKey())
        return localuin_appid;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int get_used_appid(long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this._uin_appid_map.keySet().iterator();
      boolean bool = localIterator.hasNext();
      if (!bool);
      push_uin_app_info localpush_uin_app_info;
      for (int i = 0; ; i = localpush_uin_app_info._info._notify_id)
      {
        return i;
        uin_appid localuin_appid = (uin_appid)localIterator.next();
        localpush_uin_app_info = (push_uin_app_info)this._uin_appid_map.get(localuin_appid);
        if ((localuin_appid._appid != paramLong1) || (localuin_appid._sub_appid != paramLong2) || (localpush_uin_app_info._info._msg_type == 0))
          break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean is_empty()
  {
    monitorenter;
    try
    {
      boolean bool = this._uin_appid_map.isEmpty();
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void put(uin_appid paramuin_appid, uin_app_info paramuin_app_info, push_service_callback parampush_service_callback)
  {
    monitorenter;
    try
    {
      push_uin_app_info localpush_uin_app_info1 = (push_uin_app_info)this._uin_appid_map.get(paramuin_appid);
      push_uin_app_info localpush_uin_app_info2 = new push_uin_app_info(paramuin_app_info, parampush_service_callback);
      if (localpush_uin_app_info1 != null)
        localpush_uin_app_info2._reg = localpush_uin_app_info1._reg;
      this._uin_appid_map.put(paramuin_appid, localpush_uin_app_info2);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void remove(uin_appid paramuin_appid)
  {
    monitorenter;
    try
    {
      this._uin_appid_map.remove(paramuin_appid);
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

  public void send_register(push_service parampush_service, request_push paramrequest_push)
  {
    monitorenter;
    try
    {
      Iterator localIterator = this._uin_appid_map.keySet().iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        uin_appid localuin_appid = (uin_appid)localIterator.next();
        push_uin_app_info localpush_uin_app_info = (push_uin_app_info)this._uin_appid_map.get(localuin_appid);
        if (localpush_uin_app_info._reg)
          continue;
        parampush_service.sendRequest(paramrequest_push.get_request_register(localuin_appid._uin, localuin_appid._appid, localuin_appid._sub_appid, localpush_uin_app_info._info._st, localpush_uin_app_info._info._st_key, localpush_uin_app_info._info._clear, localpush_uin_app_info._info._guid, true));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void set_all_unreg()
  {
    monitorenter;
    try
    {
      Iterator localIterator = this._uin_appid_map.keySet().iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        ((push_uin_app_info)this._uin_appid_map.get(localIterator.next()))._reg = false;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void set_reg(long paramLong1, long paramLong2, long paramLong3, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      push_uin_app_info localpush_uin_app_info = (push_uin_app_info)this._uin_appid_map.get(new uin_appid(paramLong1, paramLong2, paramLong3));
      if (localpush_uin_app_info != null)
        localpush_uin_app_info._reg = paramBoolean;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public class push_uin_app_info
  {
    public push_service_callback _callback;
    public uin_app_info _info;
    public boolean _reg = false;

    public push_uin_app_info(uin_app_info parampush_service_callback, push_service_callback arg3)
    {
      this._info = new uin_app_info(parampush_service_callback);
      this._reg = false;
      Object localObject;
      this._callback = localObject;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.push.push_info
 * JD-Core Version:    0.6.0
 */