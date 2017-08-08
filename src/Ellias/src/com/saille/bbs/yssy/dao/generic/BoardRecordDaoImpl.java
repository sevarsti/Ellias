package com.saille.bbs.yssy.dao.generic;

import com.saille.bbs.yssy.BoardRecord;
import com.saille.bbs.yssy.dao.IBoardRecordDao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BoardRecordDaoImpl extends HibernateDaoSupport implements IBoardRecordDao {
    public void save(BoardRecord record) {
        getHibernateTemplate().saveOrUpdate(record);
    }

    public BoardRecord getBoardRecord(String boardName, int date) {
        List list = getHibernateTemplate().find(" from BoardRecord where boardName = ? and date = ?", new Object[]{boardName, Integer.valueOf(date)});
        return list.size() > 0 ? (BoardRecord) list.get(0) : null;
    }
}