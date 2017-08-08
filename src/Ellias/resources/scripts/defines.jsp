<%--
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//说明：在程序所有模块的JavaScript中使用的路径均需使用此处定义。可修改，增加，删除。请所有修改遵守变量定义规则。
//      目录必须以"/"结尾。
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
--%>
// web根节点目录名字
<%
    String contextPath = request.getContextPath() + "/";
    String impagePath = contextPath + "images/";
    String scriptPath = contextPath + "scripts/";
    String stylePath = contextPath + "styles/";
    String utilPath = contextPath + "utils/";
    String imagePathForFramework = contextPath + "frameWork/";
%>
var AppRootPath = "<%= contextPath %>";
var rootUrl = "<%=request.getRequestURL().toString().substring(0,request.getRequestURL().toString().indexOf("portfolio")) %>";
// ImagePath
var ImagePath = "<%= impagePath %>";
//var ImagePathForFramework = ImagePath + "frameWork/";
var ImagePathForCommon = "<%=impagePath + "common/" %>";
var ImagePathForPanelBar = ImagePath + "panelbar/";
var ImagePathForUtils = "<%=impagePath +""%>";
var ImagePathForOrg = "<%=impagePath + "organization/"%>";
var ImagePathForRight = "<%=impagePath + ""%>";
var ImagePathForRschDoc = ImagePath + "RschDoc/";
var ImagePathForStkRsch = ImagePath + "StkRsch/";
var ImagePathForPortfolio = ImagePath + "Portfolio/";

// ScriptPath
var ScriptPath = "<%=scriptPath%>";
// StylePath
var StylePath = "<%= stylePath %>";
// UtilPath
var UtilPath = "<%= utilPath %>";
// PanelBarPath
var PanelBarPath = "<%= contextPath + "panelBar/" %>";

// 研究报告
var RschDocPath = AppRootPath + "rschDoc/";
// 个股研究
var StkRschPath = AppRootPath + "StkRsch/";
// 行业研究
var IndRschPath = AppRootPath + "IndRsch/";
// 组合管理
var PortfolioPath = AppRootPath + "Portfolio/";
// 机构管理
var InstitutionPath = AppRootPath + "Institution/";
// 收藏夹
var FavoritePath = AppRootPath + "Favorite/";
// 消息中心
var PrvMsgPath = AppRootPath + "prvMsg/";
// 会议管理
var MeetingPath = AppRootPath + "meeting/";

// 系统配置
var ConfigurationPath = AppRootPath + "configurations/";
// 系统配置-组织结构
var ConfigurationPathForOrg = ConfigurationPath + "organization/" ;
// 系统配置-资源权限
var ConfigurationPathForRight = ConfigurationPath + "right/";
// 系统配置-研究报告
var ConfigurationPathForRschDoc = ConfigurationPath + "RschDoc/";
//系统配置-文档类型
var ConfigurationPathForDocType = ConfigurationPath + "doctype/";

// 登录页面URL
var LogonPage = AppRootPath + "logon.jsp";
// 关闭页面URLs
var CloseWindowPage = AppRootPath + "closeWindows.htm";
// 无权访问页面URL
var NoRightPage = AppRootPath + "noRight.jsp";
// 修改密码URL
var ModifyPasswordPage = AppRootPath + "modifyPassword.jsp";
// 空白页面URL
var BlankPage = AppRootPath + "frameBlank.htm";
