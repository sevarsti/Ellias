package com.tencent.feedback.eup.jni;

import com.tencent.feedback.common.e;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public final class c
  implements Runnable
{
  private File a;
  private long b;
  private int c;
  private int d;

  public c(String paramString, long paramLong, int paramInt)
  {
    this.a = new File(paramString);
    this.b = paramLong;
    this.c = paramInt;
  }

  private static void a(String paramString, String[] paramArrayOfString)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length <= 0))
      e.c("rqdp{  fileNameList == null || fileNameList.length <= 0}", new Object[0]);
    while (true)
    {
      return;
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        File localFile = new File(paramString, paramArrayOfString[j]);
        if ((!localFile.exists()) || (!localFile.canWrite()))
          continue;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localFile.getPath();
        e.b("rqdp{  file delete} %s", arrayOfObject);
        localFile.delete();
      }
    }
  }

  public final void run()
  {
    this.d = 0;
    if ((this.a == null) || (!this.a.exists()) || (!this.a.isDirectory()))
    {
      e.c("rqdp{  TombFilesCleanTask mDir == null || !mDir.exists() || !mDir.isDirectory() ,pls check!}", new Object[0]);
      return;
    }
    Object[] arrayOfObject1 = new Object[4];
    arrayOfObject1[0] = "tomb_";
    arrayOfObject1[1] = this.a.getAbsolutePath();
    arrayOfObject1[2] = Long.valueOf(this.b);
    arrayOfObject1[3] = Integer.valueOf(this.c);
    e.a("rqdp{  start to clean} %s.* rqdp{  in dir} %s rqdp{  which time <} %s rqdp{  and max file nums should <} %s", arrayOfObject1);
    LinkedList localLinkedList = new LinkedList();
    String[] arrayOfString1 = this.a.list(new FilenameFilter(5, 4, localLinkedList)
    {
      public final boolean accept(File paramFile, String paramString)
      {
        e.b("rqdp{  check dir} %s rqdp{  , filename} %s", new Object[] { paramFile, paramString });
        if (paramString.startsWith("tomb_"))
        {
          c.a(c.this);
          e.b("rqdp{  accept }%s", new Object[] { paramString });
        }
        try
        {
          long l = Long.parseLong(paramString.substring(this.a, paramString.length() - this.b));
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = Long.valueOf(c.b(c.this));
          e.b("rqdp{  mRemoveBeforeDate }%d", arrayOfObject1);
          if (l <= c.b(c.this))
          {
            Object[] arrayOfObject3 = new Object[1];
            arrayOfObject3[0] = Long.valueOf(l);
            e.b("rqdp{  recordTime} %d rqdp{  is old}", arrayOfObject3);
            return true;
          }
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Long.valueOf(l);
          e.b("rqdp{  newFileTimeList add} %d", arrayOfObject2);
          this.c.add(Long.valueOf(l));
          return false;
        }
        catch (Throwable localThrowable)
        {
          e.c("rqdp{  filename is not formatted ,shoud do delete! \n path:}%s", new Object[] { paramString });
          localThrowable.printStackTrace();
        }
        return true;
      }
    });
    int i;
    int j;
    int k;
    if (arrayOfString1 != null)
    {
      i = arrayOfString1.length;
      if (i > 0)
      {
        Object[] arrayOfObject4 = new Object[1];
        arrayOfObject4[0] = Integer.valueOf(i);
        e.b("rqdp{  delete old num} %d", arrayOfObject4);
        a(this.a.getAbsolutePath(), arrayOfString1);
      }
      j = this.d - i - this.c;
      k = localLinkedList.size();
      if ((j <= 0) || (k <= 0))
        break label370;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(j);
      e.a("rqdp{  should delete not too old file num} %d", arrayOfObject2);
      Collections.sort(localLinkedList);
      if (k <= j)
        break label328;
    }
    String[] arrayOfString2;
    while (true)
    {
      arrayOfString2 = new String[j];
      StringBuffer localStringBuffer = new StringBuffer();
      for (int m = 0; (m < localLinkedList.size()) && (m < arrayOfString2.length); m++)
      {
        localStringBuffer.append("tomb_");
        localStringBuffer.append(localLinkedList.get(m));
        localStringBuffer.append(".txt");
        arrayOfString2[m] = localStringBuffer.toString();
        localStringBuffer.delete(0, localStringBuffer.length());
      }
      i = 0;
      break;
      label328: j = k;
    }
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = Integer.valueOf(arrayOfString2.length);
    e.b("rqdp{  delete not too old files} %d", arrayOfObject3);
    a(this.a.getAbsolutePath(), arrayOfString2);
    label370: e.a("rqdp{  clean end!}", new Object[0]);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.jni.c
 * JD-Core Version:    0.6.0
 */