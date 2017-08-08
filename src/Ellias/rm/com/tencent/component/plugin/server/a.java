package com.tencent.component.plugin.server;

import java.util.ArrayList;
import java.util.Collection;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

final class a extends DefaultHandler
{
  private static final String a = "plugin";
  private static final String b = "item";
  private static final String c = "id";
  private static final String d = "path";
  private static final String e = "uri";
  private static final String f = "version";
  private final ArrayList g = new ArrayList();
  private boolean h = false;

  private static int a(String paramString, int paramInt)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return paramInt;
  }

  public Collection a()
  {
    return this.g;
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
  {
    if ("plugin".equalsIgnoreCase(paramString2))
      this.h = false;
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
  {
    if ("plugin".equalsIgnoreCase(paramString2))
      this.h = true;
    if (!this.h);
    b localb;
    do
    {
      do
        return;
      while (!"item".equalsIgnoreCase(paramString2));
      localb = new b();
      localb.a = paramAttributes.getValue("id");
      localb.b = paramAttributes.getValue("path");
      localb.c = paramAttributes.getValue("uri");
      localb.d = a(paramAttributes.getValue("version"), -1);
    }
    while (!localb.a());
    this.g.add(localb);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.a
 * JD-Core Version:    0.6.0
 */