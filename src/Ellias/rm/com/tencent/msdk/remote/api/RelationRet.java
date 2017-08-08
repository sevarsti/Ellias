package com.tencent.msdk.remote.api;

import com.tencent.msdk.api.CallbackRet;
import java.util.Vector;

public class RelationRet extends CallbackRet
{
  public Vector<PersonInfo> persons = new Vector();

  public String toString()
  {
    if ((this != null) && (this.persons != null))
    {
      String str = super.toString();
      return str + "friends(num): " + this.persons.size() + ";";
    }
    return "friends(num): 0;";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.remote.api.RelationRet
 * JD-Core Version:    0.6.0
 */