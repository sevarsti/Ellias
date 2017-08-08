package com.tencent.component.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.tencent.component.ComponentContext;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.cache.sp.PreferenceUtil;
import com.tencent.component.db.entity.IdEntity;
import com.tencent.component.db.entity.TableEntity;
import com.tencent.component.db.exception.DBException;
import com.tencent.component.db.sqlite.CursorUtils;
import com.tencent.component.db.sqlite.Selector;
import com.tencent.component.db.sqlite.SqlInfo;
import com.tencent.component.db.sqlite.SqlInfoBuilder;
import com.tencent.component.db.sqlite.WhereBuilder;
import com.tencent.component.db.util.TableUtils;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.IOUtils;
import com.tencent.component.utils.KeyValue;
import com.tencent.component.utils.log.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@PluginApi(a=6)
public class EntityManager
{
  private static final String a = "EntityManager";
  private static final String b = "table_versions";
  private final SharedPreferences c;
  private final String d;
  private Boolean e = Boolean.valueOf(false);
  private Class f;
  private ISQLiteDatabase g = null;
  private ISQLiteOpenHelper h = null;
  private a i;
  private EntityContext j;

  protected EntityManager(Context paramContext, Class paramClass, EntityManager.UpdateListener paramUpdateListener, String paramString1, String paramString2, ClassLoader paramClassLoader, ISQLiteOpenHelper paramISQLiteOpenHelper)
  {
    this.h = paramISQLiteOpenHelper;
    this.g = f();
    this.f = paramClass;
    if (!TextUtils.isEmpty(paramString2))
      paramString2 = paramString2.toLowerCase().replace('.', '_');
    String str = TableUtils.a(paramClass, paramString2);
    this.j = new EntityContext(this, str, paramClassLoader);
    this.c = PreferenceUtil.a(paramContext, "table_versions");
    this.d = (paramString1 + "_" + str + "_version");
    a(paramUpdateListener);
    b(str);
  }

  private Cursor a(String paramString, String[] paramArrayOfString)
  {
    c(paramString);
    try
    {
      ISQLiteDatabase localISQLiteDatabase = e();
      if (localISQLiteDatabase != null)
        return SafeCursorWrapper.a(localISQLiteDatabase.rawQuery(paramString, paramArrayOfString));
      LogUtil.e("EntityManager", "rawQuery failed[cannot get sqlitedatabase]!");
      return null;
    }
    catch (Exception localException)
    {
      while (true)
        LogUtil.e("EntityManager", localException.getMessage(), localException);
    }
  }

  private static void a(ContentValues paramContentValues, List paramList)
  {
    Iterator localIterator;
    if ((paramList != null) && (paramContentValues != null))
      localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      KeyValue localKeyValue = (KeyValue)localIterator.next();
      Object localObject = localKeyValue.b();
      if (localObject == null)
        continue;
      if (((localObject instanceof byte[])) || ((localObject instanceof Byte[])))
      {
        paramContentValues.put(localKeyValue.a(), (byte[])(byte[])localObject);
        continue;
      }
      if ((localObject instanceof Byte))
      {
        paramContentValues.put(localKeyValue.a(), (Byte)localObject);
        continue;
      }
      if ((localObject instanceof Boolean))
      {
        paramContentValues.put(localKeyValue.a(), (Boolean)localObject);
        continue;
      }
      paramContentValues.put(localKeyValue.a(), localObject.toString());
      continue;
      LogUtil.w("EntityManager", "List<KeyValue> is empty or ContentValues is empty!");
    }
  }

  private void a(EntityManager.UpdateListener paramUpdateListener)
  {
    ISQLiteDatabase localISQLiteDatabase = this.g;
    TableEntity localTableEntity = TableEntity.a(this.f, this.j);
    int k = localTableEntity.c();
    int m = this.c.getInt(this.d, -1);
    if ((m > 0) && (m != k))
    {
      String str = localTableEntity.a();
      LogUtil.i("EntityManager", "table version changed(table:" + str + "| oldVersion:" + m + " |version:" + k + ")");
      if (paramUpdateListener != null)
      {
        LogUtil.i("EntityManager", "tableUpdateListener is not empty , dispatch version change event to listener.");
        if (k > m)
          paramUpdateListener.onTableUpgrade(localISQLiteDatabase, str, m, k);
        while (true)
        {
          this.c.edit().putInt(this.d, k).commit();
          return;
          paramUpdateListener.onTableDowngrade(localISQLiteDatabase, str, m, k);
        }
      }
      if (localISQLiteDatabase != null)
      {
        LogUtil.i("EntityManager", "tableUpdateListener is empty , try to drop the table " + str);
        try
        {
          if (g())
          {
            this.c.edit().putInt(this.d, k).commit();
            return;
          }
        }
        catch (SQLException localSQLException)
        {
          LogUtil.e("EntityManager", "It occurs some exception when drop table -->" + localSQLException.getMessage(), localSQLException);
          return;
          LogUtil.e("EntityManager", "drop table " + str + " failed .");
          return;
        }
        catch (Exception localException)
        {
          LogUtil.e("EntityManager", "It occurs some exception when drop table -->" + localException.getMessage(), localException);
          return;
        }
      }
      LogUtil.e("EntityManager", "db is empty when table version changed [ tableName:" + str + "]");
      return;
    }
    this.c.edit().putInt(this.d, k).commit();
  }

  private void a(ISQLiteDatabase paramISQLiteDatabase)
  {
    a(SqlInfoBuilder.a(this.f, null, this.j), paramISQLiteDatabase);
  }

  private void a(SqlInfo paramSqlInfo, ISQLiteDatabase paramISQLiteDatabase)
  {
    if (paramSqlInfo == null)
      return;
    c(paramSqlInfo.a());
    if (paramISQLiteDatabase == null);
    try
    {
      paramISQLiteDatabase = e();
      if (paramISQLiteDatabase == null)
        break label69;
      if (paramSqlInfo.b() != null)
      {
        paramISQLiteDatabase.execSQL(paramSqlInfo.a(), paramSqlInfo.c());
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      throw new DBException(localThrowable);
    }
    paramISQLiteDatabase.execSQL(paramSqlInfo.a());
    return;
    label69: LogUtil.e("EntityManager", "cannot get sqlitedatabase!");
  }

  private void a(Object paramObject, ISQLiteDatabase paramISQLiteDatabase)
  {
    if (TableUtils.d(this.f).a(paramObject) != null)
    {
      b(paramObject, paramISQLiteDatabase);
      return;
    }
    c(paramObject, paramISQLiteDatabase);
  }

  private void a(Object paramObject, ISQLiteDatabase paramISQLiteDatabase, String[] paramArrayOfString)
  {
    a(SqlInfoBuilder.a(this.j, this.f, paramObject, paramArrayOfString), paramISQLiteDatabase);
  }

  private void a(String paramString, ISQLiteDatabase paramISQLiteDatabase)
  {
    if (paramString == null)
      return;
    c(paramString);
    if (paramISQLiteDatabase == null);
    try
    {
      paramISQLiteDatabase = e();
      if (paramISQLiteDatabase != null)
      {
        paramISQLiteDatabase.execSQL(paramString);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      throw new DBException(localThrowable);
    }
    LogUtil.e("EntityManager", "cannot get sqlitedatabase!");
  }

  private void b(ISQLiteDatabase paramISQLiteDatabase)
  {
    try
    {
      paramISQLiteDatabase.beginTransaction();
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
    }
  }

  private void b(Object paramObject, ISQLiteDatabase paramISQLiteDatabase)
  {
    a(SqlInfoBuilder.b(this.f, paramObject, this.j), paramISQLiteDatabase);
  }

  private void b(String paramString)
  {
    SqlInfo localSqlInfo = SqlInfoBuilder.a(this.f, this.j);
    try
    {
      a(localSqlInfo);
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
    }
  }

  private void c(ISQLiteDatabase paramISQLiteDatabase)
  {
    try
    {
      paramISQLiteDatabase.setTransactionSuccessful();
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
    }
  }

  private void c(String paramString)
  {
    if (DebugUtil.a(ComponentContext.a()))
      LogUtil.d("EntityManager", paramString);
  }

  private boolean c(Object paramObject, ISQLiteDatabase paramISQLiteDatabase)
  {
    TableEntity localTableEntity = TableEntity.a(this.f, this.j);
    IdEntity localIdEntity = localTableEntity.b();
    List localList = SqlInfoBuilder.f(this.f, paramObject, this.j);
    ContentValues localContentValues;
    Long localLong;
    if ((localList != null) && (localList.size() > 0))
    {
      localContentValues = new ContentValues();
      a(localContentValues, localList);
      if (paramISQLiteDatabase == null)
        paramISQLiteDatabase = e();
      if (paramISQLiteDatabase == null)
        break label156;
      localLong = Long.valueOf(paramISQLiteDatabase.insert(localTableEntity.a(), null, localContentValues));
      if (!localIdEntity.f())
        break label129;
      if (localLong.longValue() != -1L);
    }
    else
    {
      return false;
    }
    localIdEntity.a(paramObject, localLong.longValue());
    while (true)
    {
      return true;
      label129: if (!localIdEntity.g())
        continue;
      localIdEntity.a(paramObject, localContentValues.get(localIdEntity.b()));
    }
    label156: LogUtil.e("EntityManager", "saveWithoutTransaction failed(cannot get sqlitedatabase)!");
    return false;
  }

  private void d(ISQLiteDatabase paramISQLiteDatabase)
  {
    try
    {
      paramISQLiteDatabase.endTransaction();
      return;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
    }
  }

  private void d(Object paramObject, ISQLiteDatabase paramISQLiteDatabase)
  {
    a(SqlInfoBuilder.c(this.f, paramObject, this.j), paramISQLiteDatabase);
  }

  private ISQLiteDatabase e()
  {
    ISQLiteDatabase localISQLiteDatabase = this.g;
    if ((localISQLiteDatabase == null) || (!localISQLiteDatabase.isOpen()))
      localISQLiteDatabase = f();
    return localISQLiteDatabase;
  }

  private ISQLiteDatabase f()
  {
    ISQLiteDatabase localISQLiteDatabase = null;
    try
    {
      localISQLiteDatabase = this.h.a();
      this.e = Boolean.valueOf(true);
      return localISQLiteDatabase;
    }
    catch (Exception localException)
    {
      this.e = Boolean.valueOf(false);
    }
    return localISQLiteDatabase;
  }

  private boolean g()
  {
    TableEntity localTableEntity = TableEntity.a(this.f, this.j);
    try
    {
      execSQL("DROP TABLE IF EXISTS " + localTableEntity.a());
      if (c())
        return false;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
      return false;
    }
    TableEntity.a(this.f);
    return true;
  }

  private void h()
  {
    a locala = this.i;
    if (locala != null)
      locala.a(this);
  }

  public Cursor a(String paramString)
  {
    return a(paramString, null);
  }

  public Class a()
  {
    return this.f;
  }

  void a(a parama)
  {
    this.i = parama;
  }

  public void a(SqlInfo paramSqlInfo)
  {
    a(paramSqlInfo, null);
  }

  public void a(List paramList)
  {
    if (paramList == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(localISQLiteDatabase);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          c(localIterator.next(), localISQLiteDatabase);
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
        c(localISQLiteDatabase);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "replace entities failed(cannot get sqlitedatabase)!");
  }

  public Boolean b()
  {
    if (this.e.booleanValue())
    {
      if (this.g.isOpen())
        return Boolean.valueOf(true);
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(false);
  }

  public boolean c()
  {
    TableEntity localTableEntity = TableEntity.a(this.f, this.j);
    Cursor localCursor = null;
    try
    {
      localCursor = a("SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + localTableEntity.a() + "'");
      if ((localCursor != null) && (localCursor.moveToNext()))
      {
        int k = localCursor.getInt(0);
        if (k > 0)
          return true;
      }
      return false;
    }
    finally
    {
      IOUtils.a(localCursor);
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public void close()
  {
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      localISQLiteDatabase.close();
    h();
  }

  @PluginApi(a=100)
  public Object createEntityByCursor(Cursor paramCursor)
  {
    if (paramCursor != null)
      return CursorUtils.a(paramCursor, this.f, this.j);
    return null;
  }

  public boolean d()
  {
    return false;
  }

  @PluginApi(a=6)
  public void delete(WhereBuilder paramWhereBuilder)
  {
    if (paramWhereBuilder == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(SqlInfoBuilder.a(this.f, paramWhereBuilder, this.j), localISQLiteDatabase);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "delete failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void delete(Object paramObject)
  {
    if (paramObject == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        d(paramObject, localISQLiteDatabase);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "delete entity failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void deleteAll()
  {
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(localISQLiteDatabase);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "deleteAll failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void deleteAll(List paramList)
  {
    if ((paramList == null) || (paramList.size() < 1))
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          d(localIterator.next(), localISQLiteDatabase);
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
        c(localISQLiteDatabase);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "delete entities failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void deleteById(Object paramObject)
  {
    if (paramObject == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(SqlInfoBuilder.d(this.f, paramObject, this.j), localISQLiteDatabase);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "deleteById failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void dropDb()
  {
    Cursor localCursor = null;
    try
    {
      localCursor = a("SELECT name FROM sqlite_master WHERE type ='table'");
      if (localCursor != null)
        while (true)
        {
          boolean bool = localCursor.moveToNext();
          if (!bool)
            break;
          try
          {
            String str = localCursor.getString(0);
            execSQL("DROP TABLE " + str);
            TableEntity.a(str);
          }
          catch (Throwable localThrowable)
          {
            LogUtil.e("EntityManager", localThrowable.getMessage(), localThrowable);
          }
        }
    }
    finally
    {
      IOUtils.a(localCursor);
    }
    IOUtils.a(localCursor);
  }

  @PluginApi(a=6)
  public void dropTable()
  {
    g();
  }

  @PluginApi(a=7)
  public void execSQL(String paramString)
  {
    a(paramString, null);
  }

  @PluginApi(a=6)
  public List findAll()
  {
    return findAll(Selector.create());
  }

  @PluginApi(a=6)
  public List findAll(Selector paramSelector)
  {
    ArrayList localArrayList;
    if (paramSelector == null)
      localArrayList = null;
    Cursor localCursor;
    do
    {
      return localArrayList;
      localCursor = a(paramSelector.a(this.f, this.j));
      localArrayList = new ArrayList();
    }
    while (localCursor == null);
    try
    {
      while (localCursor.moveToNext())
        localArrayList.add(CursorUtils.a(localCursor, this.f, this.j));
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
      return localArrayList;
      return localArrayList;
    }
    finally
    {
      IOUtils.a(localCursor);
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public Object findById(Object paramObject)
  {
    if (paramObject == null)
      return null;
    IdEntity localIdEntity = TableEntity.a(this.f, this.j).b();
    Cursor localCursor = a(Selector.create().where(localIdEntity.b(), "=", paramObject).limit(1).a(this.f, this.j));
    try
    {
      if (localCursor.moveToNext())
      {
        Object localObject2 = CursorUtils.a(localCursor, this.f, this.j);
        return localObject2;
      }
      return null;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
      return null;
    }
    finally
    {
      IOUtils.a(localCursor);
    }
    throw localObject1;
  }

  @PluginApi(a=6)
  public Object findFirst(Selector paramSelector)
  {
    if (paramSelector == null)
      return null;
    Cursor localCursor = a(paramSelector.limit(1).a(this.f, this.j));
    try
    {
      if (localCursor.moveToNext())
      {
        Object localObject2 = CursorUtils.a(localCursor, this.f, this.j);
        return localObject2;
      }
      return null;
    }
    catch (Exception localException)
    {
      LogUtil.e("EntityManager", localException.getMessage(), localException);
      return null;
    }
    finally
    {
      IOUtils.a(localCursor);
    }
    throw localObject1;
  }

  @PluginApi(a=6)
  public int getCount()
  {
    TableEntity localTableEntity = TableEntity.a(this.f, this.j);
    Cursor localCursor = null;
    try
    {
      localCursor = a("SELECT COUNT(*) FROM " + localTableEntity.a());
      if ((localCursor != null) && (localCursor.moveToNext()))
      {
        int k = localCursor.getInt(0);
        return k;
      }
      return 0;
    }
    finally
    {
      IOUtils.a(localCursor);
    }
    throw localObject;
  }

  @PluginApi(a=6)
  public Cursor query(Selector paramSelector)
  {
    if (paramSelector == null)
      return null;
    return a(paramSelector.a(this.f, this.j));
  }

  @PluginApi(a=6)
  public boolean save(Object paramObject)
  {
    boolean bool = false;
    if (paramObject == null)
      return false;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        bool = c(paramObject, localISQLiteDatabase);
        c(localISQLiteDatabase);
        return bool;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return bool;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "save entity failed(cannot get sqlitedatabase)!");
    return false;
  }

  @PluginApi(a=6)
  public void saveAll(List paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          if (c(localIterator.next(), localISQLiteDatabase))
            continue;
          throw new DBException("saveBindingId error, transaction will not commit!");
        }
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
        c(localISQLiteDatabase);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "saveAll entities failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void saveOrUpdate(Object paramObject)
  {
    if (paramObject == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(paramObject, localISQLiteDatabase);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "saveOrUpdate entity failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void saveOrUpdateAll(List paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          a(localIterator.next(), localISQLiteDatabase);
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
        c(localISQLiteDatabase);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "saveOrUpdateAll failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=7)
  public void update(ContentValues paramContentValues, WhereBuilder paramWhereBuilder)
  {
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        localISQLiteDatabase.update(TableEntity.a(this.f, this.j).a(), paramContentValues, paramWhereBuilder.toString(), null);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "update entity failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void update(Object paramObject, WhereBuilder paramWhereBuilder, String[] paramArrayOfString)
  {
    if (paramObject == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(SqlInfoBuilder.a(this.j, this.f, paramObject, paramWhereBuilder, paramArrayOfString), localISQLiteDatabase);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "update entity failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void update(Object paramObject, String[] paramArrayOfString)
  {
    if (paramObject == null)
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        a(paramObject, localISQLiteDatabase, paramArrayOfString);
        c(localISQLiteDatabase);
        return;
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "update entity failed(cannot get sqlitedatabase)!");
  }

  @PluginApi(a=6)
  public void updateAll(List paramList, String[] paramArrayOfString)
  {
    if ((paramList == null) || (paramList.size() < 1))
      return;
    ISQLiteDatabase localISQLiteDatabase = e();
    if (localISQLiteDatabase != null)
      try
      {
        b(localISQLiteDatabase);
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
          a(localIterator.next(), localISQLiteDatabase, paramArrayOfString);
      }
      catch (Exception localException)
      {
        LogUtil.e("EntityManager", localException.getMessage(), localException);
        return;
        c(localISQLiteDatabase);
        return;
      }
      finally
      {
        d(localISQLiteDatabase);
      }
    LogUtil.e("EntityManager", "updateAll entities failed(cannot get sqlitedatabase)!");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.EntityManager
 * JD-Core Version:    0.6.0
 */