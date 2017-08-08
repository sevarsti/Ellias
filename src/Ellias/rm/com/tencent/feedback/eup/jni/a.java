package com.tencent.feedback.eup.jni;

import android.content.Context;
import com.tencent.feedback.common.e;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
  implements Runnable
{
  private Context a;
  private String b;
  private List<File> c;

  public a(Context paramContext, String paramString, List<File> paramList)
  {
    this.a = paramContext;
    this.b = paramString;
    this.c = paramList;
  }

  public final void run()
  {
    ArrayList localArrayList1 = new ArrayList();
    if ((this.c != null) && (this.c.size() > 0))
      localArrayList1.addAll(this.c);
    File localFile1 = new File(this.b);
    if ((localFile1.exists()) && (localFile1.isDirectory()))
    {
      File[] arrayOfFile = localFile1.listFiles(new FilenameFilter()
      {
        public final boolean accept(File paramFile, String paramString)
        {
          return paramString.endsWith("so");
        }
      });
      if (arrayOfFile != null)
      {
        int m = arrayOfFile.length;
        for (int n = 0; n < m; n++)
        {
          File localFile3 = arrayOfFile[n];
          if (localArrayList1.contains(localFile3))
            continue;
          localArrayList1.add(localFile3);
        }
      }
    }
    List localList = com.tencent.feedback.common.a.a.a(this.a, null, 1, -1);
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator1 = localArrayList1.iterator();
    label635: label639: 
    while (true)
    {
      File localFile2;
      if (localIterator1.hasNext())
      {
        localFile2 = (File)localIterator1.next();
        if (localList == null)
          break label635;
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
        {
          com.tencent.feedback.common.a.d locald2 = (com.tencent.feedback.common.a.d)localIterator2.next();
          if ((!localFile2.getAbsolutePath().equals(locald2.a())) || (localFile2.lastModified() != locald2.b()) || (localFile2.length() != locald2.c()) || (locald2.d() == null))
            continue;
          localIterator2.remove();
          localArrayList2.add(locald2);
          Object[] arrayOfObject5 = new Object[4];
          arrayOfObject5[0] = locald2.a();
          arrayOfObject5[1] = locald2.f();
          arrayOfObject5[2] = locald2.d();
          arrayOfObject5[3] = Long.valueOf(localFile2.lastModified());
          e.f("rqdp{  BufFB existed n:}%s ,ar:%s, md:%s ,ut:%d", arrayOfObject5);
        }
      }
      for (int k = 1; ; k = 0)
      {
        if (k != 0)
          break label639;
        long l1 = System.currentTimeMillis();
        byte[] arrayOfByte = com.tencent.feedback.anr.a.a(localFile2.getAbsolutePath(), "SHA-1");
        long l2 = System.currentTimeMillis() - l1;
        if (arrayOfByte != null)
        {
          com.tencent.feedback.common.a.d locald1 = new com.tencent.feedback.common.a.d();
          locald1.a(1);
          locald1.a(localFile2.getAbsolutePath());
          StringBuilder localStringBuilder = new StringBuilder();
          com.tencent.feedback.common.d.a(this.a);
          locald1.c(com.tencent.feedback.common.d.d());
          locald1.c(localFile2.length());
          locald1.b(localFile2.lastModified());
          locald1.b(com.tencent.feedback.anr.a.a(arrayOfByte, false));
          Object[] arrayOfObject3 = new Object[4];
          arrayOfObject3[0] = locald1.a();
          arrayOfObject3[1] = locald1.f();
          arrayOfObject3[2] = locald1.d();
          arrayOfObject3[3] = Long.valueOf(l2);
          e.f("rqdp{  BufFB new }n:%s , ar:%s , md:%s , cs:%d", arrayOfObject3);
          localArrayList2.add(locald1);
          break;
        }
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = localFile2.getAbsolutePath();
        arrayOfObject4[1] = Long.valueOf(l2);
        e.f("rqdp{  Error BufFB md fail! pth:}%s , rqdp{  cs:}%d", arrayOfObject4);
        break;
        int i = com.tencent.feedback.common.a.a.a(this.a, 1);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(i);
        e.b("rqdp{  LBFTask del n: }%d", arrayOfObject1);
        if (localArrayList2.size() > 0)
        {
          int j = com.tencent.feedback.common.a.a.c(this.a, localArrayList2);
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(j);
          e.b("rqdp{  LBFTask ins n: }%d", arrayOfObject2);
        }
        return;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.jni.a
 * JD-Core Version:    0.6.0
 */