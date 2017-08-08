package com.tencent.component.db.annotation;

import com.tencent.component.annotation.PluginApi;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD, java.lang.annotation.ElementType.TYPE})
@PluginApi(a=6)
public @interface Encrypt
{
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.annotation.Encrypt
 * JD-Core Version:    0.6.0
 */