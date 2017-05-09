<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
<title>Around You</title>

<%@include file="js&css.jsp" %>
<script type="text/javascript">
function changeImg(){	
	var checkImg=document.getElementById("checkImg");
	checkImg.src="getValidateCodePng.do? date="+new Date().getTime();
};

function agree(){
	var checked = $("input[type='checkbox']").prop("checked");
	$("#registation").removeAttr("class");
	$("#registation").removeAttr("disabled");
	if(checked){
		$("#registation").addClass("general_button standart");
	}else{
		$("#registation").addClass("general_button standart type_5");
		$("#registation").attr("disabled","disabled");
	}
};

function getUsername(){
	var username = $("#username").val();
    var flag = false;
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"username":username},
		  url:"validateUsername.do",
		  async:false,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
			  $("#code_validate").empty();
			  if(item.status == "200"){
				  flag = true;
			  }else{
				  alert("用户名已存在");
			  }
			  }
	});
	
	return flag;
};

function validateUsername(){
	var username = $("#username").val();
	$("#un_validate").empty();
	var result = !getUsername();
	if(username.length < 2 || username.length > 10 ||result ){
		$("#un_validate").append("<img src='resources/layout/images/validate_failed.png'/>");
		return false;
	}else{
		$("#un_validate").append("<img src='resources/layout/images/validate_success.png'/>");
		return true;
	}
};

function validatePsw(){
	var psw = $("#password").val();
	$("#pw_validate").empty();
	if(psw.length < 6 || psw.length > 30){
		$("#pw_validate").append("<img src='resources/layout/images/validate_failed.png'/>");
		return false;
	}else{
		$("#pw_validate").append("<img src='resources/layout/images/validate_success.png'/>");
		return true;
	}
};

function validateRPsw(){
	var repsw = $("#repassword").val();
	var psw = $("#password").val();
	$("#rpw_validate").empty();
	if(psw!=repsw || repsw == ""){
		$("#rpw_validate").append("<img src='resources/layout/images/validate_failed.png'/>");
		return false;
	}else{
		$("#rpw_validate").append("<img src='resources/layout/images/validate_success.png'/>");
		return true;
	}
};

function validateEmail(){
	var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	var email_val = $("#email").val();
	$("#email_validate").empty();
	if(!search_str.test(email_val) || email_val == ""){       	
		alert("请输入正确的邮箱格式");	 
		$("#email_validate").append("<img src='resources/layout/images/validate_failed.png'/>");
		$('#email').focus();	 
		return false;
	 }else{
		$("#email_validate").append("<img src='resources/layout/images/validate_success.png'/>");
		return true;
	}
};

function validateCode(){
    var code = $("#code").val();
    var flag = false;
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"code":code},
		  url:"validateCode.do",
		  async:false,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
			  $("#code_validate").empty();
			  if(item.status == "200"){
				  $("#code_validate").append("<img src='resources/layout/images/validate_success.png'/>");
				  flag = true;  
			  }else{
				  $("#code_validate").append("<img src='resources/layout/images/validate_failed.png'/>");
			  }
		  }
		  });
	return flag;
};

function registUser(){
	var username = $("#username").val();
	var password = $("#password").val();
	var code = $("#code").val();
	var email = $("#email").val();

	$.ajax({		  
		  type:"post",
		  url:"registUser.do",
		  dataType:"json",
		  data:{"code":code,"username":username,"password":password,"email":email},
		  
		  success:function(data){
			  var item = jQuery.parseJSON(data);
			  $(".main_content").empty();
			  if(item.status == "200"){
				  $(".main_content").append("<div style='text-align: center;'><h3>注册成功！</h3></div>");
				  $('#overlay').show();
				  $('#login').show();
				  $(target).show(500);
			  }else{
				  $(".main_content").append("<div style='text-align: center;'><h3>注册失败！请按照提示操作！</h3></div>");
			  }
			  }
		  });
};

function registSuccess(){	

 	var cv = validateCode(); 
 	var unv = validateUsername();
	var pwv = validatePsw();
	var rpwv = validateRPsw();
	var emv = validateEmail();

	if(unv&&pwv&&rpwv&&cv&&emv){
		registUser();
	}
};


</script>
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
                        
                        <p class="general_title"><span>用户注册</span></p>
                        <div class="separator" style="height:39px;"></div>
                        
                        <div class="block_registration">
                        	<form action="user/regist.do" class="w_validation">
                            	<div class="col_1">
                                	<div class="label"><p>用户名<span>*</span>:</p></div>
                                    <div class="field">
                                         <div style="float:left;border:0px none;width:90%;height:100%;">
                                           <input type="text" class="text req" placeholder="用户名长度大于2小于8" onblur="validateUsername();" id="username"/>
                                         </div>
                                         <div style="float:left;border:0px none;width:10%;height:100%;" id="un_validate"></div>
                                     </div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:14px;"></div>                                  
                                    
                                    <div class="label"><p>密码<span>*</span>:</p></div>
                                    <div class="field">
                                    <div style="float:left;border:0px none;width:90%;height:100%;">
                                            <input type="password" class="text req" placeholder="密码长度大于6小于30" id="password" onblur="validatePsw();"/>
                                     </div>
                                     <div style="float:left;border:0px none;width:10%;height:100%;" id="pw_validate"></div>
                                     </div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:12px;"></div>
                                    
                                    <div class="label"><p>重复密码<span>*</span>:</p></div>
                                    <div class="field">
                                      <div style="float:left;border:0px none;width:90%;height:100%;">
                                            <input type="password" class="text req" placeholder="再输入一次密码" id="repassword" onblur="validateRPsw();"/>
                                     </div>
                                     <div style="float:left;border:0px none;width:10%;height:100%;" id="rpw_validate"></div> 
                                     </div>  
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:12px;"></div>                                  
                                    
                                    <div class="label"><p>邮箱<span>*</span>:</p></div>
                                    <div class="field">
                                    <div style="float:left;border:0px none;width:90%;height:100%;">
                                            <input type="text" class="text req" placeholder="填写常用邮箱" id="email" onblur="validateEmail();"/>
                                     </div>
                                     <div style="float:left;border:0px none;width:10%;height:100%;" id="email_validate"></div>
                                     </div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:12px;"></div>
                                    
                                    <div class="label"><p>验证码<span>*</span>:</p></div>
                                    <div class="field">
                                    <div style="float:left;border:0px none;width:45%;height:100%;">
                                    <input type="text" class="req textcode" placeholder="点击验证码即可刷新" id="code" onblur="validateCode();"/>
                                    </div>
                                    <div style="float:left;border:0px;width:45%;background-size: cover; -moz-background-size: cover;" >
                                    <img id="checkImg" src="getValidateCodePng.do"  onclick="changeImg();" title="点击更换验证码" />
                                    </div>
                                    <div style="float:left;border:0px;width:10%;background-size: cover; -moz-background-size: cover;" id="code_validate">
                                    </div>
                                    </div>
                                    <div class="clearboth"></div>
                                    <div class="separator" style="height:40px;"></div>
                                    
                                    <div class="label"><input type="checkbox" onclick="agree();"/></div>
                                    <div class=""><p class="info_text">同意用户注册协议 (<a href="#">用户注册协议</a>)</p></div>
                                    <div class="clearboth"></div>
                                </div>                               
                                
                                <div class="clearboth"></div>
                                <div class="separator" style="height:32px;"></div>
                                <p class="info_text"><input type="button" class="general_button standart type_5" value="马上注册" id="registation" disabled="disabled" onclick="registSuccess();"/></p>

                            </form>
                                <p class="subtitle"><b>其他登录</b></p> 
                                <div class="line_3" style="margin:10px 0px 10px;"></div>       
                                <div class="fb_button">               
                                <div style="width: 50px;float: left;">
                                <img src="resources/images/login/QQ.png" alt="" style="margin:-5px 0 0 -5px;height: 40px;width: 40px;"/>
                               </div>
                               <div style="width: 50px;float: left;">
                               <img src="resources/images/login/wx.png" alt="" style="margin:0 0 0 -5px;height: 30px;width: 30px;"/>
                               </div>
                               <div style="width: 50px;float: left;">
                               <img src="resources/images/login/xl.png" alt="" style="margin:0 0 0 -5px;height: 30px;width: 30px;"/>
                               </div>
                                
                                </div>
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
    <%@include file="rloginPopup.jsp" %>
    <!-- POPUP END -->
</body>

</html>