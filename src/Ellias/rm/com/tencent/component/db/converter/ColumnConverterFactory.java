package com.tencent.component.db.converter;

import android.os.Parcelable;
import com.qq.taf.jce.JceStruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ColumnConverterFactory
{
  private static ReadWriteLock a = new ReentrantReadWriteLock();
  private static final LinkedHashMap b = new LinkedHashMap();

  static
  {
    BooleanColumnConverter localBooleanColumnConverter = new BooleanColumnConverter();
    b.put(Boolean.TYPE, localBooleanColumnConverter);
    b.put(Boolean.class, localBooleanColumnConverter);
    ByteArrayColumnConverter localByteArrayColumnConverter = new ByteArrayColumnConverter();
    b.put([B.class, localByteArrayColumnConverter);
    ByteColumnConverter localByteColumnConverter = new ByteColumnConverter();
    b.put(Byte.TYPE, localByteColumnConverter);
    b.put(Byte.class, localByteColumnConverter);
    CharColumnConverter localCharColumnConverter = new CharColumnConverter();
    b.put(Character.TYPE, localCharColumnConverter);
    b.put(Character.class, localCharColumnConverter);
    DateColumnConverter localDateColumnConverter = new DateColumnConverter();
    b.put(java.util.Date.class, localDateColumnConverter);
    DoubleColumnConverter localDoubleColumnConverter = new DoubleColumnConverter();
    b.put(Double.TYPE, localDoubleColumnConverter);
    b.put(Double.class, localDoubleColumnConverter);
    FloatColumnConverter localFloatColumnConverter = new FloatColumnConverter();
    b.put(Float.TYPE, localFloatColumnConverter);
    b.put(Float.class, localFloatColumnConverter);
    IntegerColumnConverter localIntegerColumnConverter = new IntegerColumnConverter();
    b.put(Integer.TYPE, localIntegerColumnConverter);
    b.put(Integer.class, localIntegerColumnConverter);
    LongColumnConverter localLongColumnConverter = new LongColumnConverter();
    b.put(Long.TYPE, localLongColumnConverter);
    b.put(Long.class, localLongColumnConverter);
    ShortColumnConverter localShortColumnConverter = new ShortColumnConverter();
    b.put(Short.TYPE, localShortColumnConverter);
    b.put(Short.class, localShortColumnConverter);
    SqlDateColumnConverter localSqlDateColumnConverter = new SqlDateColumnConverter();
    b.put(java.sql.Date.class, localSqlDateColumnConverter);
    StringColumnConverter localStringColumnConverter = new StringColumnConverter();
    b.put(String.class, localStringColumnConverter);
    JceColumnConverter localJceColumnConverter = new JceColumnConverter();
    b.put(JceStruct.class, localJceColumnConverter);
    ListColumnConverter localListColumnConverter = new ListColumnConverter();
    b.put(List.class, localListColumnConverter);
    b.put(ArrayList.class, localListColumnConverter);
    ParcelColumnConverter localParcelColumnConverter = new ParcelColumnConverter();
    b.put(Parcelable.class, localParcelColumnConverter);
    SerializableColumnConverter localSerializableColumnConverter = new SerializableColumnConverter();
    b.put(Serializable.class, localSerializableColumnConverter);
  }

  // ERROR //
  public static ColumnConverter a(Class paramClass)
  {
    // Byte code:
    //   0: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   3: invokeinterface 127 1 0
    //   8: invokeinterface 132 1 0
    //   13: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   16: aload_0
    //   17: invokevirtual 136	java/util/LinkedHashMap:containsKey	(Ljava/lang/Object;)Z
    //   20: ifeq +31 -> 51
    //   23: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   26: aload_0
    //   27: invokevirtual 140	java/util/LinkedHashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   30: checkcast 142	com/tencent/component/db/converter/ColumnConverter
    //   33: astore 7
    //   35: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   38: invokeinterface 127 1 0
    //   43: invokeinterface 145 1 0
    //   48: aload 7
    //   50: areturn
    //   51: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   54: invokevirtual 149	java/util/LinkedHashMap:entrySet	()Ljava/util/Set;
    //   57: astore_2
    //   58: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   61: invokeinterface 127 1 0
    //   66: invokeinterface 145 1 0
    //   71: aload_2
    //   72: ifnull +133 -> 205
    //   75: aload_2
    //   76: invokeinterface 155 1 0
    //   81: astore_3
    //   82: aload_3
    //   83: invokeinterface 161 1 0
    //   88: ifeq +117 -> 205
    //   91: aload_3
    //   92: invokeinterface 165 1 0
    //   97: checkcast 167	java/util/Map$Entry
    //   100: astore 4
    //   102: aload 4
    //   104: invokeinterface 170 1 0
    //   109: checkcast 172	java/lang/Class
    //   112: aload_0
    //   113: invokevirtual 176	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   116: ifeq -34 -> 82
    //   119: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   122: invokeinterface 179 1 0
    //   127: invokeinterface 132 1 0
    //   132: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   135: aload_0
    //   136: aload 4
    //   138: invokeinterface 182 1 0
    //   143: invokevirtual 35	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   146: pop
    //   147: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   150: invokeinterface 179 1 0
    //   155: invokeinterface 145 1 0
    //   160: aload 4
    //   162: invokeinterface 182 1 0
    //   167: checkcast 142	com/tencent/component/db/converter/ColumnConverter
    //   170: areturn
    //   171: astore_1
    //   172: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   175: invokeinterface 127 1 0
    //   180: invokeinterface 145 1 0
    //   185: aload_1
    //   186: athrow
    //   187: astore 5
    //   189: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   192: invokeinterface 179 1 0
    //   197: invokeinterface 145 1 0
    //   202: aload 5
    //   204: athrow
    //   205: aconst_null
    //   206: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	35	171	finally
    //   51	58	171	finally
    //   119	147	187	finally
  }

  public static void a(Class paramClass, ColumnConverter paramColumnConverter)
  {
    try
    {
      a.writeLock().lock();
      b.put(paramClass, paramColumnConverter);
      return;
    }
    finally
    {
      a.writeLock().unlock();
    }
    throw localObject;
  }

  public static String b(Class paramClass)
  {
    ColumnConverter localColumnConverter = a(paramClass);
    if (localColumnConverter != null)
      return localColumnConverter.a();
    return "TEXT";
  }

  // ERROR //
  public static boolean c(Class paramClass)
  {
    // Byte code:
    //   0: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   3: invokeinterface 127 1 0
    //   8: invokeinterface 132 1 0
    //   13: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   16: aload_0
    //   17: invokevirtual 136	java/util/LinkedHashMap:containsKey	(Ljava/lang/Object;)Z
    //   20: istore_2
    //   21: iload_2
    //   22: ifeq +18 -> 40
    //   25: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   28: invokeinterface 127 1 0
    //   33: invokeinterface 145 1 0
    //   38: iconst_1
    //   39: ireturn
    //   40: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   43: invokevirtual 149	java/util/LinkedHashMap:entrySet	()Ljava/util/Set;
    //   46: astore_3
    //   47: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   50: invokeinterface 127 1 0
    //   55: invokeinterface 145 1 0
    //   60: aload_3
    //   61: ifnull +127 -> 188
    //   64: aload_3
    //   65: invokeinterface 155 1 0
    //   70: astore 4
    //   72: aload 4
    //   74: invokeinterface 161 1 0
    //   79: ifeq +109 -> 188
    //   82: aload 4
    //   84: invokeinterface 165 1 0
    //   89: checkcast 167	java/util/Map$Entry
    //   92: astore 5
    //   94: aload 5
    //   96: invokeinterface 170 1 0
    //   101: checkcast 172	java/lang/Class
    //   104: aload_0
    //   105: invokevirtual 176	java/lang/Class:isAssignableFrom	(Ljava/lang/Class;)Z
    //   108: ifeq -36 -> 72
    //   111: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   114: invokeinterface 179 1 0
    //   119: invokeinterface 132 1 0
    //   124: getstatic 22	com/tencent/component/db/converter/ColumnConverterFactory:b	Ljava/util/LinkedHashMap;
    //   127: aload_0
    //   128: aload 5
    //   130: invokeinterface 182 1 0
    //   135: invokevirtual 35	java/util/LinkedHashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   138: pop
    //   139: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   142: invokeinterface 179 1 0
    //   147: invokeinterface 145 1 0
    //   152: iconst_1
    //   153: ireturn
    //   154: astore_1
    //   155: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   158: invokeinterface 127 1 0
    //   163: invokeinterface 145 1 0
    //   168: aload_1
    //   169: athrow
    //   170: astore 6
    //   172: getstatic 17	com/tencent/component/db/converter/ColumnConverterFactory:a	Ljava/util/concurrent/locks/ReadWriteLock;
    //   175: invokeinterface 179 1 0
    //   180: invokeinterface 145 1 0
    //   185: aload 6
    //   187: athrow
    //   188: iconst_0
    //   189: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	21	154	finally
    //   40	47	154	finally
    //   111	139	170	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.converter.ColumnConverterFactory
 * JD-Core Version:    0.6.0
 */