package com.saille.bbs.yssy;

public class IdFormat {
    private static final String DEFAULT_FORMAT = "%id\t%lastlogintime\t%life\t%ip\t%post\t%logintimes";
    private String format;

    public IdFormat(String format) {
        if((format == null) || (format.equals(""))) {
            this.format = "%id\t%lastlogintime\t%life\t%ip\t%post\t%logintimes";
        } else {
            this.format = format;
        }
    }

    public String format(Id id) {
        String ret = new String(this.format);
        ret = ret.replaceAll("%id", id.getId());
        ret = ret.replaceAll("%age", String.valueOf(id.getAge()));
        ret = ret.replaceAll("%ip", id.getIp());
        ret = ret.replaceAll("%post", String.valueOf(id.getPost()));
        ret = ret.replaceAll("%lastlogintime", id.getLastLoginTime());
        ret = ret.replaceAll("%life", String.valueOf(id.getLife()));
        ret = ret.replaceAll("%nick", id.getNick());
        ret = ret.replaceAll("%logintimes", String.valueOf(id.getLoginTimes()));
        return ret;
    }
}