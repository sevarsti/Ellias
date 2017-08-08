package com.saille.bbs.yssy.dao.generic;

import com.saille.bbs.yssy.Board;
import com.saille.bbs.yssy.dao.IBoardDao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BoardDaoImpl extends HibernateDaoSupport implements IBoardDao {
    public void save(Board board) {
        getHibernateTemplate().saveOrUpdate(board);
    }

    public Board get(String boardName) {
        List list = getHibernateTemplate().find(" from Board where boardName = ?", new Object[]{boardName});
        return list.size() > 0 ? (Board) list.get(0) : null;
    }
}