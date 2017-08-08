package com.tencent.beacon.a.a;

import java.util.Locale;

public final class b
{
  public static final String[][] a = new String[4][];

  static
  {
    String[][] arrayOfString1 = a;
    String[] arrayOfString2 = new String[2];
    arrayOfString2[0] = "t_event";
    arrayOfString2[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s int , %s int , %s int , %s int , %s blob)", new Object[] { "t_event", "_id", "_time", "_type", "_prority", "_length", "_datas" });
    arrayOfString1[0] = arrayOfString2;
    String[][] arrayOfString3 = a;
    String[] arrayOfString4 = new String[2];
    arrayOfString4[0] = "t_count_event";
    arrayOfString4[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s varchar(255) unique  , %s int , %s int , %s int , %s int , %s int , %s text)", new Object[] { "t_count_event", "_id", "_countid", "_prority", "_local", "_stime", "_utime", "_ctime", "_cparams" });
    arrayOfString3[1] = arrayOfString4;
    String[][] arrayOfString5 = a;
    String[] arrayOfString6 = new String[2];
    arrayOfString6[0] = "t_strategy";
    arrayOfString6[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s int unique , %s int , %s blob)", new Object[] { "t_strategy", "_id", "_key", "_ut", "_datas" });
    arrayOfString5[2] = arrayOfString6;
    String[][] arrayOfString7 = a;
    String[] arrayOfString8 = new String[2];
    arrayOfString8[0] = "t_file";
    arrayOfString8[1] = String.format(Locale.US, "CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s text , %s int , %s text , %s text , %s text , %s text , %s text)", new Object[] { "t_file", "_id", "_n", "_ut", "_sz", "_ac", "_sa", "_t", "_p" });
    arrayOfString7[3] = arrayOfString8;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.a.b
 * JD-Core Version:    0.6.0
 */