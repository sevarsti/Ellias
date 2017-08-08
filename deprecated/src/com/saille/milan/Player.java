package com.saille.milan;

import com.sinitek.dao.domain.IdEntity;
import com.sinitek.dao.domain.ClassDescription;
import com.sinitek.dao.domain.PropertyDescription;

import java.sql.Types;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-5-13
 * Time: 12:42:13
 * To change this template use File | Settings | File Templates.
 */
@ClassDescription(table = "MIL_PLAYER")
public class Player extends IdEntity {
    private String name;

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getName() {
        return name;
    }

    @PropertyDescription(persistant = true)
    public void setName(String name) {
        this.name = name;
    }
}
