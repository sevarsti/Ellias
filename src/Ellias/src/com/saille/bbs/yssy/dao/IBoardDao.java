package com.saille.bbs.yssy.dao;

import com.saille.bbs.yssy.Board;

public abstract interface IBoardDao {
    public abstract void save(Board paramBoard);

    public abstract Board get(String paramString);
}