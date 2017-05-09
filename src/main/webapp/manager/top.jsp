<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="resources/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected");
		$(this).addClass("selected");
	});	
});	
</script>


</head>

<body style="background:url(resources/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="resources/images/logo.png" title="系统首页" /></a>
    </div>

            
    <div class="topright">    
    <ul>
    <li><a href="#">上次登录:${loginLog.loginDateString}</a></li>
    <li><a href="logout.do" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${managerInfo.managerName}</span>
    </div>    
    
    </div>
</body>
</html>
