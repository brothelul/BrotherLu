<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
<title>BusinessNews</title>

<%@include file="js&css.jsp" %>

<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <%@include file="header.jsp" %>
    	<!-- HEADER END -->
        
        <!-- CONTENT BEGIN -->
        <div id="content" class="">
        	<div class="inner">
            	<div class="general_content">
                	<div class="main_content">
                    	<div class="block_breadcrumbs">
                        	<div class="text"><p>You are here:</p></div>
                            
                            <ul>
                            	<li><a href="index.html">Home</a></li>
                                <li>Registration</li>
                            </ul>
                        </div>
                        <div class="separator" style="height:28px;"></div>
                        
                        <p class="general_title"><span>用户注册</span></p>
                        <div class="separator" style="height:39px;"></div>
                        
                        <div class="block_registration">
                        	<form action="user/regist.do" class="w_validation">
                            	<div class="col_1">
                                	<div class="label"><p>用户名<span>*</span>:</p></div>
                                    <div class="field"><input type="text" class="req" placeholder="用户名长度大于2小于10" /></div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:14px;"></div>                                  
                                    
                                    <div class="label"><p>密码<span>*</span>:</p></div>
                                    <div class="field"><input type="password" class="req" /></div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:12px;"></div>
                                    
                                    <div class="label"><p>重复密码<span>*</span>:</p></div>
                                    <div class="field"><input type="password" class="req" /></div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:12px;"></div>
                                    
                                    <div class="label"><p>邮箱<span>*</span>:</p></div>
                                    <div class="field"><input type="text" class="req" /></div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:12px;"></div>
                                    
                                    <div class="label"><p>验证码<span>*</span>:</p></div>
                                    <div class="field"><img/></div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:40px;"></div>
                                    
                                    <div class="label"><input type="checkbox"/></div>
                                    <div class=""><p class="info_text">同意用户注册协议 (<a href="#">用户注册协议</a>)</p></div>
                                    <div class="clearboth"></div>
                                </div>                               
                                
                                <div class="clearboth"></div>
                                <div class="separator" style="height:32px;"></div>
                                <p class="info_text"><input type="submit" class="general_button" value="马上注册" /></p>

                            </form>
                                <p class="subtitle">其他登录</p> 
                                <div class="line_3" style="margin:10px 0px 10px;"></div>       
                                <div class="fb_button"><a href="#"><img src="images/button_fb_login.png" alt="" /></a></div>
                        </div>
                        
                        
                    </div>
                    
  
                </div>
            </div>
        </div>
    	<!-- CONTENT END -->
        
        <!-- FOOTER BEGIN -->
        <%@include file="footer.jsp" %>
        <!-- FOOTER END -->
    </div>
    
    <!-- POPUP BEGIN -->
    <%@include file="loginPopup.jsp" %>
    <!-- POPUP END -->
</body>

</html>