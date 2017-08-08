package com.saille.bbs.yssy.dao;

import com.saille.bbs.yssy.BoardRecord;

public abstract interface IBoardRecordDao {
    public abstract void save(BoardRecord paramBoardRecord);

    public abstract BoardRecord getBoardRecord(String paramString, int paramInt);
}