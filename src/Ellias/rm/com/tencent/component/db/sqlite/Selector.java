package com.tencent.component.db.sqlite;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.db.EntityContext;
import com.tencent.component.db.entity.TableEntity;
import java.util.ArrayList;
import java.util.List;

@PluginApi(a=4)
public class Selector
{
  protected WhereBuilder a;
  protected List b;
  protected int c = 0;
  protected int d = 0;

  @PluginApi(a=4)
  public static Selector create()
  {
    return new Selector();
  }

  public String a(Class paramClass, EntityContext paramEntityContext)
  {
    String str = TableEntity.a(paramClass, paramEntityContext).a();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SELECT ");
    localStringBuilder.append("*");
    localStringBuilder.append(" FROM ").append(str);
    if ((this.a != null) && (this.a.a() > 0))
      localStringBuilder.append(" WHERE ").append(this.a.toString());
    if (this.b != null)
      for (int i = 0; i < this.b.size(); i++)
        localStringBuilder.append(" ORDER BY ").append(((Selector.OrderBy)this.b.get(i)).toString());
    if (this.c > 0)
    {
      localStringBuilder.append(" LIMIT ").append(this.c);
      localStringBuilder.append(" OFFSET ").append(this.d);
    }
    return localStringBuilder.toString();
  }

  @PluginApi(a=4)
  public Selector and(WhereBuilder paramWhereBuilder)
  {
    this.a.expr("AND (" + paramWhereBuilder.toString() + ")");
    return this;
  }

  @PluginApi(a=4)
  public Selector and(String paramString1, String paramString2, Object paramObject)
  {
    this.a.and(paramString1, paramString2, paramObject);
    return this;
  }

  @PluginApi(a=4)
  public Selector expr(String paramString)
  {
    this.a.expr(paramString);
    return this;
  }

  @PluginApi(a=4)
  public Selector expr(String paramString1, String paramString2, Object paramObject)
  {
    this.a.expr(paramString1, paramString2, paramObject);
    return this;
  }

  @PluginApi(a=4)
  public Selector limit(int paramInt)
  {
    this.c = paramInt;
    return this;
  }

  @PluginApi(a=4)
  public Selector offset(int paramInt)
  {
    this.d = paramInt;
    return this;
  }

  @PluginApi(a=4)
  public Selector or(WhereBuilder paramWhereBuilder)
  {
    this.a.expr("OR (" + paramWhereBuilder.toString() + ")");
    return this;
  }

  @PluginApi(a=4)
  public Selector or(String paramString1, String paramString2, Object paramObject)
  {
    this.a.or(paramString1, paramString2, paramObject);
    return this;
  }

  @PluginApi(a=4)
  public Selector orderBy(String paramString)
  {
    if (this.b == null)
      this.b = new ArrayList(2);
    this.b.add(new Selector.OrderBy(this, paramString));
    return this;
  }

  @PluginApi(a=4)
  public Selector orderBy(String paramString, boolean paramBoolean)
  {
    if (this.b == null)
      this.b = new ArrayList(2);
    this.b.add(new Selector.OrderBy(this, paramString, paramBoolean));
    return this;
  }

  @PluginApi(a=4)
  public Selector where(WhereBuilder paramWhereBuilder)
  {
    this.a = paramWhereBuilder;
    return this;
  }

  @PluginApi(a=4)
  public Selector where(String paramString1, String paramString2, Object paramObject)
  {
    this.a = WhereBuilder.create(paramString1, paramString2, paramObject);
    return this;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.sqlite.Selector
 * JD-Core Version:    0.6.0
 */