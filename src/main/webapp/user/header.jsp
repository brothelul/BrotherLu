<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   
 <script type="text/javascript">
 
 // 页面加载时获取分类信息
  (function(){	   
	   $.ajax({
		   
		   type:"get",
		   dataType:"json",
		   url:"loadCategory.do",
		   cache:true,
		   
		   success:function(data){
			   
			   var item = eval(data);
			   var str ="";
			   var str1 = "<li><a>更多</a><ul>";
			   var count =0;
			   $.each(item,function(i,item){
				 
				   if(i<7){
					   str = str + "<li class='big_dropdown' data-content='business'><a href='cate_detail.jsp?cateNo="+item.categoryNo+"'>"+item.categoryName+"</a></li>";
				   }
				   if(i==7){
					   str = str+str1 + "<li><a href='cate_detail.jsp?cateNo="+item.categoryNo+"'>"+item.categoryName+"</a></li>";
				   } 
				   if(i>7){
					   str = str +"<li><a href='cate_detail.jsp?cateNo="+item.categoryNo+"'>"+item.categoryName+"</a></li>";
				   }
				   count++;
			   });
			   if(count > 7){
				   str = str +"</ul></li>";
			   }
			   $(".main_menu ul").append(str);
		   }
	   });
	   
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
                        	    <li><a><img src="${userInfo.faviconUri}" alt="" style="margin:-8px 0 0 -5px;height: 30px;border-radius:15px;width: 30px;"/></a></li>
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
                    	<nav class="main_menu">
                        	<ul>
								<li class="current_page_item"><a href="index.jsp">首页</a></li>
						  </ul>
						</nav>
                    </div>
                </section>            
                
                <section class="section_secondary_menu">
                	<div class="inner">
                        
                        <div class="block_clock">
                        	<p>时间: <span id="time"></span></p>
                        </div>
                    </div>
                </section>
            </div>
        </header>
<!-- HEADER END -->