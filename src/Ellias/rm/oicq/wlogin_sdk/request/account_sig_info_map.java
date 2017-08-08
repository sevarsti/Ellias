package oicq.wlogin_sdk.request;

import B;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.util;

public class account_sig_info_map
{
  public static final String _name_file_name = "name_file";
  public static final String _sig_file_name = "tk_file";
  Context _context;
  String _last_login_file_name = "last_login_file";
  WloginLastLoginInfo _last_login_info = new WloginLastLoginInfo();
  TreeMap<String, UinInfo> _name_map = new TreeMap();
  TreeMap<Long, WloginAllSigInfo> _uin_map = new TreeMap();

  public account_sig_info_map(Context paramContext)
  {
    this._context = paramContext;
  }

  public static byte[] get_from_db(Context paramContext, String paramString)
  {
    Cursor localCursor1 = null;
    Boolean localBoolean = Boolean.valueOf(false);
    TkFileDBHelper localTkFileDBHelper = new TkFileDBHelper(paramContext, paramString, null, 1);
    SQLiteDatabase localSQLiteDatabase = localTkFileDBHelper.getWritableDatabase();
    Cursor localCursor2;
    try
    {
      localCursor1 = localSQLiteDatabase.rawQuery("select count(*) from sqlite_master where type ='table' and name ='" + paramString + "' ", null);
      if ((localCursor1.moveToNext()) && (localCursor1.getInt(0) > 0))
        localBoolean = Boolean.valueOf(true);
      if ((localCursor1 != null) && (!localCursor1.isClosed()))
        localCursor1.close();
      if (!localBoolean.booleanValue())
      {
        localTkFileDBHelper.close();
        return null;
      }
      localCursor2 = localSQLiteDatabase.query(paramString, new String[] { paramString }, "ID=0", null, null, null, null);
      if (localCursor2.getCount() == 0)
      {
        localCursor2.close();
        localTkFileDBHelper.close();
        return null;
      }
    }
    catch (SQLException localSQLException)
    {
      util.printException(localSQLException);
      if ((localCursor1 != null) && (!localCursor1.isClosed()))
        localCursor1.close();
      localTkFileDBHelper.close();
      return null;
    }
    localCursor2.moveToFirst();
    byte[] arrayOfByte = localCursor2.getBlob(0);
    localCursor2.close();
    localTkFileDBHelper.close();
    return arrayOfByte;
  }

  public static WloginSigInfo get_siginfo(Context paramContext, long paramLong)
  {
    monitorenter;
    Object localObject1 = null;
    int i = 0;
    label42: label205: label211: 
    while (true)
    {
      Object localObject3;
      try
      {
        TreeMap localTreeMap = loadTKTreeMap(paramContext, "tk_file");
        if (localTreeMap != null)
          continue;
        localObject3 = null;
        return localObject3;
        Iterator localIterator1 = localTreeMap.keySet().iterator();
        if (localIterator1.hasNext())
        {
          Long localLong1 = (Long)localIterator1.next();
          WloginAllSigInfo localWloginAllSigInfo = (WloginAllSigInfo)localTreeMap.get(localLong1);
          Iterator localIterator2 = localWloginAllSigInfo._tk_map.keySet().iterator();
          if (!localIterator2.hasNext())
            break label205;
          Long localLong2 = (Long)localIterator2.next();
          localObject1 = (WloginSigInfo)localWloginAllSigInfo._tk_map.get(localLong2);
          if (((WloginSigInfo)localObject1).iSExpireA2(paramLong))
            continue;
          WloginSigInfo localWloginSigInfo = new WloginSigInfo((WloginSigInfo)localObject1);
          localWloginSigInfo._app_pri = localLong1.longValue();
          localWloginSigInfo._ret_appid = localLong2.longValue();
          localObject1 = localWloginSigInfo;
          i = 1;
          break label205;
          localObject3 = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      while (true)
      {
        if (i == 0)
          break label211;
        localObject3 = localObject1;
        break;
        if (i == 0)
          break label42;
      }
    }
  }

  public static int indexOf(byte[] paramArrayOfByte1, int paramInt, byte[] paramArrayOfByte2)
  {
    int i = paramInt;
    if (i >= paramArrayOfByte1.length - paramArrayOfByte2.length - paramInt)
    {
      i = -1;
      label15: return i;
    }
    int j = 1;
    label58: label60: for (int k = 0; ; k++)
    {
      if (k >= paramArrayOfByte2.length);
      while (true)
      {
        if (j != 0)
          break label58;
        i++;
        break;
        if (paramArrayOfByte1[(i + k)] == paramArrayOfByte2[k])
          break label60;
        j = 0;
      }
      break label15;
    }
  }

  // ERROR //
  public static TreeMap loadTKTreeMap(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: getstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   3: ifnull +10 -> 13
    //   6: getstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   9: arraylength
    //   10: ifne +10 -> 20
    //   13: aload_0
    //   14: invokestatic 201	oicq/wlogin_sdk/tools/util:get_saved_imei	(Landroid/content/Context;)[B
    //   17: putstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   20: aload_0
    //   21: aload_1
    //   22: invokestatic 203	oicq/wlogin_sdk/request/account_sig_info_map:get_from_db	(Landroid/content/Context;Ljava/lang/String;)[B
    //   25: astore_2
    //   26: aconst_null
    //   27: astore_3
    //   28: aload_2
    //   29: ifnull +139 -> 168
    //   32: new 205	javax/crypto/spec/SecretKeySpec
    //   35: dup
    //   36: getstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   39: ldc 207
    //   41: invokespecial 210	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   44: astore 50
    //   46: ldc 207
    //   48: invokestatic 216	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   51: astore 54
    //   53: aload 54
    //   55: iconst_2
    //   56: aload 50
    //   58: invokevirtual 220	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   61: new 222	javax/crypto/CipherInputStream
    //   64: dup
    //   65: new 224	java/io/ByteArrayInputStream
    //   68: dup
    //   69: aload_2
    //   70: invokespecial 227	java/io/ByteArrayInputStream:<init>	([B)V
    //   73: aload 54
    //   75: invokespecial 230	javax/crypto/CipherInputStream:<init>	(Ljava/io/InputStream;Ljavax/crypto/Cipher;)V
    //   78: astore 55
    //   80: new 232	java/io/ObjectInputStream
    //   83: dup
    //   84: aload 55
    //   86: invokespecial 235	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   89: astore 4
    //   91: aload 4
    //   93: invokevirtual 238	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   96: checkcast 37	java/util/TreeMap
    //   99: astore 56
    //   101: aload 56
    //   103: ifnull +68 -> 171
    //   106: aload 4
    //   108: invokevirtual 239	java/io/ObjectInputStream:close	()V
    //   111: aload 4
    //   113: pop
    //   114: aload 56
    //   116: areturn
    //   117: astore 51
    //   119: new 241	java/io/StringWriter
    //   122: dup
    //   123: invokespecial 242	java/io/StringWriter:<init>	()V
    //   126: astore 52
    //   128: new 244	java/io/PrintWriter
    //   131: dup
    //   132: aload 52
    //   134: iconst_1
    //   135: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   138: astore 53
    //   140: aload 51
    //   142: aload 53
    //   144: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   147: aload 53
    //   149: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   152: aload 52
    //   154: invokevirtual 255	java/io/StringWriter:flush	()V
    //   157: ldc_w 257
    //   160: aload 52
    //   162: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   165: invokestatic 262	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   168: aload_3
    //   169: astore 4
    //   171: new 205	javax/crypto/spec/SecretKeySpec
    //   174: dup
    //   175: getstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   178: ldc 207
    //   180: invokespecial 210	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   183: astore 5
    //   185: ldc 207
    //   187: invokestatic 216	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   190: astore 48
    //   192: aload 48
    //   194: iconst_2
    //   195: aload 5
    //   197: invokevirtual 220	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   200: new 222	javax/crypto/CipherInputStream
    //   203: dup
    //   204: aload_0
    //   205: aload_1
    //   206: invokevirtual 268	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   209: aload 48
    //   211: invokespecial 230	javax/crypto/CipherInputStream:<init>	(Ljava/io/InputStream;Ljavax/crypto/Cipher;)V
    //   214: astore 49
    //   216: new 232	java/io/ObjectInputStream
    //   219: dup
    //   220: aload 49
    //   222: invokespecial 235	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   225: astore 12
    //   227: aload 12
    //   229: invokevirtual 238	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   232: checkcast 37	java/util/TreeMap
    //   235: astore 15
    //   237: aload 15
    //   239: ifnonnull +1374 -> 1613
    //   242: aload 15
    //   244: areturn
    //   245: astore 6
    //   247: aload 6
    //   249: instanceof 270
    //   252: ifne +114 -> 366
    //   255: new 65	java/lang/StringBuilder
    //   258: dup
    //   259: ldc_w 272
    //   262: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   265: getstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   268: invokestatic 276	oicq/wlogin_sdk/tools/util:buf_to_string	([B)Ljava/lang/String;
    //   271: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   277: invokestatic 279	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;)V
    //   280: sipush 256
    //   283: newarray byte
    //   285: astore 45
    //   287: aload_0
    //   288: aload_1
    //   289: invokevirtual 268	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   292: astore 46
    //   294: aload 46
    //   296: aload 45
    //   298: iconst_0
    //   299: aload 45
    //   301: arraylength
    //   302: invokevirtual 285	java/io/FileInputStream:read	([BII)I
    //   305: istore 47
    //   307: iload 47
    //   309: ifgt +136 -> 445
    //   312: aload 46
    //   314: invokevirtual 286	java/io/FileInputStream:close	()V
    //   317: new 241	java/io/StringWriter
    //   320: dup
    //   321: invokespecial 242	java/io/StringWriter:<init>	()V
    //   324: astore 43
    //   326: new 244	java/io/PrintWriter
    //   329: dup
    //   330: aload 43
    //   332: iconst_1
    //   333: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   336: astore 44
    //   338: aload 6
    //   340: aload 44
    //   342: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   345: aload 44
    //   347: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   350: aload 43
    //   352: invokevirtual 255	java/io/StringWriter:flush	()V
    //   355: ldc_w 257
    //   358: aload 43
    //   360: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   363: invokestatic 262	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   366: new 205	javax/crypto/spec/SecretKeySpec
    //   369: dup
    //   370: new 108	java/lang/String
    //   373: dup
    //   374: ldc_w 288
    //   377: invokespecial 289	java/lang/String:<init>	(Ljava/lang/String;)V
    //   380: invokevirtual 293	java/lang/String:getBytes	()[B
    //   383: ldc 207
    //   385: invokespecial 210	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   388: astore 7
    //   390: ldc 207
    //   392: invokestatic 216	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   395: astore 40
    //   397: aload 40
    //   399: iconst_2
    //   400: aload 7
    //   402: invokevirtual 220	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   405: new 222	javax/crypto/CipherInputStream
    //   408: dup
    //   409: aload_0
    //   410: aload_1
    //   411: invokevirtual 268	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   414: aload 40
    //   416: invokespecial 230	javax/crypto/CipherInputStream:<init>	(Ljava/io/InputStream;Ljavax/crypto/Cipher;)V
    //   419: astore 41
    //   421: new 232	java/io/ObjectInputStream
    //   424: dup
    //   425: aload 41
    //   427: invokespecial 235	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   430: astore 12
    //   432: aload 12
    //   434: invokevirtual 238	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   437: checkcast 37	java/util/TreeMap
    //   440: astore 15
    //   442: goto -205 -> 237
    //   445: aload 45
    //   447: iload 47
    //   449: invokestatic 296	oicq/wlogin_sdk/tools/util:buf_to_string	([BI)Ljava/lang/String;
    //   452: invokestatic 279	oicq/wlogin_sdk/tools/util:LOGI	(Ljava/lang/String;)V
    //   455: goto -161 -> 294
    //   458: astore 42
    //   460: goto -143 -> 317
    //   463: astore 8
    //   465: aload 8
    //   467: instanceof 270
    //   470: ifne +52 -> 522
    //   473: new 241	java/io/StringWriter
    //   476: dup
    //   477: invokespecial 242	java/io/StringWriter:<init>	()V
    //   480: astore 9
    //   482: new 244	java/io/PrintWriter
    //   485: dup
    //   486: aload 9
    //   488: iconst_1
    //   489: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   492: astore 10
    //   494: aload 8
    //   496: aload 10
    //   498: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   501: aload 10
    //   503: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   506: aload 9
    //   508: invokevirtual 255	java/io/StringWriter:flush	()V
    //   511: ldc_w 257
    //   514: aload 9
    //   516: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   519: invokestatic 262	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   522: sipush 256
    //   525: newarray byte
    //   527: astore 20
    //   529: aload 20
    //   531: arraylength
    //   532: bipush 40
    //   534: isub
    //   535: istore 21
    //   537: aload_0
    //   538: aload_1
    //   539: invokevirtual 268	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   542: astore 22
    //   544: aload_0
    //   545: ldc_w 298
    //   548: iconst_0
    //   549: invokevirtual 302	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   552: astore 23
    //   554: aload 22
    //   556: aload 20
    //   558: bipush 40
    //   560: iload 21
    //   562: invokevirtual 285	java/io/FileInputStream:read	([BII)I
    //   565: istore 24
    //   567: iload 24
    //   569: ifgt +54 -> 623
    //   572: aload 22
    //   574: invokevirtual 286	java/io/FileInputStream:close	()V
    //   577: aload 23
    //   579: invokevirtual 305	java/io/FileOutputStream:close	()V
    //   582: aload_0
    //   583: ldc_w 298
    //   586: invokevirtual 268	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   589: astore 25
    //   591: new 232	java/io/ObjectInputStream
    //   594: dup
    //   595: aload 25
    //   597: invokespecial 235	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   600: astore 12
    //   602: aload 12
    //   604: invokevirtual 238	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   607: checkcast 37	java/util/TreeMap
    //   610: astore 15
    //   612: aload_0
    //   613: ldc_w 298
    //   616: invokevirtual 309	android/content/Context:deleteFile	(Ljava/lang/String;)Z
    //   619: pop
    //   620: goto -383 -> 237
    //   623: bipush 40
    //   625: iload 24
    //   627: iadd
    //   628: istore 26
    //   630: aload 20
    //   632: arraylength
    //   633: istore 27
    //   635: iload 26
    //   637: iload 27
    //   639: if_icmplt +111 -> 750
    //   642: iconst_0
    //   643: istore 28
    //   645: goto +1064 -> 1709
    //   648: aload 23
    //   650: aload 20
    //   652: bipush 40
    //   654: iload 24
    //   656: invokevirtual 313	java/io/FileOutputStream:write	([BII)V
    //   659: iload 24
    //   661: bipush 40
    //   663: if_icmple -109 -> 554
    //   666: aload 20
    //   668: iload 24
    //   670: aload 20
    //   672: iconst_0
    //   673: bipush 40
    //   675: invokestatic 319	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   678: goto -124 -> 554
    //   681: astore 11
    //   683: aload 4
    //   685: astore 12
    //   687: aload 11
    //   689: instanceof 270
    //   692: ifne +52 -> 744
    //   695: new 241	java/io/StringWriter
    //   698: dup
    //   699: invokespecial 242	java/io/StringWriter:<init>	()V
    //   702: astore 13
    //   704: new 244	java/io/PrintWriter
    //   707: dup
    //   708: aload 13
    //   710: iconst_1
    //   711: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   714: astore 14
    //   716: aload 11
    //   718: aload 14
    //   720: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   723: aload 14
    //   725: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   728: aload 13
    //   730: invokevirtual 255	java/io/StringWriter:flush	()V
    //   733: ldc_w 257
    //   736: aload 13
    //   738: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   741: invokestatic 262	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   744: aconst_null
    //   745: astore 15
    //   747: goto -135 -> 612
    //   750: aload 20
    //   752: iload 26
    //   754: iconst_0
    //   755: bastore
    //   756: iinc 26 1
    //   759: goto -129 -> 630
    //   762: new 108	java/lang/String
    //   765: dup
    //   766: ldc_w 321
    //   769: invokespecial 289	java/lang/String:<init>	(Ljava/lang/String;)V
    //   772: invokevirtual 293	java/lang/String:getBytes	()[B
    //   775: astore 29
    //   777: aload 20
    //   779: iload 28
    //   781: aload 29
    //   783: invokestatic 323	oicq/wlogin_sdk/request/account_sig_info_map:indexOf	([BI[B)I
    //   786: istore 30
    //   788: iload 30
    //   790: iflt +924 -> 1714
    //   793: iload 30
    //   795: aload 29
    //   797: arraylength
    //   798: iadd
    //   799: istore 28
    //   801: iload 28
    //   803: bipush 8
    //   805: iadd
    //   806: aload 20
    //   808: arraylength
    //   809: if_icmpgt +900 -> 1709
    //   812: aload 20
    //   814: iload 28
    //   816: iconst_0
    //   817: iadd
    //   818: baload
    //   819: bipush 129
    //   821: if_icmpne +888 -> 1709
    //   824: aload 20
    //   826: iload 28
    //   828: iconst_1
    //   829: iadd
    //   830: baload
    //   831: bipush 64
    //   833: if_icmpne +876 -> 1709
    //   836: aload 20
    //   838: iload 28
    //   840: iconst_2
    //   841: iadd
    //   842: baload
    //   843: iconst_1
    //   844: if_icmpne +865 -> 1709
    //   847: aload 20
    //   849: iload 28
    //   851: iconst_3
    //   852: iadd
    //   853: baload
    //   854: bipush 111
    //   856: if_icmpne +853 -> 1709
    //   859: aload 20
    //   861: iload 28
    //   863: iconst_4
    //   864: iadd
    //   865: baload
    //   866: bipush 145
    //   868: if_icmpne +841 -> 1709
    //   871: aload 20
    //   873: iload 28
    //   875: iconst_5
    //   876: iadd
    //   877: baload
    //   878: bipush 212
    //   880: if_icmpne +829 -> 1709
    //   883: aload 20
    //   885: iload 28
    //   887: bipush 6
    //   889: iadd
    //   890: baload
    //   891: bipush 26
    //   893: if_icmpne +816 -> 1709
    //   896: aload 20
    //   898: iload 28
    //   900: bipush 7
    //   902: iadd
    //   903: baload
    //   904: bipush 155
    //   906: if_icmpne +803 -> 1709
    //   909: aload 20
    //   911: iload 28
    //   913: iconst_0
    //   914: iadd
    //   915: iconst_0
    //   916: bastore
    //   917: aload 20
    //   919: iload 28
    //   921: iconst_1
    //   922: iadd
    //   923: iconst_0
    //   924: bastore
    //   925: aload 20
    //   927: iload 28
    //   929: iconst_2
    //   930: iadd
    //   931: iconst_0
    //   932: bastore
    //   933: aload 20
    //   935: iload 28
    //   937: iconst_3
    //   938: iadd
    //   939: iconst_0
    //   940: bastore
    //   941: aload 20
    //   943: iload 28
    //   945: iconst_4
    //   946: iadd
    //   947: iconst_0
    //   948: bastore
    //   949: aload 20
    //   951: iload 28
    //   953: iconst_5
    //   954: iadd
    //   955: iconst_0
    //   956: bastore
    //   957: aload 20
    //   959: iload 28
    //   961: bipush 6
    //   963: iadd
    //   964: iconst_0
    //   965: bastore
    //   966: aload 20
    //   968: iload 28
    //   970: bipush 7
    //   972: iadd
    //   973: iconst_1
    //   974: bastore
    //   975: goto +734 -> 1709
    //   978: new 108	java/lang/String
    //   981: dup
    //   982: ldc_w 325
    //   985: invokespecial 289	java/lang/String:<init>	(Ljava/lang/String;)V
    //   988: invokevirtual 293	java/lang/String:getBytes	()[B
    //   991: astore 32
    //   993: aload 20
    //   995: iload 31
    //   997: aload 32
    //   999: invokestatic 323	oicq/wlogin_sdk/request/account_sig_info_map:indexOf	([BI[B)I
    //   1002: istore 33
    //   1004: iload 33
    //   1006: iflt +716 -> 1722
    //   1009: iload 33
    //   1011: aload 32
    //   1013: arraylength
    //   1014: iadd
    //   1015: istore 31
    //   1017: iload 31
    //   1019: bipush 8
    //   1021: iadd
    //   1022: aload 20
    //   1024: arraylength
    //   1025: if_icmpgt +692 -> 1717
    //   1028: aload 20
    //   1030: iload 31
    //   1032: iconst_0
    //   1033: iadd
    //   1034: baload
    //   1035: ifne +682 -> 1717
    //   1038: aload 20
    //   1040: iload 31
    //   1042: iconst_1
    //   1043: iadd
    //   1044: baload
    //   1045: ifne +672 -> 1717
    //   1048: aload 20
    //   1050: iload 31
    //   1052: iconst_2
    //   1053: iadd
    //   1054: baload
    //   1055: ifne +662 -> 1717
    //   1058: aload 20
    //   1060: iload 31
    //   1062: iconst_3
    //   1063: iadd
    //   1064: baload
    //   1065: ifne +652 -> 1717
    //   1068: aload 20
    //   1070: iload 31
    //   1072: iconst_4
    //   1073: iadd
    //   1074: baload
    //   1075: ifne +642 -> 1717
    //   1078: aload 20
    //   1080: iload 31
    //   1082: iconst_5
    //   1083: iadd
    //   1084: baload
    //   1085: ifne +632 -> 1717
    //   1088: aload 20
    //   1090: iload 31
    //   1092: bipush 6
    //   1094: iadd
    //   1095: baload
    //   1096: ifne +621 -> 1717
    //   1099: aload 20
    //   1101: iload 31
    //   1103: bipush 7
    //   1105: iadd
    //   1106: baload
    //   1107: ifne +610 -> 1717
    //   1110: aload 20
    //   1112: iload 31
    //   1114: iconst_0
    //   1115: iadd
    //   1116: iconst_0
    //   1117: bastore
    //   1118: aload 20
    //   1120: iload 31
    //   1122: iconst_1
    //   1123: iadd
    //   1124: iconst_0
    //   1125: bastore
    //   1126: aload 20
    //   1128: iload 31
    //   1130: iconst_2
    //   1131: iadd
    //   1132: iconst_0
    //   1133: bastore
    //   1134: aload 20
    //   1136: iload 31
    //   1138: iconst_3
    //   1139: iadd
    //   1140: iconst_0
    //   1141: bastore
    //   1142: aload 20
    //   1144: iload 31
    //   1146: iconst_4
    //   1147: iadd
    //   1148: iconst_0
    //   1149: bastore
    //   1150: aload 20
    //   1152: iload 31
    //   1154: iconst_5
    //   1155: iadd
    //   1156: iconst_0
    //   1157: bastore
    //   1158: aload 20
    //   1160: iload 31
    //   1162: bipush 6
    //   1164: iadd
    //   1165: iconst_0
    //   1166: bastore
    //   1167: aload 20
    //   1169: iload 31
    //   1171: bipush 7
    //   1173: iadd
    //   1174: iconst_1
    //   1175: bastore
    //   1176: goto +541 -> 1717
    //   1179: new 108	java/lang/String
    //   1182: dup
    //   1183: ldc_w 327
    //   1186: invokespecial 289	java/lang/String:<init>	(Ljava/lang/String;)V
    //   1189: invokevirtual 293	java/lang/String:getBytes	()[B
    //   1192: astore 35
    //   1194: aload 20
    //   1196: iload 34
    //   1198: aload 35
    //   1200: invokestatic 323	oicq/wlogin_sdk/request/account_sig_info_map:indexOf	([BI[B)I
    //   1203: istore 36
    //   1205: iload 36
    //   1207: iflt +523 -> 1730
    //   1210: iload 36
    //   1212: aload 35
    //   1214: arraylength
    //   1215: iadd
    //   1216: istore 34
    //   1218: iload 34
    //   1220: bipush 8
    //   1222: iadd
    //   1223: aload 20
    //   1225: arraylength
    //   1226: if_icmpgt +499 -> 1725
    //   1229: aload 20
    //   1231: iload 34
    //   1233: iconst_0
    //   1234: iadd
    //   1235: baload
    //   1236: bipush 57
    //   1238: if_icmpne +487 -> 1725
    //   1241: aload 20
    //   1243: iload 34
    //   1245: iconst_1
    //   1246: iadd
    //   1247: baload
    //   1248: bipush 187
    //   1250: if_icmpne +475 -> 1725
    //   1253: aload 20
    //   1255: iload 34
    //   1257: iconst_2
    //   1258: iadd
    //   1259: baload
    //   1260: bipush 172
    //   1262: if_icmpne +463 -> 1725
    //   1265: aload 20
    //   1267: iload 34
    //   1269: iconst_3
    //   1270: iadd
    //   1271: baload
    //   1272: bipush 110
    //   1274: if_icmpne +451 -> 1725
    //   1277: aload 20
    //   1279: iload 34
    //   1281: iconst_4
    //   1282: iadd
    //   1283: baload
    //   1284: bipush 210
    //   1286: if_icmpne +439 -> 1725
    //   1289: aload 20
    //   1291: iload 34
    //   1293: iconst_5
    //   1294: iadd
    //   1295: baload
    //   1296: bipush 98
    //   1298: if_icmpne +427 -> 1725
    //   1301: aload 20
    //   1303: iload 34
    //   1305: bipush 6
    //   1307: iadd
    //   1308: baload
    //   1309: bipush 225
    //   1311: if_icmpne +414 -> 1725
    //   1314: aload 20
    //   1316: iload 34
    //   1318: bipush 7
    //   1320: iadd
    //   1321: baload
    //   1322: bipush 143
    //   1324: if_icmpne +401 -> 1725
    //   1327: aload 20
    //   1329: iload 34
    //   1331: iconst_0
    //   1332: iadd
    //   1333: iconst_0
    //   1334: bastore
    //   1335: aload 20
    //   1337: iload 34
    //   1339: iconst_1
    //   1340: iadd
    //   1341: iconst_0
    //   1342: bastore
    //   1343: aload 20
    //   1345: iload 34
    //   1347: iconst_2
    //   1348: iadd
    //   1349: iconst_0
    //   1350: bastore
    //   1351: aload 20
    //   1353: iload 34
    //   1355: iconst_3
    //   1356: iadd
    //   1357: iconst_0
    //   1358: bastore
    //   1359: aload 20
    //   1361: iload 34
    //   1363: iconst_4
    //   1364: iadd
    //   1365: iconst_0
    //   1366: bastore
    //   1367: aload 20
    //   1369: iload 34
    //   1371: iconst_5
    //   1372: iadd
    //   1373: iconst_0
    //   1374: bastore
    //   1375: aload 20
    //   1377: iload 34
    //   1379: bipush 6
    //   1381: iadd
    //   1382: iconst_0
    //   1383: bastore
    //   1384: aload 20
    //   1386: iload 34
    //   1388: bipush 7
    //   1390: iadd
    //   1391: iconst_1
    //   1392: bastore
    //   1393: goto +332 -> 1725
    //   1396: new 108	java/lang/String
    //   1399: dup
    //   1400: ldc_w 329
    //   1403: invokespecial 289	java/lang/String:<init>	(Ljava/lang/String;)V
    //   1406: invokevirtual 293	java/lang/String:getBytes	()[B
    //   1409: astore 38
    //   1411: aload 20
    //   1413: iload 37
    //   1415: aload 38
    //   1417: invokestatic 323	oicq/wlogin_sdk/request/account_sig_info_map:indexOf	([BI[B)I
    //   1420: istore 39
    //   1422: iload 39
    //   1424: iflt -776 -> 648
    //   1427: iload 39
    //   1429: aload 38
    //   1431: arraylength
    //   1432: iadd
    //   1433: istore 37
    //   1435: iload 37
    //   1437: bipush 8
    //   1439: iadd
    //   1440: aload 20
    //   1442: arraylength
    //   1443: if_icmpgt +290 -> 1733
    //   1446: aload 20
    //   1448: iload 37
    //   1450: iconst_0
    //   1451: iadd
    //   1452: baload
    //   1453: bipush 138
    //   1455: if_icmpne +278 -> 1733
    //   1458: aload 20
    //   1460: iload 37
    //   1462: iconst_1
    //   1463: iadd
    //   1464: baload
    //   1465: bipush 233
    //   1467: if_icmpne +266 -> 1733
    //   1470: aload 20
    //   1472: iload 37
    //   1474: iconst_2
    //   1475: iadd
    //   1476: baload
    //   1477: bipush 128
    //   1479: if_icmpne +254 -> 1733
    //   1482: aload 20
    //   1484: iload 37
    //   1486: iconst_3
    //   1487: iadd
    //   1488: baload
    //   1489: bipush 237
    //   1491: if_icmpne +242 -> 1733
    //   1494: aload 20
    //   1496: iload 37
    //   1498: iconst_4
    //   1499: iadd
    //   1500: baload
    //   1501: bipush 230
    //   1503: if_icmpne +230 -> 1733
    //   1506: aload 20
    //   1508: iload 37
    //   1510: iconst_5
    //   1511: iadd
    //   1512: baload
    //   1513: bipush 99
    //   1515: if_icmpne +218 -> 1733
    //   1518: aload 20
    //   1520: iload 37
    //   1522: bipush 6
    //   1524: iadd
    //   1525: baload
    //   1526: bipush 41
    //   1528: if_icmpne +205 -> 1733
    //   1531: aload 20
    //   1533: iload 37
    //   1535: bipush 7
    //   1537: iadd
    //   1538: baload
    //   1539: bipush 14
    //   1541: if_icmpne +192 -> 1733
    //   1544: aload 20
    //   1546: iload 37
    //   1548: iconst_0
    //   1549: iadd
    //   1550: iconst_0
    //   1551: bastore
    //   1552: aload 20
    //   1554: iload 37
    //   1556: iconst_1
    //   1557: iadd
    //   1558: iconst_0
    //   1559: bastore
    //   1560: aload 20
    //   1562: iload 37
    //   1564: iconst_2
    //   1565: iadd
    //   1566: iconst_0
    //   1567: bastore
    //   1568: aload 20
    //   1570: iload 37
    //   1572: iconst_3
    //   1573: iadd
    //   1574: iconst_0
    //   1575: bastore
    //   1576: aload 20
    //   1578: iload 37
    //   1580: iconst_4
    //   1581: iadd
    //   1582: iconst_0
    //   1583: bastore
    //   1584: aload 20
    //   1586: iload 37
    //   1588: iconst_5
    //   1589: iadd
    //   1590: iconst_0
    //   1591: bastore
    //   1592: aload 20
    //   1594: iload 37
    //   1596: bipush 6
    //   1598: iadd
    //   1599: iconst_0
    //   1600: bastore
    //   1601: aload 20
    //   1603: iload 37
    //   1605: bipush 7
    //   1607: iadd
    //   1608: iconst_1
    //   1609: bastore
    //   1610: goto +123 -> 1733
    //   1613: aload 12
    //   1615: invokevirtual 239	java/io/ObjectInputStream:close	()V
    //   1618: goto -1376 -> 242
    //   1621: astore 17
    //   1623: new 241	java/io/StringWriter
    //   1626: dup
    //   1627: invokespecial 242	java/io/StringWriter:<init>	()V
    //   1630: astore 18
    //   1632: new 244	java/io/PrintWriter
    //   1635: dup
    //   1636: aload 18
    //   1638: iconst_1
    //   1639: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   1642: astore 19
    //   1644: aload 17
    //   1646: aload 19
    //   1648: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   1651: aload 19
    //   1653: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   1656: aload 18
    //   1658: invokevirtual 255	java/io/StringWriter:flush	()V
    //   1661: ldc_w 257
    //   1664: aload 18
    //   1666: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   1669: invokestatic 262	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   1672: aconst_null
    //   1673: astore 15
    //   1675: goto -1433 -> 242
    //   1678: astore 11
    //   1680: goto -993 -> 687
    //   1683: astore 8
    //   1685: aload 12
    //   1687: astore 4
    //   1689: goto -1224 -> 465
    //   1692: astore 6
    //   1694: aload 12
    //   1696: astore 4
    //   1698: goto -1451 -> 247
    //   1701: astore 51
    //   1703: aload 4
    //   1705: astore_3
    //   1706: goto -1587 -> 119
    //   1709: iload 28
    //   1711: ifge -949 -> 762
    //   1714: iconst_0
    //   1715: istore 31
    //   1717: iload 31
    //   1719: ifge -741 -> 978
    //   1722: iconst_0
    //   1723: istore 34
    //   1725: iload 34
    //   1727: ifge -548 -> 1179
    //   1730: iconst_0
    //   1731: istore 37
    //   1733: iload 37
    //   1735: ifge -339 -> 1396
    //   1738: goto -1090 -> 648
    //
    // Exception table:
    //   from	to	target	type
    //   32	91	117	java/lang/Exception
    //   171	227	245	java/lang/Exception
    //   280	294	458	java/lang/Exception
    //   294	307	458	java/lang/Exception
    //   312	317	458	java/lang/Exception
    //   445	455	458	java/lang/Exception
    //   366	432	463	java/lang/Exception
    //   522	554	681	java/lang/Exception
    //   554	567	681	java/lang/Exception
    //   572	602	681	java/lang/Exception
    //   630	635	681	java/lang/Exception
    //   648	659	681	java/lang/Exception
    //   666	678	681	java/lang/Exception
    //   750	756	681	java/lang/Exception
    //   762	788	681	java/lang/Exception
    //   793	975	681	java/lang/Exception
    //   978	1004	681	java/lang/Exception
    //   1009	1176	681	java/lang/Exception
    //   1179	1205	681	java/lang/Exception
    //   1210	1393	681	java/lang/Exception
    //   1396	1422	681	java/lang/Exception
    //   1427	1610	681	java/lang/Exception
    //   1613	1618	1621	java/lang/Exception
    //   602	612	1678	java/lang/Exception
    //   432	442	1683	java/lang/Exception
    //   227	237	1692	java/lang/Exception
    //   91	101	1701	java/lang/Exception
    //   106	111	1701	java/lang/Exception
  }

  // ERROR //
  public static int write_to_db(Context paramContext, String paramString, byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 56	oicq/wlogin_sdk/request/TkFileDBHelper
    //   3: dup
    //   4: aload_0
    //   5: aload_1
    //   6: aconst_null
    //   7: iconst_1
    //   8: invokespecial 59	oicq/wlogin_sdk/request/TkFileDBHelper:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   11: astore_3
    //   12: aload_3
    //   13: invokevirtual 63	oicq/wlogin_sdk/request/TkFileDBHelper:getWritableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 4
    //   18: new 65	java/lang/StringBuilder
    //   21: dup
    //   22: ldc_w 333
    //   25: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: aload_1
    //   29: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: ldc_w 335
    //   35: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: aload_1
    //   39: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   42: ldc_w 337
    //   45: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: astore 5
    //   53: aload 4
    //   55: aload 5
    //   57: invokevirtual 340	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   60: aload 4
    //   62: aload_1
    //   63: iconst_1
    //   64: anewarray 108	java/lang/String
    //   67: dup
    //   68: iconst_0
    //   69: ldc_w 342
    //   72: aastore
    //   73: ldc 110
    //   75: aconst_null
    //   76: aconst_null
    //   77: aconst_null
    //   78: aconst_null
    //   79: invokevirtual 114	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   82: astore 8
    //   84: aload 8
    //   86: invokeinterface 118 1 0
    //   91: ifne +68 -> 159
    //   94: new 65	java/lang/StringBuilder
    //   97: dup
    //   98: ldc_w 344
    //   101: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   104: aload_1
    //   105: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: ldc_w 346
    //   111: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: aload_1
    //   115: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: ldc_w 348
    //   121: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   127: astore 11
    //   129: iconst_2
    //   130: anewarray 4	java/lang/Object
    //   133: astore 12
    //   135: aload 12
    //   137: iconst_0
    //   138: iconst_0
    //   139: invokestatic 353	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   142: aastore
    //   143: aload 12
    //   145: iconst_1
    //   146: iconst_1
    //   147: newarray byte
    //   149: aastore
    //   150: aload 4
    //   152: aload 11
    //   154: aload 12
    //   156: invokevirtual 356	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   159: aload 4
    //   161: new 65	java/lang/StringBuilder
    //   164: dup
    //   165: ldc_w 358
    //   168: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   171: aload_1
    //   172: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   175: ldc_w 360
    //   178: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: aload_1
    //   182: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: ldc_w 362
    //   188: invokevirtual 74	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: invokevirtual 80	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   194: iconst_1
    //   195: anewarray 4	java/lang/Object
    //   198: dup
    //   199: iconst_0
    //   200: aload_2
    //   201: aastore
    //   202: invokevirtual 356	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   205: aload 8
    //   207: invokeinterface 102 1 0
    //   212: aload_3
    //   213: invokevirtual 106	oicq/wlogin_sdk/request/TkFileDBHelper:close	()V
    //   216: iconst_0
    //   217: ireturn
    //   218: astore 6
    //   220: aload 6
    //   222: invokestatic 124	oicq/wlogin_sdk/tools/util:printException	(Ljava/lang/Exception;)V
    //   225: aload_3
    //   226: invokevirtual 106	oicq/wlogin_sdk/request/TkFileDBHelper:close	()V
    //   229: iconst_m1
    //   230: ireturn
    //   231: astore 7
    //   233: aload 7
    //   235: invokestatic 124	oicq/wlogin_sdk/tools/util:printException	(Ljava/lang/Exception;)V
    //   238: iconst_0
    //   239: ifeq +18 -> 257
    //   242: aconst_null
    //   243: invokeinterface 99 1 0
    //   248: ifne +9 -> 257
    //   251: aconst_null
    //   252: invokeinterface 102 1 0
    //   257: aload_3
    //   258: invokevirtual 106	oicq/wlogin_sdk/request/TkFileDBHelper:close	()V
    //   261: iconst_m1
    //   262: ireturn
    //   263: astore 10
    //   265: aload 10
    //   267: invokestatic 124	oicq/wlogin_sdk/tools/util:printException	(Ljava/lang/Exception;)V
    //   270: aload 8
    //   272: invokeinterface 102 1 0
    //   277: aload_3
    //   278: invokevirtual 106	oicq/wlogin_sdk/request/TkFileDBHelper:close	()V
    //   281: iconst_m1
    //   282: ireturn
    //   283: astore 9
    //   285: aload 9
    //   287: invokestatic 124	oicq/wlogin_sdk/tools/util:printException	(Ljava/lang/Exception;)V
    //   290: aload 8
    //   292: invokeinterface 102 1 0
    //   297: aload_3
    //   298: invokevirtual 106	oicq/wlogin_sdk/request/TkFileDBHelper:close	()V
    //   301: iconst_m1
    //   302: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   53	60	218	android/database/SQLException
    //   60	84	231	android/database/SQLException
    //   94	159	263	android/database/SQLException
    //   159	205	283	android/database/SQLException
  }

  public void clear_account(String paramString)
  {
    monitorenter;
    try
    {
      this._name_map.remove(paramString);
      TreeMap localTreeMap;
      if (this._context != null)
      {
        localTreeMap = loadTKTreeMap(this._context, "name_file");
        if (localTreeMap != null)
          break label37;
      }
      while (true)
      {
        return;
        label37: localTreeMap.remove(paramString);
        refreshTKTreeMap(localTreeMap, "name_file");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void clear_sig(Long paramLong)
  {
    monitorenter;
    try
    {
      this._uin_map.remove(paramLong);
      TreeMap localTreeMap;
      if (this._context != null)
      {
        localTreeMap = loadTKTreeMap(this._context, "tk_file");
        if (localTreeMap != null)
          break label37;
      }
      while (true)
      {
        return;
        label37: localTreeMap.remove(paramLong);
        refreshTKTreeMap(localTreeMap, "tk_file");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void clear_sig(Long paramLong1, Long paramLong2)
  {
    monitorenter;
    try
    {
      WloginAllSigInfo localWloginAllSigInfo1 = (WloginAllSigInfo)this._uin_map.get(new Long(paramLong1.longValue()));
      if (localWloginAllSigInfo1 == null);
      while (true)
      {
        return;
        localWloginAllSigInfo1._tk_map.remove(new Long(paramLong2.longValue()));
        if (this._context == null)
          continue;
        TreeMap localTreeMap = loadTKTreeMap(this._context, "tk_file");
        if (localTreeMap == null)
          continue;
        WloginAllSigInfo localWloginAllSigInfo2 = (WloginAllSigInfo)localTreeMap.get(new Long(paramLong1.longValue()));
        if (localWloginAllSigInfo2 == null)
          continue;
        localWloginAllSigInfo2._tk_map.remove(new Long(paramLong2.longValue()));
        refreshTKTreeMap(localTreeMap, "tk_file");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void clear_sig_test(Long paramLong)
  {
    monitorenter;
    try
    {
      this._uin_map.remove(paramLong);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public Long get_account(String paramString)
  {
    monitorenter;
    while (true)
    {
      UinInfo localUinInfo2;
      try
      {
        UinInfo localUinInfo1 = (UinInfo)this._name_map.get(paramString);
        if (localUinInfo1 == null)
          continue;
        localLong = localUinInfo1._uin;
        return localLong;
        Context localContext = this._context;
        localLong = null;
        if (localContext == null)
          continue;
        TreeMap localTreeMap = loadTKTreeMap(this._context, "name_file");
        localLong = null;
        if (localTreeMap == null)
          continue;
        localUinInfo2 = (UinInfo)localTreeMap.get(paramString);
        if (localUinInfo2 == null)
        {
          util.LOGD("get_account 1", "null");
          localLong = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      util.LOGD("get_account 1", "not null");
      this._name_map.put(paramString, localUinInfo2);
      Long localLong = localUinInfo2._uin;
    }
  }

  public String get_account(Long paramLong)
  {
    monitorenter;
    try
    {
      Iterator localIterator1 = this._name_map.keySet().iterator();
      String str;
      if (!localIterator1.hasNext())
      {
        Context localContext = this._context;
        if (localContext != null)
          break label88;
        str = null;
      }
      while (true)
      {
        return str;
        str = (String)localIterator1.next();
        UinInfo localUinInfo1 = (UinInfo)this._name_map.get(str);
        if ((localUinInfo1 == null) || (!localUinInfo1._uin.equals(paramLong)))
          break;
        continue;
        label88: TreeMap localTreeMap = loadTKTreeMap(this._context, "name_file");
        if (localTreeMap == null)
        {
          str = null;
          continue;
        }
        Iterator localIterator2 = localTreeMap.keySet().iterator();
        boolean bool;
        do
        {
          UinInfo localUinInfo2;
          do
          {
            if (!localIterator2.hasNext())
            {
              str = null;
              break;
            }
            str = (String)localIterator2.next();
            localUinInfo2 = (UinInfo)localTreeMap.get(str);
          }
          while (localUinInfo2 == null);
          bool = localUinInfo2._uin.equals(paramLong);
        }
        while (!bool);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public List<WloginLoginInfo> get_all_logined_account(long paramLong, List<Long> paramList, boolean paramBoolean)
  {
    monitorenter;
    while (true)
    {
      Long localLong1;
      WloginAllSigInfo localWloginAllSigInfo;
      Long localLong2;
      WloginSigInfo localWloginSigInfo;
      String str1;
      try
      {
        localArrayList = new ArrayList();
        if (this._context != null)
          break label361;
        Iterator localIterator1 = this._uin_map.keySet().iterator();
        boolean bool = localIterator1.hasNext();
        if (!bool)
          return localArrayList;
        localLong1 = (Long)localIterator1.next();
        localWloginAllSigInfo = get_all_siginfo(localLong1.longValue());
        if (localWloginAllSigInfo == null)
          continue;
        Iterator localIterator2 = localWloginAllSigInfo._tk_map.keySet().iterator();
        if (!localIterator2.hasNext())
          continue;
        localLong2 = (Long)localIterator2.next();
        localWloginSigInfo = (WloginSigInfo)localWloginAllSigInfo._tk_map.get(localLong2);
        if (localWloginSigInfo == null)
          continue;
        str1 = get_account(localLong1);
        if (!paramList.contains(new Long(localLong2.longValue())))
          break label262;
        long l4 = localLong1.longValue();
        long l5 = localLong2.longValue();
        String str3 = new String(localWloginAllSigInfo._useInfo._img_url);
        long l6 = localWloginSigInfo._create_time;
        if (paramBoolean)
        {
          j = WloginLoginInfo.TYPE_LOACL;
          WloginLoginInfo localWloginLoginInfo2 = new WloginLoginInfo(str1, l4, l5, str3, l6, j);
          localArrayList.add(localWloginLoginInfo2);
          continue;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      int j = WloginLoginInfo.TYPE_REMOTE;
      continue;
      label262: if (!localWloginSigInfo.iSHighPri(paramLong))
        continue;
      long l1 = localLong1.longValue();
      long l2 = localLong2.longValue();
      String str2 = new String(localWloginAllSigInfo._useInfo._img_url);
      long l3 = localWloginSigInfo._create_time;
      if (paramBoolean);
      for (int i = WloginLoginInfo.TYPE_LOACL; ; i = WloginLoginInfo.TYPE_REMOTE)
      {
        WloginLoginInfo localWloginLoginInfo1 = new WloginLoginInfo(str1, l1, l2, str2, l3, i);
        localArrayList.add(localWloginLoginInfo1);
        break;
      }
      label361: ArrayList localArrayList = null;
    }
  }

  public List<WloginLoginInfo> get_all_logined_account(boolean paramBoolean)
  {
    monitorenter;
    ArrayList localArrayList;
    label231: Long localLong1;
    WloginAllSigInfo localWloginAllSigInfo1;
    do
    {
      Iterator localIterator3;
      boolean bool;
      Iterator localIterator1;
      while (!localIterator1.hasNext())
      {
        TreeMap localTreeMap;
        do
        {
          do
            while (true)
            {
              try
              {
                localArrayList = new ArrayList();
                if (this._context != null)
                  break label231;
                localIterator3 = this._uin_map.keySet().iterator();
                bool = localIterator3.hasNext();
                if (!bool)
                  return localArrayList;
                Long localLong3 = (Long)localIterator3.next();
                WloginAllSigInfo localWloginAllSigInfo2 = get_all_siginfo(localLong3.longValue());
                if (localWloginAllSigInfo2 == null)
                  break;
                Iterator localIterator4 = localWloginAllSigInfo2._tk_map.keySet().iterator();
                if (!localIterator4.hasNext())
                  continue;
                Long localLong4 = (Long)localIterator4.next();
                WloginSigInfo localWloginSigInfo2 = (WloginSigInfo)localWloginAllSigInfo2._tk_map.get(localLong4);
                if (localWloginSigInfo2 == null)
                  continue;
                String str3 = get_account(localLong3);
                long l4 = localLong3.longValue();
                long l5 = localLong4.longValue();
                String str4 = new String(localWloginAllSigInfo2._useInfo._img_url);
                long l6 = localWloginSigInfo2._create_time;
                if (paramBoolean)
                {
                  j = WloginLoginInfo.TYPE_LOACL;
                  localArrayList.add(new WloginLoginInfo(str3, l4, l5, str4, l6, j));
                  continue;
                  break;
                }
              }
              finally
              {
                monitorexit;
              }
              int j = WloginLoginInfo.TYPE_REMOTE;
            }
          while (this._context == null);
          localTreeMap = loadTKTreeMap(this._context, "tk_file");
        }
        while (localTreeMap == null);
        localIterator1 = localTreeMap.keySet().iterator();
      }
      localLong1 = (Long)localIterator1.next();
      localWloginAllSigInfo1 = get_all_siginfo(localLong1.longValue());
    }
    while (localWloginAllSigInfo1 == null);
    Iterator localIterator2 = localWloginAllSigInfo1._tk_map.keySet().iterator();
    label319: String str1;
    long l1;
    long l2;
    String str2;
    long l3;
    while (localIterator2.hasNext())
    {
      Long localLong2 = (Long)localIterator2.next();
      WloginSigInfo localWloginSigInfo1 = (WloginSigInfo)localWloginAllSigInfo1._tk_map.get(localLong2);
      if (localWloginSigInfo1 == null)
        continue;
      str1 = get_account(localLong1);
      if (localWloginAllSigInfo1._useInfo._img_url == null)
        localWloginAllSigInfo1._useInfo._img_url = new byte[0];
      l1 = localLong1.longValue();
      l2 = localLong2.longValue();
      str2 = new String(localWloginAllSigInfo1._useInfo._img_url);
      l3 = localWloginSigInfo1._create_time;
      if (!paramBoolean)
        break label467;
    }
    label467: for (int i = WloginLoginInfo.TYPE_LOACL; ; i = WloginLoginInfo.TYPE_REMOTE)
    {
      localArrayList.add(new WloginLoginInfo(str1, l1, l2, str2, l3, i));
      break label319;
      break;
    }
  }

  public WloginAllSigInfo get_all_siginfo(long paramLong)
  {
    monitorenter;
    while (true)
    {
      WloginAllSigInfo localWloginAllSigInfo2;
      try
      {
        util.LOGD("get_all_siginfo", "uin=" + paramLong);
        WloginAllSigInfo localWloginAllSigInfo1 = (WloginAllSigInfo)this._uin_map.get(new Long(paramLong));
        if (localWloginAllSigInfo1 == null)
          continue;
        localObject2 = localWloginAllSigInfo1;
        return localObject2;
        Context localContext = this._context;
        localObject2 = null;
        if (localContext == null)
          continue;
        TreeMap localTreeMap = loadTKTreeMap(this._context, "tk_file");
        localObject2 = null;
        if (localTreeMap == null)
          continue;
        localWloginAllSigInfo2 = (WloginAllSigInfo)localTreeMap.get(new Long(paramLong));
        if (localWloginAllSigInfo2 == null)
        {
          util.LOGD("get_all_siginfo", "null");
          localObject2 = null;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this._uin_map.put(new Long(paramLong), localWloginAllSigInfo2);
      WloginAllSigInfo localWloginAllSigInfo3 = localWloginAllSigInfo2.get_clone();
      Object localObject2 = localWloginAllSigInfo3;
    }
  }

  public WloginLastLoginInfo get_last_login_info()
  {
    monitorenter;
    try
    {
      String str = this._last_login_file_name;
      try
      {
        localObjectInputStream = new ObjectInputStream(new BufferedInputStream(this._context.openFileInput(str)));
      }
      catch (Exception localException1)
      {
        try
        {
          localWloginLastLoginInfo2 = (WloginLastLoginInfo)localObjectInputStream.readObject();
        }
        catch (Exception localException1)
        {
          try
          {
            ObjectInputStream localObjectInputStream;
            WloginLastLoginInfo localWloginLastLoginInfo2;
            localObjectInputStream.close();
            localWloginLastLoginInfo1 = localWloginLastLoginInfo2;
            while (true)
            {
              monitorexit;
              return localWloginLastLoginInfo1;
              localException3 = localException3;
              localWloginLastLoginInfo1 = null;
              continue;
              localException1 = localException1;
              StringWriter localStringWriter1 = new StringWriter();
              PrintWriter localPrintWriter1 = new PrintWriter(localStringWriter1, true);
              localException1.printStackTrace(localPrintWriter1);
              localPrintWriter1.flush();
              localStringWriter1.flush();
              util.LOGD("exception", localStringWriter1.toString());
              localWloginLastLoginInfo1 = null;
            }
          }
          catch (Exception localException2)
          {
            while (true)
            {
              StringWriter localStringWriter2 = new StringWriter();
              PrintWriter localPrintWriter2 = new PrintWriter(localStringWriter2, true);
              localException2.printStackTrace(localPrintWriter2);
              localPrintWriter2.flush();
              localStringWriter2.flush();
              util.LOGD("exception", localStringWriter2.toString());
              WloginLastLoginInfo localWloginLastLoginInfo1 = null;
            }
          }
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public WloginSigInfo get_siginfo(long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      util.LOGD("get_siginfo", "uin=" + paramLong1 + "appid=" + paramLong2);
      WloginAllSigInfo localWloginAllSigInfo = get_all_siginfo(paramLong1);
      Object localObject2 = null;
      if (localWloginAllSigInfo == null);
      while (true)
      {
        return localObject2;
        WloginSigInfo localWloginSigInfo = (WloginSigInfo)localWloginAllSigInfo._tk_map.get(new Long(paramLong2));
        localObject2 = null;
        if (localWloginSigInfo == null)
          continue;
        localObject2 = localWloginSigInfo;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public WloginSigInfo get_siginfo_by_pri(long paramLong1, long paramLong2, long paramLong3, long paramLong4)
  {
    monitorenter;
    try
    {
      util.LOGD("get_siginfo", "uin=" + paramLong1 + "appid=" + paramLong2);
      WloginAllSigInfo localWloginAllSigInfo = get_all_siginfo(paramLong1);
      Object localObject2;
      if (localWloginAllSigInfo == null)
        localObject2 = null;
      while (true)
      {
        return localObject2;
        Iterator localIterator = localWloginAllSigInfo._tk_map.keySet().iterator();
        Long localLong;
        WloginSigInfo localWloginSigInfo;
        do
        {
          if (!localIterator.hasNext())
          {
            localObject2 = null;
            break;
          }
          localLong = (Long)localIterator.next();
          localWloginSigInfo = (WloginSigInfo)localWloginAllSigInfo._tk_map.get(localLong);
        }
        while ((localWloginSigInfo.iSExpireA2(paramLong4)) || ((localLong.longValue() != paramLong2) && (!localWloginSigInfo.iSHighPri(paramLong3))));
        localWloginSigInfo.set_ret_appid(localLong.longValue());
        localObject2 = localWloginSigInfo;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public WloginSimpleInfo get_simpleinfo(long paramLong)
  {
    monitorenter;
    try
    {
      util.LOGD("get_simpleinfo", "uin=" + paramLong);
      WloginAllSigInfo localWloginAllSigInfo = get_all_siginfo(paramLong);
      if (localWloginAllSigInfo == null);
      WloginSimpleInfo localWloginSimpleInfo;
      for (Object localObject2 = null; ; localObject2 = localWloginSimpleInfo)
      {
        return localObject2;
        localWloginSimpleInfo = localWloginAllSigInfo._useInfo.get_clone();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public byte[] get_stsig(long paramLong1, long paramLong2)
  {
    monitorenter;
    try
    {
      util.LOGD("get_stsig", "uin=" + paramLong1 + "appid=" + paramLong2);
      WloginSigInfo localWloginSigInfo = get_siginfo(paramLong1, paramLong2);
      if (localWloginSigInfo == null);
      for (byte[] arrayOfByte = null; ; arrayOfByte = (byte[])localWloginSigInfo._userStSig.clone())
        return arrayOfByte;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void put_account(String paramString, Long paramLong)
  {
    monitorenter;
    try
    {
      UinInfo localUinInfo = new UinInfo(0L, paramLong);
      this._name_map.put(paramString, localUinInfo);
      if (this._context != null)
      {
        TreeMap localTreeMap = loadTKTreeMap(this._context, "name_file");
        if (localTreeMap == null)
          localTreeMap = new TreeMap();
        localTreeMap.put(paramString, localUinInfo);
        refreshTKTreeMap(localTreeMap, "name_file");
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, long paramLong5, long paramLong6, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, byte[][] paramArrayOfByte5, byte[] paramArrayOfByte6, byte[] paramArrayOfByte7, byte[] paramArrayOfByte8, byte[] paramArrayOfByte9, byte[] paramArrayOfByte10, byte[] paramArrayOfByte11, byte[] paramArrayOfByte12, byte[] paramArrayOfByte13, byte[] paramArrayOfByte14, byte[] paramArrayOfByte15, byte[] paramArrayOfByte16, byte[] paramArrayOfByte17, byte[][] paramArrayOfByte18, long[] paramArrayOfLong)
  {
    monitorenter;
    try
    {
      WloginAllSigInfo localWloginAllSigInfo1 = (WloginAllSigInfo)this._uin_map.get(new Long(paramLong1));
      if (localWloginAllSigInfo1 == null)
        localWloginAllSigInfo1 = new WloginAllSigInfo();
      localWloginAllSigInfo1.put_simpleinfo(paramLong1, paramArrayOfByte1, paramArrayOfByte2, paramArrayOfByte3, paramArrayOfByte4, paramArrayOfByte5);
      int i = localWloginAllSigInfo1.put_siginfo(paramLong2, paramLong3, paramLong4, paramLong5, paramLong6, paramArrayOfByte6, paramArrayOfByte7, paramArrayOfByte8, paramArrayOfByte9, paramArrayOfByte10, paramArrayOfByte11, paramArrayOfByte12, paramArrayOfByte13, paramArrayOfByte14, paramArrayOfByte15, paramArrayOfByte16, paramArrayOfByte17, paramArrayOfByte18, paramArrayOfLong);
      this._uin_map.put(new Long(paramLong1), localWloginAllSigInfo1);
      if (this._context != null)
      {
        TreeMap localTreeMap = loadTKTreeMap(this._context, "tk_file");
        if (localTreeMap == null)
          localTreeMap = new TreeMap();
        Long localLong = new Long(paramLong1);
        WloginAllSigInfo localWloginAllSigInfo2 = localWloginAllSigInfo1.get_clone();
        localTreeMap.put(localLong, localWloginAllSigInfo2);
        refreshTKTreeMap(localTreeMap, "tk_file");
      }
      return i;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int put_siginfo(long paramLong1, long paramLong2, long paramLong3, long paramLong4, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    monitorenter;
    try
    {
      WloginAllSigInfo localWloginAllSigInfo = (WloginAllSigInfo)this._uin_map.get(new Long(paramLong1));
      if (localWloginAllSigInfo == null);
      int i;
      for (int j = -1; ; j = i)
      {
        return j;
        i = localWloginAllSigInfo.put_siginfo(paramLong2, paramLong3, paramLong4, paramArrayOfByte1, paramArrayOfByte2);
        if (this._context == null)
          continue;
        TreeMap localTreeMap = loadTKTreeMap(this._context, "tk_file");
        if (localTreeMap == null)
          localTreeMap = new TreeMap();
        localTreeMap.put(new Long(paramLong1), localWloginAllSigInfo.get_clone());
        refreshTKTreeMap(localTreeMap, "tk_file");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void put_siginfo(Long paramLong, WloginAllSigInfo paramWloginAllSigInfo)
  {
    monitorenter;
    try
    {
      WloginAllSigInfo localWloginAllSigInfo = (WloginAllSigInfo)this._uin_map.get(new Long(paramLong.longValue()));
      if (localWloginAllSigInfo == null)
        this._uin_map.put(new Long(paramLong.longValue()), paramWloginAllSigInfo);
      while (true)
      {
        return;
        Iterator localIterator = paramWloginAllSigInfo._tk_map.keySet().iterator();
        while (localIterator.hasNext())
        {
          Long localLong = (Long)localIterator.next();
          WloginSigInfo localWloginSigInfo = (WloginSigInfo)paramWloginAllSigInfo._tk_map.get(localLong);
          localWloginAllSigInfo.put_siginfo(localLong.longValue(), localWloginSigInfo);
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void refreshTKTreeMap(TreeMap paramTreeMap, String paramString)
  {
    monitorenter;
    if (paramString == "tk_file");
    try
    {
      saveTKTreeMap(paramTreeMap, paramString);
      while (true)
      {
        return;
        if (paramString != "name_file")
          continue;
        saveTKTreeMap(paramTreeMap, paramString);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void remove_account(String paramString)
  {
    monitorenter;
    try
    {
      this._name_map.remove(paramString);
      if (this._context != null)
      {
        TreeMap localTreeMap = loadTKTreeMap(this._context, "name_file");
        if (localTreeMap == null)
          localTreeMap = new TreeMap();
        localTreeMap.remove(paramString);
        refreshTKTreeMap(localTreeMap, "name_file");
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void remove_last_login_info(String paramString)
  {
    WloginLastLoginInfo localWloginLastLoginInfo = get_last_login_info();
    if ((localWloginLastLoginInfo == null) || (!localWloginLastLoginInfo.mAccount.equals(paramString)))
      return;
    String str = this._last_login_file_name;
    this._context.deleteFile(str);
  }

  // ERROR //
  public void saveTKTreeMap(TreeMap paramTreeMap, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 205	javax/crypto/spec/SecretKeySpec
    //   5: dup
    //   6: getstatic 197	oicq/wlogin_sdk/request/request_global:_IMEI_KEY	[B
    //   9: ldc 207
    //   11: invokespecial 210	javax/crypto/spec/SecretKeySpec:<init>	([BLjava/lang/String;)V
    //   14: astore_3
    //   15: ldc 207
    //   17: invokestatic 216	javax/crypto/Cipher:getInstance	(Ljava/lang/String;)Ljavax/crypto/Cipher;
    //   20: astore 8
    //   22: aload 8
    //   24: iconst_1
    //   25: aload_3
    //   26: invokevirtual 220	javax/crypto/Cipher:init	(ILjava/security/Key;)V
    //   29: new 529	java/io/ByteArrayOutputStream
    //   32: dup
    //   33: invokespecial 530	java/io/ByteArrayOutputStream:<init>	()V
    //   36: astore 9
    //   38: new 532	java/io/ObjectOutputStream
    //   41: dup
    //   42: new 534	javax/crypto/CipherOutputStream
    //   45: dup
    //   46: aload 9
    //   48: aload 8
    //   50: invokespecial 537	javax/crypto/CipherOutputStream:<init>	(Ljava/io/OutputStream;Ljavax/crypto/Cipher;)V
    //   53: invokespecial 540	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   56: astore 10
    //   58: aload 10
    //   60: aload_1
    //   61: invokevirtual 544	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   64: aload 10
    //   66: invokevirtual 545	java/io/ObjectOutputStream:close	()V
    //   69: aload_0
    //   70: getfield 44	oicq/wlogin_sdk/request/account_sig_info_map:_context	Landroid/content/Context;
    //   73: aload_2
    //   74: aload 9
    //   76: invokevirtual 548	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   79: invokestatic 550	oicq/wlogin_sdk/request/account_sig_info_map:write_to_db	(Landroid/content/Context;Ljava/lang/String;[B)I
    //   82: pop
    //   83: aload_0
    //   84: monitorexit
    //   85: return
    //   86: astore 5
    //   88: new 241	java/io/StringWriter
    //   91: dup
    //   92: invokespecial 242	java/io/StringWriter:<init>	()V
    //   95: astore 6
    //   97: new 244	java/io/PrintWriter
    //   100: dup
    //   101: aload 6
    //   103: iconst_1
    //   104: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   107: astore 7
    //   109: aload 5
    //   111: aload 7
    //   113: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   116: aload 7
    //   118: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   121: aload 6
    //   123: invokevirtual 255	java/io/StringWriter:flush	()V
    //   126: ldc_w 257
    //   129: aload 6
    //   131: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   134: invokestatic 262	oicq/wlogin_sdk/tools/util:LOGW	(Ljava/lang/String;Ljava/lang/String;)V
    //   137: goto -54 -> 83
    //   140: astore 4
    //   142: aload_0
    //   143: monitorexit
    //   144: aload 4
    //   146: athrow
    //   147: astore 5
    //   149: goto -61 -> 88
    //   152: astore 4
    //   154: goto -12 -> 142
    //
    // Exception table:
    //   from	to	target	type
    //   2	58	86	java/lang/Exception
    //   2	58	140	finally
    //   88	137	140	finally
    //   58	83	147	java/lang/Exception
    //   58	83	152	finally
  }

  // ERROR //
  public void save_last_login_info(String paramString)
  {
    // Byte code:
    //   0: new 32	oicq/wlogin_sdk/request/WloginLastLoginInfo
    //   3: dup
    //   4: invokespecial 33	oicq/wlogin_sdk/request/WloginLastLoginInfo:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: aload_1
    //   10: putfield 526	oicq/wlogin_sdk/request/WloginLastLoginInfo:mAccount	Ljava/lang/String;
    //   13: aload_0
    //   14: getfield 30	oicq/wlogin_sdk/request/account_sig_info_map:_last_login_file_name	Ljava/lang/String;
    //   17: astore_3
    //   18: aconst_null
    //   19: astore 4
    //   21: new 532	java/io/ObjectOutputStream
    //   24: dup
    //   25: new 553	java/io/BufferedOutputStream
    //   28: dup
    //   29: aload_0
    //   30: getfield 44	oicq/wlogin_sdk/request/account_sig_info_map:_context	Landroid/content/Context;
    //   33: aload_3
    //   34: iconst_0
    //   35: invokevirtual 302	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   38: invokespecial 554	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   41: invokespecial 540	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   44: astore 5
    //   46: aload 5
    //   48: aload_2
    //   49: invokevirtual 544	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   52: aload 5
    //   54: astore 4
    //   56: aload 4
    //   58: invokevirtual 545	java/io/ObjectOutputStream:close	()V
    //   61: return
    //   62: astore 6
    //   64: new 241	java/io/StringWriter
    //   67: dup
    //   68: invokespecial 242	java/io/StringWriter:<init>	()V
    //   71: astore 7
    //   73: new 244	java/io/PrintWriter
    //   76: dup
    //   77: aload 7
    //   79: iconst_1
    //   80: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   83: astore 8
    //   85: aload 6
    //   87: aload 8
    //   89: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   92: aload 8
    //   94: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   97: aload 7
    //   99: invokevirtual 255	java/io/StringWriter:flush	()V
    //   102: ldc_w 257
    //   105: aload 7
    //   107: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   110: invokestatic 392	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   113: goto -57 -> 56
    //   116: astore 9
    //   118: new 241	java/io/StringWriter
    //   121: dup
    //   122: invokespecial 242	java/io/StringWriter:<init>	()V
    //   125: astore 10
    //   127: new 244	java/io/PrintWriter
    //   130: dup
    //   131: aload 10
    //   133: iconst_1
    //   134: invokespecial 247	java/io/PrintWriter:<init>	(Ljava/io/Writer;Z)V
    //   137: astore 11
    //   139: aload 9
    //   141: aload 11
    //   143: invokevirtual 251	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   146: aload 11
    //   148: invokevirtual 254	java/io/PrintWriter:flush	()V
    //   151: aload 10
    //   153: invokevirtual 255	java/io/StringWriter:flush	()V
    //   156: ldc_w 257
    //   159: aload 10
    //   161: invokevirtual 258	java/io/StringWriter:toString	()Ljava/lang/String;
    //   164: invokestatic 392	oicq/wlogin_sdk/tools/util:LOGD	(Ljava/lang/String;Ljava/lang/String;)V
    //   167: return
    //   168: astore 6
    //   170: aload 5
    //   172: astore 4
    //   174: goto -110 -> 64
    //
    // Exception table:
    //   from	to	target	type
    //   21	46	62	java/lang/Exception
    //   56	61	116	java/lang/Exception
    //   46	52	168	java/lang/Exception
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     oicq.wlogin_sdk.request.account_sig_info_map
 * JD-Core Version:    0.6.0
 */