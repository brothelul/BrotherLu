<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>

<script type="text/javascript">

/*       $(function(){
    	  
    	  $("#submit").click(
    		    	
        		  $.ajax({
        			  
        			  type:"post",
        			  url:"login.do",
        			  dataType:"html",
        			  data:$("#loginForm").serialize(),
        			  success:function(msg){
        				  
        				  alert(msg+$("#loginForm").serialize());
        				  if(msg == "200"){
        					  
        				  }else{
        					  $(".content").append("<font color='red'>"+msg+"</font>");
        				  }
        			  }
        		  })
          );
    	  
      }) */
</script>
     <!-- POPUP BEGIN -->
    <div id="overlay"></div>
    <div id="login" class="block_popup">
    	<div class="popup">
        	<a href="#" class="close">Close</a>
            
            <div class="content">
            	<div class="title"><p>登录</p></div>
                
                <div class="form" >
                	<form id="loginForm" method="post" action="login.do">
                    	<div class="column">
                        	<p class="label">用户名</p>
                            <div class="field"><input id="username"  name="username" type="text" /></div>
                        </div>
                        
                        <div class="column">
                        	<p class="label">密码</p>
                            <div class="field"><input type="password" id="password" name="password"/></div>
                        </div>
						
						<div class="column_2">
                            <div class="remember">
                            	<div class="checkbox"><input type="checkbox" /></div>
                                <div class="remember_label"><p>记住账号</p></div>
                            </div>
                        </div>
                        
                        <div class="column_2">
                            <p class="forgot_pass"><a href="#">忘记密码?</a></p>
                        </div>
                        
                        <div class="column button">
                          <!--     <a href="" id="submit" class="enter"><span>登录</span></a> --> 
                              <button type="submit" value="登录" class="enter"></button>                           
                        </div>      
                        <div class="clearboth"></div>
                    </form>
                </div>
                
                <div class="subtitle"><p>第三方登录</p></div>
                
                <div class="fb_button"><a href="#"><img src="resources/images/button_fb_login.png" alt="" /></a></div>
                <div class="text"><p>Use your account on the social network Facebook, to create a profile on BusinessPress</p></div>
            </div>
        </div>
    </div>
    <!-- POPUP END -->