package oicq.wlogin_sdk.report;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import oicq.wlogin_sdk.tools.util;

public class report_t
  implements Serializable
{
  public static String FILE_NAME = "report_data";
  private static final long serialVersionUID = 1L;

  public static void delete_file(Context paramContext)
  {
    monitorenter;
    try
    {
      paramContext.deleteFile(FILE_NAME);
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

  // ERROR //
  public static report_t1 read_fromfile(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aconst_null
    //   4: astore_1
    //   5: aload_0
    //   6: getstatic 18	oicq/wlogin_sdk/report/report_t:FILE_NAME	Ljava/lang/String;
    //   9: invokevirtual 37	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   12: astore 4
    //   14: new 39	java/io/ObjectInputStream
    //   17: dup
    //   18: aload 4
    //   20: invokespecial 42	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   23: astore 5
    //   25: aload 5
    //   27: invokevirtual 46	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   30: checkcast 48	oicq/wlogin_sdk/report/report_t1
    //   33: astore_1
    //   34: aload 5
    //   36: invokevirtual 51	java/io/ObjectInputStream:close	()V
    //   39: aload 4
    //   41: invokevirtual 54	java/io/FileInputStream:close	()V
    //   44: ldc 2
    //   46: monitorexit
    //   47: aload_1
    //   48: areturn
    //   49: astore_3
    //   50: ldc 2
    //   52: monitorexit
    //   53: aload_3
    //   54: athrow
    //   55: astore_2
    //   56: goto -12 -> 44
    //
    // Exception table:
    //   from	to	target	type
    //   5	44	49	finally
    //   5	44	55	java/lang/Exception
  }

  public static int write_tofile(report_t1 paramreport_t1, Context paramContext)
  {
    int i = 0;
    monitorenter;
    try
    {
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(FILE_NAME, 0);
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
      localObjectOutputStream.writeObject(paramreport_t1);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      localFileOutputStream.close();
      return i;
    }
    catch (Exception localException)
    {
      while (true)
      {
        StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter, true);
        localException.printStackTrace(localPrintWriter);
        localPrintWriter.flush();
        localStringWriter.flush();
        util.LOGW("exception", localStringWriter.toString());
        i = -1;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.report.report_t
 * JD-Core Version:    0.6.0
 */