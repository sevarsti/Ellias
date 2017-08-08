package com.tencent.apkupdate.logic.protocol;

import android.util.Log;
import com.qq.taf.jce.JceOutputStream;
import com.qq.taf.jce.JceStruct;
import com.tencent.apkupdate.c.b;
import com.tencent.apkupdate.logic.protocol.jce.JceCmd;
import com.tencent.apkupdate.logic.protocol.jce.Net;
import com.tencent.apkupdate.logic.protocol.jce.ReqHead;
import com.tencent.apkupdate.logic.protocol.jce.Request;
import com.tencent.apkupdate.logic.protocol.jce.Response;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.ByteArrayBuffer;

public abstract class a
  implements Runnable
{
  private static String a = "BaseHttpRequest";
  private JceStruct b = null;
  private boolean c = false;

  protected void a()
  {
  }

  public final void a(JceStruct paramJceStruct)
  {
    this.b = paramJceStruct;
  }

  protected abstract void a(JceStruct paramJceStruct1, JceStruct paramJceStruct2);

  protected abstract void b();

  public void run()
  {
    a();
    if (this.b == null)
      return;
    JceStruct localJceStruct1 = this.b;
    Object localObject2;
    byte[] arrayOfByte2;
    if (localJceStruct1 == null)
    {
      localObject2 = null;
      arrayOfByte2 = null;
      if (localObject2 != null)
        break label469;
    }
    ByteArrayBuffer localByteArrayBuffer;
    while (true)
    {
      HttpPost localHttpPost = new HttpPost("http://masdk.3g.qq.com/");
      localHttpPost.addHeader("User-Agent", "AssistantDownloader");
      localHttpPost.setEntity(new ByteArrayEntity(arrayOfByte2));
      try
      {
        HttpClient localHttpClient = com.tencent.apkupdate.b.a.a();
        Log.i(a, "url:http://masdk.3g.qq.com/");
        HttpResponse localHttpResponse = localHttpClient.execute(localHttpPost);
        if (localHttpResponse == null)
          break label694;
        Log.i(a, "url:http://masdk.3g.qq.com/; httpCode=" + localHttpResponse.getStatusLine().getStatusCode());
        if (localHttpResponse.getStatusLine().getStatusCode() != 200)
          break label694;
        HttpEntity localHttpEntity = localHttpResponse.getEntity();
        if (localHttpEntity == null)
          break label694;
        localByteArrayBuffer = new ByteArrayBuffer((int)localHttpEntity.getContentLength());
        InputStream localInputStream = localHttpEntity.getContent();
        byte[] arrayOfByte5 = new byte[2048];
        while (true)
        {
          int j = localInputStream.read(arrayOfByte5);
          if (j == -1)
            break;
          localByteArrayBuffer.append(arrayOfByte5, 0, j);
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        b();
        return;
      }
      Request localRequest = new Request();
      Object localObject1;
      if (localJceStruct1 == null)
      {
        localObject1 = null;
        localRequest.head = localObject1;
        if (localJceStruct1 != null)
          break label436;
      }
      label436: JceOutputStream localJceOutputStream1;
      for (byte[] arrayOfByte1 = null; ; arrayOfByte1 = localJceOutputStream1.toByteArray())
      {
        localRequest.body = arrayOfByte1;
        localObject2 = localRequest;
        break;
        byte b1 = (byte)b.a().e();
        String str1 = b.a().d();
        b.a();
        Net localNet = new Net(b1, str1, 0, b.c());
        ReqHead localReqHead = new ReqHead();
        localReqHead.requestId = b.i();
        if (localJceStruct1 == null);
        String str2;
        for (int i = -1; ; i = JceCmd.convert(str2.substring(0, -7 + str2.length())).value())
        {
          localReqHead.cmdId = i;
          b.a();
          localReqHead.qua = b.f();
          localReqHead.phoneGuid = b.a().h();
          localReqHead.terminal = b.a().g();
          localReqHead.assistantAPILevel = 0;
          localReqHead.assistantVersionCode = 0;
          localReqHead.net = localNet;
          localObject1 = localReqHead;
          break;
          str2 = localJceStruct1.getClass().getSimpleName();
        }
        localJceOutputStream1 = new JceOutputStream();
        localJceOutputStream1.setServerEncoding("utf-8");
        localJceStruct1.writeTo(localJceOutputStream1);
      }
      label469: localObject2.head.encryptWithPack = 0;
      if (localObject2.body.length > 256)
      {
        localObject2.body = com.tencent.apkupdate.a.a.b(localObject2.body);
        localObject2.head.encryptWithPack = (byte)(0x1 | localObject2.head.encryptWithPack);
      }
      byte[] arrayOfByte3 = localObject2.body;
      byte[] arrayOfByte4 = "ji*9^&43U0X-~./(".getBytes();
      localObject2.body = new com.tencent.apkupdate.c.a().b(arrayOfByte3, 0, arrayOfByte3.length, arrayOfByte4);
      localObject2.head.encryptWithPack = (byte)(0x2 | localObject2.head.encryptWithPack);
      arrayOfByte2 = null;
      if (localObject2 == null)
        continue;
      JceOutputStream localJceOutputStream2 = new JceOutputStream();
      localJceOutputStream2.setServerEncoding("utf-8");
      localObject2.writeTo(localJceOutputStream2);
      arrayOfByte2 = localJceOutputStream2.toByteArray();
    }
    byte[] arrayOfByte6 = localByteArrayBuffer.buffer();
    if ((arrayOfByte6 != null) && (arrayOfByte6.length > 4))
    {
      Response localResponse = com.tencent.apkupdate.a.a.a(arrayOfByte6);
      if ((localResponse != null) && (localResponse.body != null))
      {
        JceStruct localJceStruct2 = com.tencent.apkupdate.a.a.a(this.b, localResponse.body);
        if (localJceStruct2 != null)
        {
          a(this.b, localJceStruct2);
          return;
        }
      }
    }
    else
    {
      b();
    }
    label694: b();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.logic.protocol.a
 * JD-Core Version:    0.6.0
 */