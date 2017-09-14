<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<script type="text/javascript">
  function login(){
	  var formData = $('form').serializeArray();
	  $.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:formData,
		  url:"login.do",
		  cache:true,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
			  $(".remember").empty();
			  if(item.status == "200"){ 
				  window.location = "index.jsp";
			  }else{
				  $(".remember").append("用户名或密码不正确");
			  }
		}
	  });
  };

</script>
     <!-- POPUP BEGIN -->
    <div id="overlay"></div>
    <div id="login" class="block_popup">
    	<div class="popup">
        	<a href="#" class="close">Close</a>
            
            <div class="content">
            	<div class="title"><p>登录</p></div>
                
                <div class="form" >
                	<form id="loginForm"  method="post">
                    	<div class="column">
                        	<p class="label">用户名</p>
                            <div class="field"><input id="username"  name="username" type="text" class="input"/></div>
                        </div>
                        
                        <div class="column">
                        	<p class="label">密码</p>
                            <div class="field"><input type="password" id="password" name="password" class="input"/></div>
                        </div>
						
						<div class="column_2">
                            <div class="remember" style="color: red;">
                            </div>
                        </div>
                        
                        <div class="column_2">
                           
                        </div>
                        <div class="column button">  
                               <input type="button" class="general_button standart type_1" value="登录" onclick="login();"/>                                 
                        </div>      
                        <div class="clearboth"></div>
                    </form>
                </div>
                
                <div class="subtitle"><p>第三方登录</p></div>
                
                <div class="fb_button">       
                <div style="width: 50px;float: left;">
                <img src="resources/images/login/QQ.png" alt="" style="margin:-15px 0 0 -5px;height: 40px;width: 40px;"/>
                </div>
                <div style="width: 50px;float: left;">
                <img src="resources/images/login/wx.png" alt="" style="margin:-10px 0 0 -5px;height: 30px;width: 30px;"/>
                </div>
                <div style="width: 50px;float: left;">
                <img src="resources/images/login/xl.png" alt="" style="margin:-10px 0 0 -5px;height: 30px;width: 30px;"/>
                </div>
                </div>           
            </div>
        </div>
    </div>
    <!-- POPUP END -->