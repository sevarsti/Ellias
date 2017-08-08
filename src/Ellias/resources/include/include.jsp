<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2008-4-28
  Time: 16:47:55
  To change this template use File | Settings | File Templates.
--%>
<%--
  通用的Include页面
--%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html-el.tld" prefix="html-el" %>
<%@ taglib uri="/WEB-INF/struts-bean-el.tld" prefix="bean-el" %>
<%@ taglib uri="/WEB-INF/struts-logic-el.tld" prefix="logic-el" %>
<%@ taglib uri="/WEB-INF/struts-tiles-el.tld" prefix="tiles-el" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/string-1.1" prefix="str" %>
<%@ taglib prefix="sys" uri="http://www.ellias.com/taglib/sys"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib prefix="functions" uri="http://www.ellias.com/taglib/common"%>
<%@ taglib prefix="hhcq" uri="http://www.ellias.com/taglib/hhcq"%>
<%@ taglib prefix="milan" uri="http://www.ellias.com/taglib/milan"%>
<%@ taglib prefix="bwdl" uri="http://www.ellias.com/taglib/bwdl"%>
<%@ taglib prefix="pampers" uri="http://www.ellias.com/taglib/pampers"%>
<LINK type="text/css" rel="stylesheet" href="/include/main.css">
<%@ page contentType="text/html;charset=gbk" language="java"%>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/RMDwr.js'></script>
<script type='text/javascript' src='../scripts/tablesort.js'></script>
