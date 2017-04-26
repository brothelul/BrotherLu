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
	   var key = getUrlVar("keyWord");
	   loadSearchMessages(page,pageSize,key);
});

function getUrlVars(){
    var vars = [], hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    return vars;
}

//得到指定参数的value
function getUrlVar(name){
    return getUrlVars()[name];
}

function loadSearchMessages(page,pageSize,key){
	if(key == null){
		alert("请输入正确查询内容");
		return false;
	}
	   $.ajax({ 		  
		   type:"post",
		   dataType:"json",
		   data:{"page":page,"pageSize":pageSize,"key":key},
		   url:"loadSearchMessages.do",
		   cache:true,
		   
		   success:function(data){
			   var item = jQuery.parseJSON(data);			
			   var pagin = item.result;	
			   var str ="";
			   if(item.status == "200"){
				   $.each(pagin.items,function(i,msg){
					   str = str + "<div class='block_author'><div class='photo'><a href='#'>";
					   str = str + "<img src='"+msg.photoUri+"' alt=''' /></a></div><div class='bio'>";
					   str = str + "<p><b><a href='getMessage.do?messageNo="+msg.messageNo+"'>"+msg.messageTitle+"</a></b></p><a class='general_button w_icon registration' href='toUserPage.do?userNo="+msg.userInfo.userNo+"'>";
					   str = str + "<span>"+msg.userInfo.username+"</span></a><span class='date'>"+msg.createDateString+"</span>";
					   str = str + "<a class='comments' id='cmtCount'><span>"+msg.cmtCount+"</span></a></div><div class='line_3' style='margin:10px 0px 17px;'></div><div class='clearboth'></div></div>";
				   });
				   if(pagin.rowsCount == 0){
					   str = str + "<div class='block_author' style='font-size:16px;'><div class='bio'><p><b>暂时没有搜索到消息,换个关键字词试试，搜索<a style='color: #f85959;font-size:16px;' href='search.jsp?keyWord=中国'>中国</a>。</b></p></div></div>";
				   }
			   }
			   if(item.status == "400"){
				   str = str + "<div class='block_author' style='font-size:16px;'><div class='bio'><p><b>暂时没有搜索到消息,换个关键字词试试，搜索<a style='color: #f85959;font-size:16px;' href='searchMessages.do?currentPage=0&keyWord=中国'>中国</a>。</b></p></div></div>";
			   }
			   $(".main_content").append(str);
		   }
	   });
};
</script>

<body>
	<div class="wrapper sticky_footer">
    	<!-- HEADER BEGIN -->
        <%@include file="header.jsp" %>       
        <!-- CONTENT BEGIN -->
        <div id="content" class="right_sidebar">
        	<div class="inner">
        <div class="general_content">
            	<div class="main_content">
                       <div class="separator" style="height:30px;"></div>
                          <div class="block_search_top" style="margin-left: 100px;">                      
                            <form action="search.jsp" method="get">
                            	<div class="fields">
                            	<input type="text" name="keyWord" id="keyword"/>
                            	</div>
                                <button type="submit" class="buttons">开始搜索</button>                               
                                <div class="clearboth"></div>
                            </form>
                          </div>                     
                        <div class="separator" style="height:40px;"></div>

<!--                          <div class="block_author">
                        	<div class="photo"><a href="#"><img src="resources/images/ava_default_2.jpg" alt="" /></a></div>
                            <div class="bio">
                            	<p>There are many variations of passages of available, but the majority have suffered alteration in some form, by injected humour, or <b>randomised words which</b> don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.</p>
                                
                                <p class="www"><a href="#">www.websitename.com</a></p>
                                <p class="email"><a href="mailto:#" target="_blank">webvisionss@gmail.com</a></p>
                                
                                <div class="line_3" style="margin:10px 0px 17px;"></div>                               
                            </div>
                            
                            <div class="clearboth"></div>
                        </div>
                        <div class="block_author">
                        	<div class="photo"><a href="#"><img src="resources/images/ava_default_2.jpg" alt="" /></a></div>
                            <div class="bio">
                            	<p>There are many variations of passages of available, but the majority have suffered alteration in some form, by injected humour, or <b>randomised words which</b> don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text.</p>
                                
                                <p class="www"><a href="#">www.websitename.com</a></p>
                                <p class="email"><a href="mailto:#" target="_blank">webvisionss@gmail.com</a></p>
                                
                                <div class="line_3" style="margin:10px 0px 17px;"></div>                               
                            </div>
                            
                            <div class="clearboth"></div>
                        </div> -->
                                                               
                    </div>
                      <%@include file="search_side.jsp" %>                                       
                	<div class="clearboth"></div>
                </div>
            </div>
      </div>
    	<!-- CONTENT END -->
        <!-- FOOTER BEGIN -->
        <%@include file="footer.jsp" %>
    </div>
</body>

</html>