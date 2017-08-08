package com.tencent.component.net.download.multiplex.download;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.tencent.component.net.download.multiplex.download.extension.DBHelper;
import com.tencent.component.utils.log.LogUtil;

public class DownloadDBHelper
{
  private static final String A = "DownloadDBHelper";
  private static final String B = "id=?";
  private static final String C = "url=?";
  private static final String D = "extend_1=?";
  private static final String E = "annotation=?";
  public static final String a = "download";
  public static final String b = "id";
  public static final String c = "url";
  public static final String d = "filename";
  public static final String e = "filefolderpath";
  public static final String f = "totalsize";
  public static final String g = "downloadsize";
  public static final String h = "status";
  public static final String i = "createdate";
  public static final String j = "donedate";
  public static final String k = "supportresume";
  public static final String l = "referer";
  public static final String m = "flag";
  public static final String n = "costtime";
  public static final String o = "etag";
  public static final String p = "threadnum";
  public static final String q = "annotation";
  public static final String r = "annotationext";
  public static final String s = "extend_1";
  public static final String t = "extend_2";
  public static final String u = "extend_3";
  public static final String[] v = { "id", "url", "filename", "filefolderpath", "totalsize", "downloadsize", "status", "createdate", "donedate", "supportresume", "referer", "flag", "costtime", "etag", "threadnum", "annotation", "extend_1", "extend_2", "extend_3" };
  public static final String w = "CREATE TABLE download (id INTEGER PRIMARY KEY autoincrement, url TEXT, filename TEXT, filefolderpath TEXT, totalsize LONG, downloadsize LONG, status BYTE, supportresume INTEGER, createdate INTEGER, donedate INTEGER, referer TEXT DEFAULT '', flag INTEGER DEFAULT 0,costtime INTEGER,  INTEGER, etag TEXT, threadnum INTEGER DEFAULT 0,annotation TEXT, annotationext TEXT, extend_1 INTEGER DEFAULT 0,  extend_2 INTEGER DEFAULT 0,  extend_3 INTEGER DEFAULT 0);";
  public static final String x = "CREATE INDEX url_fileName_index on download (url, filename);";
  public static final String y = "DROP INDEX url_fileName_index;";
  public static final String z = "DROP TABLE download;";
  private DBHelper F = DBHelper.a();

  public DownloadDBHelper()
  {
    a();
  }

  public Cursor a(String paramString)
  {
    return this.F.a("download", null, "url=?", new String[] { paramString }, null);
  }

  public void a()
  {
    monitorenter;
    try
    {
      if (!this.F.a("download"))
      {
        this.F.b("CREATE TABLE download (id INTEGER PRIMARY KEY autoincrement, url TEXT, filename TEXT, filefolderpath TEXT, totalsize LONG, downloadsize LONG, status BYTE, supportresume INTEGER, createdate INTEGER, donedate INTEGER, referer TEXT DEFAULT '', flag INTEGER DEFAULT 0,costtime INTEGER,  INTEGER, etag TEXT, threadnum INTEGER DEFAULT 0,annotation TEXT, annotationext TEXT, extend_1 INTEGER DEFAULT 0,  extend_2 INTEGER DEFAULT 0,  extend_3 INTEGER DEFAULT 0);");
        this.F.b("CREATE INDEX url_fileName_index on download (url, filename);");
      }
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public boolean a(int paramInt)
  {
    try
    {
      DBHelper localDBHelper = this.F;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = String.valueOf(paramInt);
      int i1 = localDBHelper.a("download", "id=?", arrayOfString);
      return i1 > 0;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public boolean a(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null);
    while (true)
    {
      return false;
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("url", paramDownloadTask.v());
      localContentValues.put("filename", paramDownloadTask.q());
      localContentValues.put("filefolderpath", paramDownloadTask.t());
      localContentValues.put("totalsize", Long.valueOf(paramDownloadTask.x()));
      localContentValues.put("downloadsize", Long.valueOf(paramDownloadTask.w()));
      localContentValues.put("status", Byte.valueOf(paramDownloadTask.an()));
      int i1;
      long l1;
      if (paramDownloadTask.F())
      {
        i1 = 1;
        localContentValues.put("supportresume", Integer.valueOf(i1));
        l1 = System.currentTimeMillis();
        localContentValues.put("createdate", Long.valueOf(l1));
        localContentValues.put("donedate", Long.valueOf(l1));
        localContentValues.put("referer", paramDownloadTask.J());
        localContentValues.put("flag", Integer.valueOf(paramDownloadTask.K()));
        localContentValues.put("costtime", Integer.valueOf(0));
        String str = paramDownloadTask.C();
        if (!TextUtils.isEmpty(str))
          localContentValues.put("etag", str);
        localContentValues.put("threadnum", Integer.valueOf(paramDownloadTask.D()));
        localContentValues.put("annotation", paramDownloadTask.ak());
        localContentValues.put("annotationext", paramDownloadTask.aj());
        localContentValues.put("extend_1", paramDownloadTask.a());
        localContentValues.put("extend_2", Long.valueOf(paramDownloadTask.b()));
      }
      try
      {
        int i3 = this.F.a("download", localContentValues);
        i2 = i3;
        LogUtil.d("DownloadDBHelper", "New insert task id - " + i2);
        if (i2 <= -1)
          continue;
        paramDownloadTask.c(i2);
        paramDownloadTask.c(l1);
        return true;
        i1 = 0;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          int i2 = -1;
        }
      }
    }
  }

  public Cursor b()
  {
    return this.F.a("download", "status!=3", "createdate ASC");
  }

  public Cursor b(int paramInt)
  {
    String str = "id=" + paramInt;
    return this.F.a("download", str);
  }

  public Cursor b(String paramString)
  {
    return this.F.a("download", null, "extend_1=?", new String[] { paramString }, null);
  }

  public boolean b(DownloadTask paramDownloadTask)
  {
    int i1 = 1;
    if (paramDownloadTask == null)
      return false;
    String str1 = paramDownloadTask.v();
    String str2 = paramDownloadTask.q();
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("url", str1);
    localContentValues.put("filename", str2);
    localContentValues.put("filefolderpath", paramDownloadTask.t());
    localContentValues.put("totalsize", Long.valueOf(paramDownloadTask.x()));
    localContentValues.put("downloadsize", Long.valueOf(paramDownloadTask.w()));
    localContentValues.put("status", Byte.valueOf(paramDownloadTask.an()));
    int i2;
    if (paramDownloadTask.F())
      i2 = i1;
    while (true)
    {
      localContentValues.put("supportresume", Integer.valueOf(i2));
      localContentValues.put("referer", paramDownloadTask.J());
      localContentValues.put("flag", Integer.valueOf(paramDownloadTask.K()));
      localContentValues.put("donedate", Long.valueOf(System.currentTimeMillis()));
      localContentValues.put("costtime", Long.valueOf(paramDownloadTask.H()));
      localContentValues.put("etag", paramDownloadTask.C());
      localContentValues.put("threadnum", Integer.valueOf(paramDownloadTask.D()));
      localContentValues.put("annotation", paramDownloadTask.ak());
      localContentValues.put("annotationext", paramDownloadTask.aj());
      localContentValues.put("extend_1", paramDownloadTask.a());
      localContentValues.put("extend_2", Long.valueOf(paramDownloadTask.b()));
      try
      {
        DBHelper localDBHelper = this.F;
        String[] arrayOfString = new String[1];
        arrayOfString[0] = String.valueOf(paramDownloadTask.E());
        int i3 = localDBHelper.a("download", localContentValues, "id=?", arrayOfString);
        if (i3 > 0);
        while (true)
        {
          return i1;
          i2 = 0;
          break;
          i1 = 0;
        }
      }
      catch (Exception localException)
      {
        while (true)
        {
          localException.printStackTrace();
          i1 = 0;
        }
      }
    }
  }

  public Cursor c()
  {
    return this.F.a("download", "status=3", "donedate DESC");
  }

  public Cursor c(String paramString)
  {
    return this.F.a("download", null, "url=? AND status=3", new String[] { paramString }, null);
  }

  public Cursor d()
  {
    return this.F.a("download", null, null);
  }

  public Cursor d(String paramString)
  {
    return this.F.a("download", null, "annotation=? AND status=3", new String[] { paramString }, null);
  }

  public SQLiteDatabase e()
  {
    try
    {
      SQLiteDatabase localSQLiteDatabase = this.F.b();
      return localSQLiteDatabase;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.DownloadDBHelper
 * JD-Core Version:    0.6.0
 */