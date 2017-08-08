package com.saille.hhcq;

import com.sinitek.dao.domain.PropertyDescription;

public class Juli {
    private int id;
    private int gangkou1Id;
    private int gangkou2Id;
    private int juli;

    @PropertyDescription(primaryKey = true, persistant = true)
    public int getId() {
        return this.id;
    }

    @PropertyDescription(primaryKey = true, persistant = true)
    public void setId(int id) {
        this.id = id;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getGangkou1Id() {
        return this.gangkou1Id;
    }

    @PropertyDescription(persistant = true)
    public void setGangkou1Id(int gangkou1Id) {
        this.gangkou1Id = gangkou1Id;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getGangkou2Id() {
        return this.gangkou2Id;
    }

    @PropertyDescription(persistant = true)
    public void setGangkou2Id(int gangkou2Id) {
        this.gangkou2Id = gangkou2Id;
    }

    @PropertyDescription(persistant = true, sqlType = 4)
    public int getJuli() {
        return this.juli;
    }

    @PropertyDescription(persistant = true)
    public void setJuli(int juli) {
        this.juli = juli;
    }
}