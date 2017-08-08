package com.saille.ogzq.activityLoop;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.saille.ogzq.OgzqUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-3
 * Time: 9:22:35
 * To change this template use File | Settings | File Templates.
 */
public class ActivityLoopThread extends Thread {
    private static ActivityLoopThread instance = null;
    private final static Logger LOGGER = Logger.getLogger(ActivityLoopThread.class);
    private static String lastCheckDate = "00000000";
    private static Map<String, String> lastExecuteDate = new HashMap<String, String>();
//    每周一：
//          砸双蛋,巅峰挑战，超级转转转
//    每周二：
//          砸双蛋,巅峰挑战,超级转转转,冠军旗帜,球员集中营
//    每周三：
//          球员集中营
//    每周四：
//          夜间重置,欧冠连锁店
//    每周五：
//          夜间重置,欧冠连锁店,大力丸
//    每周六：
//          夜间重置,大力丸
//    每周日：
//          夜间重置
    private ActivityLoopThread() {}

    public synchronized static ActivityLoopThread getInstance() {
        if(instance == null) {
            instance = new ActivityLoopThread();
        }
        return instance;
    }

    public void run() {
        LOGGER.info("启动活动检查线程");
        while(true) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
                String today = sdf.format(new Date());
                String now = sdf2.format(new Date());
//                if(today.compareTo(lastCheckDate) > 0) {
                    if(now.compareTo("1100") > 0) {
                        Calendar c = Calendar.getInstance();
                        int weekday = c.get(Calendar.DAY_OF_WEEK);
                        switch(weekday) {
                            case Calendar.MONDAY:
                                {
                                    try {
                                        if(!lastExecuteDate.containsKey("egg") || today.compareTo(lastExecuteDate.get("egg")) > 0) {
                                            LOGGER.info("进行免费砸蛋");
                                            OgzqUtils.crazyEggFree();
                                            lastExecuteDate.put("egg", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        if(!lastExecuteDate.containsKey("topchallenge") || today.compareTo(lastExecuteDate.get("topchallenge")) > 0) {
                                            LOGGER.info("进行拜仁巅峰");
                                            BayernThread.getInstance().start();
                                            lastExecuteDate.put("topchallenge", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        if(!lastExecuteDate.containsKey("superzhuanzhuan") || today.compareTo(lastExecuteDate.get("superzhuanzhuan")) > 0) {
                                            LOGGER.info("进行超级转转");
                                            OgzqUtils.superZhuanzhuan();
                                            lastExecuteDate.put("superzhuanzhuan", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                break;
                            case Calendar.TUESDAY:
                                {
                                    try {
                                        if(!lastExecuteDate.containsKey("egg") || today.compareTo(lastExecuteDate.get("egg")) > 0) {
                                            LOGGER.info("进行免费砸蛋");
                                            OgzqUtils.crazyEggFree();
                                            lastExecuteDate.put("egg", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        if(!lastExecuteDate.containsKey("topchallenge") || today.compareTo(lastExecuteDate.get("topchallenge")) > 0) {
                                            LOGGER.info("进行拜仁巅峰");
                                            BayernThread.getInstance().start();
                                            lastExecuteDate.put("topchallenge", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        if(!lastExecuteDate.containsKey("superzhuanzhuan") || today.compareTo(lastExecuteDate.get("superzhuanzhuan")) > 0) {
                                            LOGGER.info("进行超级转转");
                                            OgzqUtils.superZhuanzhuan();
                                            lastExecuteDate.put("superzhuanzhuan", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                break;
                            case Calendar.THURSDAY:
                                {
                                    try {
                                        if(!lastExecuteDate.containsKey("liansuo") || today.compareTo(lastExecuteDate.get("liansuo")) > 0) {
                                            LOGGER.info("进行连锁店");
                                            OgzqUtils.liansuo();
                                            lastExecuteDate.put("liansuo", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                break;
                            case Calendar.FRIDAY:
                                {
                                    try {
                                        if(!lastExecuteDate.containsKey("liansuo") || today.compareTo(lastExecuteDate.get("liansuo")) > 0) {
                                            LOGGER.info("进行连锁店");
                                            OgzqUtils.liansuo();
                                            lastExecuteDate.put("liansuo", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        if(!lastExecuteDate.containsKey("daliwan") || today.compareTo(lastExecuteDate.get("daliwan")) > 0) {
                                            LOGGER.info("进行大力丸");
                                            DaliwanThread.getInstance().start();
                                            lastExecuteDate.put("daliwan", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                break;
                            case Calendar.SATURDAY:
                                {
                                    try {
                                        if(!lastExecuteDate.containsKey("gezipu") || today.compareTo(lastExecuteDate.get("gezipu")) > 0) {
                                            LOGGER.info("进行格子铺");
                                            GetGridGiftThread.getInstance().start();
                                            lastExecuteDate.put("gezipu", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    try {
                                        if(!lastExecuteDate.containsKey("daliwan") || today.compareTo(lastExecuteDate.get("daliwan")) > 0) {
                                            LOGGER.info("进行大力丸");
                                            DaliwanThread.getInstance().start();
                                            lastExecuteDate.put("daliwan", today);
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                break;
                            case Calendar.SUNDAY:
                                break;
                        }
                    }
//                }
                LOGGER.info("活动线程检查结束");
                Thread.sleep(1000 * 60 * 60);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
