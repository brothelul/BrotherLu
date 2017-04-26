<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Around You</title>

<%@include file="js&css.jsp" %>
<script type="text/javascript">
 $(document).ready(function(){
	  var page = 1;
	  var pageSize = 6;
	  var pageCount =2;
	  loadComments(page,pageSize);
      $("#commentButton").click(function(){ 
    	  saveComment();
      });
      
      $("#more").click(function(){
    	  if(page<pageCount){
    		  page++;
        	  loadComments(page,pageSize);
    	  }
      });
 });

 
 function loadComments(page,pageSize){
 	$.ajax({		  
 		  type:"post",
 		  dataType:"json",
 		  data:{"page":page,"pageSize":pageSize},
 		  url:"loadComments.do",
 		  cache:true,
 		  
 		  success:function(data){
 			  var item =jQuery.parseJSON(data);
 			  if(item.status == "200"){
 				  var pagin = item.result;
 				   var cmtCount = pagin.rowsCount;
 				   pageCount = pagin.pagesCount;
 				   var str = "";
 				   if(page == 1){
 					  $(".block_comments").empty();
 					  str ="<h4>评论</h4>";
 				   }			
 				   $.each(pagin.items,function(i,cmt){
 					   str = str + "<div class='comment'><div class='userpic'><a><img src='"+cmt.userInfo.faviconUri+"' alt='' style='margin:-8px 0 0 -5px;height: 40px;border-radius:35px;width: 40px;'/></a></div><div class='content'>";
 					   str = str + "<p class='name'><a href='toUserPage.do?userNo="+cmt.userInfo.userNo+"'>"+cmt.userInfo.username+"</a></p>";
 					   str = str + "<p class='info'><span class='date'>"+cmt.createDateString+"</span><a href='#' class='control'>回复</a></p>";
 					   str = str + "<p class='text'>"+cmt.cmtContent+"</p></div><div class='clearboth'></div><div class='line_3'></div></div>";
 				   });
 				  
 				   if(cmtCount == 0){
 					   str = str +"<div class='comment'>还没有评论，抢个沙发吧...</div>"
 					   $(".block_comments").append(str);
 				   }else{
 					   $(".block_comments").append(str);
 					   $(".block_comments").append("<div class='comment'><a href='javascript:;' id='more'>点击记载更多评论</a></div>");
 					   $("#cmtContent").val('');
 				   }
 				   $("#cmtCount span").empty();
 				   $("#cmtCount span").append(cmtCount);
 			  }
 		  }
 		});
 	};

 	function saveComment(){
 		var cmtContent = $("#cmtContent").val();
 	 	$.ajax({		  
 	 		  type:"post",
 	 		  dataType:"json",
 	 		  data:{"cmtContent":cmtContent},
 	 		  url:"saveComment.do",
 	 		  cache:true,
 	 		  
 	 		  success:function(data){
 	 			  var item =jQuery.parseJSON(data);
 	 			  if(item.status == "200"){
 	 				loadComments(1,6);
 	 			  }
 	 			  if(item.status == "401"){
 	 				   alert("请登录后再发表评论");
 	 			  }			   
 	 			  if(item.status == "500"){
 	 				   alert("评论不能为空");
 	 			  }
 	 		}
 	 	});
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
                        <article class="block_single_news">
                        	<div class="f_pic"><a href="#"><img src="${message.photoUri}" alt="" /></a></div>
                          <p class="title"><a>${message.messageTitle}</a></p>                            
                            <div class="info">
                                <div class="date"><p>${message.createDateString}</p></div>
                                <div class="author" style="margin-top: 2px;">
                                <a class="general_button w_icon registration" href="toUserPage.do?userNo=${message.userInfo.userNo}"><span>${message.userInfo.username}</span></a>
                                 </div>   
                            	<div class="r_part">
                                	<div class="category"><p><a href="initCatMessages.do?catNo=${message.secondMessageCategory.categoryNo}">${message.secondMessageCategory.categoryName}</a></p></div>
                                    <a class="comments" id="cmtCount"><span></span></a>
                                </div>
                            </div>
                            
                            <div class="content">
<!--                             	<p>There are many variations of passages of available, but the majority have suffered alteration in some form, by injected humour, or <b>randomised words which</b> don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the generators on the Internet tend to repeat predefined <a href="#" class="lnk_blue"><b>chunks as necessary</b></a>, making this the first true generator on the Internet. It uses a dictionary of over words, combined with a handful of model sentence structures, to generate which looks reasonable.</p>
                                <blockquote class="full">Going to use a passage you need to be sure there isn't anything embarrassing hidden in the middle of text. established fact that a reader will be distracted by the readable content.</blockquote>
                                <p>Available, but the majority have suffered alteration.By injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. Established fact that a reader will be <a href="#" class="lnk_blue"><b>distracted by the readable</b></a> content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less.</p>
                                
                                <div class="separator" style="height:4px;"></div>
                                <h6>Post image aligned to the left</h6>
                                <div class="separator" style="height:4px;"></div>
                                <a href="#" class="pic w_frame alignleft"><img src="images/pic_blog_post_2.jpg" alt="" /></a>
                                <p>There are many variations of passages of available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage <b>need to be sure there isn't anything</b> embarrassing.</p>
                                <p>Hidden in the middle of text. All the generators on the Internet tend to repeat predefined <a href="#" class="lnk_blue"><b>chunks as necessary</b></a>, making this the.</p> -->
                                <p>${message.messageContent}</p>
                            </div>                                                  	                          
                        </article>
                        
                        <div class="block_post_tags">
                         <a href="#" class="general_button w_icon tag"><span>${message.messageName}</span></a>                         	
                        </div>
                        <div class="block_leave_reply"> 
                        <form class="w_validation" id="comment" >
                        <input type="hidden" value="${message.messageNo}" id="messageNo"/>
                        <div class="textarea"><textarea cols="1" rows="1" placeholder="说点什么..." id="cmtContent"></textarea></div> 
                        <div style="float: right;margin-bottom:10px;"> <input type="button" class="general_button" value="提交评论"  id="commentButton"/></div>
                        </form>
                        </div>                                                                   
                        <div class="line_2" style="margin:22px 0px 29px;"></div>
                        
                        <div class="block_related_posts">
                        	<h3>相关推荐</h3>
                            
                            <div class="block_main_news">
                            	<article class="block_news_post">
                                    <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale_small"><img src="images/pic_main_news_6.jpg" alt="" /></a></div>
                                  	<p class="category"><a href="#">photography</a></p>
                                    <p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                                <article class="block_news_post">
                                    <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale_small"><img src="images/pic_main_news_8.jpg" alt="" /></a></div>
                                  	<p class="category"><a href="#">sport</a></p>
                                    <p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                                <article class="block_news_post">
                                    <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale_small"><img src="images/pic_main_news_16.jpg" alt="" /></a></div>
                                  	<p class="category"><a href="#">business</a></p>
                                    <p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                                <article class="block_news_post">
                                    <div class="f_pic"><a href="news_post.html" class="general_pic_hover scale_small"><img src="images/pic_main_news_4.jpg" alt="" /></a></div>
                                  	<p class="category"><a href="#">video</a></p>
                                    <p class="title"><a href="news_post.html">Many desktop publishing packages and web page editors.</a></p>
                                    <div class="info">
                                        <div class="date"><p>11 July, 2012</p></div>
                                        <a href="#" class="views">183</a>
                                        
                                        <div class="clearboth"></div>
                                    </div>
                                </article>
                                
                            	<div class="clearboth"></div>
                            </div>
                        </div>
                        
                        <div class="line_2" style="margin:5px 0px 28px;"></div>
                        
                        <div class="block_comments">
                        </div>
                                               
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