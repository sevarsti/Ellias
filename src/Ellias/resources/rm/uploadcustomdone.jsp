<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.saille.core.dao.BaseJdbcDao" %>
<%@ page import="com.saille.sys.dao.EmployeeDao" %>
<%@ page import="java.io.File" %>
<%@ page import="com.saille.sys.Setting" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="com.saille.aliyun.OssUtils" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/09/08 0008
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>finish upload custom song</title>
</head>
<%!
    List<String> sortKey(Map<String, double[]> map) {
        List<String> ret = new ArrayList<String>();
        for(String k : map.keySet()) {
            boolean added = false;
            double[] src = map.get(k);
            for(int i = 0; i < ret.size(); i++) {
                double[] queue = map.get(ret.get(i));
                if(src[2] < queue[2]) {
                    ret.add(i, k);
                    added = true;
                    break;
                } else if(src[2] > queue[2]) {
                    continue;
                } else {
                    if(src[1] < queue[1]) {
                        ret.add(i, k);
                        added = true;
                        break;
                    } else if(src[1] > queue[1]) {
                        continue;
                    } else {
                        if(src[0] < queue[0]) {
                            ret.add(i, k);
                            added = true;
                            break;
                        }
                    }
                }
                continue;
            }
            if(!added) {
                ret.add(k);
            }
        }
        return ret;
    }
%>
<%
    Map<String, String> params = (Map<String, String>)request.getSession().getAttribute("rm_customsong_param");
    String name = params.get("name");
    String path = params.get("path");
    String author = params.get("author");
    String memo = params.get("memo");
    double bpm = Double.parseDouble(params.get("bpm"));
    String mp3md5 = params.get("md5");
    byte[] mp3Bytes = (byte[]) request.getSession().getAttribute("rm_customsong_mp3bytes");
    int length = Integer.parseInt(params.get("length"));
    Map<String, byte[]> files = (Map<String, byte[]>)request.getSession().getAttribute("rm_customsong_imdbytes");
    Map<String, double[]> ranks = (Map<String, double[]>)request.getSession().getAttribute("rm_customsong_imdranks"); //key, rank, difficulty
    Map<String, String> imdmd5 = (Map<String, String>)request.getSession().getAttribute("rm_customsong_imdmd5s");
    byte[][] pngBytes = (byte[][])request.getSession().getAttribute("rm_customsong_imgs"); //0=小图，1=大图


    /* 保存数据库信息 */
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    BaseJdbcDao dao = (BaseJdbcDao) GlobalContext.getContextBean(EmployeeDao.class);
    int songid;

    /* 检查mp3的md5是否存在 */
    boolean mp3exist = false;
    List<Map<String, Object>> list = jt.queryForList("select * from rm_customsong where md5 = ? and path = ?", new Object[]{mp3md5, path});
    if(list.size() > 0) {
        songid = ((Number)list.get(0).get("id")).intValue();
        mp3exist = true;
    } else {
        songid = dao.getId("RM_CUSTOMSONG");
    }
    System.out.println("保存自制谱信息");
    List<String> sortedkey = sortKey(ranks);
    int prvkey = 0;
    int keycount = 1;
    Object[] objs;
    List<Object[]> insertparams = new ArrayList<Object[]>();
    for(int i = 0; i < sortedkey.size(); i++) {
        String key = sortedkey.get(i);
        objs = new Object[6];
        objs[0] = songid;
        int newkey = (int)ranks.get(key)[2];
        objs[1] = newkey;
        if(newkey == prvkey) {
            keycount++;
            if(keycount > 3) {
                out.println(prvkey + "key的谱面数量超过3个");
                return;
            }
        } else {
            prvkey = newkey;
            keycount = 1;
        }
        objs[2] = keycount;
        objs[3] = ((double)((int)(ranks.get(key)[0] * 1000))) / 1000d;
        objs[4] = ((double)((int)(ranks.get(key)[1] * 1000))) / 1000d;
        objs[5] = imdmd5.get(key);
        insertparams.add(objs);
    }
    for(Object[] obj : insertparams) {
        jt.update("insert into rm_customsongimd(songid, `key`, `level`, rank, difficulty, imdmd5) values(?,?,?,?,?,?)", obj);
    }

    if(!mp3exist) {
        objs = new Object[7];
        objs[0] = name;
        objs[1] = path;
        objs[2] = author;
        objs[3] = mp3md5;
        objs[4] = length;
        objs[5] = songid;
        objs[6] = memo;
        objs[7] = bpm;
        jt.update("insert into rm_customsong(name, path, author, md5, length, id, memo, bpm) values(?,?,?,?,?,?,?,?)", objs);

    }
    /* 保存文件 */
    System.out.println("保存文件");
    Map<String, byte[]> imdnames = new HashMap<String, byte[]>();
    String dir = Setting.getSettingString("RM_PATH") + "zizhi" + File.separator + path;

    if(!mp3exist) {
        File directory = new File(dir);
        directory.mkdirs();
        File mp3file = new File(dir + File.separator + path + ".mp3");
        mp3file.createNewFile();
        FileOutputStream fos = new FileOutputStream(mp3file);
        fos.write(mp3Bytes);
        fos.close();

        File pngfile = new File(dir + File.separator + path + ".png");
        pngfile.createNewFile();
        fos = new FileOutputStream(pngfile);
        fos.write(pngBytes[1]);
        fos.close();

        File pnghdfile = new File(dir + File.separator + path + "_title_ipad.png");
        pnghdfile.createNewFile();
        fos = new FileOutputStream(pnghdfile);
        fos.write(pngBytes[0]);
        fos.close();
    }

    prvkey = 0;
    keycount = 1;
    for(int i = 0; i < sortedkey.size(); i++) {
        String imdkey = sortedkey.get(i);
        int newkey = (int)ranks.get(imdkey)[2];
        if(newkey == prvkey) {
            keycount++;
        } else {
            prvkey = newkey;
            keycount = 1;
        }
        String level = "";
        if(keycount == 3) {
            level = "hd";
        } else if(keycount == 2) {
            level = "nm";
        } else {
            level = "ez";
        }
        String filename = path + "_" + newkey + "k_" + level + ".imd";
        File imdfile = new File(dir + File.separator + filename);
        imdfile.createNewFile();
        FileOutputStream fos = new FileOutputStream(imdfile);
        fos.write(files.get(imdkey));
        fos.close();
        imdnames.put(filename, files.get(imdkey));
    }

    /* 保存OSS */
    System.out.println("上传OSS");
    for(String key : imdnames.keySet()) {
        OssUtils.uploadFile("ellias-ia", "rm/zizhi/" + path + "/" + key, imdnames.get(key));
    }
    if(!mp3exist) {
        OssUtils.uploadFile("ellias-ia", "rm/zizhi/" + path + "/" + path + ".mp3", mp3Bytes);
        OssUtils.uploadFile("ellias-ia", "rm/zizhi/" + path + "/" + path + ".png", pngBytes[1]);
        OssUtils.uploadFile("ellias-ia", "rm/zizhi/" + path + "/" + path + "_title_ipad.png", pngBytes[0]);
    }

    /* 清理session */
    request.getSession().removeAttribute("rm_customsong_param");
    request.getSession().removeAttribute("rm_customsong_mp3bytes");
    request.getSession().removeAttribute("rm_customsong_imdbytes");
    request.getSession().removeAttribute("rm_customsong_imdranks");
    request.getSession().removeAttribute("rm_customsong_imdmd5s");
    request.getSession().removeAttribute("rm_customsong_imgs");

    out.print("文件保存完成，路径：" + path);
%>
<body>

</body>
</html>
