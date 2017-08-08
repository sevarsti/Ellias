package com.saille.hhcq.util;

import com.saille.hhcq.*;
import com.saille.hhcq.dao.HhcqDao;
import com.saille.util.SortUtils;
import org.apache.log4j.Logger;
import servlet.GlobalContext;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HhcqDwr {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public List<Gangkou> getGangkouByGuojia(int id) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List<Gangkou> list;
        if(id == 0) {
            list = dao.getAllGangkou();
        } else {
            list = dao.getGangkouByGuojia(id);
        }
        SortUtils.sortGangkou(list, 0, list.size());
        return list;
    }

    public List<Shangpin> getShangpinByLeibie(int id) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List<Shangpin> list;
        if(id == 0) {
            list = dao.getAllShangpin();
        } else {
            list = dao.getShangpinByLeibie(id);
        }
        SortUtils.sortShangpin(list, 0, list.size());
        return list;
    }

    public List<Object[]> getChushouByGangkou(int gangkouId) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List<Object[]> ret = new ArrayList<Object[]>();
        List<Chushou> list = dao.getChushouByGangkou(gangkouId);
        for(Chushou c : list) {
            Object[] o = new Object[2];
            Shangpin s = dao.getShangpin(c.getShangpinId());
            o[0] = s.getName();
            o[1] = c.getJiage();
            ret.add(o);
        }
        return ret;
    }

    public List<Object[]> getChushouByShangpin(int shangpinId) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List<Object[]> ret = new ArrayList<Object[]>();
        List<Chushou> list = dao.getChushouByShangpin(shangpinId);
        for(Chushou c : list) {
            Object[] o = new Object[2];
            Gangkou g = dao.getGangkou(c.getGangkouId());
            o[0] = g.getName();
            o[1] = c.getJiage();
            ret.add(o);
        }
        return ret;
    }

    public Jiage getJiage(int gangkou, int shangpin) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List jiage = dao.getJiage(gangkou, shangpin);
        return jiage.size() > 0 ? (Jiage) jiage.get(0) : null;
    }

    public List<Object[]> getTopJiage(int shangpinId) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List<Jiage> list = dao.getJiage(0, shangpinId);
        SortUtils.sortJiage(list, 0, list.size(), 1);
        List<Object[]> ret = new ArrayList<Object[]>();
        for(int i = 0; i < Math.min(10, list.size()); i++) {
            Object[] o = new Object[2];
            Gangkou g = dao.getGangkou(list.get(i).getGangkouId());
            if(g != null) {
                o[0] = g.getName();
            }
            o[1] = list.get(i).getJiage();
            ret.add(o);
        }
        return ret;
    }

    public int getJuli(int g1, int g2) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        Juli j = dao.getJuliByGangkou(g1, g2);
        return j == null ? 0 : j.getJuli();
    }

    public List<Object[]> getMaxSellProfit(int gangkouId) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        String sql = "select d.name,c.name,a.jiage,e.juli,a.jiage-b.jiage as p,(a.jiage-b.jiage )/e.juli xx\n, a.weixian from\njiage a join\n(select * from chushou where gangkouid =" + gangkouId + ") b\n" + "on a.shangpinid=b.shangpinid\n" + "join gangkou c on a.gangkouid=c.id\n" + "join shangpin d on a.shangpinid=d.id\n" + "join leibie f on f.id = d.leibie and f.id not in(6,13,28)" + "join juli e on a.gangkouid=e.gangkou1id and b.gangkouid=e.gangkou2id where e.juli<30000\n" + "order by p desc,e.juli desc limit 20";

        List<Object[]> ret = dao.getResultSetBySql(sql);
        DecimalFormat df;
        if(ret.size() > 0) {
            df = new DecimalFormat("#,##0.00");
            for(Object[] o : ret) {
                o[5] = df.format(Double.parseDouble(String.valueOf(o[5])));
            }
        }
        this.LOGGER.info("size: " + ret.size());
        return ret;
    }

    public List<Object[]> getProfitBetweenGangkou(int from, int to) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        String sql = "select e.name,c.jiage, c.jiage-b.jiage as xx,c.weixian from gangkou a join chushou b on a.id=b.gangkouid\nleft join (select * from jiage union select * from chushou) c on b.shangpinid=c.shangpinid\njoin gangkou d on c.gangkouid = d.id\njoin shangpin e on e.id=b.shangpinid\nwhere a.id = " + from + " and d.id = " + to + " order by xx desc";

        List<Object[]> ret = dao.getResultSetBySql(sql);

        this.LOGGER.info("size: " + ret.size());
        return ret;
    }

    public List<Object[]> getNoJuli(int from) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        String sql = "select id,name from gangkou where id not in(select gangkou2id from juli where gangkou1id=" + from + ") order by id";
        List<Object[]> ret = dao.getResultSetBySql(sql);
        this.LOGGER.info("size: " + ret.size());
        int size = ret.size();
        ret.add(0, new Object[]{size, size});
        return ret;
    }
}