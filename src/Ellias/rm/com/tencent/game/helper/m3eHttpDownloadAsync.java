package com.tencent.game.helper;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class m3eHttpDownloadAsync extends AsyncTask<String, String, String>
{
  protected String doInBackground(String[] paramArrayOfString)
  {
    String str1 = m3eHttpDownload.getInstance().fileName;
    String str2 = m3eHttpDownload.getInstance().fileDir;
    String str3 = m3eHttpDownload.getInstance().fileURL;
    int i = m3eHttpDownload.getInstance().iWidth;
    int j = m3eHttpDownload.getInstance().iHeight;
    int k = m3eHttpDownload.getInstance().misIcon;
    try
    {
      str4 = m3eFileHelper.getInstance().getUserDirectory();
      m3eFileHelper.getInstance().createDir(str4 + str2);
      URI localURI = new URI(str3);
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      if (k > 0)
        localDefaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(3000));
      localHttpResponse = localDefaultHttpClient.execute(new HttpGet(localURI));
      if (localHttpResponse.getStatusLine().getStatusCode() != 200)
      {
        m3eActivity.s_m3eActivity.nativeDownloadError(str1);
        Log.d("m3e", "http conect time out");
      }
      else
      {
        m = Integer.parseInt(localHttpResponse.getHeaders("Content-Length")[0].getValue());
        m3eActivity.s_m3eActivity.nativeDownloadBegin(m);
        Log.d("jacksondown", "jacksonTestDown  nativeDownloadBegin(" + str4 + str2 + "/" + str1 + ")" + "filesize = " + m + ", url=" + str3 + ":" + String.valueOf(i) + ":" + String.valueOf(i));
        int n = str1.lastIndexOf(".png");
        int i1 = str3.lastIndexOf(".png");
        if (m > 0)
        {
          Log.d("jacksonTestDown", "jacksonTestDown urlurlurlurlurlurlurlurlurlurlurlurl" + str3);
          if ((n != -1) && (i1 == -1))
          {
            Log.d("m3e", "xfghhhhhhhhhhtttttttttttttttttttttt" + str3);
            localInputStream2 = localHttpResponse.getEntity().getContent();
            localByteArrayOutputStream2 = new ByteArrayOutputStream();
            while (true)
            {
              int i3 = localInputStream2.read();
              if (i3 == -1)
                break;
              localByteArrayOutputStream2.write(i3);
            }
          }
        }
      }
    }
    catch (Exception localException)
    {
      String str4;
      HttpResponse localHttpResponse;
      int m;
      InputStream localInputStream2;
      ByteArrayOutputStream localByteArrayOutputStream2;
      m3eActivity.s_m3eActivity.nativeDownloadError(str1);
      break label930;
      Log.d("m3e", "byteArray.size()=" + localByteArrayOutputStream2.size() + ", lenghtOfFile=" + m);
      Bitmap localBitmap;
      FileOutputStream localFileOutputStream2;
      if (localByteArrayOutputStream2.size() == m)
      {
        byte[] arrayOfByte2 = localByteArrayOutputStream2.toByteArray();
        localBitmap = BitmapFactory.decodeByteArray(arrayOfByte2, 0, arrayOfByte2.length);
        Log.d("m3e", "xxxxxxxfile" + str4 + str2 + str1);
        localFileOutputStream2 = new FileOutputStream(new File(str4 + str2, str1));
        Log.d("m3e", "xxxxxxxxxxxxxxfile" + str4 + str2 + "xxxxxxxxxxxxxxfile" + str1);
        if ((i == 0) || (j == 0))
          break label708;
        Log.d("m3e", "fasdfdasfdsafdsafdasfasddfhhhhhhhhhhhhhhhhhh");
        Bitmap.createScaledBitmap(localBitmap, i, j, false).compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream2);
      }
      while (true)
      {
        localFileOutputStream2.close();
        Log.d("m3e", "vdffddddddddddddddd");
        localInputStream2.close();
        Log.d("jacksondown", "jacksonTestDown  nativeDownloadFinish  " + str1);
        m3eActivity.s_m3eActivity.nativeDownloadFinish(str1);
        break;
        label708: localBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream2);
        continue;
        Log.d("m3e", "cccccccccccccccccccccccccccccccccc" + str3);
        InputStream localInputStream1 = localHttpResponse.getEntity().getContent();
        ByteArrayOutputStream localByteArrayOutputStream1 = new ByteArrayOutputStream();
        byte[] arrayOfByte1 = new byte[1024];
        while (true)
        {
          int i2 = localInputStream1.read(arrayOfByte1);
          if (i2 <= 0)
            break;
          m3eActivity.s_m3eActivity.nativeDownloadUpdate(i2);
          localByteArrayOutputStream1.write(arrayOfByte1, 0, i2);
        }
        if (localByteArrayOutputStream1.size() != m)
          break;
        FileOutputStream localFileOutputStream1 = new FileOutputStream(new File(str4 + str2, str1));
        localFileOutputStream1.write(localByteArrayOutputStream1.toByteArray());
        localFileOutputStream1.close();
        Log.d("nativeDownloadFinish", "jacksonTestDown  123123123313   " + str1);
        m3eActivity.s_m3eActivity.nativeDownloadFinish(str1);
        break;
        m3eActivity.s_m3eActivity.nativeDownloadError(str1);
        Log.d("m3e", "file size = 0");
      }
    }
    label930: return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.game.helper.m3eHttpDownloadAsync
 * JD-Core Version:    0.6.0
 */