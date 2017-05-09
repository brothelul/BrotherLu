<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
   
 <script type="text/javascript">
 
 // 页面加载时获取分类信息
  (function(){	   
	   
	   $.ajax({
		   
		   type:"get",
		   dataType:"json",
		   url:"weather.do",
		   cache:true,
		   
		   success:function(data){

                var item =jQuery.parseJSON(data);                			 				   
                $(".fl div ul").append("<li class='current'><a>"+item.cityName+"</a></li><li><a>"+item.lowTemp+"°/"+item.highTemp+"°</a></li><li><a>"+item.weather+"</a></li><li><a>"+item.quality+"</a></li>");		                

		   }
	   });
   })();
 
 </script>   
    	<!-- HEADER BEGIN -->
        <header>
            <div id="header">
            	<section class="top">
                	<div class="inner">
                    	<div class="fl">
                        	<div class="block_top_menu">
                            	<ul>
                                </ul>
                            </div>
                        </div> 
                        
                        <c:if test="${userInfo != null}">
                            <div class="fr">
                        	<div class="block_top_menu">
                        	  <ul>
                        	    <li><a><img src="${userInfo.faviconUri}" alt="" style="margin:-8px 0 0 -5px;height: 30px;border-radius:20px;width: 30px;"/></a></li>
                        	    <li class="current"><a href="toUserPage.do?userNo=${userInfo.userNo}">${userInfo.username}</a></li>
                        	    <li><a href="logout.do">退出</a></li>
                        	  </ul>
                        	</div>
                        	</div>
                        </c:if>
                        <c:if test="${userInfo == null }">
                           <div class="fr">
                        	<div class="block_top_menu">
                            	<ul>
                                	<li class="current"><a href="#login" class="open_popup">登录</a></li>
                                    <li><a href="registration.jsp">注册</a></li>
                                </ul>
                            </div>
                        </div>
                        </c:if>                        
                    	<div class="clearboth"></div>
                    </div>
                </section>
                
            	<section class="bottom">
                	<div class="inner">                       
                        <div class="block_today_date">
                        	<div class="num"><p id="num_top" /></div>
                            <div class="other">
                            	<p class="month_year"><span id="month_top"></span>, <span id="year_top"></span></p>
                                <p id="day_top" class="day" />
                            </div>
                        </div>                                                 
                        <div class="clearboth"></div>
                    </div>
                </section>
                 <section class="section_main_menu">
                	<div class="inner">
                    	<nav class="main_menus">                 	    
                        	<ul>
                        	    <li class="common"><a><img src="${author.faviconUri}" alt="" style="margin:-36px 0 0 -19px;height: 80px;border-radius:38px;width: 80px;"/></a></li>
								<li class="common"><a href="toUserPage.do?userNo=${author.userNo}">${author.username}</a></li>
								<li class="common1">
								<a href="createMessage.jsp">
								<c:if test="${author.userNo == userInfo.userNo}">
								<c:if test="${author.userType == 1}">
								 发布信息    
								</c:if>
								<c:if test="${author.userType == 0}">
								 申请发布信息    
								</c:if>
								</c:if>
								</a>
								</li>
						    </ul>
						</nav>
                    </div>
                </section>
            </div>
        </header>
<!-- HEADER END -->