package com.tencent.feedback.common.a;

import java.util.Locale;

public final class b
{
  public static final String[][] a = new String[5][];

  static
  {
    String[][] arrayOfString1 = a;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "ao";
    arrayOfString2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY , %s int , %s int , %s int , %s int , %s blob , %s text , %s int ,%s int , %s int)", new Object[] { "ao", "_id", "_time", "_type", "_prority", "_length", "_datas", "_key", "_upCounts", "_count", "_state" });
    arrayOfString1[0] = arrayOfString2;
    String[][] arrayOfString3 = a;
    String[] arrayOfString4 = new String[2];
    arrayOfString4[0] = "count";
    arrayOfString4[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY , %s text unique  , %s int , %s int , %s int , %s int , %s int , %s text)", new Object[] { "count", "_id", "_countid", "_prority", "_local", "_stime", "_utime", "_ctime", "_cparams" });
    arrayOfString3[1] = arrayOfString4;
    String[][] arrayOfString5 = a;
    String[] arrayOfString6 = new String[2];
    arrayOfString6[0] = "gray";
    arrayOfString6[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY , %s int , %s text unique )", new Object[] { "gray", "_id", "_time", "_uid" });
    arrayOfString5[2] = arrayOfString6;
    String[][] arrayOfString7 = a;
    String[] arrayOfString8 = new String[2];
    arrayOfString8[0] = "file";
    arrayOfString8[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY , %s text , %s int , %s int , %s text , %s int , %s text )", new Object[] { "file", "_id", "_n", "_ut", "_sz", "_sa", "_t", "_ac" });
    arrayOfString7[3] = arrayOfString8;
    String[][] arrayOfString9 = a;
    String[] arrayOfString10 = new String[2];
    arrayOfString10[0] = "strategy";
    arrayOfString10[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY , %s int unique , %s int , %s blob)", new Object[] { "strategy", "_id", "_key", "_ut", "_datas" });
    arrayOfString9[4] = arrayOfString10;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.a.b
 * JD-Core Version:    0.6.0
 */