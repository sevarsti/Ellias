package com.pay.http;

import com.pay.common.tool.APLog;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStore.TrustedCertificateEntry;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

final class d
  implements X509TrustManager
{
  private X509TrustManager a;

  d()
  {
    while (true)
    {
      int i;
      try
      {
        CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
        ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream("-----BEGIN CERTIFICATE-----\nMIIEDzCCAvegAwIBAgIJAJhHK18zh2o5MA0GCSqGSIb3DQEBBQUAMGIxCzAJBgNV\nBAYTAkNOMRIwEAYDVQQIEwlHdWFuZ2RvbmcxETAPBgNVBAcTCFNoZW56aGVuMRUw\nEwYDVQQKEwxDUGF5IFJvb3QgQ0ExFTATBgNVBAsTDENQYXkgUm9vdCBDQTAeFw0x\nMzEwMzEwNzE1NDVaFw0yMzEwMjkwNzE1NDVaMGIxCzAJBgNVBAYTAkNOMRIwEAYD\nVQQIEwlHdWFuZ2RvbmcxETAPBgNVBAcTCFNoZW56aGVuMRUwEwYDVQQKEwxDUGF5\nIFJvb3QgQ0ExFTATBgNVBAsTDENQYXkgUm9vdCBDQTCCASIwDQYJKoZIhvcNAQEB\nBQADggEPADCCAQoCggEBALKfVOTFtDzHXOl78oi+R1MvJ80GFN/i71zbxpZnLEdz\ny4KIyotmnmt1av8ONC17bYrmHCimwyiMTonme5gBNm83XWPxByAccQbqeBowx+7a\nXUojJQwdykDE1AafiZWdtmI2vq9HGXtmZJWatxda/G3vo1lUpqQeB1juvNVb1flq\n2IZFxGihmV8EDhtTt3gGlaLwmBsI40Et+vJX/XXrd158qF6wmhzztI+obmYEqjJT\nNxor/fOMWXSIZOtvLdAA1fbrH1cMon62v2v2Sx/69KCrV9CgmK3dYJiUaAiv3SYP\n4CB+r8odiulK42QPnVxFbPR0ID8al+fewDomREqt6RkCAwEAAaOBxzCBxDAdBgNV\nHQ4EFgQUFb1Jpcd4xDZYyNLV2D3iV44X9SMwgZQGA1UdIwSBjDCBiYAUFb1Jpcd4\nxDZYyNLV2D3iV44X9SOhZqRkMGIxCzAJBgNVBAYTAkNOMRIwEAYDVQQIEwlHdWFu\nZ2RvbmcxETAPBgNVBAcTCFNoZW56aGVuMRUwEwYDVQQKEwxDUGF5IFJvb3QgQ0Ex\nFTATBgNVBAsTDENQYXkgUm9vdCBDQYIJAJhHK18zh2o5MAwGA1UdEwQFMAMBAf8w\nDQYJKoZIhvcNAQEFBQADggEBAC+WGUZTncBGxgcEfWt3MFOuL1ht3hF03WFx+6wt\n9TyEySEPRrH/g+jNUL9wsdVFztzHcY3iFmtVHqAioGXwz+Kjd7E0iCV1ijNusMv6\nN2MQsZ70JC2mbMewcwK6c2eCGABMD6hV8crTVGe7OIvz4M7mk1hevly0SlxS7Lfc\nF3i2504uOQp9bVpzQMZ++6m7s7KTysostxMsQbuPLN2xTSx+SEzVZQN/oqemSh2F\nJpODYHOnU5wDf5b7UFcZ7jL4FSJWrIfcxlHVZse/kX7e38a9odrFHxmaNhsBWYn0\nLqZpfR9qWZSjihLXz0lPTnB4ZJNVAsP0DfI/veTyoSBkX2g=\n-----END CERTIFICATE-----\n".getBytes());
        X509Certificate localX509Certificate = (X509Certificate)localCertificateFactory.generateCertificate(localByteArrayInputStream);
        localByteArrayInputStream.close();
        KeyStore.TrustedCertificateEntry localTrustedCertificateEntry = new KeyStore.TrustedCertificateEntry(localX509Certificate);
        KeyStore localKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        localKeyStore.load(null, null);
        localKeyStore.setEntry("ca_root", localTrustedCertificateEntry, null);
        TrustManagerFactory localTrustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        localTrustManagerFactory.init(localKeyStore);
        TrustManager[] arrayOfTrustManager = localTrustManagerFactory.getTrustManagers();
        i = 0;
        if (i >= arrayOfTrustManager.length)
          return;
        if ((arrayOfTrustManager[i] instanceof X509TrustManager))
        {
          this.a = ((X509TrustManager)arrayOfTrustManager[i]);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      i++;
    }
  }

  public final void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
  {
  }

  public final void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
  {
    APLog.i("APBaseHttpReq", "checkServerTrusted");
    this.a.checkServerTrusted(paramArrayOfX509Certificate, paramString);
  }

  public final X509Certificate[] getAcceptedIssuers()
  {
    return this.a.getAcceptedIssuers();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.d
 * JD-Core Version:    0.6.0
 */