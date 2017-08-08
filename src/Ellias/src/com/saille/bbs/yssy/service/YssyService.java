package com.saille.bbs.yssy.service;

import com.saille.bbs.yssy.Board;
import com.saille.bbs.yssy.BoardRecord;
import com.saille.bbs.yssy.dao.IBoardDao;
import com.saille.bbs.yssy.dao.IBoardRecordDao;
import com.saille.html.HTMLUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class YssyService {
    private IBoardDao boardDao;
    private IBoardRecordDao boardRecordDao;

    public void a() {
        try {
            File f = new File("D:\\link.txt");
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String link = br.readLine();

            String content = null;
            String[] contents = null;
            String date = null;
            String boardName = null;
            while((link != null) && (!link.trim().equals(""))) {
                String url = "http://bbs.sjtu.cn/" + link;
                content = HTMLUtil.getInstance().getWeb(url, null, null);
                if(content != null) {
                    content = content.substring(content.indexOf("自动发信系统</font><font class='c37'>(") + "自动发信系统</font><font class='c37'>(".length());
                    date = content.substring(0, 4) + content.substring(5, 7) + content.substring(8, 10);
                    System.out.println(date);
                    content = content.substring(content.indexOf("平均时间</font><font class='c37'>") + "平均时间</font><font class='c37'>".length());
                    content = content.substring(0, content.indexOf("</pre>"));
                    content = content.replace("<font class='c37'>", "");
                    contents = content.split("\n");
                    for(String c : contents) {
                        BoardRecord record = new BoardRecord();
                        if((c == null) || (c.trim().equals(""))) {
                            continue;
                        }
                        System.out.println(c);
                        record.setIndex(Integer.parseInt(c.substring(0, 4).trim()));
                        record.setBoardName(c.substring(5, 20).trim());
                        Board b = getBoard(record.getBoardName());
                        if(b == null) {
                            b = new Board();
                            b.setBoardName(record.getBoardName());
                            b.setChineseName(c.substring(22, 40).trim());
                            saveBoard(b);
                        }
                        record.setDate(Integer.parseInt(date));
                        c = c.substring(40).trim();
                        record.setAvgTime(Integer.parseInt(c.substring(19).trim()));
                        c = c.substring(0, 19).trim();
                        String[] c1 = c.split(" ");
                        record.setTimes(Integer.parseInt(c1[0]));
                        int j = 1;
                        while((j < c1.length) && (c1[j].equals(""))) {
                            j++;
                        }
                        if(j < c1.length) {
                            record.setTotalTime(Double.parseDouble(c1[j].split(":")[0]));
                        }
                        BoardRecord r = getBoardRecord(record.getBoardName(), record.getDate());
                        if(r != null) {
                            record.setId(r.getId());
                        }
                        saveBoardRecord(record);
                    }
                }
                link = br.readLine();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public Board getBoard(String boardName) {
        return this.boardDao.get(boardName);
    }

    public BoardRecord getBoardRecord(String boardName, int date) {
        return this.boardRecordDao.getBoardRecord(boardName, date);
    }

    public void saveBoard(Board board) {
        this.boardDao.save(board);
    }

    public void saveBoardRecord(BoardRecord record) {
        this.boardRecordDao.save(record);
    }

    public void setBoardDao(IBoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public void setBoardRecordDao(IBoardRecordDao boardRecordDao) {
        this.boardRecordDao = boardRecordDao;
    }
}