package CobraHallQmiProto;

import java.io.Serializable;

public final class RESULTID
  implements Serializable
{
  public static final RESULTID CMD_GIFT_ERR_CFG;
  public static final RESULTID CMD_GIFT_NOT_REG;
  public static final RESULTID CMD_MAGIC_INNER_ERR;
  public static final RESULTID CMD_RET_AUTHEXPIRE;
  public static final RESULTID CMD_RET_AUTHFAIL;
  public static final RESULTID CMD_RET_DRAW_OUT_DATE;
  public static final RESULTID CMD_RET_ERR;
  public static final RESULTID CMD_RET_FEED_PRARERR;
  public static final RESULTID CMD_RET_GIFT_AUTHFAIL;
  public static final RESULTID CMD_RET_GIFT_ERR;
  public static final RESULTID CMD_RET_GIFT_EXHAUSTED;
  public static final RESULTID CMD_RET_GIFT_ID_ERR;
  public static final RESULTID CMD_RET_GIFT_ILLEGAL;
  public static final RESULTID CMD_RET_GIFT_NOT_GRABBED;
  public static final RESULTID CMD_RET_GIFT_RECVED;
  public static final RESULTID CMD_RET_GIFT_TOMUCHCOMMENT;
  public static final RESULTID CMD_RET_MSG_NOT_FRIEND;
  public static final RESULTID CMD_RET_MSG_REFUSE;
  public static final RESULTID CMD_RET_NODATA;
  public static final RESULTID CMD_RET_NORETURN;
  public static final RESULTID CMD_RET_NOT_RIGHT;
  public static final RESULTID CMD_RET_NOUPDATE;
  public static final RESULTID CMD_RET_OUT_DATE;
  public static final RESULTID CMD_RET_TIMEOUT;
  public static final RESULTID CMD_RET_TIME_NOT_REACHED;
  public static final RESULTID CMD_RET_UC_ACCOUNT_TAKEN;
  public static final RESULTID CMD_RET_UC_ADD_YOURSELF;
  public static final RESULTID CMD_RET_UC_ALREADY_FRIEND;
  public static final RESULTID CMD_RET_UC_ALREADY_INVITE;
  public static final RESULTID CMD_RET_UC_FRI_MAX_FRIEND;
  public static final RESULTID CMD_RET_UC_MAX_FRIEND;
  public static final RESULTID CMD_RET_UC_NAME_EXIST;
  public static final RESULTID CMD_RET_UC_NEEDCOMFIRM;
  public static final RESULTID CMD_RET_UC_NOT_BLACKLIST;
  public static final RESULTID CMD_RET_UC_NOT_FRIEND;
  public static final RESULTID CMD_RET_UC_USER_NOT_EXIST;
  public static final RESULTID CMD_RET_UC_VALID_NAME;
  public static final RESULTID HALL_RET_CMDDECERR;
  public static final RESULTID HALL_RET_CMDPARAMERR;
  public static final RESULTID HALL_RET_ERR;
  public static final RESULTID HALL_RET_INVALIDCMD;
  public static final RESULTID HALL_RET_NETWORKERR;
  public static final RESULTID HALL_RET_NONETWORK;
  public static final RESULTID HALL_RET_PKGDECERR;
  public static final RESULTID HALL_RET_PKGPARAMERR;
  public static final RESULTID HALL_RET_SUCC;
  public static final int _CMD_GIFT_ERR_CFG = 207;
  public static final int _CMD_GIFT_NOT_REG = 206;
  public static final int _CMD_MAGIC_INNER_ERR = 205;
  public static final int _CMD_RET_AUTHEXPIRE = 105;
  public static final int _CMD_RET_AUTHFAIL = 104;
  public static final int _CMD_RET_DRAW_OUT_DATE = 204;
  public static final int _CMD_RET_ERR = 100;
  public static final int _CMD_RET_FEED_PRARERR = 300;
  public static final int _CMD_RET_GIFT_AUTHFAIL = 302;
  public static final int _CMD_RET_GIFT_ERR = 301;
  public static final int _CMD_RET_GIFT_EXHAUSTED = 209;
  public static final int _CMD_RET_GIFT_ID_ERR = 201;
  public static final int _CMD_RET_GIFT_ILLEGAL = 303;
  public static final int _CMD_RET_GIFT_NOT_GRABBED = 210;
  public static final int _CMD_RET_GIFT_RECVED = 200;
  public static final int _CMD_RET_GIFT_TOMUCHCOMMENT = 304;
  public static final int _CMD_RET_MSG_NOT_FRIEND = 500;
  public static final int _CMD_RET_MSG_REFUSE = 501;
  public static final int _CMD_RET_NODATA = 101;
  public static final int _CMD_RET_NORETURN = 199;
  public static final int _CMD_RET_NOT_RIGHT = 202;
  public static final int _CMD_RET_NOUPDATE = 102;
  public static final int _CMD_RET_OUT_DATE = 203;
  public static final int _CMD_RET_TIMEOUT = 103;
  public static final int _CMD_RET_TIME_NOT_REACHED = 208;
  public static final int _CMD_RET_UC_ACCOUNT_TAKEN = 408;
  public static final int _CMD_RET_UC_ADD_YOURSELF = 403;
  public static final int _CMD_RET_UC_ALREADY_FRIEND = 402;
  public static final int _CMD_RET_UC_ALREADY_INVITE = 411;
  public static final int _CMD_RET_UC_FRI_MAX_FRIEND = 407;
  public static final int _CMD_RET_UC_MAX_FRIEND = 406;
  public static final int _CMD_RET_UC_NAME_EXIST = 410;
  public static final int _CMD_RET_UC_NEEDCOMFIRM = 400;
  public static final int _CMD_RET_UC_NOT_BLACKLIST = 404;
  public static final int _CMD_RET_UC_NOT_FRIEND = 401;
  public static final int _CMD_RET_UC_USER_NOT_EXIST = 405;
  public static final int _CMD_RET_UC_VALID_NAME = 409;
  public static final int _HALL_RET_CMDDECERR = 4;
  public static final int _HALL_RET_CMDPARAMERR = 5;
  public static final int _HALL_RET_ERR = 1;
  public static final int _HALL_RET_INVALIDCMD = 6;
  public static final int _HALL_RET_NETWORKERR = 8;
  public static final int _HALL_RET_NONETWORK = 7;
  public static final int _HALL_RET_PKGDECERR = 2;
  public static final int _HALL_RET_PKGPARAMERR = 3;
  public static final int _HALL_RET_SUCC;
  private static RESULTID[] __values;
  private String __T = new String();
  private int __value;

  static
  {
    if (!RESULTID.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      $assertionsDisabled = bool;
      __values = new RESULTID[46];
      HALL_RET_SUCC = new RESULTID(0, 0, "HALL_RET_SUCC");
      HALL_RET_ERR = new RESULTID(1, 1, "HALL_RET_ERR");
      HALL_RET_PKGDECERR = new RESULTID(2, 2, "HALL_RET_PKGDECERR");
      HALL_RET_PKGPARAMERR = new RESULTID(3, 3, "HALL_RET_PKGPARAMERR");
      HALL_RET_CMDDECERR = new RESULTID(4, 4, "HALL_RET_CMDDECERR");
      HALL_RET_CMDPARAMERR = new RESULTID(5, 5, "HALL_RET_CMDPARAMERR");
      HALL_RET_INVALIDCMD = new RESULTID(6, 6, "HALL_RET_INVALIDCMD");
      HALL_RET_NONETWORK = new RESULTID(7, 7, "HALL_RET_NONETWORK");
      HALL_RET_NETWORKERR = new RESULTID(8, 8, "HALL_RET_NETWORKERR");
      CMD_RET_ERR = new RESULTID(9, 100, "CMD_RET_ERR");
      CMD_RET_NODATA = new RESULTID(10, 101, "CMD_RET_NODATA");
      CMD_RET_NOUPDATE = new RESULTID(11, 102, "CMD_RET_NOUPDATE");
      CMD_RET_TIMEOUT = new RESULTID(12, 103, "CMD_RET_TIMEOUT");
      CMD_RET_AUTHFAIL = new RESULTID(13, 104, "CMD_RET_AUTHFAIL");
      CMD_RET_AUTHEXPIRE = new RESULTID(14, 105, "CMD_RET_AUTHEXPIRE");
      CMD_RET_NORETURN = new RESULTID(15, 199, "CMD_RET_NORETURN");
      CMD_RET_GIFT_RECVED = new RESULTID(16, 200, "CMD_RET_GIFT_RECVED");
      CMD_RET_GIFT_ID_ERR = new RESULTID(17, 201, "CMD_RET_GIFT_ID_ERR");
      CMD_RET_NOT_RIGHT = new RESULTID(18, 202, "CMD_RET_NOT_RIGHT");
      CMD_RET_OUT_DATE = new RESULTID(19, 203, "CMD_RET_OUT_DATE");
      CMD_RET_DRAW_OUT_DATE = new RESULTID(20, 204, "CMD_RET_DRAW_OUT_DATE");
      CMD_MAGIC_INNER_ERR = new RESULTID(21, 205, "CMD_MAGIC_INNER_ERR");
      CMD_GIFT_NOT_REG = new RESULTID(22, 206, "CMD_GIFT_NOT_REG");
      CMD_GIFT_ERR_CFG = new RESULTID(23, 207, "CMD_GIFT_ERR_CFG");
      CMD_RET_TIME_NOT_REACHED = new RESULTID(24, 208, "CMD_RET_TIME_NOT_REACHED");
      CMD_RET_GIFT_EXHAUSTED = new RESULTID(25, 209, "CMD_RET_GIFT_EXHAUSTED");
      CMD_RET_GIFT_NOT_GRABBED = new RESULTID(26, 210, "CMD_RET_GIFT_NOT_GRABBED");
      CMD_RET_FEED_PRARERR = new RESULTID(27, 300, "CMD_RET_FEED_PRARERR");
      CMD_RET_GIFT_ERR = new RESULTID(28, 301, "CMD_RET_GIFT_ERR");
      CMD_RET_GIFT_AUTHFAIL = new RESULTID(29, 302, "CMD_RET_GIFT_AUTHFAIL");
      CMD_RET_GIFT_ILLEGAL = new RESULTID(30, 303, "CMD_RET_GIFT_ILLEGAL");
      CMD_RET_GIFT_TOMUCHCOMMENT = new RESULTID(31, 304, "CMD_RET_GIFT_TOMUCHCOMMENT");
      CMD_RET_UC_NEEDCOMFIRM = new RESULTID(32, 400, "CMD_RET_UC_NEEDCOMFIRM");
      CMD_RET_UC_NOT_FRIEND = new RESULTID(33, 401, "CMD_RET_UC_NOT_FRIEND");
      CMD_RET_UC_ALREADY_FRIEND = new RESULTID(34, 402, "CMD_RET_UC_ALREADY_FRIEND");
      CMD_RET_UC_ADD_YOURSELF = new RESULTID(35, 403, "CMD_RET_UC_ADD_YOURSELF");
      CMD_RET_UC_NOT_BLACKLIST = new RESULTID(36, 404, "CMD_RET_UC_NOT_BLACKLIST");
      CMD_RET_UC_USER_NOT_EXIST = new RESULTID(37, 405, "CMD_RET_UC_USER_NOT_EXIST");
      CMD_RET_UC_MAX_FRIEND = new RESULTID(38, 406, "CMD_RET_UC_MAX_FRIEND");
      CMD_RET_UC_FRI_MAX_FRIEND = new RESULTID(39, 407, "CMD_RET_UC_FRI_MAX_FRIEND");
      CMD_RET_UC_ACCOUNT_TAKEN = new RESULTID(40, 408, "CMD_RET_UC_ACCOUNT_TAKEN");
      CMD_RET_UC_VALID_NAME = new RESULTID(41, 409, "CMD_RET_UC_VALID_NAME");
      CMD_RET_UC_NAME_EXIST = new RESULTID(42, 410, "CMD_RET_UC_NAME_EXIST");
      CMD_RET_UC_ALREADY_INVITE = new RESULTID(43, 411, "CMD_RET_UC_ALREADY_INVITE");
      CMD_RET_MSG_NOT_FRIEND = new RESULTID(44, 500, "CMD_RET_MSG_NOT_FRIEND");
      CMD_RET_MSG_REFUSE = new RESULTID(45, 501, "CMD_RET_MSG_REFUSE");
      return;
    }
  }

  private RESULTID(int paramInt1, int paramInt2, String paramString)
  {
    this.__T = paramString;
    this.__value = paramInt2;
    __values[paramInt1] = this;
  }

  public static RESULTID a(int paramInt)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].a() == paramInt)
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public static RESULTID a(String paramString)
  {
    for (int i = 0; i < __values.length; i++)
      if (__values[i].toString().equals(paramString))
        return __values[i];
    if (!$assertionsDisabled)
      throw new AssertionError();
    return null;
  }

  public int a()
  {
    return this.__value;
  }

  public String toString()
  {
    return this.__T;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     CobraHallQmiProto.RESULTID
 * JD-Core Version:    0.6.0
 */