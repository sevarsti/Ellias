package com.tencent.qqgamemi.data;

import android.content.Context;
import com.tencent.component.db.EntityManager;
import com.tencent.qqgamemi.QMiEntityManagerFactory;

public class UserInfoProvider
{
  private EntityManager a;

  public UserInfoProvider(Context paramContext)
  {
    this.a = QMiEntityManagerFactory.a(paramContext).a(UserInfo.class, "");
  }

  public UserInfo a(long paramLong)
  {
    return (UserInfo)this.a.findById(Long.valueOf(paramLong));
  }

  public void a(UserInfo paramUserInfo)
  {
    this.a.saveOrUpdate(paramUserInfo);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.UserInfoProvider
 * JD-Core Version:    0.6.0
 */