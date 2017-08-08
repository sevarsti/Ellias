package com.pay.data.calc;

import java.math.BigDecimal;

public class APBigNum
{
  public static BigDecimal add(double paramDouble1, double paramDouble2)
  {
    return new BigDecimal(paramDouble1).add(new BigDecimal(paramDouble2));
  }

  public static BigDecimal add(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    return paramBigDecimal1.add(paramBigDecimal2);
  }

  public static BigDecimal divide(double paramDouble1, double paramDouble2)
  {
    return new BigDecimal(paramDouble1).divide(new BigDecimal(paramDouble2));
  }

  public static BigDecimal divide(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    return paramBigDecimal1.divide(paramBigDecimal2);
  }

  public static BigDecimal multiply(double paramDouble1, double paramDouble2)
  {
    return new BigDecimal(paramDouble1).multiply(new BigDecimal(paramDouble2));
  }

  public static BigDecimal multiply(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    return paramBigDecimal1.multiply(paramBigDecimal2);
  }

  public static BigDecimal subtract(double paramDouble1, double paramDouble2)
  {
    return new BigDecimal(paramDouble1).subtract(new BigDecimal(paramDouble2));
  }

  public static BigDecimal subtract(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2)
  {
    return paramBigDecimal1.subtract(paramBigDecimal2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.data.calc.APBigNum
 * JD-Core Version:    0.6.0
 */