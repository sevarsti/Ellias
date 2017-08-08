package com.tencent.tmassistantsdk.f.c;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.tmassistantsdk.f.a.b;
import com.tencent.tmassistantsdk.f.a.c;
import java.util.Iterator;
import java.util.List;

public abstract class a
  implements g
{
  // ERROR //
  public b a(int paramInt)
  {
    // Byte code:
    //   0: new 16	com/tencent/tmassistantsdk/f/c/b
    //   3: dup
    //   4: invokespecial 17	com/tencent/tmassistantsdk/f/c/b:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: new 19	java/util/ArrayList
    //   12: dup
    //   13: invokespecial 20	java/util/ArrayList:<init>	()V
    //   16: putfield 23	com/tencent/tmassistantsdk/f/c/b:a	Ljava/util/List;
    //   19: aload_2
    //   20: new 19	java/util/ArrayList
    //   23: dup
    //   24: invokespecial 20	java/util/ArrayList:<init>	()V
    //   27: putfield 26	com/tencent/tmassistantsdk/f/c/b:b	Ljava/util/List;
    //   30: iload_1
    //   31: ifle +222 -> 253
    //   34: new 28	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 29	java/lang/StringBuilder:<init>	()V
    //   41: ldc 31
    //   43: invokevirtual 35	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: iload_1
    //   47: invokevirtual 38	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   50: invokevirtual 42	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore_3
    //   54: aload_0
    //   55: invokevirtual 46	com/tencent/tmassistantsdk/f/c/a:c	()Lcom/tencent/tmassistantsdk/f/a/c;
    //   58: invokevirtual 52	com/tencent/tmassistantsdk/f/a/c:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   61: astore 4
    //   63: aload 4
    //   65: aload_0
    //   66: invokevirtual 55	com/tencent/tmassistantsdk/f/c/a:d	()Ljava/lang/String;
    //   69: aconst_null
    //   70: aconst_null
    //   71: aconst_null
    //   72: aconst_null
    //   73: aconst_null
    //   74: ldc 57
    //   76: aload_3
    //   77: invokevirtual 63	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   80: astore 9
    //   82: aload 9
    //   84: astore 6
    //   86: aload 6
    //   88: ifnull +90 -> 178
    //   91: aload 6
    //   93: invokeinterface 69 1 0
    //   98: ifeq +80 -> 178
    //   101: aload 6
    //   103: aload 6
    //   105: ldc 71
    //   107: invokeinterface 75 2 0
    //   112: invokeinterface 79 2 0
    //   117: lstore 10
    //   119: aload_2
    //   120: getfield 23	com/tencent/tmassistantsdk/f/c/b:a	Ljava/util/List;
    //   123: lload 10
    //   125: invokestatic 85	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   128: invokeinterface 91 2 0
    //   133: pop
    //   134: aload 6
    //   136: aload 6
    //   138: ldc 93
    //   140: invokeinterface 75 2 0
    //   145: invokeinterface 97 2 0
    //   150: astore 13
    //   152: aload_2
    //   153: getfield 26	com/tencent/tmassistantsdk/f/c/b:b	Ljava/util/List;
    //   156: aload 13
    //   158: invokeinterface 91 2 0
    //   163: pop
    //   164: aload 6
    //   166: invokeinterface 100 1 0
    //   171: istore 15
    //   173: iload 15
    //   175: ifne -74 -> 101
    //   178: aload 6
    //   180: ifnull +10 -> 190
    //   183: aload 6
    //   185: invokeinterface 103 1 0
    //   190: aload_2
    //   191: astore 8
    //   193: aload 8
    //   195: areturn
    //   196: astore 7
    //   198: aconst_null
    //   199: astore 6
    //   201: aload 7
    //   203: invokevirtual 106	java/lang/Exception:printStackTrace	()V
    //   206: aconst_null
    //   207: astore 8
    //   209: aload 6
    //   211: ifnull -18 -> 193
    //   214: aload 6
    //   216: invokeinterface 103 1 0
    //   221: aconst_null
    //   222: areturn
    //   223: astore 5
    //   225: aconst_null
    //   226: astore 6
    //   228: aload 6
    //   230: ifnull +10 -> 240
    //   233: aload 6
    //   235: invokeinterface 103 1 0
    //   240: aload 5
    //   242: athrow
    //   243: astore 5
    //   245: goto -17 -> 228
    //   248: astore 7
    //   250: goto -49 -> 201
    //   253: aconst_null
    //   254: astore_3
    //   255: goto -201 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   63	82	196	java/lang/Exception
    //   63	82	223	finally
    //   91	101	243	finally
    //   101	173	243	finally
    //   201	206	243	finally
    //   91	101	248	java/lang/Exception
    //   101	173	248	java/lang/Exception
  }

  public String a()
  {
    return d();
  }

  public void a(SQLiteDatabase paramSQLiteDatabase1, SQLiteDatabase paramSQLiteDatabase2)
  {
  }

  public boolean a(List paramList)
  {
    if ((paramList == null) || (paramList.size() <= 0))
      return false;
    StringBuffer localStringBuffer = new StringBuffer("(");
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      localStringBuffer.append((Long)localIterator.next());
      localStringBuffer.append(",");
    }
    if (localStringBuffer.length() > 0)
      localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    localStringBuffer.append(")");
    c().getWritableDatabase().delete(d(), "_id in " + localStringBuffer, null);
    return true;
  }

  public boolean a(byte[] paramArrayOfByte)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("logData", paramArrayOfByte);
    return c().getWritableDatabase().insert(d(), null, localContentValues) > 0L;
  }

  public String[] a(int paramInt1, int paramInt2)
  {
    return b(paramInt2);
  }

  public String b()
  {
    return e();
  }

  protected abstract String[] b(int paramInt);

  public c c()
  {
    return b.a();
  }

  protected abstract String d();

  protected abstract String e();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.f.c.a
 * JD-Core Version:    0.6.0
 */