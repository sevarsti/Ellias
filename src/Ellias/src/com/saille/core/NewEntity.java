package com.saille.core;

import com.saille.util.IOUtils;
import com.saille.util.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-7-9
 * Time: 16:39:52
 * To change this template use File | Settings | File Templates.
 */
public class NewEntity {
    private static File file;
    public static void main(String[] args) {
        try {
            System.out.println("请输入实体类名：");
            String in = IOUtils.readLine();
            String entityName = in;
            System.out.println("类名为" + entityName + "，确认吗？");
            in = IOUtils.readLine();
            if(!in.equals("y")) {
                System.exit(0);
            }
            System.out.println("请输入包名(不包含com.saille)：");
            in = IOUtils.readLine();
            String packageName = in;
            System.out.println("包名为" + packageName + "，确认吗？");
            in = IOUtils.readLine();
            if(!in.equals("y")) {
                System.exit(0);
            }
            List<String[]> params = new ArrayList<String[]>();
            while(true) {
                System.out.println("请输入属性名(格式：name type)：");
                in = IOUtils.readLine();
                if(StringUtils.isEmpty(in)) {
                    break;
                }
                String[] atts = in.split(" ");
                String attName = atts[0];
                String type = atts[1];
                params.add(new String[]{attName, type});
            }
            System.out.println("类名为：" + packageName + "." + entityName);
            System.out.println("属性包含：");
            for(String[] ss : params) {
                System.out.println(ss[0] + "\t" + ss[1]);
            }
            System.out.println("确定创建吗？");
            in = IOUtils.readLine();
            if(!"y".equals(in)) {
                System.exit(0);
            }
            /************************************/
            /*创建java文件                      */
            /************************************/
            File f = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName);
            if(!f.exists()) {
                f.mkdir();
            }
            file = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\" + entityName + ".java");
            if(file.exists()) {
                System.out.println("文件已存在");
                System.exit(0);
            }
            boolean hasDate = false;
            for(String[] ss : params) {
                if("Date".equals(ss[1])) {
                    hasDate = true;
                    break;
                }
            }
            file.createNewFile();
            FileUtils.WriteFile(file, "package com.saille." + packageName + ";" + "\r\n", true);
            FileUtils.WriteFile(file, "" + "\r\n", true);
            FileUtils.WriteFile(file, "import com.saille.core.BaseEntity;\r\n", true);
            FileUtils.WriteFile(file, "import com.sinitek.dao.domain.PropertyDescription;\r\n", true);
            write("import com.sinitek.dao.domain.ClassDescription;");
            FileUtils.WriteFile(file, "import java.sql.Types;\r\n", true);
            if(hasDate) {
                FileUtils.WriteFile(file, "import java.util.Date;" + "\r\n", true);
                FileUtils.WriteFile(file, "" + "\r\n", true);
            }
            write("@ClassDescription(table = \"" + entityName.toUpperCase() + "\")");
            FileUtils.WriteFile(file, "public class " + entityName + " extends BaseEntity {" + "\r\n", true);
            for(String[] ss : params) {
                FileUtils.WriteFile(file, "    private " + ss[1] + " " + ss[0] + ";" + "\r\n", true);
            }
            FileUtils.WriteFile(file, "" + "\r\n", true);
            for(String[] ss : params) {
                String desc = "";
                if("int".equals(ss[1])) {
                    desc = "INTEGER";
                } else if("double".equals(ss[1])) {
                    desc = "DOUBLE";
                } else if("String".equals(ss[1])) {
                    desc = "VARCHAR";
                } else if("Date".equals(ss[1])) {
                    desc = "TIMESTAMP";
                }
                FileUtils.WriteFile(file, "    @PropertyDescription(persistant = true, sqlType = Types." + desc + ")" + "\r\n", true);
                FileUtils.WriteFile(file, "    public " + ss[1] + " get" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "() {" + "\r\n", true);
                FileUtils.WriteFile(file, "        return this." + ss[0] + ";\r\n", true);
                FileUtils.WriteFile(file, "    }" + "\r\n", true);
                FileUtils.WriteFile(file, "" + "\r\n", true);
                FileUtils.WriteFile(file, "    @PropertyDescription(persistant = true)" + "\r\n", true);
                FileUtils.WriteFile(file, "    public void set" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "(" + ss[1] + " " + ss[0] + ") {" + "\r\n", true);
                FileUtils.WriteFile(file, "        this." + ss[0] + " = " + ss[0] + ";" + "\r\n", true);
                FileUtils.WriteFile(file, "    }" + "\r\n", true);
            }
            FileUtils.WriteFile(file, "}" + "\r\n", true);

            /************************************/
            /*创建dao文件                      */
            /************************************/
            f = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\dao\\");
            if(!f.exists()) {
                f.mkdir();
            }
            file = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\dao\\" + entityName + "Dao.java");
            if(file.exists()) {
                System.out.println("dao文件已存在");
                System.exit(0);
            }
            file.createNewFile();
            FileUtils.WriteFile(file, "package com.saille." + packageName + ".dao;\r\n", true);
            FileUtils.WriteFile(file, "\r\n", true);
            FileUtils.WriteFile(file, "import com.saille.core.dao.BaseJdbcDao;\r\n", true);
            write("import com.saille.core.rowMapper.ObjectRowMapper;");
            write("import com.sinitek.dao.jdbc.helper.MapperUtils;");
            write("import org.springframework.jdbc.core.JdbcTemplate;");
            write("import org.apache.log4j.Logger;");
            write("import com.saille." + packageName + "." + entityName + ";");
            write("import java.util.Date;");
            write("import java.util.List;");
            FileUtils.WriteFile(file, "\r\n", true);
            write("public class " + entityName + "Dao extends BaseJdbcDao {");
            write("    private final Logger LOGGER = Logger.getLogger(getClass());");
            write("");
            write("    public " + entityName + " get(int id) {");
            write("        String sql = \"select * from `" + entityName + "` where id = ?\";");
            write("        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());");
            write("        List<" + entityName + "> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(" + entityName + ".class));");
            write("        return list.size() > 0 ? list.get(0) : null;");
            write("    }");
            write("");
            write("    public int save(" + entityName + " obj) {");
            write("        obj.setUpdateTime(new Date());");
            write("        if(obj.getId() > 0) {");
            write("            update(obj);");
            write("        } else {");
            write("            obj.setCreateTime(new Date());");
            write("            obj.setId(getId(MapperUtils.getTableNameFromAnnotation(obj.getClass())));");
            write("            insert(obj);");
            write("        }");
            write("        return obj.getId();");
            write("    }");
            write("");
            write("    public void delete(int id) {");
            write("        String sql = \"delete from `" + entityName + "` where id = ?\";");
            write("        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());");
            write("        jt.update(sql, new Object[]{id});");
            write("    }");
            write("");
            write("    public List<" + entityName + "> getAll() {");
            write("        String sql = \"select * from `" + entityName + "` order by id\";");
            write("        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());");
            write("        return jt.query(sql, new ObjectRowMapper(" + entityName + ".class));");
            write("    }");
            write("}");

            /************************************/
            /*创建spring文件                    */
            /************************************/
            file = new File("D:\\work\\Ellias\\src\\Ellias\\src\\springCfg\\spring-" + packageName + ".xml");
            if(file.exists()) {
                File backupFile = new File("D:\\work\\Ellias\\src\\Ellias\\src\\springCfg\\spring-" + packageName + ".xml.bak");
                FileOutputStream fos = new FileOutputStream(backupFile);
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr);
                String tmp;
                while((tmp = reader.readLine()) != null) {
                    if(tmp.trim().equals("</beans>")) {
                        fos.write(("    <bean id=\"" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Dao\" class=\"com.saille." + packageName + ".dao." + entityName + "Dao\">\r\n").getBytes());
                        fos.write(("        <property name=\"dataSource\">\r\n").getBytes());
                        fos.write(("            <ref bean=\"mysql_ds\"/>\r\n").getBytes());
                        fos.write(("        </property>\r\n").getBytes());
                        fos.write(("    </bean>\r\n").getBytes());
                    }
                    fos.write((tmp + "\r\n").getBytes());
                }
                reader.close();
                isr.close();
                fis.close();
                fos.close();
                file.delete();
                backupFile.renameTo(file);
            } else {
                file.createNewFile();
                write("<?xml version=\"1.0\" encoding=\"gb2312\"?>");
                write("<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\" >");
                write("<beans>");
                write("    <bean id=\"" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Dao\" class=\"com.saille." + packageName + ".dao." + entityName + "Dao\">");
                write("        <property name=\"dataSource\">");
                write("            <ref bean=\"mysql_ds\"/>");
                write("        </property>");
                write("    </bean>");
                write("</beans>");
            }

            /************************************/
            /*创建action文件                    */
            /************************************/
            f = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\action\\");
            if(!f.exists()) {
                f.mkdir();
            }
            file = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\action\\" + entityName + "Action.java");
            if(file.exists()) {
                System.out.println("action文件已存在");
                System.exit(0);
            }
            file.createNewFile();
            write("package com.saille." + packageName + ".action;");
            write("");
            write("import com.saille." + packageName + "." + entityName + ";");
            write("import com.saille." + packageName + ".dao." + entityName + "Dao;");
            write("import com.saille." + packageName + ".form." + entityName + "Form;");
            write("import java.util.List;");
            write("import servlet.GlobalContext;");
            write("import javax.servlet.http.HttpServletRequest;");
            write("import javax.servlet.http.HttpServletResponse;");
            write("import org.apache.log4j.Logger;");
            write("import org.apache.struts.action.ActionForm;");
            write("import org.apache.struts.action.ActionForward;");
            write("import org.apache.struts.action.ActionMapping;");
            write("import servlet.AbstractDispatchAction;");
            write("");
            write("public class " + entityName + "Action extends AbstractDispatchAction {");
            write("    private final Logger LOGGER = Logger.getLogger(this.getClass());");
            write("    private " + entityName + "Dao dao;");
            write("");
            write("    public " + entityName + "Action() {");
            write("        this.dao = (" + entityName +  "Dao) GlobalContext.getContextBean(" + entityName + "Dao.class);");
            write("    }");
            write("");
            write("    public ActionForward list(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {");
            write("        " + entityName + "Form form = (" + entityName + "Form) _form;");
            write("        List<" + entityName + "> list = dao.getAll();");
            write("        form.set" + entityName + "s(list);");
            write("        return mapping.findForward(\"list\");");
            write("    }");
            write("");
            write("    public ActionForward edit(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {");
            write("        " + entityName + "Form form = (" + entityName + "Form) _form;");
            write("        if(form.getId() != 0) {");
            write("            " + entityName + " obj = this.dao.get(form.getId());");
            write("            if(obj != null) {");
            for(String[] ss : params) {
                write("                form.set" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "(obj.get" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "());");
            }
            write("            }");
            write("        }");
            write("        return mapping.findForward(\"edit\");");
            write("    }");
            write("");
            write("    public ActionForward save(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {");
            write("        " + entityName + "Form form = (" + entityName + "Form) _form;");
            write("        " + entityName + " obj;");
            write("        if(form.getId() != 0) {");
            write("            obj = this.dao.get(form.getId());");
            write("            if(obj == null) {");
            write("                obj = new " + entityName + "();");
            write("            }");
            write("        } else {");
            write("            obj = new " + entityName + "();");
            write("        }");
            for(String[] ss : params) {
                write("        obj.set" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "(form.get" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "());");
            }
            write("        this.dao.save(obj);");
            write("        form.setMsg(\"保存成功\");");
            write("        return this.list(mapping, _form, request, response);");
            write("    }");
            write("");
            write("    public ActionForward delete(ActionMapping mapping, ActionForm _form, HttpServletRequest request, HttpServletResponse response) {");
            write("        " + entityName + "Form form = (" + entityName + "Form) _form;");
            write("        if(form.getId() != 0) {");
            write("            " + entityName + " obj = this.dao.get(form.getId());");
            write("            if(obj != null) {");
            write("                this.dao.delete(obj.getId());");
            write("                form.setMsg(\"删除成功\");");
            write("            } else {");
            write("                form.setMsg(\"没有对应的记录\");");
            write("            }");
            write("        }");
            write("        return this.list(mapping, _form, request, response);");
            write("    }");
            write("}");

            /************************************/
            /*创建form文件                      */
            /************************************/
            f = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\form\\");
            if(!f.exists()) {
                f.mkdir();
            }
            file = new File("D:\\work\\Ellias\\src\\Ellias\\src\\com\\saille\\" + packageName + "\\form\\" + entityName + "Form.java");
            if(file.exists()) {
                System.out.println("form文件已存在");
                System.exit(0);
            }
            file.createNewFile();
            write("package com.saille." + packageName + ".form;");
            write("");
            write("import com.saille." + packageName + "." + entityName + ";");
            write("import org.apache.struts.action.ActionForm;");
            write("import java.util.List;");
            write("");
            write("public class " + entityName + "Form extends ActionForm {");
            write("    private List<" + entityName + "> " + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "s;");
            write("    private String msg;");
            write("    private int id;");
            for(String[] ss : params) {
                write("    private " + ss[1] + " " + ss[0] + ";");
            }
            write("");
            write("    public String getMsg() {");
            write("        return this.msg;");
            write("    }");
            write("");
            write("    public void setMsg(String msg) {");
            write("        this.msg = msg;");
            write("    }");
            write("");
            write("    public int getId() {");
            write("        return this.id;");
            write("    }");
            write("");
            write("    public void setId(int id) {");
            write("        this.id = id;");
            write("    }");
            write("");
            write("    public List<" + entityName + "> get" + entityName + "s() {");
            write("        return this." + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "s;");
            write("    }");
            write("");
            write("    public void set" + entityName + "s(List<" + entityName + "> list) {");
            write("        this." + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "s = list;");
            write("    }");
            for(String[] ss : params) {
                write("");
                write("    public " + ss[1] + " get" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "() {");
                write("        return this." + ss[0] + ";");
                write("    }");
                write("");
                write("    public void set" + ss[0].toUpperCase().charAt(0) + ss[0].substring(1) + "(" + ss[1] + " " + ss[0] + ") {");
                write("        this." + ss[0] + " = " + ss[0] + ";");
                write("    }");
            }
            write("}");

            /************************************/
            /*创建struts文件                    */
            /************************************/
            file = new File("D:\\work\\Ellias\\src\\Ellias\\WEB-INF\\strutsCfg\\struts-" + packageName + ".xml");
            if(file.exists()) {
                File backupFile = new File("D:\\work\\Ellias\\src\\Ellias\\WEB-INF\\strutsCfg\\struts-" + packageName + ".xml.bak");
                FileOutputStream fos = new FileOutputStream(backupFile);
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr);
                String tmp;
                while((tmp = reader.readLine()) != null) {
                    if(tmp.trim().equals("</form-beans>")) {
                        fos.write(("        <form-bean name=\"" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Form\" type=\"com.saille." + packageName + ".form." + entityName + "Form\" />\r\n").getBytes());
                    } else if(tmp.trim().equals("</action-mappings>")) {
                        fos.write(("        <action path=\"/" + packageName + "/" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "\"\r\n").getBytes());
                        fos.write(("                type=\"com.saille." + packageName + ".action." + entityName + "Action\"\r\n").getBytes());
                        fos.write(("                name=\"" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Form\"\r\n").getBytes());
                        fos.write(("                parameter=\"method\"\r\n").getBytes());
                        fos.write(("                scope=\"request\">\r\n").getBytes());
                        fos.write(("            <forward name=\"list\" path=\"/" + packageName + "/list" + entityName + "s.jsp\"/>\r\n").getBytes());
                        fos.write(("            <forward name=\"edit\" path=\"/" + packageName + "/edit" + entityName + ".jsp\"/>\r\n").getBytes());
                        fos.write(("        </action>\r\n").getBytes());
                    }
                    fos.write((tmp + "\r\n").getBytes());
                }
                reader.close();
                isr.close();
                fis.close();
                fos.close();
                file.delete();
                backupFile.renameTo(file);
            } else {
                file.createNewFile();
                write("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
                write("<!DOCTYPE struts-config PUBLIC \"-//Apache Software Foundation//DTD Struts Configuration 1.1//EN\" \"http://jakarta.apache.org/struts/dtds/struts-config_1_1.dtd\">");
                write("<struts-config>");
                write("    <form-beans>");
                write("        <form-bean name=\"" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Form\" type=\"com.saille." + packageName + ".form." + entityName + "Form\" />");
                write("    </form-beans>");
                write("");
                write("    <action-mappings>");
                write("        <action path=\"/" + packageName + "/" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "\"");
                write("                type=\"com.saille." + packageName + ".action." + entityName + "Action\"");
                write("                name=\"" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Form\"");
                write("                parameter=\"method\"");
                write("                scope=\"request\">");
                write("            <forward name=\"list\" path=\"/" + packageName + "/list" + entityName + "s.jsp\"/>");
                write("            <forward name=\"edit\" path=\"/" + packageName + "/edit" + entityName + ".jsp\"/>");
                write("        </action>");
                write("    </action-mappings>");
                write("</struts-config>");
            }

            /************************************/
            /*创建list.jsp文件                    */
            /************************************/
            f = new File("D:\\work\\Ellias\\src\\Ellias\\resources\\" + packageName);
            if(!f.exists()) {
                f.mkdir();
            }
            file = new File("D:\\work\\Ellias\\src\\Ellias\\resources\\" + packageName + "\\list" + entityName + "s.jsp");
            if(file.exists()) {
                System.out.println("文件已存在");
                System.exit(0);
            }
            file.createNewFile();
            write("<%@ include file=\"../include/include.jsp\"%>");
            write("");
            write("<html>");
            write("    <head>");
            write("        <META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\" />");
            write("        <META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\" />");
            write("        <title>" + entityName + "一览</title>");
            write("    </head>");
            write("    <body>");
            write("        <c:set var=\"form\" value=\"${" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Form}\"/>");
            write("        <div class=\"page_title\">" + entityName + "一览</div>");
            write("        <c:if test=\"${!empty form.msg}\">");
            write("            ${form.msg}");
            write("        </c:if>");
            write("        <table width=\"100%\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\" class=\"frame\">");
            write("            <tr class=\"head\">");
            write("                <th>ID</th>");
            for(String[] ss : params) {
                write("                <th>" + ss[0] + "</th>");
            }
            write("                <th>操作</th>");
            write("            </tr>");
            write("            <c:forEach items=\"${form." + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "s}\" var=\"obj\" varStatus=\"i\">");
            write("                <tr class=\"row${i.index % 2 + 1}\">");
            write("                    <td>${obj.id}</td>");
            for(String[] ss : params) {
                write("                    <td>${obj." + ss[0] + "}</td>");
            }
            write("                    <td>");
            write("                        <input type=\"button\" class=\"otterbtn\" value=\"修改\" onclick=\"doModify(${obj.id})\"/>");
            write("                        <input type=\"button\" class=\"otterbtn\" value=\"删除\" onclick=\"doDelete(${obj.id})\"/>");
            write("                    </td>");
            write("                </tr>");
            write("            </c:forEach>");
            write("        </table>");
            write("        <input type=\"button\" value=\"增加\" onclick=\"doModify(0);\" class=\"otterbtn\"/>");
            write("        <script type=\"text/javascript\">");
            write("            function doModify(id) {");
            write("                document.location = '<%=request.getContextPath()%>/" + packageName + "/" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + ".do?method=edit&id=' + id;");
            write("            }");
            write("");
            write("            function doDelete(id) {");
            write("                if(confirm('确定要删除吗？')) {");
            write("                    document.location = '<%=request.getContextPath()%>/" + packageName + "/" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + ".do?method=delete&id=' + id;");
            write("                }");
            write("            }");
            write("        </script>");
            write("    </body>");
            write("</html>");

            /************************************/
            /*创建edit.jsp文件                  */
            /************************************/
            file = new File("D:\\work\\Ellias\\src\\Ellias\\resources\\" + packageName);
            if(!f.exists()) {
                f.mkdir();
            }
            file = new File("D:\\work\\Ellias\\src\\Ellias\\resources\\" + packageName + "\\edit" + entityName + ".jsp");
            if(file.exists()) {
                System.out.println("文件已存在");
                System.exit(0);
            }
            file.createNewFile();
            write("<%@ include file=\"../include/include.jsp\"%>");
            write("<html>");
            write("    <head>");
            write("        <META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\" />");
            write("        <META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\" />");
            write("        <title>编辑" + entityName + "</title>");
            write("    </head>");
            write("    <body>");
            write("        <c:set var=\"form\" value=\"${" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + "Form}\"/>");
            write("        <html:form action=\"/" + packageName + "/" + entityName.toLowerCase().charAt(0) + entityName.substring(1) + ".do\">");
            write("            <input type=\"hidden\" name=\"method\" value=\"save\"/>");
            write("            <html:hidden property=\"id\"/>");
            write("            <table>");
            for(String[] ss : params) {
                write("                <tr>");
                write("                    <td class=\"fieldname\">");
                write("                        " + ss[0]);
                write("                    </td>");
                write("                    <td class=\"fieldvalue\">");
                write("                        <html:text property=\"" + ss[0] + "\"/>");
                write("                    </td>");
                write("                </tr>");
            }
            write("            </table>");
            write("            <input type=\"button\" value=\"保存\" onclick=\"document.forms[0].submit();\" class=\"otterbtn\"/>");
            write("        </html:form>");
            write("    </body>");
            write("</html>");

            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE `").append(entityName).append("`(\r\n");
            sb.append("  `id` int primary key,\r\n");
            for(String[] ss : params) {
                sb.append("  `").append(ss[0]).append("`");
                if("int".equals(ss[1])) {
                    sb.append(" int");
                } else if("double".equals(ss[1])) {
                    sb.append(" float");
                } else if("String".equals(ss[1])) {
                    sb.append(" varchar(100)");
                } else if("Date".equals(ss[1])) {
                    sb.append(" datetime");
                }
                sb.append(",\r\n");
            }
            sb.append("  `createtime` timestamp, `updatetime` timestamp);");
            System.out.println(sb.toString());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void write(String content) {
        FileUtils.WriteFile(file, content + "\r\n", true);
    }
}
