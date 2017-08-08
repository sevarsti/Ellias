package com.saille.milan;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.domain.ClassDescription;
import java.sql.Types;
@ClassDescription(table = "MILANPLAYER")
public class MilanPlayer extends BaseEntity {
    private String name;
    private String nationality;
    private int birth;

    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getName() {
        return this.name;
    }

    @PropertyDescription(persistant = true)
    public void setName(String name) {
        this.name = name;
    }
    @PropertyDescription(persistant = true, sqlType = Types.VARCHAR)
    public String getNationality() {
        return this.nationality;
    }

    @PropertyDescription(persistant = true)
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    @PropertyDescription(persistant = true, sqlType = Types.INTEGER)
    public int getBirth() {
        return this.birth;
    }

    @PropertyDescription(persistant = true)
    public void setBirth(int birth) {
        this.birth = birth;
    }
}
