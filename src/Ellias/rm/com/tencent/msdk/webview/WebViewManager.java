package com.tencent.msdk.webview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.tencent.msdk.notice.NoticeManager;
import com.tencent.msdk.tools.Logger;

public class WebViewManager
{
  private static WebViewManager instance = null;
  private Activity act = null;
  private Boolean isInit = Boolean.valueOf(false);
  public String qqAppId = null;
  private String url = "";

  public static WebViewManager getInstance()
  {
    if (instance == null)
      monitorenter;
    try
    {
      if (instance == null)
        instance = new WebViewManager();
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void loadUrlInWnd(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("url", paramString1);
    localIntent.putExtra("sharedUrl", paramString2);
    localIntent.setClass(this.act, WebViewActivity.class);
    try
    {
      this.act.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Logger.w("cann't start WVMActivity，Are you sure to register activity?");
      NoticeManager.getInstance().onResume();
    }
  }

  public String getCurrentUrl()
  {
    return this.url;
  }

  public void init(Activity paramActivity, String paramString)
  {
    if (!this.isInit.booleanValue())
    {
      this.isInit = Boolean.valueOf(true);
      this.act = paramActivity;
      this.qqAppId = paramString;
      WebViewResID.init(paramActivity);
    }
  }

  // ERROR //
  public void openUrl(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 102	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   4: new 104	com/tencent/msdk/api/LoginRet
    //   7: dup
    //   8: invokespecial 105	com/tencent/msdk/api/LoginRet:<init>	()V
    //   11: astore_2
    //   12: aload_2
    //   13: invokestatic 111	com/tencent/msdk/api/WGPlatform:WGGetLoginRecord	(Lcom/tencent/msdk/api/LoginRet;)I
    //   16: pop
    //   17: aload_1
    //   18: ifnonnull +9 -> 27
    //   21: ldc 113
    //   23: invokestatic 73	com/tencent/msdk/tools/Logger:w	(Ljava/lang/String;)V
    //   26: return
    //   27: aload_1
    //   28: ldc 115
    //   30: invokevirtual 121	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   33: ifne +55 -> 88
    //   36: aload_1
    //   37: ldc 123
    //   39: invokevirtual 121	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   42: ifne +46 -> 88
    //   45: aload_1
    //   46: ldc 125
    //   48: invokevirtual 121	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   51: ifne +37 -> 88
    //   54: aload_1
    //   55: ldc 127
    //   57: invokevirtual 121	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   60: ifne +28 -> 88
    //   63: ldc 129
    //   65: invokestatic 73	com/tencent/msdk/tools/Logger:w	(Ljava/lang/String;)V
    //   68: new 131	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   75: ldc 115
    //   77: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_1
    //   81: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: astore_1
    //   88: aload_1
    //   89: astore 4
    //   91: new 141	java/net/URL
    //   94: dup
    //   95: aload_1
    //   96: invokespecial 143	java/net/URL:<init>	(Ljava/lang/String;)V
    //   99: invokevirtual 146	java/net/URL:getQuery	()Ljava/lang/String;
    //   102: invokestatic 151	com/tencent/msdk/tools/T:ckIsEmpty	(Ljava/lang/String;)Z
    //   105: ifeq +516 -> 621
    //   108: new 131	java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   115: aload_1
    //   116: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: ldc 153
    //   121: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: astore 32
    //   129: aload 32
    //   131: astore_1
    //   132: new 131	java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   139: aload_1
    //   140: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: ldc 155
    //   145: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   151: astore 7
    //   153: new 131	java/lang/StringBuilder
    //   156: dup
    //   157: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   160: aload 7
    //   162: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: ldc 157
    //   167: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokestatic 160	com/tencent/msdk/api/WGPlatform:WGGetVersion	()Ljava/lang/String;
    //   173: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   176: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   179: astore 8
    //   181: new 131	java/lang/StringBuilder
    //   184: dup
    //   185: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   188: ldc 22
    //   190: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokestatic 166	java/lang/System:currentTimeMillis	()J
    //   196: invokevirtual 169	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   199: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   202: astore 9
    //   204: new 131	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   211: aload 8
    //   213: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: ldc 171
    //   218: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: aload 9
    //   223: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: astore 10
    //   231: ldc 22
    //   233: astore 11
    //   235: aload_2
    //   236: getfield 175	com/tencent/msdk/api/LoginRet:platform	I
    //   239: getstatic 180	com/tencent/msdk/WeGame:QQPLATID	I
    //   242: if_icmpne +429 -> 671
    //   245: invokestatic 183	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   248: getfield 186	com/tencent/msdk/WeGame:qq_appid	Ljava/lang/String;
    //   251: astore 24
    //   253: aload_2
    //   254: getfield 189	com/tencent/msdk/api/LoginRet:open_id	Ljava/lang/String;
    //   257: astore 25
    //   259: new 131	java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   266: aload 10
    //   268: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: ldc 191
    //   273: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: aload 24
    //   278: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   281: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   284: astore 26
    //   286: new 131	java/lang/StringBuilder
    //   289: dup
    //   290: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   293: aload 26
    //   295: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: ldc 193
    //   300: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: new 131	java/lang/StringBuilder
    //   306: dup
    //   307: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   310: ldc 22
    //   312: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: aload 9
    //   317: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokestatic 183	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   326: getfield 196	com/tencent/msdk/WeGame:qqAppKey	Ljava/lang/String;
    //   329: invokestatic 202	com/tencent/msdk/tools/MsdkSig:make	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   332: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   335: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   338: astore 27
    //   340: new 131	java/lang/StringBuilder
    //   343: dup
    //   344: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   347: aload 27
    //   349: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: ldc 204
    //   354: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   357: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: astore 10
    //   362: new 131	java/lang/StringBuilder
    //   365: dup
    //   366: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   369: aload 11
    //   371: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: ldc 206
    //   376: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   379: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   382: astore 28
    //   384: new 131	java/lang/StringBuilder
    //   387: dup
    //   388: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   391: aload 28
    //   393: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: ldc 191
    //   398: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: aload 24
    //   403: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   406: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   409: astore 29
    //   411: new 131	java/lang/StringBuilder
    //   414: dup
    //   415: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   418: aload 29
    //   420: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: ldc 208
    //   425: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   428: aload 25
    //   430: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: astore 30
    //   438: new 131	java/lang/StringBuilder
    //   441: dup
    //   442: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   445: aload 30
    //   447: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: ldc 210
    //   452: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: aload_2
    //   456: iconst_1
    //   457: invokevirtual 214	com/tencent/msdk/api/LoginRet:getTokenByType	(I)Ljava/lang/String;
    //   460: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   466: astore 31
    //   468: new 131	java/lang/StringBuilder
    //   471: dup
    //   472: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   475: aload 31
    //   477: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: ldc 216
    //   482: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   488: astore 11
    //   490: new 218	com/tencent/msdk/tea/TEACoding
    //   493: dup
    //   494: ldc 220
    //   496: invokevirtual 224	java/lang/String:getBytes	()[B
    //   499: invokespecial 227	com/tencent/msdk/tea/TEACoding:<init>	([B)V
    //   502: astore 20
    //   504: aload 20
    //   506: aload 11
    //   508: ldc 229
    //   510: invokevirtual 232	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   513: invokevirtual 236	com/tencent/msdk/tea/TEACoding:encode	([B)[B
    //   516: invokestatic 242	com/tencent/msdk/tools/HexUtil:bytes2HexStr	([B)Ljava/lang/String;
    //   519: astore 22
    //   521: new 131	java/lang/StringBuilder
    //   524: dup
    //   525: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   528: ldc 244
    //   530: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: aload 22
    //   535: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   541: invokestatic 102	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   544: new 131	java/lang/StringBuilder
    //   547: dup
    //   548: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   551: aload 10
    //   553: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   556: ldc 246
    //   558: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   561: aload 22
    //   563: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   569: astore 23
    //   571: aload 23
    //   573: astore 10
    //   575: aload_0
    //   576: aload 10
    //   578: putfield 24	com/tencent/msdk/webview/WebViewManager:url	Ljava/lang/String;
    //   581: new 131	java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   588: ldc 248
    //   590: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   593: aload 10
    //   595: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   601: invokestatic 102	com/tencent/msdk/tools/Logger:d	(Ljava/lang/String;)V
    //   604: invokestatic 78	com/tencent/msdk/notice/NoticeManager:getInstance	()Lcom/tencent/msdk/notice/NoticeManager;
    //   607: invokevirtual 251	com/tencent/msdk/notice/NoticeManager:onPause	()V
    //   610: aload_0
    //   611: aload_0
    //   612: getfield 24	com/tencent/msdk/webview/WebViewManager:url	Ljava/lang/String;
    //   615: aload 4
    //   617: invokespecial 253	com/tencent/msdk/webview/WebViewManager:loadUrlInWnd	(Ljava/lang/String;Ljava/lang/String;)V
    //   620: return
    //   621: aload_1
    //   622: ldc 255
    //   624: invokevirtual 258	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   627: ifne -495 -> 132
    //   630: new 131	java/lang/StringBuilder
    //   633: dup
    //   634: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   637: aload_1
    //   638: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   641: ldc 255
    //   643: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   646: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   649: astore 6
    //   651: aload 6
    //   653: astore_1
    //   654: goto -522 -> 132
    //   657: astore 5
    //   659: ldc_w 260
    //   662: invokestatic 73	com/tencent/msdk/tools/Logger:w	(Ljava/lang/String;)V
    //   665: aload 5
    //   667: invokevirtual 263	java/net/MalformedURLException:printStackTrace	()V
    //   670: return
    //   671: aload_2
    //   672: getfield 175	com/tencent/msdk/api/LoginRet:platform	I
    //   675: getstatic 266	com/tencent/msdk/WeGame:WXPLATID	I
    //   678: if_icmpne -188 -> 490
    //   681: invokestatic 183	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   684: getfield 269	com/tencent/msdk/WeGame:wx_appid	Ljava/lang/String;
    //   687: astore 12
    //   689: aload_2
    //   690: getfield 189	com/tencent/msdk/api/LoginRet:open_id	Ljava/lang/String;
    //   693: astore 13
    //   695: new 131	java/lang/StringBuilder
    //   698: dup
    //   699: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   702: aload 10
    //   704: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   707: ldc 191
    //   709: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   712: aload 12
    //   714: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   717: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   720: astore 14
    //   722: new 131	java/lang/StringBuilder
    //   725: dup
    //   726: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   729: aload 14
    //   731: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   734: ldc 193
    //   736: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   739: new 131	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   746: ldc 22
    //   748: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: aload 9
    //   753: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   756: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   759: invokestatic 183	com/tencent/msdk/WeGame:getInstance	()Lcom/tencent/msdk/WeGame;
    //   762: getfield 272	com/tencent/msdk/WeGame:wxAppKey	Ljava/lang/String;
    //   765: invokestatic 202	com/tencent/msdk/tools/MsdkSig:make	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   768: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   771: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   774: astore 15
    //   776: new 131	java/lang/StringBuilder
    //   779: dup
    //   780: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   783: aload 15
    //   785: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: ldc 204
    //   790: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   793: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   796: astore 10
    //   798: new 131	java/lang/StringBuilder
    //   801: dup
    //   802: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   805: aload 11
    //   807: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   810: ldc_w 274
    //   813: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   819: astore 16
    //   821: new 131	java/lang/StringBuilder
    //   824: dup
    //   825: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   828: aload 16
    //   830: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   833: ldc 191
    //   835: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   838: aload 12
    //   840: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   843: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   846: astore 17
    //   848: new 131	java/lang/StringBuilder
    //   851: dup
    //   852: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   855: aload 17
    //   857: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   860: ldc 208
    //   862: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   865: aload 13
    //   867: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   870: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   873: astore 18
    //   875: new 131	java/lang/StringBuilder
    //   878: dup
    //   879: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   882: aload 18
    //   884: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   887: ldc 210
    //   889: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   892: aload_2
    //   893: iconst_3
    //   894: invokevirtual 214	com/tencent/msdk/api/LoginRet:getTokenByType	(I)Ljava/lang/String;
    //   897: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   900: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   903: astore 19
    //   905: new 131	java/lang/StringBuilder
    //   908: dup
    //   909: invokespecial 132	java/lang/StringBuilder:<init>	()V
    //   912: aload 19
    //   914: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   917: ldc 216
    //   919: invokevirtual 136	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   922: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   925: astore 11
    //   927: goto -437 -> 490
    //   930: astore 21
    //   932: aload 21
    //   934: invokevirtual 275	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   937: goto -362 -> 575
    //
    // Exception table:
    //   from	to	target	type
    //   91	129	657	java/net/MalformedURLException
    //   621	651	657	java/net/MalformedURLException
    //   504	571	930	java/io/UnsupportedEncodingException
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.webview.WebViewManager
 * JD-Core Version:    0.6.0
 */