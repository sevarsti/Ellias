package com.saille.rm.action;

import servlet.AbstractDispatchAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saille.rm.form.ChangeBpmForm;
import com.saille.rm.BPMChange;
import com.saille.rm.MusicCut;
import com.saille.rm.RMConstant;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Adler32;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-4-15
 * Time: 13:56:01
 * To change this template use File | Settings | File Templates.
 */
public class ChangeBpmAction extends AbstractDispatchAction {
    public ActionForward cut(ActionMapping mapping,
                                ActionForm _form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ChangeBpmForm form = (ChangeBpmForm) _form;
        try {
            String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            int begin = form.getBegin();
            int end = form.getEnd();

            if(begin < 0 || end < 0) {
                form.setMsg("开始时间和结束时间不能小于0");
                return mapping.findForward("error");
            }
            if(end == 0) {
                form.setMsg("结束时间必须大于0");
                return mapping.findForward("error");
            }
            if(begin >= end) {
                form.setMsg("结束时间必须大于开始时间");
                return mapping.findForward("error");
            }
            FormFile imd = form.getCutimd();
            FormFile mp3 = form.getCutmp3();

            if(imd == null || mp3 == null) {
                form.setMsg("imd和mp3文件不能为空");
                return mapping.findForward("error");
            }

            if(form.getRatio() > 2 || form.getRatio() <= 0) {
                form.setMsg("变速范围必须在0-2之间");
            }

            File f = new File(RMConstant.storepath + File.separator + now);
            f.mkdirs();
            String imdname = imd.getFileName();
            String mp3name = mp3.getFileName();

            InputStream is = imd.getInputStream();
            FileOutputStream fos = new FileOutputStream(RMConstant.storepath + File.separator + now + "\\in.imd");
            byte[] tmp = new byte[1024];
            while(is.read(tmp) > 0) {
                fos.write(tmp);
            }
            fos.close();
            is.close();

            is = mp3.getInputStream();
            fos = new FileOutputStream(RMConstant.storepath + File.separator + now + "\\in.mp3");
            while((is.read(tmp) > 0)) {
                fos.write(tmp);
            }
//            while((i = is.read()) >= 0) {
//                fos.write(i);
//            }
            fos.close();
            is.close();

            int[] offsets = MusicCut.changeImd(RMConstant.storepath + File.separator + now + "\\in.imd", now,  begin * 1000, (end - begin) * 1000);
            MusicCut.changemp3(RMConstant.storepath + File.separator + now + "\\in.mp3", now, offsets[0], offsets[1]);

            FileInputStream fis = new FileInputStream("D:\\temp\\" + now + "_result.mp3");
            fos = new FileOutputStream(RMConstant.storepath + File.separator + now + "\\cut.mp3");
            while(fis.read(tmp) > 0) {
                fos.write(tmp);
            }
            fis.close();
            fos.close();

            fis = new FileInputStream("D:\\temp\\" + now + "_result.imd");
            fos = new FileOutputStream(RMConstant.storepath + File.separator + now + "\\cut.imd");
            while(fis.read(tmp) > 0) {
                fos.write(tmp);
            }
            fis.close();
            fos.close();

            new File("D:\\temp\\" + now + "_result.imd").delete();
            new File("D:\\temp\\" + now + "_result.mp3").delete();

            BPMChange.changeImd(RMConstant.storepath + File.separator + now + "\\cut.imd", now, form.getRatio());
            BPMChange.changeMp3(RMConstant.storepath + File.separator + now + "\\cut.mp3", RMConstant.storepath + File.separator + now + "\\" + mp3name, form.getRatio());

            fis = new FileInputStream("D:\\temp\\" + now + "_changebpm.imd");
            fos = new FileOutputStream(RMConstant.storepath + File.separator + now + "\\" + imdname);
            int count = 0;
            while((count = fis.read(tmp)) > 0) {
                fos.write(tmp, 0, count);
            }
            request.setAttribute("mp3path", (now + File.separator + mp3name).replaceAll("\\\\", "/"));
            request.setAttribute("imdpath", (now + File.separator + imdname).replaceAll("\\\\", "/"));
            fis.close();
            fos.close();
//            doZip(new String[]{"d:\\temp\\musiccut\\" + now + "\\" + imdname, "d:\\temp\\musiccut\\" + now + "\\" + mp3name}, zipFilename, "d:\\temp\\musiccut\\" + now);

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return mapping.findForward("success");
    }

    public ActionForward change(ActionMapping mapping,
                                ActionForm _form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        ChangeBpmForm form = (ChangeBpmForm) _form;
        String filename = BPMChange.change(form.getSong(), form.getRatio(), request.getRemoteAddr());
        File f = new File("D:\\temp\\" + filename + ".zip");

        try {
            byte[] bytes = (form.getSong() + "-" + form.getRatio() + ".zip").getBytes("GBK");
            String encoded_ = new String(bytes, "ISO-8859-1");
            response.setContentType("application/zip");
            response.setHeader("Pragma", "public");
            response.setHeader("Cache-Control", "max-age=0");
            response.setHeader("Content-disposition", "attachment; filename="+ encoded_);
            OutputStream op = response.getOutputStream();

            FileInputStream fis = new FileInputStream(f);
            FileCopyUtils.copy(fis, response.getOutputStream());
            op.flush();
            op.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
