<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Around You</title>

<%@include file="js&css.jsp" %>
        <script type="text/javascript">
  
        (function(){    	       	   

     	   $.ajax({
     		   
     		   type:"get",
     		   dataType:"json",
     		   url:"getHotMessages.do",
     		   cache:true,
     		   
     		   success:function(data){
     			   
     			   var item = eval(data);
     			   
     			   $.each(item,function(i,item){
     				 	
     			       // 页面加载时获取旋转框信息
     				   if(i<5){
          				 
     					   var str = "<li><div class='slide'><img src='"+item.photoUri+"' alt='"+item.messageName+"'/><div class='caption'><p class='title'>"+item.messageTitle+"</p><p>"+item.messageContent+"</p></div></div></li>";
                           $("#sliders").append(str);
     				   }else{
     			     	   //获取热门消息
     					   var str ="<div class='block_home_post'>";
     					   str = str +"<div class='pic'><a href='news_post.html' class='w_hover'>";
     					   str = str +"<img src='"+item.photoUri+"' alt='' /><span></span></a></div>";
     					   str = str +"<div class='text'><p class='title'><a href='news_post.html'>";
     					   str = str +item.messageTitle+"</a></p>"; 
     					   str = str +"<div class='date'><p>"+item.createDateString+"</p></div>";
     					   str = str +"<div class='icons'><ul>";  //后面补充count<a href='#' class='views'>56</a></li><li>
     					   str = str +"<li><a href='#' class='views'>56</a></li></ul></div></div></div>";//后面补充count

         				   if(i<8){

         					   if(i != 7){str = str +"<div class='line_3' style='margin:14px 0px 17px;'></div>";}
         					   $(".block_home_col_1").append(str);    					   
         				   }else if(i>7 && i < 11){
         					   
         					   if(i != 10){str = str +"<div class='line_3' style='margin:14px 0px 17px;'></div>";}
         					   $(".block_home_col_2").append(str);
         				   }
     				   }

     			   });
     		   }
     	   });
     	   
     	   //获取所有消息
     	   $.ajax({
     		  
     		   type:"get",
     		   dataType:"json",
     		   url:"getAllMessages.do",
     		   cache:true,
     		   
     		   success:function(data){
     			   
     			   var item = eval(data);
     			   
     			   $.each(item,function(i,item){
     				   
     				   if(i<4){
     					   
     				      var str = "<article class='block_topic_post'><p class='title'><a href='news_post.html'>";      
     				      str = str + item.messageTitle+ "</a></p><div class='f_pic'><a href='news_post.html' class='general_pic_hover scale'>";
     				      str = str + "<img src='"+item.photoUri+"' alt='' /></a></div><p class='text'>"+item.messageContent+"</p>";
     				      str = str + "<div class='info'><div class='date'><p>"+item.createDateString+"</p></div>";
     				      str = str + "<div class='r_part'><div class='category'><p><a href='#'>"+item.secondMessageCategory.categoryName;
     				      str = str + "</a></p></div><a href='#' class='views'>183</a></div></div></article>";
     				      $(".block_topic_news").append(str); 
     				   }
     			   });
     			   
     			   return false;
     	       }
     	   });
     	   
        })();
        
        </script>

<body>
	<div class="wrapper sticky_footer">

        <%@include file="header.jsp" %>
        
        
        <!-- CONTENT BEGIN -->
        <div id="content" class="right_sidebar">
        	<div class="inner">
            	<div class="general_content">
                	<div class="main_content">
                    	<div class="block_special_topic">
                        	<div class="type"><p>每日鸡汤</p></div>
                            <div class="title"><p><a href="#">到头来，我们记住的，不是敌人的攻击，而是朋友的沉默。——马丁·路德·金</a></p></div>
                        </div>
                        <div class="separator" style="height:17px;"></div>
                        
                        <div class="block_home_slider">
                        	<div id="home_slider" class="flexslider">
                            	<ul class="slides" id="sliders">
                                </ul>
                            </div>
                            
                            <script type="text/javascript">
                            
                                //旋转木马
								$(function () {
									$('#home_slider').flexslider({
										animation : 'slide',
										controlNav : true,
										directionNav : true,
										animationLoop : true,
										slideshow : true,
										animationSpeed:800,
										slideshowSpeed:2000,
										pauseOnHover:true,
										useCSS : false
									});
									
								});
							</script>
                        </div>
                        
                        <div class="line_2" style="margin:34px 0px 28px;"></div>
                        
                        <div class="block_home_col_1"></div>                        
                        <div class="block_home_col_2"></div>
                        <div class="clearboth"></div>
                        
                        <div class="line_3" style="margin:14px 0px 13px;"></div>
                        <a href="main_news.html" class="lnk_all_news fl">更多热门</a>
                        <div class="clearboth"></div>
                        <div class="line_3" style="margin:13px 0px 35px;"></div>
                        
                        <h3 style="font-size:16px;">推荐消息</h3>
                        <div class="line_4" style="margin:-4px 0px 18px;"></div>
                        
                        <div class="block_topic_news">

<!--                             
                            <article class="block_topic_post">
                            	<p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors now use.</a></p>
                                <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale"><img src="resources/images/pic_home_main_news_2.jpg" alt="" /></a></div>
                                <p class="text">There are many variations of passages of available, but the majority have alteration.</p>
                                <div class="info">
                                	<div class="date"><p>11 July, 2012</p></div>
                                    
                                    <div class="r_part">
                                    	<div class="category"><p><a href="#">Business</a></p></div>
                                        <a href="#" class="views">183</a>
                                    </div>
                                </div>
                            </article>
                            
                            <article class="block_topic_post">
                            	<p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors now use.</a></p>
                                <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale"><img src="resources/images/pic_home_main_news_3.jpg" alt="" /></a></div>
                                <p class="text">There are many variations of passages of available, but the majority have alteration.</p>
                                <div class="info">
                                	<div class="date"><p>11 July, 2012</p></div>
                                    
                                    <div class="r_part">
                                    	<div class="category"><p><a href="#">Business</a></p></div>
                                        <a href="#" class="views">183</a>
                                    </div>
                                </div>
                            </article>
                            
                            <article class="block_topic_post">
                            	<p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors now use.</a></p>
                                <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale"><img src="resources/images/pic_home_main_news_4.jpg" alt="" /></a></div>
                                <p class="text">There are many variations of passages of available, but the majority have alteration.</p>
                                <div class="info">
                                	<div class="date"><p>11 July, 2012</p></div>
                                    
                                    <div class="r_part">
                                    	<div class="category"><p><a href="#">Business</a></p></div>
                                        <a href="#" class="views">183</a>
                                    </div>
                                </div>
                            </article> -->
                            
                        </div>
                        
                        <div class="line_3" style="margin:20px 0px 24px;"></div>
                        
                        <div class="block_pager">
                        	<a href="#" class="prev">Previous</a>
                            <a href="#" class="next">Next</a>
                            
                            <div class="pages">
                            	<ul>
                                	<li class="current"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#">4</a></li>
                                    <li><a href="#">5</a></li>
                                    <li><a href="#">6</a></li>
                                </ul>
                            </div>
                            
                            <div class="clearboth"></div>
                        </div>
                        
                        <div class="line_2" style="margin:24px 0px 35px;"></div>                       
                        
                    </div>
 
                      <%@include file="side.jsp" %>
                      
                   </div>
            </div>
        </div>
    	<!-- CONTENT END -->
        
        <%@include file="footer.jsp" %>
        
    </div>
       
    <%@include file="loginPopup.jsp" %>
       
</body>
</html>