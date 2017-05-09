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
     					   str = str +"<div class='pic'><a href='getMessage.do?messageNo="+item.messageNo+"' class='w_hover'>";
     					   str = str +"<img src='"+item.photoUri+"' alt='' /><span></span></a></div>";
     					   str = str +"<div class='text'><p class='title'><a href='getMessage.do?messageNo="+item.messageNo+"'>";
     					   str = str +item.messageTitle+"</a></p>"; 
     					   str = str +"<div class='date'><p>"+item.createDateString+"</p></div>";
     					   str = str +"<div class='icons'><ul>";  //后面补充count<a href='#' class='views'>56</a></li><li>
     					   str = str +"<li><a class='comments'>"+item.cmtCount+"</a></li></ul></div></div></div>";//后面补充count

         				   if(i%2 == 0){

         					   str = str +"<div class='line_3' style='margin:14px 0px 17px;'></div>";
         					   $(".block_home_col_1").append(str);   					   
         				   }else if(i%2 == 1){
         					   
         					   str = str +"<div class='line_3' style='margin:14px 0px 17px;'></div>";
         					   $(".block_home_col_2").append(str);
         				   }
     					   
     				   }

     			   });
     		   }
     	   });
       })();
  	   
        $(document).ready(function(){    
        	
      	   $.ajax({ 		  
     		   type:"get",
     		   dataType:"json",
     		   url:"specialTopic.do",
     		   cache:true,
     		   
     		   success:function(data){
     			   var items = jQuery.parseJSON(data);
     			   if(items.status == "200"){
     				   $(".block_special_topic .type p").append(items.result.appName);
     				   $(".block_special_topic .title p").append("<a>"+items.result.appValue+"</a>");
     			   }
     		   }
     		   });
        
     	  var page =1;
     	  var pageSize = 4;
     	  var pageCount = loadMessage(page,pageSize);
     	  $(".prev").click(function(){ //上一页
     		  if(page > 1){
     			 page--;
     			 loadMessage(page,pageSize);
     		  }else{
     			  alert("已经到第一页了");
     			  return false;
     		  }
     	  });
     	  
     	  $(".next").click(function(){//下一页
     		  if(page < pageCount){
      			 page++;
     			 loadMessage(page,pageSize);  
     		  }else{
     			  alert("已经到最后一页了");
     			  return false;
     		  }

     	  });
        });
        
     function loadMessage(page,pageSize){

    	  var pageCount = 0;
        	$.ajax({		  
        		  type:"post",
        		  dataType:"json",
        		  data:{"page":page,"pageSize":pageSize},
        		  url:"getAllMessages.do",
        		  cache:true,
        		  async:false,
        		  
        		  success:function(data){
        			  var item =jQuery.parseJSON(data);
        			  if(item.status == "200"){
        				  $(".block_topic_news").empty();
        				  var pagin = item.result;
        				  $.each(pagin.items,function(i,msg){
        					  
         				      var str = "<article class='block_topic_post'><p class='title'><a href='getMessage.do?messageNo="+msg.messageNo+"'>";      
         				      str = str + msg.messageTitle+ "</a></p><div class='f_pic'><a href='getMessage.do?messageNo="+msg.messageNo+"' class='general_pic_hover scale'>";
         				      str = str + "<img src='"+msg.photoUri+"' alt='' /></a></div><p class='text'>";
         				      var content = msg.messageContent;
         				      if(content.length > 80){
         				    	  content = content.substring(0,80);
         				    	  content = content +"......";
         				      }
         				      str = str + content+"</p>";
         				      str = str + "<div class='info'><div class='date'><p>"+msg.createDateString+"</p></div>";
         				      str = str + "<div class='r_part'><div class='category'><p><a href='cate_detail.jsp?cateNo="+msg.secondMessageCategory.categoryNo+"'>"+msg.secondMessageCategory.categoryName;
         				      str = str + "</a></p></div><a class='views'>"+msg.cmtCount+"</a></div></div></article>";
         				      $(".block_topic_news").append(str); 
        			  });
        				  if(pagin.pagesCount <= 1){
        					  $("#page").remove();
        				  }
      				     pageCount = pagin.pagesCount;
        			 }
        		  }
        	});
        	
        	return pageCount;
        };
        
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
                        	<div class="type"><p></p></div>
                            <div class="title"><p></p></div>
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
                        
                        <h3 style="font-size:16px;">推荐消息</h3>
                        <div class="line_4" style="margin:-4px 0px 18px;"></div>
                        
                        <div class="block_topic_news">
                            
                        </div>
                        
                        <div class="line_3" style="margin:20px 0px 24px;"></div>
                        
                        <div class="block_pager" id="page">
                        	<a href="javascript:;" class="prev">Previous</a>
                            <a href="javascript:;" class="next">Next</a>                          
                            
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