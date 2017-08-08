package com.tencent.component.plugin.server;

import android.os.Bundle;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

final class j extends DefaultHandler
{
  private static final String a = "plugin";
  private static final String b = "item";
  private static final String c = "name";
  private static final String d = "value";
  private final Bundle e = new Bundle();
  private boolean f = false;

  private static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public Bundle a()
  {
    return this.e;
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    if ("plugin".equalsIgnoreCase(paramString2))
      this.f = false;
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    if ("plugin".equalsIgnoreCase(paramString2))
      this.f = true;
    if (!this.f);
    String str1;
    String str2;
    do
    {
      do
        return;
      while (!"item".equalsIgnoreCase(paramString2));
      str1 = paramAttributes.getValue("name");
      str2 = paramAttributes.getValue("value");
    }
    while (a(str1));
    this.e.putString(str1, str2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.j
 * JD-Core Version:    0.6.0
 */