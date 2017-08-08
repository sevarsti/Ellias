package com.tencent.msdk.remote.api;

public class PersonInfo
{
  public String city = "";
  public String country = "";
  public float distance = 0.0F;
  public String gender = "";
  public boolean isFriend = false;
  public String lang = "";
  public String nickName = "";
  public String openId = "";
  public String pictureLarge = "";
  public String pictureMiddle = "";
  public String pictureSmall = "";
  public String province = "";
  public long timestamp = 0L;

  public PersonInfo()
  {
  }

  public PersonInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8)
  {
    this.nickName = paramString1;
    this.openId = paramString2;
    this.gender = paramString3;
    this.pictureSmall = paramString4;
    this.pictureMiddle = paramString5;
    this.pictureLarge = paramString6;
    this.province = paramString7;
    this.city = paramString8;
  }

  public PersonInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, float paramFloat, boolean paramBoolean, long paramLong)
  {
    this.nickName = paramString1;
    this.openId = paramString2;
    this.gender = paramString3;
    this.pictureSmall = paramString4;
    this.pictureMiddle = paramString5;
    this.pictureLarge = paramString6;
    this.province = paramString7;
    this.city = paramString8;
    this.distance = paramFloat;
    this.isFriend = paramBoolean;
    this.timestamp = paramLong;
  }

  public PersonInfo(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, String paramString10)
  {
    this.nickName = paramString1;
    this.openId = paramString2;
    this.gender = paramString3;
    this.pictureSmall = paramString4;
    this.pictureMiddle = paramString5;
    this.pictureLarge = paramString6;
    this.province = paramString7;
    this.city = paramString8;
    this.lang = paramString9;
    this.country = paramString10;
  }

  public String toString()
  {
    String str1 = "" + "nickName: " + this.nickName + "; ";
    String str2 = str1 + "openId: " + this.openId + "; ";
    String str3 = str2 + "gender: " + this.gender + "; ";
    String str4 = str3 + "pictureSmall: " + this.pictureSmall + "; ";
    String str5 = str4 + "pictureMiddle: " + this.pictureMiddle + "; ";
    String str6 = str5 + "pictureLarge: " + this.pictureLarge + "; ";
    String str7 = str6 + "provice: " + this.province + "; ";
    String str8 = str7 + "city: " + this.city + "; ";
    String str9 = str8 + "distance: " + this.distance + "; ";
    String str10 = str9 + "isFriend: " + this.isFriend + "; ";
    String str11 = str10 + "timestamp: " + this.timestamp + "; ";
    String str12 = str11 + "lang: " + this.lang + "; ";
    return str12 + "country: " + this.country + "; ";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.PersonInfo
 * JD-Core Version:    0.6.0
 */