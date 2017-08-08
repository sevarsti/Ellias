package com.saille.hhcq.util;

import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Guojia;
import com.saille.hhcq.Leibie;
import com.saille.hhcq.Shangpin;
import com.saille.hhcq.dao.HhcqDao;
import com.saille.util.SortUtils;

import java.util.List;

import org.springframework.context.ApplicationContext;
import servlet.GlobalContext;

public class HhcqUtils {
    public static Guojia getGuojia(int id) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        return dao.getGuojia(id);
    }

    public static Leibie getLeibie(int id) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        return dao.getLeibie(id);
    }

    public static Gangkou getGangkou(int id) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        return dao.getGangkou(id);
    }

    public static Shangpin getShangpin(int id) {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        return dao.getShangpin(id);
    }

    public static List getAllGuojias() {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        return dao.getAllGuojia();
    }

    public static List getAllLeibies() {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        return dao.getAllLeibie();
    }

    public static List getAllGangkous() {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List list = dao.getAllGangkou();
        SortUtils.sortGangkou(list, 0, list.size());
        return list;
    }

    public static List getAllShangpins() {
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
        List list = dao.getAllShangpin();
        SortUtils.sortShangpin(list, 0, list.size());
        return list;
    }
}