package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.tencent.smtt.export.external.WebViewWizardBase;
import com.tencent.smtt.export.external.libwebp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class SDKEngine
{
  private static final int MSG_CLEAR_DEXOPT_THREAD = 100;
  private static final String MTT_SIG = "3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a";
  private static final String STR_TIMESTAMP = "timestamp";
  public static final String X5QQBROWSER_PKG_NAME = "com.tencent.mtt";
  public static final String X5_CONF = "qbx5.conf";
  public static final String X5_CONF_CORE_VER = "browser_core_version";
  public static final String X5_CORE_FOLDER_NAME = "x5core";
  public static final String X5_FILE_DEX = "webview_dex.jar";
  public static final String X5_FILE_INTERFACES_DEX = "webview_interfaces_dex.jar";
  public static final String X5_PROPERTIES = "x5.prop";
  public static final String X5_SHARE_FOLDER_NAME = "x5_share";
  public static final String X5_SO = "libmttwebcore.so";
  private static int iInitCount = 0;
  private static boolean isCompatibleChecked = false;
  private static int mCoreVersion = 0;
  private static SDKEngine mInstance = null;
  private static final int sMinSupportCoreVersion = 25102;
  private File mBrowserX5CorePath = null;
  private File mBrowserX5SharePath = null;
  private Handler mDexHandler = null;
  private HandlerThread mDexHandlerThread = null;
  private File mSoPath = null;
  private Handler mUIHandler = null;
  private boolean mUsedX5 = false;
  private WebViewWizardBase mWizard = null;
  private File mX5CorePath = null;

  static
  {
    mCoreVersion = 0;
    iInitCount = 0;
    isCompatibleChecked = false;
  }

  private void doDexOptimize(Context paramContext)
  {
    if (this.mDexHandlerThread == null)
    {
      this.mDexHandlerThread = new HandlerThread("X5DexOptimize");
      this.mDexHandlerThread.start();
    }
    if (this.mDexHandler == null)
    {
      this.mDexHandler = new Handler(this.mDexHandlerThread.getLooper());
      this.mDexHandler.post(new Runnable(paramContext)
      {
        public void run()
        {
          WebViewWizardBase localWebViewWizardBase = new WebViewWizardBase();
          localWebViewWizardBase.setWizardMode(true, true);
          Context localContext1 = SDKEngine.this.getPackageContext(this.val$context, "com.tencent.mtt");
          if (localContext1 == null)
            return;
          File localFile1 = localContext1.getDir("x5_share", 0);
          File localFile2 = new File(localFile1, "x5core");
          Context localContext2 = this.val$context;
          String[] arrayOfString = new String[2];
          arrayOfString[0] = new File(localFile1, "webview_interfaces_dex.jar").getAbsolutePath();
          arrayOfString[1] = new File(localFile2, "webview_dex.jar").getAbsolutePath();
          localWebViewWizardBase.setDexLoader(localContext2, arrayOfString, SDKEngine.this.mX5CorePath.getAbsolutePath());
          SDKEngine.this.mUIHandler.removeMessages(100);
          SDKEngine.this.mUIHandler.sendEmptyMessage(100);
        }
      });
    }
  }

  public static SDKEngine getInstance(boolean paramBoolean)
  {
    if ((mInstance == null) && (paramBoolean))
      mInstance = new SDKEngine();
    return mInstance;
  }

  private Context getPackageContext(Context paramContext, String paramString)
  {
    try
    {
      Context localContext = paramContext.createPackageContext(paramString, 2);
      return localContext;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      return null;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static int getQQBrowserCoreVersion()
  {
    return mCoreVersion;
  }

  // ERROR //
  private void initX5CoreVersionIfNeed(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 69	com/tencent/smtt/sdk/SDKEngine:mCoreVersion	I
    //   3: ifeq +4 -> 7
    //   6: return
    //   7: aconst_null
    //   8: astore_2
    //   9: aload_0
    //   10: aload_1
    //   11: ldc 17
    //   13: invokespecial 121	com/tencent/smtt/sdk/SDKEngine:getPackageContext	(Landroid/content/Context;Ljava/lang/String;)Landroid/content/Context;
    //   16: astore 7
    //   18: aload 7
    //   20: ifnonnull +20 -> 40
    //   23: iconst_0
    //   24: ifeq -18 -> 6
    //   27: aconst_null
    //   28: invokevirtual 177	java/io/FileInputStream:close	()V
    //   31: return
    //   32: astore 14
    //   34: aload 14
    //   36: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   39: return
    //   40: new 180	java/io/File
    //   43: dup
    //   44: new 180	java/io/File
    //   47: dup
    //   48: aload 7
    //   50: ldc 38
    //   52: iconst_0
    //   53: invokevirtual 184	android/content/Context:getDir	(Ljava/lang/String;I)Ljava/io/File;
    //   56: ldc 26
    //   58: invokespecial 187	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   61: ldc 20
    //   63: invokespecial 187	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   66: astore 8
    //   68: new 189	java/util/Properties
    //   71: dup
    //   72: invokespecial 190	java/util/Properties:<init>	()V
    //   75: astore 9
    //   77: new 174	java/io/FileInputStream
    //   80: dup
    //   81: aload 8
    //   83: invokespecial 193	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   86: astore 10
    //   88: aload 9
    //   90: aload 10
    //   92: invokevirtual 197	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   95: aload 10
    //   97: invokevirtual 177	java/io/FileInputStream:close	()V
    //   100: aload 9
    //   102: ldc 23
    //   104: invokevirtual 201	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   107: astore 11
    //   109: aload 11
    //   111: ifnonnull +22 -> 133
    //   114: aload 10
    //   116: ifnull -110 -> 6
    //   119: aload 10
    //   121: invokevirtual 177	java/io/FileInputStream:close	()V
    //   124: return
    //   125: astore 13
    //   127: aload 13
    //   129: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   132: return
    //   133: aload 11
    //   135: invokestatic 207	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   138: putstatic 69	com/tencent/smtt/sdk/SDKEngine:mCoreVersion	I
    //   141: aload 10
    //   143: ifnull +81 -> 224
    //   146: aload 10
    //   148: invokevirtual 177	java/io/FileInputStream:close	()V
    //   151: return
    //   152: astore 12
    //   154: aload 12
    //   156: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   159: return
    //   160: astore 5
    //   162: aload 5
    //   164: invokevirtual 167	java/lang/Exception:printStackTrace	()V
    //   167: iconst_0
    //   168: putstatic 69	com/tencent/smtt/sdk/SDKEngine:mCoreVersion	I
    //   171: aload_2
    //   172: ifnull -166 -> 6
    //   175: aload_2
    //   176: invokevirtual 177	java/io/FileInputStream:close	()V
    //   179: return
    //   180: astore 6
    //   182: aload 6
    //   184: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   187: return
    //   188: astore_3
    //   189: aload_2
    //   190: ifnull +7 -> 197
    //   193: aload_2
    //   194: invokevirtual 177	java/io/FileInputStream:close	()V
    //   197: aload_3
    //   198: athrow
    //   199: astore 4
    //   201: aload 4
    //   203: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   206: goto -9 -> 197
    //   209: astore_3
    //   210: aload 10
    //   212: astore_2
    //   213: goto -24 -> 189
    //   216: astore 5
    //   218: aload 10
    //   220: astore_2
    //   221: goto -59 -> 162
    //   224: return
    //
    // Exception table:
    //   from	to	target	type
    //   27	31	32	java/io/IOException
    //   119	124	125	java/io/IOException
    //   146	151	152	java/io/IOException
    //   9	18	160	java/lang/Exception
    //   40	88	160	java/lang/Exception
    //   175	179	180	java/io/IOException
    //   9	18	188	finally
    //   40	88	188	finally
    //   162	171	188	finally
    //   193	197	199	java/io/IOException
    //   88	109	209	finally
    //   133	141	209	finally
    //   88	109	216	java/lang/Exception
    //   133	141	216	java/lang/Exception
  }

  private static boolean isCompatible(int paramInt, String paramString)
  {
    for (int i = 1; ; i = 0)
      try
      {
        if (!isCompatibleChecked)
        {
          if ((paramInt <= 25206) && (paramInt >= 25200) && ((paramString.indexOf("/com.tencent.mm/") > 0) || (paramString.indexOf("/com.qzone/") > 0)))
            continue;
          isCompatibleChecked = true;
        }
        return i;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return false;
      }
      finally
      {
        isCompatibleChecked = true;
      }
  }

  // ERROR //
  private boolean needDexOptimize(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 80	com/tencent/smtt/sdk/SDKEngine:mX5CorePath	Ljava/io/File;
    //   4: invokevirtual 227	java/io/File:listFiles	()[Ljava/io/File;
    //   7: astore_2
    //   8: aload_2
    //   9: ifnull +9 -> 18
    //   12: aload_2
    //   13: arraylength
    //   14: iconst_1
    //   15: if_icmpgt +5 -> 20
    //   18: iconst_1
    //   19: ireturn
    //   20: new 180	java/io/File
    //   23: dup
    //   24: aload_0
    //   25: getfield 80	com/tencent/smtt/sdk/SDKEngine:mX5CorePath	Ljava/io/File;
    //   28: ldc 35
    //   30: invokespecial 187	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   33: astore_3
    //   34: aload_3
    //   35: invokevirtual 231	java/io/File:exists	()Z
    //   38: ifeq -20 -> 18
    //   41: aconst_null
    //   42: astore 4
    //   44: new 174	java/io/FileInputStream
    //   47: dup
    //   48: aload_3
    //   49: invokespecial 193	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   52: astore 5
    //   54: new 189	java/util/Properties
    //   57: dup
    //   58: invokespecial 190	java/util/Properties:<init>	()V
    //   61: astore 6
    //   63: aload 6
    //   65: aload 5
    //   67: invokevirtual 197	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   70: aload 6
    //   72: ldc 14
    //   74: invokevirtual 201	java/util/Properties:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   77: astore 11
    //   79: aload 11
    //   81: ifnull +18 -> 99
    //   84: aload 11
    //   86: invokestatic 237	com/tencent/smtt/sdk/QbSdk:getX5CoreTimestamp	()Ljava/lang/String;
    //   89: invokevirtual 241	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   92: istore 12
    //   94: iload 12
    //   96: ifne +24 -> 120
    //   99: aload 5
    //   101: ifnull -83 -> 18
    //   104: aload 5
    //   106: invokevirtual 177	java/io/FileInputStream:close	()V
    //   109: iconst_1
    //   110: ireturn
    //   111: astore 14
    //   113: aload 14
    //   115: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   118: iconst_1
    //   119: ireturn
    //   120: aload 5
    //   122: ifnull +8 -> 130
    //   125: aload 5
    //   127: invokevirtual 177	java/io/FileInputStream:close	()V
    //   130: iconst_0
    //   131: ireturn
    //   132: astore 13
    //   134: aload 13
    //   136: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   139: goto -9 -> 130
    //   142: astore 7
    //   144: aload 7
    //   146: invokevirtual 167	java/lang/Exception:printStackTrace	()V
    //   149: aload 4
    //   151: ifnull -133 -> 18
    //   154: aload 4
    //   156: invokevirtual 177	java/io/FileInputStream:close	()V
    //   159: iconst_1
    //   160: ireturn
    //   161: astore 10
    //   163: aload 10
    //   165: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   168: iconst_1
    //   169: ireturn
    //   170: astore 8
    //   172: aload 4
    //   174: ifnull +8 -> 182
    //   177: aload 4
    //   179: invokevirtual 177	java/io/FileInputStream:close	()V
    //   182: aload 8
    //   184: athrow
    //   185: astore 9
    //   187: aload 9
    //   189: invokevirtual 178	java/io/IOException:printStackTrace	()V
    //   192: goto -10 -> 182
    //   195: astore 8
    //   197: aload 5
    //   199: astore 4
    //   201: goto -29 -> 172
    //   204: astore 7
    //   206: aload 5
    //   208: astore 4
    //   210: goto -66 -> 144
    //
    // Exception table:
    //   from	to	target	type
    //   104	109	111	java/io/IOException
    //   125	130	132	java/io/IOException
    //   44	54	142	java/lang/Exception
    //   154	159	161	java/io/IOException
    //   44	54	170	finally
    //   144	149	170	finally
    //   177	182	185	java/io/IOException
    //   54	79	195	finally
    //   84	94	195	finally
    //   54	79	204	java/lang/Exception
    //   84	94	204	java/lang/Exception
  }

  public void init(Context paramContext)
  {
    monitorenter;
    while (true)
    {
      try
      {
        iInitCount = 1 + iInitCount;
        this.mX5CorePath = paramContext.getDir("x5core", 0);
        boolean bool1 = QbSdk.canLoadX5(paramContext);
        initX5CoreVersionIfNeed(paramContext);
        if (mCoreVersion >= 25102)
          continue;
        bool1 = false;
        if (isCompatible(mCoreVersion, this.mX5CorePath.toString()))
          continue;
        bool1 = false;
        QbSdk.forceSysWebView(true);
        if (!bool1)
          break label560;
        if (!needDexOptimize(paramContext))
          continue;
        doDexOptimize(paramContext);
        this.mWizard = new WebViewWizardBase();
        this.mUsedX5 = false;
        this.mWizard.setWizardMode(false, false);
        return;
        boolean bool2 = this.mUsedX5;
        if (bool2)
          continue;
        try
        {
          if (paramContext.getPackageManager().getPackageInfo("com.tencent.mtt", 64).signatures[0].toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a"))
            break label223;
          this.mUsedX5 = false;
          this.mWizard = new WebViewWizardBase();
          this.mWizard.setWizardMode(false, false);
          QbSdk.forceSysWebView(true);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          this.mUsedX5 = false;
          this.mWizard = new WebViewWizardBase();
          this.mWizard.setWizardMode(false, false);
          QbSdk.forceSysWebView(true);
        }
        continue;
      }
      finally
      {
        monitorexit;
      }
      label223: this.mWizard = new WebViewWizardBase();
      this.mWizard.setWizardMode(true, true);
      Context localContext = getPackageContext(paramContext, "com.tencent.mtt");
      if (localContext == null)
      {
        this.mUsedX5 = false;
        this.mWizard.setWizardMode(false, false);
        QbSdk.forceSysWebView(true);
        continue;
      }
      try
      {
        this.mBrowserX5SharePath = localContext.getDir("x5_share", 0);
        this.mBrowserX5CorePath = new File(this.mBrowserX5SharePath, "x5core");
        WebViewWizardBase localWebViewWizardBase = this.mWizard;
        String[] arrayOfString = new String[2];
        arrayOfString[0] = new File(this.mBrowserX5SharePath, "webview_interfaces_dex.jar").getAbsolutePath();
        arrayOfString[1] = new File(this.mBrowserX5CorePath, "webview_dex.jar").getAbsolutePath();
        localWebViewWizardBase.setDexLoader(paramContext, arrayOfString, this.mX5CorePath.getAbsolutePath());
        this.mSoPath = new File(localContext.getApplicationInfo().nativeLibraryDir);
        libwebp.loadWepLibraryIfNeed(paramContext, this.mSoPath.getAbsolutePath());
        this.mWizard.setContextHolderDevelopMode(true);
        boolean bool4 = this.mWizard.setContextHolderParams(paramContext, this.mSoPath.getAbsolutePath());
        bool3 = bool4;
        if (!bool3)
        {
          this.mUsedX5 = false;
          this.mWizard.setContextHolderDevelopMode(false);
          this.mWizard.setWizardMode(false, false);
          QbSdk.forceSysWebView(true);
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          localThrowable.printStackTrace();
          boolean bool3 = false;
        }
        if (iInitCount == 1)
        {
          this.mWizard.setContextHolderDevelopMode(true);
          this.mWizard.setSdkVersion(1);
          this.mWizard.initCookieModule(paramContext);
          this.mWizard.SmttResource_UpdateContext(localContext);
          this.mUsedX5 = true;
          continue;
        }
        this.mWizard = new WebViewWizardBase();
        this.mUsedX5 = false;
        this.mWizard.setWizardMode(false, false);
        QbSdk.forceSysWebView(true);
      }
      continue;
      label560: this.mWizard = new WebViewWizardBase();
      this.mUsedX5 = false;
      this.mWizard.setWizardMode(false, false);
    }
  }

  public boolean isX5Core()
  {
    return this.mUsedX5;
  }

  public WebViewWizardBase wizard()
  {
    return this.mWizard;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.smtt.sdk.SDKEngine
 * JD-Core Version:    0.6.0
 */