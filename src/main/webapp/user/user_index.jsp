<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<title>Around You</title>
<%@include file="js&css.jsp" %>
<script type="text/javascript">
$(document).ready(function(){    	   
	   var page = 1;
	   var pageSize = 6;
	   loadUserMessage(page,pageSize);
});

function loadUserMessage(page,pageSize){
	$.ajax({		  
		  type:"post",
		  dataType:"json",
		  data:{"page":page,"pageSize":pageSize,"type":2},
		  url:"loadAuthorMessages.do",
		  cache:true,
		  
		  success:function(data){
			  var item =jQuery.parseJSON(data);
	   	      var str = "";
			  if(item.status == "200"){
				  var pagin = item.result;
				  $.each(pagin.items,function(i,msg){
						   str = str + "<div class='block_author'><div class='photo'><a href='#'>";
						   str = str + "<img src='"+msg.photoUri+"' alt=''' /></a></div><div class='bio'>";
						   str = str + "<p><b><a href='getMessage.do?messageNo="+msg.messageNo+"'>"+msg.messageTitle+"</a></b></p><a class='general_button w_icon registration'>";
						   str = str + "<span>"+msg.userInfo.username+"</span></a><span class='date'>"+msg.createDateString+"</span>";
						   str = str + "<a class='comments' id='cmtCount'><span>"+msg.cmtCount+"</span></a></div><div class='line_3' style='margin:10px 0px 17px;'></div><div class='clearboth'></div></div>";
				  });
				  
				 if(pagin.pagesCount > page){
	 					str = str +"<div style='font-size: 14px;line-height: 30px;text-align: center;background-color: #f4f5f6;margin-top: 0px;cursor: pointer;' id='more'><center><a href='javascript:;' onclick='loadMore();' style='color: #406599;'>点击加载更多消息</a></center></div>";					   
				 }
				  if(pagin.rowsCount == 0){
					  str = str + "<div class='block_author' style='font-size:16px;'><div class='bio'><p><b>该用户暂时还没有发布任何信息。</b></p></div></div>";
				  }				  
			  }
			   if(item.status == "400"){
				   str = str + "<div class='block_author' style='font-size:16px;'><div class='bio'><p><b>该用户暂时还没有发布任何信息。</b></p></div></div>";
			   }
			   $(".main_content").append(str);
		  }
	});
};

function loadMore(){
	$("#more").remove();
	var page = 1;
	page++;
	loadUserMessage(page,6);
}
</script>

<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <%@include file="edit_header.jsp" %>       
        <!-- CONTENT BEGIN -->
        <div id="content" class="right_sidebar">
        	<div class="inner">
            <div class="general_content">
            	<div class="main_content">
            	<div class="block_author" style="font-size:16px;"><div class="bio"><p><b>他发布的消息</b></p></div></div>                      
                        <div class="line_4" style="margin:0px 0px 20px;"></div>                       
                        <div class="clearboth"></div>
                       </div>   
                                                                              
                      <%@include file="side.jsp" %>                                       
                	   <div class="clearboth"></div>
                </div>
                </div>
                </div>
    	<!-- CONTENT END -->
        <!-- FOOTER BEGIN -->
        <%@include file="footer.jsp" %>
    </div>
        <%@include file="loginPopup.jsp" %>
</body>

</html>