package com.tencent.android.tpush.service.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.tencent.android.tpush.data.CachedMessageIntent;
import com.tencent.android.tpush.data.MessageId;
import com.tencent.android.tpush.data.PushClickEntity;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.protocol.TpnsClickClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushClientReport;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushMsg;
import com.tencent.android.tpush.service.i;
import com.tencent.android.tpush.service.l;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class a
{
  private static a a = new a();
  private static final byte[] b = new byte[0];
  private static long c = 0L;
  private static volatile boolean d = false;

  public static a a()
  {
    return a;
  }

  private Object a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Object localObject = com.tencent.android.tpush.common.f.a(Rijndael.decrypt(c.c(paramContext, paramString1 + paramString2)));
      return localObject;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
    return null;
  }

  private void a(Context paramContext, String paramString1, String paramString2, ArrayList paramArrayList)
  {
    try
    {
      String str = Rijndael.encrypt(com.tencent.android.tpush.common.f.a(paramArrayList));
      c.a(paramContext, paramString1 + paramString2, str);
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  @SuppressLint({"SimpleDateFormat"})
  private void a(Intent paramIntent, String paramString, MessageId paramMessageId)
  {
    TLog.v("XGService", "dispatchMessageOnTime(" + paramIntent + "," + paramString + "," + paramMessageId + ")");
    long l1 = paramIntent.getExtras().getLong("multiPkg");
    long l2 = paramIntent.getExtras().getLong("accId");
    ArrayList localArrayList = new ArrayList();
    Object localObject;
    int i;
    label154: String str;
    if (l1 == 0L)
    {
      localArrayList.add(paramIntent.getPackage());
      localObject = localArrayList;
      TLog.i("XGService", "accid:" + l2 + ",multipkg:" + l1 + ",pkgs:" + ((List)localObject).size());
      i = 0;
      if (i >= ((List)localObject).size())
        return;
      str = (String)((List)localObject).get(i);
      TLog.i("XGService", ">> pkgs.get(" + i + "):" + str);
      if (!c.a(str))
        break label259;
      TLog.i("XGService", ">> msg.appPkgName is null!");
    }
    while (true)
    {
      i++;
      break label154;
      TLog.i("XGService", ">> multi_pkg");
      localObject = f.a().a(i.e(), l2);
      break;
      label259: if (!c.a(i.e(), str))
      {
        TLog.i("XGService", ">> " + str + " uninstalled");
        l.a().a(str);
        f.a().a(i.e(), str);
        continue;
      }
      if (a(i.e(), str, paramMessageId.id))
      {
        TLog.i("XGService", ">> msgId:" + paramMessageId.id + " has been acked.");
        continue;
      }
      if (b(i.e(), str, paramMessageId.id))
      {
        TLog.i("XGService", ">> msgId:" + paramMessageId.id + " has been cached.");
        continue;
      }
      com.tencent.android.tpush.data.b localb = CacheManager.getRegisterInfoByPkgName(str);
      if ((localb != null) && (localb.e > 0))
      {
        TLog.i("XGService", ">> " + str + " unregistered");
        continue;
      }
      paramIntent.setPackage(str);
      paramMessageId.pkgName = str;
      if (paramMessageId.id > 0L)
        a(i.e(), str, paramMessageId);
      SimpleDateFormat localSimpleDateFormat;
      try
      {
        localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        if ((c.a(paramMessageId.date)) || (localSimpleDateFormat.parse(paramMessageId.date).compareTo(localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date()))) <= 0))
          break label596;
        TLog.i("XGService", ">> date not accepted!");
        a(i.e(), paramString, paramIntent);
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
      }
      continue;
      label596: if ((c.a(paramMessageId.date)) || ((!c.a(paramMessageId.date)) && (localSimpleDateFormat.parse(paramMessageId.date).compareTo(localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date()))) == 0)))
      {
        if (c.a(paramIntent))
        {
          TLog.i("XGService", ">> send message intent:" + paramIntent);
          i.e().sendBroadcast(paramIntent);
          continue;
        }
        a(i.e(), paramString, paramIntent);
        continue;
      }
      if ((c.a(paramMessageId.date)) || (localSimpleDateFormat.parse(paramMessageId.date).compareTo(localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date()))) >= 0))
        continue;
      TLog.i("XGService", ">> send message intent:" + paramIntent);
      i.e().sendBroadcast(paramIntent);
    }
  }

  private void a(TpnsPushMsg paramTpnsPushMsg, long paramLong, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "distribute2SDK(" + paramTpnsPushMsg + "," + paramLong + "," + parama + ")");
    MessageId localMessageId = new MessageId();
    localMessageId.id = paramTpnsPushMsg.msgId;
    localMessageId.isAck = 0;
    localMessageId.accessId = paramTpnsPushMsg.accessId;
    localMessageId.host = c.b(parama.d());
    localMessageId.port = parama.e();
    localMessageId.pact = l.a(parama.b());
    localMessageId.apn = c.d(i.e());
    localMessageId.isp = c.e(i.e());
    localMessageId.pushTime = paramLong;
    localMessageId.serviceHost = i.e().getPackageName();
    localMessageId.receivedTime = System.currentTimeMillis();
    localMessageId.pkgName = paramTpnsPushMsg.appPkgName;
    localMessageId.busiMsgId = paramTpnsPushMsg.busiMsgId;
    localMessageId.timestamp = paramTpnsPushMsg.timestamp;
    localMessageId.msgType = paramTpnsPushMsg.type;
    localMessageId.multiPkg = paramTpnsPushMsg.multiPkg;
    localMessageId.date = paramTpnsPushMsg.date;
    TLog.i("confirmMs", ">> msg distribute @msgId=" + localMessageId.id + " @accId=" + localMessageId.accessId + " @timeUs=" + paramLong + " @recTime=" + localMessageId.receivedTime + " @msg.date=" + paramTpnsPushMsg.date + " @msg.busiMsgId=" + paramTpnsPushMsg.busiMsgId + " @msg.timestamp=" + paramTpnsPushMsg.timestamp + " @msg.type=" + paramTpnsPushMsg.type + " @msg.multiPkg=" + paramTpnsPushMsg.multiPkg);
    Intent localIntent = new Intent("com.tencent.android.tpush.action.INTERNAL_PUSH_MESSAGE");
    localIntent.setPackage(paramTpnsPushMsg.appPkgName);
    localIntent.putExtra("msgId", paramTpnsPushMsg.msgId);
    localIntent.putExtra("title", Rijndael.encrypt(paramTpnsPushMsg.title));
    localIntent.putExtra("content", Rijndael.encrypt(paramTpnsPushMsg.content));
    localIntent.putExtra("date", paramTpnsPushMsg.date);
    localIntent.putExtra("type", paramTpnsPushMsg.type);
    localIntent.putExtra("accId", paramTpnsPushMsg.accessId);
    localIntent.putExtra("busiMsgId", paramTpnsPushMsg.busiMsgId);
    localIntent.putExtra("timestamps", paramTpnsPushMsg.timestamp);
    localIntent.putExtra("multiPkg", paramTpnsPushMsg.multiPkg);
    try
    {
      localIntent.putExtra("enKeySet", com.tencent.android.tpush.common.f.a(new String[] { "title", "content" }));
      a(localIntent, paramTpnsPushMsg.appPkgName, localMessageId);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        TLog.e("XGService", localException.toString());
    }
  }

  private ArrayList c(Context paramContext, String paramString)
  {
    Object localObject;
    if ((paramContext != null) && (!c.a(paramString)))
    {
      TLog.v("XGService", "@@ getClickedMessageIdListByPkgName(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + ")");
      localObject = a(paramContext, paramString, ".tpns.msg.id.clicked");
      if (localObject == null);
    }
    for (ArrayList localArrayList = (ArrayList)localObject; ; localArrayList = null)
    {
      TLog.i("XGService", ">> clickedMsgIdList : " + localArrayList);
      if (localArrayList == null)
        localArrayList = new ArrayList();
      return localArrayList;
    }
  }

  private ArrayList d(Context paramContext, String paramString)
  {
    Object localObject = null;
    if (paramContext != null)
    {
      boolean bool = c.a(paramString);
      localObject = null;
      if (!bool)
      {
        TLog.v("XGService", "@@ getAckedMsgIdListByPackName(" + paramContext.getPackageName() + ")");
        ArrayList localArrayList1 = a(paramContext, paramString);
        localObject = null;
        if (localArrayList1 != null)
        {
          int i = localArrayList1.size();
          localObject = null;
          if (i > 0)
          {
            ArrayList localArrayList2 = new ArrayList();
            Iterator localIterator = localArrayList1.iterator();
            while (localIterator.hasNext())
            {
              MessageId localMessageId = (MessageId)localIterator.next();
              if (!localMessageId.a())
                continue;
              localArrayList2.add(localMessageId);
            }
            localObject = localArrayList2;
          }
        }
      }
    }
    return localObject;
  }

  private void d(Context paramContext, Intent paramIntent)
  {
    TLog.v("XGService", "@@ requestAck(" + paramContext.getPackageName() + "," + paramIntent + ")");
    long l = System.currentTimeMillis();
    ArrayList localArrayList1 = b(paramContext);
    TLog.i("XGService", ">>> requestAck >> idList:" + localArrayList1);
    if ((l - c >= com.tencent.android.tpush.service.a.a.m) || ((localArrayList1 != null) && (localArrayList1.size() >= com.tencent.android.tpush.service.a.a.l)))
    {
      StringBuilder localStringBuilder1 = new StringBuilder().append(">> msg ack available, ack_count:").append(com.tencent.android.tpush.service.a.a.l).append(",ack_duration:").append(com.tencent.android.tpush.service.a.a.m).append("current_list:");
      int i = 0;
      if (localArrayList1 == null);
      while (true)
      {
        TLog.i("XGService", i + "time:" + l + " - " + c);
        if (!d)
          break;
        TLog.i("XGService", ">> msg ack is uploading , this time will give up!");
        return;
        i = localArrayList1.size();
      }
      ArrayList localArrayList2 = a(paramContext, localArrayList1);
      if ((localArrayList2 != null) && (localArrayList2.size() > 0))
      {
        d = true;
        c = l;
      }
      l.a().a(localArrayList2, new b(this, paramContext, paramIntent));
      return;
    }
    StringBuilder localStringBuilder2 = new StringBuilder().append(">> msg ack unavailable, ack_count:").append(com.tencent.android.tpush.service.a.a.l).append(",ack_duration:").append(com.tencent.android.tpush.service.a.a.m).append("current_list:");
    int j = 0;
    if (localArrayList1 == null);
    while (true)
    {
      TLog.i("XGService", j + ",time:" + l + " - " + c);
      return;
      j = localArrayList1.size();
    }
  }

  public ArrayList a(Context paramContext)
  {
    TLog.v("XGService", "@@ getClickedReqMsgIdList(" + paramContext.getPackageName() + ")");
    ArrayList localArrayList1 = c(paramContext);
    Object localObject = null;
    if (localArrayList1 != null)
    {
      int i = localArrayList1.size();
      localObject = null;
      if (i > 0)
      {
        ArrayList localArrayList2 = new ArrayList();
        Iterator localIterator = localArrayList1.iterator();
        while (localIterator.hasNext())
        {
          PushClickEntity localPushClickEntity = (PushClickEntity)localIterator.next();
          TpnsClickClientReport localTpnsClickClientReport = new TpnsClickClientReport();
          localTpnsClickClientReport.accessId = localPushClickEntity.accessId;
          localTpnsClickClientReport.msgId = localPushClickEntity.msgId;
          localTpnsClickClientReport.broadcastId = localPushClickEntity.broadcastId;
          localTpnsClickClientReport.timestamp = localPushClickEntity.timestamp;
          localTpnsClickClientReport.type = localPushClickEntity.type;
          localTpnsClickClientReport.clickTime = localPushClickEntity.clickTime;
          localTpnsClickClientReport.action = localPushClickEntity.action;
          TLog.d("confirmMs", "+++ getClickedReqMsgIdList @msgId=" + localTpnsClickClientReport.msgId + " @accId=" + localTpnsClickClientReport.accessId + " @broadcastId=" + localTpnsClickClientReport.broadcastId + " @timestamp=" + localTpnsClickClientReport.timestamp);
          localArrayList2.add(localTpnsClickClientReport);
          if (localArrayList2.size() > 30)
            return localArrayList2;
        }
        localObject = localArrayList2;
      }
    }
    return localObject;
  }

  public ArrayList a(Context paramContext, String paramString)
  {
    Object localObject;
    if ((paramContext != null) && (!c.a(paramString)))
    {
      TLog.v("XGService", "@@ getMessageIdListByPkgName(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + ")");
      localObject = a(paramContext, paramString, ".tpns.msg.id");
      if (localObject == null);
    }
    for (ArrayList localArrayList = (ArrayList)localObject; ; localArrayList = null)
    {
      TLog.i("XGService", ">> msgIdList : " + localArrayList);
      if (localArrayList == null)
        localArrayList = new ArrayList();
      return localArrayList;
    }
  }

  public ArrayList a(Context paramContext, List paramList)
  {
    TLog.v("XGService", "@@ getAckedReqMsgIdList(" + paramContext.getPackageName() + ")");
    Object localObject = null;
    if (paramList != null)
    {
      int i = paramList.size();
      localObject = null;
      if (i > 0)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          MessageId localMessageId = (MessageId)localIterator.next();
          TpnsPushClientReport localTpnsPushClientReport = new TpnsPushClientReport();
          localTpnsPushClientReport.accessId = localMessageId.accessId;
          localTpnsPushClientReport.msgId = localMessageId.id;
          localTpnsPushClientReport.apn = localMessageId.apn;
          localTpnsPushClientReport.isp = localMessageId.isp;
          localTpnsPushClientReport.locip = localMessageId.host;
          localTpnsPushClientReport.locport = localMessageId.port;
          localTpnsPushClientReport.pack = localMessageId.pact;
          localTpnsPushClientReport.timeUs = localMessageId.pushTime;
          localTpnsPushClientReport.qua = CacheManager.getQua(paramContext, localTpnsPushClientReport.accessId);
          localTpnsPushClientReport.serviceHost = localMessageId.serviceHost;
          localTpnsPushClientReport.confirmMs = (System.currentTimeMillis() - localMessageId.receivedTime);
          localTpnsPushClientReport.broadcastId = localMessageId.busiMsgId;
          localTpnsPushClientReport.timestamp = localMessageId.timestamp;
          localTpnsPushClientReport.type = localMessageId.msgType;
          localTpnsPushClientReport.receiveTime = (localMessageId.receivedTime / 1000L);
          TLog.d("confirmMs", "+++ getAckedReqMsgIdList @msgId=" + localTpnsPushClientReport.msgId + " @accId=" + localTpnsPushClientReport.accessId + " @timeUs=" + localTpnsPushClientReport.timeUs + " @confirmMs=" + localTpnsPushClientReport.confirmMs + " @recTime=" + localMessageId.receivedTime + " @msgType=" + localMessageId.msgType);
          localArrayList.add(localTpnsPushClientReport);
          if (localArrayList.size() > 30)
            return localArrayList;
        }
        localObject = localArrayList;
      }
    }
    return localObject;
  }

  public void a(Context paramContext, Intent paramIntent)
  {
    if ((paramContext != null) && (paramIntent != null))
    {
      TLog.v("XGService", "@@ msgAck(" + paramContext.getPackageName() + "," + paramIntent + ")");
      long l = paramIntent.getLongExtra("msgId", -1L);
      a(paramContext, paramIntent.getStringExtra("packName"), l, 1);
      d(paramContext, paramIntent);
    }
  }

  public void a(Context paramContext, String paramString, long paramLong, short paramShort)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramLong > 0L))
    {
      ArrayList localArrayList;
      try
      {
        TLog.v("XGService", "@@ updateMsgIdFlag(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramLong + ")");
        localArrayList = a(paramContext, paramString);
        if ((localArrayList == null) || (localArrayList.size() <= 0))
          break label156;
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          MessageId localMessageId = (MessageId)localIterator.next();
          if (localMessageId.id != paramLong)
            continue;
          localMessageId.isAck = paramShort;
        }
      }
      finally
      {
        monitorexit;
      }
      b(paramContext, paramString, localArrayList);
    }
    label156: monitorexit;
  }

  public void a(Context paramContext, String paramString, Intent paramIntent)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if (paramContext != null);
    while (true)
    {
      int i;
      try
      {
        if ((c.a(paramString)) || (paramIntent == null))
          continue;
        TLog.v("XGService", "@@ addCachedMsgIntent(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + ")");
        CachedMessageIntent localCachedMessageIntent1 = new CachedMessageIntent();
        localCachedMessageIntent1.pkgName = paramString;
        localCachedMessageIntent1.msgId = paramIntent.getLongExtra("msgId", -1L);
        localCachedMessageIntent1.intent = Rijndael.encrypt(paramIntent.toUri(1));
        ArrayList localArrayList1 = b(paramContext, paramString);
        if (localArrayList1 != null)
          continue;
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add(localCachedMessageIntent1);
        c(paramContext, paramString, localArrayList2);
        return;
        ArrayList localArrayList3 = new ArrayList();
        i = 0;
        if (i >= localArrayList1.size())
          continue;
        CachedMessageIntent localCachedMessageIntent2 = (CachedMessageIntent)localArrayList1.get(i);
        if (localCachedMessageIntent2.equals(localCachedMessageIntent1))
        {
          TLog.i("XGService", ">> equal msgId:" + localCachedMessageIntent1.msgId);
          localArrayList3.add(localCachedMessageIntent2);
          break label258;
          localArrayList1.removeAll(localArrayList3);
          localArrayList2 = localArrayList1;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      label258: i++;
    }
  }

  public void a(Context paramContext, String paramString, MessageId paramMessageId)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if (paramContext != null);
    while (true)
    {
      ArrayList localArrayList1;
      try
      {
        if ((c.a(paramString)) || (paramMessageId == null))
          continue;
        TLog.v("XGService", "@@ addMsgId(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramMessageId + ")");
        localArrayList1 = a(paramContext, paramString);
        if (localArrayList1 != null)
          break label177;
        localArrayList2 = new ArrayList();
        localArrayList2.add(paramMessageId);
        b(paramContext, paramString, localArrayList2);
        return;
        if (i < localArrayList1.size())
        {
          if (((MessageId)localArrayList1.get(i)).id != paramMessageId.id)
            break label183;
          localArrayList1.remove(i);
          localArrayList2 = localArrayList1;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      ArrayList localArrayList2 = localArrayList1;
      continue;
      label177: int i = 0;
      continue;
      label183: i++;
    }
  }

  public void a(Context paramContext, String paramString, PushClickEntity paramPushClickEntity)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if (paramContext != null);
    while (true)
    {
      int i;
      try
      {
        if ((c.a(paramString)) || (paramPushClickEntity == null))
          continue;
        TLog.v("XGService", "@@ addClickedMsgId(" + paramContext.getPackageName() + "," + paramPushClickEntity + ")");
        ArrayList localArrayList1 = c(paramContext, paramString);
        ArrayList localArrayList2 = new ArrayList();
        i = 0;
        if (i >= localArrayList1.size())
          continue;
        PushClickEntity localPushClickEntity = (PushClickEntity)localArrayList1.get(i);
        if (localPushClickEntity.msgId == paramPushClickEntity.msgId)
        {
          TLog.i("XGService", ">> equal msgId:" + paramPushClickEntity.msgId);
          localArrayList2.add(localPushClickEntity);
          break label194;
          localArrayList1.removeAll(localArrayList2);
          localArrayList1.add(paramPushClickEntity);
          a(paramContext, paramString, localArrayList1);
          return;
        }
      }
      finally
      {
        monitorexit;
      }
      label194: i++;
    }
  }

  public void a(Context paramContext, String paramString, ArrayList paramArrayList)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramArrayList != null));
    try
    {
      TLog.v("XGService", "@@ updateClickedMsgId(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramArrayList + ")");
      a(paramContext, paramString, ".tpns.msg.id.clicked", paramArrayList);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(Context paramContext, ArrayList paramArrayList)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramArrayList != null));
    while (true)
    {
      ArrayList localArrayList2;
      try
      {
        if (paramArrayList.size() <= 0)
          continue;
        TLog.v("XGService", "@@ deleteClickedMsgIdBatch(" + paramContext.getPackageName() + "," + paramArrayList + ")");
        HashMap localHashMap;
        try
        {
          ArrayList localArrayList1 = c(paramContext);
          if ((localArrayList1 == null) || (localArrayList1.size() <= 0))
            continue;
          localHashMap = new HashMap();
          Iterator localIterator1 = localArrayList1.iterator();
          if (!localIterator1.hasNext())
            continue;
          PushClickEntity localPushClickEntity = (PushClickEntity)localIterator1.next();
          localArrayList2 = (ArrayList)localHashMap.get("" + localPushClickEntity.msgId);
          if (localArrayList2 != null)
            break label350;
          ArrayList localArrayList3 = new ArrayList();
          localHashMap.put(localPushClickEntity.pkgName, localArrayList3);
          localArrayList4 = localArrayList3;
          break label354;
          if (i >= paramArrayList.size())
            break label344;
          TpnsClickClientReport localTpnsClickClientReport = (TpnsClickClientReport)paramArrayList.get(i);
          if (localPushClickEntity.msgId != localTpnsClickClientReport.msgId)
            continue;
          paramArrayList.remove(i);
          j = 0;
          if (j == 0)
            continue;
          localArrayList4.add(localPushClickEntity);
          localHashMap.put(localPushClickEntity.pkgName, localArrayList4);
          continue;
        }
        catch (Exception localException)
        {
          TLog.e("XGService", "+++ clear msg id exception", localException);
        }
        return;
        i++;
        continue;
        Iterator localIterator2 = localHashMap.keySet().iterator();
        if (!localIterator2.hasNext())
          continue;
        String str = (String)localIterator2.next();
        a(paramContext, str, (ArrayList)localHashMap.get(str));
        continue;
        continue;
      }
      finally
      {
        monitorexit;
      }
      label344: int j = 1;
      continue;
      label350: ArrayList localArrayList4 = localArrayList2;
      label354: int i = 0;
    }
  }

  public void a(Context paramContext, List paramList, ArrayList paramArrayList)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramList != null));
    while (true)
    {
      HashMap localHashMap;
      try
      {
        if (paramList.size() <= 0)
          continue;
        TLog.v("XGService", "@@ deleteCachedMsgIntent(current pkg:" + paramContext.getPackageName() + "," + paramList + ")");
        ArrayList localArrayList1;
        int i;
        try
        {
          localArrayList1 = new ArrayList();
          if ((paramArrayList == null) || (paramArrayList.size() <= 0))
            continue;
          TLog.i("XGService", ">> before cached msg list size:" + paramArrayList.size());
          localHashMap = new HashMap();
          i = 0;
          if (i >= paramArrayList.size())
            continue;
          CachedMessageIntent localCachedMessageIntent2 = (CachedMessageIntent)paramArrayList.get(i);
          Iterator localIterator3 = paramList.iterator();
          if (!localIterator3.hasNext())
            continue;
          CachedMessageIntent localCachedMessageIntent3 = (CachedMessageIntent)localIterator3.next();
          if (!localCachedMessageIntent2.equals(localCachedMessageIntent3))
            continue;
          localArrayList1.add(localCachedMessageIntent2);
          ArrayList localArrayList3 = (ArrayList)localHashMap.get(localCachedMessageIntent3.pkgName);
          if (localArrayList3 != null)
            continue;
          localArrayList3 = new ArrayList();
          localHashMap.put(localCachedMessageIntent3.pkgName, localArrayList3);
          continue;
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
        }
        return;
        i++;
        continue;
        paramArrayList.removeAll(localArrayList1);
        TLog.i("XGService", ">> end cached msg list size:" + paramArrayList.size());
        Iterator localIterator1 = paramArrayList.iterator();
        if (localIterator1.hasNext())
        {
          CachedMessageIntent localCachedMessageIntent1 = (CachedMessageIntent)localIterator1.next();
          ArrayList localArrayList2 = (ArrayList)localHashMap.get(localCachedMessageIntent1.pkgName);
          if (localArrayList2 != null)
            continue;
          localArrayList2 = new ArrayList();
          localArrayList2.add(localCachedMessageIntent1);
          localHashMap.put(localCachedMessageIntent1.pkgName, localArrayList2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Iterator localIterator2 = localHashMap.keySet().iterator();
      while (localIterator2.hasNext())
      {
        String str = (String)localIterator2.next();
        c(paramContext, str, (ArrayList)localHashMap.get(str));
      }
    }
  }

  public void a(ArrayList paramArrayList, long paramLong, com.tencent.android.tpush.service.channel.a parama)
  {
    TLog.v("XGService", "messageDistribute(" + paramArrayList + "," + paramLong + "," + parama + ")");
    if ((i.e() != null) && (paramArrayList != null) && (paramArrayList.size() > 0))
    {
      Iterator localIterator = paramArrayList.iterator();
      while (localIterator.hasNext())
      {
        TpnsPushMsg localTpnsPushMsg = (TpnsPushMsg)localIterator.next();
        if (c.a(localTpnsPushMsg.appPkgName))
        {
          TLog.e("XGService", ">> messageDistribute, msg.appPkgName is null!");
          continue;
        }
        a(localTpnsPushMsg, paramLong, parama);
      }
    }
  }

  public boolean a(Context paramContext, String paramString, long paramLong)
  {
    if ((paramContext != null) && (!c.a(paramString)) && (paramLong > 0L))
    {
      TLog.v("XGService", "@@ isMsgAcked(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramLong + ")");
      ArrayList localArrayList = a(paramContext, paramString);
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          MessageId localMessageId = (MessageId)localIterator.next();
          if (localMessageId.id == paramLong)
            return localMessageId.a();
        }
      }
    }
    return false;
  }

  public ArrayList b(Context paramContext)
  {
    Object localObject = null;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getAckedMsgIdList(" + paramContext.getPackageName() + ")");
      List localList = c.a(paramContext);
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          ArrayList localArrayList1 = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            ArrayList localArrayList2 = d(paramContext, ((ResolveInfo)localIterator.next()).activityInfo.packageName);
            if ((localArrayList2 == null) || (localArrayList2.size() <= 0))
              continue;
            localArrayList1.addAll(localArrayList2);
          }
          localObject = localArrayList1;
        }
      }
    }
    return localObject;
  }

  public ArrayList b(Context paramContext, String paramString)
  {
    Object localObject;
    if ((paramContext != null) && (!c.a(paramString)))
    {
      TLog.v("XGService", "@@ getCachedMsgIntentListByPkgName(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + ")");
      localObject = a(paramContext, paramString, ".tpns.msg.id.cached");
      if (localObject == null);
    }
    for (ArrayList localArrayList = (ArrayList)localObject; ; localArrayList = null)
    {
      TLog.i("XGService", ">> cachedMsgIdList : " + localArrayList);
      if (localArrayList == null)
        localArrayList = new ArrayList();
      return localArrayList;
    }
  }

  public void b(Context paramContext, Intent paramIntent)
  {
    if ((paramContext != null) && (paramIntent != null))
    {
      TLog.v("XGService", "@@ msgClick(" + paramContext.getPackageName() + "," + paramIntent + ")");
      String str = paramIntent.getStringExtra("packName");
      a(paramContext, str, new PushClickEntity(paramIntent.getLongExtra("msgId", -1L), paramIntent.getLongExtra("accId", -1L), paramIntent.getLongExtra("busiMsgId", -1L), paramIntent.getLongExtra("timestamps", -1L), str, 1L, paramIntent.getLongExtra("clickTime", System.currentTimeMillis() / 1000L), paramIntent.getIntExtra("action", 0)));
      c(paramContext, paramIntent);
    }
  }

  public void b(Context paramContext, String paramString, ArrayList paramArrayList)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramArrayList != null));
    try
    {
      TLog.v("XGService", "@@ updateMsgId(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramArrayList + ")");
      a(paramContext, paramString, ".tpns.msg.id", paramArrayList);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b(Context paramContext, List paramList)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramList != null));
    while (true)
    {
      ArrayList localArrayList2;
      try
      {
        if (paramList.size() <= 0)
          continue;
        TLog.v("XGService", "@@ deleteMsgIdBatch(" + paramContext.getPackageName() + "," + paramList + ")");
        HashMap localHashMap;
        try
        {
          ArrayList localArrayList1 = b(paramContext);
          if ((localArrayList1 == null) || (localArrayList1.size() <= 0))
            continue;
          localHashMap = new HashMap();
          Iterator localIterator1 = localArrayList1.iterator();
          if (!localIterator1.hasNext())
            continue;
          MessageId localMessageId = (MessageId)localIterator1.next();
          localArrayList2 = (ArrayList)localHashMap.get(localMessageId.pkgName);
          if (localArrayList2 != null)
            break label359;
          ArrayList localArrayList3 = new ArrayList();
          localHashMap.put(localMessageId.pkgName, localArrayList3);
          localArrayList4 = localArrayList3;
          break label363;
          if (i >= paramList.size())
            break label353;
          TpnsPushClientReport localTpnsPushClientReport = (TpnsPushClientReport)paramList.get(i);
          if (localMessageId.id != localTpnsPushClientReport.msgId)
            continue;
          TLog.i("XGService", ">> deleteMsgId" + localMessageId.id);
          j = 0;
          if (j == 0)
            continue;
          localArrayList4.add(localMessageId);
          localHashMap.put(localMessageId.pkgName, localArrayList4);
          continue;
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
        }
        return;
        i++;
        continue;
        Iterator localIterator2 = localHashMap.keySet().iterator();
        if (!localIterator2.hasNext())
          continue;
        String str = (String)localIterator2.next();
        b(paramContext, str, (ArrayList)localHashMap.get(str));
        continue;
        continue;
      }
      finally
      {
        monitorexit;
      }
      label353: int j = 1;
      continue;
      label359: ArrayList localArrayList4 = localArrayList2;
      label363: int i = 0;
    }
  }

  public boolean b(Context paramContext, String paramString, long paramLong)
  {
    if ((paramContext != null) && (!c.a(paramString)))
    {
      TLog.v("XGService", "@@ isMsgCached(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramLong + ")");
      ArrayList localArrayList = b(paramContext, paramString);
      if ((localArrayList != null) && (localArrayList.size() > 0))
      {
        Iterator localIterator = localArrayList.iterator();
        while (localIterator.hasNext())
        {
          CachedMessageIntent localCachedMessageIntent = (CachedMessageIntent)localIterator.next();
          if ((localCachedMessageIntent.msgId != paramLong) || (!paramString.equals(localCachedMessageIntent.pkgName)))
            continue;
          TLog.i("XGService", ">> cached msgId:" + paramLong + " pkgName:" + paramString + ")");
          return true;
        }
      }
    }
    return false;
  }

  public ArrayList c(Context paramContext)
  {
    Object localObject = null;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getClickedMsgIdList(" + paramContext.getPackageName() + ")");
      List localList = c.a(paramContext);
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          ArrayList localArrayList1 = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            ArrayList localArrayList2 = c(paramContext, ((ResolveInfo)localIterator.next()).activityInfo.packageName);
            if ((localArrayList2 == null) || (localArrayList2.size() <= 0))
              continue;
            localArrayList1.addAll(localArrayList2);
          }
          localObject = localArrayList1;
        }
      }
    }
    return localObject;
  }

  public void c(Context paramContext, Intent paramIntent)
  {
    TLog.v("XGService", "@@ requestClick(" + paramContext.getPackageName() + "," + paramIntent + ")");
    ArrayList localArrayList = a(paramContext);
    l.a().b(localArrayList, new d(this, localArrayList, paramContext, paramIntent));
  }

  public void c(Context paramContext, String paramString, ArrayList paramArrayList)
  {
    byte[] arrayOfByte = b;
    monitorenter;
    if ((paramContext != null) && (paramArrayList != null));
    try
    {
      TLog.v("XGService", "@@ updateCachedMsgIntent(current pkg:" + paramContext.getPackageName() + ",remote pkg:" + paramString + "," + paramArrayList + ")");
      a(paramContext, paramString, ".tpns.msg.id.cached", paramArrayList);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void d(Context paramContext)
  {
    ArrayList localArrayList1;
    ResolveInfo localResolveInfo;
    ArrayList localArrayList2;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ clearLocalCachedMsgIntentListByPkgName" + paramContext.getPackageName());
      List localList = c.a(paramContext);
      if ((localList != null) && (localList.size() > 0))
      {
        TLog.i("XGService", ">> getCachedMsgIntentList apps : " + localList.size());
        localArrayList1 = new ArrayList();
        Iterator localIterator = localList.iterator();
        do
        {
          if (!localIterator.hasNext())
            break;
          localResolveInfo = (ResolveInfo)localIterator.next();
          localArrayList1.clear();
          TLog.i("XGService", ">> getCachedMsgIntentList app : " + localResolveInfo.activityInfo.packageName);
          localArrayList2 = b(paramContext, localResolveInfo.activityInfo.packageName);
        }
        while ((localArrayList2 == null) || (localArrayList2.size() <= 0));
      }
    }
    for (int i = 0; ; i++)
      if (i < localArrayList2.size())
      {
        CachedMessageIntent localCachedMessageIntent = (CachedMessageIntent)localArrayList2.get(i);
        try
        {
          String str = Rijndael.decrypt(localCachedMessageIntent.intent);
          if (c.a(str))
            continue;
          Intent localIntent = Intent.parseUri(str, 1);
          if (localIntent.getLongExtra("msgId", 0L) <= 0L)
            continue;
          TLog.i("XGService", ">> msgId" + localIntent.getLongExtra("msgId", 0L));
          localArrayList1.add(localCachedMessageIntent);
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
        }
      }
      else
      {
        c(paramContext, localResolveInfo.activityInfo.packageName, localArrayList1);
        break;
        return;
      }
  }

  public ArrayList e(Context paramContext)
  {
    Object localObject = null;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ getCachedMsgIntentList" + paramContext.getPackageName());
      List localList = c.a(paramContext);
      localObject = null;
      if (localList != null)
      {
        int i = localList.size();
        localObject = null;
        if (i > 0)
        {
          TLog.i("XGService", ">> getCachedMsgIntentList apps : " + localList.size());
          ArrayList localArrayList1 = new ArrayList();
          Iterator localIterator = localList.iterator();
          while (localIterator.hasNext())
          {
            ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
            TLog.i("XGService", ">> getCachedMsgIntentList app : " + localResolveInfo.activityInfo.packageName);
            ArrayList localArrayList2 = b(paramContext, localResolveInfo.activityInfo.packageName);
            if ((localArrayList2 == null) || (localArrayList2.size() <= 0))
              continue;
            localArrayList1.addAll(localArrayList2);
          }
          localObject = localArrayList1;
        }
      }
    }
    return localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.b.a
 * JD-Core Version:    0.6.0
 */