package com.saille.newBBS;

import com.saille.core.BaseEntity;
import com.sinitek.dao.domain.ClassDescription;

@ClassDescription(table = "Board")
public class Board extends BaseEntity {
    private String boardName;
    private int group;
    private String chineseName;
}