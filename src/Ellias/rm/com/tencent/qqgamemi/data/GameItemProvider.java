package com.tencent.qqgamemi.data;

import android.content.Context;
import com.tencent.component.db.EntityManager;
import com.tencent.component.db.sqlite.Selector;
import com.tencent.qqgamemi.QMiEntityManagerFactory;
import java.util.List;

public class GameItemProvider
{
  private EntityManager a;

  public GameItemProvider(Context paramContext)
  {
    this.a = QMiEntityManagerFactory.a(paramContext).a(GameItem.class, "");
  }

  public GameItem a(String paramString)
  {
    return (GameItem)this.a.findById(paramString);
  }

  public void a()
  {
    this.a.deleteAll();
  }

  public void a(GameItem paramGameItem)
  {
    this.a.saveOrUpdate(paramGameItem);
  }

  public void a(String paramString, Point paramPoint)
  {
    GameItem localGameItem = (GameItem)this.a.findById(paramString);
    if (localGameItem != null)
      localGameItem.movePoint = paramPoint;
    this.a.saveOrUpdate(localGameItem);
  }

  public void a(List paramList)
  {
    this.a.saveOrUpdateAll(paramList);
  }

  public List b()
  {
    return this.a.findAll();
  }

  public List c()
  {
    Selector localSelector = Selector.create();
    localSelector.where("type", "!=", Integer.valueOf(0));
    return this.a.findAll(localSelector);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.data.GameItemProvider
 * JD-Core Version:    0.6.0
 */