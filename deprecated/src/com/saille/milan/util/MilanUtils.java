package com.saille.milan.util;

import com.saille.milan.dao.MilanPlayerDao;
import com.saille.milan.MilanPlayer;

import java.util.List;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-7-11
 * Time: 1:17:03
 * To change this template use File | Settings | File Templates.
 */
public class MilanUtils {
    public static List getAllPlayers() {
        MilanPlayerDao dao = (MilanPlayerDao) GlobalContext.getContextBean(MilanPlayerDao.class);
        List<MilanPlayer> list = dao.getAll();
        return list;
    }
}
