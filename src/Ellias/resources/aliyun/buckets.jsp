<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-8-28
  Time: 10:44:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.aliyun.oss.OSSClient" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.aliyun.oss.model.*" %>
<%@ page import="com.saille.aliyun.OssUtils" %>
<%@ page import="java.util.Collections" %>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>OSS bucket</title></head>
  <body>
  <%!
      static DecimalFormat df = new DecimalFormat("#,##0");
      static DecimalFormat df2 = new DecimalFormat("#,##0.00");
      String getStorageDesc(StorageClass in) {
          String name = in.toString();
          if(name.equals("Standard")) {
              return "标准";
          } else if(name.equals("Archive")) {
              return "归档";
          } else if(name.equals("IA")) {
              return "低频";
          } else {
              return "待定";
          }
      }
      String getSizeDesc(long size) {
          double value = size;
          if(value < 1024) {
              return df2.format(value) + "&nbsp;B";
          }
          value = value / 1024;
          if(value < 1024) {
              return df2.format(value) + "&nbsp;K";
          }
          value = value / 1024;
          if(value < 1024) {
              return df2.format(value) + "&nbsp;M";
          }
          value = value / 1024;
          if(value < 1024) {
              return df2.format(value) + "&nbsp;G";
          }
          value = value / 1024;
          return df2.format(value) + "&nbsp;T";
      }
  %>
        <%
            if("POST".equals(request.getMethod())) {
                String bucket = request.getParameter("bucket");
                String key = request.getParameter("key");
                List<OSSObjectSummary> files = OssUtils.listFiles(bucket, key);
                if(files.size() != 1) {
//                    todo
                }
                OSSObjectSummary file = files.get(0);
                if(file.getKey().endsWith("/")) {
//                    todo
                }
                if(!file.getStorageClass().equals("Standard")) {
//                    todo
                }
                long size = file.getSize();
                if(size > (1024 * 1024 * 20)) { //>10M，下载到临时文件

                } else { //<=10M，直接使用流下载
                    byte[] bytes = OssUtils.download(bucket, key, (int)size);
                    response.addHeader("Content-Disposition", "attachment; filename=" + new String(key.substring(key.lastIndexOf("/") + 1).getBytes("GBK"), "ISO-8859-1"));
                    response.getOutputStream().write(bytes);
                    out.clear();
                    out = pageContext.pushBody();
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
            final String accessKeyId = OssUtils.getAccessKeyId();
            final String accessKeySecret = OssUtils.getAccessKeySecret();
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            List<Bucket> buckets = ossClient.listBuckets();
            String bucketname = request.getParameter("bucket");
            if(StringUtils.isBlank(bucketname)) {
                bucketname = "ellias-persistent";
            }
            String prefix = request.getParameter("prefix");
            if(prefix == null) {
                prefix = "";
            }
            ListObjectsRequest req = new ListObjectsRequest();
            req.setBucketName(bucketname);
            req.setPrefix(prefix);
            req.setDelimiter("/");
            ObjectListing list = ossClient.listObjects(req);
            List<String> dirs = list.getCommonPrefixes();
            Collections.sort(dirs);
            List<OSSObjectSummary> files = list.getObjectSummaries();
            files = OssUtils.sortObject(files, 0, files.size());
            String parentpath = "";
            if(prefix.indexOf("/") != prefix.lastIndexOf("/")) {
                parentpath = prefix.substring(0, prefix.substring(0, prefix.length() - 1).lastIndexOf("/")) + "/";
            }
            String[] prefixs = prefix.split("/");
            ossClient.shutdown();
        %>
        <%
            for(Bucket b : buckets) {
        %>
        <input type="button" value="<%=b.getName() + "\n" + getStorageDesc(b.getStorageClass())%>" onclick="document.location='buckets.jsp?bucket=<%=b.getName()%>';"/>
        <%
            }
        %>
        <br/>当前bucket:<br/><span style="color:red;font-weight:bold;"><%=bucketname%></span>
        <br/>当前目录:<br/>
        <h4>
            <a style="color:blue;" href="buckets.jsp?bucket=<%=bucketname%>">/</a>
            <%
                String currPrefix = "";
                for(int i = 0; i < prefixs.length; i++) {
                    if(i > 0) {
                        out.print("/");
                        currPrefix += "/";
                    }
                    currPrefix += prefixs[i];
            %>
            <a style="color:blue;" href="buckets.jsp?bucket=<%=bucketname%>&prefix=<%=currPrefix%>/"><%=prefixs[i]%></a>
            <%
                }
            %>
        </h4>
    <table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="scoredetail">
        <tr class="head">
            <th>名字</th>
            <th>大小</th>
            <th>大小</th>
            <th>修改时间</th>
            <th>操作</th>
        </tr>
        <tr class="row2">
            <td>
                <a style="color:blue;" href="buckets.jsp?bucket=<%=bucketname%>&prefix=<%=parentpath%>">
                    ..
                </a>
            </td>
            <td></td><td></td><td></td><td></td>
        </tr>
        <%
            for(int i = 0; i < dirs.size(); i++) {
                String dir = dirs.get(i);
        %>
        <tr class="row<%=i % 2 + 1%>">
            <td>
                <a style="color:blue;" href="buckets.jsp?bucket=<%=bucketname%>&prefix=<%=dir%>"><%=dir.substring(prefix.length(),dir.length() - 1)%></a>
            </td>
            <td></td><td></td><td></td><td></td>
        </tr>
        <%
            }
            for(int i = 0; i < files.size(); i++) {
                OSSObjectSummary file = files.get(i);
                if(file.getKey().endsWith("/")) {
                    continue;
                }
        %>
        <tr class="row<%=(dirs.size() + i + 1) % 2 + 1%>">
            <td>
                <%=file.getKey().substring(prefix.length())%>
            </td>
            <td align="right">
                <%=df.format(file.getSize())%>
            </td>
            <td align="right">
                <%=getSizeDesc(file.getSize())%>
            </td>
            <td>
                <%=sdf.format(file.getLastModified())%>
            </td>
            <td>
                <input type="button" onclick="downloadKey('<%=bucketname%>', '<%=file.getKey()%>');" value="下载">
            </td>
        </tr>
        <%
            }
        %>
    </table>
  <script type="text/javascript">
      function downloadKey(bucket, key)
      {
          document.getElementsByName('bucket')[0].value = bucket;
          document.getElementsByName('key')[0].value = key;
          document.forms[0].submit();
      }
  </script>
  <form action="" method="post">
      <input type="hidden" name="bucket"/>
      <input type="hidden" name="key"/>
  </form>
  </body>
</html>