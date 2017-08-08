package com.tencent.component.db.annotation;

public enum Column$ConflictAction
{
  static
  {
    ABORT = new ConflictAction("ABORT", 1);
    FAIL = new ConflictAction("FAIL", 2);
    IGNORE = new ConflictAction("IGNORE", 3);
    REPLACE = new ConflictAction("REPLACE", 4);
    ConflictAction[] arrayOfConflictAction = new ConflictAction[5];
    arrayOfConflictAction[0] = ROLLBACK;
    arrayOfConflictAction[1] = ABORT;
    arrayOfConflictAction[2] = FAIL;
    arrayOfConflictAction[3] = IGNORE;
    arrayOfConflictAction[4] = REPLACE;
    $VALUES = arrayOfConflictAction;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.db.annotation.Column.ConflictAction
 * JD-Core Version:    0.6.0
 */