package com.tencent.component.net.http.cookie;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public class SerializableCookie
  implements Serializable
{
  private static final long serialVersionUID = 6374381828722046732L;
  private transient BasicClientCookie clientCookie;
  private final transient Cookie cookie;

  public SerializableCookie(Cookie paramCookie)
  {
    this.cookie = paramCookie;
  }

  private void a(ObjectInputStream paramObjectInputStream)
  {
    this.clientCookie = new BasicClientCookie((String)paramObjectInputStream.readObject(), (String)paramObjectInputStream.readObject());
    this.clientCookie.setComment((String)paramObjectInputStream.readObject());
    this.clientCookie.setDomain((String)paramObjectInputStream.readObject());
    this.clientCookie.setExpiryDate((Date)paramObjectInputStream.readObject());
    this.clientCookie.setPath((String)paramObjectInputStream.readObject());
    this.clientCookie.setVersion(paramObjectInputStream.readInt());
    this.clientCookie.setSecure(paramObjectInputStream.readBoolean());
  }

  private void a(ObjectOutputStream paramObjectOutputStream)
  {
    paramObjectOutputStream.writeObject(this.cookie.getName());
    paramObjectOutputStream.writeObject(this.cookie.getValue());
    paramObjectOutputStream.writeObject(this.cookie.getComment());
    paramObjectOutputStream.writeObject(this.cookie.getDomain());
    paramObjectOutputStream.writeObject(this.cookie.getExpiryDate());
    paramObjectOutputStream.writeObject(this.cookie.getPath());
    paramObjectOutputStream.writeInt(this.cookie.getVersion());
    paramObjectOutputStream.writeBoolean(this.cookie.isSecure());
  }

  public Cookie a()
  {
    Object localObject = this.cookie;
    if (this.clientCookie != null)
      localObject = this.clientCookie;
    return (Cookie)localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.cookie.SerializableCookie
 * JD-Core Version:    0.6.0
 */