package com.saille.rm.loop;

import com.saille.sys.BaseThread;
import com.saille.rm.StarmallExchange;
import com.saille.rm.DownloadZipUtil;
import com.saille.rm.util.RMUtils;
import com.google.common.io.LittleEndianDataInputStream;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;

import servlet.GlobalContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-4-27
 * Time: 16:10:25
 * To change this template use File | Settings | File Templates.
 */
public class WeekSThread extends BaseThread {
    private static WeekSThread instance = null;
    protected WeekSThread(int interval) {
        super(interval);
    }

    public static void main(String[] args) {
        WeekSThread.getInstance(0).execute();
    }

    public static synchronized WeekSThread getInstance(int interval) {
        if(instance == null) {
            instance = new WeekSThread(interval);
            instance.setDaemon(true);
        }
        return instance;
    }

    public int execute() {
        try {
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);

            DownloadZipUtil.download();
            File f = new File("D:\\rm\\TableComBin\\mrock.mission_client.bin");
            FileInputStream fis = new FileInputStream(f);
            LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
            dis.skip(8);
            int unitLength = dis.readInt(); //每个单元的长度
            int count = dis.readInt(); //歌曲数量
            dis.skip(120);
            byte[] bytes = new byte[unitLength];
            for(int i = 0; i < count; i++) {
                dis.read(bytes);
//                RMUtils.printBytes(bytes, 0, bytes.length, true);
                String name = RMUtils.getString(bytes, 4, 127);
//10	玩转原创
//14	BOOM!
//17	律动节奏（星动）
//18	欧美精选
//22	乐享旋律
//23	五一大挑战
//42	动漫频道
//43	女团来袭
//47	韩日精选
//57	S大挑战
//8	周末大挑战
                int offset = RMUtils.getInt(bytes, 0, 4);
                int type = RMUtils.getInt(bytes, 132, 4);
                int beginrow = RMUtils.getInt(bytes, 392, 4);
                int endrow = RMUtils.getInt(bytes, 396, 4);
                int resetcost = RMUtils.getInt(bytes, 404, 4);
                String level = RMUtils.getString(bytes, 136, 20);
                String begintime = RMUtils.getString(bytes, 264, 20);
                String endtime = RMUtils.getString(bytes, 328, 20);
                int i1 = RMUtils.getInt(bytes, 414, 1); //1=星动
                if(i1 == 1) {
                    continue;
                }
                int c = jt.queryForInt("select count(1) from rm_weeks where totalindex = ? and startdate = ? and enddate = ? and name = ?",
                                       new Object[]{offset, begintime, endtime, name});
                if(c > 0) {
                    continue;
                } else {
                    for(int j = beginrow; j <= endrow; j++) {
                        FileInputStream fis2 = new FileInputStream("D:\\rm\\TableComBin\\mrock.floornode_client.bin");
                        fis2.skip(8);
                        fis2.skip(128);
                        byte[] bytes2 = new byte[16];
                        int targettype = 0;
                        int targetnumber = 0;
                        while(fis2.read(bytes2) > 0) {
                            int offset2 = RMUtils.getInt(bytes2, 0, 4);
                            if(offset2 == j + 10000) {
                                targettype = RMUtils.getInt(bytes2, 4, 4);
                                targetnumber = RMUtils.getInt(bytes2, 8, 4);
                                break;
                            }
                        }
                        fis2.close();
                        String sql = "insert into rm_weeks(totalindex, subindex, startdate, enddate, type, resetcost, level, name, targettype, targetnumber, updatetime, removetag) values(?,?,?,?,?,?,?,?,?,?,now(), 0)";
                        jt.update(sql, new Object[]{offset, j, begintime, endtime, type, resetcost, level, name, targettype, targetnumber});
                    }
                }
                System.out.println(offset + "\t" + i1 + "\t" + type+"\t"+beginrow + "\t" + endrow +"\t"+resetcost+"\t"+level+"\t"+begintime+"\t"+endtime + "\t"+name);
            }
            return 0;
        } catch(Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
}
