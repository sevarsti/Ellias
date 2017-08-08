package com.tencent.qqgamemi.common;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

@PluginApi(a=8)
public class QMiConfig
{

  @PluginApi(a=8)
  public static final int QMI_EMBED_TYPE_GAMEJOY = 3;

  @PluginApi(a=8)
  public static final int QMI_EMBED_TYPE_HALL = 2;

  @PluginApi(a=8)
  public static final int QMI_EMBED_TYPE_SDK = 1;
  private static final String a = QMiConfig.class.getSimpleName();
  private static final String b = "qmi_config.xml";
  private static boolean c = true;
  private static int d = 1;
  private static boolean e = false;

  public static void a(Context paramContext)
  {
    a(paramContext, "qmi_config.xml");
  }

  private static void a(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      while (true)
      {
        ConfigRecord localConfigRecord;
        try
        {
          a locala = new a();
          XMLReader localXMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
          localXMLReader.setContentHandler(locala);
          localXMLReader.parse(new InputSource(paramContext.getAssets().open(paramString)));
          localConfigRecord = locala.a();
          if (!localConfigRecord.a.equals("sdk"))
            continue;
          c = true;
          d = 1;
          if (!localConfigRecord.b)
            break label223;
          e = true;
          TLog.b(a, "ConfigRecorder.id=" + localConfigRecord.a + ",isEmbedSDK=" + c + ",isShowEnvironmentSelectDialog=" + e);
          return;
          if (localConfigRecord.a.equals("hall"))
          {
            c = false;
            d = 2;
            continue;
          }
        }
        catch (Throwable localThrowable)
        {
          TLog.b(a, "fail to parse xml " + paramString, localThrowable);
          return;
        }
        if (!localConfigRecord.a.equals("shouyoubao"))
          continue;
        c = false;
        d = 3;
        continue;
        label223: e = false;
      }
  }

  public static boolean a()
  {
    return e;
  }

  public static boolean b()
  {
    return c;
  }

  public static int c()
  {
    return d;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.common.QMiConfig
 * JD-Core Version:    0.6.0
 */