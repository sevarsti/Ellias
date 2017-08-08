package com.tencent.component.db.annotation;

import com.tencent.component.annotation.PluginApi;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
@PluginApi(a=4)
public @interface Column
{
  public abstract boolean a();

  public abstract boolean b();

  public abstract boolean c();

  public abstract Column.ConflictAction d();

  @PluginApi(a=4)
  public abstract String defaultValue();

  public abstract Column.ConflictAction e();

  @PluginApi(a=4)
  public abstract int length();

  @PluginApi(a=4)
  public abstract String name();

  @PluginApi(a=4)
  public abstract boolean unique();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.annotation.Column
 * JD-Core Version:    0.6.0
 */