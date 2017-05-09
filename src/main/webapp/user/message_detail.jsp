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
 				   $("#more").remove();
 				   $.each(pagin.items,function(i,cmt){
 					   str = str + "<div class='comment'><div class='userpic'><a><img src='"+cmt.userInfo.faviconUri+"' alt='' style='margin:-8px 0 0 -5px;height: 40px;border-radius:35px;width: 40px;'/></a></div><div class='content'>";
 					   str = str + "<p class='name'><a href='toUserPage.do?userNo="+cmt.userInfo.userNo+"'>"+cmt.userInfo.username+"</a></p>";
 					   str = str + "<p class='info'><span class='date'>"+cmt.createDateString+"</span></p>";
 					   str = str + "<p class='text'>"+cmt.cmtContent+"</p></div><div class='clearboth'></div><div class='line_3'></div></div>";
 				   });
 				  
 				   if(cmtCount == 0){
 					   str = str +"<div class='comment'>还没有评论，抢个沙发吧...</div>"
 					   $(".block_comments").append(str);
 				   }else{
 					   $(".block_comments").append(str);
 					   if(pagin.pagesCount > page){
 	 					   $(".block_comments").append("<div style='font-size: 14px;line-height: 30px;text-align: center;background-color: #f4f5f6;margin-top: 10px;cursor: pointer;' id='more'><center><a href='javascript:;' onclick='loadMore();' style='color: #406599;'>点击加载更多评论</a></center></div>");
 					   }
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
 	
 	function loadMore(){
 		var page = 1;
		page++;
    	loadComments(page,6);
 	}
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
                                	<div class="category"><p><a href="cate_detail.jsp?cateNo=${message.secondMessageCategory.categoryNo}">${message.secondMessageCategory.categoryName}</a></p></div>
                                    <a class="comments" id="cmtCount"><span></span></a>
                                </div>
                            </div>
                            
                            <div class="content">
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