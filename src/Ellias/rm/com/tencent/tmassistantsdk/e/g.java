package com.tencent.tmassistantsdk.e;

import com.qq.taf.jce.JceStruct;
import org.apache.http.client.methods.HttpPost;

public abstract class g
{
  HttpPost b = null;

  protected abstract void a(JceStruct paramJceStruct1, JceStruct paramJceStruct2, int paramInt);

  protected boolean a(JceStruct paramJceStruct)
  {
    monitorenter;
    if (paramJceStruct == null);
    while (true)
    {
      monitorexit;
      return false;
      try
      {
        if (this.b != null)
          continue;
        new Thread(new h(this, paramJceStruct)).start();
      }
      finally
      {
        monitorexit;
      }
    }
  }

  // ERROR //
  public void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 12	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   6: ifnull +54 -> 60
    //   9: aload_0
    //   10: getfield 12	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   13: invokevirtual 36	org/apache/http/client/methods/HttpPost:isAborted	()Z
    //   16: ifne +44 -> 60
    //   19: ldc 38
    //   21: new 40	java/lang/StringBuilder
    //   24: dup
    //   25: invokespecial 41	java/lang/StringBuilder:<init>	()V
    //   28: ldc 43
    //   30: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_0
    //   34: invokevirtual 50	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   37: ldc 52
    //   39: invokevirtual 47	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: invokevirtual 56	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokestatic 61	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   48: aload_0
    //   49: getfield 12	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   52: invokevirtual 64	org/apache/http/client/methods/HttpPost:abort	()V
    //   55: aload_0
    //   56: aconst_null
    //   57: putfield 12	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   60: aload_0
    //   61: monitorexit
    //   62: return
    //   63: astore_3
    //   64: aload_3
    //   65: invokevirtual 67	java/lang/Exception:printStackTrace	()V
    //   68: aload_0
    //   69: aconst_null
    //   70: putfield 12	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   73: goto -13 -> 60
    //   76: astore_1
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    //   81: astore_2
    //   82: aload_0
    //   83: aconst_null
    //   84: putfield 12	com/tencent/tmassistantsdk/e/g:b	Lorg/apache/http/client/methods/HttpPost;
    //   87: aload_2
    //   88: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   48	55	63	java/lang/Exception
    //   2	48	76	finally
    //   55	60	76	finally
    //   68	73	76	finally
    //   82	89	76	finally
    //   48	55	81	finally
    //   64	68	81	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.e.g
 * JD-Core Version:    0.6.0
 */